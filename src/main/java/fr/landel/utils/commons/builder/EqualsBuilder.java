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
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * {@link org.apache.commons.lang3.builder.EqualsBuilder}
 * 
 * @since Nov 15, 2016
 * @author Gilles
 *
 */
public class EqualsBuilder extends org.apache.commons.lang3.builder.EqualsBuilder {

    /**
     * Test if both {@link Object} returned by the {@code getter} function are
     * equal using their {@code equals} method. The {@code getter} method is
     * only applied if both {@link Object} are not {@code null}.
     * 
     * @param lhs
     *            the first object
     * @param rhs
     *            the second object
     * @param getter
     *            the function to apply if both objects are not {@code null}
     *            (required, throws a {@link NullPointerException} if
     *            {@code null})
     * @param <T>
     *            the check object type
     * @param <X>
     *            the sub type
     * @return the current builder
     */
    public <T, X> EqualsBuilder append(final T lhs, final T rhs, final Function<T, X> getter) {
        return this.append(lhs, rhs, getter, null);
    }

    /**
     * Test if both {@link Object} returned by the {@code getter} function are
     * equal using their {@code equals} method. The {@code getter} method is
     * only applied if both {@link Object} are not {@code null}. The predicate
     * function is only applied if both values are not {@code null}.
     * 
     * @param lhs
     *            the first object
     * @param rhs
     *            the second object
     * @param getter
     *            the function to apply if both objects are not {@code null}
     *            (required, throws a {@link NullPointerException} if
     *            {@code null})
     * @param predicate
     *            the function to check, if get parameter are equals (if
     *            {@code null}, use the default {@code equals} method)
     * @param <T>
     *            the check object type
     * @param <X>
     *            the sub type
     * @return the current builder
     */
    public <T, X> EqualsBuilder append(final T lhs, final T rhs, final Function<T, X> getter, final BiPredicate<X, X> predicate) {
        Objects.requireNonNull(getter);

        if (!this.isEquals()) {
            return this;
        }

        if (lhs != null && rhs != null) {
            final X v1 = getter.apply(lhs);
            final X v2 = getter.apply(rhs);
            if (predicate != null) {
                if (v1 != null && v2 != null) {
                    this.setEquals(predicate.test(v1, v2));
                } else {
                    // check if both values are null
                    this.setEquals(v1 == v2);
                }
            } else {
                this.append(v1, v2);
            }
        } else {
            this.setEquals(lhs == null && rhs == null);
        }

        return this;
    }
}
