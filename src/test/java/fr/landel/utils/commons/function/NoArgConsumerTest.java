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

import fr.landel.utils.commons.tuple.MutableSingle;
import fr.landel.utils.commons.tuple.Single;

/**
 * Check {@link NoArgConsumer}
 *
 * @since May 30, 2016
 * @author Gilles
 *
 */
public class NoArgConsumerTest {

    /**
     * Test method for {@link NoArgConsumer#run()}.
     */
    @Test
    public void testAccept() {
        final MutableSingle<String> s = Single.ofMutable("1");
        final NoArgConsumer nac = () -> s.set("2");

        assertEquals("1", s.get());
        nac.run();
        assertEquals("2", s.get());
    }

    /**
     * Test method for {@link NoArgConsumer#andThen(NoArgConsumer)}.
     */
    @Test
    public void testAndThen() {
        final MutableSingle<Integer> s = Single.ofMutable(1);

        final NoArgConsumer nac1 = () -> s.set(2);
        final NoArgConsumer nac2 = () -> s.set(s.get() + 1);

        assertEquals(1, s.get().intValue());
        nac1.run();
        assertEquals(2, s.get().intValue());

        nac1.andThen(nac2).run();
        assertEquals(3, s.get().intValue());

        try {
            nac1.andThen(null);

            fail("Consumer has to fail");
        } catch (NullPointerException e) {
            assertNotNull(e);
            assertEquals("after", e.getMessage());
        }
    }
}
