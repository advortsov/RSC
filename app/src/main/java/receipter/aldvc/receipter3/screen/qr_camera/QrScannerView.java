package receipter.aldvc.receipter3.screen.qr_camera;

import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.screen.general.LoadingView;


public interface QrScannerView extends LoadingView {

    void closeQrScanner(Receipt receipt);

}