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
import fr.landel.utils.commons.tuple.Hepta;

/**
 * Check {@link HeptaSupplier}
 *
 * @since Nov 18, 2017
 * @author Gilles
 *
 */
public class HeptaSupplierTest {

    private static final Hepta<String, String, String, String, String, String, String> HEPTA = Hepta.of("a", "b", "c", "d", "e", "f", "g");

    private static final String EXPECTED_TO_STRING = "(a,b,c,d,e,f,g)";

    /**
     * Test method for {@link HeptaSupplier#get()}.
     */
    @Test
    public void testGet() {
        boolean test = false;
        final String error = "error";

        final HeptaSupplier<String, String, String, String, String, String, String> s1 = () -> HEPTA;
        final HeptaSupplier<String, String, String, String, String, String, String> s2 = () -> {
            if (test) {
                return HEPTA;
            }
            throw new IllegalArgumentException(error);
        };

        try {
            assertEquals(EXPECTED_TO_STRING, s1.get().toString());
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
