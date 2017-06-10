package gogoal.com.Adapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import gogoal.com.Categorychoice;
import gogoal.com.Object.get;
import gogoal.R;

/**
 * Created by Go Goal on 5/6/2017.
 */
public class Nlvadapter extends BaseAdapter{

   LayoutInflater in;
    static ArrayList<get> list;
    AppCompatActivity ac;
    Typeface font;

    public Nlvadapter(AppCompatActivity ac1, ArrayList<get> gets) {

        ac=ac1;
        in= (LayoutInflater) ac.getSystemService(ac.LAYOUT_INFLATER_SERVICE);
        list=gets;
        font=Typeface.createFromAsset(ac.getAssets(),"zawgyi.ttf");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v=in.inflate(R.layout.nlv_row,null);
        LinearLayout click= (LinearLayout) v.findViewById(R.id.clicklayout);
        TextView tv= (TextView) v.findViewById(R.id.ca_row);
        tv.setText(Html.fromHtml(list.get(i).getCaname()));
        tv.setTypeface(font);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(ac,Categorychoice.class);
                it.putExtra("a",list.get(i).getCaid());
                it.putExtra("b",list.get(i).getCaname());
                ac.startActivity(it);
            }
        });

        return v;
    }

    public void refresh(ArrayList list1) {
        list=list1;
        notifyDataSetChanged();
    }
}
