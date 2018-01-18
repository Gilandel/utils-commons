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
package fr.landel.utils.commons;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import fr.landel.utils.commons.function.QuadConsumer;
import fr.landel.utils.commons.function.QuadFunction;
import fr.landel.utils.commons.function.TriConsumer;
import fr.landel.utils.commons.function.TriFunction;

/**
 * Java 8 Stream utility class
 *
 * @since Aug 2, 2017
 * @author Gilles Landel
 *
 */
public class StreamUtils {

    private static final String ERROR_PREDICATE = "predicate";
    private static final String ERROR_CONSUMER = "consumer";
    private static final String ERROR_FUNCTION = "function";
    private static final String ERROR_AFTER = "after";
    private static final String ERROR_BEFORE = "before";
    private static final String ERROR_PREDICATES = "predicates cannot be null or empty";
    private static final String ERROR_CONSUMERS = "consumers cannot be null or empty";

    /**
     * Constructor.
     * 
     */
    private StreamUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Negates the predicate
     * 
     * @param predicate
     *            the predicate
     * @param <T>
     *            the type of object tested by the predicate
     * @return the negated predicate
     */
    public static <T> Predicate<T> not(final Predicate<T> predicate) {
        return predicate.negate();
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and others
     * 
     * @param predicate
     *            the base predicate
     * @param predicates
     *            the predicate to append with and operator
     * @param <T>
     *            the type of object tested by the predicate
     * @return the composed predicate
     * @throws NullPointerException
     *             if {@code predicate} or one of {@code predicates} are
     *             {@code null}
     * @throws IllegalArgumentException
     *             if {@code predicates} is {@code null} or empty
     */
    @SafeVarargs
    public static <T> Predicate<T> and(final Predicate<T> predicate, final Predicate<? super T>... predicates) {
        Predicate<T> result = Objects.requireNonNull(predicate, ERROR_PREDICATE);
        if (ArrayUtils.isEmpty(predicates)) {
            throw new IllegalArgumentException(ERROR_PREDICATES);
        }
        for (Predicate<? super T> p : predicates) {
            result = result.and(p);
        }
        return result;
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and others
     * 
     * @param predicate
     *            the base predicate
     * @param predicates
     *            the predicate to append with and operator
     * @param <T>
     *            the type of object tested by the predicate
     * @return the composed predicate
     * @throws NullPointerException
     *             if {@code predicate} or any of {@code predicates} are
     *             {@code null}
     * @throws IllegalArgumentException
     *             if {@code predicates} is {@code null} or empty
     */
    @SafeVarargs
    public static <T> Predicate<T> or(final Predicate<T> predicate, final Predicate<? super T>... predicates) {
        Predicate<T> result = Objects.requireNonNull(predicate, ERROR_PREDICATE);
        if (ArrayUtils.isEmpty(predicates)) {
            throw new IllegalArgumentException(ERROR_PREDICATES);
        }
        for (Predicate<? super T> p : predicates) {
            result = result.or(p);
        }
        return result;
    }

    /**
     * Returns a composed {@code consumer} that performs, in sequence, this
     * operation followed by the {@code consumers} operations. If performing
     * either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the
     * {@code consumers} operations will not be performed.
     * 
     * @param consumer
     *            the current operation
     * @param consumers
     *            the operations performed after this operation
     * @param <T>
     *            the type of consumer parameter
     * @return the composed consumer
     * @throws NullPointerException
     *             if {@code predicate} or any of {@code predicates} are
     *             {@code null}
     * @throws IllegalArgumentException
     *             if {@code consumers} is {@code null} or empty
     */
    @SafeVarargs
    public static <T> Consumer<T> andThen(final Consumer<T> consumer, final Consumer<? super T>... consumers) {
        Consumer<T> result = Objects.requireNonNull(consumer, ERROR_CONSUMER);
        if (ArrayUtils.isEmpty(consumers)) {
            throw new IllegalArgumentException(ERROR_CONSUMERS);
        }
        for (Consumer<? super T> c : consumers) {
            result = result.andThen(c);
        }
        return result;
    }

    /**
     * Returns a composed {@code consumer} that performs, in sequence, this
     * operation followed by the {@code consumers} operations. If performing
     * either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the
     * {@code consumers} operations will not be performed.
     * 
     * @param consumer
     *            the current operation
     * @param consumers
     *            the operations performed after this operation
     * @param <T>
     *            the type of first consumer parameter
     * @param <U>
     *            the type of second consumer parameter
     * @return the composed consumer
     * @throws NullPointerException
     *             if {@code predicate} or any of {@code predicates} are
     *             {@code null}
     * @throws IllegalArgumentException
     *             if {@code consumers} is {@code null} or empty
     */
    @SafeVarargs
    public static <T, U> BiConsumer<T, U> andThen(final BiConsumer<T, U> consumer, final BiConsumer<? super T, ? super U>... consumers) {
        BiConsumer<T, U> result = Objects.requireNonNull(consumer, ERROR_CONSUMER);
        if (ArrayUtils.isEmpty(consumers)) {
            throw new IllegalArgumentException(ERROR_CONSUMERS);
        }
        for (BiConsumer<? super T, ? super U> c : consumers) {
            result = result.andThen(c);
        }
        return result;
    }

    /**
     * Returns a composed {@code consumer} that performs, in sequence, this
     * operation followed by the {@code consumers} operations. If performing
     * either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the
     * {@code consumers} operations will not be performed.
     * 
     * @param consumer
     *            the current operation
     * @param consumers
     *            the operations performed after this operation
     * @param <T>
     *            the type of first consumer parameter
     * @param <U>
     *            the type of second consumer parameter
     * @param <V>
     *            the type of third consumer parameter
     * @return the composed consumer
     * @throws NullPointerException
     *             if {@code predicate} or any of {@code predicates} are
     *             {@code null}
     * @throws IllegalArgumentException
     *             if {@code consumers} is {@code null} or empty
     */
    @SafeVarargs
    public static <T, U, V> TriConsumer<T, U, V> andThen(final TriConsumer<T, U, V> consumer,
            final TriConsumer<? super T, ? super U, ? super V>... consumers) {
        TriConsumer<T, U, V> result = Objects.requireNonNull(consumer, ERROR_CONSUMER);
        if (ArrayUtils.isEmpty(consumers)) {
            throw new IllegalArgumentException(ERROR_CONSUMERS);
        }
        for (TriConsumer<? super T, ? super U, ? super V> c : consumers) {
            result = result.andThen(c);
        }
        return result;
    }

    /**
     * Returns a composed {@code consumer} that performs, in sequence, this
     * operation followed by the {@code consumers} operations. If performing
     * either operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception, the
     * {@code consumers} operations will not be performed.
     * 
     * @param consumer
     *            the current operation
     * @param consumers
     *            the operations performed after this operation
     * @param <T>
     *            the type of first consumer parameter
     * @param <U>
     *            the type of second consumer parameter
     * @param <V>
     *            the type of third consumer parameter
     * @param <W>
     *            the type of fourth consumer parameter
     * @return the composed consumer
     * @throws NullPointerException
     *             if {@code predicate} or any of {@code predicates} are
     *             {@code null}
     * @throws IllegalArgumentException
     *             if {@code consumers} is {@code null} or empty
     */
    @SafeVarargs
    public static <T, U, V, W> QuadConsumer<T, U, V, W> andThen(final QuadConsumer<T, U, V, W> consumer,
            final QuadConsumer<? super T, ? super U, ? super V, ? super W>... consumers) {
        QuadConsumer<T, U, V, W> result = Objects.requireNonNull(consumer, ERROR_CONSUMER);
        if (ArrayUtils.isEmpty(consumers)) {
            throw new IllegalArgumentException(ERROR_CONSUMERS);
        }
        for (QuadConsumer<? super T, ? super U, ? super V, ? super W> c : consumers) {
            result = result.andThen(c);
        }
        return result;
    }

    /**
     * Returns a composed function that first applies this function to its
     * input, and then applies the {@code after} function to the result. If
     * evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * 
     * @param function
     *            the current function
     * @param after
     *            the function to call after the current one
     * @param <T>
     *            the type of parameter of the current function
     * @param <R>
     *            the type of the return of the current function and the type of
     *            the parameter of the after function
     * @param <V>
     *            the type of the function return
     * @return the composed function
     * @throws NullPointerException
     *             if {@code function} or {@code after} are {@code null}
     */
    public static <T, R, V> Function<T, V> andThen(final Function<T, R> function, final Function<R, V> after) {
        return Objects.requireNonNull(function, ERROR_FUNCTION).andThen(Objects.requireNonNull(after, ERROR_AFTER));
    }

    /**
     * Returns a composed function that first applies this function to its
     * input, and then applies the {@code after} function to the result. If
     * evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * 
     * @param function
     *            the current function
     * @param after
     *            the function to call after the current one
     * @param <T>
     *            the type of the first parameter of the current function
     * @param <X>
     *            the type of the second parameter of the current function
     * @param <R>
     *            the type of the return of the current function and the type of
     *            the parameter of the after function
     * @param <V>
     *            the type of the function return
     * @return the composed function
     * @throws NullPointerException
     *             if {@code function} or {@code after} are {@code null}
     */
    public static <T, X, R, V> BiFunction<T, X, V> andThen(final BiFunction<T, X, R> function, final Function<R, V> after) {
        return Objects.requireNonNull(function, ERROR_FUNCTION).andThen(Objects.requireNonNull(after, ERROR_AFTER));
    }

    /**
     * Returns a composed function that first applies this function to its
     * input, and then applies the {@code after} function to the result. If
     * evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * 
     * @param function
     *            the current function
     * @param after
     *            the function to call after the current one
     * @param <T>
     *            the type of the first parameter of the current function
     * @param <X>
     *            the type of the second parameter of the current function
     * @param <Y>
     *            the type of the third parameter of the current function
     * @param <R>
     *            the type of the return of the current function and the type of
     *            the parameter of the after function
     * @param <V>
     *            the type of the function return
     * @return the composed function
     * @throws NullPointerException
     *             if {@code function} or {@code after} are {@code null}
     */
    public static <T, X, Y, R, V> TriFunction<T, X, Y, V> andThen(final TriFunction<T, X, Y, R> function, final Function<R, V> after) {
        return Objects.requireNonNull(function, ERROR_FUNCTION).andThen(Objects.requireNonNull(after, ERROR_AFTER));
    }

    /**
     * Returns a composed function that first applies this function to its
     * input, and then applies the {@code after} function to the result. If
     * evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * 
     * @param function
     *            the current function
     * @param after
     *            the function to call after the current one
     * @param <T>
     *            the type of the first parameter of the current function
     * @param <X>
     *            the type of the second parameter of the current function
     * @param <Y>
     *            the type of the third parameter of the current function
     * @param <Z>
     *            the type of the fourth parameter of the current function
     * @param <R>
     *            the type of the return of the current function and the type of
     *            the parameter of the after function
     * @param <V>
     *            the type of the function return
     * @return the composed function
     * @throws NullPointerException
     *             if {@code function} or {@code after} are {@code null}
     */
    public static <T, X, Y, Z, R, V> QuadFunction<T, X, Y, Z, V> andThen(final QuadFunction<T, X, Y, Z, R> function,
            final Function<R, V> after) {
        return Objects.requireNonNull(function, ERROR_FUNCTION).andThen(Objects.requireNonNull(after, ERROR_AFTER));
    }

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result. If
     * evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     * 
     * @param function
     *            the current function
     * @param before
     *            the function to call before the current one
     * @param <T>
     *            the type of parameter of the current function and the type of
     *            the parameter of the after function
     * @param <R>
     *            the type of the return of the current function
     * @param <V>
     *            the type of the parameter of the before function
     * @return the composed function
     * @throws NullPointerException
     *             if {@code function} or {@code after} are {@code null}
     */
    public static <T, R, V> Function<V, R> compose(final Function<T, R> function, final Function<V, T> before) {
        return Objects.requireNonNull(function, ERROR_FUNCTION).compose(Objects.requireNonNull(before, ERROR_BEFORE));
    }
}
