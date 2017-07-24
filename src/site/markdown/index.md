# A fix for Maven Sites

Update the outdated XHTML pages created by the [Maven site plugin][maven_site] to HTML5 with the use of custom [Velocity][velocity] tools classes.

A Maven site contains lots of ugly code such as this:

```html
<table class="bodyTable testClass">
   <tbody>
      <tr class="a">
         <th>Header 1</th>
         <th>Header 2</th>
      </tr>
      <tr class="b">
         <td>Data 1</td>
         <td>Data 2</td>
      </tr>
   </tbody>
</table>
```

Which is a case of invalid HTML 5 code, as it is a table without heading, using the first row as a replacement for it.

The tools included in this project will transform that example into this:

```html
<table class="bodyTable testClass">
   <thead>
      <tr class="a">
         <th>Header 1</th>
         <th>Header 2</th>
      </tr>
   </thead>
   <tbody>
      <tr class="b">
         <td>Data 1</td>
         <td>Data 2</td>
      </tr>
   </tbody>
</table>
```

## Projects making use of these tools

The tools were developed to be used by the [Docs Maven Skin][docs-skin].

## The tools

The [tools page][tools] contains a detailed list of the tools which compose the project and their usage.

These are very ease to use, as the only requirement is adding them as a dependency. For more details on this check the [usage page][usage].

[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin
[maven_site]: https://maven.apache.org/plugins/maven-site-plugin/
[velocity]: http://velocity.apache.org/

[tools]: ./tools.html
[usage]: ./usage.html