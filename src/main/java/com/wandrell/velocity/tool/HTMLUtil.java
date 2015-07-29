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
package com.wandrell.velocity.tool;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.tools.config.DefaultKey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import com.google.common.collect.Iterables;

/**
 * Utilities class for manipulating HTML, meant for the Velocity template
 * engine.
 * <p>
 * It is configured to be used through the {@code htmlTool} key inside a
 * {@code .vm} file, as for example
 * {@code $htmlTool.split( $bodyContent, "hr" )}
 * <p>
 * It makes use of <a href="http://jsoup.org/">jsoup</a> for querying and
 * editing the HTML.
 * <p>
 * The HTML received is expected to be valid.
 * <p>
 * All the methods will take the HTML out of the {@code <body>} tag before
 * operating with it.
 * <p>
 * So if a method receives:
 * 
 * <pre>
 * {@code <html>
 *   <head></head>
 *   <body>
 *      <h1 class="class_1 class_2">A heading</h1>
 *      <p>Some text</p>
 *      <h2>Subheading</h2>
 *      <p>More text</p>
 *   </body>
 *</html>}
 * </pre>
 * <p>
 * It will return a value created from:
 * 
 * <pre>
 * {@code <h1 class="class_1 class_2">A heading</h1>
 *<p>Some text</p>
 *<h2>Subheading</h2>
 *<p>More text</p>}
 * </pre>
 * <p>
 * It is also valid using just the second HTML code.
 * 
 * @author Andrius Velykis
 * @author Bernardo Martínez Garrido
 * @see <a href="http://jsoup.org/">jsoup</a>
 */
@DefaultKey("htmlTool")
public final class HTMLUtil {

    /**
     * Returns the result from recursively splitting an {@code Element} based in
     * the specified separators.
     * <p>
     * The recursion will be used for hunting down the separators. If a child
     * {@code Element} is one of the separators, the root will be split there
     * and then the next of the roots children will be checked. Otherwise the
     * child's children will be checked in search of the separators.
     * <p>
     * Note that the way the separator is handled is marked by the
     * {@code separatorStrategy} argument. If it is {@code AFTER}, then the
     * separator will be added to the partition after the separator, if it is
     * {@code BEFORE} it will be added to the partition before the separator,
     * and if it is {@code NONE} it won't be added to the partitions.
     * 
     * @param root
     *            {@code Element} to split
     * @param separators
     *            separators used for the splitting
     * @param separatorStrategy
     *            indicates the position where the split element will be added
     * @return the root divided into several partitions
     */
    private static final Collection<Collection<Element>> split(
            final Element root, final Collection<Element> separators,
            final Position separatorStrategy) {
        final Collection<Collection<Element>> partitions; // Root partitions
        Collection<Collection<Element>> childPartitions;  // Child partitions
        Collection<Element> firstPartition;     // First partition of a child
        Collection<Element> newPartition;       // Newly created partition
        Collection<Element> grandchildren;      // All of a child children

        partitions = new LinkedList<Collection<Element>>();
        partitions.add(new LinkedList<Element>());

        for (final Element child : root.children()) {

            if (separators.contains(child)) {
                // The child is a separator
                // This point will be used for a split, and this recursion
                // branch ends

                if (separatorStrategy == Position.BEFORE) {
                    // Add child to the last partition
                    Iterables.getLast(partitions).add(child);
                }

                // Add an empty new partition
                newPartition = new LinkedList<Element>();
                partitions.add(newPartition);

                if (separatorStrategy == Position.AFTER) {
                    // Add child to the new partition
                    newPartition.add(child);
                }

            } else {
                // The child is not a separator
                // The child's children will be checked in search of a
                // separator, starting the recursion

                childPartitions = split(child, separators, separatorStrategy);

                // Add child to the last partition
                Iterables.getLast(partitions).add(child);

                if (childPartitions.size() > 1) {
                    // The child has been split into several partitions

                    grandchildren = child.children();
                    firstPartition = Iterables.getFirst(childPartitions,
                            new LinkedList<Element>());

                    // Removes all the elements which are not in the first
                    // partition
                    grandchildren.removeAll(firstPartition);
                    for (final Element removeChild : grandchildren) {
                        removeChild.remove();
                    }

                    // Add the remaining partitions
                    for (final Collection<Element> nextPartition : new LinkedList<Collection<Element>>(
                            childPartitions).subList(1,
                                    childPartitions.size())) {
                        partitions.add(nextPartition);
                    }
                }
            }
        }

        return partitions;
    }

