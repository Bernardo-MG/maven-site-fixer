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
     * Tests that a file with consecutive points gives a slugged file id.
     */
    @Test
    public final void testGetFileId_ConsecutivePoints_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("path-to\\file_name..something.html");

        Assert.assertEquals(util.getFileId(), "path-to-file-name-something");
    }

    /**
     * Tests that an empty file gives an empty file id.
     */
    @Test
    public final void testGetFileId_EmptyFile_EmptyId() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("");

        Assert.assertEquals(util.getFileId(), "");
    }

    /**
     * Tests that a file with multiple points gives a slugged file id.
     */
    @Test
    public final void testGetFileId_MultiplePoints_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("path-to\\file_name.something.html");

        Assert.assertEquals(util.getFileId(), "path-to-file-name-something");
    }

    /**
     * Tests that a file with multiple line separators gives a slugged file id.
     */
    @Test
    public final void testGetFileId_MultipleSeparators_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("path-to\\file_name---something.html");

        Assert.assertEquals(util.getFileId(), "path-to-file-name-something");
    }

    /**
     * Tests that a file without extension gives a slugged file id.
     */
    @Test
    public final void testGetFileId_NoExtension_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("path-to\\file_name");

        Assert.assertEquals(util.getFileId(), "path-to-file-name");
    }

    /**
     * Tests that a null file gives an empty file id.
     */
    @Test
    public final void testGetFileId_NullFile_EmptyId() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils(null);

        Assert.assertEquals(util.getFileId(), "");
    }

    /**
     * Tests that a file with only an extension gives an empty id.
     */
    @Test
    public final void testGetFileId_OnlyExtension_Empty() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils(".html");

        Assert.assertEquals(util.getFileId(), "");
    }

    /**
     * Tests that a valid file gives a slugged file id.
     */
    @Test
    public final void testGetFileId_ValidFile_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("path-to\\file_name.html");

        Assert.assertEquals(util.getFileId(), "path-to-file-name");
    }

    /**
     * Returns the utilities class being tested, set up for the tests.
     * 
     * @param fileName
     *            name of the current file
     * @return the utilities class to test
     */
    private final SkinConfigUtils getSkinConfigUtils(final String fileName) {
        final SkinConfigUtils util; // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context; // Velocity context

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY, fileName);

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        return util;
    }

}
