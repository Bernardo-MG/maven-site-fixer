/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2021 the original author or authors.
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
 * Unit tests for {@link SiteTool}, testing the {@code fixHeadingIds} method.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 * @see SiteTool
 */
@DisplayName("SiteTool.fixHeadingIds")
public final class TestSiteToolFixHeadingIds {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteTool util = new SiteTool();

    /**
     * Default constructor.
     */
    public TestSiteToolFixHeadingIds() {
        super();
    }

    @Test
    @DisplayName("Fixing an empty string does nothing")
    public final void testEmptyString() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html)
            .body();
        util.fixHeadingIds(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Fixing an element without headings does nothing")
    public final void testNoHeadings_Untouched() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<p>Some text</p>";
        htmlExpected = html;

        element = Jsoup.parse(html)
            .body();
        util.fixHeadingIds(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Adds heading ids")
    public final void testNoId_CorrectId() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<h1>A heading</h1><h3>Another heading</h3>";
        htmlExpected = "<h1 id=\"A-heading\">A heading</h1>\n<h3 id=\"Another-heading\">Another heading</h3>";

        element = Jsoup.parse(html)
            .body();
        util.fixHeadingIds(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Adds heading ids for titles which include points")
    public final void testNoId_WithPoints_CorrectId() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<h1>com.bernardomg</h1><h3>com.bernardomg</h3>";
        htmlExpected = "<h1 id=\"combernardomg\">com.bernardomg</h1>\n<h3 id=\"combernardomg\">com.bernardomg</h3>";

        element = Jsoup.parse(html)
            .body();
        util.fixHeadingIds(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Adds heading ids for titles which include spaces")
    public final void testNoId_WithSpaces_CorrectId() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<h1>A heading</h1><h3>Another heading</h3>";
        htmlExpected = "<h1 id=\"A-heading\">A heading</h1>\n<h3 id=\"Another-heading\">Another heading</h3>";

        element = Jsoup.parse(html)
            .body();
        util.fixHeadingIds(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Adds heading ids for titles which include special characters")
    public final void testNoId_WithSpecialChars_CorrectId() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<h1>A -_#heading *! </h1><h3>Another heading</h3>";
        htmlExpected = "<h1 id=\"A---#heading-\">A -_#heading *! </h1>\n<h3 id=\"Another-heading\">Another heading</h3>";

        element = Jsoup.parse(html)
            .body();
        util.fixHeadingIds(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Fixes heading ids")
    public final void testWithId_CorrectId() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<h1 id=\"A.Heading\">A heading</h1><h3 id=\"another_heading\">Another heading</h3>";
        htmlExpected = "<h1 id=\"AHeading\">A heading</h1>\n<h3 id=\"another-heading\">Another heading</h3>";

        element = Jsoup.parse(html)
            .body();
        util.fixHeadingIds(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