    /**
     * Transforms a collection of {@code Element} entities to HTML.
     * 
     * @param elements
     *            the elements from which the HTML code will be created
     * @return HTML code created from the elements
     */
    private static final String toHtml(final Collection<Element> elements) {
        final Element root;     // Root for aggregating the elements
        final String html;      // Returned HTML

        switch (elements.size()) {
            case 0:
                html = "";
                break;
            case 1:
                html = elements.iterator().next().outerHtml();
                break;
            default:
                // The elements are aggregated into a <div> which will be
                // removed afterwards
                root = new Element(Tag.valueOf("div"), "");
                for (final Element elem : elements) {
                    root.appendChild(elem);
                }

                // Returns the HTML code inside the div
                html = root.html();
                break;
        }

        return html;
    }

    /**
     * Constructs an instance of the {@code HtmlTool}.
     */
    public HTMLUtil() {
        super();
    }

    /**
     * Modifies HTML code, adding the specified classes to the selected
     * elements.
     * <p>
     * The selector is a CSS selector code, such as {@code table.bodyTable},
     * which identifies the elements to edit. These will be modified, adding all
     * the classes on classNames to their classes.
     * <p>
     * The modified HTML code will be returned by the method, which won't change
     * the received content.
     * 
     * @param content
     *            HTML content to modify
     * @param selector
     *            CSS selector for elements to add classes to
     * @param classNames
     *            Names of classes to add to the selected elements
     * @return HTML content with the modified elements. If no elements are
     *         found, the original content is returned.
     */
    public final String addClass(final String content, final String selector,
            final Collection<String> classNames) {
        final Collection<Element> elements; // All elements selected
        final Element body;     // Element parsed from the content
        final String html;      // Modified HTML code

        checkNotNull(content, "Received a null pointer as content");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(classNames, "Received a null pointer as class names");

        body = getBodyContents(content);
        elements = body.select(selector);

        if (elements.isEmpty()) {
            // Nothing to update
            html = content;
        } else {
            for (final Element element : elements) {
                for (final String className : classNames) {
                    element.addClass(className);
                }
            }

            html = body.html();
        }

        return html;
    }

    /**
     * Modifies HTML code, adding the specified class to the selected elements.
     * <p>
     * The selector is a CSS selector code, such as {@code table.bodyTable},
     * which identifies the elements to edit. These will be modified, adding the
     * class on className to their classes.
     * <p>
     * The modified HTML code will be returned by the method, which won't change
     * the received content.
     * 
     * @param content
     *            HTML content to modify
     * @param selector
     *            CSS selector for elements to add the class to
     * @param className
     *            Name of class to add to the selected elements
     * @return HTML content with the modified elements. If no elements are
     *         found, the original content is returned.
     */
    public final String addClass(final String content, final String selector,
            final String className) {
        return addClass(content, selector,
                Collections.singletonList(className));
    }

    /**
     * Corrects source divisions by removing redundant apparitions of it.
     * <p>
     * It is a problem with Maven sites that code divisions get wrapped by
     * another code division, so they appear like this:
     * 
     * <pre>
     * {@code <div class="source">
     *    <div class="source"> 
     *       <pre>Some code</pre>
     *    </div>
     * </div>}
     * </pre>
     * This method fixes that, by removing the outer division.
     * 
     * @param content
     * @return
     */
    public final String fixRepeatedSourceDiv(final String content) {
        final Collection<Element> codeDivs; // Repeated code divs
        final Element body;     // Element parsed from the content
        final String html;      // Fixed html
        Element validDiv;     // Fixed code block

        body = getBodyContents(content);

        codeDivs = body.select(".source:has(.source)");

        if (codeDivs.isEmpty()) {
            html = content;
        } else {
            for (final Element div : codeDivs) {
                if (!div.children().isEmpty()) {
                    validDiv = div.child(0);

                    validDiv.remove();

                    div.replaceWith(validDiv);
                }
            }

            html = body.html();
        }

        return html;
    }
    public final String fixCodeBlock(final String content) {
        final Collection<Element> codeDivs; // Code divisions
        final Element body;     // Element parsed from the content
        String html;            // Fixed html
        Element pre;            // <pre> element
        Element code;           // <code> element

        body = getBodyContents(content);

        codeDivs = body.select(".source:has(pre)");

        if (codeDivs.isEmpty()) {
            html = content;
        } else {
            for (final Element div : codeDivs) {
                if (!div.children().isEmpty()) {
                    pre = div.child(0);

                    div.append(pre.text());
                    System.out.println(pre);
                    pre.text("");

                    div.replaceWith(pre);
                    
                    pre.appendChild(div);
                    
                    code = new Element(Tag.valueOf("code"), "");
                    code.text(div.text());
                    div.replaceWith(code);
                }
            }

            html = body.html();
        }

        return html;
    }

