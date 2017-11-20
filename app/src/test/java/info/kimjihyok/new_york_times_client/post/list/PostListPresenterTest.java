package info.kimjihyok.new_york_times_client.post.list;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.PostItem;
import info.kimjihyok.new_york_times_client.post.BasePresenterTest;
import info.kimjihyok.new_york_times_client.util.DebugUtil;
import io.reactivex.Maybe;
import io.reactivex.Observable;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jkimab on 2016. 12. 5..
 */
public class PostListPresenterTest extends BasePresenterTest {
    private PostListPresenter mPresenter;
    private List<PostItem> mValidDummyData;

    @Mock private PostListPresenter.View mView;
    @Mock private DataController mDataController;

    private Observable<List<PostItem>> mPostListObservable;



    @Before
    public void setUp() throws Exception {
        mView = mock(PostListPresenter.View.class);
        mDataController = mock(DataController.class);
        mValidDummyData = DebugUtil.getDummyData();
    }

    @Test
    public void givenCombinedPostsReturnsNoList_whenViewIsAttached_shouldNotPassToAdapter() throws Exception {
        //Test when view is attached and data controller emits valid data, onSubscribe should be called from view, which is attached to the adapter
        when(mDataController.getCombinedPosts()).thenReturn(Observable.empty());

        mPresenter = new PostListPresenter(mDataController);
        mPresenter.attachView(mView);

        verify(mView, never()).onSubscribe(anyList());
    }

    @Test
    public void givenCombinedPostsReturnsEmptyList_whenViewIsAttached_shouldPassItToAdapterWithoutError() throws Exception {
        //Test when view is attached and data controller emits valid data, onSubscribe should be called from view, which is attached to the adapter
        mPostListObservable = Observable.just(getEmptyPostItems());
        when(mDataController.getCombinedPosts()).thenReturn(mPostListObservable);

        mPresenter = new PostListPresenter(mDataController);
        mPresenter.attachView(mView);

        verify(mView, atLeastOnce()).onSubscribe(getEmptyPostItems());
    }

    @Test
    public void givenCombinedPostsReturnsValidList_whenViewIsAttached_shouldPassItToAdapterWithoutError() throws Exception {
        //Test when view is attached and data controller emits valid data, onSubscribe should be called from view, which is attached to the adapter
        mPostListObservable = Observable.just(getValidPostItems());
        when(mDataController.getCombinedPosts()).thenReturn(mPostListObservable);

        mPresenter = new PostListPresenter(mDataController);
        mPresenter.attachView(mView);

        verify(mView, atLeastOnce()).onSubscribe(getValidPostItems());
    }

    @After
    public void tearDown() throws Exception {
        mPresenter.detachView();
    }

    public List<PostItem> getValidPostItems() {
        return mValidDummyData;
    }

    public List<PostItem> getNullPostItems() {
        return null;
    }

    public List<PostItem> getEmptyPostItems() {
        return Collections.emptyList();
    }
}