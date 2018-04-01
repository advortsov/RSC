package receipter.aldvc.receipter3.repository;

import android.support.annotation.NonNull;

import java.util.List;

import receipter.aldvc.receipter3.content.dto.Receipt;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface ReceiptRepository {

    @NonNull
    Observable<List<Receipt>> allReceipts();

    @NonNull
    Observable<Receipt> receipt(@NonNull String fn, @NonNull String fd, @NonNull String fs);

    @NonNull
    Observable<Receipt> save(Receipt receipt);


//    @NonNull
//    Observable<Authorization> auth(@NonNull String login, @NonNull String password);
}
