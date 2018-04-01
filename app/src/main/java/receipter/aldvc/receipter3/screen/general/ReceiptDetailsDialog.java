package receipter.aldvc.receipter3.screen.general;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.List;

import io.realm.RealmList;
import receipter.aldvc.receipter3.R;
import receipter.aldvc.receipter3.content.dto.Item;

/**
 * Created by advortsov.
 */
public class ReceiptDetailsDialog extends DialogFragment {

    @NonNull
    public static ReceiptDetailsView view(@NonNull FragmentManager fm) {
//        fm;
        return new ReceiptDetailsDialog.ReceiptDetailsDialogView();
    }

    @NonNull
    public static ReceiptDetailsView view(@NonNull Fragment fragment) {
        return view(fragment.getFragmentManager());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        setCancelable(true);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setView(View.inflate(getActivity(), R.layout.dialog_receipt_details, null))
                .create();
    }

    private static class ReceiptDetailsDialogView implements ReceiptDetailsView {

        @Override
        public void showReceiptItems(RealmList<Item> items) {

        }

        private String prepareItemsDetalisation(List<Item> items) {
            StringBuilder sb = new StringBuilder();
            for (Item i : items) {
                sb.append(i.getName()).append("   ")
                        .append(i.getQuantity()).append(" x ").append(i.getPrice())
                        .append(" = ").append(i.getSum()).append("\n");
            }
            return sb.toString();
        }
    }

}
