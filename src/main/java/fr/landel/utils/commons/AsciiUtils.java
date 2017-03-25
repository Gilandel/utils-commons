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

import java.util.function.IntPredicate;

/**
 * ASCII utility class
 *
 * @since Mar 25, 2017
 * @author Gilles
 *
 */
public class AsciiUtils {

    /**
     * Minimal ASCII index (\u0000 or "NUL")
     */
    public static final int MIN = 0;

    /**
     * Maximal ASCII index (\u00ff or "ÿ")
     */
    public static final int MAX = 255;

    /**
     * First number character in ASCII table (0)
     */
    public static final int NUM_FIRST = 48;

    /**
     * Last number character in ASCII table (9)
     */
    public static final int NUM_LAST = NUM_FIRST + 9;

    /**
     * First upper case alpha character in ASCII table (A)
     */
    public static final int ALPHA_UC_FIRST = 65;

    /**
     * Last upper case alpha character in ASCII table (Z)
     */
    public static final int ALPHA_UC_LAST = ALPHA_UC_FIRST + 25;

    /**
     * First lower case alpha character in ASCII table (a)
     */
    public static final int ALPHA_LC_FIRST = 97;

    /**
     * Last lower case alpha character in ASCII table (z)
     */
    public static final int ALPHA_LC_LAST = ALPHA_LC_FIRST + 25;

    /**
     * Predicate to check if a character is a number character /0-9/
     */
    public static final IntPredicate IS_NUMERIC = c -> NUM_FIRST <= c && c <= NUM_LAST;

    /**
     * Predicate to check if a character is an lower case alpha character /a-z/
     */
    public static final IntPredicate IS_ALPHA_LC = c -> ALPHA_LC_FIRST <= c && c <= ALPHA_LC_LAST;

    /**
     * Predicate to check if a character is an upper case alpha character /A-Z/
     */
    public static final IntPredicate IS_ALPHA_UC = c -> ALPHA_UC_FIRST <= c && c <= ALPHA_UC_LAST;

    /**
     * Predicate to check if a character is an alpha character /a-zA-Z/
     */
    public static final IntPredicate IS_ALPHA = c -> (ALPHA_UC_FIRST <= c && c <= ALPHA_UC_LAST)
            || (ALPHA_LC_FIRST <= c && c <= ALPHA_LC_LAST);

    /**
     * Predicate to check if a character is alphanumeric /0-9a-zA-Z/
     */
    public static final IntPredicate IS_ALPHANUMERIC = c -> (NUM_FIRST <= c && c <= NUM_LAST) || (ALPHA_UC_FIRST <= c && c <= ALPHA_UC_LAST)
            || (ALPHA_LC_FIRST <= c && c <= ALPHA_LC_LAST);

    /**
     * Hidden constructor
     */
    private AsciiUtils() {
        throw new UnsupportedOperationException();
    }
}
