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

package com.wandrell.velocity.tool.test.unit.skinConfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.tools.ToolContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.SkinConfigUtils;

/**
 * Unit tests for {@link SkinConfigUtils}, testing the {@code getFileId} method.
 * 
 * @author Bernardo Martínez Garrido
 * @see SkinConfigUtils
 */
public final class TestSkinConfigUtilsGetFileId {

    /**
     * Default constructor.
     */
    public TestSkinConfigUtilsGetFileId() {
        super();
    }

    /**
     * Tests that an empty file gives an empty file id.
     */
    @Test
    public final void testGetFileId_EmptyFile_EmptyId() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY, "");

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        Assert.assertEquals(util.getFileId(), "");
    }

    /**
     * Tests that a file with multiple straight lines gives a slugged file id.
     */
    @Test
    public final void testGetFileId_MultipleLines_Slugged() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY,
                "path-to\\file_name---something.html");

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        Assert.assertEquals(util.getFileId(), "path-to-file-name-something");
    }

    /**
     * Tests that a file with multiple points gives a slugged file id.
     */
    @Test
    public final void testGetFileId_MultiplePoints_Slugged() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY,
                "path-to\\file_name.something.html");

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        Assert.assertEquals(util.getFileId(), "path-to-file-name-something");
    }

    /**
     * Tests that a file without extension gives a slugged file id.
     */
    @Test
    public final void testGetFileId_NoExtension_Slugged() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY,
                "path-to\\file_name");

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        Assert.assertEquals(util.getFileId(), "path-to-file-name");
    }

    /**
     * Tests that a null file gives an empty file id.
     */
    @Test
    public final void testGetFileId_NullFile_EmptyId() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY, null);

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        Assert.assertEquals(util.getFileId(), "");
    }

    /**
     * Tests that a file with only an extension gives an empty id.
     */
    @Test
    public final void testGetFileId_OnlyExtension_Empty() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY, ".html");

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        Assert.assertEquals(util.getFileId(), "");
    }

    /**
     * Tests that a valid file gives a slugged file id.
     */
    @Test
    public final void testGetFileId_ValidFile_Slugged() {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY,
                "path-to\\file_name.html");

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        Assert.assertEquals(util.getFileId(), "path-to-file-name");
    }

}
