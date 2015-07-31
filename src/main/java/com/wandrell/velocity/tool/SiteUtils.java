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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.tools.config.DefaultKey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
 * Utilities class for adapting the site to the patterns used on Maven Skins.
 * <p>
 * These skins are meant to be used with Bootstrap 3.
 * <p>
 * This is used for customizing the HTML code, transforming the default Maven
 * Site into something that fits into patterns offered by a Maven Skin.
 * 
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("html5UpdateTool")
public class SiteUtils {

    /**
     * Constructs an instance of the {@code SiteUtil}.
     */
    public SiteUtils() {
        super();
    }

    public final String transformIcons(final String html) {
        final Map<String, String> replacements;

        replacements = new LinkedHashMap<>();
        replacements.put("img[src$=images/add.gif]",
                "<span class=\"fa fa-plus\" aria-hidden=\"true\"></span><span class=\"sr-only\">Addition</span>");
        replacements.put("img[src$=images/remove.gif]",
                "<span class=\"fa fa-minus\" aria-hidden=\"true\"></span><span class=\"sr-only\">Remove</span>");
        replacements.put("img[src$=images/fix.gif]",
                "<span class=\"fa fa-wrench\" aria-hidden=\"true\"></span><span class=\"sr-only\">Fix</span>");
        replacements.put("img[src$=images/update.gif]",
                "<span class=\"fa fa-refresh\" aria-hidden=\"true\"></span><span class=\"sr-only\">Refresh</span>");
        replacements.put("img[src$=images/icon_help_sml.gif]",
                "<span class=\"fa fa-question\" aria-hidden=\"true\"></span><span class=\"sr-only\">Question</span>");
        replacements.put("img[src$=images/icon_success_sml.gif]",
                "<span class=\"navbar-icon fa fa-check\" aria-hidden=\"true\" title=\"Passed\" aria-label=\"Passed\"></span><span class=\"sr-only\">Passed</span>");
        replacements.put("img[src$=images/icon_warning_sml.gif]",
                "<span class=\"fa fa-exclamation\" aria-hidden=\"true\"></span><span class=\"sr-only\">Warning</span>");
        replacements.put("img[src$=images/icon_error_sml.gif]",
                "<span class=\"navbar-icon fa fa-close\" aria-hidden=\"true\" title=\"Failed\" aria-label=\"Failed\"></span><span class=\"sr-only\">Failed</span>");
        replacements.put("img[src$=images/icon_info_sml.gif]",
                "<span class=\"fa fa-info\" aria-hidden=\"true\"></span><span class=\"sr-only\">Info</span>");

        return replaceAll(html, replacements);
    }

    /**
     * Transforms images on the body to figures.
     * <p>
     * This will wrap {@code <img>} elements with a {@code <figure>} element,
     * and add a {@code <figcaption>} with the contents of the image's
     * {@code alt} attribute, if it has said attribute.
     * <p>
     * Only {@code <img>} elements inside a {@code <section>} will be
     * transformed.
     * 
     * @param html
     *            HTML content to transform
     * @return HTML content, with the body image wrapped in figures.
     */
    public final String transformImagesToFigures(final String html) {
        final Collection<Element> images; // Image elements from the <body>
        final Element body;     // Body of the HTML code
        Element figure;         // <figure> element
        Element caption;        // <figcaption> element

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parseBodyFragment(html).body();

        images = body.select("section > img");
        if (!images.isEmpty()) {
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
        }

        return body.html();
    }

    public final String transformTables(final String html) {
        final Collection<Element> tables; // Tables to fix
        final Element body;     // Body of the HTML code

        body = Jsoup.parseBodyFragment(html).body();

        // Table rows with <th> tags in a <tbody>
        tables = body.select("table");
        if (!tables.isEmpty()) {
            for (final Element table : tables) {
                table.addClass("bodyTable");
                table.addClass("table-striped");
                table.addClass("table-bordered");
            }
        }

        return body.html();
    }

    /**
     * Replaces a collection of HTML elements.
     * <p>
     * These elements are received as a {@code Map}, made up of pairs where the
     * key is a CSS selector, and the value is the replacement for the selected
     * element.
     * 
     * @param html
     *            HTML content to modify
     * @param replacements
     *            {@code Map} where the key is a CSS selector and the value the
     *            element's replacement
     * @return HTML content with replaced elements. If no elements are found,
     *         the original content is returned.
     */
    private final String replaceAll(final String html,
            final Map<String, String> replacements) {
        final Element body;  // Element parsed from the content
        String selector;     // Iterated selector
        String replacement;  // Iterated HTML replacement
        Element replacementElem; // Iterated replacement
        Collection<Element> elements; // Selected elements

        checkNotNull(html, "Received a null pointer as html");
        checkNotNull(replacements, "Received a null pointer as replacements");

        body = Jsoup.parseBodyFragment(html).body();

        for (final Entry<String, String> replacementEntry : replacements
                .entrySet()) {
            selector = replacementEntry.getKey();
            replacement = replacementEntry.getValue();

            elements = body.select(selector);
            if (!elements.isEmpty()) {
                // Take the first child
                replacementElem = Jsoup.parseBodyFragment(replacement).body()
                        .child(0);

                if (replacementElem != null) {
                    for (final Element element : elements) {
                        element.replaceWith(replacementElem.clone());
                    }
                }
            }
        }

        return body.html();
    }

}
