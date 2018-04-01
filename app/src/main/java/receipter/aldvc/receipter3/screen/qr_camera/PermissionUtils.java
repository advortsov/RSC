package receipter.aldvc.receipter3.screen.qr_camera;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

/**
 * Created by advortsov.
 */
public class PermissionUtils {

    private static final int REQUEST_CAMERA = 1;
    private static final int CAM_ID = Camera.CameraInfo.CAMERA_FACING_BACK;

    private Activity activity;
    private Context context;

    public PermissionUtils(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public void checkAll() {
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.M) {
            if (!hasPermission()) {
                requestPermission();
            }
        }
    }

    private boolean hasPermission() {
        return (ContextCompat.checkSelfPermission(context, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult(Context context, int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(context, "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    } else {
                        requestPermission();
                        Toast.makeText(context, "Permission Denied, You cannot access camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (activity.shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel(
                                        (dialog, which) -> activity.requestPermissions(new String[]{CAMERA},
                                                REQUEST_CAMERA));
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage("You need to allow access to both the permissions")
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void startCamera(@NonNull ZXingScannerView.ResultHandler handler,
                            @NonNull ZXingScannerView mScannerView) {
        checkPermission();
        mScannerView.setResultHandler(handler);
        mScannerView.startCamera(CAM_ID);
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermission()) {
                requestPermission();
            }
        }
    }

}
