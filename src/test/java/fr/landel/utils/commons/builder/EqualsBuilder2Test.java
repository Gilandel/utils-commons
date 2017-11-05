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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.function.BiPredicate;

import org.junit.Test;

/**
 * Check {@link EqualsBuilder2}
 *
 * @since Feb 11, 2017
 * @author Gilles
 *
 */
public class EqualsBuilder2Test {

    /**
     * Test method for {@link EqualsBuilder2#build()}.
     */
    @Test
    public void testBuild() {
        final BiPredicate<String, String> equalsIgnoreCase = (a, b) -> a.equalsIgnoreCase(b);

        IllegalArgumentException e1 = new IllegalArgumentException("error");
        IllegalStateException e2 = new IllegalStateException("error");
        IllegalArgumentException e3 = new IllegalArgumentException("ERROR");

        assertTrue(new EqualsBuilder2<>(e1, e1).build());

        assertTrue(new EqualsBuilder2<>(e1, e1).isEqual());
        assertFalse(new EqualsBuilder2<>(e1, e2).isEqual()); // class diff
        assertTrue(new EqualsBuilder2<>(e1, e2, Exception.class).isEqual());
        assertTrue(new EqualsBuilder2<>(e1, e3).isEqual());
        assertFalse(new EqualsBuilder2<>(e1, null, Exception.class).isEqual());

        assertTrue(new EqualsBuilder2<>(null, null).isEqual());
        assertFalse(new EqualsBuilder2<>(null, e2).isEqual());
        assertFalse(new EqualsBuilder2<>(e1, null).isEqual());
        assertFalse(new EqualsBuilder2<>((Exception) null, e2).append(e -> e.getMessage()).isEqual());
        assertFalse(new EqualsBuilder2<>(null, e2, Exception.class).append(e -> e.getMessage()).isEqual());
        assertFalse(new EqualsBuilder2<>(e1, null).append(e -> e.getMessage()).isEqual());

        assertFalse(new EqualsBuilder2<>(e1, e2).append(e -> e.getMessage()).isEqual());
        assertTrue(new EqualsBuilder2<>(e1, e2, Exception.class).append(e -> e.getMessage()).isEqual());
        assertFalse(new EqualsBuilder2<>(e1, e3).append(e -> e.getMessage()).isEqual());
        assertTrue(new EqualsBuilder2<>(e1, e3).append(e -> e.getMessage(), equalsIgnoreCase).isEqual());

        assertFalse(new EqualsBuilder2<>(e1, e2).append("toto", "titi").isEqual());
        assertTrue(new EqualsBuilder2<>(e1, e3).append("toto", "titi", e -> e.length()).isEqual());
        assertTrue(new EqualsBuilder2<>(e1, e3).append("toto", "titi", e -> e.length()).append("toto", "toto").isEqual());
        assertFalse(new EqualsBuilder2<>(e1, e3).append("toto", "titi").isEqual());
        assertFalse(new EqualsBuilder2<>(e1, e3).append("toto", "tit", e -> e.length()).append("toto", "toto").isEqual());

        assertFalse(
                new EqualsBuilder2<>(e1, e2).append("toto", "titi").and(e1, e3).append(e -> e.getMessage(), equalsIgnoreCase).isEqual());
        assertFalse(new EqualsBuilder2<>(e1, e2, Exception.class).append("toto", "toto").and(e1, e3).append(e -> e.getMessage()).isEqual());

        assertTrue(new EqualsBuilder2<>(e1, e2, Exception.class).append("toto", "toto").and(e1, e3)
                .append(e -> e.getMessage(), equalsIgnoreCase).isEqual());
        assertTrue(new EqualsBuilder2<>(e1, e2, Exception.class).append(e -> e.getMessage()).and(e1, e3)
                .append(e -> e.getMessage(), equalsIgnoreCase).isEqual());

        assertTrue(new EqualsBuilder2<>(e1, e3, Exception.class).append(e -> e.getMessage(), equalsIgnoreCase).and(e1, e2, Exception.class)
                .append("toto", "toto").isEqual());

        try {
            new EqualsBuilder2<>(e1, e3).append(null).isEqual();
            fail();
        } catch (NullPointerException e) {
            assertNotNull(e);
        }

        assertTrue(new EqualsBuilder2<>(e1, e2, Exception.class).append(e1.getMessage(), e2.getMessage()).isEqual());
    }
}
