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
package fr.landel.utils.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Function;
import java.util.function.IntPredicate;

import org.junit.jupiter.api.Test;

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
