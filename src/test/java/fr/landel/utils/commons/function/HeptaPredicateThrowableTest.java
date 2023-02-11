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
package fr.landel.utils.commons.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Check {@link HeptaPredicateThrowable}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class HeptaPredicateThrowableTest {

    private static final String ERROR1 = "The first argument is null";
    private static final String ERROR2 = "The second argument is null";
    private static final String ERROR3 = "Both arguments are null";
    private static final String ERROR4 = "First string is not in upper case";
    private static final String ERROR5 = "Second string is not in upper case";
    private static final String ERROR6 = "Both strings are not in upper case";

    private static final HeptaPredicateThrowable<String, String, Integer, String, String, String, String, IllegalArgumentException> P1 = (
            s1, s2, i, s3, s4, s5, s6) -> {
        if (s1 != null && s2 != null && i > 0) {
            return s1.length() > s2.length();
        } else if (s1 != null) {
            throw new IllegalArgumentException(ERROR2);
        } else if (s2 != null) {
            throw new IllegalArgumentException(ERROR1);
        }
        throw new IllegalArgumentException(ERROR3);
    };

    private static final HeptaPredicateThrowable<String, String, Integer, String, String, String, String, IllegalArgumentException> P2 = (
            s1, s2, i, s3, s4, s5, s6) -> {
        String s1u = s1.toUpperCase();
        String s2u = s2.toUpperCase();
        if (s1u.equals(s1) && s2u.equals(s2)) {
            return s1u.contains(s2u) || s2u.contains(s1u);
        } else if (!s1u.equals(s1)) {
            throw new IllegalArgumentException(ERROR4);
        } else if (!s2u.equals(s2)) {
            throw new IllegalArgumentException(ERROR5);
        }
        throw new IllegalArgumentException(ERROR6);
    };

    /**
     * Test method for
     * {@link HeptaPredicateThrowable#test(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testTest() {
        try {
            assertTrue(P1.test("v12", "v8", 1, "turbo", "t", "u", "r"));
        } catch (FunctionException e) {
            fail("Predicate failed");
        }

        try {
            P1.test(null, "v2", 1, "turbo", "t", "u", "r");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link HeptaPredicateThrowable#testThrows(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testTestThrows() {
        try {
            assertTrue(P1.testThrows("v12", "v8", 1, "turbo", "t", "u", "r"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            P1.testThrows(null, "v2", 1, "turbo", "t", "u", "r");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link HeptaPredicateThrowable#and(HeptaPredicateThrowable)}.
     */
    @Test
    public void testAnd() {
        final HeptaPredicateThrowable<String, String, Integer, String, String, String, String, IllegalArgumentException> pp = P1.and(P2);

        try {
            assertTrue(pp.testThrows("V12", "V1", 1, "turbo", "t", "u", "r"));
            assertFalse(pp.testThrows("V12", "V8", 1, "turbo", "t", "u", "r"));
            assertFalse(pp.testThrows("V6", "V12", 1, "turbo", "t", "u", "r"));
            assertFalse(pp.testThrows("V6", "V6", 1, "turbo", "t", "u", "r"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            pp.testThrows(null, "V8", 1, "turbo", "t", "u", "r");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }

        try {
            pp.testThrows("V12", "v8", 1, "turbo", "t", "u", "r");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR5, e.getMessage());
        }
    }

    /**
     * Test method for {@link HeptaPredicateThrowable#negateThrows()}.
     */
    @Test
    public void testNegateThrows() {
        final HeptaPredicateThrowable<String, String, Integer, String, String, String, String, IllegalArgumentException> pp = P1
                .negateThrows();

        try {
            assertFalse(pp.testThrows("V12", "V8", 1, "turbo", "t", "u", "r"));
            assertTrue(pp.testThrows("v6", "V8", 2, "turbo", "t", "u", "r"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            pp.testThrows("V6", null, 1, "turbo", "t", "u", "r");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR2, e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link HeptaPredicateThrowable#or(HeptaPredicateThrowable)}.
     */
    @Test
    public void testOr() {
        final HeptaPredicateThrowable<String, String, Integer, String, String, String, String, IllegalArgumentException> pp = P1.or(P2);

        try {
            assertTrue(pp.testThrows("V12", "V1", 1, "turbo", "t", "u", "r"));
            assertTrue(pp.testThrows("V", "V1", 1, "turbo", "t", "u", "r"));
            assertTrue(pp.testThrows("V12", "V12", 1, "turbo", "t", "u", "r"));
            assertFalse(pp.testThrows("V6", "V12", 1, "turbo", "t", "u", "r"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            pp.testThrows(null, "V8", 1, "turbo", "t", "u", "r");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }

        try {
            // first test pass and return true, so the second one is not
            // executed
            assertTrue(pp.testThrows("V12", "v8", 1, "turbo", "t", "u", "r"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            // first test pass and return false, so the next is executed
            pp.testThrows("v6", "V8", 1, "turbo", "t", "u", "r");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR4, e.getMessage());
        }
    }
}
