#Usage

These tools are meant to be used through Velocity, meaning they are to be used inside a '.vm' file.

For this each utilities class has a key assigned, which can be used to call their methods. For example, updating the code sections to HTML5 just requires using the following command in the site.vm file:

```
#set ( $bodyContent = $html5UpdateTool.updateCodeSections( $bodyContent ) )
```

To get more examples, check a project which makes use of the library such as the [Docs Maven Skin][docs-skin].

## Keys

Each class has the following key assigned:

|Class|Key|
|---|---|
|[HTML5UpdateUtils][html5-update-javadoc]|$html5UpdateTool|
|[HTMLUtils][html-utils-javadoc]|$htmlTool|
|[SiteUtils][site-utils-javadoc]|$siteTool|
|[SkinConfigUtils][skin-config-javadoc]|$config|

[html5-update-javadoc]: ./apidocs/com/wandrell/velocity/tool/HTML5UpdateUtils.html
[site-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/SiteUtils.html
[html-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/HTMLUtils.html
[skin-config-javadoc]: ./apidocs/com/wandrell/velocity/tool/SkinConfigUtils.html

[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin