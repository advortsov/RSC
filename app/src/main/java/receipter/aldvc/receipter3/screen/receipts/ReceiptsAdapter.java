package receipter.aldvc.receipter3.screen.receipts;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import receipter.aldvc.receipter3.R;
import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.widget.BaseAdapter;


public class ReceiptsAdapter extends BaseAdapter<ReceiptHolder, Receipt> {

    public ReceiptsAdapter(@NonNull List<Receipt> items) {
        super(items);
    }

    @Override
    public ReceiptHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReceiptHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_receipt, parent, false));
    }

    @Override
    public void onBindViewHolder(ReceiptHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Receipt receipt = getItem(position);
        holder.bind(receipt);
    }

}
