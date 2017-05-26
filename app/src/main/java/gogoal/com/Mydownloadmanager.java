package gogoal.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import gogoal.com.Adapter.Downloadadapter;
import gogoal.com.utils.Downloadlist;

/**
 * Created by Go Goal on 5/24/2017.
 */

public class Mydownloadmanager extends AppCompatActivity{

    ArrayList<String> list;

    static AppCompatActivity ac;

    RecyclerView rv;
    LinearLayoutManager llm;
    Downloadadapter adpater;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dmrecycler);

        ac=this;

        getSupportActionBar().setTitle("Download Manager");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_icn_close);

        list=new ArrayList<>();
        list= Downloadlist.getlist();

        rv= (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(MainActivity.getactivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        adpater=new Downloadadapter(ac,list);
        rv.setAdapter(adpater);


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                ac.overridePendingTransition(0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
