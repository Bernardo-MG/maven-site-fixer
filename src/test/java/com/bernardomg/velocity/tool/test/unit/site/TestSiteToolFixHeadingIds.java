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

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.bernardomg.velocity.tool.SiteTool;
import com.bernardomg.velocity.tool.test.utils.test.AbstractUtilsTest;

/**
 * Unit tests for {@link SiteTool}, testing the {@code fixHeadingIds} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see SiteTool
 */
@RunWith(JUnitPlatform.class)
public final class TestSiteToolFixHeadingIds extends AbstractUtilsTest {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteTool util = new SiteTool();

    /**
     * Default constructor.
     */
    public TestSiteToolFixHeadingIds() {
        super();
    }

    /**
     * Tests that HTML with no headings is left untouched
     */
    @Test
    public final void testNoHeadings_Untouched() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<p>Some text</p>";
        htmlExpected = html;

        runTest(html, htmlExpected);
    }

    /**
     * Tests that the id is correctly fixed.
     */
    @Test
    public final void testWithId_CorrectId() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<h1 id=\"A.Heading\">A heading</h1><h3 id=\"another_heading\">Another heading</h3>";
        htmlExpected = "<h1 id=\"aheading\">A heading</h1>\n<h3 id=\"anotherheading\">Another heading</h3>";

        runTest(html, htmlExpected);
    }

    /**
     * Tests that the id is correctly added to headings with points.
     */
    @Test
    public final void testWithPoints_CorrectId() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<h1>com.bernardomg</h1><h3>com.bernardomg</h3>";
        htmlExpected = "<h1 id=\"combernardomg\">com.bernardomg</h1>\n<h3 id=\"combernardomg\">com.bernardomg</h3>";

        runTest(html, htmlExpected);
    }

    /**
     * Tests that the id is correctly added to headings with spaces.
     */
    @Test
    public final void testWithSpaces_CorrectId() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result

        html = "<h1>A heading</h1><h3>Another heading</h3>";
        htmlExpected = "<h1 id=\"aheading\">A heading</h1>\n<h3 id=\"anotherheading\">Another heading</h3>";

        runTest(html, htmlExpected);
    }

    @Override
    protected final void callTestedMethod(final Element element) {
        util.fixHeadingIds(element);
    }

}
