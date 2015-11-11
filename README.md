# JMarkdown2RevealJs

[![Build Status](https://travis-ci.org/stefanbirkner/jmarkdown2revealjs.svg?branch=master)](https://travis-ci.org/stefanbirkner/jmarkdown2revealjs)


JMarkdown2RevealJs is a Java library that converts
[Markdown](http://daringfireball.net/projects/markdown/) files to
[reveal.js](http://lab.hakim.se/reveal-js/) presentations.
JMarkdown2RevealJs has been inspired by Ulf Börgenholz'
[markdown2deckjs](https://github.com/ulf/markdown2deckjs).

JMarkdown2RevealJs is published under the
[MIT license](http://opensource.org/licenses/MIT). It uses Java 8. Please
[open an issue](https://github.com/stefanbirkner/jmarkdown2revealjs/issues/new)
if you want to use it with an earlier version of Java.

## Installation

JMarkdown2RevealJs is available from [Maven Central](http://search.maven.org/).

    <dependency>
      <groupId>com.github.stefanbirkner</groupId>
      <artifactId>jmarkdown2revealjs</artifactId>
      <version>0.1.0-SNAPSHOT</version>
    </dependency>


## Usage

### File Format

Use a standard Markdown file. JMarkdown2RevealJs create a new slide
whenever it encounters an `h1` or `h2`. Here is an example file with
three slides.

    Title Slide
    ===========

    First Slide
    -----------

    content of first slide

    Second Slide
    ------------

    content of second slide

### Create the Presentation

    import com.github.stefanbirkner.jmarkdown2revealjs.*;

    String markdown = readMarkdown(); //your code that reads the markdown
    String revealJsHtml = new JMarkdown2RevealJs().convert(markdown);

JMarkdown2RevealJs uses relative URLs for the CSS and JavaScript files by
default. You can create HTML files with different URLs by providing a
configuration with a prefix for the URLs.

    import com.github.stefanbirkner.jmarkdown2revealjs.*;

    Configuration configuration = new Configuration("http://your.domain/");
    String markdown = readMarkdown(); //your code that reads the markdown
    String revealJsHtml = new JMarkdown2RevealJs(configuration).convert(markdown);


## Contributing

You have three options if you have a feature request, found a bug or
simply have a question about JMarkdown2RevealJs.

* [Write an issue.](https://github.com/stefanbirkner/jmarkdown2revealjs/issues/new)
* Create a pull request. (See [Understanding the GitHub Flow](https://guides.github.com/introduction/flow/index.html))
* [Write a mail to mail@stefan-birkner.de](mailto:mail@stefan-birkner.de)


## Development Guide

JMarkdown2RevealJs is build with [Maven](http://maven.apache.org/). If you
want to contribute code than

* Please write a test for your change.
* Ensure that you didn't break the build by running `mvn test`.
* Fork the repo and create a pull request. (See [Understanding the GitHub Flow](https://guides.github.com/introduction/flow/index.html))

The basic coding style is described in the
[EditorConfig](http://editorconfig.org/) file `.editorconfig`.

JMarkdown2RevealJs supports [Travis CI](https://travis-ci.org/) for continuous
integration. Your pull request will be automatically build by Travis
CI.


## Release Guide

* Select a new version according to the
  [Semantic Versioning 2.0.0 Standard](http://semver.org/).
* Set the new version in `pom.xml` and in the `Installation` section of
  this readme.
* Commit the modified `pom.xml` and `README.md`.
* Run `mvn clean deploy` with JDK 8.
* Add a tag for the release: `git tag jmarkdown2revealjs-X.X.X`
