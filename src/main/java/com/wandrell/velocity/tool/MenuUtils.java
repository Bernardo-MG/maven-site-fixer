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

package com.wandrell.velocity.tool;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.maven.doxia.site.decoration.Menu;
import org.apache.maven.doxia.site.decoration.MenuItem;
import org.apache.velocity.tools.config.DefaultKey;

/**
 * Utilities class for generating Doxia menu classes.
 * <p>
 * This was created for Maven Sites, which are built through Doxia. With the use
 * of this class it is possible creating new menus dynamically with ease inside
 * Velocity macros.
 * 
 * @author Bernardo Martínez Garrido
 */
@DefaultKey("menuTool")
public final class MenuUtils {

    /**
     * Constructs an instance of the {@code MenuUtils}.
     */
    public MenuUtils() {
        super();
    }

    /**
     * Adds a link menu item to a menu.
     * <p>
     * This is a menu item with a value set in the href field.
     * 
     * @param menu
     *            the menu where the item will be added
     * @param name
     *            name of the item
     * @param url
     *            url for the link
     * @return the menu with the item added
     */
    public final Menu addLinkItem(final Menu menu, final String name,
            final String url) {
        final MenuItem item; // Menu item to add

        checkNotNull(menu, "Received a null pointer as menu");
        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(url, "Received a null pointer as url");

        item = new MenuItem();
        item.setName(name);
        item.setHref(url);

        menu.addItem(item);

        return menu;
    }

    /**
     * Adds a link menu item to a menu.
     * <p>
     * This is a menu item with a value set in the href field.
     * 
     * @param menu
     *            the menu where the item will be added
     * @param name
     *            name of the item
     * @param url
     *            url for the link
     * @param description
     *            description for the link
     * @return the menu with the item added
     */
    public final Menu addLinkItem(final Menu menu, final String name,
            final String url, final String description) {
        final MenuItem item; // Menu item to add

        checkNotNull(menu, "Received a null pointer as menu");
        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(url, "Received a null pointer as url");
        checkNotNull(description, "Received a null pointer as description");

        item = new MenuItem();
        item.setName(name);
        item.setHref(url);
        item.setDescription(description);

        menu.addItem(item);

        return menu;
    }

    /**
     * Generates a Doxia menu instance with the specified name.
     * 
     * @param name
     *            the name for the menu
     * @return a menu with the specified name
     */
    public final Menu menu(final String name) {
        final Menu menu; // Generated menu

        checkNotNull(name, "Received a null pointer as name");

        menu = new Menu();
        menu.setName(name);

        return menu;
    }

}
