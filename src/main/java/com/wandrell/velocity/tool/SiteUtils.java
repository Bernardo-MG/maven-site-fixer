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
 * Utilities class for adapting a Doxia generated site to the patterns used on
 * Maven Skins.
 * <p>
 * Unlike the other utilities classes, this is not meant to be completely
 * generic, but applies some very concrete fixes which are meant to work with
 * concrete technologies, such as for example, transforming the Maven Site icons
 * to Font Awesome.
 * <p>
 * Still a few methods, like {@code fixReport} which will correct headings on
 * Maven Site reports, are more generic, but still expect the site to follow a
 * concrete type of structure.
 * <p>
 * In general, these fit the patterns I follow for my Maven Skins, but may not
 * work as well with other skins.
 * <p>
 * The class makes use of <a href="http://jsoup.org/">jsoup</a> for querying and
 * editing. This library will process the HTML code received by the methods, so
 * only the contents of the {@code <body>} tag (or the full HTML if this tag is
 * missing) will be used.
 * <p>
 * Take into account that while the returned HTML will be correct, the validity
 * of the received HTML won't be checked. That falls fully on the hands of the
 * user.
 * 
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("siteTool")
public class SiteUtils {

    /**
     * Constructs an instance of the {@code SiteUtil}.
     */
    public SiteUtils() {
        super();
    }

    /**
     * Returns the result from fixing links to anchors in the same page on the
     * received HTML code.
     * <p>
     * The href value will be in lower case, and with spaces and points removed.
     * 
     * @param html
     *            HTML with anchors to fix
     * @return HTML content, with the anchor href attribute fixed
     */
    public final String fixAnchorLinks(final String html) {
        final Element body;     // Body of the HTML code
        String ref;             // Value of the href attribute

        body = Jsoup.parse(html).body();

        // Anchors
        for (final Element child : body.getElementsByTag("a")) {
            ref = child.attr("href");

            if ((!ref.isEmpty()) && (ref.substring(0, 1).equals("#"))) {
                child.attr("href", ref.toLowerCase().replaceAll("[ _.]", ""));
            }
        }

        return body.html();
    }

    /**
     * Returns the result from adding or fixing ids to the headings on the
     * received HTML code.
     * <p>
     * The id will be the text from the heading, in lower case, and with spaces
     * and points removed. If it already has an id, then it will be set to lower
     * case, with spaces and points removed.
     * 
     * @param html
     *            HTML with headings where an id should be added
     * @return HTML content, with the tables transformed
     */
    public final String fixHeadingIds(final String html) {
        final Collection<Element> headings; // Headings to fix
        final Element body;     // Body of the HTML code

        body = Jsoup.parse(html).body();

        // Table rows with <th> tags in a <tbody>
        headings = body.select("h1,h2,h3,h4,h5,h6");
        for (final Element heading : headings) {
            if (!heading.hasAttr("id")) {
                heading.attr("id",
                        heading.text().toLowerCase().replaceAll("[ _.]", ""));
            } else {
                heading.attr("id", heading.attr("id").toLowerCase()
                        .replaceAll("[ _.]", ""));
            }
        }

        return body.html();
    }

