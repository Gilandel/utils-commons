/*
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
package fr.landel.utils.commons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Utility class to manage strings.
 *
 * @since Nov 27, 2015
 * @author Gilles Landel
 *
 */
public final class StringUtils extends StringFormatUtils {

    /**
     * The comma separator to join (for readability)
     */
    public static final String SEPARATOR_COMMA = ", ";

    /**
     * The semicolon separator to join (for readability)
     */
    public static final String SEPARATOR_SEMICOLON = "; ";

    /**
     * The equal separator to join (for readability)
     */
    public static final String SEPARATOR_EQUAL = " = ";

    private static final String BRACE_OPEN = "{";
    private static final String BRACE_CLOSE = "}";
    private static final String DOLLAR_BRACE_OPEN = "${";
    private static final String BRACE_OPEN_EXCLUDE = "{{";
    private static final String BRACE_CLOSE_EXCLUDE = "}}";
    private static final String DOLLAR_BRACE_OPEN_EXCLUDE = "${{";
    private static final String BRACES = "{}";

    private static final String BRACE_OPEN_TMP = "[#STR_UT_TMP#[";
    private static final String BRACE_CLOSE_TMP = "]#STR_UT_TMP#]";
    private static final String TEMP_REPLACEMENT = "___ST_REPLACEMENT___";

    public static final Pair<String, String> INCLUDE_CURLY_BRACES = Pair.of(BRACE_OPEN, BRACE_CLOSE);
    public static final Pair<String, String> EXCLUDE_CURLY_BRACES = Pair.of(BRACE_OPEN_EXCLUDE, BRACE_CLOSE_EXCLUDE);

    public static final Pair<String, String> INCLUDE_DOLLAR_CURLY_BRACES = Pair.of(DOLLAR_BRACE_OPEN, BRACE_CLOSE);
    public static final Pair<String, String> EXCLUDE_DOLLAR_CURLY_BRACES = Pair.of(DOLLAR_BRACE_OPEN_EXCLUDE, BRACE_CLOSE_EXCLUDE);

    private static final int ENSURE_CAPACITY = 16;

    private static final int BUFFER_CAPACITY = 64;
    private static final char[] NULL_CHARS = {'n', 'u', 'l', 'l'};

    /**
     * Hidden constructor.
     */
    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the char sequence if not empty and null otherwise.
     * 
     * @param cs
     *            The CharSequence to check, may be null
     * @param <C>
     *            Type of the char sequence
     * @return a char sequence or null
     */
    public static <C extends CharSequence> C nullIfEmpty(final C cs) {
        if (isNotEmpty(cs)) {
            return cs;
        }
        return null;
    }

    /**
     * Get the char sequence if not null and not empty and the default one
     * otherwise.
     * 
     * @param cs
     *            The CharSequence to check, may be null
     * @param defaultCS
     *            The default char sequence
     * @param <C>
     *            Type of the char sequence
     * @return a char sequence
     */
    public static <C extends CharSequence> C defaultIfEmpty(final C cs, final C defaultCS) {
        if (isNotEmpty(cs)) {
            return cs;
        }
        return defaultCS;
    }

    /**
     * Get the char sequence if not null and the default one otherwise.
     * 
     * @param cs
     *            The CharSequence to check, may be null
     * @param defaultCS
     *            The default char sequence
     * @param <C>
     *            Type of the char sequence
     * @return a char sequence
     */
    public static <C extends CharSequence> C defaultIfNull(final C cs, final C defaultCS) {
        if (cs != null) {
            return cs;
        }
        return defaultCS;
    }

    /**
     * Get the toString if not null and the default one otherwise.
     * 
     * @param obj
     *            The object to check, may be null
     * @param defaultStr
     *            The default string
     * @return a string
     */
    public static String toStringOrDefaultIfNull(final Object obj, final String defaultStr) {
        if (obj != null) {
            return obj.toString();
        }
        return defaultStr;
    }

    /**
     * Get the string part between the separator at the index position. The
     * index starts from the left at 0, or at -1 from the right. If the index is
     * over the number of split parts, the method returns an empty string.
     * 
     * <pre>
     * StringUtils.substring("test1::test2:test3::test4", "::", 0) =&gt; "test1"
     * StringUtils.substring("test1::test2:test3::test4", "::", 1) =&gt; "test2:test3"
     * StringUtils.substring("test1::test2:test3::test4", "::", 2) =&gt; "test4"
     * StringUtils.substring("test1::test2:test3::test4", "::", -1) =&gt; "test4"
     * StringUtils.substring("test1::test2:test3::test4", "::", -2) =&gt; "test2:test3"
     * StringUtils.substring("test1::test2:test3::test4", "::", -3) =&gt; "test1"
     * StringUtils.substring("test1::test2:test3::test4", "::", 100) =&gt; ""
     * StringUtils.substring("test1::test2:test3::test4", "::", -100) =&gt; ""
     * </pre>
     * 
     * @param str
     *            The input string
     * @param separator
     *            The string separator
     * @param index
     *            The index position
     * @return The sub-string
     */
    public static String substring(final String str, final String separator, final int index) {
        if (index > -1) {
            return substring(str, separator, index, index + 1);
        } else {
            return substring(str, separator, index, index - 1);
        }
    }

