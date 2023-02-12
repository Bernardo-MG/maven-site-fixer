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
 * Unit tests for {@link SiteTool}, testing the {@code transformImagesToFigures} method.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 * @see SiteTool
 */
@DisplayName("SiteTool.transformImagesToFigures")
public final class TestSiteToolTransformImagesToFigures {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteTool util = new SiteTool();

    /**
     * Default constructor.
     */
    public TestSiteToolTransformImagesToFigures() {
        super();
    }

    @Test
    @DisplayName("Generates a caption from the alt attribute")
    public final void testCaption_Transforms() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<p><img src=\"imgs/diagram.png\" alt=\"A diagram\"></p>";
        htmlExpected = "<figure>\n <img src=\"imgs/diagram.png\" alt=\"A diagram\">\n <figcaption>\n  A diagram\n </figcaption>\n</figure>";

        element = Jsoup.parse(html)
            .body();
        util.transformImagesToFigures(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    @DisplayName("Transforming an emtpy string does nothing")
    public final void testEmptyString() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html)
            .body();
        util.transformImagesToFigures(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("If there are no images it does nothing")
    public final void testNoImages_Untouched() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<p>Some text</p>";
        htmlExpected = "<p>Some text</p>";

        element = Jsoup.parse(html)
            .body();
        util.transformImagesToFigures(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Transforms images into figures")
    public final void testSimple_Transforms() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<img src=\"imgs/diagram.png\">";
        htmlExpected = "<figure>\n <img src=\"imgs/diagram.png\">\n</figure>";

        element = Jsoup.parse(html)
            .body();
        util.transformImagesToFigures(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
