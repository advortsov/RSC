package receipter.aldvc.receipter3.screen.receipts;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.repository.RepositoryProvider;

/**
 * Created by aldvc on 15.03.2018.
 */
@InjectViewState
public class ReceiptsPresenter extends MvpPresenter<ReceiptsView> {

    public void presentAllReceipts() {
        RepositoryProvider.provideReceiptRepository()
                .allReceipts()
                .doOnSubscribe(getViewState()::showLoading)
                .doOnTerminate(getViewState()::hideLoading)
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(receipts -> getViewState().showReceipts(receipts),
                        throwable -> getViewState().showError());
    }

    public void onItemClick(@NonNull Receipt receipt) {
        getViewState().showReceiptItems(receipt);
    }
}
