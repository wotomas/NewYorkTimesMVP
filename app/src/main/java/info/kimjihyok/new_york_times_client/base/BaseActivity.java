package info.kimjihyok.new_york_times_client.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        //Add to customize font for UI
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
