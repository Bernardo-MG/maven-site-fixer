/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2021 the original author or authors.
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.velocity.tool.SiteTool;

/**
 * Unit tests for {@link SiteTool}, testing the {@code fixReport} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see SiteTool
 */
@DisplayName("SiteTool.fixReport")
public final class TestSiteToolFixReport {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteTool util = new SiteTool();

    /**
     * Default constructor.
     */
    public TestSiteToolFixReport() {
        super();
    }

    @Test
    @DisplayName("Fixes changes report")
    public final void testChangesReport() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<section><h2>Project Changes</h2><section><h3>Release History</h3></section><section><h3 id=\"a010\">Release 0.1.0 – 2015-05-17</h3></section></section>";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "changes-report");

        htmlExpected = "<h1>Project Changes</h1>\n<section>\n <h2>Release History</h2>\n</section>\n<section id=\"a010\">\n <h3>Release 0.1.0 <small>(<time>2015-05-17</time>)</small></h3>\n</section>";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Fixes checkstyle report")
    public final void testCheckstyleReport() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<h2>Checkstyle</h2><section><p><img alt=\"rss feed\" src=\"images/rss.png\"></p></section>";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "checkstyle");

        htmlExpected = "<h1>Checkstyle</h1>\n<section>\n <p></p>\n</section>";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Fixing an empty string does nothing")
    public final void testEmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";
        htmlExpected = "";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "");

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Fixes plugin management report")
    public final void testPluginManagementReport() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<section><h2>Plugin Management</h2><p>Data</p></section>";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "plugin-management");

        htmlExpected = "<h1>Plugin Management</h1>\n<p>Data</p>";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Fixes plugins report")
    public final void testPluginsReport() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<section><h2>Heading 2</h2></section>";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "plugins");

        htmlExpected = "<h1>Plugins Report</h1>\n<section>\n <h2>Heading 2</h2>\n</section>";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    @Test
    @DisplayName("Fixes surefire report")
    public final void testSurefireReport() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<section><h2>Surefire Report</h2></section><section><h2>Summary</h2></section><section><h2>Package List</h2></section>";

        element = Jsoup.parse(html).body();
        util.fixReport(element, "surefire-report");

        htmlExpected = "<section>\n <h1>Surefire Report</h1>\n</section>\n<section>\n <h2>Summary</h2>\n</section>\n<section>\n <h2>Package List</h2>\n</section>";

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
