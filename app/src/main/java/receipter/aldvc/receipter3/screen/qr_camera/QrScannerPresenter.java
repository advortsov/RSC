package receipter.aldvc.receipter3.screen.qr_camera;

import android.net.Uri;

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
        Uri uri = Uri.parse("www.ddd.ru/?" + rawResult.getText());
        String fn = uri.getQueryParameter("fn");
        String fd = uri.getQueryParameter("i");
        String fs = uri.getQueryParameter("fp");
        createReceipt(fn, fd, fs);
    }


}
