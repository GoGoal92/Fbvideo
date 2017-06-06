package gogoal.myfb;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fenjuly.library.ArrowDownloadButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Timer;
import java.util.TimerTask;

import br.com.bemobi.medescope.Medescope;
import br.com.bemobi.medescope.callback.DownloadStatusCallback;
import gogoal.myfb.Object.Jsonparser;
import gogoal.myfb.Object.MyAlertdialog;
import gogoal.myfb.model.NotificationData;
import gogoal.myfb.retorfit.Myclient;
import gogoal.myfb.utils.Downloadlist;
import gogoal.myfb.utils.MyTimer;
import gogoal.myfb.utils.Mytoast;
import gogoal.R;
/**
 * Created by Go Goal on 5/8/2017.
 */
public class Moviedetail extends AppCompatActivity {

    ImageView iv;
    static String imagepath, movieid, dnfilename;

    static NestedScrollView nsv;
    static TextView tv, title;
    static Button btn;
    static AppCompatActivity ac;

    Typeface font;


    static FloatingActionButton fab;
    static FrameLayout ffab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mdetail);

        ac = this;

        button = (ArrowDownloadButton) findViewById(R.id.arrow_download_button);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ffab = (FrameLayout) findViewById(R.id.framfab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        iv = (ImageView) findViewById(R.id.detailiv);
        nsv = (NestedScrollView) findViewById(R.id.nsv);
        tv = (TextView) findViewById(R.id.tv);
        title = (TextView) findViewById(R.id.title);
        btn = (Button) findViewById(R.id.watchbtn);

        nsv.setVisibility(View.GONE);


        imagepath = getIntent().getExtras().getString("a");
        movieid = getIntent().getExtras().getString("b");
        dnfilename = getIntent().getExtras().getString("c");

        title.setText(Html.fromHtml("<b>" + dnfilename + "</b>"));
        font = Typeface.createFromAsset(ac.getAssets(), "zawgyi.ttf");

        title.setTypeface(font);
        tv.setTypeface(font);

        Picasso.with(this) //
                .load(imagepath)
                .into(new Target() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        iv.setBackground(drawable);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        iv.setBackgroundResource(R.drawable.error_image);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        iv.setBackgroundResource(R.drawable.loading);
                    }
                });


        Myclient.getmoviedetail(movieid);

        button = (ArrowDownloadButton) findViewById(R.id.arrow_download_button);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((count % 2) == 0) {
                    button.startAnimating();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress = progress + 1;
                                    button.setProgress(progress);
                                }
                            });
                        }
                    }, 800, 20);
                } else {
                    button.reset();
                }
                count++;
            }
        });


    }

    int count = 0;
    int progress = 0;
    static ArrowDownloadButton button;
    static Medescope mMedescope;


  /*  @Override
    protected void onResume() {
        super.onResume();

        if (showads){
            if (dialogcon){
                dialogcon=false;
                mInterstitialAd = new InterstitialAd(ac);
                mInterstitialAd.setAdUnitId(getString(R.string.Interstitial));
                if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
                    AdRequest mInterstitialAd_request = new AdRequest.Builder().build();
                    mInterstitialAd.loadAd(mInterstitialAd_request);
                    mInterstitialAd.setAdListener(new AdListener() {

                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();

                            try{
                                AlertDialog.Builder ab=new AlertDialog.Builder(ac);
                                ab.setTitle("Advertisement");
                                ab.setCancelable(false);
                                View vv=getLayoutInflater().inflate(R.layout.alert_onetext,null);
                                TextView tv= (TextView) vv.findViewById(R.id.alerttv);
                                tv.setText("မၾကာမီ ေၾကာ္ျငာေလးတတ္လာပါမည္  ။  Gold user အျဖစ္ Upgrade လုပ္၍ ေၾကာ္ျငာမ်ားကို ေဖ်ာက္ႏိုင္ပါသည္ ။ Gold user ျဖစ္လိုပါက Upgrade ခလုပ္ကို ႏိုပ္၍ Myanmar Android Store page ႏွင့္ဆက္သြယ္ပါ ။");
                                tv.setTypeface(Typeface.createFromAsset(getAssets(),"zawgyi.ttf"));
                                ab.setView(vv);
                                ab.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mInterstitialAd.show();

                                    }
                                }).setNegativeButton("Upgrade", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        mInterstitialAd.show();
                                        ac.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                                                .parse(pgurl)));
                                    }
                                }).show();
                            }catch (Exception e){

                            }



                        }
                    });



                }
            }

        }

    }*/

    public static void Feedback(String s) {

        ffab.setVisibility(View.VISIBLE);

        String moviedetail = Jsonparser.getonestring(s, "detail");
        final String movieurl = Jsonparser.getonestring(s, "url");
        nsv.setVisibility(View.VISIBLE);
        tv.setText(moviedetail);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MyTimer.getcanusebool()) {

                    ac.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                            .parse(movieurl)));
                } else {

                    MyAlertdialog.show(ac);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (MyTimer.getcanusebool()) {

                    if (running){
                        fab.setClickable(false);
                        fab.setEnabled(false);
                    }else{

                        Intent it = new Intent(ac, webviewdownloader.class);
                        it.putExtra("url", movieurl);
                        it.putExtra("cla", "md");
                        ac.startActivity(it);
                    }

                } else {

                    MyAlertdialog.show(ac);
                }





            }
        });


    }

    static boolean running = false;


    @Override
    protected void onResume() {
        super.onResume();

        running = false;

        mMedescope = Medescope.getInstance(this);
        mMedescope.setApplicationName(getString(R.string.app_name));

        mMedescope.subscribeStatus(this, dnfilename, new DownloadStatusCallback() {
            @Override
            public void onDownloadNotEnqueued(String downloadId) {

            }

            @Override
            public void onDownloadPaused(String downloadId, int reason) {

            }

            @Override
            public void onDownloadInProgress(String downloadId, final int progress) {

                if (!running) {

                    Log.e("button", "animated");
                    button.startAnimating();
                    running = true;
                }
                //button.reset();
                // button.clearAnimation();
                // button.animating();
                button.setProgress(progress);

                //  button.setProgress(progress);
            }

            @Override
            public void onDownloadOnFinishedWithError(String downloadId, int reason, String data) {

                title.setText("ACTION FINISH WITH ERROR" + reason);
                button.reset();

            }

            @Override
            public void onDownloadOnFinishedWithSuccess(String downloadId, String filePath, String data) {
                button.reset();
            }

            @Override
            public void onDownloadCancelled(String downloadId) {

            }
        });
    }


    public static void Feedback_Error() {
        Mytoast.show(ac, "Network Fail");
        ac.finish();
    }

    public static void Feedback_fbError() {

        button.reset();
        Mytoast.show(ac, "Sorry , saung Zuckerberg Deleted this video XD");
    }

    public static void Facebookfeedback(final String downurl) {
//        String downurl = Jsonparser.getonestring(s, "source");

        AlertDialog.Builder ab=new AlertDialog.Builder(ac);
        ab.setTitle("Confirmation");
        View vv=ac.getLayoutInflater().inflate(R.layout.alert_onetext,null);
        TextView tv= (TextView) vv.findViewById(R.id.alerttv);
        tv.setTypeface(Typeface.createFromAsset(ac.getAssets(),"zawgyi.ttf"));
        tv.setText("Are you sure to download this video?\n" +
                "ဤ video ကို သင္ Download ျပဳလုပ္မည္မွာေသခ်ာပါသလား  ?");
        ab.setView(vv);
        ab.setPositiveButton("Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                fab.setClickable(false);
                fab.setEnabled(false);
                button.startAnimating();
                NotificationData data = new NotificationData(dnfilename, dnfilename, "F Movie");


                Downloadlist.addlist(dnfilename);

                mMedescope.enqueue(
                        data.getId(),
                        downurl,
                        dnfilename + ".mp4",
                        dnfilename,
                        data.toJson()
                );


            }
        }).setNegativeButton("Cancel", null).show();




    }


}
