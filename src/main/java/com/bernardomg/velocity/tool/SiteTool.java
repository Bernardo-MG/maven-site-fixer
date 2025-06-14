/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015-2025 the original author or authors.
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

package com.bernardomg.velocity.tool;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.tools.config.DefaultKey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities class for fixing several issues in Doxia generated sites, updating and homogenising their layouts.
 * <p>
 * This was created for Maven Sites. These are built through Doxia which supports XHTML, and not HTML5, and so this
 * library generates outdated pages. Also some of the reports which can be generated through Maven plugins suffer from
 * similar issues and add another problem, that their layouts may not match at all.
 * <p>
 * The <a href="https://github.com/Bernardo-MG/docs-maven-skin">Docs Maven Skin</a> and its requirements have dictated
 * the development of this class. For more generic methods use the {@link com.bernardomg.velocity.tool.HtmlTool
 * HtmlTool}.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@DefaultKey("siteTool")
public class SiteTool {

    /**
     * Regular expresion indicating invalid values for ids and internal links which will be replaced by hyphens.
     */
    private static final String ID_HYPHEN_REGEX   = "[ _]";

    /**
     * Regular expresion indicating invalid values for ids and internal links will will be removed.
     */
    private static final String ID_REJECTED_REGEX = "[^\\w#-]";

    /**
     * Logger for the class.
     */
    private static final Logger log               = LoggerFactory.getLogger(SiteTool.class);

    /**
     * Constructs an instance of the utilities class.
     */
    public SiteTool() {
        super();
    }

    /**
     * Fixes links to anchors in the same page.
     * <p>
     * Any link such as {@code <a href=\"#An_Internal.Link\">A link</a>} will be transformed into
     * {@code <a href=\"#aninternallink\">A link</a>}.
     * <p>
     * The href value will receive the following modifications, only if it is an internal link:
     * <ul>
     * <li>Text will be set to lower case</li>
     * <li>Underscores will be removed</li>
     * <li>Points will be removed</li>
     * <li>Empty spaces will be removed</li>
     * </ul>
     *
     * @param root
     *            root element with anchors to fix
     * @return transformed element
     */
    public final Element fixAnchorLinks(final Element root) {
        String ref; // Value of the href attribute
        String id;  // Formatted id

        if (root == null) {
            log.warn("Received null root");
        } else {
            // Anchors
            for (final Element anchor : root.getElementsByTag("a")) {
                // If the attribute doesn't exist then the ref will be an empty
                // string
                ref = anchor.attr("href");

                if ((!ref.isEmpty()) && ("#".equals(ref.substring(0, 1)))) {
                    id = formatId(ref);
                    anchor.attr("href", id);
                }
            }
        }

        return root;
    }

    /**
     * Adds or fixes heading ids.
     * <p>
     * If a heading has an id it will be corrected if needed, otherwise it will be created from the heading text.
     * <p>
     * The following operations are applied during this process:
     * <ul>
     * <li>Text will be set to lower case</li>
     * <li>Underscores will be removed</li>
     * <li>Points will be removed</li>
     * <li>Empty spaces will be removed</li>
     * </ul>
     * <p>
     * With this headings will end looking like {@code <h1 id=\"aheading\">A heading</h1>}.
     *
     * @param root
     *            root element with headings where an id should be added
     * @return transformed element
     */
    public final Element fixHeadingIds(final Element root) {
        final Collection<Element> headings; // Headings to fix
        String                    idText;   // Text to generate the id
        String                    id;       // Formatted id

        if (root == null) {
            log.warn("Received null root");
        } else {
            // Table rows with <th> tags in a <tbody>
            headings = root.select("h1,h2,h3,h4,h5,h6");
            for (final Element heading : headings) {
                if (heading.hasAttr("id")) {
                    // Contains an id
                    // The id text is taken from the attribute
                    idText = heading.attr("id");
                } else {
                    // Doesn't contain an id
                    // The id text is taken from the heading text
                    idText = heading.text();
                }
                id = formatId(idText);
                heading.attr("id", id);
            }
        }

        return root;
    }

    /**
     * Formats the received id, transforming it into a valid internal anchor id.
     *
     * @param id
     *            id to transform
     * @return a valid anchor id
     */
    private final String formatId(final String id) {
        return id.trim()
            .replaceAll(ID_HYPHEN_REGEX, "-")
            .replaceAll(ID_REJECTED_REGEX, "");
    }

