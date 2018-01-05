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

/**
 * <p>
 * A mutable pair consisting of two {@code Object} elements.
 * </p>
 * 
 * <p>
 * Not #ThreadSafe#
 * </p>
 *
 * @param <T>
 *            the element type
 * 
 * @see org.apache.commons.lang3.tuple.Pair
 *
 * @since Jul 26, 2016
 * @author Gilles
 *
 */
public class MutablePairIso<T> extends PairIso<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6102457045775716086L;

    /** Left object */
    public T left;
    /** Right object */
    public T right;

    /**
     * Create a new pair instance of two nulls.
     */
    public MutablePairIso() {
        super();
    }

    /**
     * Create a new mutable pair instance.
     *
     * @param left
     *            the left value, may be null
     * @param right
     *            the right value, may be null
     */
    public MutablePairIso(final T left, final T right) {
        this();
        this.left = left;
        this.right = right;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getLeft() {
        return left;
    }

    /**
     * Sets the left element of the pair.
     * 
     * @param left
     *            the new value of the left element, may be null
     */
    public void setLeft(final T left) {
        this.left = left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getRight() {
        return right;
    }

    /**
     * Sets the right element of the pair.
     * 
     * @param right
     *            the new value of the right element, may be null
     */
    public void setRight(final T right) {
        this.right = right;
    }

    /**
     * Sets the {@code Map.Entry} key. This sets the left element of the pair.
     * 
     * @param key
     *            the left value to set, not null
     * @return the old key for the left element
     */
    public T setKey(final T key) {
        final T result = getKey();
        setLeft(key);
        return result;
    }

    /**
     * Sets the {@code Map.Entry} value. This sets the right element of the
     * pair.
     * 
     * @param value
     *            the right value to set, not null
     * @return the old value for the right element
     */
    @Override
    public T setValue(final T value) {
        final T result = getRight();
        setRight(value);
        return result;
    }
}
