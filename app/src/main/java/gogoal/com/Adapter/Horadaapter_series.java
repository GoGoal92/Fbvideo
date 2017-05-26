package gogoal.com.Adapter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gogoal.com.R;

/**
 * Created by Go Goal on 5/8/2017.
 */
public class Horadaapter_series extends BaseAdapter{

    LayoutInflater in;
    static int count,pos;
    AppCompatActivity ac;

    public Horadaapter_series(AppCompatActivity ac1, int count1,int position) {

        ac=ac1;
        in= (LayoutInflater) ac.getSystemService(ac.LAYOUT_INFLATER_SERVICE);
        count=count1;
        pos=position;

    }

    @Override
    public int getCount() {
        return count;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v=in.inflate(R.layout.room_row,null);

        int gg=i+1;

        TextView tv= (TextView) v.findViewById(R.id.vv);
        RelativeLayout rl= (RelativeLayout) v.findViewById(R.id.rmm);
        if (pos==i){
            rl.setBackgroundResource(R.drawable.room_boder2);
            tv.setTextColor(Color.WHITE);
        }else{
            tv.setTextColor(Color.BLACK);
            rl.setBackgroundResource(R.drawable.room_boder);
        }
        tv.setText(gg+"");

        return v;
    }

    public void refeshcoun(int count1) {
        count=count1;
        notifyDataSetChanged();
    }

    public void refeshpos(int userpo) {
        pos=userpo;
        notifyDataSetChanged();
    }
}
