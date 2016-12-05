package info.kimjihyok.new_york_times_client.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import info.kimjihyok.new_york_times_client.post.detail.PostDetailActivity;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class NavigationHelper {
    private Activity activity;

    public NavigationHelper(Activity activity) {
        this.activity = activity;
    }

    public void goToPostDetailPage(String postUrlKey) {
        Intent postDetailPage = new Intent(activity, PostDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("postKey", postUrlKey);
        postDetailPage.putExtras(bundle);
        activity.startActivity(postDetailPage);
    }
}
