package receipter.aldvc.receipter3.screen.qr_camera;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import receipter.aldvc.receipter3.repository.RepositoryProvider;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by advortsov.
 */
@InjectViewState
public class QrScannerPresenter extends MvpPresenter<QrScannerView> implements ZXingScannerView.ResultHandler {

    public void createReceipt(String fn, String fd, String fs) {
        RepositoryProvider.provideReceiptRepository()
                .receipt(fn, fd, fs)
                .doOnSubscribe(getViewState()::showLoading)
                .doOnTerminate(getViewState()::hideLoading)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::closeQrScanner,
                        throwable -> getViewState().showLoadingError(throwable));
    }

    @Override
    public void handleResult(Result rawResult) {
        String fn = getQueryParameter(rawResult.getText(), "fn");
        String fd = getQueryParameter(rawResult.getText(), "i");
        String fs = getQueryParameter(rawResult.getText(), "fp");
        createReceipt(fn, fd, fs);
    }

    private String getQueryParameter(String query, String param) {
        try {
            String cutFromLeft = query.substring(query.indexOf(param + "=") + 1, query.length());
            return cutFromLeft.substring(0, query.indexOf("&"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not parse result of qr scan: " + e.getMessage());
        }
    }


}