    /**
     * Corrects table headers by adding a {@code <thead>} section where missing.
     * <p>
     * This method will search for {@code 
     * 
    <th>} within a {@code <tbody>} tag, but not wrapped by a {@code <thead>}.
     * <p>
     * This means it will search for headers such as:
     * 
     * <pre>
     * {@code <table>
     *    <tbody>
     *       <tr>
     *          <th>Header 1</th>
     *          <th>Header 2</th>
     *       </tr>
     *       <tr>
     *          <td>Data 1.1</td>
     *          <td>Data 1.2</td>
     *       </tr>
     *       <tr>
     *          <td>Data 2.1</td>
     *          <td>Data 2.2</td>
     *       </tr>
     *    </tbody>
     * </table>}
     * </pre>
     * <p>
     * And transforms it into:
     * 
     * <pre>
     * {@code <table>
     *    <thead>
     *       <tr>
     *          <th>Header 1</th>
     *          <th>Header 2</th>
     *       </tr>
     *    </thead>
     *    <tbody>
     *       <tr>
     *          <th>Header 1</th>
     *          <th>Header 2</th>
     *       </tr>
     *       <tr>
     *          <td>Data 1.1</td>
     *          <td>Data 1.2</td>
     *       </tr>
     *       <tr>
     *          <td>Data 2.1</td>
     *          <td>Data 2.2</td>
     *       </tr>
     *    </tbody>
     * </table>}
     * </pre>
     * 
     * This serves to fix an error with tables created by some Maven report
     * plugins.
     * 
     * @param content
     *            HTML content to modify
     * @return HTML content with all the table heads fixed. If all the heads
     *         were correct, the original content is returned.
     */
    public final String fixTableHeads(final String content) {
        final Collection<Element> tableHeadRows; // Heads to fix
        final Element body;     // Element parsed from the content
        final String html;      // Fixed html
        Element table;  // HTML table
        Element thead;  // Table's head for wrapping

        checkNotNull(content, "Received a null pointer as content");

        body = getBodyContents(content);

        // Selects rows with <th> tags within a <tr> in a <tbody>
        tableHeadRows = body.select("table > tbody > tr:has(th)");
        if (tableHeadRows.isEmpty()) {
            // No table heads to fix
            html = content;
        } else {
            for (final Element row : tableHeadRows) {
                // Gets the row's table
                table = row.parent().parent();

                // Removes the row from its original position
                row.remove();

                // Creates a table header element with the row
                thead = new Element(Tag.valueOf("thead"), "");
                thead.appendChild(row);
                // Adds the head at the beginning of the table
                table.prependChild(thead);
            }

            html = body.html();
        }

        return html;
    }

    /**
     * Returns the values for an attribute on a selected element.
     * <p>
     * The element is selected by using a CSS selector.
     * 
     * @param content
     *            HTML where the element will be searched
     * @param selector
     *            CSS selector for the elements to query
     * @param attributeKey
     *            Attribute name
     * @return Attribute values for all the matching elements. If no elements
     *         are found, an empty list is returned.
     */
    public final Collection<String> getAttr(final String content,
            final String selector, final String attributeKey) {
        final Collection<Element> elements;  // Selected elements
        final Collection<String> attrs;      // Queried attributes
        final Element body;     // Element parsed from the content

        checkNotNull(content, "Received a null pointer as content");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(attributeKey, "Received a null pointer as attribute key");

        body = getBodyContents(content);
        elements = body.select(selector);

        attrs = new ArrayList<String>();
        for (final Element element : elements) {
            attrs.add(element.attr(attributeKey));
        }

        return attrs;
    }

