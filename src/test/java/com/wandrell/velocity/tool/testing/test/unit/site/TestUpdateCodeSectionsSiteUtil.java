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
 * Unit tests for {@link HTMLUtils}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When trying to fix the outdated code blocks these are updated correctly.
 * </li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtils
 */
public final class TestUpdateCodeSectionsSiteUtil {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtils util = new SiteUtils();

    /**
     * Default constructor.
     */
    public TestUpdateCodeSectionsSiteUtil() {
        super();
    }

    /**
     * Tests that when trying to fix the outdated code blocks these are updated
     * correctly.
     */
    @Test
    public final void testFixCodeBlock() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<div class=\"source\"><div class=\"source\"><pre>Some code</pre></div></div>";

        result = util.updateCodeSections(html);

        htmlExpected = "<pre><code>Some code</code></pre>";

        System.out.println(result);
        System.out.println(htmlExpected);

        Assert.assertEquals(htmlExpected, result);
    }

}
