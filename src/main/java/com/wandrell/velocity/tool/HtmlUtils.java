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

/**
 * Utilities class for manipulating HTML, to be used as an extension of the
 * Velocity templating engine.
 * <p>
 * The methods offered help enhancing the look of a Maven Site by manipulating
 * the HTML structure.
 * <p>
 * The class makes use of <a href="http://jsoup.org/">jsoup</a> for querying and
 * editing. This library will process the HTML code received by the methods, so
 * only the contents of the {@code <body>} tag (or the full HTML if this tag is
 * missing) will be used.
 * <p>
 * Take into account that while the returned HTML will be correct, the validity
 * of the received HTML won't be checked. That falls fully on the hands of the
 * user.
 * <p>
 * This class has been created from the HTML Tool class from the
 * <a href="http://andriusvelykis.github.io/reflow-maven-skin/">Reflow Maven
 * Skin</a>.
 * 
 * @author Andrius Velykis
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("htmlTool")
public final class HtmlUtils {

    /**
     * Constructs an instance of the {@code HTMLUtils}.
     */
    public HtmlUtils() {
        super();
    }

    /**
     * Returns the HTML code with the elements marked by the selector wrapped on
     * the received wrapper element.
     * <p>
     * The method will find all the elements fitting into the selector, and then
     * wrap them with the wrapper element. The HTML code will then be adapted to
     * this change and returned.
     * 
     * @param html
     *            HTML content to modify
     * @param selector
     *            CSS selector for elements to wrap
     * @param wrapper
     *            HTML to use for wrapping the selected elements
     * @return HTML with the selected elements wrapped with the wrapper element
     */
    public final String wrap(final String html, final String selector,
            final String wrapper) {
        final Iterable<Element> elements; // Selected elements
        final Element body;  // Element parsed from the content

        checkNotNull(html, "Received a null pointer as html");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(wrapper, "Received a null pointer as HTML wrap");

        body = Jsoup.parse(html).body();
        elements = body.select(selector);

        for (final Element element : elements) {
            element.wrap(wrapper);
        }

        return body.html();
    }

    /**
     * Returns the HTML code with the first element marked by the selector
     * wrapped on the received wrapper element.
     * <p>
     * The method will find the first element fitting into the selector, and
     * then wrap it with the wrapper element. The HTML code will then be adapted
     * to this change and returned.
     * 
     * @param html
     *            HTML content to modify
     * @param selector
     *            CSS selector for elements to wrap
     * @param wrapper
     *            HTML to use for wrapping the selected elements
     * @return HTML with the selected elements wrapped with the wrapper element
     */
    public final String wrapFirst(final String html, final String selector,
            final String wrapper) {
        final Collection<Element> elements; // Selected elements
        final Element body;  // Element parsed from the content

        checkNotNull(html, "Received a null pointer as html");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(wrapper, "Received a null pointer as HTML wrap");

        body = Jsoup.parse(html).body();
        elements = body.select(selector);

        if (!elements.isEmpty()) {
            elements.iterator().next().wrap(wrapper);
        }

        return body.html();
    }

}
