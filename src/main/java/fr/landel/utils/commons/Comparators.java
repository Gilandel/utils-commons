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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 * Comparators list (byte, short, integer, long, float, double, big integer, big
 * decimal, character and string)
 *
 * @since Jun 26, 2016
 * @author Gilles
 *
 */
public class Comparators {

    /**
     * Byte comparator (handle null)
     */
    public static final BiComparator<Byte> BYTE = new BiComparator<>();

    /**
     * Short comparator (handle null)
     */
    public static final BiComparator<Short> SHORT = new BiComparator<>();

    /**
     * Integer comparator (handle null)
     */
    public static final BiComparator<Integer> INTEGER = new BiComparator<>();

    /**
     * Long comparator (handle null)
     */
    public static final BiComparator<Long> LONG = new BiComparator<>();

    /**
     * Float comparator (handle null)
     */
    public static final BiComparator<Float> FLOAT = new BiComparator<>();

    /**
     * Double comparator (handle null)
     */
    public static final BiComparator<Double> DOUBLE = new BiComparator<>();

    /**
     * Big integer comparator (handle null)
     */
    public static final BiComparator<BigInteger> BIG_INTEGER = new BiComparator<>();

    /**
     * Big decimal comparator (handle null)
     */
    public static final BiComparator<BigDecimal> BIG_DECIMAL = new BiComparator<>();

    /**
     * Character comparator (handle null)
     */
    public static final BiComparator<Character> CHAR = new BiComparator<>();

    /**
     * String comparator (handle null)
     */
    public static final BiComparator<String> STRING = new BiComparator<>();

    /**
     * Date comparator (handle null)
     */
    public static final BiComparator<Date> DATE = new BiComparator<>();

    /**
     * Calendar comparator (handle null)
     */
    public static final BiComparator<Calendar> CALENDAR = new BiComparator<>();

    /**
     * Duration comparator (handle null)
     */
    public static final BiComparator<Duration> DURATION = new BiComparator<>();

    /**
     * Instant comparator (handle null)
     */
    public static final BiComparator<Instant> INSTANT = new BiComparator<>();

    /**
     * OffsetTime comparator (handle null)
     */
    public static final BiComparator<OffsetTime> OFFSET_TIME = new BiComparator<>();

    /**
     * OffsetDateTime comparator (handle null)
     */
    public static final BiComparator<OffsetDateTime> OFFSET_DATETIME = new BiComparator<>();

    /**
     * LocalTime comparator (handle null)
     */
    public static final BiComparator<LocalTime> LOCAL_TIME = new BiComparator<>();

    /**
     * ChronoLocalDate comparator (handle null)
     */
    public static final BiComparator<ChronoLocalDate> LOCAL_DATE = new BiComparator<>();

    /**
     * {@link ChronoLocalDateTime} comparator (handle null)
     */
    public static final BiComparator<ChronoLocalDateTime<?>> LOCAL_DATETIME = new BiComparator<ChronoLocalDateTime<?>>();

    /**
     * ChronoZonedDateTime comparator (handle null)
     */
    public static final BiComparator<ChronoZonedDateTime<?>> ZONED_DATETIME = new BiComparator<ChronoZonedDateTime<?>>();

    /**
     * Version comparator (handle null)
     */
    public static final BiComparator<Version> VERSION = new BiComparator<>();

    /**
     * Hidden constructor
     */
    private Comparators() {
        throw new UnsupportedOperationException();
    }

    /**
     * Compare two comparables
     * 
     * @param o1
     *            The first value
     * @param o2
     *            The second value
     * @param mapper
     *            the mapper that gets the comparable element (the mapper is
     *            only called if value is not {@code null})
     * @param <T>
     *            the type of object
     * @param <X>
     *            The comparable type
     * @return The comparison, if o1 is {@code null}, returns
     *         {@link Integer#MIN_VALUE} and if o2 is {@code null}, returns
     *         {@link Integer#MAX_VALUE}
     * @throws NullPointerException
     *             if {@code mapper} is {@code null}
     */
    public static <T, X extends Comparable<X>> int compare(final T o1, final T o2, final Function<T, X> mapper) {
        return compare(map(o1, mapper), map(o2, mapper));
    }

