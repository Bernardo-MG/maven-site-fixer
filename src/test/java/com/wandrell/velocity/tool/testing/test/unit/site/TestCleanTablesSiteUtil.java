/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 the original author or authors.
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
package com.wandrell.velocity.tool.testing.test.unit.site;

import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtil;
import com.wandrell.velocity.tool.SiteUtil;

import junit.framework.Assert;

/**
 * Unit tests for {@link HTMLUtil}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Outdated tables are correctly cleaned up.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtil
 */
public class TestCleanTablesSiteUtil {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtil util = new SiteUtil();

    /**
     * Default constructor.
     */
    public TestCleanTablesSiteUtil() {
        super();
    }

    /**
     * Tests that outdated tables are correctly cleaned up.
     */
    @Test
    public final void testCleanTables() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<table border=\"0\" class=\"bodyTable table table-striped table-bordered\"><thead><tr class=\"a\"><th>Header 1</th><th>Header 2</th></tr></thead><tbody><tr class=\"b\"><td>Data 1</td><td>Data 2</td></tr></tbody></table>";

        result = util.cleanTables(html);

        htmlExpected = "<table class=\"table table-striped table-bordered\">\n <thead>\n  <tr>\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n </thead>\n <tbody>\n  <tr>\n   <td>Data 1</td>\n   <td>Data 2</td>\n  </tr>\n </tbody>\n</table>";

        Assert.assertEquals(htmlExpected, result);
    }

}
