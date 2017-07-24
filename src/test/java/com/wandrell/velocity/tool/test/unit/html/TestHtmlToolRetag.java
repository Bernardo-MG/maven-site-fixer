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

package com.wandrell.velocity.tool.test.unit.html;

import org.jsoup.nodes.Element;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HtmlTool;
import com.wandrell.velocity.tool.test.utils.test.AbstractUtilsSelectorArgumentTest;

/**
 * Unit tests for {@link HtmlTool} testing the {@code retag} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
public final class TestHtmlToolRetag
        extends AbstractUtilsSelectorArgumentTest {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlTool util = new HtmlTool();

    /**
     * Default constructor.
     */
    public TestHtmlToolRetag() {
        super();
    }

    /**
     * Tests that retagging an element works as expected.
     */
    @Test
    public final void testDivToCode() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final String tag;          // New tag

        html = "<div class=\"source\"><div><div class=\"source\"><pre>Some code</pre></div></div></div>";
        htmlExpected = "<code class=\"source\">\n <div>\n  <code class=\"source\"><pre>Some code</pre></code>\n </div></code>";
        selector = "div.source";
        tag = "code";

        runTest(html, htmlExpected, selector, tag);
    }

    /**
     * Tests that retagging a not existing element does nothing.
     */
    @Test
    public final void testNotExisting_Nothing() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final String tag;          // New tag

        html = "<div class=\"source\"><div><div class=\"source\"><pre>Some code</pre></div></div></div>";
        htmlExpected = "<div class=\"source\">\n <div>\n  <div class=\"source\">\n   <pre>Some code</pre>\n  </div>\n </div>\n</div>";
        selector = "div.abc";
        tag = "code";

        runTest(html, htmlExpected, selector, tag);
    }

    @Override
    protected final void callTestedMethod(final Element element,
            final String selector, final String argument) {
        util.retag(element, selector, argument);
    }

}