    /**
     * Returns the result from replacing a collection of HTML elements on the received HTML code.
     * <p>
     * These elements are received as a {@code Map}, made up of pairs where the key is a CSS selector, and the value is
     * the replacement for the selected element.
     *
     * @param root
     *            root element for the content to modify
     * @param replacements
     *            {@code Map} where the key is a CSS selector and the value the element's replacement
     */
    private final void replaceAll(final Element root, final Map<String, String> replacements) {
        String              selector;        // Iterated selector
        String              replacement;     // Iterated HTML replacement
        Element             replacementElem; // Iterated replacement
        Collection<Element> elements;        // Selected elements
        Element             replacementBody; // Body of the replacement

        for (final Entry<String, String> replacementEntry : replacements.entrySet()) {
            selector = replacementEntry.getKey();
            replacement = replacementEntry.getValue();

            elements = root.select(selector);
            if (!elements.isEmpty()) {
                // There are elements to replace

                // Processes the replacement
                replacementBody = Jsoup.parse(replacement)
                    .body();
                if (!replacementBody.children()
                    .isEmpty()) {
                    replacementElem = replacementBody.child(0);

                    // Replaces the elements
                    for (final Element element : elements) {
                        element.replaceWith(replacementElem.clone());
                    }
                }
            }
        }
    }

    /**
     * Transforms the default icons used by the Maven Site to Font Awesome icons.
     *
     * @param root
     *            root element with the page
     * @return transformed element
     */
    public final Element transformIcons(final Element root) {
        final Map<String, String> replacements;

        if (root == null) {
            log.warn("Received null root");
        } else {
            replacements = new HashMap<>();
            replacements.put("img[src$=images/add.gif]",
                "<span><span class=\"fa-solid fa-plus\" aria-hidden=\"true\"></span><span class=\"sr-only\">Addition</span></span>");
            replacements.put("img[src$=images/remove.gif]",
                "<span><span class=\"fa-solid fa-minus\" aria-hidden=\"true\"></span><span class=\"sr-only\">Remove</span></span>");
            replacements.put("img[src$=images/fix.gif]",
                "<span><span class=\"fa-solid fa-wrench\" aria-hidden=\"true\"></span><span class=\"sr-only\">Fix</span></span>");
            replacements.put("img[src$=images/update.gif]",
                "<span><span class=\"fa-solid fa-rotate\" aria-hidden=\"true\"></span><span class=\"sr-only\">Refresh</span></span>");
            replacements.put("img[src$=images/icon_help_sml.gif]",
                "<span><span class=\"fa-solid fa-question\" aria-hidden=\"true\"></span><span class=\"sr-only\">Question</span></span>");
            replacements.put("img[src$=images/icon_success_sml.gif]",
                "<span><span class=\"navbar-icon fa-solid fa-check\" aria-hidden=\"true\" title=\"Passed\" aria-label=\"Passed\"></span><span class=\"sr-only\">Passed</span></span>");
            replacements.put("img[src$=images/icon_warning_sml.gif]",
                "<span><span class=\"fa-solid fa-exclamation\" aria-hidden=\"true\"></span><span class=\"sr-only\">Warning</span>");
            replacements.put("img[src$=images/icon_error_sml.gif]",
                "<span><span class=\"navbar-icon fa-solid fa-xmark\" aria-hidden=\"true\" title=\"Failed\" aria-label=\"Failed\"></span><span class=\"sr-only\">Failed</span></span>");
            replacements.put("img[src$=images/icon_info_sml.gif]",
                "<span><span class=\"fa-solid fa-info\" aria-hidden=\"true\"></span><span class=\"sr-only\">Info</span></span>");

            replaceAll(root, replacements);
        }

        return root;
    }

    /**
     * Transforms simple {@code <img>} elements to {@code <figure>} elements.
     * <p>
     * This will wrap {@code <img>} elements with a {@code <figure>} element, and add a {@code <figcaption>} with the
     * contents of the image's {@code alt} attribute, if said attribute exists.
     *
     * @param root
     *            root element with images to transform
     * @return transformed element
     */
    public final Element transformImagesToFigures(final Element root) {
        final Collection<Element> images;  // Image elements from the <body>
        final Collection<Element> figures; // figure elements from the <body>
        Element                   figure;  // <figure> element
        Element                   caption; // <figcaption> element

        if (root == null) {
            log.warn("Received null root");
        } else {
            images = root.select("img");
            for (final Element img : images) {
                figure = new Element(Tag.valueOf("figure"), "");

                img.replaceWith(figure);
                figure.appendChild(img);

                if (img.hasAttr("alt")) {
                    caption = new Element(Tag.valueOf("figcaption"), "");
                    caption.text(img.attr("alt"));
                    figure.appendChild(caption);
                }
            }

            figures = root.select("figure");
            for (final Element fig : figures) {
                if ("p".equals(fig.parent()
                    .tag()
                    .getName())) {
                    fig.parent()
                        .unwrap();
                }
            }
        }

        return root;
    }

}
