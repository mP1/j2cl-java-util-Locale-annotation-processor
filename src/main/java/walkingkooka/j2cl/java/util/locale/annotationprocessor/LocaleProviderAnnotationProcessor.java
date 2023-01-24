/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.j2cl.java.util.locale.annotationprocessor;

import walkingkooka.collect.set.Sets;
import walkingkooka.j2cl.locale.annotationprocessor.LocaleAwareAnnotationProcessor;
import walkingkooka.text.CharSequences;
import walkingkooka.text.printer.IndentingPrinter;

import java.io.DataOutput;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class LocaleProviderAnnotationProcessor extends LocaleAwareAnnotationProcessor {

    @Override
    protected Set<String> additionalArguments() {
        return Sets.of(
                DEFAULT_LOCALE
        );
    }

    @Override
    protected Optional<String> defaultValue(final Set<String> locales,
                                            final Function<String, String> arguments) {
        final String defaultLocale = arguments.apply(DEFAULT_LOCALE);
        if (false == locales.contains(defaultLocale)) {
            throw new IllegalArgumentException(
                    "Default Locale " +
                            CharSequences.quoteAndEscape(defaultLocale) +
                            " missing from selected locales " +
                            CharSequences.quoteAndEscape(
                                    locales.stream()
                                            .collect(Collectors.joining(","))
                            )
            );
        }
        return Optional.of(defaultLocale);
    }

    private final static String DEFAULT_LOCALE = "walkingkooka.j2cl.java.util.Locale.DEFAULT";

    @Override
    protected String generate(final String filter,
                              final Set<String> languageTags,
                              final Function<String, String> arguments,
                              final DataOutput data,
                              final IndentingPrinter comments) throws Exception {
        return LocaleProviderTool.generate(filter, languageTags, data, comments);
    }
}
