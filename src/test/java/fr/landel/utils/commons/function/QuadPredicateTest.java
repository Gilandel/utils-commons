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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Check {@link QuadPredicate}
 *
 * @since May 30, 2016
 * @author Gilles
 *
 */
public class QuadPredicateTest {

    private static final QuadPredicate<String, String, Integer, String> P1 = (s1, s2, i, s3) -> {
        if (s1 != null && s2 != null && i > 0) {
            return s1.length() > s2.length();
        } else if (s1 != null) {
            return true;
        } else if (s2 != null) {
            return false;
        }
        return false;
    };

    private static final QuadPredicate<String, String, Integer, String> P2 = (s1, s2, i, s3) -> {
        if (s1 != null && s2 != null) {
            String s1u = s1.toUpperCase();
            String s2u = s2.toUpperCase();
            if (s1u.equals(s1) && s2u.equals(s2)) {
                return s1u.contains(s2u) || s2u.contains(s1u);
            } else if (!s1u.equals(s1)) {
                return true;
            } else if (!s2u.equals(s2)) {
                return false;
            }
        }
        return false;
    };

    /**
     * Test method for
     * {@link QuadPredicate#test(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testTest() {
        assertTrue(P1.test("v12", "v8", 1, "VX"));
        assertFalse(P1.test(null, "v2", 1, "VX"));
    }

    /**
     * Test method for {@link QuadPredicate#and(QuadPredicate)}.
     */
    @Test
    public void testAnd() {
        final QuadPredicate<String, String, Integer, String> pp = P1.and(P2);

        assertTrue(pp.test("V12", "V1", 1, "VX"));
        assertFalse(pp.test("V12", "V8", 1, "VX"));
        assertFalse(pp.test("V6", "V12", 1, "VX"));
        assertFalse(pp.test("V6", "V6", 1, "VX"));

        assertFalse(pp.test(null, "V8", 1, "VX"));
        assertFalse(pp.test("V12", "v8", 1, "VX"));
    }

    /**
     * Test method for {@link QuadPredicate#negateThrows()}.
     */
    @Test
    public void testNegateThrows() {
        final QuadPredicate<String, String, Integer, String> pp = P1.negate();

        assertFalse(pp.test("V12", "V8", 1, "VX"));
        assertTrue(pp.test("v6", "V8", 2, "VX"));

        assertFalse(pp.test("V6", null, 1, "VX"));
    }

    /**
     * Test method for {@link QuadPredicate#or(QuadPredicate)}.
     */
    @Test
    public void testOr() {
        final QuadPredicate<String, String, Integer, String> pp = P1.or(P2);

        assertTrue(pp.test("V12", "V1", 1, "VX"));
        assertTrue(pp.test("V", "V1", 1, "VX"));
        assertTrue(pp.test("V12", "V12", 1, "VX"));
        assertFalse(pp.test("V6", "V12", 1, "VX"));

        assertFalse(pp.test(null, "V8", 1, "VX"));

        assertTrue(pp.test("V12", "v8", 1, "VX"));
        assertTrue(pp.test("v6", "V8", 1, "VX"));
    }
}
