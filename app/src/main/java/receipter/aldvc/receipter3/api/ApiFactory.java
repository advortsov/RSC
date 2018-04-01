package receipter.aldvc.receipter3.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import receipter.aldvc.receipter3.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.gdgkazan.githubmvp.api.OkHttpProviderMock;

public final class ApiFactory {

    private static volatile FnsService sService;

    private ApiFactory() {
    }

    @NonNull
    public static FnsService getFnsService() {
        FnsService service = sService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = buildRetrofit().create(FnsService.class);
                }
            }
        }
        return service;
    }

    public static void setFnsService(@NonNull FnsService service) {
        sService = service;
    }

    public static void recreate() {
        OkHttpProvider.recreate();
        sService = buildRetrofit().create(FnsService.class);
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
//                .client(OkHttpProvider.provideClient())
                .client(OkHttpProviderMock.provideClient()) // for testing
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
