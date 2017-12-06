/*
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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

/**
 * Check utility class (strings).
 *
 * @since Nov 27, 2015
 * @author Gilles Landel
 *
 */
public class StringUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link StringUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(StringUtils.class));
    }

    /**
     * Test method for
     * {@link fr.landel.utils.commons.StringUtils#nullIfEmpty(java.lang.CharSequence)}
     * .
     */
    @Test
    public void testGetNullIfEmpty() {
        String expected = "value";
        assertEquals(expected, StringUtils.nullIfEmpty(expected));

        assertNull(StringUtils.nullIfEmpty(""));
        assertNull(StringUtils.nullIfEmpty(null));
    }

    /**
     * Test method for
     * {@link fr.landel.utils.commons.StringUtils#defaultIfEmpty(java.lang.CharSequence, java.lang.CharSequence)}
     * .
     */
    @Test
    public void testGetDefaultIfEmpty() {
        String expected = "value";
        String defaultValue = "def";
        assertEquals(expected, StringUtils.defaultIfEmpty(expected, defaultValue));

        assertEquals(defaultValue, StringUtils.defaultIfEmpty("", defaultValue));
        assertEquals(defaultValue, StringUtils.defaultIfEmpty(null, defaultValue));
    }

    /**
     * Test method for
     * {@link fr.landel.utils.commons.StringUtils#defaultIfNull(java.lang.CharSequence, java.lang.CharSequence)}
     * .
     */
    @Test
    public void testGetDefaultIfNull() {
        String expected = "value";
        String defaultValue = "def";
        assertEquals(expected, StringUtils.defaultIfNull(expected, defaultValue));

        assertEquals("", StringUtils.defaultIfNull("", defaultValue));
        assertEquals(defaultValue, StringUtils.defaultIfNull(null, defaultValue));
    }

    /**
     * Test method for
     * {@link fr.landel.utils.commons.StringUtils#toStringOrDefaultIfNull(java.lang.Object, java.lang.String)}
     * .
     */
    @Test
    public void testGetToStringOrDefaultIfNull() {
        Long expected = 1L;
        String defaultValue = "def";
        assertEquals(String.valueOf(expected), StringUtils.toStringOrDefaultIfNull(expected, defaultValue));

        assertEquals("", StringUtils.toStringOrDefaultIfNull("", defaultValue));
        assertEquals(defaultValue, StringUtils.toStringOrDefaultIfNull(null, defaultValue));
    }

    /**
     * Test method for
     * {@link StringUtils#substring(java.lang.Object, char, int)} .
     * {@link StringUtils#substring(java.lang.Object, char, int, int)} .
     */
    @Test
    public void testSubtring() {
        String str = "test:toto:titi:";

        assertEquals(str, StringUtils.substring(str, "!", 0));

        str = "test:toto:titi:";

        assertEquals("test", StringUtils.substring(str, ":", 0));
        assertEquals("toto", StringUtils.substring(str, ":", 1));
        assertEquals("titi", StringUtils.substring(str, ":", 2));
        // exists
        assertEquals("", StringUtils.substring(str, ":", 3));
        // out of bounds
        assertEquals("", StringUtils.substring(str, ":", 4));

        // exists
        assertEquals("", StringUtils.substring(str, ":", -1));
        assertEquals("titi", StringUtils.substring(str, ":", -2));
        assertEquals("toto", StringUtils.substring(str, ":", -3));
        assertEquals("test", StringUtils.substring(str, ":", -4));
        // out of bounds
        assertEquals("", StringUtils.substring(str, ":", -5));

        assertEquals("", StringUtils.substring("", ":", 0, 2));
        assertEquals("a", StringUtils.substring("a", ":", 0, 2));
        assertEquals("test:toto", StringUtils.substring(str, ":", 0, 2));
        assertEquals("test:toto:titi", StringUtils.substring(str, ":", 0, 3));
        assertEquals("test:toto:titi:", StringUtils.substring(str, ":", 0, 4));
        // last is out of bounds
        assertEquals("test:toto:titi:", StringUtils.substring(str, ":", 0, 5));

        assertEquals("titi:", StringUtils.substring(str, ":", -1, -3));
        assertEquals("toto:titi:", StringUtils.substring(str, ":", -1, -4));
        assertEquals("test:toto:titi:", StringUtils.substring(str, ":", -1, -5));
        assertEquals("test:toto:titi:", StringUtils.substring(str, ":", -1, -6));

        assertEquals("toto", StringUtils.substring(str, ":", 1, 2));
        assertEquals("toto:titi", StringUtils.substring(str, ":", 1, 3));
        assertEquals("toto:titi:", StringUtils.substring(str, ":", 1, 4));
        assertEquals("toto:titi:", StringUtils.substring(str, ":", 1, 5));

        assertEquals("titi", StringUtils.substring(str, ":", -2, -3));
        assertEquals("toto:titi", StringUtils.substring(str, ":", -2, -4));
        assertEquals("test:toto:titi", StringUtils.substring(str, ":", -2, -5));
        assertEquals("test:toto:titi", StringUtils.substring(str, ":", -2, -6));

        str = "test::toto:titi::";

        assertEquals("test", StringUtils.substring(str, "::", 0));
        assertEquals("toto:titi", StringUtils.substring(str, "::", 1));
        assertEquals("", StringUtils.substring(str, "::", 2));
        assertEquals("", StringUtils.substring(str, "::", 3));
        assertEquals("", StringUtils.substring(str, "::", 4));

        assertEquals("", StringUtils.substring(str, "::", -1));
        assertEquals("toto:titi", StringUtils.substring(str, "::", -2));
        assertEquals("test", StringUtils.substring(str, "::", -3));
        assertEquals("", StringUtils.substring(str, "::", -4));
        assertEquals("", StringUtils.substring(str, "::", -5));

        assertEquals("test::toto:titi", StringUtils.substring(str, "::", 0, 2));
        assertEquals("test::toto:titi::", StringUtils.substring(str, "::", 0, 3));
        assertEquals("test::toto:titi::", StringUtils.substring(str, "::", 0, 4));
        assertEquals("test::toto:titi::", StringUtils.substring(str, "::", 0, 5));

        assertEquals("toto:titi::", StringUtils.substring(str, "::", -1, -3));
        assertEquals("test::toto:titi::", StringUtils.substring(str, "::", -1, -4));
        assertEquals("test::toto:titi::", StringUtils.substring(str, "::", -1, -5));
        assertEquals("test::toto:titi::", StringUtils.substring(str, "::", -1, -6));

        assertEquals("toto:titi", StringUtils.substring(str, "::", 1, 2));
        assertEquals("toto:titi::", StringUtils.substring(str, "::", 1, 3));
        assertEquals("toto:titi::", StringUtils.substring(str, "::", 1, 4));
        assertEquals("toto:titi::", StringUtils.substring(str, "::", 1, 5));

        assertEquals("toto:titi", StringUtils.substring(str, "::", -2, -3));
        assertEquals("test::toto:titi", StringUtils.substring(str, "::", -2, -4));
        assertEquals("test::toto:titi", StringUtils.substring(str, "::", -2, -5));
        assertEquals("test::toto:titi", StringUtils.substring(str, "::", -2, -6));

        assertEquals("toto:titi", StringUtils.substring(str, "::", 1, -1));

        assertEquals("test", StringUtils.substring(str, "::", 0, -2));

        assertEquals("toto:titi", StringUtils.substring(str, "::", -3, 2));

        // not checked
        assertEquals("", StringUtils.substring(str, "::", 100, 10));
        assertEquals("", StringUtils.substring(str, "::", -10, 2));
    }

    /**
     * Test method for
     * {@link StringUtils#substring(java.lang.Object, char, int, int)} .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtringException() {
        String str = "test:toto:titi:";

        // from=0, to=0
        StringUtils.substring(str, ":", 0, 0);
    }

    /**
     * Test method for
     * {@link StringUtils#substring(java.lang.Object, char, int, int)} .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtringException2() {
        String str = "test::toto:titi::";

        // from=0, to=0
        StringUtils.substring(str, "::", 1, -5);
    }

    /**
     * Test method for
     * {@link StringUtils#substring(java.lang.Object, char, int, int)} .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtringException3() {
        String str = "test::toto:titi::";

        // from=0, to=0
        StringUtils.substring(str, "::", 1, -2);
    }

    /**
     * Test method for
     * {@link StringUtils#substring(java.lang.Object, char, int, int)} .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtringException4() {
        String str = "test::toto:titi::";

        // from > to (negative, so starts from right)
        StringUtils.substring(str, "::", -2, -1);
    }

    /**
     * Test method for
     * {@link StringUtils#substring(java.lang.Object, char, int, int)} .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtringException5() {
        String str = "test::toto:titi::";

        // from > to (positive, so starts from left)
        StringUtils.substring(str, "::", 2, 1);
    }

    /**
     * Test method for
     * {@link StringUtils#replace(java.lang.String, java.lang.String, int, int)}
     * .
     */
    @Test
    public void testReplace() {
        String string = "I'll go to the beach this afternoon.";
        assertEquals("I'll go to the theater this afternoon.", StringUtils.replace(string, "theater", 15, 20));
        assertEquals("I will go to the beach this afternoon.", StringUtils.replace(string, "I will", 0, 4));
        assertEquals("I'll go to the beach this morning.", StringUtils.replace(string, "morning", 26, 35));
        assertEquals("I'll go to the beach this afternoon!", StringUtils.replace(string, "!", 35, 36));
    }

    /**
     * Test method for
     * {@link StringUtils#replace(java.lang.String, java.lang.String, int, int)}
     * .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReplaceException() {
        final String string = "I'll go to the beach this afternoon.";
        StringUtils.replace(string, "theater", -1, 1);
    }

    /**
     * Test method for
     * {@link StringUtils#replace(java.lang.String, java.lang.String, int, int)}
     * .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReplaceException2() {
        final String string = "I'll go to the beach this afternoon.";
        StringUtils.replace(string, "theater", 0, 37);
    }

    /**
     * Test method for
     * {@link StringUtils#replace(java.lang.String, java.lang.String, int, int)}
     * .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReplaceException3() {
        final String string = "I'll go to the beach this afternoon.";
        StringUtils.replace(string, null, 0, 1);
    }

    /**
     * Test method for
     * {@link StringUtils#replace(java.lang.String, java.lang.String, int, int)}
     * .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReplaceException4() {
        StringUtils.replace(null, "theater", 0, 1);
    }

    /**
     * Test method for
     * {@link StringUtils#replace(java.lang.String, java.lang.String, int, int)}
     * .
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReplaceException5() {
        final String string = "I'll go to the beach this afternoon.";
        StringUtils.replace(string, "theater", 2, 1);
    }

    /**
     * Test method for {@link StringUtils#toChars(CharSequence)} .
     */
    @Test
    public void testToChars() {
        final String string = "I'll go to the beach this afternoon.";
        final char[] expectedChars = new char[] {'I', '\'', 'l', 'l', ' ', 'g', 'o', ' ', 't', 'o', ' ', 't', 'h', 'e', ' ', 'b', 'e', 'a',
                'c', 'h', ' ', 't', 'h', 'i', 's', ' ', 'a', 'f', 't', 'e', 'r', 'n', 'o', 'o', 'n', '.'};
        final char[] chars = StringUtils.toChars(string);
        assertEquals(expectedChars.length, chars.length);
        for (int i = 0; i < chars.length; i++) {
            assertEquals(expectedChars[i], chars[i]);
        }

        try {
            StringUtils.toChars(null);
            fail();
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    /**
     * Test method for {@link StringUtils#join(Iterable, String, Function)}
     * {@link StringUtils#join(Iterator, String, Function)} .
     */
    @Test
    public void testJoin() {
        Function<Object, String> fKey = e -> "x" + String.valueOf(e);

        assertNull(StringUtils.join((Iterable<?>) null, null, null));
        assertEquals("", StringUtils.join(Collections.emptyList(), null, null));
        assertEquals("v", StringUtils.join(Arrays.asList("v"), null, null));
        assertEquals("xv", StringUtils.join(Arrays.asList("v"), null, fKey));
        assertEquals("12", StringUtils.join(Arrays.asList(1, 2), null, null));
        assertEquals("x1,x2", StringUtils.join(Arrays.asList(1, 2), ",", fKey));

        Function<String, String> fKeyStr = e -> "x" + String.valueOf(e);
        Function<Integer, String> fKeyInt = e -> "x" + String.valueOf(e);

        assertNull(StringUtils.join((Iterator<?>) null, null, null));
        assertEquals("", StringUtils.join(Collections.emptyIterator(), null, null));
        assertEquals("v", StringUtils.join(Arrays.asList("v").iterator(), null, null));
        assertEquals("xv", StringUtils.join(Arrays.asList("v").iterator(), null, fKeyStr));
        assertEquals("12", StringUtils.join(Arrays.asList(1, 2).iterator(), null, null));
        assertEquals("x1,x2", StringUtils.join(Arrays.asList(1, 2).iterator(), ",", fKeyInt));

        assertNull(StringUtils.join((Object[]) null, null, 0, 0, null));
        assertEquals("", StringUtils.join(new Object[0], null, 0, 0, null));
        assertEquals("v", StringUtils.join(new Object[] {"v", null}, null, 0, 2, null));
        assertEquals("v", StringUtils.join(new Object[] {"v"}, null, 0, 1, null));
        assertEquals("", StringUtils.join(new Object[] {"v"}, null, 1, 1, null));
        assertEquals("xv", StringUtils.join(new Object[] {"v"}, null, 0, 1, fKey));
        assertEquals("12", StringUtils.join(new Object[] {1, 2}, null, 0, 2, null));
        assertEquals("1", StringUtils.join(new Object[] {1, 2}, null, 0, 1, null));
        assertEquals("x1,x2", StringUtils.join(new Object[] {1, 2}, ",", 0, 2, fKey));
        assertEquals("x2", StringUtils.join(new Object[] {1, 2}, ",", 1, 2, fKey));

        assertNull(StringUtils.join((Object[]) null, null, null));
        assertEquals("", StringUtils.join(new Object[0], null, null));
        assertEquals("v", StringUtils.join(new Object[] {"v"}, null, null));
        assertEquals("xv", StringUtils.join(new Object[] {"v"}, null, fKey));
        assertEquals("12", StringUtils.join(new Object[] {1, 2}, null, null));
        assertEquals("x1,x2", StringUtils.join(new Object[] {1, 2}, ",", fKey));
    }

    /**
     * Test method for
     * {@link StringUtils#join(Map, String, java.util.function.Function)} .
     */
    @Test
    public void testJoinMap() {
        Function<Entry<String, Object>, String> fKey = e -> e.getKey();

        assertNull(StringUtils.join((Map<?, ?>) null, null, null));
        assertEquals("", StringUtils.join(Collections.emptyMap(), null, null));
        assertEquals("k = v", StringUtils.join(Collections.singletonMap("k", "v"), null, null));
        assertEquals("k", StringUtils.join(Collections.singletonMap("k", "v"), null, fKey));
        assertEquals("k1 = 1k2 = 2", StringUtils.join(MapUtils2.newHashMap(Pair.of("k1", 1), Pair.of("k2", 2)), null, null));
        assertEquals("k1,k2", StringUtils.join(MapUtils2.newHashMap(Pair.of("k1", 1), Pair.of("k2", 2)), ",", fKey));
    }

    /**
     * Test method for {@link StringUtils#joinComma} .
     */
    @Test
    public void testJoinComma() {
        assertNull(StringUtils.joinComma((Object[]) null));
        assertEquals("", StringUtils.joinComma(new Object[0]));
        assertEquals("test", StringUtils.joinComma("test"));
        assertEquals("t1, t2", StringUtils.joinComma("t1", "t2"));
        assertEquals("t1, ", StringUtils.joinComma("t1", null));

        assertNull(StringUtils.joinComma((Iterable<?>) null));
        assertEquals("", StringUtils.joinComma(Collections.emptyList()));
        assertEquals("test", StringUtils.joinComma(Arrays.asList("test")));
        assertEquals("t1, t2", StringUtils.joinComma(Arrays.asList("t1", "t2")));
        assertEquals("t1, ", StringUtils.joinComma(Arrays.asList("t1", null)));

        assertNull(StringUtils.joinComma((Iterator<?>) null));
        assertEquals("", StringUtils.joinComma(Collections.emptyIterator()));
        assertEquals("test", StringUtils.joinComma(Arrays.asList("test").iterator()));
        assertEquals("t1, t2", StringUtils.joinComma(Arrays.asList("t1", "t2").iterator()));
        assertEquals("t1, ", StringUtils.joinComma(Arrays.asList("t1", null).iterator()));
    }

    /**
     * Test method for
     * {@link StringUtils#inject(java.lang.String, java.lang.Object...)} .
     */
    @Test
    public void testInject() {
        assertEquals("", StringUtils.inject("", "test"));
        assertEquals("I'll go to the beach this afternoon", StringUtils.inject("I'll go to the {} this {}", "beach", "afternoon"));
        assertEquals("I'll go to the beach this afternoon", StringUtils.inject("I'll go to the {1} this {0}", "afternoon", "beach"));
        assertEquals("I'll go to the beach this afternoon", StringUtils.inject("I'll go to the {1} this {}", "afternoon", "beach"));
        assertEquals("I'll go to the beach this afternoon",
                StringUtils.inject("I'll go to {} {3} {} {2}", "the", "this", "afternoon", "beach"));

        assertEquals("I'll go to the beach this {}", StringUtils.inject("I'll go to the {} this {}", "beach"));
        assertEquals("I'll go to the beach this {1}", StringUtils.inject("I'll go to the {0} this {1}", "beach"));
        assertEquals("I'll go to the beach this afternoon afternoon",
                StringUtils.inject("I'll go to the {1} this {0} {0}", "afternoon", "beach"));
        assertEquals("I'll go to the beach this afternoon", StringUtils.inject("I'll go to the {} this afternoon", "beach", "noon"));

        assertEquals("Test", StringUtils.inject("Test"));
        assertEquals("Test", StringUtils.inject("Test", "test"));
        assertEquals("Test", StringUtils.inject("Test", (Object[]) null));

        assertEquals("I'll go to the {0} {}{}this {test} {12}",
                StringUtils.inject("I'll go to the {{0}} {{}{}}this {test} {12}", "beach", "afternoon"));

        assertEquals("I'll go to the beach this afternoon",
                StringUtils.inject("I'll go to {} {3} {} {2}", "the", "this", "afternoon", "beach"));

        assertEquals("I'll go to {}beach the afternoon{0} {4} {text}",
                StringUtils.inject("I'll go to {{}}{3} {} {2}{{0}} {4} {text}", "the", "this", "afternoon", "beach"));

        try {
            StringUtils.inject(null, "test");
            fail("An exception is expected");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    /**
     * Test method for
     * {@link StringUtils#injectKeys(CharSequence, org.apache.commons.lang3.tuple.Pair...)}
     * .
     */
    @Test
    public void testInjectKeys() {
        assertEquals("", StringUtils.injectKeys("", Pair.of("key", "test")));
        assertEquals("test", StringUtils.injectKeys("test", Pair.of("key", "test")));
        assertEquals("test1 {key0} test2 test1 {key1}",
                StringUtils.injectKeys("{key1} {key0} {key2} {key1} {{key1}}", Pair.of("key1", "test1"), Pair.of("key2", "test2")));
        assertEquals("Test null", StringUtils.injectKeys("Test {key}", Pair.of("key", null)));
        assertEquals("Test null", StringUtils.injectKeys("Test {null}", Pair.of(null, null)));
        assertEquals("Test test", StringUtils.injectKeys("Test {null}", Pair.of(null, "test")));

        assertEquals("Test", StringUtils.injectKeys("Test"));
        assertEquals("Test", StringUtils.injectKeys("Test", Pair.of("key", null)));
        assertEquals("Test", StringUtils.injectKeys("Test", (Pair<String, Object>) null));
        assertEquals("Test", StringUtils.injectKeys("Test", (Pair<String, Object>[]) null));

        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}"));
        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}", Pair.of("key1", null)));
        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}", (Pair<String, Object>) null));
        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}", (Pair<String, Object>[]) null));

        assertException(() -> StringUtils.injectKeys(null, Pair.of("key", "test")), IllegalArgumentException.class,
                "The input char sequence cannot be null");
    }

    /**
     * Test method for
     * {@link StringUtils#injectKeys(Pair, Pair, CharSequence, Entry...)} .
     */
    @Test
    public void testInjectKeysIncludeExclude() {
        assertEquals("", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, "",
                Pair.of("key", "test")));
        assertEquals("test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "test", Pair.of("key", "test")));
        assertEquals("test1 ${key0} test2 test1 ${key1}",
                StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                        "${key1} ${key0} ${key2} ${key1} ${{key1}}", Pair.of("key1", "test1"), Pair.of("key2", "test2")));
        assertEquals("Test null", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", Pair.of("key", null)));
        assertEquals("Test null", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${null}", Pair.of(null, null)));
        assertEquals("Test test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${null}", Pair.of(null, "test")));

        assertEquals("Test",
                StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, "Test"));
        assertEquals("Test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test", Pair.of("key", null)));
        assertEquals("Test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test", (Pair<String, Object>) null));
        assertEquals("Test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test", (Pair<String, Object>[]) null));

        assertEquals("Test ${key}",
                StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, "Test ${key}"));
        assertEquals("Test ${key}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", Pair.of("key1", null)));
        assertEquals("Test ${key}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", (Pair<String, Object>) null));
        assertEquals("Test ${key}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", (Pair<String, Object>[]) null));

        assertException(() -> StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, null,
                Pair.of("key", "test")), IllegalArgumentException.class, "The input char sequence cannot be null");
    }

    /**
     * Test method for
     * {@link StringUtils#injectKeys(Pair, Pair, CharSequence, Map)} .
     */
    @Test
    public void testInjectKeysMap() {
        assertEquals("", StringUtils.injectKeys("", Collections.singletonMap("key", "test")));
        assertEquals("test", StringUtils.injectKeys("test", Collections.singletonMap("key", "test")));
        assertEquals("test1 {key0} test2 test1 {key1}", StringUtils.injectKeys("{key1} {key0} {key2} {key1} {{key1}}",
                MapUtils2.newHashMap(Pair.of("key1", "test1"), Pair.of("key2", "test2"))));
        assertEquals("Test null", StringUtils.injectKeys("Test {key}", Collections.singletonMap("key", null)));
        assertEquals("Test null", StringUtils.injectKeys("Test {null}", Collections.singletonMap(null, null)));
        assertEquals("Test test", StringUtils.injectKeys("Test {null}", Collections.singletonMap(null, "test")));

        assertEquals("Test", StringUtils.injectKeys("Test", Collections.emptyMap()));
        assertEquals("Test", StringUtils.injectKeys("Test", Collections.singletonMap("key", null)));
        assertEquals("Test", StringUtils.injectKeys("Test", (Map<String, Object>) null));

        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}", Collections.emptyMap()));
        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}", Collections.singletonMap("key1", null)));
        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}", (Map<String, Object>) null));

        assertException(() -> StringUtils.injectKeys(null, Collections.singletonMap("key", "test")), IllegalArgumentException.class,
                "The input char sequence cannot be null");
    }

    /**
     * Test method for
     * {@link StringUtils#injectKeys(Pair, Pair, CharSequence, Entry...)} .
     */
    @Test
    public void testInjectKeysMapIncludeExclude() {
        assertEquals("", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, "",
                Collections.singletonMap("key", "test")));
        assertEquals("test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "test", Collections.singletonMap("key", "test")));
        assertEquals("test1 ${key0} test2 test1 ${key1} ${{key1} test1}",
                StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                        "${key1} ${key0} ${key2} ${key1} ${{key1}} ${{key1} ${key1}}",
                        MapUtils2.newHashMap(Pair.of("key1", "test1"), Pair.of("key2", "test2"))));
        assertEquals("Test null", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", Collections.singletonMap("key", null)));
        assertEquals("Test null", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${null}", Collections.singletonMap(null, null)));
        assertEquals("Test test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${null}", Collections.singletonMap(null, "test")));

        assertEquals("Test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test", Collections.emptyMap()));
        assertEquals("Test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test", Collections.singletonMap("key", null)));
        assertEquals("Test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test", (Map<String, Object>) null));

        assertEquals("Test ${key}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", Collections.emptyMap()));
        assertEquals("Test ${key}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", Collections.singletonMap("key1", null)));
        assertEquals("Test ${key}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", (Map<String, Object>) null));

        assertException(() -> StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, null,
                Collections.singletonMap("key", "test")), IllegalArgumentException.class, "The input char sequence cannot be null");
        assertException(
                () -> StringUtils.injectKeys(null, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, "test",
                        Collections.singletonMap("key", "test")),
                IllegalArgumentException.class, "The include and exclude parameters cannot be null");
        assertException(
                () -> StringUtils.injectKeys(Pair.of(null, "}"), StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, "test",
                        Collections.singletonMap("key", "test")),
                IllegalArgumentException.class, "The include and exclude values cannot be null");
    }

    /**
     * Test method for
     * {@link StringUtils#injectKeys(CharSequence, java.util.Properties)} .
     */
    @Test
    public void testInjectKeysProperties() {
        Properties properties1 = new Properties();
        properties1.setProperty("key", "test");

        assertEquals("", StringUtils.injectKeys("", properties1));
        assertEquals("test", StringUtils.injectKeys("test", properties1));

        Properties properties2 = new Properties();
        properties2.setProperty("key1", "test1");
        properties2.setProperty("key2", "test2");

        assertEquals("test1 {key0} test2 test1 {key1}", StringUtils.injectKeys("{key1} {key0} {key2} {key1} {{key1}}", properties2));

        assertEquals("Test", StringUtils.injectKeys("Test", new Properties()));
        assertEquals("Test", StringUtils.injectKeys("Test", (Properties) null));

        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}", new Properties()));
        assertEquals("Test {key}", StringUtils.injectKeys("Test {key}", (Properties) null));

        assertException(() -> StringUtils.injectKeys(null, properties1), IllegalArgumentException.class,
                "The input char sequence cannot be null");
    }

    /**
     * Test method for
     * {@link StringUtils#injectKeys(Pair, Pair, CharSequence, Properties)} .
     */
    @Test
    public void testInjectKeysPropertiesIncludeExclude() {
        Properties properties1 = new Properties();
        properties1.setProperty("key", "test");

        assertEquals("",
                StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, "", properties1));
        assertEquals("test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "test", properties1));

        Properties properties2 = new Properties();
        properties2.setProperty("key1", "test1");
        properties2.setProperty("key2", "test2");

        assertEquals("test1 ${key0} test2 test1 ${key1}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES,
                StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, "${key1} ${key0} ${key2} ${key1} ${{key1}}", properties2));

        assertEquals("Test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test", new Properties()));
        assertEquals("Test", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test", (Properties) null));

        assertEquals("Test ${key}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", new Properties()));
        assertEquals("Test ${key}", StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES,
                "Test ${key}", (Properties) null));

        assertException(() -> StringUtils.injectKeys(StringUtils.INCLUDE_DOLLAR_CURLY_BRACES, StringUtils.EXCLUDE_DOLLAR_CURLY_BRACES, null,
                properties1), IllegalArgumentException.class, "The input char sequence cannot be null");

        assertException(() -> StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${", "}"), "${test}", properties1),
                IllegalArgumentException.class, "The exclude values cannot be equal to include operators");

        assertException(() -> StringUtils.injectKeys(Pair.of("$(", "}"), Pair.of("${", "}"), "${test}", properties1),
                IllegalArgumentException.class, "The exclude values cannot be equal to include operators");

        assertException(() -> StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("$<", ">"), "${test}", properties1),
                IllegalArgumentException.class, "The exclude values must contain include operators");

        assertException(() -> StringUtils.injectKeys(Pair.of("${{", "}"), Pair.of("${", ">"), "${test}", properties1),
                IllegalArgumentException.class, "The exclude values must contain include operators");

        assertException(() -> StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", ">"), "${test}", properties1),
                IllegalArgumentException.class, "The exclude values must contain include operators");
    }

    /**
     * Test method for {@link StringUtils#format} .
     */
    @Test
    public void testFormat() {
        final Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        Object[] parameters = new Object[] {"param1", "param2"};
        Object[] arguments = new Object[] {1.025f, "arg2", "arg3"};

        assertEquals("1.025 'param1' 'param2' '1.02' 'param1' 'arg2' 'arg3'",
                StringUtils.format("%s '%s*' '%s*' '%1$.2f' '%1$s*' '%s' '%s'", parameters, arguments));

        assertEquals("1.025 'param1' 'param2' '1,02' 'param1' 'arg2' 'arg3'",
                StringUtils.format(Locale.FRENCH, "%s '%s*' '%s*' '%1$.2f' '%1$s*' '%s' '%s'", parameters, arguments));

        Locale.setDefault(defaultLocale);
    }

    /**
     * Test method for {@link StringUtils#prepareFormat} .
     */
    @Test
    public void testPrepareFormat() {
        assertEquals("e = %2$+10.4f %3$11d %1$2d", StringUtils.prepareFormat("e = %+10.4f %11d %2d*", 1, 2).toString());

        assertEquals("e = %2$+10.4f %3$11d%1$2d %4$d", StringUtils.prepareFormat("e = %+10.4f %11d%2d* %$d", 1, 3).toString());

        assertEquals("e = %1$+10.4f   1$s", StringUtils.prepareFormat("e = %+10.4f %s %s* %s1$s", 0, 1).toString());

        assertEquals("e = %2$+10.4f %2$s %1$s %3$s1$s", StringUtils.prepareFormat("e = %1$+10.4f %s %s* %s1$s", 1, 2).toString());

        assertEquals("e = %1$+10.4f  ", StringUtils.prepareFormat("e = %+10.4f %11222d %2d*", 0, 1).toString());

        assertEquals("e = %1$+10.4f  ", StringUtils.prepareFormat("e = %+10.4f %2$11d %1$2d*", 0, 1).toString());

        assertEquals("e = %1$+10.4f  %1$* ", StringUtils.prepareFormat("e = %+10.4f %2$11d %1$* ", 0, 1).toString());

        assertEquals("Duke's Birthday: %2$tb %2$te, %2$tY",
                StringUtils.prepareFormat("Duke's Birthday: %1$tb %1$te, %1$tY", 1, 3).toString());
        assertEquals("Duke's Birthday: %2$tm %2$<te,%3$<TY",
                StringUtils.prepareFormat("Duke's Birthday: %1$tm %<te,%<TY", 1, 3).toString());

        byte[] authorized = new byte[] {32, 35};

        for (int i = AsciiUtils.MIN; i <= AsciiUtils.MAX; i++) {
            String ch = String.valueOf((char) i);
            String format = StringUtils.prepareFormat("%" + ch, 0, 1).toString();
            if (AsciiUtils.IS_ALPHA.test(i) || i == '%') {
                assertEquals("%1$" + ch, format);
            } else if (Arrays.binarySearch(authorized, (byte) i) == -1) {
                assertNotEquals("%1$" + ch, format);
            }
        }
    }

    /**
     * Test method for {@link StringUtils#replaceQuotes(String)} .
     */
    @Test
    public void testReplaceQuotes() {
        assertNull(StringUtils.replaceQuotes(null));
        assertEquals("", StringUtils.replaceQuotes(""));
        assertEquals("t\"o\"t\"\"o", StringUtils.replaceQuotes("t'o't''o"));
        assertEquals("\\\"t\"o\"t\"\"o", StringUtils.replaceQuotes("\\'t\'o't''o"));
    }

    /**
     * Test method for {@link StringUtils#concat(Object...)}
     */
    @Test
    public void testConcat() {
        assertEquals("", StringUtils.concat());
        assertEquals("null", StringUtils.concat((Object) null));
        assertEquals("null test nulltest", StringUtils.concat(null, " test ", null, "test"));

        String lettersNumbers = "abcdefghijklmnopqrstuvwxyz0123456789";

        assertEquals(lettersNumbers + lettersNumbers, StringUtils.concat(lettersNumbers, lettersNumbers));

        assertException(() -> StringUtils.concat((Object[]) null), NullPointerException.class, "objects array cannot be null");
    }
}
