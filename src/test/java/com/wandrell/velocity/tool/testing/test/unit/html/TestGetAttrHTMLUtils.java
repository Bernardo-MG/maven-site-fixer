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

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtils;

/**
 * Unit tests for {@link HTMLUtils}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When trying to acquire an attribute, if there are multiple options with
 * valid data these are all returned correctly.</li>
 * <li>When trying to acquire an attribute, if there are multiple options but
 * not all contain the attribute the ones without the attribute return empty
 * values.</li>
 * <li>When trying to acquire an attribute, if there are multiple options but
 * none contains the attribute only empty values are returned.</li>
 * <li>When trying to acquire an attribute, if there are no options no value is
 * returned.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtils
 */
public final class TestGetAttrHTMLUtils {

    /**
     * Instance of the utils class being tested.
     */
    private final HTMLUtils util = new HTMLUtils();

    /**
     * Default constructor.
     */
    public TestGetAttrHTMLUtils() {
        super();
    }

    /**
     * Tests that when trying to acquire an attribute, if there are multiple
     * options with valid data these are all returned correctly.
     */
    @Test
    public final void testGetAttr_MultipleResult() {
        final String html;              // HTML code where the classes are added
        final Collection<String> result;// Acquired attributes
        final Iterator<String> itr;     // Iterator for the result

        html = "<body><h1 attr1=\"value1\" attr2=\"value2 value3 value4\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1 attr2=\"value2\">Another heading</h1><p>Even more text</p></body>";

        result = util.getAttr(html, "h1", "attr2");

        Assert.assertEquals(result.size(), 2);

        itr = result.iterator();

        Assert.assertEquals(itr.next(), "value2 value3 value4");
        Assert.assertEquals(itr.next(), "value2");
    }

    /**
     * Tests that when trying to acquire an attribute, if there are multiple
     * options but none contains the attribute only empty values are returned.
     */
    @Test
    public final void testGetAttr_NoResult_NoAttribute() {
        final String html;              // HTML code where the classes are added
        final Collection<String> result;// Acquired attributes
        final Iterator<String> itr;     // Iterator for the result

        html = "<body><h1 attr1=\"value1\" attr2=\"value2\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";

        result = util.getAttr(html, "h1", "attr3");

        Assert.assertEquals(result.size(), 2);

        itr = result.iterator();

        Assert.assertEquals(itr.next(), "");
        Assert.assertEquals(itr.next(), "");
    }

    /**
     * Tests that when trying to acquire an attribute, if there are no options
     * no value is returned.
     */
    @Test
    public final void testGetAttr_NoResult_NoSelected() {
        final String html;              // HTML code where the classes are added
        final Collection<String> result;// Acquired attributes

        html = "<body><h1 attr1=\"value1\" attr2=\"value2\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";

        result = util.getAttr(html, "h3", "attr2");

        Assert.assertEquals(result.size(), 0);
    }

    /**
     * Tests that when trying to acquire an attribute, if there are multiple
     * options but not all contain the attribute the ones without the attribute
     * return empty values.
     */
    @Test
    public final void testGetAttr_SingleResult() {
        final String html;              // HTML code where the classes are added
        final Collection<String> result;// Acquired attributes
        final Iterator<String> itr;     // Iterator for the result

        html = "<body><h1 attr1=\"value1\" attr2=\"value2\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p><h1>Another heading</h1><p>Even more text</p></body>";

        result = util.getAttr(html, "h1", "attr2");

        Assert.assertEquals(result.size(), 2);

        itr = result.iterator();

        Assert.assertEquals(itr.next(), "value2");
        Assert.assertEquals(itr.next(), "");
    }

}