    /**
     * Get the string part between the separator at the index position. The
     * index starts from the left at 0, or at -1 from the right. If the from
     * index is over the number of split parts, the method returns an empty
     * string (the to index isn't checked).
     * 
     * <pre>
     * StringUtils.substring("test1::test2:test3::test4", "::", 0, 1); // =&gt; "test1""
     * StringUtils.substring("test1::test2:test3::test4", "::", 1, 2); // =&gt; "test2:test3""
     * StringUtils.substring("test1::test2:test3::test4", "::", 1, 3); // =&gt; "test2:test3::test4""
     * StringUtils.substring("test1::test2:test3::test4", "::", 1, 100); // =&gt; "test2:test3::test4""
     * StringUtils.substring("test1::test2:test3::test4", "::", -1, -2); // =&gt; "test4"
     * StringUtils.substring("test1::test2:test3::test4", "::", -2, -3); // =&gt; "test2:test3"
     * StringUtils.substring("test1::test2:test3::test4", "::", 0, -2); // =&gt; "test1"
     * StringUtils.substring("test1::test2:test3::test4", "::", -3, 0); / =&gt; IllegalArgumentException, from=1, to=0
     * StringUtils.substring("test1::test2:test3::test4", "::", 0, 0); // =&gt; IllegalArgumentException, from=to
     * StringUtils.substring("test1::test2:test3::test4", "::", 0, -3); // =&gt; IllegalArgumentException, from=0, to=0
     * </pre>
     * 
     * @param str
     *            The input string
     * @param separator
     *            The string separator
     * @param from
     *            The from index position (inclusive)
     * @param to
     *            The to index position (exclusive)
     * @return The sub-string
     */
    public static String substring(final String str, final String separator, final int from, final int to) {
        if (isNotEmpty(str)) {
            if (from == to) {
                throw new IllegalArgumentException("The 'from' index is equal to 'to' index");
            }

            final List<String> strs = splitAsList(str, separator);
            final int size = strs.size();

            if (from >= size || (from < 0 && Math.abs(from) - 1 >= size)) {
                return "";
            } else {
                final StringBuilder stringBuilder = new StringBuilder();

                int end;
                int start;

                if (from > -1) {
                    start = from;
                    if (to > -1) {
                        end = to;
                    } else if (size + to > start) {
                        end = size + to;
                    } else {
                        throw new IllegalArgumentException("The 'to' index is invalid");
                    }
                } else if (to > -1) {
                    start = size + from + 1;
                    end = to;
                } else if (to < from) {
                    if (size + to + 1 < 0) {
                        start = 0;
                    } else {
                        start = size + to + 1;
                    }
                    end = size + from + 1;
                } else {
                    throw new IllegalArgumentException("The 'to' index is invalid");
                }

                if (start >= end) {
                    throw new IllegalArgumentException("The 'from' and 'to' indexes are invalid");
                }

                for (int i = start; i < end && i < size; i++) {
                    stringBuilder.append(strs.get(i));
                    if (i < end - 1 && i < size - 1) {
                        stringBuilder.append(separator);
                    }
                }

                return stringBuilder.toString();
            }
        }
        return str;
    }

    private static List<String> splitAsList(final String str, final String separator) {
        int pos = 0;
        int pPos = 0;
        final int separatorLength = separator.length();
        final List<String> strs = new ArrayList<>();
        while ((pos = str.indexOf(separator, pPos)) > -1) {
            strs.add(str.substring(pPos, pos));
            pPos = pos + separatorLength;
        }
        strs.add(str.substring(pPos, str.length()));
        return strs;
    }

