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

package com.wandrell.velocity.tool.test.unit.site;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.SiteUtils;

/**
 * Unit tests for {@link SiteUtils}, testing the {@code fixReport} method using
 * empty strings.
 * <p>
 * The meaning behind this test is verifying that the initial queries done by
 * the utilities class doesn't break with empty inputs.
 * 
 * @author Bernardo Martínez Garrido
 * @see SiteUtils
 */
public final class TestSiteUtilsFixReportEmpty {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtils util = new SiteUtils();

    /**
     * Default constructor.
     */
    public TestSiteUtilsFixReportEmpty() {
        super();
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testChangesReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "changes-report");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testCheckstyleReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "checkstyle");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testCpdReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "cpd");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testDependenciesReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "<h1>Dependencies Report</h1>";

        result = util.fixReport(html, "dependencies");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testFindbugsReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "findbugs");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testJdependReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "jdepend-report");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testLicenseReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "<h1>License</h1>";

        result = util.fixReport(html, "license");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testPluginManagementReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "plugin-management");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testPluginsReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "<h1>Plugins Report</h1>";

        result = util.fixReport(html, "plugins");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testPmdReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "pmd");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testProjectSummaryReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "project-summary");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testSurefireReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "surefire-report");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testTaglistReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "taglist");

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testTeamListReport_Empty() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";
        htmlExpected = "";

        result = util.fixReport(html, "team-list");

        Assert.assertEquals(result, htmlExpected);
    }

}
