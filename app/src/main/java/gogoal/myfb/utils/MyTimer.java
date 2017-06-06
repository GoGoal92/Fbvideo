package gogoal.myfb.utils;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Go Goal on 5/25/2017.
 */

public class MyTimer {

    static CountDownTimer cdt;
    static boolean canuse;

    public static void start(Long remai, AppCompatActivity ac) {


        try{
            cdt.cancel();
        }catch (Exception e){
            e.printStackTrace();
        }


        cdt = new CountDownTimer(remai, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                canuse=true;
            }

            @Override
            public void onFinish() {

                canuse=false;

            }
        }.start();
    }

    public static void stoptime() {
        cdt.cancel();
    }

    public static boolean getcanusebool() {
        return canuse;
    }

    public static void cantuse() {
        canuse=false;
    }
}
