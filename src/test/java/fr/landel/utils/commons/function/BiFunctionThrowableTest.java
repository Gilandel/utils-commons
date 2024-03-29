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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Check {@link BiFunctionThrowable}
 *
 * @since May 30, 2016
 * @author Gilles
 *
 */
public class BiFunctionThrowableTest {

    private static final String ERROR1 = "The first argument is null";
    private static final String ERROR2 = "The second argument is null";
    private static final String ERROR3 = "Both arguments are null";
    private static final String ERROR4 = "Value has to be equal to zero";

    private static final BiFunctionThrowable<String, String, Integer, IllegalArgumentException> FN1 = (s1, s2) -> {
        if (s1 != null && s2 != null) {
            return s1.length() + s2.length();
        } else if (s1 != null) {
            throw new IllegalArgumentException(ERROR2);
        } else if (s2 != null) {
            throw new IllegalArgumentException(ERROR1);
        }
        throw new IllegalArgumentException(ERROR3);
    };

    private static final BiFunctionThrowable<String, String, Integer, IllegalArgumentException> FN2 = FN1.andThen((u) -> {
        if (u == 0) {
            return u + 10;
        }
        throw new IllegalArgumentException(ERROR4);
    });

    /**
     * Test method for
     * {@link BiFunctionThrowable#apply(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testApply() {
        try {
            assertEquals(4, FN1.apply("v1", "v2").intValue());
        } catch (FunctionException e) {
            fail("Function failed");
        }

        try {
            FN1.apply(null, "v2");
            fail("Function has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link BiFunctionThrowable#applyThrows(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testApplyThrows() {
        try {
            assertEquals(4, FN1.applyThrows("v1", "v2").intValue());
        } catch (IllegalArgumentException e) {
            fail("Function failed");
        }

        try {
            FN1.applyThrows(null, "v2");
            fail("Function has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR1, e.getMessage());
        }
    }

    /**
     * Test method for {@link BiFunctionThrowable#andThen(FunctionThrowable)}.
     */
    @Test
    public void testAndThen() {
        try {
            FN1.andThen(null);
            fail("Function has to fail");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }

        try {
            FN2.applyThrows("", null);
            fail("Function has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR2, e.getMessage());
        }

        try {
            FN2.applyThrows("v", "");
            fail("Function has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR4, e.getMessage());
        }

        try {
            assertEquals(10, FN2.applyThrows("", "").intValue());
        } catch (IllegalArgumentException e) {
            fail("Function failed");
        }
    }
}
