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
package samples.builder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Test;

public class EqualsTest {
    // private static final Logger LOGGER =
    // LoggerFactory.getLogger(TestEquals.class);

    @Test
    public void interfaceTest() {
        Entity1 e1 = new Entity1();
        Entity1 e2 = new Entity1();

        e1.setName("test");
        e1.setValue(1);
        e1.setDescription("desc");

        e2.setName("text");
        e2.setValue(1);
        e2.setDescription("desc");

        assertTrue(Objects.equals(e1, e2));

        e2.setName("test");
        e2.setValue(1);

        assertTrue(Objects.equals(e1, e2));

        e2.setName("test");
        e2.setValue(2);

        assertFalse(Objects.equals(e1, e2));

        System.out.println(e1.toString());
        System.out.println(e2.toString());
    }

    @Test
    public void classTest() {
        Entity2 e1 = new Entity2();
        Entity2 e2 = new Entity2();

        e1.setName("test");
        e1.setValue(1);
        e1.setDescription("desc");

        e2.setName("text");
        e2.setValue(1);
        e2.setDescription("desc");

        assertTrue(Objects.equals(e1, e2));

        e2.setName("test");
        e2.setValue(1);

        assertTrue(Objects.equals(e1, e2));

        e2.setName("test");
        e2.setValue(2);

        assertFalse(Objects.equals(e1, e2));

        System.out.println(e1.toString());
        System.out.println(e2.toString());
    }

    @Test
    public void EqualsBuilderTest() {
        Entity3 e1 = new Entity3();
        Entity3 e2 = new Entity3();

        e1.setName("test");
        e1.setValue(1);
        e1.setDescription("desc");

        e2.setName("text");
        e2.setValue(1);
        e2.setDescription("desc");

        assertTrue(Objects.equals(e1, e2));

        e2.setName("test");
        e2.setValue(1);

        assertTrue(Objects.equals(e1, e2));

        e2.setName("test");
        e2.setValue(2);

        assertFalse(Objects.equals(e1, e2));
    }

    @Test
    public void basicTest() {
        Entity4 e1 = new Entity4();
        Entity4 e2 = new Entity4();

        e1.setName("test");
        e1.setValue(1);
        e1.setDescription("desc");

        e2.setName("text");
        e2.setValue(1);
        e2.setDescription("desc");

        assertTrue(Objects.equals(e1, e2));

        e2.setName("test");
        e2.setValue(1);

        assertTrue(Objects.equals(e1, e2));

        e2.setName("test");
        e2.setValue(2);

        assertFalse(Objects.equals(e1, e2));
    }
}
