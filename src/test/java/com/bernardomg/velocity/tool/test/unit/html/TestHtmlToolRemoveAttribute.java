/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2025 the original author or authors.
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

package com.bernardomg.velocity.tool.test.unit.html;

import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.velocity.tool.HtmlTool;

/**
 * Unit tests for {@link HtmlTool} testing the {@code removeAttribute} method.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
@DisplayName("HtmlTool.removeAttribute")
public final class TestHtmlToolRemoveAttribute {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlTool util = new HtmlTool();

    /**
     * Default constructor.
     */
    public TestHtmlToolRemoveAttribute() {
        super();
    }

    @Test
    @DisplayName("Removing not existing attributes does nothing")
    public final void testNotExistingAttribute_Untouched() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final String  selector;     // CSS selector
        final String  attribute;    // Removed attribute
        final Element element;      // Parsed HTML

        html = "<table class=\"bodyTable testClass\"><tbody><tr class=\"a\"><th>Header 1</th><th>Header 2</th></tr><tr class=\"b\"><td>Data 1</td><td>Data 2</td></tr></tbody></table>";
        htmlExpected = """
                       <table class=\"bodyTable testClass\">
                        <tbody>
                         <tr class=\"a\">
                          <th>Header 1</th>
                          <th>Header 2</th>
                         </tr>
                         <tr class=\"b\">
                          <td>Data 1</td>
                          <td>Data 2</td>
                         </tr>
                        </tbody>
                       </table>""";
        selector = "table";
        attribute = "border";

        element = Jsoup.parse(html)
            .body();
        util.removeAttribute(element, selector, attribute);

        Assertions.assertThat(element.html())
            .isEqualTo(htmlExpected);
    }

    @Test
    @DisplayName("Removing attributes from an empty string does nothing")
    public final void testRemoveAttribute_EmptyString() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final Element element;      // Parsed HTML

        html = "";

        element = Jsoup.parse(html)
            .body();
        util.removeAttribute(element, "a.externalLink", "externalLink");

        htmlExpected = "";

        Assertions.assertThat(element.html())
            .isEqualTo(htmlExpected);
    }

    @Test
    @DisplayName("Removing attributes")
    public final void testRemovesAttribute() {
        final String  html;         // HTML code to edit
        final String  htmlExpected; // Expected result
        final String  selector;     // CSS selector
        final String  attribute;    // Removed attribute
        final Element element;      // Parsed HTML

        html = "<table border=\"0\" class=\"bodyTable testClass\"><tbody><tr class=\"a\"><th>Header 1</th><th>Header 2</th></tr><tr class=\"b\"><td>Data 1</td><td>Data 2</td></tr></tbody></table>";
        htmlExpected = """
                       <table class=\"bodyTable testClass\">
                        <tbody>
                         <tr class=\"a\">
                          <th>Header 1</th>
                          <th>Header 2</th>
                         </tr>
                         <tr class=\"b\">
                          <td>Data 1</td>
                          <td>Data 2</td>
                         </tr>
                        </tbody>
                       </table>""";
        selector = "table[border]";
        attribute = "border";

        element = Jsoup.parse(html)
            .body();
        util.removeAttribute(element, selector, attribute);

        Assertions.assertThat(element.html())
            .isEqualTo(htmlExpected);
    }

}
