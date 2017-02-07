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

package com.wandrell.velocity.tool.test.unit.html;

import org.jsoup.nodes.Element;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HtmlUtils;

/**
 * Unit tests for {@link HtmlUtils} testing the {@code removeClass} method.
 * 
 * @author Bernardo Martínez Garrido
 * @see HtmlUtils
 */
public final class TestHtmlUtilsRemoveClass {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlUtils util = new HtmlUtils();

    /**
     * Default constructor.
     */
    public TestHtmlUtilsRemoveClass() {
        super();
    }

    /**
     * Tests that when removing a class, if there are multiple classes in the
     * element then they are left untouched.
     */
    @Test
    public final void testMultipleClasses() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<a class=\"externalLink class1\" href=\"https://somewhere.com/\">A link</a>";

        element = util.parse(html);
        util.removeClass(element, "a.externalLink", "externalLink");

        htmlExpected = "<a class=\"class1\" href=\"https://somewhere.com/\">A link</a>";

        Assert.assertEquals(element.html(), htmlExpected);
    }

    /**
     * Tests that when removing a class, if no more classes are left in the
     * element then the class attribute is removed too.
     */
    @Test
    public final void testNoClassLeft() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<a class=\"externalLink\" href=\"https://somewhere.com/\">A link</a>";

        element = util.parse(html);
        util.removeClass(element, "a.externalLink", "externalLink");

        htmlExpected = "<a href=\"https://somewhere.com/\">A link</a>";

        Assert.assertEquals(element.html(), htmlExpected);
    }

    /**
     * Tests that removing a not existing class does nothing.
     */
    @Test
    public final void testNotExistingClass_Untouched() {
        final String html;         // HTML code to edit
        final String htmlExpected; // Expected result
        final Element element;     // Parsed HTML

        html = "<p>Some text</p>";

        element = util.parse(html);
        util.removeClass(element, "a.externalLink", "externalLink");

        htmlExpected = "<p>Some text</p>";

        Assert.assertEquals(element.html(), htmlExpected);
    }

}
