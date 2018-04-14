package receipter.aldvc.receipter3.screen.receipts;

import android.support.annotation.NonNull;

import java.util.List;

import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.screen.general.LoadingView;

public interface ReceiptsView extends LoadingView {

    void showError();

    void showReceipts(@NonNull List<Receipt> receipts);

    void startQrCamera();

    void showReceiptItems(Receipt receipt);

    void openCalendar();

    void showReceiptsSum(List<Receipt> receipts);
}
