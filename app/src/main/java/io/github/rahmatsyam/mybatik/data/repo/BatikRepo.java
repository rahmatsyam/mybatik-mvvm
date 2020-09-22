package io.github.rahmatsyam.mybatik.data.repo;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.rahmatsyam.mybatik.data.api.APIUtils;
import io.github.rahmatsyam.mybatik.data.model.BatikModel;
import io.github.rahmatsyam.mybatik.data.model.ResultModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class BatikRepo {

    public LiveData<List<ResultModel>> getData() {
        MutableLiveData<List<ResultModel>> data = new MutableLiveData<>();

        APIUtils.getApiInterface()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BatikModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BatikModel batikModel) {
                        data.setValue(batikModel.getHasil());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return data;
    }

    public LiveData<List<ResultModel>> getBatikPopular(){
        MutableLiveData<List<ResultModel>> data = new MutableLiveData<>();

        APIUtils.getApiInterface()
                .getBatikPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BatikModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BatikModel batikModel) {
                        data.setValue(batikModel.getHasil());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return data;
    }

}
