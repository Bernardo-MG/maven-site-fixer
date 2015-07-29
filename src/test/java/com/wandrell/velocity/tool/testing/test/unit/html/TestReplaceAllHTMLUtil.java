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

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtil;

/**
 * Unit tests for {@link HTMLUtil}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Replacing all elements of a type works as expected.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtil
 */
public final class TestReplaceAllHTMLUtil {

    /**
     * Instance of the utils class being tested.
     */
    private final HTMLUtil util = new HTMLUtil();

    /**
     * Default constructor.
     */
    public TestReplaceAllHTMLUtil() {
        super();
    }

    /**
     * Tests that replacing all elements of a type works as expected.
     */
    @Test
    public final void testReplaceAll() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result
        final Map<String, String> replacements; // Mapped replacements

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";

        replacements = new LinkedHashMap<String, String>();
        replacements.put("h1", "<p>This was a header of first level</p>");
        replacements.put("h2", "<h1>This was a header of second level</h1>");

        result = util.replaceAll(html, replacements);

        htmlExpected = "<p>This was a header of first level</p>\n<p>Some text</p>\n<h1>This was a header of second level</h1>\n<p>More text</p>\n<p>This was a header of first level</p>\n<p>Even more text</p>";

        Assert.assertEquals(result, htmlExpected);
    }

}
