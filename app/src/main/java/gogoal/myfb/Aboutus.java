package gogoal.myfb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import gogoal.R;

/**
 * Created by Go Goal on 5/9/2017.
 */
public class Aboutus extends AppCompatActivity {

    static AppCompatActivity ac;
    static TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readmore);

        ac = this;
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_icn_close);

        tv = (TextView) findViewById(R.id.vvb);
       String str="FB Movie ( v 1.1 )\n" +
               "\n" +
               "Developer\n" +
               "San Go Go (Go Goal)\n" +
               "\n" +
               "If you wanna create your own mobile app cheapest and  fastest, You can contact me \n" +
               "\n" +
               "Ph - +959784714282\n" +
               "sangogo92@gmail.com";

        tv.setText(str);


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
}