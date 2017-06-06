package gogoal.myfb.Object;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import gogoal.R;
import gogoal.myfb.Help;
import gogoal.myfb.model.NotificationData;
import gogoal.myfb.utils.Downloadlist;

/**
 * Created by Go Goal on 6/6/2017.
 */

public class MyAlertdialog {

    public static void show(final AppCompatActivity ac) {

        AlertDialog.Builder ab=new AlertDialog.Builder(ac);
        ab.setTitle("Need Time");
        View vv=ac.getLayoutInflater().inflate(R.layout.alert_onetext,null);
        TextView tv= (TextView) vv.findViewById(R.id.alerttv);
        tv.setTypeface(Typeface.createFromAsset(ac.getAssets(),"zawgyi.ttf"));
        tv.setText("သင့္၏ သံုးစြဲခြင့္ သက္တမ္းမွာ ကုန္ဆံုးသြားပါျပီ ။\n" +
                "\n" +
                "ထို႔ေၾကာင့္ သက္တမ္းတိုးရန္လုိအပ္ေနပါသည္ ။\n" +
                "\n" +
                "သက္တမ္းတိုးရန္ Upgrade ခလုတ္ကို ႏိုပ္ပါ ။\n" +
                "\n" +
                "Fmovie သည္ အခမဲ့ App ျဖစ္သည္။");
        ab.setView(vv);
        ab.setPositiveButton("Upgrade", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (appInstalledOrNot("gg.googlrewardads",ac)){
                    Intent LaunchIntent = ac.getPackageManager()
                            .getLaunchIntentForPackage("gg.googlrewardads");
                    ac.startActivity(LaunchIntent);
                }else {
                    Intent it = new Intent(ac, Help.class);
                    ac.startActivity(it);
                }



            }
        }).show();


    }

    private static boolean appInstalledOrNot(String uri,Activity ac) {
        PackageManager pm = ac.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
