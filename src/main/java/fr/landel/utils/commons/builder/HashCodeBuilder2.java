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
package fr.landel.utils.commons.builder;

import java.util.Objects;
import java.util.function.Function;

/**
 * {@link org.apache.commons.lang3.builder.HashCodeBuilder}
 * 
 * @since May 19, 2017
 * @author Gilles
 *
 */
public class HashCodeBuilder2<T> extends HashCodeBuilder {

    private T object;

    public HashCodeBuilder2(final T object) {
        super();

        this.object = object;
    }

    /**
     * Append the {@code hashCode} returned by the {@code getter} function. The
     * {@code getter} method is only applied if the {@code object} is not
     * {@code null}.
     * 
     * @param getter
     *            the function to apply if both objects are not {@code null}
     *            (required, throws a {@link NullPointerException} if
     *            {@code null})
     * @param <X>
     *            the sub type
     * @return the current builder
     */
    public <X> HashCodeBuilder2<T> append(final Function<T, X> getter) {
        Objects.requireNonNull(getter, "getter");
        if (this.object != null) {
            this.append(getter.apply(this.object));
        }
        return this;
    }
}
