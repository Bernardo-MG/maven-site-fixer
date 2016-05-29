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

package com.wandrell.velocity.tool.test.unit.html5fix;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTML5UpdateUtils;

/**
 * Unit tests for {@link HTML5UpdateUtils}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When trying to fix the outdated section divisions these are updated
 * correctly.</li>
 * <li>HTML with no outdated sections is ignored.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTML5UpdateUtils
 */
public final class TestUpdateSectionDivHTML5UpdateUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final HTML5UpdateUtils util = new HTML5UpdateUtils();

    /**
     * Default constructor.
     */
    public TestUpdateSectionDivHTML5UpdateUtils() {
        super();
    }

    /**
     * Tests that HTML with no outdated sections is ignored.
     */
    @Test
    public final void testNoSections_Ignored() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<p>Some text</p>";

        result = util.updateSectionDiv(html);

        htmlExpected = "<p>Some text</p>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that when trying to fix the outdated section divisions these are
     * updated correctly.
     */
    @Test
    public final void testOutdatedSection_Updated() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<div class=\"section\"><p>Some text</p></div>";

        result = util.updateSectionDiv(html);

        htmlExpected = "<section>\n <p>Some text</p>\n</section>";

        Assert.assertEquals(result, htmlExpected);
    }

}
