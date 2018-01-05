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
package samples;

import java.util.Objects;
import java.util.Optional;

import fr.landel.utils.commons.CastUtils;
import fr.landel.utils.commons.ClassUtils;
import fr.landel.utils.commons.function.ThrowableSupplier;

/**
 * Just a try test for {@link ThrowableSupplier}
 * 
 * <p>
 * Not good for production, because this hides bugs. (can only be used, if a
 * library throws {@link Exception} or {@link Throwable}...)
 * </p>
 *
 * @since Aug 11, 2016
 * @author Gilles
 *
 */
public class Try {

    public static <E extends Throwable> Optional<Catch<E>> that(final ThrowableSupplier<E> exceptionSupplier) {
        Objects.requireNonNull(exceptionSupplier, "exceptionSupplier");

        try {
            exceptionSupplier.throwException();
        } catch (final Throwable e) {
            return Optional.of(new Catch<E>(CastUtils.cast(e)));
        }
        return Optional.empty();
    }

    public static class Catch<E extends Throwable> {
        private final E exception;
        private final Class<E> clazz;

        private Catch(final E exception) {
            this.exception = exception;
            this.clazz = ClassUtils.getClass(exception);
        }

        public boolean is(final Class<?> clazz) {
            return Objects.requireNonNull(clazz, "clazz").isAssignableFrom(this.clazz);
        }

        public boolean has(final String message) {
            return Objects.equals(this.exception.getMessage(), message);
        }

        public E get() {
            return this.exception;
        }
    }
}
