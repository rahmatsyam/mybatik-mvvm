package io.github.rahmatsyam.mybatik.data.repo;

import io.github.rahmatsyam.mybatik.data.model.ResultModel;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class RXBus {

    private static BehaviorSubject<ResultModel> behaviorSubject = BehaviorSubject.create();

    public static BehaviorSubject<ResultModel> getBehaviorSubject() {
        return behaviorSubject;
    }
}
