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
import org.junit.Test;

import com.bernardomg.velocity.tool.HtmlTool;
import com.bernardomg.velocity.tool.test.utils.test.AbstractUtilsSelectorTest;

/**
 * Unit tests for {@link HtmlTool} testing the {@code swapTagWithParent} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
public final class TestHtmlToolSwapTagWithParent
        extends AbstractUtilsSelectorTest {

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

    /**
     * Tests that swapping a not existing element does nothing.
     */
    @Test
    public final void testNotExistingNothing() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector

        html = "<code><pre>Some code</pre></code>";
        htmlExpected = html;
        selector = "code > abc";

        runTest(html, htmlExpected, selector);
    }

    /**
     * Tests that swapping elements works as expected.
     */
    @Test
    public final void testSwapCodePre() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector

        html = "<code><pre>Some code</pre></code>";
        htmlExpected = "<pre><code>Some code</code></pre>";
        selector = "code > pre";

        runTest(html, htmlExpected, selector);
    }

    @Override
    protected final void callTestedMethod(final Element element,
            final String selector) {
        util.swapTagWithParent(element, selector);
    }

}
