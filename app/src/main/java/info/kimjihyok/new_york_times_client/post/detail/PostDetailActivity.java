package info.kimjihyok.new_york_times_client.post.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.base.activity.BaseActivity;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import info.kimjihyok.new_york_times_client.util.ScreenUtil;

public class PostDetailActivity extends BaseActivity implements PostDetailPresenter.View {
    private static final String TAG = "PostDetailActivity";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private TextView mTitleTextView;
    private TextView mCreatedDateTextView;
    private TextView mPostSectionTextView;
    private TextView mPostAuthorTextView;
    private ImageView mPostDetailImage;
    private String mPostUrlKey;

    @Inject DataController mDataController;
    @Inject PostDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        bindView();

        BaseActivity.getActivityComponent().inject(this);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("New York Times");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if(intent != null) mPresenter.setPostUrlKey(intent.getStringExtra("postKey"));
    }

    @Override
    protected void onScreenChangeToLandscape() {
        //Do nothing in post detail activity
    }

    @Override
    protected void onScreenChangeToPortrait() {
        //Do nothing in post detail activity
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void bindView() {
        mTitleTextView = (TextView) findViewById(R.id.post_detail_title);
        mCreatedDateTextView = (TextView) findViewById(R.id.post_detail_created_date);
        mPostSectionTextView = (TextView) findViewById(R.id.post_detail_section);
        mPostAuthorTextView = (TextView) findViewById(R.id.post_detail_author);
        mPostDetailImage = (ImageView) findViewById(R.id.post_detail_image);
    }

    @Override
    public void setTitle(String text) {
        mTitleTextView.setText(text);
    }

    @Override
    public void setMainImage(Multimedia media) {
        if(media == null) {
            mPostDetailImage.setVisibility(View.GONE);
        } else {
            int screenWidth = ScreenUtil.getScreenWidth((Activity) this);
            Picasso.with(this).load(media.getUrl())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.load_error_image)
                    .resize(screenWidth, screenWidth).centerInside()
                    .into(mPostDetailImage);
        }
    }

    @Override
    public void setSectionText(String text) {
        mPostSectionTextView.setText(text);
    }

    @Override
    public void setAuthor(String author) {
        mPostAuthorTextView.setText(author);
    }

    @Override
    public void setCreatedDate(String date) {
        mCreatedDateTextView.setText(date);
    }
}