    /**
     * Replace the part of a string between two bounds
     * 
     * <pre>
     * StringUtils.replace("I'll go to the beach this afternoon.", "theater", 15, 20)
     * // =&gt; "I'll go to the theater this afternoon."
     * </pre>
     * 
     * @param string
     *            The input string
     * @param replacement
     *            The replacement string
     * @param start
     *            The start position (exclusive)
     * @param end
     *            The end position (inclusive)
     * @return The new string
     * @throws IllegalArgumentException
     *             If input string is null or empty. If replacement string is
     *             null. If the start is lower than 0. If the start is lower
     *             than the end. If the end is greater than the string length.
     */
    public static String replace(final String string, final String replacement, final int start, final int end) {
        if (isEmpty(string)) {
            throw new IllegalArgumentException("The input string cannot be empty");
        } else if (replacement == null) {
            throw new IllegalArgumentException("The replacement string cannot be null");
        } else if (start < 0) {
            throw new IllegalArgumentException("The start parameter must be greated than or equal to 0");
        } else if (end > string.length()) {
            throw new IllegalArgumentException("The end parameter must be lower than or equal to the length of string");
        } else if (start >= end) {
            throw new IllegalArgumentException("The start parameter must be lower than the end");
        }

        String part1 = "";
        if (start > 0) {
            part1 = string.substring(0, start);
        }
        String part2 = "";
        if (end < string.length()) {
            part2 = string.substring(end);
        }

        return part1 + replacement + part2;
    }

