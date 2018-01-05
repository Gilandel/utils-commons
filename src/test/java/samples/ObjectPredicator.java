/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2018 Gilles Landel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package samples;

import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.landel.utils.commons.ArrayUtils;

/**
 * A collection of predicates
 *
 * @since Mar 7, 2017
 * @author Gilles
 *
 */
public class ObjectPredicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectPredicator.class.getName());

    public static <T> Predicate<T> isNull() {
        return Objects::isNull;
    }

    public static <T> Predicate<T> isNotNull() {
        return Objects::nonNull;
    }

    public static <T> Predicate<T> isEqual(final Object object) {
        return t -> Objects.equals(t, object);
    }

    public static <T> Predicate<T> isNotEqual(final Object object) {
        return t -> !Objects.equals(t, object);
    }

    public static <T extends CharSequence> Predicate<T> isEmpty() {
        return StringUtils::isEmpty;
    }

    public static <T extends CharSequence> Predicate<T> isNotEmpty2() {
        return StringUtils::isNotEmpty;
    }

    public static <T> Predicate<T[]> isNotEmpty() {
        return ArrayUtils::isNotEmpty;
    }

    public static <T extends CharSequence> Predicate<T> isBlank() {
        return StringUtils::isBlank;
    }

    public static <T extends CharSequence> Predicate<T> isNotBlank() {
        return StringUtils::isNotBlank;
    }

    public static <T extends CharSequence> Predicate<T> startsWith(final CharSequence prefix) {
        return t -> StringUtils.startsWith(t, prefix);
    }

    public static <T extends CharSequence> Predicate<T> endsWith(final CharSequence suffix) {
        return t -> StringUtils.endsWith(t, suffix);
    }

    public static <T extends CharSequence> Predicate<T> contains(final CharSequence subtext) {
        return t -> StringUtils.contains(t, subtext);
    }

    public static <T extends CharSequence> Predicate<T> equalsIgnoreCase(final CharSequence suffix) {
        return t -> StringUtils.equalsIgnoreCase(t, suffix);
    }

    public static <T> Predicate<Iterable<T>> hasLength(final int length) {
        return t -> CollectionUtils.size(t) == length;
    }

    public static <T> Predicate<Iterable<T>> isEmptyCollection() {
        return CollectionUtils::sizeIsEmpty;
    }

    public static <T> Predicate<Iterable<T>> contains(final T element) {
        return t -> IterableUtils.contains(t, element);
    }

    public static void main(String... strings) {
        LOGGER.info("Result: {}, expected: {}", isEmpty().or(isNotBlank()).test("toto"), true);
        LOGGER.info("Result: {}, expected: {}", isEmpty().or(isNotBlank()).test(""), true);
        LOGGER.info("Result: {}, expected: {}", isEmpty().or(isNotBlank()).test("   "), false);
        LOGGER.info("Result: {}, expected: {}", isEmpty().or(contains("test")).test("  test "), true);
        LOGGER.info("Result: {}, expected: {}", isEmpty().or(contains("test")).test(""), true);
        LOGGER.info("Result: {}, expected: {}", isEmpty().or(contains("test")).test("tesot"), false);
        LOGGER.info("Result: {}, expected: {}", isEmpty().or(contains("test")).or(equalsIgnoreCase("TeSoT")).test("tesot"), true);
    }
}
