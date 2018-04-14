package receipter.aldvc.receipter3.screen.test;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.repository.ReceiptRepository;
import rx.Observable;

/**
 * Created by advortsov.
 */
public class TestReceiptRepository implements ReceiptRepository {

//    @NonNull
//    @Override
//    public Observable<List<Receipt>> allReceipts() {
//        return Observable.empty();
//    }

    @NonNull
    @Override
    public Observable<Receipt> receipt(@NonNull String fn, @NonNull String fd, @NonNull String fs) {
        return Observable.empty();
    }

    @NonNull
    @Override
    public Observable<Receipt> save(Receipt receipt) {
        return Observable.empty();
    }

    @NonNull
    @Override
    public Receipt simpleSave(Receipt receipt) {
        return null;
    }

    @NonNull
    @Override
    public Observable<List<Receipt>> receiptsByPeriod(Date from, Date to) {
        return null;
    }

}