    /**
     * Converts the char sequence in char array
     * 
     * @param sequence
     *            the input sequence
     * @return the array
     */
    public static char[] toChars(final CharSequence sequence) {
        Objects.requireNonNull(sequence);

        final int length = sequence.length();
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = sequence.charAt(i);
        }
        return chars;
    }

    /**
     * Replaces single quotes by double quotes.
     * 
     * @param str
     *            The input {@link String}
     * @return the new sequence
     */
    public static String replaceQuotes(final String str) {
        return replaceChars(str, '\'', '\"');
    }

    /**
     * <p>
     * Joins the elements of the provided iterable into a single String
     * containing the provided list of elements.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. A {@code null} separator
     * is the same as an empty String (""). The formatter if provided should
     * support {@code null} value. By default the formatter used the function
     * {@link String#valueOf}.
     * </p>
     *
     * <pre>
     * StringUtils.join(null, *, *)                = null
     * StringUtils.join([], *, *)                  = ""
     * StringUtils.join([null], *, null)           = "null"
     * StringUtils.join(["a", "b", "c"], "--", null)  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null, StringUtils::upperCase)  = "ABC"
     * StringUtils.join(["a", "b", "c"], "", StringUtils::upperCase)    = "ABC"
     * StringUtils.join([null, "", "a"], ",", StringUtils::upperCase)   = ",,A"
     * </pre>
     * 
     * @param iterable
     *            the {@link Iterable} providing the values to join together,
     *            may be null
     * @param separator
     *            the separator character to use, null treated as ""
     * @param formatter
     *            the formatter to stringify each element, null treated as
     *            {@link String#valueOf}
     * @param <T>
     *            the type of iterable element
     * @return the joined String, {@code null} if null array input.
     */
    public static <T> String join(final Iterable<T> iterable, final String separator, final Function<T, String> formatter) {
        if (iterable == null) {
            return null;
        }

        return join(iterable.iterator(), separator, formatter);
    }

    /**
     * <p>
     * Joins the elements of the provided iterator into a single String
     * containing the provided list of elements.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. A {@code null} separator
     * is the same as an empty String (""). The formatter if provided should
     * support {@code null} value. By default the formatter used the function
     * {@link String#valueOf}.
     * </p>
     *
     * <pre>
     * StringUtils.join(null, *, *)                = null
     * StringUtils.join([], *, *)                  = ""
     * StringUtils.join([null], *, null)           = "null"
     * StringUtils.join(["a", "b", "c"], "--", null)  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null, StringUtils::upperCase)  = "ABC"
     * StringUtils.join(["a", "b", "c"], "", StringUtils::upperCase)    = "ABC"
     * StringUtils.join([null, "", "a"], ",", StringUtils::upperCase)   = ",,A"
     * </pre>
     * 
     * @param iterator
     *            the {@link Iterator} providing the values to join together,
     *            may be null
     * @param separator
     *            the separator character to use, null treated as ""
     * @param formatter
     *            the formatter to stringify each element, null treated as
     *            {@link String#valueOf}
     * @param <T>
     *            the type of iterator element
     * @return the joined String, {@code null} if null array input.
     */
    public static <T> String join(final Iterator<T> iterator, final String separator, final Function<T, String> formatter) {
        if (iterator == null) {
            return null;
        }

        final String sep = ObjectUtils.defaultIfNull(separator, EMPTY);
        final Function<T, String> frmt = ObjectUtils.defaultIfNull(formatter, String::valueOf);

        final StringBuilder buf = new StringBuilder();

        while (iterator.hasNext()) {
            if (buf.length() > 0) {
                buf.append(sep);
            }
            buf.append(frmt.apply(iterator.next()));
        }
        return buf.toString();
    }

    /**
     * <p>
     * Joins the elements of the provided array into a single String containing
     * the provided list of elements.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. A {@code null} separator
     * is the same as an empty String (""). The formatter if provided should
     * support {@code null} value. By default the formatter used the function
     * {@link String#valueOf}.
     * </p>
     *
     * <pre>
     * StringUtils.join(null, *, *)                = null
     * StringUtils.join([], *, *)                  = ""
     * StringUtils.join([null], *, null)           = "null"
     * StringUtils.join(["a", "b", "c"], "--", null)  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null, StringUtils::upperCase)  = "ABC"
     * StringUtils.join(["a", "b", "c"], "", StringUtils::upperCase)    = "ABC"
     * StringUtils.join([null, "", "a"], ",", StringUtils::upperCase)   = ",,A"
     * </pre>
     *
     * @param array
     *            the array of values to join together, may be null
     * @param separator
     *            the separator character to use, null treated as ""
     * @param formatter
     *            the formatter to stringify each element, null treated as
     *            {@link String#valueOf}
     * @param <T>
     *            the type of array element
     * @return the joined String, {@code null} if null array input.
     */
    public static <T> String join(final T[] array, final String separator, final Function<T, String> formatter) {
        return join(array, separator, 0, array == null ? 0 : array.length, formatter);
    }

    /**
     * <p>
     * Joins the elements of the provided array into a single String containing
     * the provided list of elements.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. A {@code null} separator
     * is the same as an empty String (""). The formatter if provided should
     * support {@code null} value. By default the formatter used the function
     * {@link String#valueOf}.
     * </p>
     *
     * <pre>
     * StringUtils.join(null, *, *, *, *)                = null
     * StringUtils.join([], *, *, *, *)                  = ""
     * StringUtils.join([null], *, *, *, null)           = "null"
     * StringUtils.join(["a", "b", "c"], "--", 0, 3, null)  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], "--", 1, 3, null)  = "b--c"
     * StringUtils.join(["a", "b", "c"], "--", 2, 3, StringUtils::upperCase)  = "C"
     * StringUtils.join(["a", "b", "c"], "--", 2, 2, StringUtils::upperCase)  = ""
     * StringUtils.join(["a", "b", "c"], null, 0, 3, StringUtils::upperCase)  = "ABC"
     * StringUtils.join(["a", "b", "c"], "", 0, 3, StringUtils::upperCase)    = "ABC"
     * StringUtils.join([null, "", "a"], ",", 0, 3, StringUtils::upperCase)   = "null,,A"
     * </pre>
     *
     * @param array
     *            the array of values to join together, may be null
     * @param separator
     *            the separator character to use, null treated as ""
     * @param startIndex
     *            the first index to start joining from.
     * @param endIndex
     *            the index to stop joining from (exclusive).
     * @param formatter
     *            the formatter to stringify each element, null treated as
     *            {@link String#valueOf}
     * @param <T>
     *            the type of array element
     * @return the joined String, {@code null} if null array input; or the empty
     *         string if {@code endIndex - startIndex <= 0}. The number of
     *         joined entries is given by {@code endIndex - startIndex}
     * @throws ArrayIndexOutOfBoundsException
     *             if<br>
     *             {@code startIndex < 0} or <br>
     *             {@code startIndex >= array.length()} or <br>
     *             {@code endIndex < 0} or <br>
     *             {@code endIndex > array.length()}
     */
    public static <T> String join(final T[] array, final String separator, final int startIndex, final int endIndex,
            final Function<T, String> formatter) {
        if (array == null) {
            return null;
        }

        final String sep = ObjectUtils.defaultIfNull(separator, EMPTY);
        final Function<T, String> frmt = ObjectUtils.defaultIfNull(formatter, String::valueOf);

        // endIndex - startIndex > 0: Len = NofStrings *(len(firstString) +
        // len(separator))
        // (Assuming that all Strings are roughly equally long)
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }

        final StringBuilder buf = new StringBuilder(noOfItems * ENSURE_CAPACITY);

        for (int i = startIndex; i < endIndex; ++i) {
            if (i > startIndex) {
                buf.append(sep);
            }
            if (array[i] != null) {
                buf.append(frmt.apply(array[i]));
            }
        }
        return buf.toString();
    }

    /**
     * <p>
     * Joins the elements of the provided map into a single String containing
     * the provided list of elements. On each map entry, the formatter is
     * applied.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. A {@code null} separator
     * is the same as an empty String (""). If no formatter is specified, the
     * format used is the following "key = value". Null key or value within the
     * map are represented by the word "null".
     * </p>
     *
     * <pre>
     * StringUtils.join(null, ",", null) = null
     * StringUtils.join(Collections.emptyMap(), ",", null) = ""
     * StringUtils.join(Collections.singletonMap("key", "value"), ",", null) = "key = value"
     * StringUtils.join(Collections.singletonMap("key", "value"), ",", e -&gt; e.getKey()) = "key"
     * StringUtils.join(MapUtils2.newHashMap(Pair.of("k1", 1), Pair.of("k2", 2)), ", ", null) = "k1 = 1, k2 = 2"
     * </pre>
     *
     * @param map
     *            the map of entries to join together, may be null
     * @param separator
     *            the separator character to use, null treated as ""
     * @param formatter
     *            the formatter to stringify each entry, null treated as
     *            {@code "key = value"}
     * @param <K>
     *            the type of each key
     * @param <V>
     *            the type of each value
     * @return the joined String, {@code null} if null map input
     */
    public static <K, V> String join(final Map<K, V> map, final String separator, final Function<Entry<K, V>, String> formatter) {
        if (map == null) {
            return null;
        }

        final String sep = ObjectUtils.defaultIfNull(separator, EMPTY);
        final Function<Entry<K, V>, String> frmt = ObjectUtils.defaultIfNull(formatter,
                e -> new StringBuilder().append(e.getKey()).append(SEPARATOR_EQUAL).append(e.getValue()).toString());

        final StringBuilder buf = new StringBuilder(map.size() * ENSURE_CAPACITY);

        for (Entry<K, V> entry : map.entrySet()) {
            if (buf.length() > 0) {
                buf.append(sep);
            }
            buf.append(frmt.apply(entry));
        }
        return buf.toString();
    }

    /**
     * <p>
     * Joins the elements of the provided array into a single String containing
     * the provided list of elements. Each element is separated by a comma
     * followed by a space.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. A {@code null} separator
     * is the same as an empty String (""). Null objects or empty strings within
     * the array are represented by empty strings.
     * </p>
     *
     * <pre>
     * StringUtils.join(null)            = null
     * StringUtils.join([])              = ""
     * StringUtils.join([null])          = ""
     * StringUtils.join(["a"])           = "a"
     * StringUtils.join(["a", "b", "c"]) = "a, b, c"
     * </pre>
     *
     * @param elements
     *            the array of values to join together, may be null
     * @param <T>
     *            the type of each element
     * @return the joined String, {@code null} if null array input
     */
    @SafeVarargs
    public static <T> String joinComma(final T... elements) {
        return join(elements, SEPARATOR_COMMA);
    }

    /**
     * <p>
     * Joins the elements of the provided {@code Iterable} into a single String
     * containing the provided elements.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. The comma followed by a
     * space is used as separator (", ").
     * </p>
     *
     * <p>
     * See the examples here: {@link #join(Iterable, String)}.
     * </p>
     *
     * @param iterable
     *            the {@link Iterable} providing the values to join together,
     *            may be null
     * @param <T>
     *            the type of each element
     * @return the joined String, {@code null} if null iterator input
     */
    public static <T> String joinComma(final Iterable<T> iterable) {
        if (iterable == null) {
            return null;
        }
        return joinComma(iterable.iterator());
    }

    /**
     * <p>
     * Joins the elements of the provided {@code Iterator} into a single String
     * containing the provided elements.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. The comma followed by a
     * space is used as separator (", ").
     * </p>
     *
     * <p>
     * See the examples here: {@link #join(Iterator, String)}.
     * </p>
     *
     * @param iterator
     *            the {@link Iterator} providing the values to join together,
     *            may be null
     * @param <T>
     *            the type of each element
     * @return the joined String, {@code null} if null iterator input
     */
    public static <T> String joinComma(final Iterator<T> iterator) {
        return join(iterator, SEPARATOR_COMMA);
    }

    /**
     * Injects all arguments in the specified char sequence. The arguments are
     * injected by replacement of the braces. If no index is specified between
     * braces, an internal index is created and the index is automatically
     * incremented. The index starts from 0. To exclude braces, just double them
     * (like {{0}} will return {0}). If number greater than arguments number are
     * specified, they are ignored.
     * 
     * <p>
     * precondition: {@code charSequence} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * StringUtils.inject("", "test"); // =&gt; ""
     * 
     * StringUtils.inject("I'll go to the {} this {}", "beach", "afternoon");
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.inject("I'll go to the {1} this {0}", "afternoon", "beach");
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.inject("I'll go to the {1} this {}", "afternoon", "beach");
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.inject("I'll go to {} {3} {} {2}", "the", "this", "afternoon", "beach");
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.inject("I'll go to {{}}{3} {} {2}{{0}} {4} {text}", "the", "this", "afternoon", "beach");
     * // =&gt; "I'll go to {}beach the afternoon{0} {4} {text}"
     * </pre>
     * 
     * @param charSequence
     *            the input char sequence
     * @param arguments
     *            the arguments to inject
     * @return the result with replacements
     */
    public static String inject(final CharSequence charSequence, final Object... arguments) {
        if (charSequence == null) {
            throw new IllegalArgumentException("The input char sequence cannot be null");
        } else if (isEmpty(charSequence) || arguments == null || arguments.length == 0) {
            return charSequence.toString();
        }

        final StringBuilder output = new StringBuilder(charSequence);

        // if no brace, just returns the string
        if (output.indexOf(BRACE_OPEN) < 0) {
            return output.toString();
        }

        // replace the excluded braces by a temporary string
        replaceBrace(output, BRACE_OPEN_EXCLUDE, BRACE_OPEN_TMP);
        replaceBrace(output, BRACE_CLOSE_EXCLUDE, BRACE_CLOSE_TMP);

        // replace the braces without index by the arguments
        int i = 0;
        int index = 0;
        while ((index = output.indexOf(BRACES, index)) > -1 && i < arguments.length) {
            output.replace(index, index + BRACES.length(), String.valueOf(arguments[i++]));
            index += BRACES.length();
        }

        // replace braces with index by the arguments
        int len;
        String param;
        for (i = 0; i < arguments.length; ++i) {
            index = 0;
            param = new StringBuilder(BRACE_OPEN).append(i).append(BRACE_CLOSE).toString();
            len = param.length();
            while ((index = output.indexOf(param, index)) > -1) {
                output.replace(index, index + len, String.valueOf(arguments[i]));
                index += len;
            }
        }

        // replace the temporary brace by the simple brace
        replaceBrace(output, BRACE_OPEN_TMP, BRACE_OPEN);
        replaceBrace(output, BRACE_CLOSE_TMP, BRACE_CLOSE);

        return output.toString();
    }

    /**
     * Injects all arguments in the specified char sequence. The arguments are
     * injected by replacement of keys between braces. To exclude keys, just
     * double braces (like {{key}} will return {key}). If some keys aren't
     * found, they are ignored.
     * 
     * <p>
     * precondition: {@code charSequence} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "", Pair.of("key", "test"));
     * // =&gt; ""
     * 
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "I'll go to the {where} this {when}", Pair.of("where", "beach"),
     *         Pair.of("when", "afternoon"));
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "I'll go to {key}{{key}}{key}", Pair.of("key", "beach"));
     * // =&gt; "I'll go to beach{key}beach"
     * </pre>
     * 
     * @param include
     *            the characters that surround the property key to replace
     * @param exclude
     *            the characters that surround the property key to exclude of
     *            replacement
     * @param charSequence
     *            the input char sequence
     * @param arguments
     *            the pairs to inject
     * @param <T>
     *            the type arguments
     * @return the result with replacements
     */
    @SafeVarargs
    public static <T extends Map.Entry<String, Object>> String injectKeys(final Pair<String, String> include,
            final Pair<String, String> exclude, final CharSequence charSequence, final T... arguments) {

        checkParamsInjectKeys(include, exclude, charSequence);

        if (isEmpty(charSequence) || arguments == null || arguments.length == 0) {
            return charSequence.toString();
        }

        final StringBuilder output = new StringBuilder(charSequence);

        // if no brace, just returns the string
        if (output.indexOf(include.getLeft()) < 0) {
            return output.toString();
        }

        for (T argument : arguments) {
            if (argument != null) {
                int index = 0;

                final String key = new StringBuilder(include.getLeft()).append(argument.getKey()).append(include.getRight()).toString();
                final String keyExclude = new StringBuilder(exclude.getLeft()).append(argument.getKey()).append(exclude.getRight())
                        .toString();

                // replace the excluded braces by a temporary string
                while ((index = output.indexOf(keyExclude, index)) > -1) {
                    output.replace(index, index + keyExclude.length(), TEMP_REPLACEMENT);
                }

                // replace the key by the argument
                index = 0;
                while ((index = output.indexOf(key, index)) > -1) {
                    output.replace(index, index + key.length(), String.valueOf(argument.getValue()));
                }

                // replace the temporary string by the excluded braces
                index = 0;
                while ((index = output.indexOf(TEMP_REPLACEMENT, index)) > -1) {
                    output.replace(index, index + TEMP_REPLACEMENT.length(), key);
                }
            }
        }

        return output.toString();
    }

    private static <T extends Map.Entry<String, Object>> void checkParamsInjectKeys(final Pair<String, String> include,
            final Pair<String, String> exclude, final CharSequence charSequence) {
        if (charSequence == null) {
            throw new IllegalArgumentException("The input char sequence cannot be null");
        } else if (ObjectUtils.anyNull(include, exclude)) {
            throw new IllegalArgumentException("The include and exclude parameters cannot be null");
        } else if (ObjectUtils.anyNull(include.getLeft(), include.getRight(), exclude.getLeft(), exclude.getRight())) {
            throw new IllegalArgumentException("The include and exclude values cannot be null");
        } else if (exclude.getLeft().equals(include.getLeft()) || exclude.getRight().equals(include.getRight())) {
            throw new IllegalArgumentException("The exclude values cannot be equal to include operators");
        } else if (!exclude.getLeft().contains(include.getLeft()) || !exclude.getRight().contains(include.getRight())) {
            throw new IllegalArgumentException("The exclude values must contain include operators");
        }
    }

    /**
     * Injects all arguments in the specified char sequence. The arguments are
     * injected by replacement of keys between braces. To exclude keys, just
     * double braces (like {{key}} will return {key}). If some keys aren't
     * found, they are ignored.
     * 
     * <p>
     * precondition: {@code charSequence} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * StringUtils.injectKeys("", Pair.of("key", "test")); // =&gt; ""
     * 
     * StringUtils.injectKeys("I'll go to the {where} this {when}", Pair.of("where", "beach"), Pair.of("when", "afternoon"));
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.injectKeys("I'll go to {key}{{key}}{key}", Pair.of("key", "beach"));
     * // =&gt; "I'll go to beach{key}beach"
     * </pre>
     * 
     * @param charSequence
     *            the input char sequence
     * @param arguments
     *            the pairs to inject
     * @param <T>
     *            the type arguments
     * @return the result with replacements
     */
    @SafeVarargs
    public static <T extends Map.Entry<String, Object>> String injectKeys(final CharSequence charSequence, final T... arguments) {
        return injectKeys(INCLUDE_CURLY_BRACES, EXCLUDE_CURLY_BRACES, charSequence, arguments);
    }

    /**
     * Injects all arguments in the specified char sequence. The arguments are
     * injected by replacement of keys between braces. To exclude keys, just
     * double braces (like {{key}} will return {key}). If some keys aren't
     * found, they are ignored.
     * 
     * <p>
     * precondition: {@code charSequence} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "", Collections.singletonMap("key", "test"));
     * // =&gt; ""
     * 
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "I'll go to the ${where} this {when}",
     *         MapUtils2.newHashMap(Pair.of("where", "beach"), Pair.of("when", "afternoon")));
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "I'll go to ${key}${{key}}${key}",
     *         Collections.singletonMap("key", "beach"));
     * // =&gt; "I'll go to beach${key}beach"
     * </pre>
     * 
     * @param include
     *            the characters that surround the property key to replace
     * @param exclude
     *            the characters that surround the property key to exclude of
     *            replacement
     * @param charSequence
     *            the input char sequence
     * @param arguments
     *            the map of pairs to inject
     * @return the result with replacements
     */
    public static String injectKeys(final Pair<String, String> include, final Pair<String, String> exclude, final CharSequence charSequence,
            final Map<String, Object> arguments) {
        if (charSequence == null) {
            throw new IllegalArgumentException("The input char sequence cannot be null");
        } else if (isEmpty(charSequence) || MapUtils.isEmpty(arguments)) {
            return charSequence.toString();
        }

        return injectKeys(include, exclude, charSequence, arguments.entrySet().toArray(CastUtils.cast(new Map.Entry[arguments.size()])));
    }

    /**
     * Injects all arguments in the specified char sequence. The arguments are
     * injected by replacement of keys between braces. To exclude keys, just
     * double braces (like {{key}} will return {key}). If some keys aren't
     * found, they are ignored.
     * 
     * <p>
     * precondition: {@code charSequence} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * StringUtils.injectKeys("", Collections.singletonMap("key", "test"));
     * // =&gt; ""
     * 
     * StringUtils.injectKeys("I'll go to the {where} this {when}",
     *         MapUtils2.newHashMap(Pair.of("where", "beach"), Pair.of("when", "afternoon")));
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.injectKeys("I'll go to {key}{{key}}{key}", Collections.singletonMap("key", "beach"));
     * // =&gt; "I'll go to beach{key}beach"
     * </pre>
     * 
     * @param charSequence
     *            the input char sequence
     * @param arguments
     *            the map of pairs to inject
     * @return the result with replacements
     */
    public static String injectKeys(final CharSequence charSequence, final Map<String, Object> arguments) {
        return injectKeys(INCLUDE_CURLY_BRACES, EXCLUDE_CURLY_BRACES, charSequence, arguments);
    }

    /**
     * Injects all arguments in the specified char sequence. The arguments are
     * injected by replacement of keys between braces. To exclude keys, just
     * double braces (like {{key}} will return {key}). If some keys aren't
     * found, they are ignored.
     * 
     * <p>
     * precondition: {@code charSequence} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * Properties properties = new Properties();
     * properties.setProperty("where", "beach");
     * properties.setProperty("when", "afternoon");
     * 
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "", properties);
     * // =&gt; ""
     * 
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "I'll go to the ${where} this ${when}", properties);
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.injectKeys(Pair.of("${", "}"), Pair.of("${{", "}}"), "I'll go to ${where}${{where}}${where}", properties);
     * // =&gt; "I'll go to beach${where}beach"
     * </pre>
     * 
     * @param include
     *            the characters that surround the property key to replace
     * @param exclude
     *            the characters that surround the property key to exclude of
     *            replacement
     * @param charSequence
     *            the input char sequence
     * @param properties
     *            the properties to inject
     * @return the result with replacements
     */
    public static String injectKeys(final Pair<String, String> include, final Pair<String, String> exclude, final CharSequence charSequence,
            final Properties properties) {
        if (charSequence == null) {
            throw new IllegalArgumentException("The input char sequence cannot be null");
        } else if (isEmpty(charSequence) || properties == null || properties.isEmpty()) {
            return charSequence.toString();
        }

        return injectKeys(include, exclude, charSequence, properties.entrySet().toArray(CastUtils.cast(new Map.Entry[properties.size()])));
    }

    /**
     * Injects all arguments in the specified char sequence. The arguments are
     * injected by replacement of keys between braces. To exclude keys, just
     * double braces (like {{key}} will return {key}). If some keys aren't
     * found, they are ignored.
     * 
     * <p>
     * precondition: {@code charSequence} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * Properties properties = new Properties();
     * properties.setProperty("where", "beach");
     * properties.setProperty("when", "afternoon");
     * 
     * StringUtils.injectKeys("", properties);
     * // =&gt; ""
     * 
     * StringUtils.injectKeys("I'll go to the {where} this {when}", properties);
     * // =&gt; "I'll go to the beach this afternoon"
     * 
     * StringUtils.injectKeys("I'll go to {where}{{where}}{where}", properties);
     * // =&gt; "I'll go to beach{where}beach"
     * </pre>
     * 
     * @param charSequence
     *            the input char sequence
     * @param properties
     *            the properties to inject
     * @return the result with replacements
     */
    public static String injectKeys(final CharSequence charSequence, final Properties properties) {
        return injectKeys(INCLUDE_CURLY_BRACES, EXCLUDE_CURLY_BRACES, charSequence, properties);
    }

    private static void replaceBrace(final StringBuilder output, final String text, final String replacement) {
        int index = 0;
        while ((index = output.indexOf(text, index)) > -1) {
            output.replace(index, index + text.length(), replacement);
            index += replacement.length();
        }
    }

    /**
     * Concatenate objects. If one object is {@code null}, it's replaced by the
     * word 'null', otherwise calls toString method for each object.
     * 
     * @param objects
     *            the objects to concatenate
     * @return the concatenated String (may be empty if objects array is empty)
     * @throws NullPointerException
     *             if {@code objects} array is {@code null}
     */
    public static String concat(final Object... objects) {
        Objects.requireNonNull(objects, "objects array cannot be null");

        if (objects.length > 0) {
            char[] buf = new char[BUFFER_CAPACITY];
            int pos = 0;
            for (Object object : objects) {
                char[] chars;
                if (object != null) {
                    chars = object.toString().toCharArray();
                } else {
                    chars = NULL_CHARS;
                }

                if (pos + chars.length > buf.length) {
                    char[] newBuf = new char[buf.length + chars.length + ENSURE_CAPACITY];
                    System.arraycopy(buf, 0, newBuf, 0, buf.length);
                    buf = newBuf;
                }
                System.arraycopy(chars, 0, buf, pos, chars.length);
                pos += chars.length;
            }
            return new String(buf, 0, pos);
        } else {
            return StringUtils.EMPTY;
        }
    }
}
