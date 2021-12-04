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

import org.junit.jupiter.api.Test;
import walkingkooka.j2cl.java.io.string.StringDataInputDataOutput;
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

public final class LocaleProviderToolTest implements ClassTesting<LocaleProviderTool> {

    @Test
    public void testEncodeEN() throws Exception {
        this.generateAndCheck("EN",
                "// en                  language=en         country=            variant=            script=             encoded=en\n" +
                        "\n" +
                        "\n" +
                        "1,en");
    }

    @Test
    public void testEncodeENAU() throws Exception {
        this.generateAndCheck("EN-AU",
                "// en-AU               language=en         country=AU          variant=            script=             encoded=en-AU,en,AU\n" +
                        "\n" +
                        "\n" +
                        "1,en-AU\\,en\\,AU");
    }

    @Test
    public void testEncodeDE() throws Exception {
        this.generateAndCheck("DE*",
                "// de                  language=de         country=            variant=            script=             encoded=de\n" +
                        "// de-AT               language=de         country=AT          variant=            script=             encoded=de-AT,de,AT\n" +
                        "// de-BE               language=de         country=BE          variant=            script=             encoded=de-BE,de,BE\n" +
                        "// de-CH               language=de         country=CH          variant=            script=             encoded=de-CH,de,CH\n" +
                        "// de-DE               language=de         country=DE          variant=            script=             encoded=de-DE,de,DE\n" +
                        "// de-LI               language=de         country=LI          variant=            script=             encoded=de-LI,de,LI\n" +
                        "// de-LU               language=de         country=LU          variant=            script=             encoded=de-LU,de,LU\n" +
                        "\n" +
                        "\n" +
                        "7,de,de-AT\\,de\\,AT,de-BE\\,de\\,BE,de-CH\\,de\\,CH,de-DE\\,de\\,DE,de-LI\\,de\\,LI,de-LU\\,de\\,LU");
    }

    private void generateAndCheck(final String filter, final String expected) throws Exception {
        final StringBuilder comments = new StringBuilder();
        final StringBuilder data = new StringBuilder();
        final LineEnding eol = LineEnding.NL;

        try (final Printer printer = Printers.stringBuilder(comments, eol)) {
            LocaleProviderTool.generate(filter,
                    WalkingkookaLanguageTag.all(filter),
                    StringDataInputDataOutput.output(data::append),
                    LocaleProviderAnnotationProcessor.comments(printer));
            printer.print(eol);
            printer.flush();
            printer.close();

            this.checkEquals(expected, "" + comments + eol + data);
        }
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<LocaleProviderTool> type() {
        return LocaleProviderTool.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
