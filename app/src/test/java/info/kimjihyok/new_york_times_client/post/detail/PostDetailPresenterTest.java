package info.kimjihyok.new_york_times_client.post.detail;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import info.kimjihyok.new_york_times_client.db.PostItem;
import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by jkimab on 2016. 12. 5..
 */
public class PostDetailPresenterTest {
    private PostDetailPresenter mPresenter;
    @Mock private PostDetailPresenter.View mView;
    @Mock private DataController mDataController;

    private Observable<PostItem> mSinglePostObservable;

    private static final String URL = "http://mockURl.com";
    private static final String TITLE = "TITLE_TEXT";
    private static final String SECTION = "SECTION_TEXT";
    private static final String BYLINE = "BYLINE_TEXT";
    private static final String CREATEDDATE = "CREATE_DATE_TEXT";


    @Before
    public void setUp() throws Exception {
        mView = mock(PostDetailPresenter.View.class);
        mDataController = mock(DataController.class);
    }

//    @Test
//    public void givenValidPostItemIsReturned_whenViewIsAttached_shouldCorrectlySetViewsWithFollowingTexts() throws Exception {
//        //when valid post item is returned and the view is attached, presenter should correctly call the views to set information to valid data
//        mSinglePostObservable = Observable.just(getValidPostItem());
//        when(mDataController.getSinglePostItem(URL)).thenReturn(mSinglePostObservable);
//
//        mPresenter = new PostDetailPresenter(mDataController);
//        mPresenter.setPostUrlKey(URL);
//        mPresenter.attachView(mView);
//
//        verify(mView, atLeastOnce()).setTitle(TITLE);
//        verify(mView, atLeastOnce()).setSectionText(SECTION);
//        verify(mView, atLeastOnce()).setCreatedDate(CREATEDDATE);
//        verify(mView, atLeastOnce()).setAuthor(BYLINE);
//    }
//
//    @Test
//    public void givenNullPostItemIsReturned_whenViewIsAttached_shouldNotPassToViewButShowNothing() throws Exception {
//        when(mDataController.getSinglePostItem(URL)).thenReturn(Observable.just(null));
//
//        mPresenter = new PostDetailPresenter(mDataController);
//        mPresenter.setPostUrlKey(URL);
//        mPresenter.attachView(mView);
//
//        verify(mView, never()).setTitle(TITLE);
//        verify(mView, never()).setSectionText(SECTION);
//        verify(mView, never()).setCreatedDate(CREATEDDATE);
//        verify(mView, never()).setAuthor(BYLINE);
//    }

    @After
    public void tearDown() throws Exception {
        mPresenter.detachView();
    }

    public PostItem getValidPostItem() {
        PostItem newPostItem = new PostItem();
        newPostItem.setTitle(TITLE);
        newPostItem.setSection(SECTION);
        newPostItem.setByline(BYLINE);
        newPostItem.setCreated_date(CREATEDDATE);

        Multimedia newMedia = new Multimedia();
        newMedia.setUrl(URL);
        List<Multimedia> list = new ArrayList<>();
        list.add(newMedia);

        newPostItem.setMultimedia(list);

        return newPostItem;
    }
}