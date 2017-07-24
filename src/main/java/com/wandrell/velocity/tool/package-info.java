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
/**
 * Provides various utilities classes for Apache Velocity, used to update the
 * XHTML produced by Doxia to current HTML5 standards.
 * <p>
 * They also improve the look and structure of Maven Sites.
 * <p>
 * All these classes are prepared to be run through Velocity. To allow calling
 * them from Velocity templates each class is annotated with a default key, and
 * a {@code tools.xml} file is included.
 * <p>
 * Using the tools just requires calling its key. For example, the following
 * line calls the {@code unwrap} method of
 * {@link com.wandrell.velocity.tool.HtmlTool HtmlTool}:
 * <p>
 * {@code #set ( $sections = $htmlTool.unwrap( $bodyContent, "a:not([href])" ) )}
 * <p>
 * The tools make use of <a href="http://jsoup.org/">jsoup</a> to edit the HTML
 * code. For this reason their methods expect a jsoup element. Take note that
 * this element won't be validated in any way. Validation of the end result is
 * the programmer's responsibility.
 */

package com.wandrell.velocity.tool;
