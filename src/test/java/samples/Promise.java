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
package samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.landel.utils.commons.function.ConsumerThrowable;
import fr.landel.utils.commons.function.PredicateThrowable;
import fr.landel.utils.commons.function.SupplierThrowable;
import fr.landel.utils.commons.tuple.MutableSingle;
import fr.landel.utils.commons.tuple.Single;

/**
 * Test a promise expression
 *
 * @since Apr 29, 2017
 * @author Gilles
 *
 */
public class Promise {

    private static final Logger LOGGER = LoggerFactory.getLogger(Promise.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MutableSingle<Response> response = Single.ofMutable(null);

        Output<Response, RuntimeException> output = execute().onSuccess(r -> {
            LOGGER.info("success: {}, {}", r.getStatus(), r.getMessage());
        }).onError(r -> {
            LOGGER.error("error 1: {}, {}", r.getStatus(), r.getMessage());
        }).onError(r -> {
            response.set(r);
            LOGGER.error("error 2: {}, {}", r.getStatus(), r.getMessage());
        });

        LOGGER.info("waiting, is null = {}", response.get() == null);

        output.complete();

        LOGGER.error("error 3: {}, {}", response.get().getStatus(), response.get().getMessage());
    }

    public static Output<Response, RuntimeException> execute() {
        final int status = 400;
        final int ok = 200;

        final int sleep = 1_000;

        final FutureTask<Response> future = createFuture(() -> {
            Thread.sleep(sleep);
            return new Response(status, "bad command");
        });

        future.run();

        return new Output<>(response -> response.getStatus() == ok, future);
    }

    private static <E extends Throwable> FutureTask<Response> createFuture(final SupplierThrowable<Response, E> supplier) {
        return new FutureTask<>(new Callable<Response>() {
            @Override
            public Response call() throws InterruptedException {
                return supplier.get();
            }
        });
    }

    static class Response {
        private final int status;
        private final String message;

        public Response(final int status, final String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public String getMessage() {
            return this.message;
        }
    }

    static class Output<T, E extends Throwable> {
        private final Output<T, E> previous;
        private final boolean success;
        private final ConsumerThrowable<T, E> exceptionSupplier;
        private final Predicate<T> predicate;
        private final Future<T> future;

        public Output(final PredicateThrowable<T, E> predicate, final Future<T> future) {
            this.success = true;
            this.previous = null;
            this.predicate = predicate;
            this.future = future;
            this.exceptionSupplier = null;
        }

        public Output(final Output<T, E> previous, final boolean success, final ConsumerThrowable<T, E> exceptionSupplier) {
            this.success = success;
            this.previous = previous;
            this.predicate = null;
            this.future = null;
            this.exceptionSupplier = exceptionSupplier;
        }

        public Output<T, E> onSuccess(final ConsumerThrowable<T, E> exceptionSupplier) {
            return new Output<>(this, true, exceptionSupplier);
        }

        public Output<T, E> onError(final ConsumerThrowable<T, E> exceptionSupplier) {
            return new Output<>(this, false, exceptionSupplier);
        }

        public void complete() throws InterruptedException, ExecutionException {
            Output<T, E> first = this;
            final List<Output<T, E>> list = new ArrayList<>();
            list.add(first);
            while ((first = first.previous) != null) {
                list.add(first);
            }
            Collections.reverse(list);

            final Output<T, E> root = list.get(0);
            final T obj = root.future.get();
            boolean success = root.predicate.test(obj);

            for (Output<T, E> output : list) {
                if (output.exceptionSupplier != null && output.success == success) {
                    output.exceptionSupplier.accept(obj);
                }
            }
        }
    }
}
