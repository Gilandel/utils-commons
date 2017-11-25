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
package fr.landel.utils.commons.expect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.ComparisonFailure;
import org.junit.Test;

import fr.landel.utils.commons.AbstractTest;
import fr.landel.utils.commons.StringUtils;
import fr.landel.utils.commons.function.ThrowableSupplier;

/**
 * Check the expect class
 *
 * @since Jul 16, 2016
 * @author Gilles
 *
 */
public class ExpectTest extends AbstractTest {

    /**
     * Test constructor for {@link Expect} .
     */
    @Test
    public void testConstructors() {
        assertTrue(checkPrivateConstructor(Expect.class));
    }

    /**
     * Test method for {@link Expect#exception(ThrowableSupplier, Class)}.
     */
    @Test
    public void testExceptionThrowableSupplierOfThrowableClassOfT() {
        Expect.exception(() -> {
            throw new IllegalArgumentException();
        }, IllegalArgumentException.class);

        try {
            Expect.exception(() -> {
                throw new IllegalArgumentException();
            }, IOException.class);
            fail();
        } catch (ExpectException e) {
            assertEquals("The expected exception never came up (thrown: IllegalArgumentException).", e.getMessage());
        }

        try {
            Expect.exception(() -> {
            }, IOException.class);
            fail();
        } catch (ExpectException e) {
            assertEquals("No exception thrown", e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link Expect#exception(ThrowableSupplier, Class, String)},
     * {@link Expect#exception(ThrowableSupplier, Class, Pattern)} and
     * {@link Expect#exception(ThrowableSupplier, Class, Predicate)}.
     */
    @Test
    public void testExceptionThrowableSupplierOfThrowableClassOfTString() {
        Expect.exception(() -> {
            throw new IllegalArgumentException("message");
        }, IllegalArgumentException.class, "message");

        Expect.exception(() -> {
            throw new IllegalArgumentException("message");
        }, IllegalArgumentException.class, Pattern.compile("^mes.*"));

        Expect.exception(() -> {
            throw new IllegalArgumentException("message");
        }, IllegalArgumentException.class, StringUtils::isNotEmpty);

        try {
            Expect.exception(() -> {
                throw new IllegalArgumentException("message");
            }, IllegalArgumentException.class, "message2");
            fail();
        } catch (ExpectException e) {
            assertEquals(
                    "The exception message isn't as expected.\nExpected (first part) and result (second part):\nmessage2\n-----\nmessage",
                    e.getMessage());
        }

        try {
            Expect.exception(() -> {
                throw new IllegalArgumentException("message");
            }, IllegalArgumentException.class, Pattern.compile(".*?2$"));
            fail();
        } catch (ExpectException e) {
            assertEquals("The exception message isn't as expected.\nExpected (first part) and result (second part):\n.*?2$\n-----\nmessage",
                    e.getMessage());
        }

        try {
            Expect.exception(() -> {
                throw new IllegalArgumentException("message");
            }, IllegalArgumentException.class, StringUtils::isBlank);
            fail();
        } catch (ExpectException e) {
            assertEquals(
                    "The exception message isn't as expected.\nExpected (first part) and result (second part):\npredicate\n-----\nmessage",
                    e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link Expect#exception(ThrowableSupplier, Class, Throwable)}.
     * 
     * @throws IOException
     *             On error
     */
    @Test
    public void testExceptionThrowableSupplierOfThrowableClassOfTE() throws IOException {
        Expect.exception(() -> {
            throw new IllegalArgumentException();
        }, IllegalArgumentException.class, (ok, e, a) -> new IOException());

        try {
            Expect.exception(() -> {
                throw new IllegalArgumentException();
            }, IOException.class, (ok, e, a) -> new IOException(ok + ":" + e + ":" + a));
            fail();
        } catch (IOException e) {
            assertEquals("false:null:null", e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link Expect#exception(ThrowableSupplier, Class, String, Throwable)}.
     * 
     * @throws IOException
     */
    @Test
    public void testExceptionThrowableSupplierOfThrowableClassOfTStringE() throws IOException {
        Expect.exception(() -> {
            throw new IllegalArgumentException("message");
        }, IllegalArgumentException.class, "message", (ok, e, a) -> new IOException());

        try {
            Expect.exception(() -> {
                throw new IllegalArgumentException("message");
            }, IllegalArgumentException.class, "message2", (ok, e, a) -> new IOException());
            fail();
        } catch (IOException e) {
            assertNotNull(e);
        }
    }

    /**
     * Test method for
     * {@link Expect#exception(ThrowableSupplier, Class, String, Throwable)}.
     * 
     * @throws IOException
     */
    @Test(expected = AssertionError.class)
    public void testExceptionJUnit() throws IOException {
        // To raise a failure in JUnit tests in place of error
        assertException(() -> {
            throw new IllegalArgumentException("message");
        }, IllegalArgumentException.class, "message2");
    }

    /**
     * Test method for
     * {@link Expect#exception(ThrowableSupplier, Class, String, Throwable)}.
     * 
     * @throws IOException
     */
    @Test
    public void testExceptionJUnit2() throws IOException {

        try {
            assertException(() -> {
                throw new IllegalArgumentException();
            }, IOException.class);
            fail();
        } catch (AssertionError e) {
            assertNotNull(e);
        }
        // To raise a failure in JUnit tests in place of error
        Expect.exception(() -> {
            assertException(() -> {
                throw new IllegalArgumentException("message");
            }, IllegalArgumentException.class, "message2");
        }, ComparisonFailure.class, "The exception message don't match. expected:<message[2]> but was:<message[]>");
    }
}
