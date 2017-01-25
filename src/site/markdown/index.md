#A fix for Maven Sites

Maven makes use of [Apache Velocity][velocity] to create web pages. Sadly, the result of this is an outdated XHTML page, which also is hard to customize.

This became apparent when creating the [Docs Maven Skin][docs-skin], which required a set of helping tools, initially adapted from the [Reflow Maven Skin][reflow], to both create the desired look and a valid HTML5 site.

All the tools created to solve these problems are included in this project, but these are not meant to be currently generic tools, but are prepared to be used on the Docs Maven Skin.

---

## Maven Skin

The tools being offered by this project are not to be used by themselves, instead they are to be integrated in a Maven Skin, such as the already mentioned [Docs Maven Skin][docs-skin], and used in the site.vm file.

## The tools

Inside the [tools page][tools] all the utilities contained in the project are detailed.


[docs-skin]: https://github.com/Bernardo-MG/docs-maven-skin
[reflow]: http://andriusvelykis.github.io/reflow-maven-skin/
[velocity]: http://velocity.apache.org/

[tools]: ./tools.html