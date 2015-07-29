# Velocity Tools

This is a set of tools for [Apache Velocity][velocity] meant to ease the use of Maven Site templates.

It is based on the tools included on the [Reflow Maven Skin][reflow], which have received heavy refactoring, including removing all the unneeded methods, such as URI modification.

These tools are meant to be used by the [Docs Maven Skin][docs-skin], and so are not meant to be generic. Still most of their code may be reused on similar projects.

## The tools

Two utilities classes, meant to be the tool classes for Velocity, are offered:

- [__SkinConfigTool__][javadoc-skin-config] through the '$config' key it offers methods to access the skin's configuration options in the site.xml file.
- [__HtmlTool__][javadoc-html] through the '$htmlTool' key it offers methods to query and edit HTML content, and includes method to upgrade the code to HTML5.


[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin
[reflow]: http://andriusvelykis.github.io/reflow-maven-skin/
[velocity]: http://velocity.apache.org/

[javadoc-skin-config]: ./skin_config_tool.html
[javadoc-html]: ./html_tool.html