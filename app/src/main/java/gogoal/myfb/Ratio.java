package gogoal.myfb;

import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Created by Go Goal on 5/6/2017.
 */

public class Ratio {


    public  static int screenWidth,screenheight;

    public Ratio(AppCompatActivity ac) {
        DisplayMetrics metrics = new DisplayMetrics();
        ac.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenheight=metrics.heightPixels;
    }

    public static float getmovieclipart_width() {

        float abc=(screenWidth/2)-50;


        return abc;
    }
}
