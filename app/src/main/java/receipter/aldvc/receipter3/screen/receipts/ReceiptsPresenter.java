package receipter.aldvc.receipter3.screen.receipts;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.repository.RepositoryProvider;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by aldvc on 15.03.2018.
 */
@InjectViewState
public class ReceiptsPresenter extends MvpPresenter<ReceiptsView> {

    public void onItemClick(@NonNull Receipt receipt) {
        getViewState().showReceiptItems(receipt);
    }

    public void receiptsByPeriod(Date from, Date to) {
        RepositoryProvider.provideReceiptRepository()
                .receiptsByPeriod(from, to)
                .doOnSubscribe(getViewState()::showLoading)
                .doOnTerminate(getViewState()::hideLoading)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(receipts -> {
                            getViewState().showReceipts(receipts);
                            getViewState().showReceiptsSum(receipts);
                        },
                        throwable -> getViewState().showError());
    }

}
