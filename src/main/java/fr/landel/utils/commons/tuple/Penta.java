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
 * A penta consisting of five elements.
 * </p>
 * 
 * <p>
 * This class is an abstract implementation defining the basic API. It refers to
 * the elements as 'first', 'second', 'third', 'fourth' and 'fifth'.
 * </p>
 * 
 * <p>
 * Subclass implementations may be mutable or immutable. However, there is no
 * restriction on the type of the stored objects that may be stored. If mutable
 * objects are stored in the penta, then the penta itself effectively becomes
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
 *
 * @since Nov 16, 2017
 * @author Gilles
 *
 */
public abstract class Penta<A, B, C, D, E> extends AbstractPenta<A, B, C, D, E> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7301128872551217806L;

    /**
     * <p>
     * Obtains an immutable penta of from five objects inferring the generic
     * types.
     * </p>
     * 
     * <p>
     * This factory allows the penta to be created using inference to obtain the
     * generic types.
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
     * @return a penta formed from the five parameters, not null
     */
    public static <A, B, C, D, E> Penta<A, B, C, D, E> of(final A first, final B second, final C third, final D fourth, final E fifth) {
        return new ImmutablePenta<A, B, C, D, E>(first, second, third, fourth, fifth);
    }

    /**
     * <p>
     * Obtains a mutable penta of from five objects inferring the generic types.
     * </p>
     * 
     * <p>
     * This factory allows the penta to be created using inference to obtain the
     * generic types.
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
     * @return a penta formed from the five parameters, not null
     */
    public static <A, B, C, D, E> MutablePenta<A, B, C, D, E> ofMutable(final A first, final B second, final C third, final D fourth,
            final E fifth) {
        return new MutablePenta<A, B, C, D, E>(first, second, third, fourth, fifth);
    }
}
