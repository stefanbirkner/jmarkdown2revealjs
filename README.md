# jmarkdown2revealjs

[![Build Status](https://travis-ci.org/stefanbirkner/jmarkdown2revealjs.svg?branch=master)](https://travis-ci.org/stefanbirkner/jmarkdown2revealjs)


## Installation

jmarkdown2revealjs is available from
[Maven Central](http://search.maven.org/).

    <dependency>
      <groupId>com.github.stefanbirkner</groupId>
      <artifactId>jmarkdown2revealjs</artifactId>
      <version>0.1.0-SNAPSHOT</version>
    </dependency>


## Usage

...


## Contributing

You have three options if you have a feature request, found a bug or
simply have a question about jmarkdown2revealjs.

* [Write an issue.](https://github.com/stefanbirkner/jmarkdown2revealjs/issues/new)
* Create a pull request. (See [Understanding the GitHub Flow](https://guides.github.com/introduction/flow/index.html))
* [Write a mail to mail@stefan-birkner.de](mailto:mail@stefan-birkner.de)


## Development Guide

jmarkdown2revealjs is build with [Maven](http://maven.apache.org/). If you
want to contribute code than

* Please write a test for your change.
* Ensure that you didn't break the build by running `mvn test`.
* Fork the repo and create a pull request. (See [Understanding the GitHub Flow](https://guides.github.com/introduction/flow/index.html))

The basic coding style is described in the
[EditorConfig](http://editorconfig.org/) file `.editorconfig`.

jmarkdown2revealjs supports [Travis CI](https://travis-ci.org/) for continuous
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
