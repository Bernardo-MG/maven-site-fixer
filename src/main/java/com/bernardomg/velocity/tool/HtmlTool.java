/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2019 the original author or authors.
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 * Utilities class for manipulating HTML, to be used as an extension of the
 * Velocity templating engine.
 * <p>
 * The methods will make small and well defined operations into HTML code. The
 * edition is handled through <a href="http://jsoup.org/">jsoup</a>, and the
 * HTML should have been parsed with it before handling it to the modification
 * methods.
 * <p>
 * To ease parsing HTML the {@link parse} method can be used. It receives HTML
 * code and returns a jsoup element.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@DefaultKey("htmlTool")
public final class HtmlTool {

    /**
     * Constructs an instance of the utilities class.
     */
    public HtmlTool() {
        super();
    }

    /**
     * Finds a set of elements through a CSS selector and adds a class to them.
     * 
     * @param root
     *            root element for the selection
     * @param selector
     *            CSS selector for the elements to retag
     * @param className
     *            new class for the elements
     * @return transformed element
     */
    public final Element addClass(final Element root, final String selector,
            final String className) {
        final Iterable<Element> elements; // Elements selected

        Objects.requireNonNull(root, "Received a null pointer as root element");
        Objects.requireNonNull(selector, "Received a null pointer as selector");
        Objects.requireNonNull(className, "Received a null pointer as class");

        // Selects and iterates over the elements
        elements = root.select(selector);
        for (final Element element : elements) {
            element.addClass(className);
        }

        return root;
    }

    /**
     * Parses the received HTML code.
     * <p>
     * The resulting object can be used on the other methods. Only the content
     * of the {@code <body>} tag will be parsed.
     * 
     * @param html
     *            HTML to parse
     * @return the parsed HTML body
     */
    public final Element parse(final String html) {
        Objects.requireNonNull(html, "Received a null pointer as body");

        return Jsoup.parse(html).body();
    }

    /**
     * Finds a set of elements through a CSS selector and removes the received
     * attribute from them, if they have it.
     * 
     * @param root
     *            root element for the selection
     * @param selector
     *            CSS selector for the elements with the attribute to remove
     * @param attribute
     *            attribute to remove
     * @return transformed element
     */
    public final Element removeAttribute(final Element root,
            final String selector, final String attribute) {
        final Iterable<Element> elements; // Elements selected

        Objects.requireNonNull(root, "Received a null pointer as root element");
        Objects.requireNonNull(selector, "Received a null pointer as selector");
        Objects.requireNonNull(attribute, "Received a null pointer as attribute");

        // Selects and iterates over the elements
        elements = root.select(selector);
        for (final Element element : elements) {
            element.removeAttr(attribute);
        }

        return root;
    }

    /**
     * Finds a set of elements through a CSS selector and removes the received
     * class from them, if they have it.
     * <p>
     * If the elements end without classes then the class attribute is also
     * removed.
     * 
     * @param root
     *            root element for the selection
     * @param selector
     *            CSS selector for the elements with the class to remove
     * @param className
     *            class to remove
     * @return transformed element
     */
    public final Element removeClass(final Element root, final String selector,
            final String className) {
        final Iterable<Element> elements; // Elements selected

        Objects.requireNonNull(root, "Received a null pointer as root element");
        Objects.requireNonNull(selector, "Received a null pointer as selector");
        Objects.requireNonNull(className, "Received a null pointer as className");

        // Selects and iterates over the elements
        elements = root.select(selector);
        for (final Element element : elements) {
            element.removeClass(className);

            if (element.classNames().isEmpty()) {
                element.removeAttr("class");
            }
        }

        return root;
    }

    /**
     * Finds a set of elements through a CSS selector and changes their tags.
     * 
     * @param root
     *            root element for the selection
     * @param selector
     *            CSS selector for the elements to retag
     * @param tag
     *            new tag for the elements
     * @return transformed element
     */
    public final Element retag(final Element root, final String selector,
            final String tag) {
        final Iterable<Element> elements; // Elements selected

        Objects.requireNonNull(root, "Received a null pointer as root element");
        Objects.requireNonNull(selector, "Received a null pointer as selector");
        Objects.requireNonNull(tag, "Received a null pointer as tag");

        // Selects and iterates over the elements
        elements = root.select(selector);
        for (final Element element : elements) {
            element.tagName(tag);
        }

        return root;
    }

    /**
     * Finds a set of elements through a CSS selector and swaps its tag with
     * that from its parent.
     * 
     * @param root
     *            body element with source divisions to upgrade
     * @param selector
     *            CSS selector for the elements to swap with its parent
     * @return transformed element
     */
    public final Element swapTagWithParent(final Element root,
            final String selector) {
        final Iterable<Element> elements; // Selected elements
        Element parent;                   // Parent element
        String text;                      // Preserved text

        Objects.requireNonNull(root, "Received a null pointer as root element");
        Objects.requireNonNull(selector, "Received a null pointer as selector");

        // Selects and iterates over the elements
        elements = root.select(selector);
        for (final Element element : elements) {
            parent = element.parent();

            // Takes the text out of the element
            text = element.text();
            element.text("");

            // Swaps elements
            parent.replaceWith(element);
            element.appendChild(parent);

            // Sets the text into what was the parent element
            parent.text(text);
        }

        return root;
    }

    /**
     * Finds a set of elements through a CSS selector and unwraps them.
     * <p>
     * This allows removing elements without losing their contents.
     * 
     * @param root
     *            root element for the selection
     * @param selector
     *            CSS selector for the elements to unwrap
     * @return transformed element
     */
    public final Element unwrap(final Element root, final String selector) {
        final Iterable<Element> elements; // Elements to unwrap

        Objects.requireNonNull(root, "Received a null pointer as root element");
        Objects.requireNonNull(selector, "Received a null pointer as selector");

        // Selects and iterates over the elements
        elements = root.select(selector);
        for (final Element element : elements) {
            element.unwrap();
        }

        return root;
    }

    /**
     * Finds a set of elements through a CSS selector and wraps them with the
     * received wrapper element.
     * 
     * @param root
     *            root element for the selection
     * @param selector
     *            CSS selector for the elements to wrap
     * @param wrapper
     *            HTML to use for wrapping the selected elements
     * @return transformed element
     */
    public final Element wrap(final Element root, final String selector,
            final String wrapper) {
        final Iterable<Element> elements; // Selected elements

        Objects.requireNonNull(root, "Received a null pointer as root element");
        Objects.requireNonNull(selector, "Received a null pointer as selector");
        Objects.requireNonNull(wrapper, "Received a null pointer as HTML wrap");

        // Selects and iterates over the elements
        elements = root.select(selector);
        for (final Element element : elements) {
            element.wrap(wrapper);
        }

        return root;
    }

}