    /**
     * Returns the result from fixing the received Maven Site report.
     * <p>
     * This is prepared for the following reports:
     * <ul>
     * <li>Changes report</li>
     * <li>Checkstyle</li>
     * <li>CPD</li>
     * <li>Dependencies</li>
     * <li>Failsafe report</li>
     * <li>Findbugs</li>
     * <li>JDepend</li>
     * <li>License</li>
     * <li>Plugins</li>
     * <li>Plugin management</li>
     * <li>Project summary</li>
     * <li>PMD</li>
     * <li>Surefire report</li>
     * <li>Tags list</li>
     * <li>Team list</li>
     * </ul>
     * Most of the times, the fix consists on correcting the heading levels, and
     * adding an initial heading if needed.
     * 
     * @param html
     *            the HTML code from the report
     * @param report
     *            the report name
     * @return the fixed HTML report
     */
    public final String fixReport(final String html, final String report) {
        final Element body; // Body of the HTML code

        body = Jsoup.parse(html).body();

        if ("plugins".equals(report)) {
            fixReportPlugins(body);
        } else if ("plugin-management".equals(report)) {
            fixReportPluginManagement(body);
        } else if ("changes-report".equals(report)) {
            fixReportChanges(body);
        } else if ("surefire-report".equals(report)) {
            fixReportSurefire(body);
        } else if ("failsafe-report".equals(report)) {
            fixReportFailsafe(body);
        } else if ("checkstyle".equals(report)) {
            fixReportCheckstyle(body);
        } else if ("findbugs".equals(report)) {
            fixReportFindbugs(body);
        } else if ("pmd".equals(report)) {
            fixReportPmd(body);
        } else if ("cpd".equals(report)) {
            fixReportCpd(body);
        } else if ("jdepend-report".equals(report)) {
            fixReportJdepend(body);
        } else if ("taglist".equals(report)) {
            fixReportTaglist(body);
        } else if ("dependencies".equals(report)) {
            fixReportDependencies(body);
        } else if ("project-summary".equals(report)) {
            fixReportProjectSummary(body);
        } else if ("license".equals(report)) {
            fixReportLicense(body);
        } else if ("team-list".equals(report)) {
            fixReportTeamList(body);
        }

        return body.html();
    }

