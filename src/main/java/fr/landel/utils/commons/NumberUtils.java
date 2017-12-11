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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class to manage numbers.
 *
 * @since Nov 27, 2015
 * @author Gilles Landel
 *
 */
public final class NumberUtils extends NumberUtilsParsers {

    private static final int TEN = 10;

    private static final byte[] INFINITY = {'I', 'n', 'f', 'i', 'n', 'i', 't', 'y'};

    private static final Predicate<Byte> IS_SIGN = b -> '+' == b || '-' == b;
    private static final Predicate<Byte> IS_EXPONENT = b -> 'e' == b || 'E' == b;
    private static final Predicate<Byte> IS_LONG = b -> 'l' == b || 'L' == b;
    private static final Predicate<Byte> IS_DECIMAL = b -> 'f' == b || 'F' == b || 'd' == b || 'D' == b;

    /**
     * Hidden constructor.
     */
    private NumberUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the max decimal length.
     * 
     * @param num1
     *            double
     * @param num2
     *            double
     * @return the max decimal length
     */
    private static Integer getMaxDecimalsLength(final Double num1, final Double num2) {
        return Math.max(BigDecimal.valueOf(num1).scale(), BigDecimal.valueOf(num2).scale());
    }

    /**
     * Get the max decimal length..
     * 
     * @param num1
     *            float
     * @param num2
     *            float
     * @return the max decimal length
     */
    private static Integer getMaxDecimalsLength(final Float num1, final Float num2) {
        return Math.max(BigDecimal.valueOf(num1).scale(), BigDecimal.valueOf(num2).scale());
    }

    /**
     * Check if the two doubles are equal (nullsafe).
     * 
     * @param num1
     *            The first double
     * @param num2
     *            The second double
     * @return true if equals
     */
    public static boolean isEqual(final Double num1, final Double num2) {
        return isEqual(num1, num2, getMaxDecimalsLength(num1, num2));
    }

    /**
     * Check if the two doubles are equal (nullsafe).
     * 
     * @param num1
     *            The first double
     * @param num2
     *            The second double
     * @param accuracy
     *            The accuracy (1/pow(10,accuracy))
     * @return true if equals
     */
    public static boolean isEqual(final Double num1, final Double num2, final Integer accuracy) {
        if (num1 != null && num2 != null) {
            if (accuracy != null) {
                double maxGap = 1d / Math.pow(TEN, accuracy);
                return Math.abs(num1 - num2) < maxGap;
            } else {
                return isEqual(num1, num2);
            }
        }
        return num1 == null && num2 == null;
    }

    /**
     * Check if the two floats are equal (nullsafe).
     * 
     * @param num1
     *            The first float
     * @param num2
     *            The second float
     * @return true if equals
     */
    public static boolean isEqual(final Float num1, final Float num2) {
        return isEqual(num1, num2, getMaxDecimalsLength(num1, num2));
    }

    /**
     * Check if the two floats are equal (nullsafe).
     * 
     * @param num1
     *            The first float
     * @param num2
     *            The second float
     * @param accuracy
     *            The accuracy (1/pow(10,accuracy))
     * @return true if equals
     */
    public static boolean isEqual(final Float num1, final Float num2, final Integer accuracy) {
        if (num1 != null && num2 != null) {
            if (accuracy != null) {
                float maxGap = (float) (1d / Math.pow(TEN, accuracy));
                return Math.abs(num1 - num2) < maxGap;
            } else {
                return isEqual(num1, num2);
            }
        }
        return num1 == null && num2 == null;
    }

    /**
     * Get the number if not null and the default one otherwise.
     * 
     * @param num
     *            The number to check, may be null
     * @param defaultNum
     *            The default number
     * @param <N>
     *            Type of the number
     * @return a number
     */
    public static <N extends Number> N getDefaultIfNull(final N num, final N defaultNum) {
        if (num != null) {
            return num;
        }
        return defaultNum;
    }

    /**
     * Round to 0.x
     * 
     * @param value
     *            The value
     * @return A float
     */
    public static Float round(final Double value) {
        if (value != null) {
            return (float) (Math.round(value * TEN)) / TEN;
        }
        return null;
    }

