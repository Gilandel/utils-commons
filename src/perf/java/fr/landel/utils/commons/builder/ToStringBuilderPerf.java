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

import java.awt.Color;
import java.util.Optional;
import java.util.function.Function;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * Check performance {@link ToStringBuilder}
 *
 * @since Mar 6, 2017
 * @author Gilles
 *
 */
@State(Scope.Benchmark)
public class ToStringBuilderPerf {

//    @Override
//    protected double getExpectedMinNbOpsPerSeconds() {
//        return 100_000d;
//    }

	/**
	 * Test method for {@link ToStringBuilder}.
	 */
	@Benchmark
	public void testBuild() {
		final Function<String, CharSequence> upper = text -> text.toUpperCase();

		final ToStringBuilder builder = new ToStringBuilder("test");
		builder.append(Color.BLACK);
		builder.appendAndFormat(Color.BLACK, color -> String.valueOf(color.getBlue()));
		builder.append("blue", Color.BLUE);
		builder.appendAndFormat("value", 120_156.568_9, ToStringBuilder.NUMBER_FORMATTER);
		builder.appendIfPresent(Optional.empty());
		builder.appendIfPresent(Optional.of("optional"));
		builder.appendAndFormatIfPresent(Optional.of("optional"), upper);
		builder.appendIfPresent("optional", Optional.ofNullable(null));
		builder.appendIfPresent("optional", Optional.of("optional"));
		builder.appendAndFormatIfPresent("optional", Optional.of("optional"), upper);
		builder.build();
	}
}
