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
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.bernardomg.velocity.tool.HtmlTool;

/**
 * Unit tests for {@link HtmlTool} testing the {@code unwrap} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
@RunWith(JUnitPlatform.class)
@DisplayName("HtmlTool.unwrap")
public final class TestHtmlToolUnwrap {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlTool util = new HtmlTool();

    /**
     * Default constructor.
     */
    public TestHtmlToolUnwrap() {
        super();
    }

    /**
     * Tests that unwrapping an empty element removes it.
     */
    @Test
    public final void testEmpty_Removed() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final Element element;     // Parsed HTML

        html = "<h1><a name=\"a_heading\"></a>A heading</h1><h3><a name=\"a_heading\"/>A heading</h3><a></a>";
        htmlExpected = "<h1>A heading</h1>\n<h3>A heading</h3>";
        selector = "a:not([href])";

        element = Jsoup.parse(html).body();
        util.unwrap(element, selector);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that unwrapping a not existing element does nothing.
     */
    @Test
    public final void testNotExisting_Nothing() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final Element element;     // Parsed HTML

        html = "<p>Some text</p>";
        htmlExpected = "<p>Some text</p>";
        selector = "a:not([href])";

        element = Jsoup.parse(html).body();
        util.unwrap(element, selector);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem to the {@code unwrap}
     * method.
     */
    @Test
    public final void testUnwrap_EmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.unwrap(element, "a:not([href])");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that unwrapping an element with text keeps this text.
     */
    @Test
    public final void testWithText_TextKept() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final Element element;     // Parsed HTML

        html = "<h1><a name=\"a_heading\">A heading</a></h1><h3><a name=\"a_heading\">A heading</h3></a><a></a>";
        htmlExpected = "<h1>A heading</h1>\n<h3>A heading</h3>";
        selector = "a:not([href])";

        element = Jsoup.parse(html).body();
        util.unwrap(element, selector);

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
