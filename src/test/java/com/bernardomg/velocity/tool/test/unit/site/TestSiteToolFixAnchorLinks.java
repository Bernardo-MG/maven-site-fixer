/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2023 the original author or authors.
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.velocity.tool.SiteTool;

/**
 * Unit tests for {@link SiteTool}, testing the {@code fixAnchorLinks} method.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 * @see SiteTool
 */
@DisplayName("SiteTool.fixAnchorLinks")
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

    @Test
    @DisplayName("Empty links are not modified")
    public final void testEmptyLink_Untouched() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<a href=\"\">A link</a>";
        htmlExpected = html;

        element = Jsoup.parse(html)
            .body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Fixing empty strings does nothing")
    public final void testEmptyString() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html)
            .body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("External links are not modified")
    public final void testExternalLink_Untouched() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<a href=\"www.somewhere.com\">A link</a>";
        htmlExpected = html;

        element = Jsoup.parse(html)
            .body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Internal links are formatted correctly")
    public final void testInternalLink_Formatted() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<a href=\"#An_Internal. Link \">A link</a>";
        htmlExpected = "<a href=\"#An-Internal-Link\">A link</a>";

        element = Jsoup.parse(html)
            .body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Internal links with special characters are formatted correctly")
    public final void testInternalLink_SpecialCharacters_Formatted() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<a href=\"#link_-with?special!*chars\">A link</a>";
        htmlExpected = "<a href=\"#link--withspecialchars\">A link</a>";

        element = Jsoup.parse(html)
            .body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Internal links with points are formatted correctly")
    public final void testInternalLink_WithPoints_Formatted() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<a href=\"#link.with.points\">A link</a>";
        htmlExpected = "<a href=\"#linkwithpoints\">A link</a>";

        element = Jsoup.parse(html)
            .body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Elements without anchors are not modified")
    public final void testNoAnchors_Untouched() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<p>Some text</p>";
        htmlExpected = html;

        element = Jsoup.parse(html)
            .body();
        util.fixAnchorLinks(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