    /**
     * Removes selected elements from the HTML content.
     * <p>
     * The elements are selected through the use of a CSS selector.
     * 
     * @param content
     *            HTML content to modify
     * @param selector
     *            CSS selector for elements to remove
     * @return HTML content without the removed elements. If no elements are
     *         found, the original content is returned.
     */
    public final String remove(final String content, final String selector) {
        final Collection<Element> elements; // Elements to remove
        final Element body;     // Element parsed from the content
        final String html;      // Resulting HTML

        checkNotNull(content, "Received a null pointer as content");
        checkNotNull(selector, "Received a null pointer as selector");

        body = getBodyContents(content);
        elements = body.select(selector);

        if (elements.isEmpty()) {
            // Nothing changed
            html = content;
        } else {
            for (final Element element : elements) {
                element.remove();
            }

            html = body.html();
        }

        return html;
    }

    /**
     * Replaces a collection of HTML elements.
     * <p>
     * These elements are received as a {@code Map}, made up of pairs where the
     * key is a CSS selector, and the value is the replacement for the selected
     * element.
     * 
     * @param content
     *            HTML content to modify
     * @param replacements
     *            {@code Map} where the key is a CSS selector and the value the
     *            element's replacement
     * @return HTML content with replaced elements. If no elements are found,
     *         the original content is returned.
     */
    public final String replaceAll(final String content,
            final Map<String, String> replacements) {
        final Element body; // Element parsed from the content
        final String html;  // Resulting HTML
        Boolean modified;   // Flag indicating if the HTML has been modified
        String selector;    // Iterated selector
        String replacement; // Iterated HTML replacement
        Element replacementElem; // Iterated replacement
        Collection<Element> elements; // Selected elements

        checkNotNull(content, "Received a null pointer as content");
        checkNotNull(replacements, "Received a null pointer as replacements");

        body = getBodyContents(content);

        modified = false;
        for (final Entry<String, String> replacementEntry : replacements
                .entrySet()) {
            selector = replacementEntry.getKey();
            replacement = replacementEntry.getValue();

            elements = body.select(selector);
            if (!elements.isEmpty()) {
                // Take the first child
                replacementElem = getBodyContents(replacement).child(0);

                if (replacementElem != null) {
                    for (final Element element : elements) {
                        element.replaceWith(replacementElem.clone());
                    }

                    modified = true;
                }
            }
        }

        if (modified) {
            html = body.html();
        } else {
            // Nothing changed
            html = content;
        }

        return html;
    }

    /**
     * Sets the attribute on a selected element to the specified value.
     * 
     * @param content
     *            HTML content to set attributes on
     * @param selector
     *            CSS selector for elements to modify
     * @param attributeKey
     *            Attribute name
     * @param value
     *            Attribute value
     * @return HTML content with the modified elements. If no elements are
     *         found, the original content is returned.
     */
    public final String setAttr(final String content, final String selector,
            final String attributeKey, final String value) {
        final Collection<Element> elements; // Selected elements
        final Element body; // Element parsed from the content
        final String html;  // Resulting HTML

        checkNotNull(content, "Received a null pointer as content");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(attributeKey, "Received a null pointer as attribute key");
        checkNotNull(value, "Received a null pointer as value");

        body = getBodyContents(content);
        elements = body.select(selector);

        if (elements.isEmpty()) {
            // Nothing to update
            html = content;
        } else {
            for (final Element element : elements) {
                element.attr(attributeKey, value);
            }

            html = body.html();
        }

        return html;
    }

    /**
     * Returns the received HTML split into partitions, based on the given
     * separator selector. The separators themselves are dropped from the
     * results.
     * <p>
     * If splitting an element breaks it, it will be fixed by taking all the
     * children elements out of if.
     * <p>
     * For example, if the following is split at the {@code 
     * 
    <hr>
     * }:
     * 
     * <pre>
     * {@code <div>
     *    <p>First paragraph</p>
     *    <hr>
     *    <p>Second paragraph</p>
     *</div>}
     * </pre>
     * 
     * Then the result will be two groups, one will be:
     * 
     * <pre>
     * {@code <div>
     *    <p>First paragraph</p>
     *</div>}
     * </pre>
     * 
     * While the other will be:
     * 
     * <pre>
     * {@code <p>Second paragraph</p>}
     * </pre>
     * 
     * @param content
     *            HTML content to split
     * @param selector
     *            CSS selector for the separators.
     * @return the HTML split into section, and without the separators
     */
    public final Collection<String> split(final String content,
            final String selector) {
        return split(content, selector, Position.NONE);
    }

