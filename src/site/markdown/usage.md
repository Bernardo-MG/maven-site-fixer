#Usage

These tools are meant to be used by a Maven Skin through Velocity, and will be put into action when generating a Maven Site. For this first they have to be added as a dependency, and then called inside any file processed by Velocity.

To know more about Velocity and Maven Sites read the plugin's docs. But as a general rule, any '.vm' file in the Maven Site folder will be processed into an HTML file by Velocity.

## Maven Site tools loader

Maven Site will load these tools automatically, they just need added as a dependency on the Maven Skin, and then they will be ready to be called when needed.

```
<dependencies>
   ...
   <dependency>
      <!-- Maven Site Fixer -->
      <groupId>com.wandrell.velocity</groupId>
      <artifactId>maven-site-fixer</artifactId>
      <version>${wandrell.siteFixer.version}</version>
   </dependency>
   ...
</dependencies>
```

Actually it is not just enough adding the dependency, it should include the 'site-tools.xml' settings file, at the path 'META-INF/maven'. Of course, it comes included in the project.

Try to use the latest Maven Site plugin version, as the tools won't work in all the versions due to various compatibility issues.

## Calling the tools

Each utilities class has a key assigned, and after being loaded this key can be used inside any Maven Site file being processed by Velocity.


For example, updating the code sections to HTML5 just requires using the following command in the site.vm file:

```
#set ( $bodyContent = $html5UpdateTool.updateCodeSections( $bodyContent ) )
```

### Keys

Each class has the following key assigned:

|Class|Key|
|---|---|
|[HTML5UpdateUtils][html5-update-javadoc]|$html5UpdateTool|
|[HTMLUtils][html-utils-javadoc]|$htmlTool|
|[MenuUtils][menu-utils-javadoc]|$menuTool|
|[SiteUtils][site-utils-javadoc]|$siteTool|
|[SkinConfigUtils][skin-config-javadoc]|$config|

## Usage examples

The [Docs Maven Skin][docs-skin] makes use of these tools, and can be a good example for them.

[html5-update-javadoc]: ./apidocs/com/wandrell/velocity/tool/HTML5UpdateUtils.html
[html-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/HTMLUtils.html
[menu-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/MenuUtils.html
[site-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/SiteUtils.html
[skin-config-javadoc]: ./apidocs/com/wandrell/velocity/tool/SkinConfigUtils.html

[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin