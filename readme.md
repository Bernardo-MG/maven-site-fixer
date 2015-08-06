# Maven Site Fixer

A small group of tools for the Velocity templating engine, meant to be used for updating Maven Sites to HTML5, and fixing some structure errors and limitations.

To check a project which makes use of this library take a look at the [Docs Maven Skin][docs-skin].

[![Maven Central](https://img.shields.io/maven-central/v/com.wandrell.velocity/maven-site-fixer.svg.svg)][maven-repo]
[![Bintray](https://api.bintray.com/packages/bernardo-mg/maven/maven-site-fixer/images/download.svg)][bintray-repo]

[![Release docs](https://img.shields.io/badge/docs-release-blue.svg)][site-release]
[![Development docs](https://img.shields.io/badge/docs-develop-blue.svg)][site-develop]

[![Release javadocs](https://img.shields.io/badge/javadocs-release-blue.svg)][javadoc-release]
[![Development javadocs](https://img.shields.io/badge/javadocs-develop-blue.svg)][javadoc-develop]

## Features

The project is composed by a small set of tools each offering solutions to a problem or limitation:

- Upgrading XHTML to HTML5.
- Various corrections, such as fixing report headings.
- Easy access to custom Maven Site configuration info.

## Documentation

Documentation is always generated for the latest release, kept in the 'master' branch:

- The [latest release documentation page][site-release].
- The [the latest release Javadoc site][javadoc-release].

Documentation is also generated from the latest snapshot, taken from the 'develop' branch:

- The [the latest snapshot documentation page][site-develop].
- The [the latest snapshot Javadoc site][javadoc-develop].

The documentation site sources come along the source code (as it is a Maven site), so it is always possible to generate them using the following Maven command:

```
$ mvn verify site
```

The verify phase is required, as otherwise some of the reports won't be created.

## Usage

The application is coded in Java, using Maven to manage the project.

### Prerequisites

The project has been tested on the following Java versions:
* JDK 7
* JDK 8
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file.

### Installing

The recommended way to install the project is by setting up your preferred dependencies manager. To get the configuration information for this check the [Bintray repository][bintray-repo], or the [Maven Central Repository][maven-repo].

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
[bintray-repo]: https://bintray.com/bernardo-mg/maven/velocity-tools/view
[maven-repo]: http://mvnrepository.com/artifact/com.wandrell.velocity/velocity-tools
[issues]: https://github.com/bernardo-mg/velocity-tools/issues
[javadoc-develop]: http://docs.wandrell.com/development/maven/velocity-tools/apidocs
[javadoc-release]: http://docs.wandrell.com/maven/velocity-tools/apidocs
[license]: http://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/velocity-tools
[site-develop]: http://docs.wandrell.com/development/maven/velocity-tools
[site-release]: http://docs.wandrell.com/maven/velocity-tools
