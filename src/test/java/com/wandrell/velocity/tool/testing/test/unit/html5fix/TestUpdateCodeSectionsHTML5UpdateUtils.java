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
package com.wandrell.velocity.tool.testing.test.unit.html5fix;

import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTML5UpdateUtils;

import junit.framework.Assert;

/**
 * Unit tests for {@link HTML5UpdateUtils}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When trying to fix the outdated code blocks these are updated correctly.
 * </li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTML5UpdateUtils
 */
public final class TestUpdateCodeSectionsHTML5UpdateUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final HTML5UpdateUtils util = new HTML5UpdateUtils();

    /**
     * Default constructor.
     */
    public TestUpdateCodeSectionsHTML5UpdateUtils() {
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

        Assert.assertEquals(htmlExpected, result);
    }

}
