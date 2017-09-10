/*-
8 * #%L
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
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <p>
 * Operations on {@code Object}.
 * </p>
 *
 * <p>
 * This class tries to handle {@code null} input gracefully. An exception will
 * generally not be thrown for a {@code null} input. Each method documents its
 * behaviour in more detail.
 * </p>
 *
 * <p>
 * #ThreadSafe#
 * </p>
 *
 * @since Aug 15, 2016
 * @author Gilles
 *
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    private static final String ERROR = "The parameter '{}' cannot be null";

    private static final String OBJECTS_ERROR = StringUtils.inject(ERROR, "objects");
    private static final String PREDICATE_ERROR = StringUtils.inject(ERROR, "predicate");
    private static final String TRANSFORMER_ERROR = StringUtils.inject(ERROR, "transformer");
    private static final String SUPPLIER_ERROR = StringUtils.inject(ERROR, "defaultValueSupplier");
    private static final String THROWABLE_SUPPLIER_ERROR = StringUtils.inject(ERROR, "throwableSupplier");

    /**
     * Returns a default value if the object passed is {@code null}.
     * 
     * <pre>
     * ObjectUtils.defaultIfNull(null, null, o -&gt; o.getTitle())              = null
     * ObjectUtils.defaultIfNull(null, "",  o -&gt; o.getTitle())               = ""
     * ObjectUtils.defaultIfNull(null, "zz", o -&gt; o.getTitle())              = "zz"
     * ObjectUtils.defaultIfNull("abc", "NO_TEXT", o -&gt; o.toUpperCase())     = "ABC"
     * ObjectUtils.defaultIfNull("false", Boolean.TRUE, Boolean::parseBoolean)  = Boolean.TRUE
     * </pre>
     * 
     * @param object
     *            the object to check
     * @param defaultObject
     *            the default value
     * @param transformer
     *            the object mapper
     * @param <T>
     *            the type of the object to check
     * @param <X>
     *            the type of the output
     * @return transformed {@code object} if not {@code null}, default value
     *         otherwise
     * @throws NullPointerException
     *             if {@code transformer} is {@code null}
     */
    public static <T, X> X defaultIfNull(final T object, final X defaultObject, final Function<T, X> transformer) {
        Objects.requireNonNull(transformer, TRANSFORMER_ERROR);

        return object != null ? transformer.apply(object) : defaultObject;
    }

    /**
     * Returns a default value if the object passed is {@code null}.
     *
     * <pre>
     * ObjectUtils.defaultIfNull(null, () -&gt; null)          = null
     * ObjectUtils.defaultIfNull(null, () -&gt; "")            = ""
     * ObjectUtils.defaultIfNull(null, () -&gt; "zz")          = "zz"
     * ObjectUtils.defaultIfNull("abc", () -&gt; *)            = "abc"
     * ObjectUtils.defaultIfNull(Boolean.TRUE, () -&gt; *)     = Boolean.TRUE
     * </pre>
     *
     * @param object
     *            the {@code Object} to test, may be {@code null}
     * @param defaultValueSupplier
     *            the default value supplier, cannot be {@code null}, may supply
     *            {@code null}
     * @param <T>
     *            the type of the object
     * @return {@code object} if it is not {@code null}, defaultValue otherwise
     * @throws NullPointerException
     *             if supplier is {@code null}
     */
    public static <T> T defaultIfNull(final T object, final Supplier<? extends T> defaultValueSupplier) {
        Objects.requireNonNull(defaultValueSupplier, "The parameter defaultValueSupplier cannot be null");

        return object != null ? object : defaultValueSupplier.get();
    }

    /**
     * Returns a default value if the object doesn't match the predicate.
     * 
     * <pre>
     * ObjectUtils.defaultIf(Objects::nonNull, null, () -&gt; null)          = null
     * ObjectUtils.defaultIf(Objects::nonNull, null, () -&gt; "")            = ""
     * ObjectUtils.defaultIf(Objects::nonNull, null, () -&gt; "zz")          = "zz"
     * ObjectUtils.defaultIf(Objects::nonNull, "abc", () -&gt; *)            = "abc"
     * ObjectUtils.defaultIf(Objects::nonNull, Boolean.TRUE, () -&gt; *)     = Boolean.TRUE
     * </pre>
     * 
     * @param predicate
     *            the predicate (cannot be {@code null})
     * @param object
     *            the {@code Object} to test, may be {@code null}
     * @param defaultValueSupplier
     *            the default value supplier, cannot be {@code null}, may supply
     *            {@code null}
     * @param <T>
     *            the type of the object
     * @return {@code object} if it matches the predicate, defaultValue
     *         otherwise
     * @throws NullPointerException
     *             if {@code predicate} or {@code defaultValueSupplier} are
     *             {@code null}
     */
    public static <T> T defaultIf(final Predicate<T> predicate, final T object, final Supplier<? extends T> defaultValueSupplier) {
        Objects.requireNonNull(predicate, PREDICATE_ERROR);
        Objects.requireNonNull(defaultValueSupplier, SUPPLIER_ERROR);

        return predicate.test(object) ? object : defaultValueSupplier.get();
    }

    /**
     * Returns a default value if the object doesn't match the predicate.
     * 
     * <pre>
     * ObjectUtils.defaultIf(Objects::nonNull, null, null)          = null
     * ObjectUtils.defaultIf(Objects::nonNull, null, "")            = ""
     * ObjectUtils.defaultIf(Objects::nonNull, null, "zz")          = "zz"
     * ObjectUtils.defaultIf(Objects::nonNull, "abc", *)            = "abc"
     * ObjectUtils.defaultIf(Objects::nonNull, Boolean.TRUE, *)     = Boolean.TRUE
     * </pre>
     * 
     * @param predicate
     *            the predicate (cannot be {@code null})
     * @param object
     *            the {@code Object} to test, may be {@code null}
     * @param defaultValue
     *            the default value, may be {@code null}
     * @param <T>
     *            the type of the object
     * @return {@code object} if it matches the predicate, defaultValue
     *         otherwise
     * @throws NullPointerException
     *             if {@code predicate} is {@code null}
     */
    public static <T> T defaultIf(final Predicate<T> predicate, final T object, final T defaultValue) {
        Objects.requireNonNull(predicate, PREDICATE_ERROR);

        return predicate.test(object) ? object : defaultValue;
    }

    /**
     * Returns a default value if the object doesn't match the predicate.
     *
     * <pre>
     * ObjectUtils.defaultIf(Objects::nonNull, null, null, o -&gt; o.getTitle())              = null
     * ObjectUtils.defaultIf(Objects::nonNull, null, "",  o -&gt; o.getTitle())               = ""
     * ObjectUtils.defaultIf(Objects::nonNull, null, "zz", o -&gt; o.getTitle())              = "zz"
     * ObjectUtils.defaultIf(Objects::nonNull, "abc", "NO_TEXT", o -&gt; o.toUpperCase())     = "ABC"
     * ObjectUtils.defaultIf(Objects::nonNull, "false", Boolean.TRUE, Boolean::parseBoolean)  = Boolean.TRUE
     * </pre>
     * 
     * @param predicate
     *            the predicate (cannot be {@code null})
     * @param object
     *            the {@code Object} to test, may be {@code null}
     * @param defaultObject
     *            the default value
     * @param transformer
     *            the object mapper
     * @param <T>
     *            the type of the object
     * @param <X>
     *            the type of output
     * @return {@code object} if it matches the predicate, defaultValue
     *         otherwise
     * @throws NullPointerException
     *             if {@code predicate} or {@code transformer} are {@code null}
     */
    public static <T, X> X defaultIf(final Predicate<T> predicate, final T object, final X defaultObject,
            final Function<T, X> transformer) {
        Objects.requireNonNull(predicate, PREDICATE_ERROR);
        Objects.requireNonNull(transformer, TRANSFORMER_ERROR);

        return predicate.test(object) ? transformer.apply(object) : defaultObject;
    }

    /**
     * Check if all objects are {@code null}.
     * 
     * <p>
     * precondition: {@code objects} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * ObjectUtils.allNull(5, true); // =&gt; false
     * ObjectUtils.allNull(5, null); // =&gt; false
     * ObjectUtils.allNull(null, null); // =&gt; true
     * ObjectUtils.allNull((Object) null); // =&gt; true
     * 
     * ObjectUtils.allNull((Object[]) null); // =&gt; throw a NullPointerException
     * </pre>
     * 
     * @param objects
     *            The list of objects to check
     * @return true, if all are {@code null}
     */
    public static boolean allNull(final Object... objects) {
        Objects.requireNonNull(objects, OBJECTS_ERROR);

        boolean notNull = false;
        for (Object object : objects) {
            notNull |= object != null;
        }
        return !notNull;
    }

    /**
     * Check if any objects are {@code null}.
     * 
     * <p>
     * precondition: {@code objects} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * ObjectUtils.anyNull(5, true); // =&gt; false
     * ObjectUtils.anyNull(5, null); // =&gt; true
     * ObjectUtils.anyNull(null, null); // =&gt; true
     * ObjectUtils.anyNull((Object) null); // =&gt; true
     * 
     * ObjectUtils.anyNull((Object[]) null); // =&gt; throw a NullPointerException
     * </pre>
     * 
     * @param objects
     *            The list of objects to check
     * @return true, if any are {@code null}
     */
    public static boolean anyNull(final Object... objects) {
        Objects.requireNonNull(objects, OBJECTS_ERROR);

        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if all objects are not {@code null}.
     * 
     * <p>
     * precondition: {@code objects} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * ObjectUtils.allNotNull(5, true); // =&gt; true
     * ObjectUtils.allNotNull(5, null); // =&gt; false
     * ObjectUtils.allNotNull(null, null); // =&gt; false
     * ObjectUtils.allNotNull((Object) null); // =&gt; false
     * 
     * ObjectUtils.allNotNull((Object[]) null);
     * // =&gt; throw a NullPointerException
     * </pre>
     * 
     * @param objects
     *            The list of objects to check
     * @return true, if all are {@code null}
     */
    public static boolean allNotNull(final Object... objects) {
        Objects.requireNonNull(objects, OBJECTS_ERROR);

        boolean areNull = false;
        for (Object object : objects) {
            areNull |= object == null;
        }
        return !areNull;
    }

    /**
     * Check if any objects are not{@code null}.
     * 
     * <p>
     * precondition: {@code objects} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * ObjectUtils.anyNotNull(5, true); // =&gt; true
     * ObjectUtils.anyNotNull(5, null); // =&gt; true
     * ObjectUtils.anyNotNull(null, null); // =&gt; false
     * ObjectUtils.anyNotNull((Object) null); // =&gt; false
     * 
     * ObjectUtils.anyNotNull((Object[]) null);
     * // =&gt; throw a NullPointerException
     * </pre>
     * 
     * @param objects
     *            The list of objects to check
     * @return true, if any are {@code null}
     */
    public static boolean anyNotNull(final Object... objects) {
        Objects.requireNonNull(objects, OBJECTS_ERROR);

        for (Object object : objects) {
            if (object != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the {@code object} is not {@code null}, otherwise throws the
     * {@code throwableSupplier}.
     * 
     * <p>
     * precondition: {@code throwableSupplier} cannot be {@code null}
     * </p>
     * 
     * <pre>
     * String object1 = ObjectUtils.requireNonNull("test", Exception::new);
     * // -&gt; set object
     * String object2 = ObjectUtils.requireNonNull(null, Exception::new);
     * // -&gt; throws an exception
     * String object3 = ObjectUtils.requireNonNull("test", null);
     * // -&gt; throws a NullPointerException
     * </pre>
     * 
     * @param object
     *            the object to check
     * @param throwableSupplier
     *            the throwable supplier
     * @param <T>
     *            the object type
     * @param <E>
     *            the throwable type
     * @return the object if not {@code null}
     * @throws E
     *             exception thrown if object is {@code null}
     * @throws NullPointerException
     *             if throwable supplier is {@code null}
     */
    public static <T, E extends Throwable> T requireNonNull(final T object, final Supplier<E> throwableSupplier) throws E {
        Objects.requireNonNull(throwableSupplier, THROWABLE_SUPPLIER_ERROR);
        if (object == null) {
            throw throwableSupplier.get();
        }
        return object;
    }

    /**
     * Check if all objects are not {@code null}.
     * 
     * @param objects
     *            the objects to check
     * @throws NullPointerException
     *             if one object is {@code null}
     */
    @SafeVarargs
    public static void requireNonNulls(final Object... objects) {
        Objects.requireNonNull(objects, OBJECTS_ERROR);

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                throw new NullPointerException(new StringBuilder("Object at index '").append(i).append("' cannot be null").toString());
            }
        }
    }

    /**
     * Get the transformed value if not {@code null}
     * 
     * @param object
     *            the object to transform
     * @param transformer
     *            the transformer function (cannot be {@code null})
     * @param <T>
     *            the type of the object to check
     * @param <X>
     *            the type of the output
     * @return an {@link Optional} of the transformed value or
     *         {@link Optional#empty()}
     */
    public static <T, X> Optional<X> ifNotNull(final T object, final Function<T, X> transformer) {
        Objects.requireNonNull(transformer, TRANSFORMER_ERROR);

        return object != null ? Optional.ofNullable(transformer.apply(object)) : Optional.empty();
    }

    /**
     * Get the transformed value if not {@code null}
     * 
     * @param predicate
     *            the predicate (cannot be {@code null})
     * @param object
     *            the object to transform
     * @param transformer
     *            the transformer function (cannot be {@code null})
     * @param <T>
     *            the type of the object to check
     * @param <X>
     *            the type of the output
     * @return an {@link Optional} of the transformed value or
     *         {@link Optional#empty()}
     */
    public static <T, X> Optional<X> ifPredicate(final Predicate<T> predicate, final T object, final Function<T, X> transformer) {
        Objects.requireNonNull(predicate, PREDICATE_ERROR);
        Objects.requireNonNull(transformer, TRANSFORMER_ERROR);

        return predicate.test(object) ? Optional.ofNullable(transformer.apply(object)) : Optional.empty();
    }
}