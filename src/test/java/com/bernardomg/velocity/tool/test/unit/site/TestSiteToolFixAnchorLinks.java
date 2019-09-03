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

package com.bernardomg.velocity.tool.test.unit.site;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.bernardomg.velocity.tool.SiteTool;

/**
 * Unit tests for {@link SiteTool}, testing the {@code fixAnchorLinks} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see SiteTool
 */
@RunWith(JUnitPlatform.class)
public final class TestSiteToolFixAnchorLinks {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteTool util = new SiteTool();

    /**
     * Default constructor.
     */
    public TestSiteToolFixAnchorLinks() {
        super();
    }

    /**
     * Tests that an empty link is left untouched.
     */
    @Test
    public final void testEmptyLink_Untouched() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<a href=\"\">A link</a>";
        htmlExpected = html;

        element = Jsoup.parse(html).body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an external link is left untouched.
     */
    @Test
    public final void testExternalLink_Untouched() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<a href=\"www.somewhere.com\">A link</a>";
        htmlExpected = html;

        element = Jsoup.parse(html).body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an internal link is correctly formatted.
     */
    @Test
    public final void testInternalLink_Formatted() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<a href=\"#An_Internal. Link\">A link</a>";
        htmlExpected = "<a href=\"#an-internal-link\">A link</a>";

        element = Jsoup.parse(html).body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that HTML with no links is left untouched.
     */
    @Test
    public final void testNoAnchors_Untouched() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<p>Some text</p>";
        htmlExpected = html;

        element = Jsoup.parse(html).body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
