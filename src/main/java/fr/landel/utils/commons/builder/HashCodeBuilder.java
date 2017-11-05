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
package fr.landel.utils.commons.builder;

import java.util.Objects;
import java.util.function.Function;

/**
 * {@link org.apache.commons.lang3.builder.HashCodeBuilder}
 * 
 * @since Nov 15, 2016
 * @author Gilles
 *
 */
public class HashCodeBuilder extends org.apache.commons.lang3.builder.HashCodeBuilder {

    /**
     * Append the {@code hashCode} returned by the {@code getter} function. The
     * {@code getter} method is only applied if the {@code object} is not
     * {@code null}.
     * 
     * @param object
     *            the first object
     * @param getter
     *            the function to apply if both objects are not {@code null}
     *            (required, throws a {@link NullPointerException} if
     *            {@code null})
     * @param <O>
     *            the check object type
     * @param <X>
     *            the sub type
     * @return the current builder
     */
    public <O, X> HashCodeBuilder append(final O object, final Function<O, X> getter) {
        Objects.requireNonNull(getter, "getter");
        if (object != null) {
            this.append(getter.apply(object));
        }
        return this;
    }
}
