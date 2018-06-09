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

package com.bernardomg.velocity.tool.test.unit.site;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;

import com.bernardomg.velocity.tool.SiteTool;

/**
 * Unit tests for {@link SiteTool}, testing the {@code fixReport} method using
 * empty strings.
 * <p>
 * The meaning behind this test is verifying that the initial queries done by
 * the utilities class doesn't break with empty inputs.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see SiteTool
 */
public final class TestSiteToolFixReportEmpty {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteTool util = new SiteTool();

    /**
     * Default constructor.
     */
    public TestSiteToolFixReportEmpty() {
        super();
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testChangesReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "changes-report");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testCheckstyleReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "checkstyle");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testCpdReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "cpd");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testDependenciesReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "<h1>Dependencies Report</h1>";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "dependencies");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testFailsafeReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "failsafe-report");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testFindbugsReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "findbugs");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testJdependReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "jdepend-report");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testLicenseReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "<h1>License</h1>";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "license");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testPluginManagementReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "plugin-management");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testPluginsReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "<h1>Plugins Report</h1>";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "plugins");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testPmdReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "pmd");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testProjectSummaryReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "project-summary");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testSurefireReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "surefire-report");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testTaglistReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "taglist");

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testTeamListReport_Empty() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "team-list");

        Assert.assertEquals(htmlExpected, element.html());
    }

}
