/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2019 the original author or authors.
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.bernardomg.velocity.tool.Html5UpdateTool;

/**
 * Unit tests for {@link Html5UpdateTool} testing the {@code updateTableHeads}
 * method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see Html5UpdateTool
 */
@RunWith(JUnitPlatform.class)
@DisplayName("Html5UpdateTool.updateTableHeads")
public final class TestHtml5UpdateToolUpdateTableHeads {

    /**
     * Instance of the utils class being tested.
     */
    private final Html5UpdateTool util = new Html5UpdateTool();

    /**
     * Default constructor.
     */
    public TestHtml5UpdateToolUpdateTableHeads() {
        super();
    }

    @Test
    @DisplayName("Updating an empty string does nothing")
    public final void testEmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.updateTableHeads(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Updated a table's head")
    public final void testFullTable_UpdatesHeader() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<table border=\"0\" class=\"bodyTable testClass\"><tbody><tr class=\"a\"><th>Header 1</th><th>Header 2</th></tr><tr class=\"b\"><td>Data 1</td><td>Data 2</td></tr></tbody></table>";
        htmlExpected = "<table border=\"0\" class=\"bodyTable testClass\">\n <thead>\n  <tr class=\"a\">\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n </thead>\n <tbody>\n  <tr class=\"b\">\n   <td>Data 1</td>\n   <td>Data 2</td>\n  </tr>\n </tbody>\n</table>";

        element = Jsoup.parse(html).body();
        util.updateTableHeads(element);

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
