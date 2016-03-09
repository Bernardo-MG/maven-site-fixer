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

import org.apache.velocity.tools.config.DefaultKey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import com.google.common.collect.Iterables;

/**
 * Utilities class for manipulating HTML, to be used as an extension of the
 * Velocity templating engine.
 * <p>
 * The methods offered help enhancing the look of a Maven Site by manipulating
 * the HTML structure.
 * <p>
 * The class makes use of <a href="http://jsoup.org/">jsoup</a> for querying and
 * editing. This library will process the HTML code received by the methods, so
 * only the contents of the {@code <body>} tag (or the full HTML if this tag is
 * missing) will be used.
 * <p>
 * Take into account that while the returned HTML will be correct, the validity
 * of the received HTML won't be checked. That falls fully on the hands of the
 * user.
 * <p>
 * This class has been created from the HTML Tool class from the
 * <a href="http://andriusvelykis.github.io/reflow-maven-skin/">Reflow Maven
 * Skin</a>.
 * 
 * @author Andrius Velykis
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("htmlTool")
public final class HTMLUtils {

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
     * Returns the result from transforming a collection of {@code Element}
     * entities to HTML.
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
     * Constructs an instance of the {@code HTMLUtils}.
     */
    public HTMLUtils() {
        super();
    }

    /**
     * Returns the received HTML split into partitions, based on the given
     * separator selector. The separators themselves are dropped from the
     * results.
     * <p>
     * If splitting an element breaks it, it will be fixed by taking all the
     * children elements out of if.
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
     * Returns the result from splitting the received HTML code into partitions,
     * each starting with the given separator selector. The separators are kept
     * as the first element of each partition.
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
     * the received wrapper element.
     * <p>
     * The method will find all the elements fitting into the selector, and then
     * wrap them with the wrapper element. The HTML code will then be adapted to
     * this change and returned.
     * 
     * @param html
     *            HTML content to modify
     * @param selector
     *            CSS selector for elements to wrap
     * @param wrapper
     *            HTML to use for wrapping the selected elements
     * @return HTML with the selected elements wrapped with the wrapper element
     */
    public final String wrap(final String html, final String selector,
            final String wrapper) {
        final Collection<Element> elements; // Selected elements
        final Element body;  // Element parsed from the content

        checkNotNull(html, "Received a null pointer as html");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(wrapper, "Received a null pointer as HTML wrap");

        body = Jsoup.parse(html).body();
        elements = body.select(selector);

        if (!elements.isEmpty()) {
            for (final Element element : elements) {
                element.wrap(wrapper);
            }
        }

        return body.html();
    }

    /**
     * Returns the HTML code with the first element marked by the selector
     * wrapped on the received wrapper element.
     * <p>
     * The method will find the first element fitting into the selector, and
     * then wrap it with the wrapper element. The HTML code will then be adapted
     * to this change and returned.
     * 
     * @param html
     *            HTML content to modify
     * @param selector
     *            CSS selector for elements to wrap
     * @param wrapper
     *            HTML to use for wrapping the selected elements
     * @return HTML with the selected elements wrapped with the wrapper element
     */
    public final String wrapFirst(final String html, final String selector,
            final String wrapper) {
        final Collection<Element> elements; // Selected elements
        final Element body;  // Element parsed from the content

        checkNotNull(html, "Received a null pointer as html");
        checkNotNull(selector, "Received a null pointer as selector");
        checkNotNull(wrapper, "Received a null pointer as HTML wrap");

        body = Jsoup.parse(html).body();
        elements = body.select(selector);

        if (!elements.isEmpty()) {
            elements.iterator().next().wrap(wrapper);
        }

        return body.html();
    }

    /**
     * Returns the result from splitting the received HTML into partitions,
     * based on the received CSS selector.
     * <p>
     * The separator strategy works as in
     * {@link #split(Element, Collection, Position)}.
     * 
     * @param html
     *            HTML content to split
     * @param selector
     *            CSS selector for separators
     * @param separatorStrategy
     *            strategy to drop or keep separators
     * @return the HTML split based on the selectors
     * @see #split(Element, Collection, Position)
     */
    private final Collection<String> split(final String html,
            final String selector, final Position separatorStrategy) {
        final Collection<Collection<Element>> split; // Split HTML before
        // adapting
        final Collection<String> partitions;  // HTML split by the separators
        final Collection<Element> separators; // Found separators
        final Element body; // Element parsed from the content

        body = Jsoup.parse(html).body();
        separators = body.select(selector);

        if (separators.isEmpty()) {
            // Nothing to split
            partitions = Collections.singletonList(html);
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
