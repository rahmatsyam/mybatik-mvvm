package io.github.rahmatsyam.mybatik.data.api;

import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

public class APIClient {

    private static String Tag1 = "Batik";
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://batikita.herokuapp.com/index.php/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.d(message, Tag1));

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            Moshi moshi = new Moshi.Builder().build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
//                    .addCallAdapterFactory(RetryCallAdapterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
