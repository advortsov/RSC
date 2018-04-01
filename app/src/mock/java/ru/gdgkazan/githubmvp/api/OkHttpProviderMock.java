package ru.gdgkazan.githubmvp.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import receipter.aldvc.receipter3.api.ApiFactory;
import receipter.aldvc.receipter3.api.LoggingInterceptor;

/**
 * @author Artur Vasilov
 */
public final class OkHttpProviderMock {

    private static volatile OkHttpClient sClient;

    private OkHttpProviderMock() {
    }

    @NonNull
    public static OkHttpClient provideClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    public static void recreate() {
        sClient = null;
        sClient = buildClient();
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(MockingInterceptor.create())
                .addInterceptor(LoggingInterceptor.create())
                .build();
    }

}
