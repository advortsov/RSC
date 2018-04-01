package receipter.aldvc.receipter3.screen.receipts;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import receipter.aldvc.receipter3.R;
import receipter.aldvc.receipter3.content.dto.Item;
import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.screen.general.LoadingDialog;
import receipter.aldvc.receipter3.screen.general.LoadingView;
import receipter.aldvc.receipter3.screen.qr_camera.QrScannerActivity;
import receipter.aldvc.receipter3.widget.BaseAdapter;
import receipter.aldvc.receipter3.widget.DividerItemDecoration;
import receipter.aldvc.receipter3.widget.EmptyRecyclerView;


/**
 * @author Artur Vasilov
 */
public class ReceiptsActivity extends MvpAppCompatActivity implements ReceiptsView,
        BaseAdapter.OnItemClickListener<Receipt> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    @BindView(R.id.fab)
    FloatingActionButton startQrScannerBtn;

    @InjectPresenter
    ReceiptsPresenter mPresenter;

    LoadingView mLoadingView;

    ReceiptsAdapter mReceiptsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_receipts);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);

        mReceiptsAdapter = new ReceiptsAdapter(new ArrayList<>());
        mReceiptsAdapter.attachToRecyclerView(mRecyclerView);
        mReceiptsAdapter.setOnItemClickListener(this);

        mPresenter.presentAllReceipts();

        startQrScannerBtn.setOnClickListener(view -> this.startQrCamera());
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
        mLoadingView.showLoadingError(e);
    }

    @Override
    public void showReceipts(@NonNull List<Receipt> receipts) {
        mReceiptsAdapter.changeDataSet(receipts);
    }

    @Override
    public void showError() {
        mReceiptsAdapter.clear();
    }

    @Override
    public void onItemClick(@NonNull Receipt item) {
        mPresenter.onItemClick(item);
    }

    @Override
    public void startQrCamera() {
        QrScannerActivity.start(this);
    }

    @Override
    public void showReceiptItems(Receipt receipt) {

        RealmList<Item> items = receipt.getItems();
        StringBuilder message = new StringBuilder();
        for (Item i : items) {
            message.append(i.getName()).append(" ").append(i.getQuantity()).append("x")
                    .append(i.getPrice()).append(" = ").append(i.getSum()).append("\n");
        }
        // TODO
        new AlertDialog.Builder(this)
                .setTitle(receipt.getRetailPlaceAddress())
                .setMessage(message.toString())
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.presentAllReceipts();
    }
}
