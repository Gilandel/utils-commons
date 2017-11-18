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
import fr.landel.utils.commons.tuple.Nona;

/**
 * Check {@link NonaSupplierThrowable}
 *
 * @since Nov 17, 2017
 * @author Gilles
 *
 */
public class NonaSupplierThrowableTest {

    private static final Nona<String, String, String, String, String, String, String, String, String> NONA = Nona.of("a", "b", "c", "d",
            "e", "f", "g", "h", "i");

    private static final String EXPECTED_TO_STRING = "(a,b,c,d,e,f,g,h,i)";

    /**
     * Test method for {@link NonaSupplierThrowable#get()}.
     */
    @Test
    public void testGet() {
        boolean test = false;
        final String error = "error";

        final NonaSupplierThrowable<String, String, String, String, String, String, String, String, String, IllegalArgumentException> s1 = () -> NONA;
        final NonaSupplierThrowable<String, String, String, String, String, String, String, String, String, IOException> s2 = () -> {
            if (test) {
                return NONA;
            }
            throw new IOException(error);
        };

        try {
            assertEquals(EXPECTED_TO_STRING, s1.get().toString());
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
     * Test method for {@link NonaSupplierThrowable#getThrows()}.
     */
    @Test
    public void testGetThrows() {
        boolean test = false;
        final String error = "error";

        final NonaSupplierThrowable<String, String, String, String, String, String, String, String, String, IllegalArgumentException> s1 = () -> NONA;
        final NonaSupplierThrowable<String, String, String, String, String, String, String, String, String, IOException> s2 = () -> {
            if (test) {
                return NONA;
            }
            throw new IOException(error);
        };

        try {
            assertEquals(EXPECTED_TO_STRING, s1.getThrows().toString());
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
