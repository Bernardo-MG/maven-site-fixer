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
 * Utilities class for upgrading a Velocity's XHTML code to HTML5.
 * <p>
 * This was created for Maven Sites, which are built through Doxia. This
 * supports XHTML, and not HTML5, which has the effect making such pages, by
 * default, outdated.
 * <p>
 * The various methods contained in this class aim to fix this problem, and will
 * transform several known errors into valid HTML5.
 * <p>
 * The class makes use of <a href="http://jsoup.org/">jsoup</a> for querying and
 * editing. This library will process the HTML code received by the methods, so
 * only the contents of the {@code <body>} tag (or the full HTML if this tag is
 * missing) will be used.
 * <p>
 * Take into account that while the returned HTML will be correct, the validity
 * of the received HTML won't be checked. That falls fully on the hands of the
 * user.
 * 
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("html5UpdateTool")
public class HTML5UpdateUtils {

    /**
     * Constructs an instance of the {@code HTML5UpdateUtils}.
     */
    public HTML5UpdateUtils() {
        super();
    }

    /**
     * Returns the result from fixing internal links, and ids, which are using
     * point separators from the received HTML code. This fix consists just on
     * removing said points.
     * <p>
     * Some internal links on Doxia sites use points on the anchor ids, and this
     * stops such links from working correctly.
     * <p>
     * This method will transform any id, or internal href, such as
     * "id.with.points" to "idwithpoints".
     * 
     * @param html
     *            HTML where points are to be removed from internal lins and ids
     * @return HTML content, with the points removed from internal links and ids
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
     * Returns the result from removing the {@code externalLink} class from
     * links from the received HTML code.
     * <p>
     * These are used by Doxia but are meaningless.
     * <p>
     * If a after removing the class any link ends without classes, then the
     * {@code class} attribute will be removed too.
     * 
     * @param html
     *            HTML where the {@code externalLink} class is to be removed
     * @return HTML content with the {@code externalLink} class removed
     */
    public final String removeExternalLinks(final String html) {
        final Collection<Element> links; // Links to fix
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // <a> elements with the externalLink class
        links = body.select("a.externalLink");
        for (final Element link : links) {
            link.removeClass("externalLink");

            if (link.classNames().isEmpty()) {
                link.removeAttr("class");
            }
        }

        return body.html();
    }

    /**
     * Returns the result from removing links with no {@code href} attribute
     * from the received HTML code.
     * <p>
     * These links are added by Doxia mainly to the headings. The idea seems to
     * allow getting an internal anchor by clicking on a heading, but it does
     * not work correctly on all skins, or maybe it is just missing something,
     * making it invalid HTML code.
     * 
     * @param html
     *            HTML where links with no {@code href} attribute are to be
     *            removed
     * @return HTML content, with no link missing the {@code href} attribute
     */
    public final String removeNoHrefLinks(final String html) {
        final Collection<Element> links; // Links to fix
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // Links missing the href attribute
        links = body.select("a:not([href])");
        for (final Element link : links) {
            link.remove();
        }

        return body.html();
    }

    /**
     * Returns the result from updating and correcting source divisions on the
     * received HTML code.
     * <p>
     * Outdated source divisions, which look as {@code 
     * <div class="source">}, are transformed to the new {@code <code>} elements
     * Additionally, it will correct the position of the {@code pre} element,
     * will me moved out of the code section.
     * <p>
     * It also fixes a Doxia error where the source division is wrapped by a
     * second source division.
     * 
     * @param html
     *            HTML where the source sections are to be updated
     * @return HTML content, with the source sections updated
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
     * Returns the result from updating section divisions, such as {@code 
     * <div class="section">}, to the new {@code <section>} element on the
     * received HTML code.
     * 
     * @param html
     *            HTML where the section divisions are to be updated
     * @return HTML content, with the section divisions updated
     */
    public final String updateSectionDiv(final String html) {
        final Collection<Element> sectionDivs; // Section divisions
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // divs with the section class
        sectionDivs = body.select("div.section");
        for (final Element div : sectionDivs) {
            div.tagName("section");
            div.removeClass("section");

            if (div.classNames().isEmpty()) {
                div.removeAttr("class");
            }
        }

        return body.html();
    }

