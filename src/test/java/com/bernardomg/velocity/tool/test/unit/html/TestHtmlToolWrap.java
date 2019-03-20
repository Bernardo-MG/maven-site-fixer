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

package com.bernardomg.velocity.tool.test.unit.html;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.bernardomg.velocity.tool.HtmlTool;
import com.bernardomg.velocity.tool.test.utils.test.AbstractUtilsSelectorArgumentTest;

/**
 * Unit tests for {@link HtmlTool} testing the {@code wrap} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
@RunWith(JUnitPlatform.class)
public final class TestHtmlToolWrap extends AbstractUtilsSelectorArgumentTest {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlTool util = new HtmlTool();

    /**
     * Default constructor.
     */
    public TestHtmlToolWrap() {
        super();
    }

    /**
     * Tests that wrapping an element, without indicating the closing tag,
     * closes the wrap.
     */
    @Test
    public final void testNotClosed_Closed() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final String wrapper;      // Node for wrapping

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";
        htmlExpected = "<header>\n <h1>A heading</h1>\n</header>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>\n<header>\n <h1>Another heading</h1>\n</header>\n<p>Even more text</p>";
        selector = "h1";
        wrapper = "<header>";

        runTest(html, htmlExpected, selector, wrapper);
    }

    /**
     * Test that wrapping a not existing element does nothing.
     */
    @Test
    public final void testNotExisting_Nothing() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final String wrapper;      // Node for wrapping

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";
        htmlExpected = "<h1>A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>\n<h1>Another heading</h1>\n<p>Even more text</p>";
        selector = "h3";
        wrapper = "<header></header>";

        runTest(html, htmlExpected, selector, wrapper);
    }

    /**
     * Tests that wrapping an element works as expected.
     */
    @Test
    public final void testWrap() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final String wrapper;      // Node for wrapping

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";
        htmlExpected = "<header>\n <h1>A heading</h1>\n</header>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>\n<header>\n <h1>Another heading</h1>\n</header>\n<p>Even more text</p>";
        selector = "h1";
        wrapper = "<header></header>";

        runTest(html, htmlExpected, selector, wrapper);
    }

    @Override
    protected final void callTestedMethod(final Element element,
            final String selector, final String argument) {
        util.wrap(element, selector, argument);
    }

}
