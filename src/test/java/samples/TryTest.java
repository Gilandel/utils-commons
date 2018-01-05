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
package samples;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import fr.landel.utils.commons.tuple.MutableSingle;
import fr.landel.utils.commons.tuple.Single;

/**
 * Test for {@link Try} sample
 *
 * @since Aug 11, 2016
 * @author Gilles
 *
 */
public class TryTest {

    @Test
    public void testThat() {
        final MutableSingle<Boolean> ok = Single.ofMutable(false);

        Try.that(() -> {
            if ("".isEmpty()) {
                throw new IOException("my message");
            }
        }).ifPresent(catched -> {
            assertNotNull(catched);
            assertNotNull(catched.get());
            assertTrue(catched.has("my message"));
            assertTrue(catched.is(IOException.class));

            ok.set(true);
        });

        assertTrue(ok.get());

        ok.set(false);

        Try.that(() -> {
            ok.set(true); // no exception
        }).ifPresent(catched -> {
            ok.set(false);
        });

        assertTrue(ok.get());
    }
}
