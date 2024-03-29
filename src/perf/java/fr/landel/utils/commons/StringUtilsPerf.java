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
package fr.landel.utils.commons;

import java.util.Collections;

import org.apache.commons.lang3.tuple.Pair;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * Check {@link StringUtils} performance
 *
 * @since Feb 28, 2017
 * @author Gilles
 *
 */
@Fork(1)
@State(Scope.Benchmark)
public class StringUtilsPerf {

//    @Override
//    protected double getExpectedMinNbOpsPerSeconds() {
//        return 100_000d;
//    }

    /**
     * Test method for
     * {@link StringUtils#inject(java.lang.CharSequence, java.lang.Object[])}.
     */
    @Benchmark
    public void testInject() {
        StringUtils.inject("I'll go to the beach this afternoon");
        StringUtils.inject("I'll go to {} {3} {} {2}");
        StringUtils.inject("I'll go to {} {3} {} {2}", "the", "this", "afternoon", "beach");
        StringUtils.inject("I'll go to {{}}{3} {} {2}{{0}} {4} {text}", "the", "this", "afternoon", "beach");
    }

    /**
     * Test method for
     * {@link StringUtils#injectKeys(CharSequence, java.util.Map.Entry...)}.
     */
    @Benchmark
    public void testInjectKeys() {
        StringUtils.injectKeys("I'll go to {where} this {when}", Pair.of("where", "beach"), Pair.of("when", "afternoon"));
        StringUtils.injectKeys("I'll go to {where} this {when} {{when}}", Pair.of("where", "beach"), Pair.of("when", "afternoon"));
        StringUtils.injectKeys("I'll go to {where} this {when}",
                MapUtils2.newHashMap(Pair.of("where", "beach"), Pair.of("when", "afternoon")));
        StringUtils.injectKeys("I'll go to {where}", Collections.singletonMap("where", "beach"));
    }

    /**
     * Test method for
     * {@link StringUtils#inject(java.lang.CharSequence, java.lang.Object[])}.
     */
    // @Benchmark
    public void testFormat() {
        // last comparison
        // StringUtils.inject: 570 449,731 ops/s
        // String.format: 140 260,855 ops/s

        String.format("I'll go to the beach this afternoon");
        String.format("I'll go to %%s %%4$s %%s %%3$s");
        String.format("I'll go to %s %4$s %s %3$s", "the", "this", "afternoon", "beach");
        String.format("I'll go to %%4$s %s %3$s%%s %%5$s %%text", "the", "this", "afternoon", "beach");
    }
}
