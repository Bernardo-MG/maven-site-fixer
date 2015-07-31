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

import org.apache.velocity.tools.config.DefaultKey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
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

}
