package io.github.rahmatsyam.mybatik.util;

import java.util.ArrayList;
import java.util.List;

import io.github.rahmatsyam.mybatik.data.model.ResultModel;

public class ArrayHelper {

    public static List<ResultModel> filterLimitData(List<ResultModel> repo) {
        List<ResultModel> limitData = new ArrayList<>();
        for (ResultModel resultModel : repo) {
            limitData.add(resultModel);
            if (limitData.size() > 3) {
                break;
            }
        }

        return limitData;

    }

}
