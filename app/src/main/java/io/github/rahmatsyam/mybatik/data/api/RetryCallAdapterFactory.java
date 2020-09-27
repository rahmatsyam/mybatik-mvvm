package io.github.rahmatsyam.mybatik.data.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

public class RetryCallAdapterFactory extends CallAdapter.Factory {

    private static final String Tag = "RetryCallAdapterFactory";

    public static RetryCallAdapterFactory create() {
        return new RetryCallAdapterFactory();
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(@NotNull Type returnType, @NotNull Annotation[] annotations, @NotNull Retrofit retrofit) {
        /**
         * You can setup a default max retry count for all connections.
         */
        int itShouldRetry = 0;
        final Retry retry = getRetry(annotations);
        if (retry != null) {
            itShouldRetry = retry.max();
        }
        Timber.d("Starting a CallAdapter with {} retries.%s", itShouldRetry);

        return new RetryCallAdapter<>(
                retrofit.nextCallAdapter(this, returnType, annotations),
                itShouldRetry
        );
    }

    private Retry getRetry(@NonNull Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Retry) {
                return (Retry) annotation;
            }
        }
        return null;
    }

    static final class RetryCallAdapter<R, T> implements CallAdapter<R, T> {

        private final CallAdapter<R, T> delegated;
        private final int maxRetries;

        public RetryCallAdapter(CallAdapter<R, T> delegated, int maxRetries) {
            this.delegated = delegated;
            this.maxRetries = maxRetries;
        }

        @Override
        public Type responseType() {
            return delegated.responseType();
        }

        @Override
        public T adapt(@NotNull Call<R> call) {
            return delegated.adapt(maxRetries > 0 ? new RetryingCall<>(call, maxRetries) : call);
        }
    }

    static final class RetryingCall<R> implements Call<R> {

        /**
         * Synchronously send the request and return its response.
         *
         * @throws IOException      if a problem occurred talking to the server.
         * @throws RuntimeException (and subclasses) if an unexpected error occurs creating the request
         * or decoding the response.
         */

        private final Call<R> delegated;
        private final int maxRetries;

        public RetryingCall(Call<R> delegated, int maxRetries) {
            this.delegated = delegated;
            this.maxRetries = maxRetries;
        }

        @Override
        public Response<R> execute() throws IOException {
            return delegated.execute();
        }

        /**
         * Asynchronously send the request and notify {@code callback} of its response or if an error
         * occurred talking to the server, creating the request, or processing the response.
         *
         * @param callback
         */
        @Override
        public void enqueue(@NotNull Callback<R> callback) {
            delegated.enqueue(new RetryCallback<>(delegated, callback, maxRetries));
        }

        /**
         * Returns true if this call has been either {@linkplain #execute() executed} or {@linkplain
         * #enqueue(Callback) enqueued}. It is an error to execute or enqueue a call more than once.
         */
        @Override
        public boolean isExecuted() {
            return delegated.isExecuted();
        }

        /**
         * Cancel this call. An attempt will be made to cancel in-flight calls, and if the call has not
         * yet been executed it never will be.
         */
        @Override
        public void cancel() {
            delegated.cancel();
        }

        /**
         * True if {@link #cancel()} was called.
         */
        @Override
        public boolean isCanceled() {
            return delegated.isCanceled();
        }

        /**
         * Create a new, identical call to this one which can be enqueued or executed even if this call
         * has already been.
         */
        @Override
        public Call<R> clone() {
            return new RetryingCall<>(delegated.clone(), maxRetries);
        }

        /**
         * The original HTTP request.
         */
        @Override
        public Request request() {
            return delegated.request();
        }

        @Override
        public Timeout timeout() {
            return null;
        }
    }

    static final class RetryCallback<T> implements Callback<T> {

        private final Call<T> call;
        private final Callback<T> callback;
        private final int maxRetries;

        public RetryCallback(Call<T> call, Callback<T> callback, int maxRetries) {
            this.call = call;
            this.callback = callback;
            this.maxRetries = maxRetries;
        }

        private AtomicInteger retryCount = new AtomicInteger(0);

        /**
         * Invoked for a received HTTP response.
         * <p>
         * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
         * Call {@link Response#isSuccessful()} to determine if the response indicates success.
         *
         * @param call
         * @param response
         */
        @Override
        public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {
            if (!response.isSuccessful() && retryCount.incrementAndGet() <= maxRetries) {
                Log.d(Tag, "Call with no success result code: {} " + response.code());
                retryCall();
            } else {
                callback.onResponse(call, response);
            }
        }

        /**
         * Invoked when a network exception occurred talking to the server or when an unexpected
         * exception occurred creating the request or processing the response.
         *
         * @param call
         * @param t
         */
        @Override
        public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
            Log.d(Tag, "Call failed with message:  " + t.getMessage(), t);
            if (retryCount.incrementAndGet() <= maxRetries) {
                retryCall();
            } else if (maxRetries > 0) {
                Log.d(Tag, "No retries left sending timeout up.");
                callback.onFailure(call, new TimeoutException(String.format("No retries left after %s attempts.", maxRetries)));
            } else {
                callback.onFailure(call, t);
            }

        }

        private void retryCall() {
            Log.w(Tag, "" + retryCount.get() + "/" + maxRetries + " " + " Retrying...");
            call.clone().enqueue(this);
        }

    }


}
