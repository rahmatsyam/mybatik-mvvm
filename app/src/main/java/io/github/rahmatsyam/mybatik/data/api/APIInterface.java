package io.github.rahmatsyam.mybatik.data.api;

import io.github.rahmatsyam.mybatik.data.model.BatikModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @Retry
    @GET("batik/all")
    Observable<BatikModel> getAllBatik();

    @GET("batik/")
    Observable<BatikModel> search(@Query("batik") String batik);

    @GET("batik/popular")
    Observable<BatikModel> getBatikPopular();

}