    /**
     * Returns the result from transforming the default icons used by the Maven
     * Site to Font Awesome icons on the received HTML code.
     * 
     * @param html
     *            HTML code for the page
     * @return the HTML with all the icons swapped for Font Awesome icons
     */
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
     * Returns the result from transforming simple {@code <img>} elements to
     * {@code <figure>} elements on the received HTML code.
     * <p>
     * This will wrap {@code <img>} elements with a {@code <figure>} element,
     * and add a {@code <figcaption>} with the contents of the image's
     * {@code alt} attribute, if said attribute exists.
     * <p>
     * Only {@code <img>} elements inside a {@code <section>} will be
     * transformed.
     * 
     * @param html
     *            HTML with images to transform
     * @return HTML content, with the body image wrapped in figures.
     */
    public final String transformImagesToFigures(final String html) {
        final Collection<Element> images; // Image elements from the <body>
        final Element body;     // Body of the HTML code
        Element figure;         // <figure> element
        Element caption;        // <figcaption> element

        checkNotNull(html, "Received a null pointer as html");

        body = Jsoup.parse(html).body();

        images = body.select("section img");
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

    /**
     * Returns the result from transforming tables to stripped and bordered
     * tables on the received HTML code.
     * 
     * @param html
     *            HTML with tables to transform
     * @return HTML content, with the tables transformed
     */
    public final String transformTables(final String html) {
        final Collection<Element> tables; // Tables to fix
        final Element body;     // Body of the HTML code

        body = Jsoup.parse(html).body();

        // Table rows with <th> tags in a <tbody>
        tables = body.select("table");
        for (final Element table : tables) {
            table.addClass("table");
            table.addClass("table-striped");
            table.addClass("table-bordered");
        }

        return body.html();
    }

    /**
     * Fixes the changes report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportChanges(final Element body) {
        final Collection<Element> headings;  // Headings in the body
        final Element section;  // First section
        Element timeElement;    // Element with the date
        Element smallElement;   // Element with the small date
        String text;            // Heading text
        String[] texts;         // Split heading text

        // Sets all the h2 to h1
        for (final Element head : body.getElementsByTag("h2")) {
            head.tagName("h1");
        }

        headings = body.getElementsByTag("h3");
        if (!headings.isEmpty()) {
            // Sets first h3 to h2
            headings.iterator().next().tagName("h2");
        }

        // Takes again all the h3 elements, to avoid the new h2
        for (final Element heading : body.getElementsByTag("h3")) {
            // Moves the heading id to the parent
            heading.parent().attr("id", heading.id());
            heading.removeAttr("id");

            // Transforms the date on the heading
            text = heading.text();
            texts = text.split("–", 2);
            if (texts.length == 2) {
                timeElement = new Element(Tag.valueOf("time"), "");
                timeElement.text(texts[1].trim());

                smallElement = new Element(Tag.valueOf("small"), "");
                smallElement.append("(");
                smallElement.appendChild(timeElement);
                smallElement.append(")");

                heading.text(texts[0]);
                heading.appendChild(smallElement);
            }
        }

        // Moves all the elements out of the sections
        section = body.getElementsByTag("section").iterator().next();
        for (final Element child : section.children()) {
            child.remove();
            body.appendChild(child);
        }

        section.remove();
    }

    /**
     * Fixes the Checkstyle report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportCheckstyle(final Element body) {
        body.getElementsByTag("h2").iterator().next().tagName("h1");
        body.select("img[src=\"images/rss.png\"]").remove();
    }

    /**
     * Fixes the CPD report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportCpd(final Element body) {
        body.getElementsByTag("h2").iterator().next().tagName("h1");
    }

    /**
     * Fixes the dependencies report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportDependencies(final Element body) {
        body.prepend("<h1>Dependencies Report</h1>");
    }

    /**
     * Fixes the Failsafe report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportFailsafe(final Element body) {
        final Element heading; // First h2 heading

        heading = body.getElementsByTag("h2").iterator().next();
        heading.tagName("h1");
        heading.text("Failsafe Report");
    }

    /**
     * Fixes the Findbugs report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportFindbugs(final Element body) {
        body.getElementsByTag("h2").iterator().next().tagName("h1");
    }

    /**
     * Fixes the JDepend report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportJdepend(final Element body) {
        body.getElementsByTag("h2").iterator().next().tagName("h1");
    }

    /**
     * Fixes the License report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportLicense(final Element body) {
        body.prepend("<h1>License</h1>");
    }

    /**
     * Fixes the plugin management report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportPluginManagement(final Element body) {
        final Element section;  // Section element

        for (final Element head : body.getElementsByTag("h2")) {
            head.tagName("h1");
        }

        section = body.getElementsByTag("section").iterator().next();

        for (final Element child : section.children()) {
            child.remove();
            body.appendChild(child);
        }

        section.remove();
    }

    /**
     * Fixes the plugins report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportPlugins(final Element body) {
        body.prepend("<h1>Plugins Report</h1>");
    }

    /**
     * Fixes the PMD report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportPmd(final Element body) {
        body.getElementsByTag("h2").iterator().next().tagName("h1");
    }

    /**
     * Fixes the project summary report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportProjectSummary(final Element body) {
        for (final Element head : body.getElementsByTag("h2")) {
            head.tagName("h1");
        }

        for (final Element head : body.getElementsByTag("h3")) {
            head.tagName("h2");
        }
    }

    /**
     * Fixes the Surefire report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportSurefire(final Element body) {
        body.getElementsByTag("h2").iterator().next().tagName("h1");
    }

    /**
     * Fixes the tag list report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportTaglist(final Element body) {
        body.getElementsByTag("h2").iterator().next().tagName("h1");
    }

    /**
     * Fixes the team list report page.
     * 
     * @param body
     *            element for the body of the report page
     */
    private final void fixReportTeamList(final Element body) {
        for (final Element head : body.getElementsByTag("h2")) {
            head.tagName("h1");
        }

        for (final Element head : body.getElementsByTag("h3")) {
            head.tagName("h2");
        }
    }

    /**
     * Returns the result from replacing a collection of HTML elements on the
     * received HTML code.
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
     * @return HTML content with replaced elements
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

        body = Jsoup.parse(html).body();

        for (final Entry<String, String> replacementEntry : replacements
                .entrySet()) {
            selector = replacementEntry.getKey();
            replacement = replacementEntry.getValue();

            elements = body.select(selector);
            if (!elements.isEmpty()) {
                // Take the first child
                replacementElem = Jsoup.parse(replacement).body().child(0);

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
