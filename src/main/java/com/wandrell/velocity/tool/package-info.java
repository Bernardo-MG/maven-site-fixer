/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2014 the original author or authors.
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
 * It also offers a few helpful tools, meant to improve the look and structure
 * of Maven Sites.
 * <p>
 * All these classes are prepared to be run through Velocity, and can be called
 * directly from {@code .vm} files. All of them have default keys assigned,
 * which are repeated on the {@code tools.xml} file on the resources folder,
 * which can be used for this.
 * <p>
 * For example, to call the {@code HTMLUtils} {@code split} method the following
 * VTL line can be used:
 * <p>
 * {@code #set ( $sections = $htmlTool.split( $bodyContent, "hr" ) )}
 * <p>
 * They also share a series of features, first of all the fact that all editing
 * and querying of HTML is done through the
 * <a href="http://jsoup.org/">jsoup</a> library.
 * <p>
 * Also there are some common limitations. First of all the validity of the
 * received HTML won't be checked, that falls on the user's hands. Second, the
 * HTML code received by the methods will be stripped, and only the contents of
 * the {@code <body>} tag will be kept, unless that tag is missing, in which
 * case the full HTML code will be used.
 */
package com.wandrell.velocity.tool;
