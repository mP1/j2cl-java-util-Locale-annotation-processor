[![Build Status](https://travis-ci.com/mP1/j2cl-java-util-Locale-annotation-processor.svg?branch=master)](https://travis-ci.com/mP1/j2cl-java-util-Locale-annotation-processor.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/mP1/j2cl-java-util-Locale-annotation-processor/badge.svg?branch=master)](https://coveralls.io/github/mP1/j2cl-java-util-Locale-annotation-processor?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/j2cl-java-util-Locale-annotation-processor.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-util-Locale-annotation-processor/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/j2cl-java-util-Locale-annotation-processor.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-util-Locale-annotation-processor/alerts/)



# j2cl java-util-Locale-annotation-processor

An annotation processor that generates the `LocaleProvider` used by the emulated `java.util.Locale` in [j2cl-java-util-Locale](https://travis-ci.com/mP1/j2cl-java-util-Locale)]



## Locale selection (System property)

The locales must be selected by
setting the system property `walkingkooka.java-util-Locale` with a comma separated list of desired locales with
trailing wildcard support.

Some examples

- `*` All locales
- `EN` Only includes the `EN` locale without including `EN-US` or `EN-GB`.
- `EN-*` Includes all locales beginning with `EN`.
- `EN-*,FR-*` Include all English and French locales.



The classes included in this project should be considered internal, and this project should only be referenced as a dependency
by .[j2cl-java-util-Locale](https://travis-ci.com/mP1/j2cl-java-util-Locale)]



## Getting the source

You can either download the source using the "ZIP" button at the top
of the github page, or you can make a clone using git:

```
git clone git://github.com/mP1/j2cl-java-util-Locale-annotation-processor.git
```
