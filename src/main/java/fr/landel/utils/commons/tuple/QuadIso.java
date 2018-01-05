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
 * A quad consisting of four elements of same type.
 * </p>
 * 
 * <p>
 * This class is an abstract implementation defining the basic API. It refers to
 * the elements as 'first', 'second', 'third' and 'fourth'.
 * </p>
 * 
 * <p>
 * Subclass implementations may be mutable or immutable. However, there is no
 * restriction on the type of the stored objects that may be stored. If mutable
 * objects are stored in the triple, then the triple itself effectively becomes
 * mutable.
 * </p>
 *
 * @param <T>
 *            the element type
 *
 * @since Jul 26, 2016
 * @author Gilles
 *
 */
public abstract class QuadIso<T> extends AbstractQuad<T, T, T, T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6033257956595424676L;

    /**
     * <p>
     * Obtains an immutable quad of from four objects inferring the generic
     * types.
     * </p>
     * 
     * <p>
     * This factory allows the quad to be created using inference to obtain the
     * generic types.
     * </p>
     * 
     * @param <T>
     *            the element types
     * @param first
     *            the first element, may be null
     * @param second
     *            the second element, may be null
     * @param third
     *            the third element, may be null
     * @param fourth
     *            the fourth element, may be null
     * @return a quad formed from the four parameters, not null
     */
    public static <T> QuadIso<T> of(final T first, final T second, final T third, final T fourth) {
        return new ImmutableQuadIso<T>(first, second, third, fourth);
    }

    /**
     * <p>
     * Obtains a mutable quad of from four objects inferring the generic types.
     * </p>
     * 
     * <p>
     * This factory allows the quad to be created using inference to obtain the
     * generic types.
     * </p>
     * 
     * @param <T>
     *            the element type
     * @param first
     *            the first element, may be null
     * @param second
     *            the second element, may be null
     * @param third
     *            the third element, may be null
     * @param fourth
     *            the fourth element, may be null
     * @return a quad formed from the four parameters, not null
     */
    public static <T> MutableQuadIso<T> ofMutable(final T first, final T second, final T third, final T fourth) {
        return new MutableQuadIso<T>(first, second, third, fourth);
    }
}
