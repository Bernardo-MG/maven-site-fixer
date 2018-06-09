/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2017 the original author or authors.
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

package com.bernardomg.velocity.tool.test.utils.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Assert;

/**
 * Base class for HTML modification tests. It simplifies the test methods by
 * encapsulating the repetitive code, so each test just contains the meaningful
 * variables.
 * <p>
 * This variant of the base test is meant for those tests which require a CSS
 * selector.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public abstract class AbstractUtilsSelectorTest {

    /**
     * Default constructor.
     */
    public AbstractUtilsSelectorTest() {
        super();
    }

    /**
     * Runs the test with the specified HTML values and using the specified CSS
     * selector.
     * <p>
     * This will call the tested method, after creating an {@code Element} from
     * the received HTML code, and will compare the result with the specified
     * HTML.
     * <p>
     * If the final HTML does not match the HTML contained in the {@code result}
     * parameter then the test will fail.
     * 
     * @param html
     *            initial HTML
     * @param htmlExpected
     *            expected HTML
     * @param selector
     *            CSS selector
     */
    public final void runTest(final String html, final String htmlExpected,
            final String selector) {
        final Element element; // Parsed HTML

        element = Jsoup.parse(html).body();
        callTestedMethod(element, selector);

        Assert.assertEquals(htmlExpected, element.html());
    }

    /**
     * Calls the method being tested.
     * <p>
     * The received element was created from the initial test HTML.
     * 
     * @param element
     *            element with the HTML being tested
     * @param selector
     *            CSS selector
     */
    protected abstract void callTestedMethod(final Element element,
            final String selector);

}
