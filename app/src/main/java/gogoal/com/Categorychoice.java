package gogoal.com;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import gogoal.R;
import gogoal.com.Adapter.Cate_recycler_adapter;
import gogoal.com.Object.Jsonparser;
import gogoal.com.Object.get;
import gogoal.com.retorfit.Myclient;
import gogoal.com.utils.HorizontalListView;
import gogoal.com.Adapter.Horadaapter;
import gogoal.com.utils.Mytoast;

/**
 * Created by Go Goal on 5/8/2017.
 */
public class Categorychoice extends AppCompatActivity {

    HorizontalListView hlv;
    static ArrayList<get> list;
    static AppCompatActivity ac;
    static int count = 0;
    String caid;
    static Horadaapter adapter;
    static ProgressBar pg;

    static Cate_recycler_adapter gadapter;


    public static SwipeRefreshLayout mSwipeRefreshLayout;
    static RecyclerView rv;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorydetail);

        getSupportActionBar().setTitle(getIntent().getExtras().getString("b"));
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ac = this;
        list = new ArrayList<>();

        rv = (RecyclerView) findViewById(R.id.rv);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        gadapter = new Cate_recycler_adapter(ac, list);

        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(MainActivity.getactivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.setAdapter(gadapter);


        pg = (ProgressBar) findViewById(R.id.ppgg);

        caid = getIntent().getExtras().getString("a");
        list = new ArrayList<get>();

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
                Myclient.getcategory_choice(caid, k + "");
            }
        });

        Myclient.getcategory_choice(caid, "1");


        hlv = (HorizontalListView) findViewById(R.id.hlv);
        adapter = new Horadaapter(ac, count, userpo);
        hlv.setAdapter(adapter);

        userpo = 1;


        hlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int item, long l) {

                int cc=item + 1;
                if (userpo !=cc){
                    userpo = item + 1;
                    Myclient.getcategory_choice(caid, userpo + "");
                    adapter.refeshpos(userpo - 1);
                    rv.setVisibility(View.GONE);
                    pg.setVisibility(View.VISIBLE);
                }





            }
        });


    }

    static int userpo = 0;


    public static void Feedback_Error() {
        Mytoast.show(ac, "Network Fail");
        ac.finish();

    }

    public static void Feedback(String s) {

        rv.setVisibility(View.VISIBLE);

        mSwipeRefreshLayout.setRefreshing(false);
        pg.setVisibility(View.GONE);
        list = new ArrayList<>();
        list = Jsonparser.getMovielist_ca(s);
        gadapter.refresh(list);

        String kk = Jsonparser.getonestring(s, "count");
        int hh = Integer.parseInt(kk);
        count = hh / 10;
        int remain = hh % 10;
        if (remain > 0) {
            count++;
        }

        adapter.refeshcoun(count);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
