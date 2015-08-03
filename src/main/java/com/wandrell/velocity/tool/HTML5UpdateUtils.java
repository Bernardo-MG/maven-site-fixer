/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.wandrell.velocity.tool;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import org.apache.velocity.tools.config.DefaultKey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
 * Utilities class for fixing a Maven Site's HTML code, updating it to HTML5.
 * <p>
 * Maven Sites are created by using Doxia, which supports XHTML, and not HTML5,
 * for this reason, these sites need some fixes and updates, in order to fit
 * into the latest HTML standards.
 * <p>
 * For this various methods are offered, which will remove old and obsolete
 * patterns being used on the HTML files.
 * <p>
 * Note that these methods will only modify the contents of the {@code 
 * <body>} element if it exists, or all the received code otherwise.
 * 
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("html5UpdateTool")
public class HTML5UpdateUtils {

    /**
     * Constructs an instance of the {@code SiteUtils}.
     */
    public HTML5UpdateUtils() {
        super();
    }

    /**
     * Fixes same-file links and ids using point separators.
     * <p>
     * This is because if a link points to an id with points, that link won't
     * work.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with the links and ids fixes
     */
    public final String fixInternalLinks(final String html) {
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        removePointsFromIds(body);
        removePointsFromInternalHref(body);

        return body.html();
    }

    /**
     * Removes the externalLink class from links.
     * <p>
     * If a links ends without classes due to this, then the {@code class}
     * attribute will be removed too.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with no link having the externalLink class
     */
    public final String removeExternalLinks(final String html) {
        final Collection<Element> links; // Links to fix
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // <a> elements with the externalLink class
        links = body.select("a.externalLink");
        if (!links.isEmpty()) {
            for (final Element link : links) {
                link.removeClass("externalLink");

                if (link.classNames().isEmpty()) {
                    link.removeAttr("class");
                }
            }
        }

        return body.html();
    }

    /**
     * Removes the links with no {@code href} attribute from headings.
     * <p>
     * These links are added by Doxia to the headings.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with no link not having the href attribute
     */
    public final String removeNoHrefLinks(final String html) {
        final Collection<Element> links; // Links to fix
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // Links in headings which have no href attribute
        links = body.select("a:not([href])");
        if (!links.isEmpty()) {
            for (final Element link : links) {
                link.remove();
            }
        }

        return body.html();
    }

    /**
     * Transforms outdated source divisions to the new {@code <code>} elements.
     * Additionally, it will correct the position of the {@code 
     * 
     * 
    
    <pre>
             * } element,
             * which will me moved out of the code section.
             * <p>
             * Maven sites handle code blocks in an outdated fashion, and look like
             * this:
             * 
             * <pre>
             * {@code <div class="source">
             *    <pre>Some code
    </pre>
    
    * </div>}
     * </pre>
     * This method fixes them, transforming them into:
     * 
     * <pre>
     * {@code <pre>
     *    <source>Some code</source>
     * 
    </pre>
    
    }
     * </pre>
     * 
     * @param html
     *            HTML content to fix
     * @return HTML content, with the source blocks updated
     */
    public final String updateCodeSections(final String html) {
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        removeRedundantSourceDivs(body);
        takeOutSourceDivPre(body);
        updateSourceDivsToCode(body);

        return body.html();
    }

    /**
     * Transforms divisions with the section class into the new {@code 
     * <section>} element.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with the sections updated
     */
    public final String updateSectionDiv(final String html) {
        final Collection<Element> sectionDivs; // Section divisions
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // divs with the section class
        sectionDivs = body.select("div.section");
        if (!sectionDivs.isEmpty()) {
            for (final Element div : sectionDivs) {
                div.tagName("section");
                div.removeClass("section");

                if (div.classNames().isEmpty()) {
                    div.removeAttr("class");
                }
            }
        }

        return body.html();
    }

    /**
     * Updates the tables, applying various fixes and removed unneeded code.
     * <p>
     * This method will update the table heads, remove the border attribute,
     * remove the bodyTable class and remove the alternating rows attributes.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with the tables cleaned
     */
    public final String updateTables(final String html) {
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        updateTableClasses(body);
        updateTableHeads(body);
        removeTableBorder(body);
        removeBodyTable(body);
        updateTableRowAlternates(body);

        return body.html();
    }

