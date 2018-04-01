package receipter.aldvc.receipter3;

import android.content.Context;
import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by advortsov.
 */
public class AppDelegate extends android.app.Application {
    private static Context sContext;

    @NonNull
    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        sContext = this;
        super.onCreate();
        Hawk.init(this).build();
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.
                Builder().
                deleteRealmIfMigrationNeeded().
                build();

        Realm.setDefaultConfiguration(configuration);
    }
}
