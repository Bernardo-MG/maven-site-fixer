# Maven Site Fixer

Update the outdated XHTML pages created by the [Maven site plugin][maven_site] to HTML5 with the use of custom [Velocity][velocity] tools.

To check these tools on action take a look at the [Docs Maven Skin][docs-skin], a modern and reactive Maven Site skin.

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

[![Maven Central](https://img.shields.io/maven-central/v/com.bernardomg.velocity/maven-site-fixer.svg)][maven-repo]

## Features

The project is composed by a small set of tools which allow editing the content of a Maven site page

- Upgrading XHTML to HTML5.
- Manual edition of HTML.
- Reports corrections.
- Various corrections, such as fixing anchor links.

## Projects making use of these tools

The tools were developed to be used by the [Docs Maven Skin][docs-skin].

## Acknowledgement

The code comes from adapting the tools at the [Reflow Maven Skin][reflow-skin]. They were made their own project, instead of being part of a multi-module Maven project, and then heavily modified.

## Usage

The application is coded in Java, using Maven to manage the project.

The tools are meant to be using through Velocity, by making use of Maven Site autofinder feature. Just include the project as a dependency on any Maven Skin, and then the tools can be used like this:

```
#set( $empty = $html5UpdateTool.updateTableHeads( $bodyContentParsed ) )
```

More information can be found in the documentation pages.

### Prerequisites

The project has been tested on the following Java versions:
* JDK 7
* JDK 8
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file.

### Installing

The recommended way to install the project is by setting up your preferred dependencies manager. To get the configuration information for this check the [Maven Central Repository][maven-repo].

If for some reason manual installation is necessary, just use the following Maven command:

```
$ mvn install
```

## Collaborate

Any kind of help with the project will be well received, and there are two main ways to give such help:

- Reporting errors and asking for extensions through the issues management
- or forking the repository and extending the project

### Issues management

Issues are managed at the GitHub [project issues tracker][issues], where any Github user may report bugs or ask for new features.

### Getting the code

If you wish to fork or modify the code, visit the [GitHub project page][scm], where the latest versions are always kept. Check the 'master' branch for the latest release, and the 'develop' for the current, and stable, development version.

## License

The project has been released under the [MIT License][license].

[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin
[maven_site]: https://maven.apache.org/plugins/maven-site-plugin/
[reflow-skin]: https://github.com/andriusvelykis/reflow-maven-skin
[velocity]: http://velocity.apache.org/

[maven-repo]: http://mvnrepository.com/artifact/com.bernardomg.velocity/maven-site-fixer
[issues]: https://github.com/bernardo-mg/maven-site-fixer/issues
[license]: http://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/maven-site-fixer
