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
 * <li>When trying to fix the table headers, if there are tables needing to be
 * fixed these are modified correctly.</li>
 * <li>When trying to fix the table headers, if there are no tables needing to
 * be fixed these are not touched.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtil
 */
public final class TestFixTableHeadSiteUtil {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtil util = new SiteUtil();

    /**
     * Default constructor.
     */
    public TestFixTableHeadSiteUtil() {
        super();
    }

    /**
     * Tests that when trying to fix the table headers, if there are tables
     * needing to be fixed these are modified correctly
     */
    @Test
    public final void testFixTableHeads_FixNeeded() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<table><tbody><tr><th>Header 1</th><th>Header 2</th></tr><tr><td>Data 1.1</td><td>Data 1.2</td></tr><tr><td>Data 2.1</td><td>Data 2.2</td></tr></tbody></table>";

        result = util.fixTableHeads(html);

        htmlExpected = "<table>\n <thead>\n  <tr>\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n </thead>\n <tbody>\n  <tr>\n   <td>Data 1.1</td>\n   <td>Data 1.2</td>\n  </tr>\n  <tr>\n   <td>Data 2.1</td>\n   <td>Data 2.2</td>\n  </tr>\n </tbody>\n</table>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when trying to fix the table headers, if there are no tables
     * needing to be fixed these are not touched.
     */
    @Test
    public final void testFixTableHeads_NoFixNeeded() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<table><thead><tr><th>Header 1</th><th>Header 2</th></tr></thead><tbody><tr><td>Data 1.1</td><td>Data 1.2</td></tr><tr><td>Data 2.1</td><td>Data 2.2</td></tr></tbody></table>";

        result = util.fixTableHeads(html);

        htmlExpected = "<table>\n <thead>\n  <tr>\n   <th>Header 1</th>\n   <th>Header 2</th>\n  </tr>\n </thead>\n <tbody>\n  <tr>\n   <td>Data 1.1</td>\n   <td>Data 1.2</td>\n  </tr>\n  <tr>\n   <td>Data 2.1</td>\n   <td>Data 2.2</td>\n  </tr>\n </tbody>\n</table>";

        Assert.assertEquals(htmlExpected, result);
    }

}
