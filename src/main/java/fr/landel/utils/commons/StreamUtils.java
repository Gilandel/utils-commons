/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilandel
 * %%
 * Authors: Gilles Landel
 * URL: https://github.com/Gilandel
 * 
 * This file is under Apache License, version 2.0 (2004).
 * #L%
 */
package fr.landel.utils.commons;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Java 8 Stream utility class
 *
 * @since Aug 2, 2017
 * @author Gilles Landel
 *
 */
public class StreamUtils {

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
     */
    @SafeVarargs
    public static <T> Predicate<T> and(final Predicate<T> predicate, final Predicate<? super T>... predicates) {
        Predicate<T> result = Objects.requireNonNull(predicate, "predicate");
        if (ArrayUtils.isEmpty(predicates)) {
            throw new IllegalArgumentException("predicates cannot be null or empty");
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
     */
    @SafeVarargs
    public static <T> Predicate<T> or(final Predicate<T> predicate, final Predicate<? super T>... predicates) {
        Predicate<T> result = Objects.requireNonNull(predicate, "predicate");
        if (ArrayUtils.isEmpty(predicates)) {
            throw new IllegalArgumentException("predicates cannot be null or empty");
        }
        for (Predicate<? super T> p : predicates) {
            result = result.or(p);
        }
        return result;
    }
}
