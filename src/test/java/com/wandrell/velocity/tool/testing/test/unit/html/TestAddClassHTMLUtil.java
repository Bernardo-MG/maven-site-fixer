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
import java.util.LinkedList;

import org.testng.annotations.Test;

import com.wandrell.velocity.tool.HTMLUtil;

import junit.framework.Assert;

/**
 * Unit tests for {@link HTMLUtil}.
 * <p>
 * Checks the following cases:
 * <ol>
 * <li>When adding multiple classes with multiple selectors, when the selected
 * elements have no classes, these are added correctly.</li>
 * <li>When adding a single class with multiple selectors, when the selected
 * elements have no classes, these are added correctly.</li>
 * <li>When adding multiple classes with multiple selectors, when the selected
 * elements have classes assigned, these are added correctly.</li>
 * <li>When adding a single class with multiple selectors, when the selected
 * elements have classes assigned, these are added correctly.</li>
 * <li>When adding multiple classes with a single selector, when the selected
 * elements have no classes, these are added correctly.</li>
 * <li>When adding a single class with a single selector, when the selected
 * elements have no classes, these are added correctly.</li>
 * <li>When adding multiple classes with a single selector, when the selected
 * elements have classes assigned, these are added correctly.</li>
 * <li>When adding a single class with a single selector, when the selected
 * elements have classes assigned, these are added correctly.</li>
 * </ol>
 * 
 * @author Bernardo Martínez Garrido
 * @see HTMLUtil
 */
public final class TestAddClassHTMLUtil {

    /**
     * Instance of the utils class being tested.
     */
    private final HTMLUtil util = new HTMLUtil();

    /**
     * Default constructor.
     */
    public TestAddClassHTMLUtil() {
        super();
    }

    /**
     * Tests that when adding multiple classes with multiple selectors, when the
     * selected elements have classes assigned, these are added correctly.
     */
    @Test
    public final void testAddClass_MultipleSelection_BaseClass_Collection() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result
        final Collection<String> classes; // Added classes

        html = "<body><h1 class=\"class_3\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p></body>";

        classes = new LinkedList<String>();
        classes.add("class_1");
        classes.add("class_2");

        result = util.addClass(html, "h1, h2", classes);

        htmlExpected = "<h1 class=\"class_3 class_1 class_2\">A heading</h1>\n<p>Some text</p>\n<h2 class=\"class_1 class_2\">Subheading</h2>\n<p>More text</p>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when adding a single class with multiple selectors, when the
     * selected elements have classes assigned, these are added correctly.
     */
    @Test
    public final void testAddClass_MultipleSelection_BaseClass_Single() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<body><h1 class=\"class_3\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p></body>";

        result = util.addClass(html, "h1, h2", "class_1");

        htmlExpected = "<h1 class=\"class_3 class_1\">A heading</h1>\n<p>Some text</p>\n<h2 class=\"class_1\">Subheading</h2>\n<p>More text</p>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when adding multiple classes with multiple selectors, when the
     * selected elements have no classes, these are added correctly.
     */
    @Test
    public final void testAddClass_MultipleSelection_NoBaseClass_Collection() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result
        final Collection<String> classes; // Added classes

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p></body>";

        classes = new LinkedList<String>();
        classes.add("class_1");
        classes.add("class_2");

        result = util.addClass(html, "h1, h2", classes);

        htmlExpected = "<h1 class=\"class_1 class_2\">A heading</h1>\n<p>Some text</p>\n<h2 class=\"class_1 class_2\">Subheading</h2>\n<p>More text</p>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when adding a single class with multiple selectors, when the
     * selected elements have no classes, these are added correctly.
     */
    @Test
    public final void testAddClass_MultipleSelection_NoBaseClass_Single() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p></body>";

        result = util.addClass(html, "h1, h2", "class_1");

        htmlExpected = "<h1 class=\"class_1\">A heading</h1>\n<p>Some text</p>\n<h2 class=\"class_1\">Subheading</h2>\n<p>More text</p>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when adding multiple classes with a single selector, when the
     * selected elements have classes assigned, these are added correctly.
     */
    @Test
    public final void testAddClass_SingleSelection_BaseClass_Collection() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result
        final Collection<String> classes; // Added classes

        html = "<body><h1 class=\"class_3\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p></body>";

        classes = new LinkedList<String>();
        classes.add("class_1");
        classes.add("class_2");

        result = util.addClass(html, "h1", classes);

        htmlExpected = "<h1 class=\"class_3 class_1 class_2\">A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when adding a single class with a single selector, when the
     * selected elements have classes assigned, these are added correctly.
     */
    @Test
    public final void testAddClass_SingleSelection_BaseClass_Single() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<body><h1 class=\"class_3\">A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p></body>";

        result = util.addClass(html, "h1", "class_1");

        htmlExpected = "<h1 class=\"class_3 class_1\">A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when adding multiple classes with a single selector, when the
     * selected elements have no classes, these are added correctly.
     */
    @Test
    public final void testAddClass_SingleSelection_NoBaseClass_Collection() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result
        final Collection<String> classes; // Added classes

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p></body>";

        classes = new LinkedList<String>();
        classes.add("class_1");
        classes.add("class_2");

        result = util.addClass(html, "h1", classes);

        htmlExpected = "<h1 class=\"class_1 class_2\">A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>";

        Assert.assertEquals(htmlExpected, result);
    }

    /**
     * Tests that when adding a single class with a single selector, when the
     * selected elements have no classes, these are added correctly.
     */
    @Test
    public final void testAddClass_SingleSelection_NoBaseClass_Single() {
        final String html;         // HTML code where the classes are added
        final String htmlExpected; // Expected result
        final String result;       // Actual result

        html = "<body><h1>A heading</h1><p>Some text</p><h2>Subheading</h2><p>More text</p></body>";

        result = util.addClass(html, "h1", "class_1");

        htmlExpected = "<h1 class=\"class_1\">A heading</h1>\n<p>Some text</p>\n<h2>Subheading</h2>\n<p>More text</p>";

        Assert.assertEquals(htmlExpected, result);
    }

}
