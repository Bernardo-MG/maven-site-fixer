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
package com.wandrell.velocity.tool.testing.test.unit.site;

import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtils;
import com.wandrell.velocity.tool.SiteUtils;

import junit.framework.Assert;

/**
 * Unit tests for {@link SiteUtils}, testing the {@code fixHeadingIds} method.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>The id is correctly added to headings with points.</li>
 * <li>The id is correctly added to headings with spaces.</li>
 * <li>The id is correctly fixed.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtils
 */
public final class TestFixHeadingIdsSiteUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtils util = new SiteUtils();

    /**
     * Default constructor.
     */
    public TestFixHeadingIdsSiteUtils() {
        super();
    }

    /**
     * Tests that the id is correctly fixed.
     */
    @Test
    public final void testFixHeadingIds_HasId() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<h1 id=\"a.heading\">A heading</h1><h3 id=\"another.heading\">Another heading</h3>";

        result = util.fixHeadingIds(html);

        htmlExpected = "<h1 id=\"aheading\">A heading</h1>\n<h3 id=\"anotherheading\">Another heading</h3>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that the id is correctly added to headings with points.
     */
    @Test
    public final void testFixHeadingIds_Points() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<h1>com.wandrell</h1><h3>com.wandrell</h3>";

        result = util.fixHeadingIds(html);

        htmlExpected = "<h1 id=\"comwandrell\">com.wandrell</h1>\n<h3 id=\"comwandrell\">com.wandrell</h3>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that the id is correctly added to headings with spaces.
     */
    @Test
    public final void testFixHeadingIds_Spaces() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<h1>A heading</h1><h3>Another heading</h3>";

        result = util.fixHeadingIds(html);

        htmlExpected = "<h1 id=\"aheading\">A heading</h1>\n<h3 id=\"anotherheading\">Another heading</h3>";

        Assert.assertEquals(htmlExpected, result);
    }

}
