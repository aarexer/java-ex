package utils;

import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;


@ParametersAreNonnullByDefault
public class AsyncTaskExecutor {

    private final ExecutorService executorService;

    public AsyncTaskExecutor(int threadPooSize) {
        this.executorService = Executors.newFixedThreadPool(threadPooSize);
    }

    @Nonnull
    public <T, U> List<ImmutablePair<U, T>> executeWaitResult(final List<T> tasks,
                                                              final Function<T, U> callableMethodReference) {
        return tasks
                .stream()
                .map(element ->
                        CompletableFuture.supplyAsync(
                                () -> callableMethodReference.apply(element),
                                executorService
                        ).handle((res, ex) -> {
                            if (ex != null) {
                                return ImmutablePair.of((U) null, element);
                            } else {
                                return ImmutablePair.of(res, (T) null);
                            }
                        })
                )
                .collect(Collectors.collectingAndThen(Collectors.toList(), this::unwrapCfResultCollection));
    }

    @Nonnull
    public <U, T> List<ImmutablePair<U, T>> unwrapCfResultCollection(final List<CompletableFuture<ImmutablePair<U, T>>> futures) {
        return CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(
                        ignored -> futures
                                .stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList())
                ).join();
    }
}