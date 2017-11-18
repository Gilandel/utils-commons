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
package fr.landel.utils.commons.tuple;

/**
 * <p>
 * An immutable triple consisting of three {@code Object} elements.
 * </p>
 * 
 * <p>
 * Although the implementation is immutable, there is no restriction on the
 * objects that may be stored. If mutable objects are stored in the triple, then
 * the triple itself effectively becomes mutable. The class is also
 * {@code final}, so a subclass can not add undesirable behaviour.
 * </p>
 * 
 * <p>
 * #ThreadSafe# if all triple objects are thread-safe
 * </p>
 *
 * @param <T>
 *            the element type
 * 
 * @see org.apache.commons.lang3.tuple.Triple
 *
 * @since Jul 26, 2016
 * @author Gilles
 *
 */
public final class ImmutableTripleIso<T> extends AbstractImmutableTripleIso<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4594913866105614916L;

    /**
     * Create a new immutable triple instance.
     *
     * @param left
     *            the left value, may be null
     * @param middle
     *            the middle value, may be null
     * @param right
     *            the right value, may be null
     */
    public ImmutableTripleIso(final T left, final T middle, final T right) {
        super(left, middle, right);
    }
}
