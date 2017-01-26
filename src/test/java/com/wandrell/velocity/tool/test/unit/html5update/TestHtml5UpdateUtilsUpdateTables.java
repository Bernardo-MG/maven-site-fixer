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

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.Html5UpdateUtils;

/**
 * Unit tests for {@link Html5UpdateUtils}.
 * 
 * @author Bernardo Martínez Garrido
 * @see Html5UpdateUtils
 */
public class TestHtml5UpdateUtilsUpdateTables {

    /**
     * Instance of the utils class being tested.
     */
    private final Html5UpdateUtils util = new Html5UpdateUtils();

    /**
     * Default constructor.
     */
    public TestHtml5UpdateUtilsUpdateTables() {
        super();
    }

    /**
     * Tests that HTML with no tables is ignored.
     */
    @Test
    public final void testNoTables_Ignored() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<p>Some text</p>";

        result = util.updateTables(html);

        htmlExpected = "<p>Some text</p>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that outdated tables with additional classes keep these.
     */
    @Test
    public final void testOutdatedTable_KeepsAdditionalClasses() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<table border=\"0\" class=\"bodyTable testClass\"><tbody><tr class=\"a\"><th>Header 1</th><th>Header 2</th></tr><tr class=\"b\"><td>Data 1</td><td>Data 2</td></tr></tbody></table>";

        result = util.updateTables(html);

        htmlExpected = "<table class=\"testClass\">\n <thead>\n  <tr>\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n </thead>\n <tbody>\n  <tr>\n   <td>Data 1</td>\n   <td>Data 2</td>\n  </tr>\n </tbody>\n</table>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that outdated tables are correctly cleaned up.
     */
    @Test
    public final void testOutdatedTable_Updated() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<table border=\"0\" class=\"bodyTable\"><tbody><tr class=\"a\"><th>Header 1</th><th>Header 2</th></tr><tr class=\"b\"><td>Data 1</td><td>Data 2</td></tr></tbody></table>";

        result = util.updateTables(html);

        htmlExpected = "<table>\n <thead>\n  <tr>\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n </thead>\n <tbody>\n  <tr>\n   <td>Data 1</td>\n   <td>Data 2</td>\n  </tr>\n </tbody>\n</table>";

        Assert.assertEquals(result, htmlExpected);
    }

}
