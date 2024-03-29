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
 * Check {@link PredicateNoThrow}
 *
 * @since Jan 25, 2018
 * @author Gilles
 *
 */
public class PredicateNoThrowTest {

    private static final String ERROR1 = "The argument is null";
    private static final String ERROR2 = "String is not in upper case";

    private static final PredicateNoThrow<String, IllegalArgumentException> P1 = (s1) -> {
        if (s1 != null) {
            return s1.length() > 2;
        }
        throw new IllegalArgumentException(ERROR1);
    };

    private static final PredicateNoThrow<String, IllegalArgumentException> P2 = (s1) -> {
        String s1u = s1.toUpperCase();
        if (s1u.equals(s1)) {
            return s1u.contains("V");
        }
        throw new IllegalArgumentException(ERROR2);
    };

    /**
     * Test method for {@link PredicateNoThrow#test(java.lang.Object)}.
     */
    @Test
    public void testTest() {
        try {
            assertTrue(P1.test("v12"));
        } catch (FunctionException e) {
            fail("Predicate failed");
        }

        assertFalse(P1.test(null));
    }

    /**
     * Test method for {@link PredicateNoThrow#testThrows(java.lang.Object)}.
     */
    @Test
    public void testTestThrows() {
        try {
            assertTrue(P1.testThrows("v12"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            P1.testThrows(null);
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }
    }

    /**
     * Test method for {@link PredicateNoThrow#and(PredicateNoThrow)}.
     */
    @Test
    public void testAnd() {
        final PredicateNoThrow<String, IllegalArgumentException> pp = P1.and(P2);

        try {
            assertTrue(pp.testThrows("V12"));
            assertFalse(pp.testThrows("V1"));
            assertFalse(pp.testThrows("A69"));
            assertFalse(pp.testThrows("A6"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            pp.testThrows(null);
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }

        try {
            pp.testThrows("v12");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR2, e.getMessage());
        }
    }

    /**
     * Test method for {@link PredicateNoThrow#negateThrows()}.
     */
    @Test
    public void testNegateThrows() {
        final PredicateNoThrow<String, IllegalArgumentException> pp = P1.negateThrows();

        try {
            assertFalse(pp.testThrows("V12"));
            assertTrue(pp.testThrows("v6"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            pp.testThrows(null);
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }
    }

    /**
     * Test method for {@link PredicateNoThrow#or(PredicateNoThrow)}.
     */
    @Test
    public void testOr() {
        final PredicateNoThrow<String, IllegalArgumentException> pp = P1.or(P2);

        try {
            assertTrue(pp.testThrows("V1"));
            assertTrue(pp.testThrows("V12"));
            assertTrue(pp.testThrows("v12"));
            assertFalse(pp.testThrows("A1"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            pp.testThrows(null);
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }

        try {
            // first test pass and return true, so the second one is not
            // executed
            assertTrue(pp.testThrows("v12"));
        } catch (IllegalArgumentException e) {
            fail("Predicate failed");
        }

        try {
            // first test pass and return false, so the next is executed
            pp.testThrows("v6");
            fail("Predicate has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR2, e.getMessage());
        }
    }
}
