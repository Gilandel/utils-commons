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
 * A mutable generic consisting of {@code Object} elements.
 * </p>
 * 
 * <p>
 * Not #ThreadSafe#
 * </p>
 *
 * @param <T>
 *            the element type
 *
 * @since Jul 26, 2016
 * @author Gilles
 *
 */
public class MutableGeneric<T> extends AbstractImmutableGeneric<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8268554086505788682L;

    /**
     * Create a new immutable generic instance.
     *
     * @param objects
     *            the objects value, may be null but not the whole array, ex:
     *            <tt>(Object[]) null</tt>
     */
    @SafeVarargs
    public MutableGeneric(final T... objects) {
        super(objects);
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index
     *            index of the element to replace
     * @param element
     *            element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException
     *             if the <tt>set</tt> operation is not supported by this list
     * @throws ClassCastException
     *             if the class of the specified element prevents it from being
     *             added to this list
     * @throws NullPointerException
     *             if the specified element is null and this list does not
     *             permit null elements
     * @throws IllegalArgumentException
     *             if some property of the specified element prevents it from
     *             being added to this list
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     *             <tt>(index &lt; 0 || index &gt;= size())</tt>
     */
    public T set(final int index, final T element) {
        synchronized (this.getList()) {
            final T object = this.getList().get(index);

            this.getList().set(index, element);

            return object;
        }
    }
}
