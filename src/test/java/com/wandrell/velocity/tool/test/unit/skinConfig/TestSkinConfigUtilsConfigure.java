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

package com.wandrell.velocity.tool.test.unit.skinConfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.maven.doxia.site.decoration.DecorationModel;
import org.apache.velocity.tools.ToolContext;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.SkinConfigUtils;

/**
 * Unit tests for {@link SkinConfigUtils}, testing the {@code configure} method
 * to make sure it can support various mostly invalid configurations.
 * <p>
 * These are just smoke tests to verify the class does not break in these cases.
 * 
 * @author Bernardo Martínez Garrido
 * @see SkinConfigUtils
 */
public final class TestSkinConfigUtilsConfigure {

    /**
     * Default constructor.
     */
    public TestSkinConfigUtilsConfigure() {
        super();
    }

    /**
     * Tests that the configuration method supports an empty velocity context.
     */
    @Test
    public final void test_EmptyContext() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);
    }

    /**
     * Tests that the configuration method supports an empty decoration.
     */
    @Test
    public final void test_EmptyDecoration() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context
        final DecorationModel decoration;

        util = new SkinConfigUtils();

        decoration = new DecorationModel();

        context = new ToolContext();
        context.put(SkinConfigUtils.DECORATION_KEY, decoration);

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);
    }

    /**
     * Tests that the configuration method supports an empty map.
     */
    @Test
    public final void test_EmptyMap() {
        final SkinConfigUtils util; // Utilities class to test

        util = new SkinConfigUtils();

        util.configure(new HashMap<>());
    }

}
