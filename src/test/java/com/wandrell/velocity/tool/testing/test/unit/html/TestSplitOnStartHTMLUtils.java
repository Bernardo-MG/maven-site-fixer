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

package com.wandrell.velocity.tool.testing.test.unit.html;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtils;

/**
 * Unit tests for {@link HTMLUtils}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Splitting an HTML tree of height 1 works as expected.</li>
 * <li>Splitting an HTML tree of height 1, including a tail split, works as
 * expected.</li>
 * <li>Splitting an HTML tree of height 2 works as expected.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtils
 */
public final class TestSplitOnStartHTMLUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final HTMLUtils util = new HTMLUtils();

    /**
     * Default constructor.
     */
    public TestSplitOnStartHTMLUtils() {
        super();
    }

    /**
     * Tests that splitting an HTML tree of height 1 works as expected.
     */
    @Test
    public final void testSplit() {
        final String html;          // HTML code to split
        final Collection<String> result; // Split HTML
        final Iterator<String> itr; // Split HTML iterator

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";

        result = util.splitOnStart(html, "h1");

        Assert.assertEquals(result.size(), 2);

        itr = result.iterator();
        Assert.assertEquals(itr.next(),
                "<h1>A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>");
        Assert.assertEquals(itr.next(),
                "<h1>Another heading</h1>\n<p>Even more text</p>");
    }

    /**
     * Tests that splitting an HTML tree of height 2 works as expected.
     */
    @Test
    public final void testSplit_Deep() {
        final String html;          // HTML code to split
        final Collection<String> result; // Split HTML
        final Iterator<String> itr; // Split HTML iterator

        html = "<body><h1>A heading</h1><p>Some text</p><div><h1>Second heading</h1><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></div></body>";

        result = util.splitOnStart(html, "h1");

        Assert.assertEquals(result.size(), 3);

        itr = result.iterator();
        Assert.assertEquals(itr.next(),
                "<h1>A heading</h1>\n<p>Some text</p>\n<div></div>");
        Assert.assertEquals(itr.next(),
                "<h1>Second heading</h1>\n<h2>Subheading</h2>\n<p>More text</p>");
        Assert.assertEquals(itr.next(),
                "<h1>Another heading</h1>\n<p>Even more text</p>");
    }

    /**
     * Tests that splitting an HTML tree of height 1, including a tail split,
     * works as expected.
     */
    @Test
    public final void testSplit_Tail() {
        final String html;          // HTML code to split
        final Collection<String> result; // Split HTML
        final Iterator<String> itr; // Split HTML iterator

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body><h1>Extra heading</h1>";

        result = util.splitOnStart(html, "h1");

        Assert.assertEquals(result.size(), 3);

        itr = result.iterator();
        Assert.assertEquals(itr.next(),
                "<h1>A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>");
        Assert.assertEquals(itr.next(),
                "<h1>Another heading</h1>\n<p>Even more text</p>");
        Assert.assertEquals(itr.next(), "<h1>Extra heading</h1>");
    }

}
