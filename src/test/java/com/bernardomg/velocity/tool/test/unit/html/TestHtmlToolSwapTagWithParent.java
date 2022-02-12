/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2021 the original author or authors.
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

package com.bernardomg.velocity.tool.test.unit.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.velocity.tool.HtmlTool;

/**
 * Unit tests for {@link HtmlTool} testing the {@code swapTagWithParent} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
@DisplayName("HtmlTool.swapTagWithParent")
public final class TestHtmlToolSwapTagWithParent {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlTool util = new HtmlTool();

    /**
     * Default constructor.
     */
    public TestHtmlToolSwapTagWithParent() {
        super();
    }

    @Test
    @DisplayName("Swapping an empty string does nothing")
    public final void testEmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.swapTagWithParent(element, "code > pre");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Swapping a not existing element does nothing")
    public final void testNotExistingNothing() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final Element element;     // Parsed HTML

        html = "<code><pre>Some code</pre></code>";
        htmlExpected = html;
        selector = "code > abc";

        element = Jsoup.parse(html).body();
        util.swapTagWithParent(element, selector);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Swaps elements")
    public final void testSwapCodePre() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final Element element;     // Parsed HTML

        html = "<code><pre>Some code</pre></code>";
        htmlExpected = "<pre><code>Some code</code></pre>";
        selector = "code > pre";

        element = Jsoup.parse(html).body();
        util.swapTagWithParent(element, selector);

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
