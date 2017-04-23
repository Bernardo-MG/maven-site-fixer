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

package com.wandrell.velocity.tool.test.unit.site;

import org.jsoup.nodes.Element;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.SiteUtils;
import com.wandrell.velocity.tool.test.utils.test.AbstractUtilsTest;

/**
 * Unit tests for {@link SiteUtils}, testing the
 * {@code transformImagesToFigures} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see SiteUtils
 */
public final class TestSiteUtilsTransformTables extends AbstractUtilsTest {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtils util = new SiteUtils();

    /**
     * Default constructor.
     */
    public TestSiteUtilsTransformTables() {
        super();
    }

    /**
     * Tests that HTML with no tables is left untouched
     */
    @Test
    public final void testNoTable_Untouched() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<p>Some text</p>";
        htmlExpected = "<p>Some text</p>";

        runTest(html, htmlExpected);
    }

    /**
     * Tests that tables are transformed correctly.
     */
    @Test
    public final void testTable_Transforms() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<table class=\"bodyTable\"><thead><tr><th>Header 1</th><th>Header 2</th></tr></thead><tbody><tr><td>Data 1</td><td>Data 2</td></tr></tbody></table>";
        htmlExpected = "<table class=\"bodyTable table table-striped table-bordered\">\n <thead>\n  <tr>\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n </thead>\n <tbody>\n  <tr>\n   <td>Data 1</td>\n   <td>Data 2</td>\n  </tr>\n </tbody>\n</table>";

        runTest(html, htmlExpected);
    }

    @Override
    protected final void callTestedMethod(final Element element) {
        util.transformTables(element);
    }

}