    /**
     * Check if the string contains an integer number ({@link Byte},
     * {@link Short}, {@link Integer}, {@link Long} or {@link BigInteger}). The
     * following regular expression is applied {@code [+-]?\d+}
     * 
     * <p>
     * Only format is checked, not if the {@code float} is an {@code int} is a
     * {@code long} ({@link Integer#MAX_VALUE} or {@link Integer#MIN_VALUE})...
     * </p>
     * 
     * <pre>
     * NumberUtils.isNumberInteger("25"); // -&gt; true
     * NumberUtils.isNumberInteger("+25"); // -&gt; true
     * NumberUtils.isNumberInteger("-25"); // -&gt; true
     * NumberUtils.isNumberInteger("+25l"); // -&gt; false
     * NumberUtils.isNumberInteger("-25L"); // -&gt; false
     * NumberUtils.isNumberInteger("25d"); // -&gt; false
     * NumberUtils.isNumberInteger("25f"); // -&gt; false
     * 
     * NumberUtils.isNumberInteger(""); // -&gt; false
     * NumberUtils.isNumberInteger("text"); // -&gt; false
     * NumberUtils.isNumberInteger((String) null); // -&gt; false
     * </pre>
     * 
     * 
     * @param string
     *            the input String
     * @return true, if integer number
     */
    public static boolean isNumberInteger(final String string) {
        return isNumberInteger(string, false);
    }

    /**
     * Check if the string contains an integer number ({@link Byte},
     * {@link Short}, {@link Integer}, {@link Long} or {@link BigInteger}). If
     * typeSupported is true, the following regular expression is applied
     * {@code [+-]?\d+[lL]?}, otherwise {@code [+-]?\d+}
     * 
     * <p>
     * Only format is checked, not if the {@code float} is an {@code int} is a
     * {@code long} ({@link Integer#MAX_VALUE} or {@link Integer#MIN_VALUE})...
     * </p>
     * 
     * <pre>
     * NumberUtils.isNumberInteger("25", false); // -&gt; true
     * NumberUtils.isNumberInteger("+25", false); // -&gt; true
     * NumberUtils.isNumberInteger("-25", false); // -&gt; true
     * NumberUtils.isNumberInteger("+25l", false); // -&gt; false
     * NumberUtils.isNumberInteger("-25L", false); // -&gt; false
     * NumberUtils.isNumberInteger("+25l", true); // -&gt; true
     * NumberUtils.isNumberInteger("-25L", true); // -&gt; true
     * NumberUtils.isNumberInteger("25d", true); // -&gt; false
     * NumberUtils.isNumberInteger("25f", true); // -&gt; false
     * 
     * NumberUtils.isNumberInteger("", true); // -&gt; false
     * NumberUtils.isNumberInteger("text", true); // -&gt; false
     * NumberUtils.isNumberInteger((String) null, true); // -&gt; false
     * </pre>
     * 
     * 
     * @param string
     *            the input String
     * @param typeSupported
     *            if typed is supported (like: "10L")
     * @return true, if integer number
     */
    public static boolean isNumberInteger(final String string, final boolean typeSupported) {

        if (StringUtils.isEmpty(string)) {
            return false;
        }

        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);

        int start = 0;
        if (IS_SIGN.test(bytes[0])) {
            start = 1;
        }

        final int length = bytes.length;

        short nb = 0;
        byte typed = 0;

