package receipter.aldvc.receipter3.repository;

import android.support.annotation.NonNull;
import android.util.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import receipter.aldvc.receipter3.api.ApiFactory;
import receipter.aldvc.receipter3.content.dto.Receipt;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;


public class DefaultReceiptRepository implements ReceiptRepository {

    private static final String USERNAME = "+79521001847";
    private static final String PASSWORD = "573761";

    @NonNull
    @Override
    public Observable<Receipt> receipt(@NonNull String fn, @NonNull String fd, @NonNull String fs) {
        return ApiFactory.getFnsService()
                .receipt(getHeaders(), fn, fd, fs, "no")
                .flatMap(documentResponse -> save(documentResponse.getDocument().getReceipt()))
                .compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<Receipt> save(Receipt receipt) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> r.insertOrUpdate(receipt));
        return Observable.just(receipt);
    }

    @NonNull
    @Override
    public Receipt simpleSave(Receipt receipt) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> r.insertOrUpdate(receipt));
        return receipt;
    }

    @NonNull
    @Override
    public Observable<List<Receipt>> receiptsByPeriod(Date from, Date to) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Receipt> receipts = realm
                .where(Receipt.class)
                .findAll().where().between("dateTime", from, to).findAll()
                .sort("dateTime", Sort.DESCENDING);
        return Observable
                .just(realm.copyFromRealm(receipts))
                .compose(RxUtils.async());
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Device-Id", "cc23b318156e473faa6e517bf091a0f0");
        headers.put("Device-OS", "Android 4.4.4");
        headers.put("Version", "2");
        headers.put("ClientVersion", "1.4.1.3");
        String credentials = USERNAME + ":" + PASSWORD;
        String auth = "Basic "
                + Base64.encodeToString(credentials.getBytes(),
                Base64.NO_WRAP);
        headers.put("Authorization", auth);
        return headers;
    }
}
