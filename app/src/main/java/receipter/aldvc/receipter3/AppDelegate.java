package receipter.aldvc.receipter3;

import android.content.Context;
import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import receipter.aldvc.receipter3.content.dto.Receipt;

/**
 * Created by advortsov.
 */
public class AppDelegate extends android.app.Application {
    private final static long ONE_DAY = 1000 * 60 * 60 * 24;
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

        Date now = new Date();
        Date d2 = new Date(now.getTime() - ONE_DAY);
        Date d3 = new Date(now.getTime() - 31 * ONE_DAY);

        Receipt receipt1 = new Receipt();
        receipt1.setFiscalSign("ddsdssdsd");
        receipt1.setDateTime(new Date());
        receipt1.setTotalSum(565);

        Receipt receipt2 = new Receipt();
        receipt2.setFiscalSign("ddsd");
        receipt2.setDateTime(d2);
        receipt2.setTotalSum(55);

        Receipt receipt3 = new Receipt();
        receipt3.setFiscalSign("dds");
        receipt3.setDateTime(d3);
        receipt3.setTotalSum(775);


//        Realm.getDefaultInstance().beginTransaction();
//        RealmResults<Item> allItems = Realm.getDefaultInstance().where(Item.class).findAll();
//        Realm.getDefaultInstance().commitTransaction();

//        RepositoryProvider.provideReceiptRepository().simpleSave(receipt1);
//        RepositoryProvider.provideReceiptRepository().simpleSave(receipt2);
//        RepositoryProvider.provideReceiptRepository().simpleSave(receipt3);
    }
}
