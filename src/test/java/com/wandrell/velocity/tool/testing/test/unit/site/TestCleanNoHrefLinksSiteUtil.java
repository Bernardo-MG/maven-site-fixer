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

import com.wandrell.velocity.tool.HTMLUtil;
import com.wandrell.velocity.tool.SiteUtil;

import junit.framework.Assert;

/**
 * Unit tests for {@link HTMLUtil}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Links without the {@code href} attribute are removed.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtil
 */
public final class TestCleanNoHrefLinksSiteUtil {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtil util = new SiteUtil();

    /**
     * Default constructor.
     */
    public TestCleanNoHrefLinksSiteUtil() {
        super();
    }

    /**
     * Tests links without the {@code href} attribute are removed.
     */
    @Test
    public final void testCleanNoHrefLinks_MultipleClasses() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<h1><a>a_heading</a>A heading</h1>";

        result = util.cleanNoHrefLinks(html);

        htmlExpected = "<h1>A heading</h1>";

        Assert.assertEquals(htmlExpected, result);
    }

}
