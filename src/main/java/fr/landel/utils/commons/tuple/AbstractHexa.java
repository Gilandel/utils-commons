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
package fr.landel.utils.commons.tuple;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * <p>
 * A hexa consisting of six elements.
 * </p>
 * 
 * <p>
 * This class is an abstract implementation defining the basic API. It refers to
 * the elements as 'first', 'second', 'third', 'fourth', 'fifth' and 'sixth'.
 * </p>
 * 
 * <p>
 * Subclass implementations may be mutable or immutable. However, there is no
 * restriction on the type of the stored objects that may be stored. If mutable
 * objects are stored in the hexa, then the hexa itself effectively becomes
 * mutable.
 * </p>
 *
 * @param <A>
 *            the first element type
 * @param <B>
 *            the second element type
 * @param <C>
 *            the third element type
 * @param <D>
 *            the fourth element type
 * @param <E>
 *            the fifth element type
 * @param <F>
 *            the sixth element type
 *
 * @since Nov 16, 2017
 * @author Gilles
 *
 */
public abstract class AbstractHexa<A, B, C, D, E, F> implements Comparable<AbstractHexa<A, B, C, D, E, F>>, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1319128872551227846L;

    /**
     * <p>
     * Gets the first element from this hexa.
     * </p>
     *
     * @return the first element, may be null
     */
    public abstract A getFirst();

    /**
     * <p>
     * Gets the second element from this hexa.
     * </p>
     *
     * @return the second element, may be null
     */
    public abstract B getSecond();

    /**
     * <p>
     * Gets the third element from this hexa.
     * </p>
     *
     * @return the third element, may be null
     */
    public abstract C getThird();

    /**
     * <p>
     * Gets the fourth element from this hexa.
     * </p>
     *
     * @return the fourth element, may be null
     */
    public abstract D getFourth();

    /**
     * <p>
     * Gets the fifth element from this hexa.
     * </p>
     *
     * @return the fifth element, may be null
     */
    public abstract E getFifth();

    /**
     * <p>
     * Gets the sixth element from this hexa.
     * </p>
     *
     * @return the sixth element, may be null
     */
    public abstract F getSixth();

    /**
     * <p>
     * Compares the hexa based on the first element followed by the second, the
     * third, the fourth, the fifth and the sixth element. The types must be
     * {@code Comparable}.
     * </p>
     * 
     * @param other
     *            the other hexa, not null
     * @return negative if this is less, zero if equal, positive if greater and
     *         if other is {@code null}, returns {@link Integer#MAX_VALUE}
     */
    @Override
    public int compareTo(final AbstractHexa<A, B, C, D, E, F> other) {
        if (other == null) {
            return Integer.MAX_VALUE;
        }

        // @formatter:off
        return new CompareToBuilder()
                .append(this.getFirst(), other.getFirst())
                .append(this.getSecond(), other.getSecond())
                .append(this.getThird(), other.getThird())
                .append(this.getFourth(), other.getFourth())
                .append(this.getFifth(), other.getFifth())
                .append(this.getSixth(), other.getSixth())
                .toComparison();
        // @formatter:on
    }

    /**
     * <p>
     * Compares this hexa to another based on the six elements.
     * </p>
     * 
     * @param obj
     *            the object to compare to, null returns false
     * @return true if the elements of the hexa are equal
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractHexa<?, ?, ?, ?, ?, ?>) {
            final AbstractHexa<?, ?, ?, ?, ?, ?> other = (AbstractHexa<?, ?, ?, ?, ?, ?>) obj;
            // @formatter:off
            return new EqualsBuilder()
                    .append(this.getFirst(), other.getFirst())
                    .append(this.getSecond(), other.getSecond())
                    .append(this.getThird(), other.getThird())
                    .append(this.getFourth(), other.getFourth())
                    .append(this.getFifth(), other.getFifth())
                    .append(this.getSixth(), other.getSixth())
                    .isEquals();
            // @formatter:on
        }
        return false;
    }

    /**
     * <p>
     * Returns a suitable hash code.
     * </p>
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        // @formatter:off
        return Objects.hash(this.getFirst(),
                this.getSecond(),
                this.getThird(),
                this.getFourth(),
                this.getFifth(),
                this.getSixth());
        // @formatter:on
    }

    /**
     * <p>
     * Returns a String representation of this hexa using the format
     * {@code ($first,$second,$third,$fourth,$fifth,$sixth)}.
     * </p>
     * 
     * @return a string describing this object, not null
     */
    @Override
    public String toString() {
        // @formatter:off
        return new StringBuilder()
                .append('(')
                .append(this.getFirst()).append(',')
                .append(this.getSecond()).append(',')
                .append(this.getThird()).append(',')
                .append(this.getFourth()).append(',')
                .append(this.getFifth()).append(',')
                .append(this.getSixth()).append(')')
                .toString();
     // @formatter:on
    }

    /**
     * <p>
     * Formats the receiver using the given format.
     * </p>
     * 
     * <p>
     * This uses {@link java.util.Formattable} to perform the formatting. Six
     * variables may be used to embed the elements. Use {@code %1$s} for the
     * first element (key) and {@code %2$s} for the second element (value)...
     * The default format used by {@code toString()} is
     * {@code (%1$s,%2$s,%3$s,%4$s,%5$s,%6$s)}.
     * </p>
     * 
     * @param format
     *            the format string, optionally containing {@code %1$s},
     *            {@code %2$s}, {@code %3$s}, {@code %4$s}, {@code %5$s} and
     *            {@code %6$s}, not null
     * @return the formatted string, not null
     */
    public String toString(final String format) {
        // @formatter:off
        return String.format(format,
                this.getFirst(),
                this.getSecond(),
                this.getThird(),
                this.getFourth(),
                this.getFifth(),
                this.getSixth());
        // @formatter:on
    }
}
