package gogoal.com;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Go Goal on 5/26/2017.
 */

public class FakeDialog extends AppCompatActivity{

    String url,type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fakelayout);

        url=getIntent().getExtras().getString("url");
        type=getIntent().getExtras().getString("type");

        if (type.equals("md")){

            Moviedetail.Facebookfeedback(url);
            webviewdownloader.finishactivity();
            finish();

        }else if (type.equals("sd")){

            Seriesdetail.Facebookfeedback(url);
            webviewdownloader.finishactivity();
            finish();

        }



    }
}
