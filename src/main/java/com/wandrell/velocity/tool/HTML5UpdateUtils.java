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
 * Utilities class for fixing a Maven Site's HTML code.
 * <p>
 * Maven Sites are created by using Doxia, which supports XHTML, and not HTML5,
 * for this reason, these sites need some fixes and updates, in order to fit
 * into the latest HTML standards.
 * <p>
 * For this various methods are offered, each updating a type of element, or
 * fixing a common error.
 * <p>
 * Note that these methods will only modify the contents of the {@code 
 * <body>} element if it exists, or all the received code otherwise.
 * 
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("html5UpdateTool")
public class HTML5UpdateUtils {

    /**
     * Constructs an instance of the {@code SiteUtil}.
     */
    public HTML5UpdateUtils() {
        super();
    }

    /**
     * Fixes same-file links and ids using point separators.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with the links and ids fixes
     */
    public final String fixInternalLinks(final String html) {
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parseBodyFragment(html).body();

        fixIdsWithPoints(body);
        fixHrefsWithPoints(body);

        return body.html();
    }

    /**
     * Removes the externalLink class from links.
     * <p>
     * If a links ends without classes due to this, then the class attribute
     * will be removed too.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with no link having the externalLink class
     */
    public final String removeExternalLinks(final String html) {
        final Collection<Element> links; // Links to fix
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parseBodyFragment(html).body();

        // Selects rows with <th> tags within a <tr> in a <tbody>
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
     * Removes the links with no {@code href} attribute.
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

        body = Jsoup.parseBodyFragment(html).body();

        // Selects links with no href attribute
        links = body.select("a:not([href])");
        if (!links.isEmpty()) {
            for (final Element link : links) {
                link.remove();
            }
        }

        return body.html();
    }

    /**
     * Corrects code divisions, by both correction the elements order, and by
     * swapping code classes for the {@code <code>} block.
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

        body = Jsoup.parseBodyFragment(html).body();

        removeRedundantSourceDivs(body);
        updateSourceDivs(body);

        return body.html();
    }

    /**
     * Fixes divisions with the section class, transforming them into actual
     * sections.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with the sections updated
     */
    public final String updateSectionDiv(final String html) {
        final Collection<Element> sectionDivs; // Section divisions
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parseBodyFragment(html).body();

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
     * Cleans the tables, removing unneeded attributes and classes.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with the tables cleaned
     */
    public final String updateTables(final String html) {
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parseBodyFragment(html).body();

        updateTableHeads(body);
        removeTableBorder(body);
        removeBodyTable(body);
        updateTableRowAlternates(body);

        return body.html();
    }

    private final void fixHrefsWithPoints(final Element body) {
        final Collection<Element> links; // Links to fix
        String href;    // href attribute contents

        links = body.select("a[href^=\"#\"]");
        if (!links.isEmpty()) {
            for (final Element element : links) {
                href = element.attr("href").replaceAll("\\.", "");
                element.attr("href", href);
            }
        }
    }

    private final void fixIdsWithPoints(final Element body) {
        final Collection<Element> elements; // Elements to fix
        String id;      // id attribute contents

        elements = body.select("[id]");
        if (!elements.isEmpty()) {
            for (final Element element : elements) {
                id = element.attr("id").replaceAll("\\.", "");

                element.attr("id", id);
            }
        }
    }

    private final void removeBodyTable(final Element body) {
        final Collection<Element> tables;   // Tables to fix

        // Selects tables with the bodyTable class
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
     * Corrects source divisions by removing redundant apparitions of it.
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
     * 
     * @param html
     *            HTML content to fix
     * @return HTML content, with the redundant source classes removed
     */
    private final void removeRedundantSourceDivs(final Element body) {
        final Collection<Element> codeDivs; // Repeated code divs
        Element validDiv;       // Fixed code block

        codeDivs = body.select(".source:has(.source)");
        if (!codeDivs.isEmpty()) {
            for (final Element div : codeDivs) {
                if (!div.children().isEmpty()) {
                    validDiv = div.child(0);

                    validDiv.remove();

                    div.replaceWith(validDiv);
                }
            }
        }
    }

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

    private final void updateSourceDivs(final Element body) {
        final Collection<Element> divs; // Code divisions
        Element pre;    // <pre> element
        String text;    // Preserved text

        divs = body.select(".source:has(pre)");
        if (!divs.isEmpty()) {
            for (final Element div : divs) {
                pre = div.getElementsByTag("pre").get(0);

                text = pre.text();
                pre.text("");

                div.tagName("code");
                div.replaceWith(pre);
                pre.appendChild(div);

                div.removeClass("source");
                if (div.classNames().isEmpty()) {
                    div.removeAttr("class");
                }

                div.text(text);
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

        // Selects rows with <th> tags within a <tr> in a <tbody>
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

    private final void updateTableRowAlternates(final Element body) {
        final Collection<Element> elements;   // Tables and rows to fix

        // Selects rows with the class "a" or "b"
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
