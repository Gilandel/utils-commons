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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

/**
 * Check {@link StreamUtils}
 *
 * @since Aug 3, 2017
 * @author Gilles Landel
 *
 */
public class StreamUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link StreamUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(StreamUtils.class));
    }

    /**
     * Test method for {@link StreamUtils#not(java.util.function.Predicate)}.
     */
    @Test
    public void testNot() {
        assertTrue(StreamUtils.not(Objects::isNull).test("not null"));
    }

    /**
     * Test method for {@link StreamUtils#and(java.util.function.Predicate,
     * java.util.function.Predicate<? super T>[])}.
     */
    @Test
    public void testAnd() {
        assertTrue(StreamUtils.and(Objects::nonNull, StringUtils::isAllLowerCase).test("and"));
        assertFalse(StreamUtils.and(Objects::isNull, StringUtils::isAllLowerCase).test("and"));
        assertFalse(StreamUtils.and(Objects::nonNull, StringUtils::isAllUpperCase).test("and"));
        assertFalse(StreamUtils.and(Objects::isNull, StringUtils::isAllUpperCase).test("and"));

        assertException(() -> StreamUtils.and(null, StringUtils::isAllLowerCase), NullPointerException.class);
        assertException(() -> StreamUtils.and(Objects::isNull), IllegalArgumentException.class, "predicates cannot be null or empty");
    }

    /**
     * Test method for {@link StreamUtils#or(java.util.function.Predicate,
     * java.util.function.Predicate<? super T>[])}.
     */
    @Test
    public void testOr() {
        assertTrue(StreamUtils.or(Objects::nonNull, StringUtils::isAllLowerCase).test("and"));
        assertTrue(StreamUtils.or(Objects::isNull, StringUtils::isAllLowerCase).test("and"));
        assertTrue(StreamUtils.or(Objects::nonNull, StringUtils::isAllUpperCase).test("and"));
        assertFalse(StreamUtils.or(Objects::isNull, StringUtils::isAllUpperCase).test("and"));

        assertException(() -> StreamUtils.or(null, StringUtils::isAllLowerCase), NullPointerException.class);
        assertException(() -> StreamUtils.or(Objects::isNull), IllegalArgumentException.class, "predicates cannot be null or empty");
    }
}
