[![Build Status](https://github.com/mP1/j2cl-java-util-Locale-annotation-processor/workflows/build.yaml/badge.svg)](https://github.com/mP1/j2cl-java-util-Locale-annotation-processor/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/j2cl-java-util-Locale-annotation-processor/badge.svg?branch=master)](https://coveralls.io/github/mP1/j2cl-java-util-Locale-annotation-processor?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/j2cl-java-util-Locale-annotation-processor.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-util-Locale-annotation-processor/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/j2cl-java-util-Locale-annotation-processor.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/j2cl-java-util-Locale-annotation-processor/alerts/)



# j2cl java-util-Locale-annotation-processor

An annotation processor that generates the `LocaleProvider` used by the emulated `java.util.Locale`.

Several options are required to select the default and available locales.

```xml
<compilerArgs>
    <arg>-Awalkingkooka.j2cl.java.util.Locale=EN-*</arg>
    <arg>-Awalkingkooka.j2cl.java.util.Locale.DEFAULT=en-AU</arg>
    <arg>-Awalkingkooka.j2cl.locale.Logging=SLASH_SLASH_COMMENTS</arg>
</compilerArgs>
```

The above selects all English locales along with a default locale of Australian English. 

For more details [click here](https://github.com/mP1/j2cl-locale)



## Runtime features.

See [j2cl-java-util-Locale](https://travis-ci.com/mP1/j2cl-java-util-Locale) for a more comprehensive summary.