    /**
     * Splits the given HTML content into partitions, each starting with the
     * given separator selector. The separators are kept as the first element of
     * each partition.
     * <p>
     * Note that if the split is successful the first part will be removed, as
     * it will be before the first selector.
     * 
     * @param content
     *            HTML content to split
     * @param selector
     *            CSS selector for separators
     * @return the HTML split into sections, each beginning with a separator
     */
    public final Collection<String> splitOnStart(final String content,
            final String selector) {
        final Collection<String> split;  // HTML after being split
        final Collection<String> result; // The final sections

        checkNotNull(content, "Received a null pointer as content");
        checkNotNull(selector,
                "Received a null pointer as separator CSS selector");

        // Splitting. The result will have to be checked.
        split = split(content, selector, Position.AFTER);

        if (split.size() <= 1) {
            // No result or just one part. Return what we have.
            result = split;
        } else {
            // The first section will have to be removed, as it is before the
            // first section.
            // For example, if the HTML was split based on headings, then the
            // first section will contain the code before the first heading.
            result = new LinkedList<String>(split).subList(1, split.size());
        }

        return result;
    }

    /**
     * Returns the HTML code with the elements marked by the selector wrapped on
     * the received element.
     * <p>
     * The method will find all the elements fitting into the selector, and then
     * wrap them with the wrapper element. The HTML code will then be adapted to
     * this change and returned.
     * 
     * @param content
     *            HTML content to modify
     * @param selector
     *            CSS selector for elements to wrap
     * @param wrapper
     *            HTML to use for wrapping the selected elements
     * @return HTML with the selected elements wrapped with the wrapper element
     */
    public final String wrap(final String content, final String selector,
            final String wrapper) {
        final Collection<Element> elements; // Selected elements
        final Element body; // Element parsed from the content
        final String html;  // Modified HTML

        checkNotNull(content, "Received a null pointer as content");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(wrapper, "Received a null pointer as HTML wrap");

        body = getBodyContents(content);
        elements = body.select(selector);

        if (elements.isEmpty()) {
            // Nothing to update
            html = content;
        } else {
            for (final Element element : elements) {
                element.wrap(wrapper);
            }

            html = body.html();
        }

        return html;
    }

    /**
     * Parses the HTML into a jsoup {@code Element}, which will contain only the
     * data on the {@code <body>} section.
     * 
     * @param html
     *            the html code of which to get the {@code <body>} contents
     * @return the content of the {@code <body>} element of the HTML code
     */
    private final Element getBodyContents(final String html) {
        return Jsoup.parseBodyFragment(html).body();
    }

    /**
     * Returns the result from splitting the received HTML into partitions,
     * based on the received CSS selector.
     * <p>
     * The separator strategy works as in
     * {@link #split(Element, Collection, Position)}.
     * 
     * @param content
     *            HTML content to split
     * @param selector
     *            CSS selector for separators
     * @param separatorStrategy
     *            strategy to drop or keep separators
     * @return the HTML split based on the selectors
     * @see #split(Element, Collection, Position)
     */
    private final Collection<String> split(final String content,
            final String selector, final Position separatorStrategy) {
        final Collection<Collection<Element>> split; // Split HTML before
        // adapting
        final Collection<String> partitions;  // HTML split by the separators
        final Collection<Element> separators; // Found separators
        final Element body; // Element parsed from the content

        body = getBodyContents(content);
        separators = body.select(selector);

        if (separators.isEmpty()) {
            // Nothing to split
            partitions = Collections.singletonList(content);
        } else {
            split = split(body, separators, separatorStrategy);

            partitions = new ArrayList<String>();

            for (final Collection<Element> partition : split) {
                partitions.add(toHtml(partition));
            }
        }

        return partitions;
    }

}
