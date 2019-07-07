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

package com.bernardomg.velocity.tool.test.unit.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.bernardomg.velocity.tool.HtmlTool;

/**
 * Unit tests for {@link HtmlTool} testing the {@code addClass} method.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
@RunWith(JUnitPlatform.class)
public final class TestHtmlToolAddClass {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlTool util = new HtmlTool();

    /**
     * Default constructor.
     */
    public TestHtmlToolAddClass() {
        super();
    }

    /**
     * Tests that when adding a class, if the element has multiple classes the
     * new one is added.
     */
    @Test
    public final void testMultipleClasses() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final String cssClass;     // Removed class
        final Element element;     // Parsed HTML

        html = "<a class=\"class1 class2\" href=\"https://somewhere.com/\">A link</a>";
        htmlExpected = "<a class=\"class1 class2 class3\" href=\"https://somewhere.com/\">A link</a>";
        selector = "a";
        cssClass = "class3";

        element = Jsoup.parse(html).body();
        util.addClass(element, selector, cssClass);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that when adding a class, if the element has no classes the new one
     * is added.
     */
    @Test
    public final void testNoClass() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final String cssClass;     // Removed class
        final Element element;     // Parsed HTML

        html = "<a href=\"https://somewhere.com/\">A link</a>";
        htmlExpected = "<a href=\"https://somewhere.com/\" class=\"class1\">A link</a>";
        selector = "a";
        cssClass = "class1";

        element = Jsoup.parse(html).body();
        util.addClass(element, selector, cssClass);

        Assertions.assertEquals(htmlExpected, element.html());
    }

    /**
     * Tests that add to a not existing element does nothing.
     */
    @Test
    public final void testNotExistingElement_Untouched() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final String selector;     // CSS selector
        final String cssClass;     // Removed class
        final Element element;     // Parsed HTML

        html = "<a href=\"https://somewhere.com/\">A link</a>";
        htmlExpected = "<a href=\"https://somewhere.com/\">A link</a>";
        selector = "p";
        cssClass = "class2";

        element = Jsoup.parse(html).body();
        util.addClass(element, selector, cssClass);

        Assertions.assertEquals(htmlExpected, element.html());
    }

}
