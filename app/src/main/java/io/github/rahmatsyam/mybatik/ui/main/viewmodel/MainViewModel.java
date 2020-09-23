package io.github.rahmatsyam.mybatik.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.rahmatsyam.mybatik.data.model.ResultModel;
import io.github.rahmatsyam.mybatik.data.repo.BatikRepo;

public class MainViewModel extends AndroidViewModel {

    private BatikRepo batikRepo;

    public MainViewModel(@NonNull Application application) {
        super(application);
        batikRepo = new BatikRepo();
    }

    public LiveData<List<ResultModel>> getDataList() {
        return batikRepo.getData();
    }

    public LiveData<List<ResultModel>> getDataBatikPopular() {
        return batikRepo.getBatikPopular();
    }

}
