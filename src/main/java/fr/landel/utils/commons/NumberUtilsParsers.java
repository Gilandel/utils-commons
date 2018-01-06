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

import org.apache.commons.lang3.StringUtils;

import fr.landel.utils.commons.function.FunctionThrowable;

/**
 * Utility class to parse numbers.
 *
 * @since Dec 11, 2017
 * @author Gilles Landel
 *
 */
class NumberUtilsParsers extends org.apache.commons.lang3.math.NumberUtils {

    protected static final int RADIX = 10;

    /**
     * Parse a string into a byte. (Null safe and number safe). Returns null, if
     * the string is null or not a number.
     * 
     * @param string
     *            The input
     * @return The parsed result
     */
    public static Byte parseByte(final String string) {
        return parseByte(string, null);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe). Returns null, if
     * the string is null or not a number (if {@code noThrow} is {@code true}).
     * 
     * @param string
     *            The input
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Byte parseByte(final String string, final boolean noThrow) {
        return parseByte(string, null, RADIX, noThrow);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @return The parsed result
     */
    public static Byte parseByte(final String string, final Byte defaultValue) {
        return parseByte(string, defaultValue, RADIX);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Byte parseByte(final String string, final Byte defaultValue, final boolean noThrow) {
        return parseByte(string, defaultValue, RADIX, noThrow);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param radix
     *            The radix to be used while parsing the string
     * @return The parsed result
     */
    public static Byte parseByte(final String string, final Byte defaultValue, final int radix) {
        return parseByte(string, defaultValue, radix, true);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param radix
     *            The radix to be used while parsing the string
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Byte parseByte(final String string, final Byte defaultValue, final int radix, final boolean noThrow) {
        return parse(string, defaultValue, s -> Byte.parseByte(s, radix), noThrow);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe). Returns null, if
     * the string is null or not a number.
     * 
     * @param string
     *            The input
     * @return The parsed result
     */
    public static Short parseShort(final String string) {
        return parseShort(string, null, RADIX, true);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe). Returns null, if
     * the string is null or not a number (if {@code noThrow} is {@code true}).
     * 
     * @param string
     *            The input
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Short parseShort(final String string, final boolean noThrow) {
        return parseShort(string, null, RADIX, noThrow);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @return The parsed result
     */
    public static Short parseShort(final String string, final Short defaultValue) {
        return parseShort(string, defaultValue, RADIX);
    }

    /**
     * Parse a string into a byte. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Short parseShort(final String string, final Short defaultValue, final boolean noThrow) {
        return parseShort(string, defaultValue, RADIX, noThrow);
    }

    /**
     * Parse a string into a short. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param radix
     *            The radix to be used while parsing the string
     * @return The parsed result
     */
    public static Short parseShort(final String string, final Short defaultValue, final int radix) {
        return parseShort(string, defaultValue, radix, true);
    }

    /**
     * Parse a string into a short. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param radix
     *            The radix to be used while parsing the string
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Short parseShort(final String string, final Short defaultValue, final int radix, final boolean noThrow) {
        return parse(string, defaultValue, s -> Short.parseShort(s, radix), noThrow);
    }

    /**
     * Parse a string into an integer. (Null safe and number safe). Returns
     * null, if the string is null or not a number.
     * 
     * @param string
     *            The input
     * @return The parsed result
     */
    public static Integer parseInt(final String string) {
        return parseInt(string, null, RADIX);
    }

    /**
     * Parse a string into an integer. (Null safe and number safe). Returns
     * null, if the string is null or not a number (if {@code noThrow} is
     * {@code true}).
     * 
     * @param string
     *            The input
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Integer parseInt(final String string, final boolean noThrow) {
        return parseInt(string, null, RADIX, noThrow);
    }

    /**
     * Parse a string into an integer. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @return The parsed result
     */
    public static Integer parseInt(final String string, final Integer defaultValue) {
        return parseInt(string, defaultValue, RADIX, true);
    }

    /**
     * Parse a string into an integer. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Integer parseInt(final String string, final Integer defaultValue, final boolean noThrow) {
        return parseInt(string, defaultValue, RADIX, noThrow);
    }

    /**
     * Parse a string into an integer. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param radix
     *            The radix to be used while parsing the string
     * @return The parsed result
     */
    public static Integer parseInt(final String string, final Integer defaultValue, final int radix) {
        return parseInt(string, defaultValue, radix, true);
    }

    /**
     * Parse a string into an integer. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param radix
     *            The radix to be used while parsing the string
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Integer parseInt(final String string, final Integer defaultValue, final int radix, final boolean noThrow) {
        return parse(string, defaultValue, s -> Integer.parseInt(s, radix), noThrow);
    }

    /**
     * Parse a string into a long. (Null safe and number safe). Returns null, if
     * the string is null or not a number.
     * 
     * @param string
     *            The input
     * @return The parsed result
     */
    public static Long parseLong(final String string) {
        return parseLong(string, null, RADIX);
    }

    /**
     * Parse a string into a long. (Null safe and number safe). Returns null, if
     * the string is null or not a number (if {@code noThrow} is {@code true}).
     * 
     * @param string
     *            The input
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Long parseLong(final String string, final boolean noThrow) {
        return parseLong(string, null, noThrow);
    }

    /**
     * Parse a string into a long. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @return The parsed result
     */
    public static Long parseLong(final String string, final Long defaultValue) {
        return parseLong(string, defaultValue, true);
    }

    /**
     * Parse a string into a long. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Long parseLong(final String string, final Long defaultValue, final boolean noThrow) {
        return parseLong(string, defaultValue, RADIX, noThrow);
    }

    /**
     * Parse a string into a long. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param radix
     *            The radix to be used while parsing the string
     * @return The parsed result
     */
    public static Long parseLong(final String string, final Long defaultValue, final int radix) {
        return parseLong(string, defaultValue, radix, true);
    }

    /**
     * Parse a string into a long. (Null safe and number safe)
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param radix
     *            The radix to be used while parsing the string
     * @param noThrow
     *            if true, don't throw a {@link NumberFormatException} if parse
     *            failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Long parseLong(final String string, final Long defaultValue, final int radix, final boolean noThrow) {
        return parse(string, defaultValue, s -> Long.parseLong(s, radix), noThrow);
    }

    /**
     * Parse a string into a float. (Null safe and number safe). Returns null,
     * if the string is null or not a number.
     * 
     * @param string
     *            The input
     * @return The parsed result
     */
    public static Float parseFloat(final String string) {
        return parseFloat(string, null, true);
    }

    /**
     * Parse a string into a float. (Null safe and number safe). Returns null,
     * if the string is null or not a number (if {@code noThrow} is
     * {@code true}).
     * 
     * @param string
     *            The input
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Float parseFloat(final String string, final boolean noThrow) {
        return parseFloat(string, null, noThrow);
    }

    /**
     * Parse a string into a float. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @return The parsed result
     */
    public static Float parseFloat(final String string, final Float defaultValue) {
        return parseFloat(string, defaultValue, true);
    }

    /**
     * Parse a string into a float. (Null safe and number safe).
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Float parseFloat(final String string, final Float defaultValue, final boolean noThrow) {
        return parse(string, defaultValue, Float::parseFloat, noThrow);
    }

    /**
     * Parse a string into a double. (Null safe and number safe). Returns null,
     * if the string is null or not a number.
     * 
     * @param string
     *            The input
     * @return The parsed result
     */
    public static Double parseDouble(final String string) {
        return parseDouble(string, null, true);
    }

    /**
     * Parse a string into a double. (Null safe and number safe). Returns null,
     * if the string is null or not a number (if {@code noThrow} is
     * {@code true}).
     * 
     * @param string
     *            The input
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Double parseDouble(final String string, final boolean noThrow) {
        return parseDouble(string, null, noThrow);
    }

    /**
     * Parse a string into a double. (Null safe and number safe)
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @return The parsed result
     */
    public static Double parseDouble(final String string, final Double defaultValue) {
        return parseDouble(string, defaultValue, true);
    }

    /**
     * Parse a string into a double. (Null safe and number safe)
     * 
     * @param string
     *            The input
     * @param defaultValue
     *            If the input cannot be parse, value is returned
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @return The parsed result
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    public static Double parseDouble(final String string, final Double defaultValue, final boolean noThrow) {
        return parse(string, defaultValue, Double::parseDouble, noThrow);
    }

    /**
     * Parse a string into a number through the provided parser, if the string
     * cannot be parsed or if parse failed the default number is returned
     * 
     * @param string
     *            the string to parse
     * @param defaultValue
     *            the default value
     * @param parser
     *            the parser
     * @param noThrow
     *            true, to avoid throwing a {@link NumberFormatException} if
     *            parse failed
     * @param <T>
     *            the output number type
     * @return the parsed value or the default value
     * @throws NumberFormatException
     *             if the number cannot be parsed and {@code noThrow} is
     *             {@code false}
     */
    private static <T extends Number> T parse(final String string, final T defaultValue,
            final FunctionThrowable<String, T, NumberFormatException> parser, final boolean noThrow) {

        if (StringUtils.isNotEmpty(string)) {
            try {
                return parser.apply(string);
            } catch (final NumberFormatException e) {
                if (!noThrow) {
                    throw e;
                }
            }
        }
        return defaultValue;
    }
}
