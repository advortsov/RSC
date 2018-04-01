package receipter.aldvc.receipter3;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import receipter.aldvc.receipter3.content.dto.Receipt;
import receipter.aldvc.receipter3.repository.DefaultReceiptRepository;
import receipter.aldvc.receipter3.repository.ReceiptRepository;
import receipter.aldvc.receipter3.repository.RepositoryProvider;
import receipter.aldvc.receipter3.test.RxSchedulersTestRule;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ReceiptsRepositoryTest {

    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    private ReceiptRepository mRepository;

    @Before
    public void setUp() {
        mRepository = new DefaultReceiptRepository();
    }

//    @Test
//    public void testSuccessAuth() throws Exception {
////        Authorization authorization = mRepository.auth("root", "12345").toBlocking().first();
////        assertEquals(TOKEN, authorization.getToken());
////
////        KeyValueStorage storage = RepositoryProvider.provideKeyValueStorage();
////        assertEquals(TOKEN, storage.getToken());
////        assertEquals("root", storage.getUserName().toBlocking().first());
//    }
//
//    @Test
//    public void testErrorAuth() throws Exception {
////        RepositoryProvider.provideKeyValueStorage().saveToken(ERROR);
////        TestSubscriber<Authorization> testSubscriber = new TestSubscriber<>();
////        mRepository.auth("error", "12").subscribe(testSubscriber);
////
////        testSubscriber.assertError(HttpException.class);
////
////        KeyValueStorage storage = RepositoryProvider.provideKeyValueStorage();
////        assertEquals("", storage.getToken());
////        assertEquals("", storage.getUserName().toBlocking().first());
//    }

    @Test
    public void testFetchAllReceipts() {
        Receipt r1 = new Receipt();
        r1.setFiscalSign("ghhgfhhgf");
        Receipt r2 = new Receipt();
        r2.setFiscalSign("ghhgf");
        mRepository.save(r1).subscribe();
        mRepository.save(r2).subscribe();
        TestSubscriber<Receipt> testSubscriber = new TestSubscriber<>();
        mRepository.allReceipts().flatMap(Observable::from).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(2);
    }

    @Test
    public void testLoadAndCreateReceipt() {
        TestSubscriber<Receipt> testSubscriber = new TestSubscriber<>();
        mRepository.receipt("777", "777", "777").subscribe(testSubscriber);

        int savedCount = Realm.getDefaultInstance()
                .where(Receipt.class).equalTo("fiscalSign", "4266921602")
                .findAll()
                .size();

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        assertEquals(1, savedCount);

    }

    @Test
    public void testLoadNotAvailableReceipt() {
        TestSubscriber<Receipt> testSubscriber = new TestSubscriber<>();
        mRepository.receipt("888", "888", "888").subscribe(testSubscriber);


        testSubscriber.assertNoErrors();

    }

    @Test
    public void testSaveReceipt() {
        mRepository.save(new Receipt()).subscribe();
        int savedCount = Realm.getDefaultInstance()
                .where(Receipt.class)
                .findAll()
                .size();
        assertEquals(1, savedCount);
    }

//    @Test
//    public void testRepositoriesRestoredFromCache() throws Exception {
//        //load all repositories
//        mRepository.repositories().subscribe();
//
//        //force error for loading
//        RepositoryProvider.provideKeyValueStorage().saveToken(ERROR);
//
//        TestSubscriber<Repository> testSubscriber = new TestSubscriber<>();
//        mRepository.repositories().flatMap(Observable::from).subscribe(testSubscriber);
//
//        testSubscriber.assertNoErrors();
//        testSubscriber.assertValueCount(22);
//}

//    @Test
//    public void testLoadCommits() throws Exception {
//        TestSubscriber<Commit> testSubscriber = new TestSubscriber<>();
//        mRepository.commits("advortsov", "java_school")
//                .flatMap(Observable::from).subscribe(testSubscriber);
//        testSubscriber.assertNoErrors();
//        testSubscriber.assertValueCount(24);
//    }

//    @Test
//    public void testCommitsSaved() throws Exception {
//        mRepository.commits("advortsov", "java_school").subscribe();
//        System.out.println("CommitsSaved :::::::::::: " + Realm.getDefaultInstance()
//                .where(Commit.class)
//                .findAll());
//        int savedCount = Realm.getDefaultInstance()
//                .where(Commit.class)
//                .findAll()
//                .size();
//        assertEquals(24, savedCount);
//    }

//    @Test
//    public void testCommitsRestoredFromCache() throws Exception {
//        //load all repositories
//        mRepository.commits("advortsov", "java_school").subscribe();
//
//        //force error for loading
//        RepositoryProvider.provideKeyValueStorage().saveToken(ERROR);
//
//        TestSubscriber<Commit> testSubscriber = new TestSubscriber<>();
//        mRepository.commits("advortsov", "java_school")
//                .flatMap(Observable::from).subscribe(testSubscriber);
//
//        testSubscriber.assertNoErrors();
//        testSubscriber.assertValueCount(24);
//    }

    @After
    public void tearDown() {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.delete(Receipt.class));
        RepositoryProvider.provideKeyValueStorage().clear();
    }
}