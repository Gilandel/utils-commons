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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Check {@link HexaFunction}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class HexaFunctionTest {

    private static final HexaFunction<String, String, String, String, String, String, Integer> FN1 = (s1, s2, s3, s4, s5, s6) -> {
        if (s1 != null && s2 != null) {
            return s1.length() + s2.length();
        } else if (s1 != null) {
            return -1;
        } else if (s2 != null) {
            return -2;
        }
        return -3;
    };

    private static final HexaFunction<String, String, String, String, String, String, Integer> FN2 = FN1.andThen((u) -> {
        if (u == 0) {
            return u + 10;
        }
        return -1;
    });

    /**
     * Test method for
     * {@link HexaFunction#apply(Object, Object, Object, Object, Object, Object)}.
     */
    @Test
    public void testApply() {
        assertEquals(4, FN1.apply("v1", "v2", "v3", "v4", "t", "u").intValue());
        assertEquals(-2, FN1.apply(null, "v2", "v3", "v4", "t", "u").intValue());
    }

    /**
     * Test method for {@link HexaFunction#andThen(FunctionThrowable)}.
     */
    @Test
    public void testAndThen() {
        try {
            FN1.andThen(null);
            fail("Function has to fail");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }

        assertEquals(-1, FN2.apply("", null, "v3", "v4", "t", "u").intValue());
        assertEquals(-1, FN2.apply("v", "", "v3", "v4", "t", "u").intValue());
        assertEquals(10, FN2.apply("", "", "v3", "v4", "t", "u").intValue());
    }
}
