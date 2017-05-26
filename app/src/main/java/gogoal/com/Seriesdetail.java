package gogoal.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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

import java.io.File;

import br.com.bemobi.medescope.Medescope;
import br.com.bemobi.medescope.callback.DownloadStatusCallback;
import br.com.bemobi.medescope.exception.DirectoryNotMountedException;
import br.com.bemobi.medescope.exception.PathNotFoundException;
import gogoal.com.Object.Constant;
import gogoal.com.Object.Jsonparser;
import gogoal.com.Object.Pageurl;
import gogoal.com.model.NotificationData;
import gogoal.com.retorfit.Myclient;
import gogoal.com.utils.DownloadService;
import gogoal.com.utils.Downloadlist;
import gogoal.com.utils.MyResultReceiver;
import gogoal.com.utils.MyTimer;
import gogoal.com.utils.Mytoast;

/**
 * Created by Go Goal on 5/8/2017.
 */
public class Seriesdetail extends AppCompatActivity  {

    String mid;
    static String dnfilename;

    static ImageView iv;
    static NestedScrollView nsv;
    static TextView tv, title;
    static Button btn;
    static AppCompatActivity ac;

    Typeface font;

    static String pgurl = Pageurl.geturl();
    static boolean showads = Pageurl.getbool();

    static FloatingActionButton fab;
    static FrameLayout ffab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mdetail);

        ac = this;
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

        font = Typeface.createFromAsset(ac.getAssets(), "zawgyi.ttf");
        tv.setTypeface(font);

        mid = getIntent().getExtras().getString("a");
        dnfilename = getIntent().getExtras().getString("filename");


        Log.e("downfilenmae",dnfilename);

        Myclient.getserisemaininfo(mid);




        button = (ArrowDownloadButton) findViewById(R.id.arrow_download_button);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ffab= (FrameLayout) findViewById(R.id.framfab);



        DownloadService.setreciever(mReceiver);


    }


    public static MyResultReceiver mReceiver;
    static ArrowDownloadButton button;
    static boolean dialogcon;


    static Medescope mMedescope;


    @Override
    protected void onPause() {
        super.onPause();
        Medescope.getInstance(this).unsubscribeStatus(this);
    }



    static boolean running=false;


    @Override
    protected void onResume() {
        super.onResume();

        running=false;

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

                if (!running){

                    Log.e("button","animated");
                    button.startAnimating();
                    running=true;
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


    public static void Feedback(String s) {


        ffab.setVisibility(View.VISIBLE);

        String moviedetail = Jsonparser.getonestring(s, "detail");
        final String movieurl = Jsonparser.getonestring(s, "url");
        final String moviecover = Jsonparser.getonestring(s, "cover");
        String titlemovie = Jsonparser.getonestring(s, "title");

        nsv.setVisibility(View.VISIBLE);
        tv.setText(moviedetail);
        title.setText(Html.fromHtml("<b>" + titlemovie + "</b>"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (MyTimer.getcanusebool()){

                    ac.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                            .parse(movieurl)));
                }else{

                    Mytoast.show(ac,"Your time is expired now.");
                }



            }
        });

        Picasso.with(ac) //
                .load(Constant.host + "scover/" + moviecover)

                .into(new Target() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Drawable drawable = new BitmapDrawable(ac.getResources(), bitmap);
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
                        it.putExtra("cla", "sd");
                        ac.startActivity(it);
                    }

                } else {

                    Mytoast.show(ac, "Your time is expired now.");
                }



            }
        });


    }

    public static void Facebookfeedback(final String downurl) {


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

    public static void Feedback_fbError() {
        button.reset();
        Mytoast.show(ac, "Sorry , saung Zuckerberg Deleted this video XD");
    }



    public static void Feedback_Error() {
        Mytoast.show(ac, "Network Fail");
        ac.finish();
    }
}