        for (int i = start; i < length; ++i) {
            if (AsciiUtils.IS_NUMERIC.test(bytes[i])) {
                ++nb;
            } else if (typeSupported && i == length - 1) {
                typed = IS_LONG.test(bytes[i]) ? (byte) 1 : 0;
            }
        }
        return start + nb + typed == length;
    }

    /**
     * Check if the class is an integer number class ({@link Byte},
     * {@link Short}, {@link Integer}, {@link Long}, {@link BigInteger},
     * {@link AtomicInteger} or {@link AtomicLong})
     * 
     * @param clazz
     *            the input class
     * @return true, if integer number
     */
    public static boolean isNumberInteger(final Class<?> clazz) {
        boolean result = false;
        if (clazz != null) {
            if (Integer.class.isAssignableFrom(clazz)) {
                result = true;
            } else if (Long.class.isAssignableFrom(clazz)) {
                result = true;
            } else if (Byte.class.isAssignableFrom(clazz)) {
                result = true;
            } else if (Short.class.isAssignableFrom(clazz)) {
                result = true;
            } else if (BigInteger.class.isAssignableFrom(clazz)) {
                result = true;
            } else if (AtomicInteger.class.isAssignableFrom(clazz)) {
                result = true;
            } else if (AtomicLong.class.isAssignableFrom(clazz)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Check if the number is an integer number ({@link Byte}, {@link Short},
     * {@link Integer}, {@link Long} or {@link BigInteger})
     * 
     * @param object
     *            the input object
     * @return true, if integer number
     */
    public static boolean isNumberInteger(final Object object) {
        return isNumberInteger(ClassUtils.getClass(object));
    }

    /**
     * Check if the string contains a decimal number ({@link Float},
     * {@link Double}, {@link BigDecimal}). The following regular expression is
     * applied {@code [+-]?(\\d+)?\\.\\d+}
     * 
     * <p>
     * Only format is checked, not if the {@code float} is a {@code double}
     * ({@link Float#MAX_VALUE} or {@link Float#MIN_VALUE}) or an {@code int} is
     * a {@code long}...
     * </p>
     * 
     * <pre>
     * NumberUtils.isNumberDecimal("25.6"); // -&gt; true
     * NumberUtils.isNumberDecimal("+25.6"); // -&gt; true
     * NumberUtils.isNumberDecimal("-25.6"); // -&gt; true
     * NumberUtils.isNumberDecimal("+25.6d"); // -&gt; false
     * NumberUtils.isNumberDecimal("-25.6F"); // -&gt; false
     * NumberUtils.isNumberDecimal("25d"); // -&gt; false
     * NumberUtils.isNumberDecimal("25f"); // -&gt; false
     * NumberUtils.isNumberDecimal(".25"); // -&gt; true
     * NumberUtils.isNumberDecimal("25."); // -&gt; false
     * NumberUtils.isNumberDecimal("25"); // -&gt; false
     * 
     * NumberUtils.isNumberDecimal(""); // -&gt; false
     * NumberUtils.isNumberDecimal("text"); // -&gt; false
     * NumberUtils.isNumberDecimal((String) null); // -&gt; false
     * </pre>
     * 
     * @param string
     *            the input String
     * @return true, if decimal number
     */
    public static boolean isNumberDecimal(final String string) {
        return isNumberDecimal(string, false);
    }

    /**
     * Check if the string contains a decimal number ({@link Float},
     * {@link Double}, {@link BigDecimal}). if typeSupported is true, the
     * following regular expression is applied
     * {@code [+-]?(\\d+[dfDF]|(\\d+)?\\.\\d+[dfDF]?)}, otherwise
     * {@code [+-]?(\\d+)?\\.\\d+}
     * 
     * <p>
     * Only format is checked, not if the {@code float} is a {@code double}
     * ({@link Float#MAX_VALUE} or {@link Float#MIN_VALUE}) or an {@code int} is
     * a {@code long}...
     * </p>
     * 
     * <pre>
     * NumberUtils.isNumberDecimal("25.6"); // -&gt; true
     * NumberUtils.isNumberDecimal("+25.6"); // -&gt; true
     * NumberUtils.isNumberDecimal("-25.6"); // -&gt; true
     * NumberUtils.isNumberDecimal("+25.6d"); // -&gt; false
     * NumberUtils.isNumberDecimal("-25.6F"); // -&gt; false
     * NumberUtils.isNumberDecimal("25d"); // -&gt; false
     * NumberUtils.isNumberDecimal("25f"); // -&gt; false
     * NumberUtils.isNumberDecimal(".25"); // -&gt; true
     * NumberUtils.isNumberDecimal("25."); // -&gt; false
     * NumberUtils.isNumberDecimal("25"); // -&gt; false
     * 
     * NumberUtils.isNumberDecimal(""); // -&gt; false
     * NumberUtils.isNumberDecimal("text"); // -&gt; false
     * NumberUtils.isNumberDecimal((String) null); // -&gt; false
     * </pre>
     * 
     * @param string
     *            the input String
     * @param typeSupported
     *            if typed is supported (like: "10.2f")
     * @return true, if decimal number
     */
    public static boolean isNumberDecimal(final String string, final boolean typeSupported) {
        return isNumberDecimal(string, typeSupported, false);
    }

    /**
     * Check if the string contains a decimal number ({@link Float},
     * {@link Double}, {@link BigDecimal}).
     * 
     * <p>
     * Regular expression used by cases:
     * </p>
     * 
     * <ul>
     * <li>typeSupported is true and lenient is false:
     * {@code [+-]?(\\d+[dfDF]|(\\d+)?\\.\\d+([eE][+-]?\\d+)?[dfDF]?)}</li>
     * 
     * <li>typeSupported is true and lenient is true:
     * {@code [+-]?(\\d+|(\\d+)?\\.\\d+)([eE][+-]?\\d+)?[dfDF]?}</li>
     * 
     * <li>typeSupported is false and lenient is false:
     * {@code [+-]?(\\d+)?\\.\\d+([eE][+-]?\\d+)?}</li>
     * 
     * <li>typeSupported is false and lenient is true:
     * {@code [+-]?(\\d+|(\\d+)?\\.\\d+)?([eE][+-]?\\d+)?}</li>
     * </ul>
     * 
     * <p>
     * Infinity key word is also supported
     * </p>
     * 
     * <p>
     * Only format is checked, not if the {@code float} is a {@code double}
     * ({@link Float#MAX_VALUE} or {@link Float#MIN_VALUE}) or an {@code int} is
     * a {@code long} or the exponent length is correct...
     * </p>
     * 
     * <pre>
     * NumberUtils.isNumberDecimal("25.6", false, false); // -&gt; true
     * NumberUtils.isNumberDecimal("+25.6", false, false); // -&gt; true
     * NumberUtils.isNumberDecimal("-25.6", false, false); // -&gt; true
     * NumberUtils.isNumberDecimal("+25.6d", true, false); // -&gt; true
     * NumberUtils.isNumberDecimal("-25.6F", true, false); // -&gt; true
     * NumberUtils.isNumberDecimal("+25.6d", true, false); // -&gt; true
     * NumberUtils.isNumberDecimal("-25.6F", true, false); // -&gt; true
     * NumberUtils.isNumberDecimal(".25d", true, false); // -&gt; true
     * NumberUtils.isNumberDecimal("25d", true, false); // -&gt; true
     * NumberUtils.isNumberDecimal("25f", true, false); // -&gt; true
     * NumberUtils.isNumberDecimal("25", false, false); // -&gt; false
     * NumberUtils.isNumberDecimal("25", false, true); // -&gt; true
     * 
     * NumberUtils.isNumberDecimal("", false, true); // -&gt; false
     * NumberUtils.isNumberDecimal("text", false, true); // -&gt; false
     * NumberUtils.isNumberDecimal((String) null, false, true); // -&gt; false
     * </pre>
     * 
     * @param string
     *            the input String
     * @param typeSupported
     *            if typed is supported (like: "10.2f")
     * @param lenient
     *            set to false if dot is required
     * @return true, if decimal number
     */
    public static boolean isNumberDecimal(final String string, final boolean typeSupported, final boolean lenient) {

        if (StringUtils.isEmpty(string)) {
            return false;
        }

        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);

        byte start = 0;
        if (IS_SIGN.test(bytes[0])) {
            start = 1;
        }

        final int length = bytes.length;

        final Boolean infinity = isInfinity(bytes, length, start);
        if (infinity != null) {
            return infinity;
        }

        byte dot = 0;
        byte exponent = 0;
        int exponentPos = -1;
        byte expSign = 0;
        byte typed = 0;
        short nb1 = 0;
        short nb2 = 0;
        short nb3 = 0;

        for (int i = start; i < length; ++i) {
            if (AsciiUtils.IS_NUMERIC.test((int) bytes[i])) {
                if (exponent == 1) {
                    ++nb3;
                } else if (dot == 1) {
                    ++nb2;
                } else {
                    ++nb1;
                }
            } else if ('.' == bytes[i]) {
                if (dot == 1 || exponent == 1) {
                    // multiple dots or after an exponent marker
                    return false;
                } else {
                    dot = 1;
                }
            } else if (IS_EXPONENT.test(bytes[i])) {
                if (exponent == 1) {
                    return false; // multiple exponents
                } else {
                    exponent = 1;
                    exponentPos = i;
                }
            } else if (IS_SIGN.test(bytes[i])) {
                if (expSign == 1 || exponentPos != i - 1) {
                    // multiple exponent signs, no exponent marker or not just
                    // after exponent marker
                    return false;
                } else {
                    expSign = 1;
                }
            } else if (typeSupported && i == length - 1 && IS_DECIMAL.test(bytes[i])) {
                typed = 1;
            }
        }
        // prepare length by subtracting all common values
        return compareLength(nb1, dot, nb2, typed, length - (start + getExpLength(exponent, expSign, nb3) + typed), lenient);
    }

    private static Boolean isInfinity(final byte[] bytes, final int length, final int start) {
        if ('I' == bytes[start]) {
            if (length > 1) {
                final byte[] bytesWithoutSign = new byte[length - start];
                System.arraycopy(bytes, start, bytesWithoutSign, 0, length - start);
                return Arrays.equals(INFINITY, bytesWithoutSign);
            } else {
                return false;
            }
        }
        return null;
    }

    private static boolean compareLength(final short nb1, final byte dot, final short nb2, final byte typed, final int length,
            final boolean lenient) {

        if (nb1 > 0) {
            if (dot == 1 && nb2 > 0) {
                return nb1 + dot + nb2 == length;
            } else if (lenient || typed == 1) {
                return nb1 == length;
            }
        } else if (dot == 1 && nb2 > 0) {
            return dot + nb2 == length;
        }
        return false;
    }

    private static int getExpLength(final byte exponent, final byte expSign, final short nb3) {

        if (exponent == 1 && nb3 > 0) {
            return exponent + expSign + nb3;
        } else {
            return 0;
        }
    }

    /**
     * Check if the class is a decimal number class ({@link Float},
     * {@link Double}, {@link BigDecimal})
     * 
     * @param clazz
     *            the input class
     * @return true, if integer number
     */
    public static boolean isNumberDecimal(final Class<?> clazz) {
        boolean result = false;
        if (clazz != null) {
            if (Float.class.isAssignableFrom(clazz)) {
                result = true;
            } else if (Double.class.isAssignableFrom(clazz)) {
                result = true;
            } else if (BigDecimal.class.isAssignableFrom(clazz)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Check if the number is a decimal number ({@link Float}, {@link Double},
     * {@link BigDecimal})
     * 
     * @param object
     *            the input object
     * @return true, if integer number
     */
    public static boolean isNumberDecimal(final Object object) {
        return isNumberDecimal(ClassUtils.getClass(object));
    }

    /**
     * Check if the number is a {@link Byte}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isByte(final N number) {
        return isNumberType(number, Byte.class);
    }

    /**
     * Check if the number is a {@link Short}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isShort(final N number) {
        return isNumberType(number, Short.class);
    }

    /**
     * Check if the number is an {@link Integer}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isInteger(final N number) {
        return isNumberType(number, Integer.class);
    }

    /**
     * Check if the number is a {@link Long}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isLong(final N number) {
        return isNumberType(number, Long.class);
    }

    /**
     * Check if the number is a {@link Float}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isFloat(final N number) {
        return isNumberType(number, Float.class);
    }

    /**
     * Check if the number is a {@link Double}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isDouble(final N number) {
        return isNumberType(number, Double.class);
    }

    /**
     * Check if the number is a {@link BigInteger}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isBigInteger(final N number) {
        return isNumberType(number, BigInteger.class);
    }

    /**
     * Check if the number is a {@link BigDecimal}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isBigDecimal(final N number) {
        return isNumberType(number, BigDecimal.class);
    }

    /**
     * Check if the number is an {@link AtomicInteger}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isAtomicInteger(final N number) {
        return isNumberType(number, AtomicInteger.class);
    }

    /**
     * Check if the number is an {@link AtomicLong}
     * 
     * @param number
     *            The number to check
     * @param <N>
     *            The type of the number
     * @return true, if matches
     */
    public static <N extends Number> boolean isAtomicLong(final N number) {
        return isNumberType(number, AtomicLong.class);
    }

    private static <N extends Number> boolean isNumberType(final N number, final Class<? extends Number> classNumber) {
        return number != null && classNumber.isAssignableFrom(number.getClass());
    }

    /**
     * Get the sign of the number
     * 
     * @param number
     *            the number to check
     * @param <N>
     *            The type of the number
     * @return 1 if number &gt; 0, -1 if number &lt; 0 and 0 otherwise, even if
     *         null
     */
    public static <N extends Number> int signum(final N number) {
        int result = 0;
        if (number != null) {
            if (NumberUtils.isInteger(number)) {
                final Integer n = (Integer) number;
                result = n > 0 ? 1 : n < 0 ? -1 : 0;
            } else if (NumberUtils.isLong(number)) {
                final Long n = (Long) number;
                result = n > 0 ? 1 : n < 0 ? -1 : 0;
            } else if (NumberUtils.isFloat(number)) {
                final Float n = (Float) number;
                result = n > 0 ? 1 : n < 0 ? -1 : 0;
            } else if (NumberUtils.isDouble(number)) {
                final Double n = (Double) number;
                result = n > 0 ? 1 : n < 0 ? -1 : 0;
            } else if (NumberUtils.isByte(number)) {
                final Byte n = (Byte) number;
                result = n > 0 ? 1 : n < 0 ? -1 : 0;
            } else if (NumberUtils.isShort(number)) {
                final Short n = (Short) number;
                result = n > 0 ? 1 : n < 0 ? -1 : 0;
            } else if (NumberUtils.isBigInteger(number)) {
                result = ((BigInteger) number).signum();
            } else if (NumberUtils.isAtomicInteger(number)) {
                final int n = ((AtomicInteger) number).get();
                result = n > 0 ? 1 : n < 0 ? -1 : 0;
            } else if (NumberUtils.isAtomicLong(number)) {
                final long n = ((AtomicLong) number).get();
                result = n > 0 ? 1 : n < 0 ? -1 : 0;
            } else if (NumberUtils.isBigDecimal(number)) {
                result = ((BigDecimal) number).signum();
            }
        }
        return result;
    }

    /**
     * Check if the number is equal to zero
     * 
     * @param number
     *            the number to check
     * @param <N>
     *            The type of the number
     * @return true if number = 0, false otherwise
     */
    public static <N extends Number> boolean isZero(final N number) {
        boolean result = false;
        if (number != null) {
            if (NumberUtils.isInteger(number)) {
                final Integer n = (Integer) number;
                result = n == 0;
            } else if (NumberUtils.isLong(number)) {
                final Long n = (Long) number;
                result = n == 0;
            } else if (NumberUtils.isFloat(number)) {
                final Float n = (Float) number;
                result = n == 0;
            } else if (NumberUtils.isDouble(number)) {
                final Double n = (Double) number;
                result = n == 0;
            } else if (NumberUtils.isByte(number)) {
                final Byte n = (Byte) number;
                result = n == 0;
            } else if (NumberUtils.isShort(number)) {
                final Short n = (Short) number;
                result = n == 0;
            } else if (NumberUtils.isBigInteger(number)) {
                result = ((BigInteger) number).signum() == 0;
            } else if (NumberUtils.isAtomicInteger(number)) {
                final int n = ((AtomicInteger) number).get();
                result = n == 0;
            } else if (NumberUtils.isAtomicLong(number)) {
                final long n = ((AtomicLong) number).get();
                result = n == 0;
            } else if (NumberUtils.isBigDecimal(number)) {
                result = ((BigDecimal) number).signum() == 0;
            }
        }
        return result;
    }
}
