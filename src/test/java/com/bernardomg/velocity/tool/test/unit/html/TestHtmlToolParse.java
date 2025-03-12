/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2023 the original author or authors.
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

package com.bernardomg.velocity.tool.test.unit.html;

import org.assertj.core.api.Assertions;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.velocity.tool.HtmlTool;

/**
 * Unit tests for {@link HtmlTool} testing the {@code parse} method.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 * @see HtmlTool
 */
@DisplayName("HtmlTool.parse")
public final class TestHtmlToolParse {

    /**
     * Instance of the utils class being tested.
     */
    private final HtmlTool util = new HtmlTool();

    /**
     * Default constructor.
     */
    public TestHtmlToolParse() {
        super();
    }

    @Test
    @DisplayName("Parsing an empty string returns an element")
    public final void testParse_EmptyString() {
        final String  html;    // HTML code to edit
        final Element element; // Parsed HTML

        html = "";

        element = util.parse(html);

        Assertions.assertThat(element)
            .isNotNull();
    }

    @Test
    @DisplayName("Parsing a null returns an element")
    public final void testParse_Null() {
        final String  html;    // HTML code to edit
        final Element element; // Parsed HTML

        html = null;

        element = util.parse(html);

        Assertions.assertThat(element)
            .isNull();
    }

}
