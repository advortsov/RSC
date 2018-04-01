package receipter.aldvc.receipter3.screen.general;

import com.arellomobile.mvp.MvpView;

/**
 * @author Artur Vasilov
 */
public interface LoadingView extends MvpView {

    void showLoading();

    void hideLoading();

    void showLoadingError(Throwable e);
}