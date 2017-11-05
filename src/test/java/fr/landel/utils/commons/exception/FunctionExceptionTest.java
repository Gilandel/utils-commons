/*
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
package fr.landel.utils.commons.exception;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

/**
 * Check function exception
 *
 * @since Apr 23, 2016
 * @author Gilles
 *
 */
public class FunctionExceptionTest {

    /**
     * Test method for
     * {@link FunctionException#FunctionException(java.lang.Throwable)} .
     */
    @Test
    public void testFunctionExceptionThrowable() {
        FunctionException exception = new FunctionException(new IOException("test")) {

            /**
             * serialVersionUID
             */
            private static final long serialVersionUID = 1L;
        };

        assertTrue(Exception.class.isAssignableFrom(FunctionException.class));

        try {
            throw exception;
        } catch (FunctionException e) {
            assertNotNull(e.getCause());
            assertTrue(IOException.class.isAssignableFrom(e.getCause().getClass()));
        }
    }
}