    /**
     * Compare two comparables
     * 
     * @param o1
     *            The first value
     * @param o2
     *            The second value
     * @param <T>
     *            The comparable type
     * @return The comparison, if o1 is {@code null}, returns
     *         {@link Integer#MIN_VALUE} and if o2 is {@code null}, returns
     *         {@link Integer#MAX_VALUE}
     */
    public static <T extends Comparable<T>> int compare(final T o1, final T o2) {
        int result;
        if (o1 == o2) {
            result = 0;
        } else if (o1 == null) {
            result = Integer.MIN_VALUE;
        } else if (o2 == null) {
            result = Integer.MAX_VALUE;
        } else {
            result = o1.compareTo(o2);
        }
        return result;
    }

    /**
     * Compares two comparables (in reverse mode)
     * 
     * @param o1
     *            The first value
     * @param o2
     *            The second value
     * @param mapper
     *            the mapper that gets the comparable element (the mapper is
     *            only called if value is not {@code null})
     * @param <T>
     *            the type of object
     * @param <X>
     *            The comparable type
     * @return The reverse comparison, if o1 is {@code null}, returns
     *         {@link Integer#MAX_VALUE} and if o2 is {@code null}, returns
     *         {@link Integer#MIN_VALUE}
     * @throws NullPointerException
     *             if {@code mapper} is {@code null}
     */
    public static <T, X extends Comparable<X>> int compareReverse(final T o1, final T o2, final Function<T, X> mapper) {
        return compareReverse(map(o1, mapper), map(o2, mapper));
    }

    /**
     * Compares two comparables (in reverse mode)
     * 
     * @param o1
     *            The first value
     * @param o2
     *            The second value
     * @param <T>
     *            The comparable type
     * @return The reverse comparison, if o1 is {@code null}, returns
     *         {@link Integer#MAX_VALUE} and if o2 is {@code null}, returns
     *         {@link Integer#MIN_VALUE}
     */
    public static <T extends Comparable<T>> int compareReverse(final T o1, final T o2) {
        int result;
        if (o1 == o2) {
            result = 0;
        } else if (o1 == null) {
            result = Integer.MAX_VALUE;
        } else if (o2 == null) {
            result = Integer.MIN_VALUE;
        } else {
            result = o2.compareTo(o1);
        }
        return result;
    }

    /**
     * Applies the mapper on the value only if the object is NOT {@code null}
     * 
     * @param o
     *            the value
     * @param mapper
     *            the mapper function
     * @return the mapped value
     * @throws NullPointerException
     *             if {@code mapper} is {@code null}
     */
    private static final <T, X extends Comparable<X>> X map(final T o, final Function<T, X> mapper) {
        Objects.requireNonNull(mapper, "mapper");

        if (o == null) {
            return null;
        } else {
            return mapper.apply(o);
        }
    }

    /**
     * Implementation of comparator
     *
     * @since Jul 2, 2016
     * @author Gilles
     *
     * @param <T>
     *            The comparable type
     */
    private static class ComparatorImpl<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(final T o1, final T o2) {
            return Comparators.compare(o1, o2);
        }
    }

    /**
     * Implementation of reverse comparator
     *
     * @since Jul 2, 2016
     * @author Gilles
     *
     * @param <T>
     *            The comparable type
     */
    private static class ComparatorReverseImpl<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(final T o1, final T o2) {
            return Comparators.compareReverse(o1, o2);
        }
    }

    /**
     * Class to create bi-directional comparators
     *
     * @since Jul 2, 2016
     * @author Gilles
     *
     * @param <T>
     *            The comparable type
     */
    public static class BiComparator<T extends Comparable<T>> {
        private final Comparator<T> asc;
        private final Comparator<T> desc;

        /**
         * 
         * Constructor
         *
         */
        protected BiComparator() {
            this.asc = new ComparatorImpl<>();
            this.desc = new ComparatorReverseImpl<>();
        }

        /**
         * @return the asc
         */
        public Comparator<T> asc() {
            return this.asc;
        }

        /**
         * @return the desc
         */
        public Comparator<T> desc() {
            return this.desc;
        }
    }
}
