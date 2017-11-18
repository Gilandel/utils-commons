/*-
 * #%L
 * utils-commons
 * %%
 * Copyright (C) 2016 - 2017 Gilles Landel
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Check {@link HeptaPredicate}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class HeptaPredicateTest {

    private static final HeptaPredicate<String, String, Integer, String, String, String, String> P1 = (s1, s2, i, s3, s4, s5, s6) -> {
        if (s1 != null && s2 != null && i > 0) {
            return s1.length() > s2.length();
        } else if (s1 != null) {
            return true;
        } else if (s2 != null) {
            return false;
        }
        return false;
    };

    private static final HeptaPredicate<String, String, Integer, String, String, String, String> P2 = (s1, s2, i, s3, s4, s5, s6) -> {
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
     * {@link HeptaPredicate#test(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testTest() {
        assertTrue(P1.test("v12", "v8", 1, "VX", "t", "u", "r"));
        assertFalse(P1.test(null, "v2", 1, "VX", "t", "u", "r"));
    }

    /**
     * Test method for {@link HeptaPredicate#and(HeptaPredicate)}.
     */
    @Test
    public void testAnd() {
        final HeptaPredicate<String, String, Integer, String, String, String, String> pp = P1.and(P2);

        assertTrue(pp.test("V12", "V1", 1, "VX", "t", "u", "r"));
        assertFalse(pp.test("V12", "V8", 1, "VX", "t", "u", "r"));
        assertFalse(pp.test("V6", "V12", 1, "VX", "t", "u", "r"));
        assertFalse(pp.test("V6", "V6", 1, "VX", "t", "u", "r"));

        assertFalse(pp.test(null, "V8", 1, "VX", "t", "u", "r"));
        assertFalse(pp.test("V12", "v8", 1, "VX", "t", "u", "r"));
    }

    /**
     * Test method for {@link HeptaPredicate#negateThrows()}.
     */
    @Test
    public void testNegateThrows() {
        final HeptaPredicate<String, String, Integer, String, String, String, String> pp = P1.negate();

        assertFalse(pp.test("V12", "V8", 1, "VX", "t", "u", "r"));
        assertTrue(pp.test("v6", "V8", 2, "VX", "t", "u", "r"));

        assertFalse(pp.test("V6", null, 1, "VX", "t", "u", "r"));
    }

    /**
     * Test method for {@link HeptaPredicate#or(HeptaPredicate)}.
     */
    @Test
    public void testOr() {
        final HeptaPredicate<String, String, Integer, String, String, String, String> pp = P1.or(P2);

        assertTrue(pp.test("V12", "V1", 1, "VX", "t", "u", "r"));
        assertTrue(pp.test("V", "V1", 1, "VX", "t", "u", "r"));
        assertTrue(pp.test("V12", "V12", 1, "VX", "t", "u", "r"));
        assertFalse(pp.test("V6", "V12", 1, "VX", "t", "u", "r"));

        assertFalse(pp.test(null, "V8", 1, "VX", "t", "u", "r"));

        assertTrue(pp.test("V12", "v8", 1, "VX", "t", "u", "r"));
        assertTrue(pp.test("v6", "V8", 1, "VX", "t", "u", "r"));
    }
}
