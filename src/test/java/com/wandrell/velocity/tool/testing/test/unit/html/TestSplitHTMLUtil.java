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

import com.wandrell.velocity.tool.HTMLUtil;

/**
 * Unit tests for {@link HTMLUtil}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Splitting an HTML tree of height 1 works as expected.</li>
 * <li>Splitting an HTML tree of height 1, including a tail split, works as
 * expected.</li>
 * <li>Splitting an HTML tree of height 2 works as expected.</li>
 * <li>Splitting an HTML tree of height 2 multiple times works as expected.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtil
 */
public final class TestSplitHTMLUtil {

    /**
     * Instance of the utils class being tested.
     */
    private final HTMLUtil util = new HTMLUtil();

    /**
     * Default constructor.
     */
    public TestSplitHTMLUtil() {
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

        html = "<body><hr><p>Some text</p><h2>Subheading</h2><p>More text</p><hr><p>Even more text</p></body>";

        result = util.split(html, "hr");

        Assert.assertEquals(result.size(), 3);

        itr = result.iterator();
        Assert.assertEquals(itr.next(), "");
        Assert.assertEquals(itr.next(),
                "<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>");
        Assert.assertEquals(itr.next(), "<p>Even more text</p>");
    }

    /**
     * Tests that splitting an HTML tree of height 2 works as expected.
     */
    @Test
    public final void testSplit_Deep() {
        final String html;          // HTML code to split
        final Collection<String> result; // Split HTML
        final Iterator<String> itr; // Split HTML iterator

        html = "<body><hr><p>Some text</p><div><h2>Subheading</h2><p>More text</p><hr><p>Even more text</p></div></body>";

        result = util.split(html, "hr");

        Assert.assertEquals(result.size(), 3);

        itr = result.iterator();
        Assert.assertEquals(itr.next(), "");
        Assert.assertEquals(itr.next(),
                "<p>Some text</p>\n<div>\n <h2>Subheading</h2>\n <p>More text</p>\n</div>");
        Assert.assertEquals(itr.next(), "<p>Even more text</p>");
    }

    /**
     * Tests that splitting an HTML tree of height 2 multiple times works as
     * expected.
     */
    @Test
    public final void testSplit_Deep_Multiple() {
        final String html;          // HTML code to split
        final Collection<String> result; // Split HTML
        final Iterator<String> itr; // Split HTML iterator

        html = "<body><hr><p>Some text</p><div><h2>Subheading</h2><p>More text</p><hr><p>Even more text</p><hr><p>Text</p></div></body>";

        result = util.split(html, "hr");

        Assert.assertEquals(result.size(), 4);

        itr = result.iterator();
        Assert.assertEquals(itr.next(), "");
        Assert.assertEquals(itr.next(),
                "<p>Some text</p>\n<div>\n <h2>Subheading</h2>\n <p>More text</p>\n</div>");
        Assert.assertEquals(itr.next(), "<p>Even more text</p>");
        Assert.assertEquals(itr.next(), "<p>Text</p>");
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

        html = "<body><hr><p>Some text</p><h2>Subheading</h2><p>More text</p><hr><p>Even more text</p></body><hr>";

        result = util.split(html, "hr");

        Assert.assertEquals(result.size(), 4);

        itr = result.iterator();
        Assert.assertEquals(itr.next(), "");
        Assert.assertEquals(itr.next(),
                "<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>");
        Assert.assertEquals(itr.next(), "<p>Even more text</p>");
        Assert.assertEquals(itr.next(), "");
    }

}
