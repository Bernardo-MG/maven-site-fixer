#A fix for Maven Sites

Maven makes use of [Apache Velocity][velocity] to create web pages. Sadly, the result of this is an outdated XHTML page, which also is hard to customize.

This became apparent when creating the [Docs Maven Skin][docs-skin], which required a set of helping tools, initially adapted from the [Reflow Maven Skin][reflow], to both create the desired look and a valid HTML5 site.

All the tools created to solve these problems are included in this project, but these are not meant to be currently generic tools, but are prepared to be used on the Docs Maven Skin.

---

## Maven Skin

The tools being offered by this project are not to be used by themselves, instead they are to be integrated in a Maven Skin, such as the already mentioned [Docs Maven Skin][docs-skin], and used in the site.vm file.

## The tools

The following utilities classes are currently part of the project:

- [HTML5UpdateUtils][html5-update-javadoc], called through the '$html5UpdateTool' key, it is used to update old XHTML code to the new HTML5 one.
- [SiteUtils][site-utils-javadoc], called through the '$siteTool' key, offers various methods which upgrade a Maven Site, but are meant to be used on my skins, and so may not be considered completely generic.
- [HTMLUtils][html-utils-javadoc], called through the '$htmlTool' key, contains various helpful methods for extending what a Maven Skin may do.
- [SkinConfigUtils][skin-config-javadoc], called through the '$config' key, offers methods to handle custom configuration info to be used on any Maven Skin.


[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin
[reflow]: http://andriusvelykis.github.io/reflow-maven-skin/
[velocity]: http://velocity.apache.org/

[html5-update-javadoc]: ./apidocs/com/wandrell/velocity/tool/HTML5UpdateUtils.html
[site-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/SiteUtils.html
[html-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/HTMLUtils.html
[skin-config-javadoc]: ./apidocs/com/wandrell/velocity/tool/SkinConfigUtils.html