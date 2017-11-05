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
 * Check {@link BooleanSupplierThrowable}
 *
 * @since May 30, 2016
 * @author Gilles
 *
 */
public class BooleanSupplierThrowableTest {

    /**
     * Test method for {@link BooleanSupplierThrowable#get()}.
     */
    @Test
    public void testGet() {
        boolean test = false;
        final String error = "error";

        final BooleanSupplierThrowable<IllegalArgumentException> s1 = () -> true;
        final BooleanSupplierThrowable<IllegalArgumentException> s2 = () -> {
            if (test) {
                return true;
            }
            throw new IllegalArgumentException(error);
        };

        try {
            assertEquals(true, s1.getAsBoolean());
        } catch (FunctionException e) {
            fail("Supplier failed");
        }

        try {
            s2.getAsBoolean();
            fail("Supplier has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(error, e.getMessage());
        }
    }

    /**
     * Test method for {@link BooleanSupplierThrowable#getThrows()}.
     */
    @Test
    public void testGetThrows() {
        boolean test = false;
        final String error = "error";

        final BooleanSupplierThrowable<IllegalArgumentException> s1 = () -> true;
        final BooleanSupplierThrowable<IllegalArgumentException> s2 = () -> {
            if (test) {
                return true;
            }
            throw new IllegalArgumentException(error);
        };

        try {
            assertEquals(true, s1.getAsBooleanThrows());
        } catch (IllegalArgumentException e) {
            fail("Supplier failed");
        }

        try {
            s2.getAsBooleanThrows();
            fail("Supplier has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(error, e.getMessage());
        }
    }
}
