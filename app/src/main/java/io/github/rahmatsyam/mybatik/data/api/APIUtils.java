package io.github.rahmatsyam.mybatik.data.api;

public class APIUtils {

    private static APIInterface apiInterface;

    public static APIInterface getApiInterface() {
        if (apiInterface == null) {
            apiInterface = APIClient.getClient().create(APIInterface.class);
        }
        return apiInterface;
    }
}
