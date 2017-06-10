package gogoal.com;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import gogoal.com.Adapter.Nlvadapter_series;
import gogoal.com.Object.Jsonparser;
import gogoal.com.Object.Pageurl;
import gogoal.com.Object.get;
import gogoal.com.retorfit.Myclient;
import gogoal.com.utils.HorizontalListView;
import gogoal.com.utils.Mytoast;
import gogoal.com.utils.NestedListView;
import gogoal.com.Adapter.Horadaapter_series;
import gogoal.R;
/**
 * Created by Go Goal on 5/9/2017.
 */
public class Series_ca_detail extends AppCompatActivity /*implements View.OnClickListener*/ {


    ImageView iv;
    String imagepath, movieid, moviename;

    HorizontalListView hlv;
    static ArrayList<get> list;
    static AppCompatActivity ac;
    static int count = 0;
    static Horadaapter_series adapter;
    static Nlvadapter_series nvadapter;
    static ProgressBar pg;



    //static LinearLayout[] lay = new LinearLayout[10];
    //static TextView[] tv = new TextView[10];
    //static ImageView[] img = new ImageView[10];

    TextView readmore, title;
    public static SwipeRefreshLayout mSwipeRefreshLayout;

    static String pgurl= Pageurl.geturl();
    static boolean showads=Pageurl.getbool();


    static NestedListView nlv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.series_detail_ca);


        ac = this;
        pg = (ProgressBar) findViewById(R.id.ppgg);
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

        imagepath = getIntent().getExtras().getString("a");
        movieid = getIntent().getExtras().getString("b");
        moviename = getIntent().getExtras().getString("c");

        iv = (ImageView) findViewById(R.id.detailiv);
        readmore = (TextView) findViewById(R.id.tv);
        title = (TextView) findViewById(R.id.title);

        nlv= (NestedListView) findViewById(R.id.nlv);

        list = new ArrayList<>();
        nvadapter=new Nlvadapter_series(ac,list,moviename);
        nlv.setAdapter(nvadapter);


        Picasso.with(this) //
                .load(imagepath)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error_image)
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


        title.setText(Html.fromHtml("<b>" + moviename + "</b>"));
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(getApplicationContext(),Readmore.class);
                it.putExtra("a",movieid);
                startActivity(it);
                overridePendingTransition(0, 0);

            }
        });

        userpo = 0;

        hlv = (HorizontalListView) findViewById(R.id.hlv);
        adapter = new Horadaapter_series(ac, count, userpo);
        hlv.setAdapter(adapter);

        bool = true;

        Myclient.getcategory_series(movieid, "1");

        hlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int item, long l) {

                int cc=item + 1;
                if (userpo !=cc){
                    userpo = item + 1;
                    Myclient.getcategory_series(movieid, userpo + "");
                    adapter.refeshpos(userpo - 1);
                    nlv.setVisibility(View.GONE);
                    pg.setVisibility(View.VISIBLE);
                }



            }
        });


        dialogcon=true;


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.GREEN, Color.RED, Color.BLUE, Color.CYAN);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int k = 0;
                if (userpo == 0) {
                    k = 1;
                } else {
                    k = userpo;
                }
                Myclient.getcategory_series(movieid, k + "");
            }
        });


    }

    static  boolean dialogcon;



    public static void Feedback_Error() {
        Mytoast.show(ac, "Network Fail");
        pg.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    static int ifeed;
    static int userpo = 0;
    static boolean bool = true;

    public static void Feedback(String s) {

        Log.e("s",s);

        nlv.setVisibility(View.VISIBLE);
        pg.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);

        list = new ArrayList<>();
        list = Jsonparser.getserieslistrow(s);

        nvadapter.refresh(list);






        if (bool) {
            String kk = Jsonparser.getonestring(s, "count");
            int hh = Integer.parseInt(kk);
            count = hh / 10;
            int remain = hh % 10;
            if (remain > 0) {
                count++;
            }

            adapter.refeshcoun(count);
            bool = false;
        }



    }

 /*   @Override
    public void onClick(View view) {

        for (int k = 0; k < lay.length; k++) {

            if (view == lay[k]) {


                dialogcon=true;
                ac.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse(list.get(k).getMovieimgurl())));






            }

        }

    }*/
}
