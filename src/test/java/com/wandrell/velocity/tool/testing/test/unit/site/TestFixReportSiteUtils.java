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

import com.wandrell.velocity.tool.HTMLUtils;
import com.wandrell.velocity.tool.SiteUtils;

import junit.framework.Assert;

/**
 * Unit tests for {@link HTMLUtils}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Transforming images to figures works correctly when an {@code alt}
 * attribute is present.</li>
 * <li>Images out of a content element are ignored.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtils
 */
public final class TestFixReportSiteUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtils util = new SiteUtils();

    /**
     * Default constructor.
     */
    public TestFixReportSiteUtils() {
        super();
    }

    @Test
    public final void testFixReportHeading_Changes() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<section><h2>Project Changes</h2><section><h3>Release History</h3></section><section><h3 id=\"a010\">Release 0.1.0 – 2015-05-17</h3></section></section>";

        result = util.fixReport(html, "changes-report");

        htmlExpected = "<h1>Project Changes</h1>\n<section>\n <h2>Release History</h2>\n</section>\n<section id=\"a010\">\n <h3>Release 0.1.0 <small>(<time>2015-05-17</time>)</small></h3>\n</section>";

        System.out.println(result);
        Assert.assertEquals(htmlExpected, result);
    }

    @Test
    public final void testFixReportHeading_PluginManagement() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<section><h2>Plugin Management</h2><p>Data</p></section>";

        result = util.fixReport(html, "plugin-management");

        htmlExpected = "<h1>Plugin Management</h1>\n<p>Data</p>";

        Assert.assertEquals(htmlExpected, result);
    }

    @Test
    public final void testFixReportHeading_Plugins() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<section><h2>Heading 2</h2></section>";

        result = util.fixReport(html, "plugins");

        htmlExpected = "<h1>Plugins Report</h1>\n<section>\n <h2>Heading 2</h2>\n</section>";

        Assert.assertEquals(htmlExpected, result);
    }

}
