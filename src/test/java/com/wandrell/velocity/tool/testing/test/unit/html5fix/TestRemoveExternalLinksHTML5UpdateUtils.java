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
 * <li>When removing the externalLink class from links, if no more classes are
 * left then the class attribute is removed too.</li>
 * <li>When removing the externalLink class from links, if more classes are left
 * then they are untouched.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTML5UpdateUtils
 */
public final class TestRemoveExternalLinksHTML5UpdateUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final HTML5UpdateUtils util = new HTML5UpdateUtils();

    /**
     * Default constructor.
     */
    public TestRemoveExternalLinksHTML5UpdateUtils() {
        super();
    }

    /**
     * Tests that when removing the externalLink class from links, if more
     * classes are left then they are untouched.
     */
    @Test
    public final void testCleanExternalLinks_MultipleClasses() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<a class=\"externalLink class1\" href=\"https://somewhere.com/\">A link</a>";

        result = util.removeExternalLinks(html);

        htmlExpected = "<a class=\"class1\" href=\"https://somewhere.com/\">A link</a>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when removing the externalLink class from links, if no more
     * classes are left then the class attribute is removed too.
     */
    @Test
    public final void testCleanExternalLinks_SingleClass() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<a class=\"externalLink\" href=\"https://somewhere.com/\">A link</a>";

        result = util.removeExternalLinks(html);

        htmlExpected = "<a href=\"https://somewhere.com/\">A link</a>";

        Assert.assertEquals(htmlExpected, result);
    }

}
