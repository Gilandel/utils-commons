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

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Objects;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.RunnerException;

import fr.landel.utils.microbenchmark.AbstractMicrobenchmark;
import samples.builder.Entity1;
import samples.builder.Entity2;
import samples.builder.Entity3;
import samples.builder.Entity4;
import samples.builder.EqualsProperty;

/**
 * Check performance {@link EqualsProperty}
 *
 * @since Mar 6, 2017
 * @author Gilles
 *
 */
@State(Scope.Benchmark)
public class EqualsBuilderPerf extends AbstractMicrobenchmark {

    @Override
    protected double getExpectedMinNbOpsPerSeconds() {
        return 1_000_000d;
    }

    /**
     * Test method for {@link EqualsProperty}.
     */
    @Benchmark
    public void testClass() {
        Entity2 e1 = new Entity2();
        Entity2 e2 = new Entity2();

        e1.setName("test");
        e1.setValue(1);
        e1.setDescription("desc");

        e2.setName("text");
        e2.setValue(1);
        e2.setDescription("desc");

        Objects.equals(e1, e2);

        e2.setName("test");
        e2.setValue(1);

        Objects.equals(e1, e2);

        e2.setName("test");
        e2.setValue(2);

        Objects.equals(e1, e2);
    }

    /**
     * Test method for {@link EqualsProperty}.
     */
    @Benchmark
    public void testInterface() {
        Entity1 e1 = new Entity1();
        Entity1 e2 = new Entity1();

        e1.setName("test");
        e1.setValue(1);
        e1.setDescription("desc");

        e2.setName("text");
        e2.setValue(1);
        e2.setDescription("desc");

        Objects.equals(e1, e2);

        e2.setName("test");
        e2.setValue(1);

        Objects.equals(e1, e2);

        e2.setName("test");
        e2.setValue(2);

        Objects.equals(e1, e2);
    }

    /**
     * Test method for {@link EqualsProperty}.
     */
    @Benchmark
    public void testEqualsBuilder() {
        Entity3 e1 = new Entity3();
        Entity3 e2 = new Entity3();

        e1.setName("test");
        e1.setValue(1);
        e1.setDescription("desc");

        e2.setName("text");
        e2.setValue(1);
        e2.setDescription("desc");

        Objects.equals(e1, e2);

        e2.setName("test");
        e2.setValue(1);

        Objects.equals(e1, e2);

        e2.setName("test");
        e2.setValue(2);

        Objects.equals(e1, e2);
    }

    /**
     * Test method for {@link EqualsProperty}.
     */
    @Benchmark
    public void testBasic() {
        Entity4 e1 = new Entity4();
        Entity4 e2 = new Entity4();

        e1.setName("test");
        e1.setValue(1);
        e1.setDescription("desc");

        e2.setName("text");
        e2.setValue(1);
        e2.setDescription("desc");

        Objects.equals(e1, e2);

        e2.setName("test");
        e2.setValue(1);

        Objects.equals(e1, e2);

        e2.setName("test");
        e2.setValue(2);

        Objects.equals(e1, e2);
    }

    @Test
    public void testPerf() throws IOException, RunnerException {
        assertNotNull(super.run());
    }
}
