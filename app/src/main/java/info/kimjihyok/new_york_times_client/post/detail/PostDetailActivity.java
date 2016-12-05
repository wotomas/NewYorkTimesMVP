package info.kimjihyok.new_york_times_client.post.detail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.R;

public class PostDetailActivity extends AppCompatActivity implements PostDetailPresenter.View {
    private static final String TAG = "PostDetailActivity";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private TextView mTitleTextView;
    private TextView mBodyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("New York Times");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        bindView();
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
        mBodyTextView = (TextView) findViewById(R.id.post_detail_body);
    }

    @Override
    public void setTitle(String text) {
        mTitleTextView.setText(text);
    }

    @Override
    public void setMainImage(String url) {

    }

    @Override
    public void setBodyText(String text) {
        mBodyTextView.setText(text);
    }
}
