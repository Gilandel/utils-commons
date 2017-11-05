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
package fr.landel.utils.commons.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.UUID;
import java.util.function.Function;

import org.junit.Test;

/**
 * Check {@link HashCodeBuilder2}
 *
 * @since Feb 11, 2017
 * @author Gilles
 *
 */
public class HashCodeBuilder2Test {

    /**
     * Test method for
     * {@link HashCodeBuilder2#append(java.lang.Object, java.util.function.Function)}.
     */
    @Test
    public void testAppendTFunctionOfTX() {
        final String uid = UUID.randomUUID().toString();
        final IllegalArgumentException e1 = new IllegalArgumentException(uid);
        final IllegalStateException e2 = new IllegalStateException(uid);

        assertEquals(new HashCodeBuilder2<Exception>(e1).append(e -> e.getMessage()).toHashCode(),
                new HashCodeBuilder2<Exception>(e2).append(e -> e.getMessage()).toHashCode());

        assertEquals(new HashCodeBuilder2<Exception>(null).toHashCode(),
                new HashCodeBuilder2<Exception>(null).append(e -> e.getMessage()).toHashCode());

        try {
            new HashCodeBuilder2<Exception>(e1).append((Function<Exception, String>) null).toHashCode();
            fail();
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }
}
