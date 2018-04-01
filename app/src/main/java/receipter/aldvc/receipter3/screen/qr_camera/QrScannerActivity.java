package receipter.aldvc.receipter3.screen.qr_camera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.io.EOFException;

import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import receipter.aldvc.receipter3.R;
import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.screen.general.LoadingDialog;
import receipter.aldvc.receipter3.screen.general.LoadingView;

public class QrScannerActivity extends MvpAppCompatActivity implements QrScannerView {

    ZXingScannerView mScannerView;

    LoadingView mLoadingView;

    @InjectPresenter
    QrScannerPresenter mQrScannerPresenter;

    PermissionUtils permissionUtils;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, QrScannerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        setContentView(mScannerView);
        ButterKnife.bind(this);

        permissionUtils = new PermissionUtils(this, getApplicationContext());
        permissionUtils.checkAll();
    }

    @Override
    public void onResume() {
        super.onResume();
        permissionUtils.startCamera(mQrScannerPresenter, mScannerView);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScannerView != null) {
            mScannerView.stopCamera();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mScannerView != null) {
            mScannerView.stopCamera();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mScannerView != null) {
            mScannerView.stopCameraPreview();
            mScannerView.stopCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        permissionUtils.onRequestPermissionsResult(getApplicationContext(), requestCode,
                permissions, grantResults);
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showLoadingError(Throwable e) {
        if (e instanceof EOFException) {
            Toast.makeText(getApplicationContext(), R.string.not_in_fns_db_now, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        mLoadingView.showLoadingError(e);
        closeQrScanner(null);
    }

    @Override
    public void closeQrScanner(Receipt receipt) {
        if (receipt != null) {
            Toast.makeText(getApplicationContext(), R.string.new_receipt_was_added, Toast.LENGTH_LONG).show();
            if (mScannerView != null) {
                mScannerView.stopCamera();
                finish();
            }
        } else {
            Toast.makeText(getApplicationContext(), R.string.receipt_was_not_added, Toast.LENGTH_LONG).show();
        }

    }

}