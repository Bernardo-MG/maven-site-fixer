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
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.Html5UpdateUtils;
import com.wandrell.velocity.tool.test.utils.test.AbstractUtilsTest;

/**
 * Unit tests for {@link Html5UpdateUtils} testing the {@code removeNoHrefLinks}
 * method.
 * 
 * @author Bernardo Martínez Garrido
 * @see Html5UpdateUtils
 */
public final class TestHtml5UpdateUtilsRemoveNoHrefLinks
        extends AbstractUtilsTest {

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
     * Tests that HTML with no links is not edited.
     */
    @Test
    public final void testNoAnchors_Nothing() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<p>Some text</p>";
        htmlExpected = html;

        runTest(html, htmlExpected);
    }

    /**
     * Tests that links without the {@code href} attribute are removed.
     */
    @Test
    public final void testNoHref_Empty_Removed() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<h1><a name=\"a_heading\"></a>A heading</h1><h3><a name=\"a_heading\"/>A heading</h3><a></a>";
        htmlExpected = "<h1>A heading</h1>\n<h3>A heading</h3>";

        runTest(html, htmlExpected);
    }

    /**
     * Tests that links without the {@code href} attribute are removed, and
     * their contents moved to the parent.
     */
    @Test
    public final void testNoHref_WithText_TextKept() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<h1><a name=\"a_heading\">A heading</a></h1><h3><a name=\"a_heading\">A heading</h3></a><a></a>";
        htmlExpected = "<h1>A heading</h1>\n<h3>A heading</h3>";

        runTest(html, htmlExpected);
    }

    @Override
    protected final void callTestedMethod(final Element element) {
        util.removeNoHrefLinks(element);
    }

}
