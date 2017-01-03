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

package com.wandrell.velocity.tool.test.unit.html5fix;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.Html5UpdateUtils;

/**
 * Unit tests for {@link Html5UpdateUtils}.
 * 
 * @author Bernardo Martínez Garrido
 * @see Html5UpdateUtils
 */
public final class TestHtml5UpdateUtilsFixInternalLinks {

    /**
     * Instance of the utils class being tested.
     */
    private final Html5UpdateUtils util = new Html5UpdateUtils();

    /**
     * Default constructor.
     */
    public TestHtml5UpdateUtilsFixInternalLinks() {
        super();
    }

    /**
     * Tests that points on anchors are correctly removed.
     */
    @Test
    public final void testInternalLink_Points_Fixed() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<h1 id=\"1.2.3\">Header</h1><a href=\"#1.2.3\">To the header</a><a href=\"1.2.3\">Not to be modified</a>";

        result = util.fixInternalLinks(html);

        htmlExpected = "<h1 id=\"123\">Header</h1>\n<a href=\"#123\">To the header</a>\n<a href=\"1.2.3\">Not to be modified</a>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that HTML with no anchors is ignored.
     */
    @Test
    public final void testNoAnchors_Ignored() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<p>Some text</p>";

        result = util.fixInternalLinks(html);

        htmlExpected = "<p>Some text</p>";

        Assert.assertEquals(result, htmlExpected);
    }

}
