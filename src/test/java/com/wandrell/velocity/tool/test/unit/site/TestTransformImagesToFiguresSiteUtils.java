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
 * Unit tests for {@link SiteUtils}, testing the
 * {@code transformImagesToFigures} method.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>Transforming images to figures works correctly when an {@code alt}
 * attribute is present.</li>
 * <li>Transforming images to figures works correctly when an {@code alt}
 * attribute is not present.</li>
 * <li>Images out of a content element are ignored.</li>
 * <li>HTML with no images is ignored.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtils
 */
public final class TestTransformImagesToFiguresSiteUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final SiteUtils util = new SiteUtils();

    /**
     * Default constructor.
     */
    public TestTransformImagesToFiguresSiteUtils() {
        super();
    }

    /**
     * Tests that when transforming images to figures works correctly when an
     * {@code alt} attribute is not present.
     */
    @Test
    public final void testCaption_Transforms() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<section><p><img src=\"imgs/diagram.png\" alt=\"A diagram\"></p></section>";

        result = util.transformImagesToFigures(html);

        htmlExpected = "<section>\n <p>\n  <figure>\n   <img src=\"imgs/diagram.png\" alt=\"A diagram\">\n   <figcaption>\n    A diagram\n   </figcaption>\n  </figure></p>\n</section>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that when transforming images to figures works correctly when an
     * {@code alt} attribute is present.
     */
    @Test
    public final void testNoCaption_Transforms() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<section><img src=\"imgs/diagram.png\"></section>";

        result = util.transformImagesToFigures(html);

        htmlExpected = "<section>\n <figure>\n  <img src=\"imgs/diagram.png\">\n </figure>\n</section>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that HTML with no images is ignored.
     */
    @Test
    public final void testNoImages_Ignored() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<p>Some text</p>";

        result = util.transformImagesToFigures(html);

        htmlExpected = "<p>Some text</p>";

        Assert.assertEquals(result, htmlExpected);
    }

    /**
     * Tests that images out of a content element are ignored.
     */
    @Test
    public final void testOutOfContent_Ignored() {
        final String html;         // HTML code to fix
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<body><header><img src=\"imgs/header.png\" alt=\"Header image\"></header><section></section><footer><img src=\"imgs/footer.png\" alt=\"Footer image\"></footer></body>";

        result = util.transformImagesToFigures(html);

        htmlExpected = "<header>\n <img src=\"imgs/header.png\" alt=\"Header image\">\n</header>\n<section></section>\n<footer>\n <img src=\"imgs/footer.png\" alt=\"Footer image\">\n</footer>";

        Assert.assertEquals(result, htmlExpected);
    }

}
