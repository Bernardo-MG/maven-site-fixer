/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2017 the original author or authors.
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

import org.apache.velocity.tools.config.DefaultKey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
 * Utilities class for upgrading a Velocity's XHTML code to HTML5.
 * <p>
 * This was created for Maven Sites. These are built through Doxia which
 * supports XHTML, and not HTML5, and so this library generates outdated pages.
 * <p>
 * The various methods contained in this class aim to fix this problem, and will
 * transform several known mistakes into valid HTML5, but they won't transform
 * the full page. The end user should make sure that the template being used,
 * probably a Maven Skin, matches expectations.
 * <p>
 * There is a problem with this class. It was developed for the Docs Maven Skin,
 * and is tailored for the needs of that project, which makes use of Bootstrap
 * for the UI.
 * <p>
 * The class makes use of <a href="http://jsoup.org/">jsoup</a> for querying and
 * editing. This library will process the HTML code received by the methods, so
 * only the contents of the {@code <body>} tag (or the full HTML if this tag is
 * missing) will be used.
 * 
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("html5UpdateTool")
public class Html5UpdateUtils {

    /**
     * HTML utils class to allow reusing its methods.
     */
    private final HtmlUtils htmlUtils = new HtmlUtils();

    /**
     * Constructs an instance of the {@code HTML5UpdateUtils}.
     */
    public Html5UpdateUtils() {
        super();
    }

    /**
     * Returns the result from fixing internal links which are using point
     * separators. It also takes care of removing the points from the internal
     * ids. Using points for internal anchors can break navigation.
     * <p>
     * The fix consists on just removing all points from all the ids. For
     * example "id.with.points" will become "idwithpoints".
     * 
     * @param html
     *            HTML to fix internal links
     * @return HTML content, with the points removed from internal links and ids
     */
    public final String fixInternalLinks(final String html) {
        final Element body; // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // Removes points from the id attributes
        removePointsFromAttr(body, "[id]", "id");
        // Removes points from the href attributes for internal links
        removePointsFromAttr(body, "[href^=\"#\"]", "href");

        return body.html();
    }

    /**
     * Returns the result from removing the {@code externalLink} class from
     * links from the received HTML code.
     * <p>
     * These are used by Doxia but are meaningless for most modern UI
     * frameworks, such as Bootstrap.
     * <p>
     * If a after removing the class any link ends without classes, then the
     * {@code class} attribute will be removed too.
     * 
     * @param html
     *            HTML where the {@code externalLink} class is to be removed
     * @return HTML content with the {@code externalLink} class removed
     */
    public final String removeExternalLinks(final String html) {
        final Element body;            // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // <a> elements with the externalLink class
        getHtmlUtils().removeClass(body, "a.externalLink", "externalLink");

        return body.html();
    }

    /**
     * Returns the result from removing links with no {@code href} attribute
     * defined from the received HTML code.
     * <p>
     * These links are added by Doxia mainly to the headings. The idea seems to
     * allow getting an internal anchor by clicking on a heading, but it does
     * not work correctly on all skins (or maybe it is just missing something)
     * making it invalid HTML code.
     * <p>
     * Instead of just removing the links these will be actually unwrapped,
     * keeping any text they may contain.
     * 
     * @param html
     *            HTML to clear of any empty {@code href} link
     * @return HTML content, with no link missing the {@code href} attribute
     */
    public final String removeNoHrefLinks(final String html) {
        final Element body;            // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // Links missing the href attribute
        // Unwrapped to avoid losing texts
        getHtmlUtils().unwrap(body, "a:not([href])");

        return body.html();
    }

    /**
     * Removes the points from the contents of the specified attribute.
     * 
     * @param body
     *            body element with attributes to fix
     * @param selector
     *            CSS selector for the elements
     * @param attr
     *            attribute to clean
     */
    public final void removePointsFromAttr(final Element body,
            final String selector, final String attr) {
        final Iterable<Element> elements; // Elements to fix

        checkNotNull(body, "Received a null pointer as body");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(attr, "Received a null pointer as attribute");

        // Elements with the id attribute
        elements = body.select(selector);
        for (final Element element : elements) {
            removePointsFromAttr(element, attr);
        }
    }

