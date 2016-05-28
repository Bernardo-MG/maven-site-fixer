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

package com.wandrell.velocity.tool.test.unit.site;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtils;
import com.wandrell.velocity.tool.SiteUtils;

/**
 * Unit tests for {@link SiteUtils}, testing the {@code fixAnchorLinks} method.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>An external link is left untouched.</li>
 * <li>An internal link is formatted.</li>
 * <li>An empty string causes no errors.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtils
 */
public final class TestFixAnchorLinksSiteUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtils util = new SiteUtils();

    /**
     * Default constructor.
     */
    public TestFixAnchorLinksSiteUtils() {
        super();
    }

    /**
     * Tests that an empty string causes no errors.
     */
    @Test
    public final void testEmpty_NoError() {
        util.fixAnchorLinks("");
    }

    /**
     * Tests that an external link is left untouched.
     */
    @Test
    public final void testExternalLink_NotChanged() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<a href=\"www.somewhere.com\">A link</a>";
        htmlExpected = "<a href=\"www.somewhere.com\">A link</a>";

        result = util.fixAnchorLinks(html);

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that an external link is left untouched.
     */
    @Test
    public final void testInternalLink_Formatted() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<a href=\"#an_internal_link\">A link</a>";
        htmlExpected = "<a href=\"#aninternallink\">A link</a>";

        result = util.fixAnchorLinks(html);

        Assert.assertEquals(result, htmlExpected);
    }

}
