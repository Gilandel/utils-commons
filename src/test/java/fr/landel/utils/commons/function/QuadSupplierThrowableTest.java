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

import java.io.IOException;

import org.junit.Test;

import fr.landel.utils.commons.exception.FunctionException;
import fr.landel.utils.commons.tuple.Quad;

/**
 * Check {@link QuadSupplierThrowable}
 *
 * @since May 30, 2016
 * @author Gilles
 *
 */
public class QuadSupplierThrowableTest {

    /**
     * Test method for {@link QuadSupplierThrowable#get()}.
     */
    @Test
    public void testGet() {
        boolean test = false;
        final String error = "error";

        final QuadSupplierThrowable<String, String, String, String, IllegalArgumentException> s1 = () -> Quad.of("a", "b", "c", "d");
        final QuadSupplierThrowable<String, String, String, String, IOException> s2 = () -> {
            if (test) {
                return Quad.of("a", "b", "c", "d");
            }
            throw new IOException(error);
        };

        try {
            assertEquals("(a,b,c,d)", s1.get().toString());
        } catch (FunctionException e) {
            fail("Supplier failed");
        }

        try {
            s2.get();
            fail("Supplier has to fail");
            throw new IOException(); // just for the compiler
        } catch (IOException e) {
            assertNotNull(e);
            assertEquals(error, e.getMessage());
        }
    }

    /**
     * Test method for {@link QuadSupplierThrowable#getThrows()}.
     */
    @Test
    public void testGetThrows() {
        boolean test = false;
        final String error = "error";

        final QuadSupplierThrowable<String, String, String, String, IllegalArgumentException> s1 = () -> Quad.of("a", "b", "c", "d");
        final QuadSupplierThrowable<String, String, String, String, IOException> s2 = () -> {
            if (test) {
                return Quad.of("a", "b", "c", "d");
            }
            throw new IOException(error);
        };

        try {
            assertEquals("(a,b,c,d)", s1.getThrows().toString());
        } catch (IllegalArgumentException e) {
            fail("Supplier failed");
        }

        try {
            s2.getThrows();
            fail("Supplier has to fail");
        } catch (IOException e) {
            assertNotNull(e);
            assertEquals(error, e.getMessage());
        }
    }
}
