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
 * 
 * <p>
 * An immutable hepta consisting of seven {@code Object} elements.
 * </p>
 * 
 * <p>
 * Although the implementation is immutable, there is no restriction on the
 * objects that may be stored. If mutable objects are stored in the hepta, then
 * the hepta itself effectively becomes mutable. The class is also
 * {@code final}, so a subclass can not add undesirable behaviour.
 * </p>
 * 
 * <p>
 * #ThreadSafe# if all hepta objects are thread-safe
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
 * @param <G>
 *            the seventh element type
 *
 * @since Nov 16, 2017
 * @author Gilles
 *
 */
public final class ImmutableHepta<A, B, C, D, E, F, G> extends AbstractImmutableHepta<A, B, C, D, E, F, G> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2206706642322589044L;

    /**
     * Create a new immutable hepta instance.
     *
     * @param first
     *            the first element, may be null
     * @param second
     *            the second element, may be null
     * @param third
     *            the third element, may be null
     * @param fourth
     *            the fourth element, may be null
     * @param fifth
     *            the fifth element, may be null
     * @param sixth
     *            the sixth element, may be null
     * @param seventh
     *            the seventh element, may be null
     */
    public ImmutableHepta(final A first, final B second, final C third, final D fourth, final E fifth, final F sixth, final G seventh) {
        super(first, second, third, fourth, fifth, sixth, seventh);
    }
}
