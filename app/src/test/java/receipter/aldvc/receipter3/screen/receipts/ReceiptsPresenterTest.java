package receipter.aldvc.receipter3.screen.receipts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.repository.RepositoryProvider;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by advortsov.
 */
@RunWith(JUnit4.class)
public class ReceiptsPresenterTest {

    @Mock
    ReceiptsView$$State repositoriesViewState;

    private ReceiptsPresenter mPresenter;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mPresenter = new ReceiptsPresenter();
        mPresenter.setViewState(repositoriesViewState);

        RxJavaPlugins.getInstance().reset();
        RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
            @Override
            public Scheduler getIOScheduler() {
                return Schedulers.immediate();
            }
        });
        RxAndroidPlugins.getInstance().reset();
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void testCreated() {
        assertNotNull(mPresenter);
        Mockito.verifyNoMoreInteractions(repositoriesViewState);
    }

//    @Test
//    public void testProgressShowingDuringLoading() {
//        ReceiptRepository repository = new TestRepository(new ArrayList<>(), false);
//        RepositoryProvider.setReceiptRepository(repository);
//
//        mPresenter.presentAllReceipts();
//        Mockito.verify(repositoriesViewState).showLoading();
//        Mockito.verify(repositoriesViewState).hideLoading();
//    }

//    @Test
//    public void testReceiptsLoaded() {
//        List<Receipt> receipts = new ArrayList<>();
//        receipts.add(new Receipt());
//        receipts.add(new Receipt());
//        ReceiptRepository repository = new TestRepository(receipts, false);
//        RepositoryProvider.setReceiptRepository(repository);
//
//        mPresenter.presentAllReceipts();
//        Mockito.verify(repositoriesViewState).showReceipts(receipts);
//    }
//
//    @Test
//    public void testErrorHandled() {
//        ReceiptRepository repository = new TestRepository(new ArrayList<>(), true);
//        RepositoryProvider.setReceiptRepository(repository);
//
//        mPresenter.presentAllReceipts();
//        Mockito.verify(repositoriesViewState).showError();
//    }

    @Test
    public void testShowItemsOnClick() {
        Receipt receipt = new Receipt();
        mPresenter.onItemClick(receipt);
        Mockito.verify(repositoriesViewState).showReceiptItems(receipt);
    }

    @SuppressWarnings("ConstantConditions")
    @After
    public void tearDown() {
        RepositoryProvider.setReceiptRepository(null);
    }


}
