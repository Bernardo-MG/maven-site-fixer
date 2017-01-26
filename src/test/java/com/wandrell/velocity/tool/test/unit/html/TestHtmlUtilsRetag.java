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

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.Html5UpdateUtils;
import com.wandrell.velocity.tool.HtmlUtils;

/**
 * Unit tests for {@link Html5UpdateUtils}.
 * 
 * @author Bernardo Martínez Garrido
 * @see Html5UpdateUtils
 */
public final class TestHtmlUtilsRetag {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlUtils util = new HtmlUtils();

    /**
     * Default constructor.
     */
    public TestHtmlUtilsRetag() {
        super();
    }

    /**
     * Tests that when removing the externalLink class from links, if more
     * classes are left then they are untouched.
     */
    @Test
    public final void testSimple() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<div class=\"source\"><div><div class=\"source\"><pre>Some code</pre></div></div></div>";

        result = util.retag(html, "div.source", "code");

        htmlExpected = "<code class=\"source\">\n <div>\n  <code class=\"source\"><pre>Some code</pre></code>\n </div></code>";

        Assert.assertEquals(result, htmlExpected);
    }

}
