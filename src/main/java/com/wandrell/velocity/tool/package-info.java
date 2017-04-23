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
 * Using the tools just requires calling its key. For example, to call the
 * {@link com.wandrell.velocity.tool.HtmlTool HtmlTool} {@code unwrap} method
 * the following VTL line can be used:
 * <p>
 * {@code #set ( $sections = $htmlTool.unwrap( $bodyContent, "a:not([href])" ) )}
 * <p>
 * HTML is edited with the use of <a href="http://jsoup.org/">jsoup</a>, and the
 * methods expect a jsoup element to work with, but it is not validated in any
 * way. Validation of the end result is the programmer's responsibility.
 */

package com.wandrell.velocity.tool;
