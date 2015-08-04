#Usage

While the tools on this library are usable by themselves, they are meant to be part of a Maven Skin, which will call and make use of the utilities classes.

To check a project which makes use of the library take a look at the [Docs Maven Skin][docs-skin].

But they are very easy to use, just importing the libraries allows calling them by the alias assigned to them in the tools.xml file contained in the resources folder.

Once they have been added to a project they can be called directly. For example, updating the code sections to HTML5 just requires using the following command in the site.vm file:

```
#set ( $bodyContent = $html5UpdateTool.updateCodeSections( $bodyContent ) )
```

[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin