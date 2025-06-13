/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2025 the original author or authors.
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

package com.bernardomg.velocity.tool;

import java.util.Objects;

import org.apache.velocity.tools.config.DefaultKey;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities class for upgrading XHTML code to HTML5.
 * <p>
 * This was created for Maven Sites. These are built through Doxia which supports XHTML, and not HTML5, and so this
 * library generates outdated pages.
 * <p>
 * The various methods contained in this class aim to fix this problem, and will transform several known mistakes into
 * valid HTML5, but they won't transform the full page. The end user should make sure that the template being used,
 * probably a Maven Skin, matches expectations.
 * <p>
 * The <a href="https://github.com/Bernardo-MG/docs-maven-skin">Docs Maven Skin</a> and its requirements have dictated
 * the development of this class. For more generic methods use the {@link com.bernardomg.velocity.tool.HtmlTool
 * HtmlTool}.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@DefaultKey("html5UpdateTool")
public class Html5UpdateTool {

    /**
     * Logger for the class.
     */
    private static final Logger log = LoggerFactory.getLogger(Html5UpdateTool.class);

    /**
     * Constructs an instance of the utilities class.
     */
    public Html5UpdateTool() {
        super();
    }

    /**
     * Removes the points from the contents of the specified attribute.
     *
     * @param root
     *            root element for the selection
     * @param selector
     *            CSS selector for the elements
     * @param attr
     *            attribute to clean
     * @return transformed element
     */
    public final Element removePointsFromAttr(final Element root, final String selector, final String attr) {
        final Iterable<Element> elements; // Elements to fix

        Objects.requireNonNull(selector, "Received a null pointer as selector");
        Objects.requireNonNull(attr, "Received a null pointer as attribute");

        if (root == null) {
            log.warn("Received null root");
        } else {
            // Selects and iterates over the elements
            elements = root.select(selector);
            for (final Element selected : elements) {
                removePointsFromAttr(selected, attr);
            }
        }

        return root;
    }

    /**
     * Corrects table headers by adding a {@code <thead>} section where missing.
     * <p>
     * This serves to fix an error with tables created by Doxia, which will add the header rows into the {@code <tbody>}
     * element, instead on a {@code
     * <thead>} element.
     *
     * @param root
     *            root element with tables to fix
     * @return transformed element
     */
    public final Element updateTableHeads(final Element root) {
        final Iterable<Element> tableHeadRows; // Heads to fix
        Element                 table;         // HTML table
        Element                 thead;         // Table's head for wrapping

        if (root == null) {
            log.warn("Received null root");
        } else {
            // Table rows with <th> tags in a <tbody>
            tableHeadRows = root.select("table > tbody > tr:has(th)");
            for (final Element row : tableHeadRows) {
                // Gets the row's table
                // The selector ensured the row is inside a tbody
                table = row.parent()
                    .parent();

                // Removes the row from its original position
                row.remove();

                // Creates a table header element with the row
                thead = new Element(Tag.valueOf("thead"), "");
                thead.appendChild(row);
                // Adds the head at the beginning of the table
                table.prependChild(thead);
            }
        }

        return root;
    }

    /**
     * Removes the points from the contents of the specified attribute.
     *
     * @param element
     *            element with the attribute to clean
     * @param attr
     *            attribute to clean
     */
    private final void removePointsFromAttr(final Element element, final String attr) {
        final String value; // Content of the attribute

        // Takes and clean the old attribute value
        value = element.attr(attr)
            .replace(".", "");

        // Sets the cleaned value
        element.attr(attr, value);
    }

}
