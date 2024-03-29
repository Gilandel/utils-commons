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

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.landel.utils.commons.AbstractTest;
import fr.landel.utils.commons.exception.FunctionException;

/**
 * Check {@link FunctionThrowable}
 *
 * @since May 30, 2016
 * @author Gilles
 *
 */
public class ConsumerThrowableTest extends AbstractTest {

    private static final String ERROR = "The parameter is null";
    private static final ConsumerThrowable<String, IllegalArgumentException> C = (s1) -> {
        if (s1 == null) {
            throw new IllegalArgumentException(ERROR);
        }
    };

    /**
     * Test method for {@link FunctionThrowable#accept(java.lang.Object)}.
     */
    @Test
    public void testAccept() {
        try {
            C.accept("v1");
        } catch (FunctionException e) {
            fail("Consumer failed");
        }

        try {
            C.accept(null);
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR, e.getMessage());
        }
    }

    /**
     * Test method for {@link FunctionThrowable#acceptThrows(java.lang.Object)}.
     */
    @Test
    public void testAcceptThrows() {
        try {
            C.acceptThrows("v1");
        } catch (IllegalArgumentException e) {
            fail("Consumer failed");
        }

        try {
            C.acceptThrows(null);
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(ERROR, e.getMessage());
        }
    }

    /**
     * Test method for {@link ConsumerThrowable#andThen(ConsumerThrowable)}.
     */
    @Test
    public void testAndThen() {
        try {
            C.andThen(null);
            fail("Consumer has to fail");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }

        final String error1 = "The parameter is null";
        final String error2 = "The parameter is empty";

        final ConsumerThrowable<String, IllegalArgumentException> c2 = C.andThen((s1) -> {
            if (s1.length() == 0) {
                throw new IllegalArgumentException(error2);
            }
        });

        try {
            c2.acceptThrows("v1");
        } catch (IllegalArgumentException e) {
            fail("Consumer failed");
        }

        try {
            c2.acceptThrows(null);
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(error1, e.getMessage());
        }

        try {
            c2.acceptThrows("");
            fail("Consumer has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(error2, e.getMessage());
        }
    }

    /**
     * Check that the thrown exception is correctly raised and thrown outer the
     * functional method
     */
    @Test
    public void testInnerException() {
        Arrays.asList("titi", "tata").stream().forEach((ConsumerThrowable<String, AssertionError>) s -> {
            if ("toto".equals(s)) {
                fail();
            }
        });

        assertException(() -> {
            Arrays.asList("titi", "toto").stream().forEach((ConsumerThrowable<String, IOException>) s -> {
                if ("toto".equals(s)) {
                    throw new IOException(s);
                }
            });
        }, IOException.class, "toto");
    }
}
