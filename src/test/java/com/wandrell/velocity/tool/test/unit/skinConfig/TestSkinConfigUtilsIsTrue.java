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
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.SkinConfigUtils;

/**
 * Unit tests for {@link SkinConfigUtils}, testing the {@code isTrue} method.
 * 
 * @author Bernardo Martínez Garrido
 * @see SkinConfigUtils
 */
public final class TestSkinConfigUtilsIsTrue {

    /**
     * Default constructor.
     */
    public TestSkinConfigUtilsIsTrue() {
        super();
    }

    /**
     * Tests that a false value returns {@code true}.
     */
    @Test
    public final void testIsTrue_False_False() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("key", "false");

        Assert.assertTrue(!util.isTrue("key"));
    }

    /**
     * Tests that a not existing key returns {@code false}.
     */
    @Test
    public final void testIsTrue_NotExisting_False() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("", "");

        Assert.assertTrue(!util.isTrue("ABC"));
    }

    /**
     * Tests that a not null value returns {@code false}.
     */
    @Test
    public final void testIsTrue_Null_False() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("key", null);

        Assert.assertTrue(!util.isTrue("key"));
    }

    /**
     * Tests that a true value returns {@code true}.
     */
    @Test
    public final void testIsTrue_True_True() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("key", "true");

        Assert.assertTrue(util.isTrue("key"));
    }

    /**
     * Returns the utilities class being tested, set up for the tests.
     * 
     * @param key
     *            key for the value set
     * @param value
     *            value for the value set
     * @return the utilities class to test
     */
    private final SkinConfigUtils getSkinConfigUtils(final String key,
            final String value) {
        final SkinConfigUtils util;    // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;     // Velocity context
        final DecorationModel deco;    // Decoration model
        final Xpp3Dom customNode;      // <custom> node
        final Xpp3Dom skinNode;        // <skinConfig> node
        final Xpp3Dom pageNode;        // Current page node
        final Xpp3Dom valueNode;       // Node with the test value
        final String currentFile;      // Current page

        currentFile = "page";

        // Creates test value node
        valueNode = new Xpp3Dom(key);
        valueNode.setValue(value);

        // Creates page node
        pageNode = new Xpp3Dom(currentFile);
        pageNode.addChild(valueNode);

        // Creates skin node
        skinNode = new Xpp3Dom(SkinConfigUtils.SKIN_KEY);

        // Creates custom data node
        customNode = new Xpp3Dom("custom");
        customNode.addChild(skinNode);

        // Creates decoration model
        deco = Mockito.mock(DecorationModel.class);
        Mockito.when(deco.getCustom()).thenReturn(customNode);

        // Creates utilities class
        util = new SkinConfigUtils();

        // Creates context
        context = new ToolContext();
        context.put(SkinConfigUtils.DECORATION_KEY, deco);
        context.put(SkinConfigUtils.CURRENT_FILE_NAME_KEY, currentFile);

        // Prepares configuration
        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        return util;
    }

}
