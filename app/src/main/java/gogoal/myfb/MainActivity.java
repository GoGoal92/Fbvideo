package gogoal.myfb;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import gogoal.R;
import gogoal.myfb.Adapter.Fragmentadapter_Main;
import gogoal.myfb.Adapter.Nlvadapter;
import gogoal.myfb.Fragment.Fragment_Movies;
import gogoal.myfb.Fragment.Fragment_Series;
import gogoal.myfb.Object.Constant;
import gogoal.myfb.Object.Jsonparser;
import gogoal.myfb.Object.Pageurl;
import gogoal.myfb.Object.get;
import gogoal.myfb.retorfit.Myclient;
import gogoal.myfb.utils.Downloadlist;
import gogoal.myfb.utils.MyTimer;
import gogoal.myfb.utils.NestedListView;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    static AppCompatActivity ac;
    NestedListView nlv;
    static Nlvadapter adapter;
    Fragmentadapter_Main vpadapter;
    LinearLayout aboutus, rateme, dm, remaintime;
    static RelativeLayout splash;
    static TextView loading, myid, accounttype, remaingtime;

    static String fbpageurl;


    public static AppCompatActivity getactivity() {
        return ac;
    }

    String[] per = new String[]{android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private void Requestpermission() {
        if (EasyPermissions.hasPermissions(this, per)) {

        } else {
            EasyPermissions.requestPermissions(this, "All Permission Must Grant", 200, per);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, per, grantResults, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Requestpermission();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ac = this;
        Downloadlist.setcontext(ac);

        Ratio ra = new Ratio(ac);
        nlv = (NestedListView) findViewById(R.id.nlv);
        adapter = new Nlvadapter(ac, new ArrayList<get>());
        nlv.setAdapter(adapter);
        nlv.setDividerHeight(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("Latest Movie", "Latest Series");

        vpadapter = new Fragmentadapter_Main(getSupportFragmentManager());
        ViewPager vp = (ViewPager) findViewById(R.id.vpager);
        vp.setAdapter(vpadapter);
        navigationTabStrip.setViewPager(vp);
        vp.setOffscreenPageLimit(2);

        aboutus = (LinearLayout) findViewById(R.id.aboutus);
        rateme = (LinearLayout) findViewById(R.id.rateus);
        dm = (LinearLayout) findViewById(R.id.download);
        remaintime = (LinearLayout) findViewById(R.id.myclickuser);



        rateme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                        .parse(Constant.appplaystroe));
                startActivity(intent);


            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Aboutus.class);
                startActivity(it);
                overridePendingTransition(0, 0);
            }
        });

        dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Mydownloadmanager.class);
                startActivity(it);
                overridePendingTransition(0, 0);
            }
        });


        remaintime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Help.class);
                startActivity(it);

            }
        });

        splash = (RelativeLayout) findViewById(R.id.spl);
        loading = (TextView) findViewById(R.id.loading);

        myid = (TextView) findViewById(R.id.myid);
        accounttype = (TextView) findViewById(R.id.acctype);
        remaingtime = (TextView) findViewById(R.id.remaintime);

        String did = Settings.Secure.getString(ac.getContentResolver(), Settings.Secure.ANDROID_ID);
        String dmodel = Build.MODEL;

        userid = did + dmodel;
        Myclient.Registercheck(userid);

        loading.setText("Checking User");

    }

    static String userid;


    @Override
    protected void onResume() {
        super.onResume();

        Myclient.Registercheck(userid);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            Mytoast.show(ac,"Coming soon");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    public static void Feedback(String s) {

        loading.setText("Get Latest Movies");
        Myclient.getmovielist_main(Constant.apikey, "1");

        ArrayList list = new ArrayList<get>();
        list = Jsonparser.getobjectlist(s);
        adapter.refresh(list);


    }

    public static void Feedback_Error() {

        AlertDialog.Builder ab = new AlertDialog.Builder(ac);
        ab.setTitle("Network Fail");
        ab.setMessage("Unstable Connection Please Check Your Network");
        ab.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Myclient.Registercheck(userid);

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ac.finish();
            }
        });

        ab.setCancelable(false);
        ab.show();

    }

    public static void Feedback_Reg(String s) {


        String status = Jsonparser.getonestring(s, "status");

        Log.e("status", status);

        if (status.equals("0")) {

            String msg = Jsonparser.getonestring(s, "msg");
            AlertDialog.Builder ab = new AlertDialog.Builder(ac);
            ab.setTitle("Need Update");
            ab.setMessage(msg);
            ab.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                            .parse("https://play.google.com/store/apps/details?id=gogoal.fbvideo.com"));
                    ac.startActivity(intent);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ac.finish();
                }
            });

            ab.setCancelable(false);
            ab.show();

        } else {

            myid.setText(Jsonparser.getonestring(s, "userid"));
            fbpageurl = Jsonparser.getonestring(s, "pageurl");

            if (status.equals("1")) {
                accounttype.setText("Gold User");
                String remai = Jsonparser.getonestring(s, "remaintime");
                Long rem = Long.parseLong(remai);
                MyTimer.start(rem, ac);

                remaingtime.setText(ConvertMilliSecondsToFormattedDate(rem));
                fbpageurl = "0";

            }

            if (status.equals("2")) {

                remaingtime.setText("00:00");
                MyTimer.cantuse();
            }


            Pageurl.sentrealurl(fbpageurl);

            loading.setText("Refresh Categories");
            Myclient.getcategory(Constant.apikey);
        }


    }

    public static void Feedback_moveieslist(String s) {
        loading.setText("Get Latest Series");
        Fragment_Movies.Feedback_main(s);
        Myclient.getserieslist_Main(Constant.apikey, 1 + "");
    }

    public static void Feedback_seriesliist(String s) {
        Fragment_Series.Feedback(s);
        splash.setVisibility(View.GONE);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == 4) {
            MainActivity.ac.moveTaskToBack(true);
        }
        return true;
    }


    public static String ConvertMilliSecondsToFormattedDate(long timeInMilliSeconds) {
        long seconds = timeInMilliSeconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + " day " + hours % 24 + " hr " + minutes % 60 + "min";
        return time;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyTimer.stoptime();
    }
}
