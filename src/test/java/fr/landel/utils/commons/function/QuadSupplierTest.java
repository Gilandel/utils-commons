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
import fr.landel.utils.commons.tuple.Quad;

/**
 * Check {@link QuadSupplier}
 *
 * @since May 30, 2016
 * @author Gilles
 *
 */
public class QuadSupplierTest {

    /**
     * Test method for {@link QuadSupplier#get()}.
     */
    @Test
    public void testGet() {
        boolean test = false;
        final String error = "error";

        final QuadSupplier<String, String, String, String> s1 = () -> Quad.of("a", "b", "c", "d");
        final QuadSupplier<String, String, String, String> s2 = () -> {
            if (test) {
                return Quad.of("a", "b", "c", "d");
            }
            throw new IllegalArgumentException(error);
        };

        try {
            assertEquals("(a,b,c,d)", s1.get().toString());
        } catch (FunctionException e) {
            fail("Supplier failed");
        }

        try {
            s2.get();
            fail("Supplier has to fail");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(error, e.getMessage());
        }
    }
}
