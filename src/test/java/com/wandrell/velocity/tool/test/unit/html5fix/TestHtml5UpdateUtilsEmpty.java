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

package com.wandrell.velocity.tool.test.unit.html5fix;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.Html5UpdateUtils;

/**
 * Unit tests for {@link Html5UpdateUtils}, testing the methods using empty
 * strings.
 * <p>
 * The meaning behind this test is verifying that the initial queries done by
 * the utilities class doesn't break with empty inputs.
 * 
 * @author Bernardo Martínez Garrido
 * @see Html5UpdateUtils
 */
public final class TestHtml5UpdateUtilsEmpty {

    /**
     * Instance of the utils class being tested.
     */
    private final Html5UpdateUtils util = new Html5UpdateUtils();

    /**
     * Default constructor.
     */
    public TestHtml5UpdateUtilsEmpty() {
        super();
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testFixInternalLinks_EmptyString() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";

        result = util.fixInternalLinks(html);

        htmlExpected = "";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testRemoveExternalLinks_EmptyString() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";

        result = util.removeExternalLinks(html);

        htmlExpected = "";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testRemoveNoHrefLinks_EmptyString() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";

        result = util.removeNoHrefLinks(html);

        htmlExpected = "";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testUpdateCodeSections_EmptyString() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";

        result = util.updateCodeSections(html);

        htmlExpected = "";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testUpdateSectionDiv_EmptyString() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";

        result = util.updateSectionDiv(html);

        htmlExpected = "";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an empty string causes no problem.
     */
    @Test
    public final void testUpdateTables_EmptyString() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "";

        result = util.updateTables(html);

        htmlExpected = "";

        Assert.assertEquals(result, htmlExpected);
    }

}
