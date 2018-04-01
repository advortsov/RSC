package receipter.aldvc.receipter3.screen.receipts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import receipter.aldvc.receipter3.R;
import receipter.aldvc.receipter3.content.dto.Receipt;

import static receipter.aldvc.receipter3.utils.TextUtils.formatDate;
import static receipter.aldvc.receipter3.utils.TextUtils.formatPrice;

public class ReceiptHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.receiptDate)
    TextView mDate;

    @BindView(R.id.receiptSum)
    TextView mSum;

    public ReceiptHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Receipt receipt) {
        mDate.setText(formatDate(receipt.getDateTime()));
        mSum.setText(formatPrice(receipt.getTotalSum()));
    }

}