    /**
     * Removes the {@code bodyTable} class from tables.
     * 
     * @param body
     *            body element with the HTML code to transform
     */
    private final void removeBodyTable(final Element body) {
        final Collection<Element> tables;   // Tables to fix

        // Tables with the bodyTable class
        tables = body.select("table.bodyTable");
        if (!tables.isEmpty()) {
            for (final Element table : tables) {
                table.removeClass("bodyTable");
                if (table.classNames().isEmpty()) {
                    table.removeAttr("class");
                }
            }
        }
    }

    /**
     * Removes points from the {@code id} attributes.
     * 
     * @param body
     *            body element with the HTML code to transform
     */
    private final void removePointsFromIds(final Element body) {
        final Collection<Element> elements; // Elements to fix
        String id;      // id attribute contents

        // Elements with the id attribute
        elements = body.select("[id]");
        if (!elements.isEmpty()) {
            for (final Element element : elements) {
                id = element.attr("id").replaceAll("\\.", "");

                element.attr("id", id);
            }
        }
    }

    /**
     * Removes points from the {@code href} attributes, if these are pointing
     * internally to the document.
     * 
     * @param body
     *            body element with the HTML code to transform
     */
    private final void removePointsFromInternalHref(final Element body) {
        final Collection<Element> links; // Links to fix
        String href;    // href attribute contents

        // Elements with an internal href
        links = body.select("[href^=\"#\"]");
        if (!links.isEmpty()) {
            for (final Element element : links) {
                href = element.attr("href").replaceAll("\\.", "");
                element.attr("href", href);
            }
        }
    }

    /**
     * Removes redundant source divisions. This is meant as a cleanup step
     * before updating the code sections.
     * <p>
     * It is a problem with Maven sites that code divisions get wrapped by
     * another code division, so they appear like this:
     * 
     * <pre>
     * {@code <div class="source">
     *    <div class="source"> 
     *       <pre>Some code
    </pre>
    
     *    </div>
     * </div>}
     * </pre>
     * This method fixes that, by removing the outer division.
     * <p>
     * Note that it is expected to have only one children with the
     * {@code source} class.
     * 
     * @param html
     *            HTML content to fix
     * @return HTML content, with the redundant source classes removed
     */
    private final void removeRedundantSourceDivs(final Element body) {
        final Collection<Element> sourceDivs; // Repeated source divs
        Collection<Element> children;         // Children source divs
        Element validDiv;                     // Fixed code block

        // Divs with the source class with another div with the source class as
        // a child
        sourceDivs = body.select("div.source:has(div.source)");
        if (!sourceDivs.isEmpty()) {
            for (final Element div : sourceDivs) {
                children = div.getElementsByClass(".source");
                if (!children.isEmpty()) {
                    validDiv = children.iterator().next();

                    validDiv.remove();

                    div.replaceWith(validDiv);
                }
            }
        }
    }

    /**
     * Removes the {@code border} attribute from {@code <table} elements.
     * <p>
     * This attribute should be defined in CSS files.
     * 
     * @param body
     *            body element with the HTML code to transform
     */
    private final void removeTableBorder(final Element body) {
        final Collection<Element> tables;   // Tables to fix

        // Selects tables with border defined
        tables = body.select("table[border]");
        if (!tables.isEmpty()) {
            for (final Element table : tables) {
                table.removeAttr("border");
            }
        }
    }

    /**
     * Moves the {@code 
     * 
     * 
    
    <pre>
     * } element out of source divisions, so it wraps said division, and not the
     * other way around.
     * <p>
     * Note that it is expected to have only one children with the {@code 
     * 
     * 
    
    <pre>
     * } tag.
     * 
     * @param body
     *            body element with the HTML code to transform
     */
    private final void takeOutSourceDivPre(final Element body) {
        final Collection<Element> divs; // Code divisions
        Collection<Element> pres;       // <pre> elements
        Element pre;                    // <pre> element
        String text;                    // Preserved text

        // Divs with the source class and a pre
        divs = body.select("div.source:has(pre)");
        if (!divs.isEmpty()) {
            for (final Element div : divs) {
                pres = div.getElementsByTag("pre");
                if (!pres.isEmpty()) {
                    pre = pres.iterator().next();

                    text = pre.text();
                    pre.text("");

                    div.replaceWith(pre);
                    pre.appendChild(div);

                    div.text(text);
                }
            }
        }
    }

