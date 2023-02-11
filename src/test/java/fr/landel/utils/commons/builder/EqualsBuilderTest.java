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
package fr.landel.utils.commons.builder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.BiPredicate;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

/**
 * Check {@link EqualsBuilder}
 *
 * @since Feb 10, 2017
 * @author Gilles
 *
 */
public class EqualsBuilderTest {

    /**
     * Test method for
     * {@link EqualsBuilder#append(java.lang.Object, java.lang.Object, java.util.function.Function)}.
     */
    @Test
    public void testAppend() {
        final IllegalArgumentException e1 = new IllegalArgumentException("error");
        final IllegalStateException e2 = new IllegalStateException("error");
        final IllegalArgumentException e3 = new IllegalArgumentException("ERROR");
        final IllegalArgumentException e4 = new IllegalArgumentException((String) null);

        final Function<Exception, String> getter = e -> e.getMessage();
        final BiPredicate<String, String> predicate = (a, b) -> a.equalsIgnoreCase(b);

        assertTrue(new EqualsBuilder().append(e1, e2, getter).isEquals());
        assertFalse(new EqualsBuilder().append(e1, e3, getter).isEquals());

        assertFalse(new EqualsBuilder().append(e1, e3, getter).append(e1, e2, getter).isEquals());
        assertFalse(new EqualsBuilder().append(e1, e2, getter).append(e1, e3, getter).isEquals());

        assertTrue(new EqualsBuilder().append((Exception) null, null, getter).isEquals());
        assertFalse(new EqualsBuilder().append(e1, null, getter).isEquals());
        assertFalse(new EqualsBuilder().append(null, e2, getter).isEquals());

        assertFalse(new EqualsBuilder().append(e1, e4, getter, predicate).isEquals());
        assertFalse(new EqualsBuilder().append(e4, e1, getter, predicate).isEquals());
        assertTrue(new EqualsBuilder().append(e4, e4, getter, predicate).isEquals());

        try {
            new EqualsBuilder().append(e1, e2, null).isEquals();
            fail();
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }
}