    /**
     * Returns the result from updating the tables, by applying various fixes
     * and removing unneeded code, on the received HTML code.
     * <p>
     * This method will add the missing {@code <thead>} element to table, remove
     * the unneeded border attribute and the {@code bodyTable} class.
     * <p>
     * It also removes the alternating rows attributes, which marks them as the
     * {@code a} and {@code b} classes. This seems to be an outdated method to
     * get alternating colored rows.
     * 
     * @param html
     *            HTML with tables to update
     * @return HTML content, with the tables updated
     */
    public final String updateTables(final String html) {
        final Element body;     // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        removeTableBodyClass(body);
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
     *            body element with the table to fix
     */
    private final void removeBodyTable(final Element body) {
        final Collection<Element> tables;   // Tables to fix

        // Tables with the bodyTable class
        tables = body.select("table.bodyTable");
        for (final Element table : tables) {
            table.removeClass("bodyTable");

            if (table.classNames().isEmpty()) {
                table.removeAttr("class");
            }
        }
    }

    /**
     * Removes points from the {@code id} attributes.
     * 
     * @param body
     *            body element with ids to fix
     */
    private final void removePointsFromIds(final Element body) {
        final Collection<Element> elements; // Elements to fix
        String id;      // id attribute contents

        // Elements with the id attribute
        elements = body.select("[id]");
        for (final Element element : elements) {
            id = element.attr("id").replaceAll("\\.", "");

            element.attr("id", id);
        }
    }

    /**
     * Removes points from the {@code href} attributes, if these are using
     * internal anchors.
     * 
     * @param body
     *            body element with links to fix
     */
    private final void removePointsFromInternalHref(final Element body) {
        final Collection<Element> links; // Links to fix
        String href;    // href attribute contents

        // Elements with an internal href
        links = body.select("[href^=\"#\"]");
        for (final Element element : links) {
            href = element.attr("href").replaceAll("\\.", "");

            element.attr("href", href);
        }
    }

    /**
     * Removes redundant source divisions. This serves as a cleanup step before
     * updating the code sections.
     * <p>
     * Sites created with Doxia for some reason wrap a source code division with
     * another source code division, and this needs to be fixed before applying
     * other fixes to such divisions.
     * <p>
     * Due to the way this method works, if those divisions were to have more
     * than a code division, those additional elements will be lost.
     * 
     * @param body
     *            body element with source divisions to fix
     */
    private final void removeRedundantSourceDivs(final Element body) {
        final Collection<Element> sourceDivs; // Repeated source divs
        Element parent;                       // Parent <div>

        // Divs with the source class with another div with the source class as
        // a child
        sourceDivs = body.select("div.source > div.source");
        for (final Element div : sourceDivs) {
            parent = div.parent();
            div.remove();
            parent.replaceWith(div);
        }
    }

    /**
     * Removes the {@code bodyTable} from tables.
     * <p>
     * If the table ends without classes, then the {@code class} attribute is
     * removed.
     * 
     * @param body
     *            body element with tables to fix
     */
    private final void removeTableBodyClass(final Element body) {
        final Collection<Element> tables; // Tables to fix

        // Tables with the bodyTable class
        tables = body.select("table.bodyTable");
        for (final Element table : tables) {
            table.removeClass("bodyTable");

            if (table.attr("class").isEmpty()) {
                table.removeAttr("class");
            }
        }
    }

    /**
     * Removes the {@code border} attribute from {@code <table} elements.
     * <p>
     * This attribute, which should be defined in CSS files, is added by Doxia
     * to tables.
     * 
     * @param body
     *            body element with tables to fix
     */
    private final void removeTableBorder(final Element body) {
        final Collection<Element> tables;   // Tables to fix

        // Selects tables with border defined
        tables = body.select("table[border]");
        for (final Element table : tables) {
            table.removeAttr("border");
        }
    }

    /**
     * Moves the {@code pre} element out of source divisions, so it wraps said
     * division, and not the other way around.
     * <p>
     * Note that these source divisions are expected to have only one children
     * with the {@code pre} tag.
     * 
     * @param body
     *            body element with source divisions to upgrade
     */
    private final void takeOutSourceDivPre(final Element body) {
        final Collection<Element> divs; // Code divisions
        Element pre;                    // <pre> element
        String text;                    // Preserved text

        // Divs with the source class and a pre
        divs = body.select("div.source:has(pre)");
        for (final Element div : divs) {
            pre = div.getElementsByTag("pre").iterator().next();

            text = pre.text();
            pre.text("");

            div.replaceWith(pre);
            pre.appendChild(div);

            div.text(text);
        }
    }

    /**
     * Transforms {@code <div>} elements with the {@code source} class into
     * {@code <code>} elements.
     * 
     * @param body
     *            body element with source division to upgrade
     */
    private final void updateSourceDivsToCode(final Element body) {
        final Collection<Element> divs; // Code divisions

        // Divs with the source class
        divs = body.select("div.source");
        for (final Element div : divs) {
            div.tagName("code");

            div.removeClass("source");
            if (div.classNames().isEmpty()) {
                div.removeAttr("class");
            }
        }
    }

    /**
     * Corrects table headers by adding a {@code <thead>} section where missing.
     * <p>
     * This serves to fix an error with tables created by Doxia, which will add
     * the header rows into the {@code <tbody>} element, instead on a {@code 
     * <thead>} element.
     * 
     * @param body
     *            body element with tables to fix
     */
    private final void updateTableHeads(final Element body) {
        final Collection<Element> tableHeadRows; // Heads to fix
        Element table;  // HTML table
        Element thead;  // Table's head for wrapping

        // Table rows with <th> tags in a <tbody>
        tableHeadRows = body.select("table > tbody > tr:has(th)");
        for (final Element row : tableHeadRows) {
            // Gets the row's table
            // The selector ensured the row is inside a tbody
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

    /**
     * Removes the alternating {@code a} and {@code b} classes from table rows.
     * <p>
     * This seems to be an obsolete way to get alternate colored rows.
     * 
     * @param body
     *            body element with tables to fix
     */
    private final void updateTableRowAlternates(final Element body) {
        final Collection<Element> elements;   // Tables and rows to fix

        // Table rows with the class "a" or "b"
        elements = body.select("tr.a, tr.b");
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
