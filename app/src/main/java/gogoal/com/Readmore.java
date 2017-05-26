package gogoal.com;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import gogoal.com.Object.Jsonparser;
import gogoal.com.retorfit.Myclient;
import gogoal.com.utils.Mytoast;

/**
 * Created by Go Goal on 5/9/2017.
 */
public class Readmore extends AppCompatActivity{

    static AppCompatActivity ac;
    static TextView tv;
    Typeface font;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readmore);

        ac = this;
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_icn_close);

        font=Typeface.createFromAsset(ac.getAssets(),"zawgyi.ttf");

        tv= (TextView) findViewById(R.id.vvb);
        String a=getIntent().getExtras().getString("a");
        Myclient.getreadmore(a);

        tv.setTypeface(font);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                ac.overridePendingTransition(0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public static void Feedback(String s) {

        String detail= Jsonparser.getonestring(s,"detail");
        tv.setText(detail);

    }

    public static void Feedback_Error() {
        Mytoast.show(ac, "Network Fail");
        ac.finish();
        ac.overridePendingTransition(0, 0);

    }
}
