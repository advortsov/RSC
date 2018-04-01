package receipter.aldvc.receipter3.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public final class RepositoryProvider {

    private static ReceiptRepository sReceiptRepository;
    private static KeyValueStorage sKeyValueStorage;

    private RepositoryProvider() {
    }

    @NonNull
    public static ReceiptRepository provideReceiptRepository() {
        if (sReceiptRepository == null) {
            sReceiptRepository = new DefaultReceiptRepository();
        }
        return sReceiptRepository;
    }

    public static void setReceiptRepository(@NonNull ReceiptRepository receiptRepository) {
        sReceiptRepository = receiptRepository;
    }

    @NonNull
    public static KeyValueStorage provideKeyValueStorage() {
        if (sKeyValueStorage == null) {
            sKeyValueStorage = new HawkKeyValueStorage();
        }
        return sKeyValueStorage;
    }

    public static void setKeyValueStorage(@NonNull KeyValueStorage keyValueStorage) {
        sKeyValueStorage = keyValueStorage;
    }

    @MainThread
    public static void init() {
        sReceiptRepository = new DefaultReceiptRepository();
        sKeyValueStorage = new HawkKeyValueStorage();
    }
}
