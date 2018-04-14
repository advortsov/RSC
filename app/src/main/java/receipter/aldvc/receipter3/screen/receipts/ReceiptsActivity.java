package receipter.aldvc.receipter3.screen.receipts;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import receipter.aldvc.receipter3.R;
import receipter.aldvc.receipter3.content.dto.Item;
import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.screen.calendar.PeriodActivity;
import receipter.aldvc.receipter3.screen.general.LoadingDialog;
import receipter.aldvc.receipter3.screen.general.LoadingView;
import receipter.aldvc.receipter3.screen.qr_camera.QrScannerActivity;
import receipter.aldvc.receipter3.utils.TextUtils;
import receipter.aldvc.receipter3.widget.BaseAdapter;
import receipter.aldvc.receipter3.widget.DividerItemDecoration;
import receipter.aldvc.receipter3.widget.EmptyRecyclerView;


public class ReceiptsActivity extends MvpAppCompatActivity implements ReceiptsView,
        BaseAdapter.OnItemClickListener<Receipt> {

    private final int PERIOD_REQUEST = 42;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.title)
    TextView period;

    @BindView(R.id.periodSum)
    TextView periodSum;

    @BindView(R.id.calendar_btn)
    ImageButton mCalndarImageButton;

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

    private Date from;
    private Date to;

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

        initPeriod();
        period.setText(getFormattedPeriod());

        mPresenter.receiptsByPeriod(from, to);

        startQrScannerBtn.setOnClickListener(view -> this.startQrCamera());
        mCalndarImageButton.setOnClickListener(view -> this.openCalendar());
    } // buttonA.setOnClickListener(v -> Observable.just("A").subscribe(s -> toast(s)));

    private CharSequence getFormattedPeriod() {
        return TextUtils.formatToolbarPeriod(from, to);
    }

    private void initPeriod() {
        this.from = TextUtils.getStartOfTheDay(new Date());
        this.to = new Date();
    }

    @Override
    public void openCalendar() {
        Intent intent = new Intent(this, PeriodActivity.class);
        startActivityForResult(intent, PERIOD_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERIOD_REQUEST && resultCode == RESULT_OK) {
            initPeriodFrom(data);
            mPresenter.receiptsByPeriod(from, to);
        }
    }

    private void initPeriodFrom(Intent data) {
        Long f = data.getLongExtra("from", 0L);
        Long t = data.getLongExtra("to", 0L);
        this.from = new Date(f);
        this.to = new Date(t);
        period.setText(getFormattedPeriod());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.receiptsByPeriod(from, to);
    }

    @Override
    public void showReceiptsSum(List<Receipt> receipts) {
        periodSum.setText(totalSum(receipts));
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

    private CharSequence totalSum(List<Receipt> receipts) {
        int sum = 0;
        for (Receipt r : receipts) {
            sum += r.getTotalSum();
        }
        return String.valueOf(TextUtils.formatPrice(sum));
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

}
