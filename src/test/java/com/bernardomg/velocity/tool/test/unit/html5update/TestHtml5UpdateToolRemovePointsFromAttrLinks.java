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

package com.bernardomg.velocity.tool.test.unit.html5update;

import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.velocity.tool.Html5UpdateTool;

/**
 * Unit tests for {@link Html5UpdateTool} testing the {@code removePointsFromAttr} method.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 * @see Html5UpdateTool
 */
@DisplayName("Html5UpdateTool.removePointsFromAttr")
public final class TestHtml5UpdateToolRemovePointsFromAttrLinks {

    /**
     * Instance of the utils class being tested.
     */
    private final Html5UpdateTool util = new Html5UpdateTool();

    /**
     * Default constructor.
     */
    public TestHtml5UpdateToolRemovePointsFromAttrLinks() {
        super();
    }

    @Test
    @DisplayName("Removing from an empty string does nothing")
    public final void testEmptyString() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<a name=\"a_heading\" href=\"a.b.c\">Text</a>";
        htmlExpected = "<a name=\"a_heading\" href=\"abc\">Text</a>";

        element = Jsoup.parse(html)
            .body();
        util.removePointsFromAttr(element, "[href]", "href");

        Assertions.assertThat(element.html())
            .isEqualTo(htmlExpected);
    }

    @Test
    @DisplayName("Points are removed from links")
    public final void testSimple_Removed() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "<a name=\"a_heading\" href=\"a.b.c\">Text</a>";
        htmlExpected = "<a name=\"a_heading\" href=\"abc\">Text</a>";

        element = Jsoup.parse(html)
            .body();
        util.removePointsFromAttr(element, "[href]", "href");

        Assertions.assertThat(element.html())
            .isEqualTo(htmlExpected);
    }

}