    /**
     * Transforms {@code <div>} elements with the {@code source} class into
     * {@code <code>} elements.
     * 
     * @param body
     *            body element with the HTML code to transform
     */
    private final void updateSourceDivsToCode(final Element body) {
        final Collection<Element> divs; // Code divisions

        // Divs with the source class
        divs = body.select("div.source");
        if (!divs.isEmpty()) {
            for (final Element div : divs) {
                div.tagName("code");

                div.removeClass("source");
                if (div.classNames().isEmpty()) {
                    div.removeAttr("class");
                }
            }
        }
    }

    private final void updateTableClasses(final Element body) {
        final Collection<Element> tables; // Tables to fix

        // Table rows with <th> tags in a <tbody>
        tables = body.select("table.bodyTable");
        if (!tables.isEmpty()) {
            for (final Element table : tables) {
                table.removeClass("bodyTable");

                if (table.attr("class").isEmpty()) {
                    table.removeAttr("class");
                }
            }
        }
    }

    /**
     * Corrects table headers by adding a {@code <thead>} section where missing.
     * <p>
     * This method will search for {@code 
     * 
    <th>} within a {@code <tbody>} tag, but not wrapped by a {@code <thead>}.
     * <p>
     * This means it will search for headers such as:
     * 
     * <pre>
     * {@code <table>
     *    <tbody>
     *       <tr>
     *          <th>Header 1</th>
     *          <th>Header 2</th>
     *       </tr>
     *       <tr>
     *          <td>Data 1.1</td>
     *          <td>Data 1.2</td>
     *       </tr>
     *       <tr>
     *          <td>Data 2.1</td>
     *          <td>Data 2.2</td>
     *       </tr>
     *    </tbody>
     * </table>}
     * </pre>
     * <p>
     * And transforms it into:
     * 
     * <pre>
     * {@code <table>
     *    <thead>
     *       <tr>
     *          <th>Header 1</th>
     *          <th>Header 2</th>
     *       </tr>
     *    </thead>
     *    <tbody>
     *       <tr>
     *          <th>Header 1</th>
     *          <th>Header 2</th>
     *       </tr>
     *       <tr>
     *          <td>Data 1.1</td>
     *          <td>Data 1.2</td>
     *       </tr>
     *       <tr>
     *          <td>Data 2.1</td>
     *          <td>Data 2.2</td>
     *       </tr>
     *    </tbody>
     * </table>}
     * </pre>
     * 
     * This serves to fix an error with tables created by some Maven report
     * plugins.
     * 
     * @param html
     *            HTML content to modify
     * @return HTML content with all the table heads fixed. If all the heads
     *         were correct, the original content is returned.
     */
    private final void updateTableHeads(final Element body) {
        final Collection<Element> tableHeadRows; // Heads to fix
        Element table;  // HTML table
        Element thead;  // Table's head for wrapping

        // Table rows with <th> tags in a <tbody>
        tableHeadRows = body.select("table > tbody > tr:has(th)");
        if (!tableHeadRows.isEmpty()) {
            for (final Element row : tableHeadRows) {
                // Gets the row's table
                table = row.parent().parent();

                // Removes the row from its original position
                row.remove();

                // Creates a table header element with the row
                thead = new Element(Tag.valueOf("thead"), "");
                thead.appendChild(row);
                // Adds the head at the beginning of the table
                table.prependChild(thead);
            }
        }
    }

    /**
     * Removes the alternating {@code a} and {@code b} classes from table rows.
     * <p>
     * This seems to be an obsolete way to get alternate colored rows.
     * 
     * @param body
     *            body element with the HTML code to transform
     */
    private final void updateTableRowAlternates(final Element body) {
        final Collection<Element> elements;   // Tables and rows to fix

        // Table rows with the class "a" or "b"
        elements = body.select("tr.a, tr.b");
        if (!elements.isEmpty()) {
            for (final Element row : elements) {
                if (row.hasClass("a")) {
                    row.removeClass("a");
                } else {
                    row.removeClass("b");
                }
                if (row.classNames().isEmpty()) {
                    row.removeAttr("class");
                }
            }
        }
    }

}
