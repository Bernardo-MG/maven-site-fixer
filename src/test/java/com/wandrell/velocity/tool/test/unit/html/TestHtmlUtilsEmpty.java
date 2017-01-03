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

package com.wandrell.velocity.tool.test.unit.html;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HtmlUtils;

/**
 * Unit tests for {@link HtmlUtils}, testing the methods using empty strings.
 * <p>
 * The meaning behind this test is verifying that the initial queries done by
 * the utilities class doesn't break with empty inputs.
 * 
 * @author Bernardo Martínez Garrido
 * @see HtmlUtils
 */
public final class TestHtmlUtilsEmpty {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlUtils util = new HtmlUtils();

    /**
     * Default constructor.
     */
    public TestHtmlUtilsEmpty() {
        super();
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testWrap_EmptyString() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";

        result = util.wrap(html, "h1", "<header></header>");

        htmlExpected = "";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testWrapFirst_EmptyString() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";

        result = util.wrapFirst(html, "h1", "<header></header>");

        htmlExpected = "";

        Assert.assertEquals(result, htmlExpected);
    }

}