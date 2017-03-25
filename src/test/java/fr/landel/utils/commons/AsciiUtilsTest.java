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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.function.Function;
import java.util.function.IntPredicate;

import org.junit.Test;

/**
 * Check {@link AsciiUtils}
 *
 * @since Mar 25, 2017
 * @author Gilles
 *
 */
public class AsciiUtilsTest extends AbstractTest {

    /**
     * Test constructor for {@link AsciiUtils} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(AsciiUtils.class));
    }

    @Test
    public void test() {
        final String number = "0123456789";
        final String alphaLC = "abcdefghijklmnopqrstuvwxyz";
        final String alphaUC = alphaLC.toUpperCase();
        final String alpha = alphaLC + alphaUC;
        final String alphanumeric = number + alpha;

        final Function<Integer, IntPredicate> p = i -> c -> c == i;

        for (int i = AsciiUtils.MIN; i <= AsciiUtils.MAX; i++) {
            assertEquals(number.chars().anyMatch(p.apply(i)), AsciiUtils.IS_NUMERIC.test(i));
            assertEquals(alphaLC.chars().anyMatch(p.apply(i)), AsciiUtils.IS_ALPHA_LC.test(i));
            assertEquals(alphaUC.chars().anyMatch(p.apply(i)), AsciiUtils.IS_ALPHA_UC.test(i));
            assertEquals(alpha.chars().anyMatch(p.apply(i)), AsciiUtils.IS_ALPHA.test(i));
            assertEquals(alphanumeric.chars().anyMatch(p.apply(i)), AsciiUtils.IS_ALPHANUMERIC.test(i));
        }
    }
}