    /**
     * Removes the points from the contents of the specified attribute.
     * 
     * @param html
     *            html element with attributes to fix
     * @param selector
     *            CSS selector for the elements
     * @param attr
     *            attribute to clean
     * @return HTML content, with the points removed from the attributes
     */
    public final String removePointsFromAttr(final String html,
            final String selector, final String attr) {
        final Element body;            // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(attr, "Received a null pointer as attribute");

        body = Jsoup.parse(html).body();

        removePointsFromAttr(body, selector, attr);

        return body.html();
    }

    /**
     * Returns the result from updating and correcting source divisions on the
     * received HTML code.
     * <p>
     * Outdated source divisions such as {@code 
     * <div class="source">} are transformed to the new {@code <code>} elements.
     * Additionally, it will correct the position of the {@code pre} element,
     * moving it out of the code section.
     * <p>
     * It also fixes a Doxia error where the source division is wrapped by a
     * second source division.
     * 
     * @param html
     *            HTML where the source sections are to be updated
     * @return HTML content, with the source sections updated
     */
    public final String updateCodeSections(final String html) {
        final Element body; // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // Source divs are transformed to code tags
        getHtmlUtils().retag(body, "div.source", "code");
        // Removes redundant tags
        getHtmlUtils().unwrap(body, "code > code");

        getHtmlUtils().swapTagWithParent(body, "code > pre");

        // Removes source class from code tags
        getHtmlUtils().removeClass(body, "code.source", "source");

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
        final Element body;                  // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // Section divs are transformed to section tags
        getHtmlUtils().retag(body, "div.section", "section");
        // Removes section class from section tags
        getHtmlUtils().removeClass(body, "section.section", "section");

        return body.html();
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
    public final void updateTableHeads(final Element body) {
        final Iterable<Element> tableHeadRows; // Heads to fix
        Element table;  // HTML table
        Element thead;  // Table's head for wrapping

        checkNotNull(body, "Received a null pointer as body");

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
     * Corrects table headers by adding a {@code <thead>} section where missing.
     * <p>
     * This serves to fix an error with tables created by Doxia, which will add
     * the header rows into the {@code <tbody>} element, instead on a {@code 
     * <thead>} element.
     * 
     * @param html
     *            HTML with tables to update
     * @return HTML content, with the tables updated
     */
    public final String updateTableHeads(final String html) {
        final Element body;                  // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        updateTableHeads(body);

        return body.html();
    }

    /**
     * Returns the result from updating the tables on the received HTML code.
     * <p>
     * This method will add the missing {@code <thead>} element to table, remove
     * the unneeded border attribute and remove the {@code bodyTable} class.
     * <p>
     * It also removes the alternating rows attributes. Doxia marks them with
     * the {@code a} and {@code b} classes. This seems to be an outdated method
     * to get alternating colored rows.
     * 
     * @param html
     *            HTML with tables to update
     * @return HTML content, with the tables updated
     */
    public final String updateTables(final String html) {
        final Element body; // Body of the HTML code

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        // Removes bodyTable from tables
        getHtmlUtils().removeClass(body, "table.bodyTable", "bodyTable");
        updateTableHeads(body);
        // Removes border attribute
        getHtmlUtils().removeAttribute(body, "table[border]", "border");
        // Removes alternating rows classes
        getHtmlUtils().removeClass(body, "tr.a", "a");
        getHtmlUtils().removeClass(body, "tr.b", "b");

        return body.html();
    }

    /**
     * Returns the HTML utils class.
     * 
     * @return the HTML utils class
     */
    private final HtmlUtils getHtmlUtils() {
        return htmlUtils;
    }

    /**
     * Removes the points from the contents of the specified attribute.
     * 
     * @param element
     *            element with the attribute to clean
     * @param attr
     *            attribute to clean
     */
    private final void removePointsFromAttr(final Element element,
            final String attr) {
        final String value; // Content of the attribute

        value = element.attr(attr).replaceAll("\\.", "");

        element.attr(attr, value);
    }

}
