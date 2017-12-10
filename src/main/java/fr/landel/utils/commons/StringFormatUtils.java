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

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import fr.landel.utils.commons.Comparators.BiComparator;

/**
 * This class is an helper to inject parameters into a {@link String} formatter
 * in addition of message arguments.
 *
 * @since Apr 30, 2017
 * @author Gilles
 *
 */
public abstract class StringFormatUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * Empty string
     */
    public static final String EMPTY = "";

    // The regular expression from String#format
    // (just for info, the original regular expression, it's replaced here cause
    // of performance issues)
    // "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])"

    /**
     * the parameter suffix used to detect, if the expression is a standard or a
     * parameter type
     */
    private static final char PARAM_SUFFIX = '*';

    /**
     * Flags in regular expression (sorted for binarySearch)
     */
    private static final char[] FLAGS = StringUtils.toChars(" #(+,-0<\\");

    private static final char PERCENT = '%';
    private static final char PREFIX = PERCENT;
    private static final String PREFIX_PERCENT = "%";
    private static final char DOT = '.';
    private static final char INDEX_SUFFIX = '$';
    private static final char TIME_LOWERCASE = 't';
    private static final char TIME_UPPERCASE = 'T';

    private static final int STATE_NOTHING = 0;
    private static final int STATE_NUMBER = 1;
    private static final int STATE_INDEX = 2;
    private static final int STATE_FLAGS = 4;
    private static final int STATE_INTEGER = 8;
    private static final int STATE_DOT = 16;
    private static final int STATE_DECIMAL = 32;
    private static final int STATE_TIME = 64;
    private static final int STATE_TYPE = 128;
    private static final int STATE_SUFFIX = 256;

    private static final int SHIFT_LEFT = 10;

    /**
     * Returns a formatted string using the specified format string, parameters
     * and arguments.
     * 
     * <p>
     * Examples:
     * </p>
     * 
     * <pre>
     * String before1 = "%s '%s*' '%s*' '%1$.2f' '%1$s*' '%s' '%s'";
     * 
     * // is equal to (if we specify position)
     * 
     * String before2 = "%1$s '%1$s*' '%2$s*' '%1$.2f' '%1$s*' '%3$s' '%4$s'";
     * 
     * // will become with 3 parameters and 2 arguments
     * 
     * String after = "%4$s '%1$s' '%2$s' '%4$.2f' '%1$s' '%5$s' ''";
     * 
     * // Format
     * Object[] parameters = new Object[] {"param1", "param2"};
     * Object[] arguments = new Object[] {1.025f, "arg2", "arg3"};
     * String result = StringUtils.format(before1, parameters, arguments);
     * System.out.println(result);
     * // =&gt; 1.025 'param1' 'param2' '1.02' 'param1' 'arg2' 'arg3'
     * </pre>
     * 
     * @param format
     *            a <a href="../util/Formatter.html#syntax">format string</a>
     * @param parameters
     *            parameters has the same specification as {@code arguments}. In
     *            the {@code format} expression, parameters are suffixed with
     *            character '*'.
     * @param arguments
     *            arguments referenced by the format specifiers in the format
     *            string. If there are more arguments than format specifiers,
     *            the extra arguments are ignored. The number of arguments is
     *            variable and may be zero. The maximum number of arguments is
     *            limited by the maximum dimension of a Java array as defined by
     *            <cite>The Java&trade; Virtual Machine Specification</cite>.
     *            The behaviour on a {@code null} argument depends on the
     *            <a href="../util/Formatter.html#syntax">conversion</a>.
     * @throws java.util.IllegalFormatException
     *             If a format string contains an illegal syntax, a format
     *             specifier that is incompatible with the given arguments,
     *             insufficient arguments given the format string, or other
     *             illegal conditions. For specification of all possible
     *             formatting errors, see the
     *             <a href="../util/Formatter.html#detail">Details</a> section
     *             of the formatter class specification
     * @return a formatted string
     */
    public static String format(final CharSequence format, final Object[] parameters, final Object... arguments) {
        return format(null, format, parameters, arguments);
    }

    /**
     * Returns a formatted string using the specified format string, parameters
     * and arguments.
     * 
     * <p>
     * Examples:
     * </p>
     * 
     * <pre>
     * String before1 = "%s '%s*' '%s*' '%1$.2f' '%1$s*' '%s' '%s'";
     * 
     * // is equal to (if we specify position)
     * 
     * String before2 = "%1$s '%1$s*' '%2$s*' '%1$.2f' '%1$s*' '%3$s' '%4$s'";
     * 
     * // will become with 3 parameters and 2 arguments
     * 
     * String after = "%4$s '%1$s' '%2$s' '%4$.2f' '%1$s' '%5$s' ''";
     * 
     * // Format
     * Object[] parameters = new Object[] {"param1", "param2"};
     * Object[] arguments = new Object[] {1.025f, "arg2", "arg3"};
     * String result = StringUtils.format(Locale.FRENCH, before1, parameters, arguments);
     * System.out.println(result);
     * // =&gt; 1.025 'param1' 'param2' '1,02' 'param1' 'arg2' 'arg3'
     * </pre>
     * 
     * @param locale
     *            the {@linkplain java.util.Locale locale} to apply during
     *            formatting. If {@code locale} is {@code null} then no
     *            localization is applied.
     * @param format
     *            a <a href="../util/Formatter.html#syntax">format string</a>
     * @param parameters
     *            parameters has the same specification as {@code arguments}. In
     *            the {@code format} expression, parameters are suffixed with
     *            character '*'.
     * @param arguments
     *            arguments referenced by the format specifiers in the format
     *            string. If there are more arguments than format specifiers,
     *            the extra arguments are ignored. The number of arguments is
     *            variable and may be zero. The maximum number of arguments is
     *            limited by the maximum dimension of a Java array as defined by
     *            <cite>The Java&trade; Virtual Machine Specification</cite>.
     *            The behaviour on a {@code null} argument depends on the
     *            <a href="../util/Formatter.html#syntax">conversion</a>.
     * @throws java.util.IllegalFormatException
     *             If a format string contains an illegal syntax, a format
     *             specifier that is incompatible with the given arguments,
     *             insufficient arguments given the format string, or other
     *             illegal conditions. For specification of all possible
     *             formatting errors, see the
     *             <a href="../util/Formatter.html#detail">Details</a> section
     *             of the formatter class specification
     * @return a formatted string
     */
    public static String format(final Locale locale, final CharSequence format, final Object[] parameters, final Object... arguments) {
        Objects.requireNonNull(format, "format");

        return String.format(ObjectUtils.defaultIfNull(locale, Locale.getDefault(Locale.Category.FORMAT)),
                prepareFormat(format, parameters.length, arguments.length).toString(), ArrayUtils.addAll(parameters, arguments));
    }

    /**
     * Parse the string to find parameters and arguments expressions, changes
     * the index to match the combining of the two arrays and removes expression
     * with unavailable parameters or arguments.
     * 
     * <p>
     * Examples:
     * </p>
     * 
     * <pre>
     * String before1 = "%s '%s*' '%s*' '%1$.2f' '%1$s*' '%s' '%s'";
     * 
     * // is equal to (if we specify position)
     * 
     * String before2 = "%1$s '%1$s*' '%2$s*' '%1$.2f' '%1$s*' '%3$s' '%4$s'";
     * 
     * // will become with 3 parameters and 2 arguments
     * 
     * String after = "%4$s '%1$s' '%2$s' '%4$.2f' '%1$s' '%5$s' ''";
     * 
     * // Format
     * Object[] parameters = new Object[] {"param1", "param2"};
     * Object[] arguments = new Object[] {1.025f, "arg2", "arg3"};
     * String result = StringUtils.format("%s '%s*' '%s*' '%1$.2f' '%1$s*' '%s' '%s'", parameters, arguments);
     * System.out.println(result);
     * // =&gt; 1.025 'param1' 'param2' '1,02' 'param1' 'arg2' 'arg3'
     * </pre>
     * 
     * @param text
     *            the text where to search
     * @param nbParameters
     *            the number of parameters
     * @param nbArguments
     *            the number of arguments
     * @return the new char sequence
     */
    public static CharSequence prepareFormat(final CharSequence text, final int nbParameters, final int nbArguments) {
        return prepareFormat(text, nbParameters, 1, nbArguments, 1);
    }

    /**
     * Parse the string to find parameters and arguments expressions, changes
     * the index to match the combining of the two arrays and removes expression
     * with unavailable parameters or arguments.
     * 
     * <p>
     * Examples:
     * </p>
     * 
     * <pre>
     * "%s '%s*' '%s*' '%1$.2f' '%1$s*' '%s' '%s'"
     * 
     * // is equal to (if we specify position)
     * 
     * "%1$s '%1$s*' '%2$s*' '%1$.2f' '%1$s*' '%3$s' '%4$s'"
     * 
     * // will become with 3 parameters and 2 arguments
     * 
     * "%4$s '%1$s' '%2$s' '%4$.2f' '%1$s' '%5$s' ''"
     * </pre>
     * 
     * @param text
     *            the text where to search
     * @param nbParameters
     *            the number of parameters
     * @param startParameters
     *            the position of the parameter found
     * @param nbArguments
     *            the number of arguments
     * @param startArguments
     *            the position of the first arguments (after parameters)
     * @return the new char sequence
     */
    public static CharSequence prepareFormat(final CharSequence text, final int nbParameters, final int startParameters,
            final int nbArguments, final int startArguments) {

        final char[] chars = StringUtils.toChars(text);

        int start = -1;
        int state = STATE_NOTHING;

        final Set<Group> groups = new TreeSet<>();

        int i;
        Group group = null;

        for (i = 0; i < chars.length; i++) {
            if (group == null && chars[i] == PREFIX) {
                start = i;
                group = new Group(start);
            } else if (group != null) {
                if (state < STATE_INDEX && AsciiUtils.IS_NUMERIC.test(chars[i])) {
                    // (\\d+\\$)? ; the number
                    if (state == STATE_NOTHING) {
                        state = STATE_NUMBER;
                        group.index = 0;
                    }
                    // shift left from 1 number and add the number (convert char
                    // into number)
                    group.index = group.index * SHIFT_LEFT + chars[i] - AsciiUtils.NUM_FIRST;
                } else if (state < STATE_INDEX && chars[i] == INDEX_SUFFIX) {
                    // (\\d+\\$)? ; the dollar
                    state |= STATE_INDEX;
                } else if (state < STATE_INTEGER && Arrays.binarySearch(FLAGS, chars[i]) > -1) {
                    // ([-#+ 0,(\\<]*)?
                    state |= STATE_FLAGS;
                    group.flags.append((char) chars[i]);
                } else if (state < STATE_DOT && chars[i] == DOT) {
                    // (\\d+)?(\\.\\d+)? ; the dot
                    state |= STATE_DOT;
                    group.number.append((char) chars[i]);
                } else if (state < STATE_TIME && AsciiUtils.IS_NUMERIC.test(chars[i])) {
                    // (\\d+)?(\\.\\d+)? ; 8 (integer) for numbers before dot
                    // and 32 (decimal) for numbers after
                    if ((state & STATE_DOT) == STATE_DOT) {
                        state |= STATE_DECIMAL;
                    } else {
                        state |= STATE_INTEGER;
                    }
                    group.number.append((char) chars[i]);
                } else if (state < STATE_TIME && chars[i] == TIME_UPPERCASE || chars[i] == TIME_LOWERCASE) {
                    // [tT]
                    state |= STATE_TIME;
                    group.time = (char) chars[i];
                } else if (state < STATE_TYPE && (AsciiUtils.IS_ALPHA.test(chars[i]) || chars[i] == PERCENT)) {
                    // [a-zA-Z%]
                    state |= STATE_TYPE;
                    group.type.append((char) chars[i]);
                } else if (state < STATE_SUFFIX && chars[i] == PARAM_SUFFIX) {
                    // to detect internal parameter form
                    state |= STATE_SUFFIX;
                    group.asterisk = true;
                } else {
                    if ((state & STATE_INDEX) != STATE_INDEX && group.index > -1) {
                        // no index, so first number detected is the format, not
                        // a number
                        group.number.insert(0, String.valueOf(group.index));
                        group.index = -1;
                    }
                    if ((state & STATE_TYPE) == STATE_TYPE) {
                        // complete
                        group.end = i;
                        groups.add(group);
                    }
                    if (chars[i] == PREFIX) {
                        // prepare the next expression
                        start = i;
                        state = STATE_NOTHING;
                        group = new Group(start);
                    } else {
                        group = null;
                        state = STATE_NOTHING;
                    }
                }
            }
        }

        if (group != null) {
            if ((state & 2) != 2 && group.index > -1) {
                // no index, so first number detected is the format, not a
                // number
                group.number.insert(0, String.valueOf(group.index));
                group.index = -1;
            }

            group.end = i;
            groups.add(group);
        }

        return replaceAndClear(groups, reindex(groups, text, nbParameters, startParameters, nbArguments, startArguments));
    }

    private static StringBuilder reindex(final Set<Group> groups, final CharSequence text, final int nbParameters,
            final int startParameters, final int nbArguments, final int startArguments) {

        int posArg = startParameters - 1 + nbParameters + startArguments;
        int posParam = startParameters;
        final StringBuilder sb = new StringBuilder(text);

        for (Group group : groups) {
            if (group.index == -1) {
                if (group.asterisk) {
                    if (nbParameters < posParam) {
                        group.remove = true;
                    } else {
                        group.index = posParam;
                        posParam++;
                    }
                } else if (nbArguments < posArg - nbParameters) {
                    group.remove = true;
                } else {
                    group.index = posArg;
                    posArg++;
                }
            } else if (!group.asterisk) {
                if (nbArguments < group.index) {
                    group.remove = true;
                } else {
                    group.index += nbParameters;
                }
            } else if (nbParameters < group.index) {
                group.remove = true;
            }
        }
        return sb;
    }

    private static StringBuilder replaceAndClear(final Set<Group> groups, final StringBuilder sb) {
        groups.stream().sorted(Group.COMPARATOR.desc()).forEachOrdered((g) -> {
            if (g.remove) {
                sb.replace(g.start, g.end, EMPTY);
            } else {
                g.asterisk = false;
                sb.replace(g.start, g.end, g.toString());
            }
        });

        return sb;
    }

    /**
     * Class to manage groups and ordering
     *
     * @since Aug 9, 2016
     * @author Gilles
     *
     */
    private static class Group implements Comparable<Group> {

        private static final BiComparator<Group> COMPARATOR = new BiComparator<>();

        private final int start;
        private int end;
        private int index = -1;
        private StringBuilder flags;
        private StringBuilder number;
        private char time;
        private StringBuilder type;
        private boolean asterisk;

        private boolean remove;

        /**
         * Constructor
         *
         * @param start
         *            the start index
         */
        public Group(final int start) {
            this.start = start;
            this.flags = new StringBuilder();
            this.number = new StringBuilder();
            this.type = new StringBuilder();
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(PREFIX_PERCENT);
            sb.append(this.index).append(INDEX_SUFFIX);
            sb.append(this.flags);
            sb.append(this.number);
            if (this.time > 0) {
                sb.append(this.time);
            }
            sb.append(this.type);
            return sb.toString();
        }

        @Override
        public int compareTo(final Group o) {
            return this.start - o.start;
        }
    }
}
