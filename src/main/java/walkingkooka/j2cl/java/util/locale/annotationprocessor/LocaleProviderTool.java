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

import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.text.CharSequences;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;

/**
 * This tool prints to sysout, that prints a List holding all {@link WalkingkookaLanguageTag} with their data queried from the JDK classes.
 */
public final class LocaleProviderTool {

    public static void main(final String[] args) throws Exception {
        try (final Printer printer = Printers.sysOut()) {
            final StringBuilder data = new StringBuilder();
            generate(WalkingkookaLanguageTag.all("EN"),
                    StringDataInputDataOutput.output(data::append),
                    LocaleProviderAnnotationProcessor.comments(printer));
            printer.print(CharSequences.quoteAndEscape(data));
            printer.flush();
        }
    }

    static void generate(final Set<String> languageTags,
                         final DataOutput data,
                         final IndentingPrinter comments) throws Exception {
        data.writeInt(languageTags.size() + (languageTags.contains("nn-NO") ? 1 : 0));

        for (final String tag : languageTags) {
            generate0(tag, data, comments);
        }
    }

    private LocaleProviderTool() {
        super();
    }

    private static void generate0(final String languageTag,
                                  final DataOutput data,
                                  final IndentingPrinter comments) throws IOException {
        final Locale locale = Locale.forLanguageTag(languageTag);

        final String language = locale.getLanguage();
        final String country = locale.getCountry();
        final String variant = locale.getVariant();
        final String script = locale.getScript();

        String encoded = languageTag + WalkingkookaLanguageTag.LOCALE_COMPONENT_SEPARATOR +
                language + WalkingkookaLanguageTag.LOCALE_COMPONENT_SEPARATOR +
                country + WalkingkookaLanguageTag.LOCALE_COMPONENT_SEPARATOR +
                variant + WalkingkookaLanguageTag.LOCALE_COMPONENT_SEPARATOR +
                script;

        for (; ; ) {
            if (false == encoded.endsWith(WalkingkookaLanguageTag.LOCALE_COMPONENT_SEPARATOR)) {
                break;
            }
            encoded = CharSequences.subSequence(encoded, 0, -1).toString();
        }

        if (encoded.equals(languageTag + WalkingkookaLanguageTag.LOCALE_COMPONENT_SEPARATOR + languageTag)) {
            encoded = languageTag;
        }

        comments.print("" +
                pad(languageTag) +
                pad("language=" + language) +
                pad("country=" + country) +
                pad("variant=" + variant) +
                pad("script=" + script) +
                "encoded=" + encoded);
        comments.print(comments.lineEnding());

        data.writeUTF(encoded);

        if (languageTag.equals("nn-NO")) {
            comments.print("" +
                    pad(languageTag) +
                    pad("language=nn-NO") +
                    pad("country=no") +
                    pad("variant=NO") +
                    pad("script=NY") +
                    "encoded=" + encoded);
            comments.print(comments.lineEnding());

            data.writeUTF("nn-NO,nn-NO,no,NO,NY");
        }
    }

    private static CharSequence pad(final String text) {
        return CharSequences.padRight(text, 20, ' ');
    }
}
