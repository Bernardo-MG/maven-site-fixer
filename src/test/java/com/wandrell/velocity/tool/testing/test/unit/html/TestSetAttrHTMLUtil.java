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
package com.wandrell.velocity.tool.testing.test.unit.html;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtils;

/**
 * Unit tests for {@link HTMLUtils}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Setting an attribute value when the attribute does not exists adds it.
 * </li>
 * <li>Setting an attribute value when the attribute exists changes it's value.
 * </li>
 * <li>Setting an attribute on an element which doesn't exist does nothing.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtils
 */
public final class TestSetAttrHTMLUtil {

    /**
     * Instance of the utils class being tested.
     */
    private final HTMLUtils util = new HTMLUtils();

    /**
     * Default constructor.
     */
    public TestSetAttrHTMLUtil() {
        super();
    }

    /**
     * Tests that setting an attribute value when the attribute exists changes
     * it's value.
     */
    @Test
    public final void testSetAttr_ExistsAttribute() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<body><h1 attr1=\"value2\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";

        result = util.setAttr(html, "h1", "attr1", "value1");

        htmlExpected = "<h1 attr1=\"value1\">A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>\n<h1 attr1=\"value1\">Another heading</h1>\n<p>Even more text</p>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that setting an attribute value when the attribute does not exists
     * adds it.
     */
    @Test
    public final void testSetAttr_NoAttribute() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";

        result = util.setAttr(html, "h1", "attr1", "value1");

        htmlExpected = "<h1 attr1=\"value1\">A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>\n<h1 attr1=\"value1\">Another heading</h1>\n<p>Even more text</p>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that setting an attribute on an element which doesn't exist does
     * nothing.
     */
    @Test
    public final void testSetAttr_NoElement() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";

        result = util.setAttr(html, "h3", "attr1", "value1");

        htmlExpected = "<h1>A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>\n<h1>Another heading</h1>\n<p>Even more text</p>";

        Assert.assertEquals(result, htmlExpected);
    }

}
