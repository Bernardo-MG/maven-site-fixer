# A fix for Maven Sites

Update the outdated XHTML pages created by the [Maven site plugin][maven_site] to HTML5 with the use of custom [Velocity][velocity] tools classes.

## Projects making use of these tools

The tools were developed to be used by the [Docs Maven Skin][docs-skin].

## Usage

Import the tools into a Maven skin project, and call them inside a Velocity template:

```
#set ( $bodyContent = $html5UpdateTool.updateCodeSections( $bodyContent ) )
```

## The tools

They are detailed in the [tools page][tools].


[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin
[maven_site]: https://maven.apache.org/plugins/maven-site-plugin/
[velocity]: http://velocity.apache.org/

[tools]: ./tools.html