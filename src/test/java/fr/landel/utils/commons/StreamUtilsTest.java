/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilles Landel
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
package fr.landel.utils.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Test;

import fr.landel.utils.commons.function.QuadConsumer;
import fr.landel.utils.commons.function.QuadFunction;
import fr.landel.utils.commons.function.TriConsumer;
import fr.landel.utils.commons.function.TriFunction;
import fr.landel.utils.commons.tuple.MutableSingle;
import fr.landel.utils.commons.tuple.Single;

/**
 * Check {@link StreamUtils}
 *
 * @since Aug 3, 2017
 * @author Gilles Landel
 *
 */
public class StreamUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link StreamUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(StreamUtils.class));
    }

    /**
     * Test method for {@link StreamUtils#not(java.util.function.Predicate)}.
     */
    @Test
    public void testNot() {
        assertTrue(StreamUtils.not(Objects::isNull).test("not null"));
    }

    /**
     * Test method for {@link StreamUtils#and(java.util.function.Predicate,
     * java.util.function.Predicate<? super T>[])}.
     */
    @Test
    public void testAnd() {
        assertTrue(StreamUtils.and(Objects::nonNull, StringUtils::isAllLowerCase).test("and"));
        assertFalse(StreamUtils.and(Objects::isNull, StringUtils::isAllLowerCase).test("and"));
        assertFalse(StreamUtils.and(Objects::nonNull, StringUtils::isAllUpperCase).test("and"));
        assertFalse(StreamUtils.and(Objects::isNull, StringUtils::isAllUpperCase).test("and"));

        assertException(() -> StreamUtils.and(null, StringUtils::isAllLowerCase), NullPointerException.class);
        assertException(() -> StreamUtils.and(Objects::isNull), IllegalArgumentException.class, "predicates cannot be null or empty");
    }

    /**
     * Test method for {@link StreamUtils#or(java.util.function.Predicate,
     * java.util.function.Predicate<? super T>[])}.
     */
    @Test
    public void testOr() {
        assertTrue(StreamUtils.or(Objects::nonNull, StringUtils::isAllLowerCase).test("and"));
        assertTrue(StreamUtils.or(Objects::isNull, StringUtils::isAllLowerCase).test("and"));
        assertTrue(StreamUtils.or(Objects::nonNull, StringUtils::isAllUpperCase).test("and"));
        assertFalse(StreamUtils.or(Objects::isNull, StringUtils::isAllUpperCase).test("and"));

        assertException(() -> StreamUtils.or(null, StringUtils::isAllLowerCase), NullPointerException.class);
        assertException(() -> StreamUtils.or(Objects::isNull), IllegalArgumentException.class, "predicates cannot be null or empty");
    }

    /**
     * Test method for {@link StreamUtils#andThen}.
     */
    @Test
    public void testAndThenConsumer() {
        final String error = "consumers cannot be null or empty";

        final MutableSingle<Integer> single = Single.ofMutable(0);
        final Consumer<String> consumer1 = s -> single.set(s.length());
        final Consumer<String> consumer2 = s -> single.set(single.get() * s.length());
        StreamUtils.andThen(consumer1, consumer2).accept("test");
        assertEquals(16, single.get().intValue());

        single.set(0);
        final BiConsumer<String, String> biConsumer1 = (s1, s2) -> single.set(s1.length() + s2.length());
        final BiConsumer<String, String> biConsumer2 = (s1, s2) -> single.set(single.get() * (s1.length() + s2.length()));
        StreamUtils.andThen(biConsumer1, biConsumer2).accept("te", "st");
        assertEquals(16, single.get().intValue());

        single.set(0);
        final TriConsumer<String, String, String> triConsumer1 = (s1, s2, s3) -> single.set(s1.length() + s2.length() + s3.length());
        final TriConsumer<String, String, String> triConsumer2 = (s1, s2, s3) -> single
                .set(single.get() * (s1.length() + s2.length() + s3.length()));
        StreamUtils.andThen(triConsumer1, triConsumer2).accept("te", "st", "3");
        assertEquals(25, single.get().intValue());

        single.set(0);
        final QuadConsumer<String, String, String, String> quadConsumer1 = (s1, s2, s3, s4) -> single
                .set(s1.length() + s2.length() + s3.length() + s4.length());
        final QuadConsumer<String, String, String, String> quadConsumer2 = (s1, s2, s3, s4) -> single
                .set(single.get() * (s1.length() + s2.length() + s3.length() + s4.length()));
        StreamUtils.andThen(quadConsumer1, quadConsumer2).accept("t", "e", "s", "t");
        assertEquals(16, single.get().intValue());

        assertException(() -> StreamUtils.andThen((Consumer<String>) null).accept("test"), NullPointerException.class, "consumer");
        assertException(() -> StreamUtils.andThen(consumer1, (Consumer<String>) null).accept("test"), NullPointerException.class);
        assertException(() -> StreamUtils.andThen(consumer1).accept("test"), IllegalArgumentException.class, error);

        assertException(() -> StreamUtils.andThen((BiConsumer<String, String>) null).accept("te", "st"), NullPointerException.class,
                "consumer");
        assertException(() -> StreamUtils.andThen(biConsumer1, (BiConsumer<String, String>) null).accept("te", "st"),
                NullPointerException.class);
        assertException(() -> StreamUtils.andThen(biConsumer1).accept("te", "st"), IllegalArgumentException.class, error);

        assertException(() -> StreamUtils.andThen((TriConsumer<String, String, String>) null).accept("te", "st", "3"),
                NullPointerException.class, "consumer");
        assertException(() -> StreamUtils.andThen(triConsumer1, (TriConsumer<String, String, String>) null).accept("te", "st", "3"),
                NullPointerException.class);
        assertException(() -> StreamUtils.andThen(triConsumer1).accept("te", "st", "3"), IllegalArgumentException.class, error);

        assertException(() -> StreamUtils.andThen((QuadConsumer<String, String, String, String>) null).accept("t", "e", "s", "t"),
                NullPointerException.class, "consumer");
        assertException(
                () -> StreamUtils.andThen(quadConsumer1, (QuadConsumer<String, String, String, String>) null).accept("t", "e", "s", "t"),
                NullPointerException.class);
        assertException(() -> StreamUtils.andThen(quadConsumer1).accept("t", "e", "s", "t"), IllegalArgumentException.class, error);
    }

    /**
     * Test method for {@link StreamUtils#andThen}.
     */
    @Test
    public void testAndThenFunction() {
        assertTrue(StreamUtils.andThen(Objects::nonNull, Boolean::valueOf).apply(""));

        final BiFunction<Object, Object, Boolean> biFunction = Objects::equals;
        assertTrue(StreamUtils.andThen(biFunction, Boolean::valueOf).apply("", ""));

        final TriFunction<String, String, Integer, String> triFunction = StringUtils::substring;
        assertTrue(StreamUtils.andThen(triFunction, StringUtils::isNotEmpty).apply("text", "ex", 1));

        final QuadFunction<String, String, Integer, Integer, String> quadFunction = StringUtils::replace;
        assertTrue(StreamUtils.andThen(quadFunction, StringUtils::isNotEmpty).apply("text", "ex", 1, 2));

        assertException(() -> StreamUtils.andThen((Function<String, Boolean>) null, Boolean::valueOf).apply(""),
                NullPointerException.class);
        assertException(() -> StreamUtils.andThen(Objects::nonNull, (Function<Boolean, Boolean>) null).apply(""),
                NullPointerException.class);

        assertException(() -> StreamUtils.andThen((BiFunction<Object, Object, Boolean>) null, Boolean::valueOf).apply("", ""),
                NullPointerException.class);
        assertException(() -> StreamUtils.andThen(biFunction, (Function<Boolean, Boolean>) null).apply("", ""), NullPointerException.class);

        assertException(() -> StreamUtils.andThen((TriFunction<String, String, Integer, String>) null, StringUtils::isNotEmpty)
                .apply("text", "ex", 1), NullPointerException.class);
        assertException(() -> StreamUtils.andThen(triFunction, (Function<String, Boolean>) null).apply("text", "ex", 1),
                NullPointerException.class);

        assertException(() -> StreamUtils.andThen((QuadFunction<String, String, Integer, Integer, String>) null, StringUtils::isNotEmpty)
                .apply("text", "ex", 1, 2), NullPointerException.class);
        assertException(() -> StreamUtils.andThen(quadFunction, (Function<String, Boolean>) null).apply("text", "ex", 1, 2),
                NullPointerException.class);
    }

    /**
     * Test method for
     * {@link StreamUtils#compose(java.util.function.Function, java.util.function.Function)}.
     */
    @Test
    public void testCompose() {
        assertTrue(StreamUtils.compose(Boolean::valueOf, Objects::nonNull).apply(""));

        assertException(() -> StreamUtils.compose(null, Objects::nonNull).apply(""), NullPointerException.class);
        assertException(() -> StreamUtils.compose(Boolean::valueOf, (Function<String, Boolean>) null).apply(""),
                NullPointerException.class);
    }
}
