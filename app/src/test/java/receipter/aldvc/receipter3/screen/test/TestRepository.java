package receipter.aldvc.receipter3.screen.test;

import android.support.annotation.NonNull;

import java.util.List;

import receipter.aldvc.receipter3.content.dto.Receipt;

/**
 * Created by advortsov.
 */
public class TestRepository extends TestReceiptRepository {

    private final List<Receipt> mReceipts;
    private final boolean mIsError;

    public TestRepository(@NonNull List<Receipt> receipts, boolean isError) {
        mReceipts = receipts;
        mIsError = isError;
    }

//    @NonNull
//    @Override
//    public Observable<List<Receipt>> allReceipts() {
//        if (mIsError) {
//            return Observable.error(new IOException());
//        }
//        return Observable.just(mReceipts);
//    }
}