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

import fr.landel.utils.commons.exception.FunctionException;

/**
 * Check {@link PentaConsumerThrowable}
 *
 * @since Nov 16, 2017
 * @author Gilles
 *
 */
public class PentaConsumerThrowableTest {

    private static final String ERROR = "At least one parameter is null";
    private static final PentaConsumerThrowable<String, String, String, Integer, String, IllegalArgumentException> C = (s1, s2, s3, i,
            s4) -> {
        if (s1 == null || s2 == null || s3 == null && i > 0) {
            throw new IllegalArgumentException(ERROR);
        }
    };

    /**
     * Test method for
     * {@link PentaConsumerThrowable#accept(Object, Object, Object, Object, Object)}.
     */
    @Test
    public void testAccept() {
        try {
            C.accept("v1", "v2", "v3", 1, "t");
        } catch (FunctionException e) {
            fail("Consumer failed");
        }

        try {
            C.accept("v1", null, "v3", 1, "t");
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR, e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link PentaConsumerThrowable#acceptThrows(Object, Object, Object, Object, Object)}.
     */
    @Test
    public void testAcceptThrows() {
        try {
            C.acceptThrows("v1", "v2", "v3", 1, "t");
        } catch (IllegalArgumentException e) {
            fail("Consumer failed");
        }

        try {
            C.acceptThrows("v1", null, "v3", 1, "t");
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR, e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link PentaConsumerThrowable#andThen(PentaConsumerThrowable)}.
     */
    @Test
    public void testAndThen() {
        try {
            C.andThen(null);
            fail("Consumer has to fail");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }

        final String error1 = "First parameter is null";
        final String error2 = "Second parameter is null";
        final String error3 = "Evil error";

        final PentaConsumerThrowable<String, String, String, Integer, String, IllegalArgumentException> c1 = (s1, s2, s3, i, s4) -> {
            if (s1 == null) {
                throw new IllegalArgumentException(error1);
            }
        };

        final PentaConsumerThrowable<String, String, String, Integer, String, IllegalArgumentException> c2 = c1
                .andThen((s1, s2, s3, i, s4) -> {
                    if (s2 == null) {
                        throw new IllegalArgumentException(error2);
                    }
                }).andThen((s1, s2, s3, i, s4) -> {
                    throw new IllegalArgumentException(error3);
                });

        try {
            c2.acceptThrows(null, "v2", "v3", 1, "t");
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(error1, e.getMessage());
        }

        try {
            c2.acceptThrows("v1", null, "v3", 1, "t");
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(error2, e.getMessage());
        }

        try {
            c2.acceptThrows("v1", "v2", "v3", 1, "t");
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(error3, e.getMessage());
        }
    }
}
