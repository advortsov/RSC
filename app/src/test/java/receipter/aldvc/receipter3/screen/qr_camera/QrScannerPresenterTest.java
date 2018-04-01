package receipter.aldvc.receipter3.screen.qr_camera;

import com.google.zxing.Result;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import receipter.aldvc.receipter3.repository.ReceiptRepository;
import receipter.aldvc.receipter3.repository.RepositoryProvider;
import receipter.aldvc.receipter3.screen.test.TestRepository;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;

/**
 * Created by advortsov.
 */
@RunWith(JUnit4.class)
public class QrScannerPresenterTest {

    @Mock
    QrScannerView$$State qrScannerViewState;
    ///
    @Captor
    ArgumentCaptor<String> captor;
    private QrScannerPresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new QrScannerPresenter();
        mPresenter.setViewState(qrScannerViewState);

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

    @After
    public void tearDown() {
    }

    @Test
    public void testProgressShowingDuringCreating() {
        ReceiptRepository repository = new TestRepository(new ArrayList<>(), false);
        RepositoryProvider.setReceiptRepository(repository);
        mPresenter.createReceipt("111", "111", "111");

        verify(qrScannerViewState).showLoading();
        verify(qrScannerViewState).hideLoading();
    }

    @Test
    public void testCloseQrScanner() {
    }

    @Test
    public void testNoReceiptInFNSErrorHandle() {
    }

//    @Test
//    public void testHandleParseableResult() {
//         mPresenter.handleResult(new Result("t=20180330T1909&s=346.80&fn=8710000100135743&i=165863&fp=2010052824&n=1", null, null, null));
//
//        ArgumentCaptor<String> argument1 = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<String> argument2  = ArgumentCaptor.forClass(String.class);
//        ArgumentCaptor<String> argument3 = ArgumentCaptor.forClass(String.class);
//
////        Mockito.when(mPresenter.getHeaders()).thenReturn(mockedResult);
//
//        verify(mock(QrScannerPresenter.class)).createReceipt(argument1.capture(), argument2.capture(), argument3.capture());
//
//        assertEquals("8710000100135743", argument1.getValue() );
//        assertEquals("165863", argument2.getValue() );
//        assertEquals("2010052824", argument3.getValue() );
//    }


    @Test(expected = IllegalArgumentException.class)
    public void testHandleNotParseableResult() {
        mPresenter.handleResult(new Result("tsdfsddfdsffdsfds", null, null, null));
    }
}