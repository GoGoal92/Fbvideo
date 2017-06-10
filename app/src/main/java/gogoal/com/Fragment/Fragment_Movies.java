package gogoal.com.Fragment;

/**
 * Created by Go Goal on 11/25/2016.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gogoal.com.Adapter.Main_recycler_adapter;
import gogoal.com.MainActivity;
import gogoal.com.Object.Constant;
import gogoal.com.Object.Jsonparser;
import gogoal.com.Object.get;
import gogoal.com.retorfit.Myclient;
import gogoal.R;


/**
 * Created by Go Goal on 11/20/2016.
 */

public class Fragment_Movies extends Fragment {


    public static Fragment_Movies newInstance() {

        Bundle args = new Bundle();

        Fragment_Movies fragment = new Fragment_Movies();
        fragment.setArguments(args);
        return fragment;
    }


    static Main_recycler_adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=new ArrayList<get>();
        adapter = new Main_recycler_adapter(MainActivity.getactivity(), list);


    }



    static  int count=1;
    static ArrayList<get> list;
    public static SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView rv;
    LinearLayoutManager llm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recyclerview, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rv);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh_layout);

        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(MainActivity.getactivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);





        mSwipeRefreshLayout.setColorSchemeColors(Color.GREEN, Color.RED, Color.BLUE, Color.CYAN);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Myclient.getmovielist_refresh(Constant.apikey,"1");
            }
        });


        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int ydy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = llm.getChildCount();
                int totalItemCount = llm.getItemCount();
                int pastVisibleItems = llm.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    count++;
                    adapter.load_pg(true);
                    Myclient.getmovielist(Constant.apikey,count + "");
                }


            }
        });

         return v;
    }


    public static void Feedback(String s) {

        adapter.load_pg(false);
        mSwipeRefreshLayout.setRefreshing(false);


        String status= Jsonparser.getonestring(s,"status");
        if (status.equals("end")){

        }else{
            list=new ArrayList<get>();
            list=Jsonparser.addMovielist(s,list);
            adapter.refresh(list);
        }



    }

    public static void Feedback_Error() {
        adapter.load_pg(false);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public static void Feedback_refresh(String s) {
        adapter.load_pg(false);
        count=1;
        list=new ArrayList<>();
        mSwipeRefreshLayout.setRefreshing(false);
        list=Jsonparser.addMovielist(s,list);
        adapter.refresh(list);
    }

    public static void Feedback_main(String s) {
        list=new ArrayList<get>();
        list=Jsonparser.addMovielist(s,list);
        adapter.refresh(list);
    }
}
