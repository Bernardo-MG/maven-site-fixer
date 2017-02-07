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

package com.wandrell.velocity.tool.test.unit.html;

import org.jsoup.nodes.Element;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HtmlUtils;

/**
 * Unit tests for {@link HtmlUtils} testing the {@code removeAttribute} method.
 * 
 * @author Bernardo Martínez Garrido
 * @see HtmlUtils
 */
public class TestHtmlUtilsRemoveAttribute {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlUtils util = new HtmlUtils();

    /**
     * Default constructor.
     */
    public TestHtmlUtilsRemoveAttribute() {
        super();
    }

    /**
     * Tests that removing not existing attributes does nothing.
     */
    @Test
    public final void testNotExistingAttribute_Untouched() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<table class=\"bodyTable testClass\"><tbody><tr class=\"a\"><th>Header 1</th><th>Header 2</th></tr><tr class=\"b\"><td>Data 1</td><td>Data 2</td></tr></tbody></table>";

        element = new HtmlUtils().parse(html);
        util.removeAttribute(element, "table[border]", "border");

        htmlExpected = "<table class=\"bodyTable testClass\">\n <tbody>\n  <tr class=\"a\">\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n  <tr class=\"b\">\n   <td>Data 1</td>\n   <td>Data 2</td>\n  </tr>\n </tbody>\n</table>";

        Assert.assertEquals(element.html(), htmlExpected);
    }

    /**
     * Tests that attributes are removed.
     */
    @Test
    public final void testRemovesAttribute() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<table border=\"0\" class=\"bodyTable testClass\"><tbody><tr class=\"a\"><th>Header 1</th><th>Header 2</th></tr><tr class=\"b\"><td>Data 1</td><td>Data 2</td></tr></tbody></table>";

        element = new HtmlUtils().parse(html);
        util.removeAttribute(element, "table[border]", "border");

        htmlExpected = "<table class=\"bodyTable testClass\">\n <tbody>\n  <tr class=\"a\">\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n  <tr class=\"b\">\n   <td>Data 1</td>\n   <td>Data 2</td>\n  </tr>\n </tbody>\n</table>";

        Assert.assertEquals(element.html(), htmlExpected);
    }

}
