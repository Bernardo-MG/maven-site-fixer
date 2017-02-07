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

package com.wandrell.velocity.tool.test.unit.html5update;

import org.jsoup.nodes.Element;
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
public final class TestHtml5UpdateUtilsRemoveNoHrefLinks {

    /**
     * Instance of the utils class being tested.
     */
    private final Html5UpdateUtils util = new Html5UpdateUtils();

    /**
     * Default constructor.
     */
    public TestHtml5UpdateUtilsRemoveNoHrefLinks() {
        super();
    }

    /**
     * Tests links without the {@code href} attribute are removed.
     */
    @Test
    public final void testHeading_NoHref_Removed() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<h1><a name=\"a_heading\"></a>A heading</h1><h3><a name=\"a_heading\"/>A heading</h3><a></a>";

        element = new HtmlUtils().parse(html);
        util.removeNoHrefLinks(element);

        htmlExpected = "<h1>A heading</h1>\n<h3>A heading</h3>";

        Assert.assertEquals(element.html(), htmlExpected);
    }

    /**
     * Tests links without the {@code href} attribute are removed, and their
     * contents moved to the parent.
     */
    @Test
    public final void testHeading_NoHref_WithText_TextKept() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<h1><a name=\"a_heading\">A heading</a></h1><h3><a name=\"a_heading\">A heading</h3></a><a></a>";

        element = new HtmlUtils().parse(html);
        util.removeNoHrefLinks(element);

        htmlExpected = "<h1>A heading</h1>\n<h3>A heading</h3>";

        Assert.assertEquals(element.html(), htmlExpected);
    }

    /**
     * Tests that HTML with no links is ignored.
     */
    @Test
    public final void testNoAnchors_Ignored() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<p>Some text</p>";

        element = new HtmlUtils().parse(html);
        util.removeNoHrefLinks(element);

        htmlExpected = "<p>Some text</p>";

        Assert.assertEquals(element.html(), htmlExpected);
    }

}
