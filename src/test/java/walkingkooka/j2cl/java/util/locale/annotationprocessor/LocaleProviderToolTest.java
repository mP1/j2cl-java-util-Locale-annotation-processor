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
import walkingkooka.j2cl.locale.WalkingkookaLanguageTag;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.Indentation;
import walkingkooka.text.printer.Printers;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LocaleProviderToolTest implements ClassTesting<LocaleProviderTool> {

    @Test
    public void testEncode() {
        assertEquals(Stream.concat(Stream.of("", "in", "in-ID", "iw", "iw-IL", "ji", "ji-001"),
                Arrays.stream(Locale.getAvailableLocales())
                        .map(Locale::toLanguageTag)
                        .filter(t -> false == WalkingkookaLanguageTag.isUnsupported(t)))
                        .sorted()
                        .collect(Collectors.joining("\n")),
                Arrays.stream(LocaleProviderTool.encode(WalkingkookaLanguageTag.all(), Printers.sink().indenting(Indentation.with(""))).split(WalkingkookaLanguageTag.LOCALE_SEPARATOR))
                        .map(s -> {
                            final int slash = s.indexOf(WalkingkookaLanguageTag.LOCALE_COMPONENT_SEPARATOR);
                            return -1 == slash ? s : s.substring(0, slash);
                        })
                        .collect(Collectors.joining("\n")));
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
