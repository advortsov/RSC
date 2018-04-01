package receipter.aldvc.receipter3.screen.general;

import io.realm.RealmList;
import receipter.aldvc.receipter3.content.dto.Item;

/**
 * Created by advortsov.
 */
public interface ReceiptDetailsView {


    void showReceiptItems(RealmList<Item> items);
}

