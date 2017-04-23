# Usage

These tools are meant to be used by a Maven Skin through Velocity, and will be put into action when generating a Maven Site. For this first they have to be added as a dependency, and then called inside any file processed by Velocity.

To know more about Velocity and Maven Sites read the plugin's docs. But as a general rule, any '.vm' file in the Maven Site folder will be processed into an HTML file by Velocity.

## Maven Site tools loader

Maven Site will load these tools automatically, they just need added as a dependency on the project.

Try to use the latest Maven Site plugin version, as the tools won't work in all the versions due to various compatibility issues.

## Calling the tools

Each utilities class has a key assigned which can be used inside any Maven Site file being processed by Velocity.

For example, updating the code sections to HTML5 just requires using the following command in the site.vm file:

```
#set( $empty = $html5UpdateTool.updateTableHeads( $bodyContentParsed ) )
```

It is recommended calling the methods inside a set, to silence the output.

### Keys

Check the [tools page][tools] for finding the key for each utilities class.

### Parsing the body content

Before using any of the tools the body content of the page has to be parsed:

```
#set( $bodyContentParsed = $htmlTool.parse( $bodyContent ) )
```

Once the content has been fixed, the HTML can be recovered this way:

```
#set( $bodyContent = $bodyContentParsed.html() )
```

## Usage examples

The [Docs Maven Skin][docs-skin] makes use of these tools, and can be a good example for them.

[html5-update-javadoc]: ./apidocs/com/wandrell/velocity/tool/HTML5UpdateUtils.html
[html-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/HTMLUtils.html
[site-utils-javadoc]: ./apidocs/com/wandrell/velocity/tool/SiteUtils.html
[skin-config-javadoc]: ./apidocs/com/wandrell/velocity/tool/ConfigTool.html

[tools]: ./tools.html

[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin