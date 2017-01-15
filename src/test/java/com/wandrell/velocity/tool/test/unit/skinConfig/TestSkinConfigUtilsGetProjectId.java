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

import org.apache.maven.project.MavenProject;
import org.apache.velocity.tools.ToolContext;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.velocity.tool.SkinConfigUtils;

/**
 * Unit tests for {@link SkinConfigUtils}, testing the {@code getProjectId}
 * method.
 * 
 * @author Bernardo Martínez Garrido
 * @see SkinConfigUtils
 */
public final class TestSkinConfigUtilsGetProjectId {

    /**
     * Default constructor.
     */
    public TestSkinConfigUtilsGetProjectId() {
        super();
    }

    /**
     * Tests that a name beggining with a point gives a slugged project id.
     */
    @Test
    public final void testgetProjectId_BeginsWithPoint_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils(".project");

        Assert.assertEquals(util.getProjectId(), "-project");
    }

    /**
     * Tests that a name with consecutive points gives a slugged project id.
     */
    @Test
    public final void testgetProjectId_ConsecutivePoints_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("project..something.name");

        Assert.assertEquals(util.getProjectId(), "project-something-name");
    }

    /**
     * Tests that an empty name gives an empty project id.
     */
    @Test
    public final void testgetProjectId_EmptyName_EmptyId() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("");

        Assert.assertEquals(util.getProjectId(), "");
    }

    /**
     * Tests that a name with multiple points gives a slugged project id.
     */
    @Test
    public final void testgetProjectId_MultiplePoints_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("project.something.name");

        Assert.assertEquals(util.getProjectId(), "project-something-name");
    }

    /**
     * Tests that a name with multiple line separators gives a slugged project
     * id.
     */
    @Test
    public final void testgetProjectId_MultipleSeparators_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("project---name");

        Assert.assertEquals(util.getProjectId(), "project-name");
    }

    /**
     * Tests that an invalid project data object gives an empty project id.
     */
    @Test
    public final void testgetProjectId_NoMavenProject_EmptyId() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtilsNoMavenProject();

        Assert.assertEquals(util.getProjectId(), "");
    }

    /**
     * Tests that a null name gives an empty project id.
     */
    @Test
    public final void testgetProjectId_NullName_EmptyId() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils(null);

        Assert.assertEquals(util.getProjectId(), "");
    }

    /**
     * Tests that a valid name gives a slugged project id.
     */
    @Test
    public final void testgetProjectId_ValidName_Slugged() {
        final SkinConfigUtils util; // Utilities class to test

        util = getSkinConfigUtils("project-name");

        Assert.assertEquals(util.getProjectId(), "project-name");
    }

    /**
     * Returns the utilities class being tested, set up for the tests.
     * 
     * @param projectName
     *            name of the project
     * @return the utilities class to test
     */
    private final SkinConfigUtils getSkinConfigUtils(final String projectName) {
        final SkinConfigUtils util; // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;  // Velocity context
        final MavenProject project; // Maven project data

        project = Mockito.mock(MavenProject.class);
        Mockito.when(project.getArtifactId()).thenReturn(projectName);

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.MAVEN_PROJECT_KEY, project);

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        return util;
    }

    /**
     * Returns the utilities class where the project data is not stored in a
     * Maven project object.
     * 
     * @return the utilities class to test
     */
    private final SkinConfigUtils getSkinConfigUtilsNoMavenProject() {
        final SkinConfigUtils util; // Utilities class to test
        final Map<Object, Object> map; // Configuration map
        final ToolContext context;  // Velocity context
        final String project;       // Maven project data

        project = "project";

        util = new SkinConfigUtils();

        context = new ToolContext();
        context.put(SkinConfigUtils.MAVEN_PROJECT_KEY, project);

        map = new HashMap<>();
        map.put(SkinConfigUtils.VELOCITY_CONTEXT_KEY, context);

        util.configure(map);

        return util;
    }

}
