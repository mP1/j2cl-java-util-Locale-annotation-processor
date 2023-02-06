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
import walkingkooka.collect.set.Sets;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class LocaleProviderAnnotationProcessorTest implements ClassTesting<LocaleProviderAnnotationProcessor> {

    @Test
    public void testDefaultPublicConstructor() throws Exception {
        this.checkEquals(
                JavaVisibility.PUBLIC,
                JavaVisibility.of(LocaleProviderAnnotationProcessor.class.getConstructor())
        );
    }

    @Test
    public void testFailIdDefaultLocaleMissingFails() {
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> LocaleProviderAnnotationProcessor.failIdDefaultLocaleMissing(
                        "EN-AU",
                        Sets.of("FR", "EN-GB")
                )
        );

        this.checkEquals(
                "Default Locale \"EN-AU\" missing from selected locales \"FR, EN-GB\"",
                thrown.getMessage()
        );
    }

    @Test
    public void testFailIdDefaultLocaleMissingDifferentCase() {
        LocaleProviderAnnotationProcessor.failIdDefaultLocaleMissing(
                "EN-AU",
                Sets.of("en-AU")
        );
    }

    @Test
    public void testFailIdDefaultLocaleMissingSameCase() {
        LocaleProviderAnnotationProcessor.failIdDefaultLocaleMissing(
                "EN-AU",
                Sets.of("en-AU")
        );
    }

    @Override
    public Class<LocaleProviderAnnotationProcessor> type() {
        return LocaleProviderAnnotationProcessor.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
