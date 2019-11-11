/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2019 the original author or authors.
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

package com.bernardomg.velocity.tool.test.unit.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.bernardomg.velocity.tool.HtmlTool;

/**
 * Unit tests for {@link HtmlTool}, testing the methods using empty strings.
 * <p>
 * The meaning behind this test is verifying that the initial queries done by
 * the utilities class doesn't break with empty inputs.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
@RunWith(JUnitPlatform.class)
public final class TestHtmlToolEmpty {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlTool util = new HtmlTool();

    /**
     * Default constructor.
     */
    public TestHtmlToolEmpty() {
        super();
    }

    /**
     * Tests that an empty string causes no problem to the {@code addClass}
     * method.
     */
    @Test
    public final void testAddClass_EmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.addClass(element, "a.externalLink", "externalLink");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem to the
     * {@code removeAttribute} method.
     */
    @Test
    public final void testRemoveAttribute_EmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.removeAttribute(element, "a.externalLink", "externalLink");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem to the {@code removeClass}
     * method.
     */
    @Test
    public final void testRemoveClass_EmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.removeClass(element, "a.externalLink", "externalLink");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem to the {@code retag} method.
     */
    @Test
    public final void testRetag_EmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.retag(element, "a.externalLink", "externalLink");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem to the
     * {@code swapTagWithParent} method.
     */
    @Test
    public final void testSwapTagWithParent_EmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.swapTagWithParent(element, "code > pre");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem to the {@code unwrap}
     * method.
     */
    @Test
    public final void testUnwrap_EmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.unwrap(element, "a:not([href])");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that an empty string causes no problem to the {@code wrap} method.
     */
    @Test
    public final void testWrap_EmptyString() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "";

        element = Jsoup.parse(html).body();
        util.wrap(element, "h1", "<header></header>");

        htmlExpected = "";

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
