package gogoal.myfb.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import gogoal.myfb.Moviedetail;
import gogoal.myfb.Object.Constant;
import gogoal.myfb.Object.get;
import gogoal.R;
import gogoal.myfb.Ratio;
import gogoal.myfb.Series_ca_detail;

/**
 * Created by Go Goal on 5/6/2017.
 */
public class Gripadapter_ca extends BaseAdapter{

   LayoutInflater in;
    static ArrayList<get> list;
    AppCompatActivity ac;

    public Gripadapter_ca(AppCompatActivity ac1, ArrayList<get> gets) {

        ac=ac1;
        in= (LayoutInflater) ac.getSystemService(ac.LAYOUT_INFLATER_SERVICE);
        list=gets;

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

        View v=in.inflate(R.layout.movie_clipart,null);

        LinearLayout parma= (LinearLayout) v.findViewById(R.id.parmalinear);

        ViewGroup.LayoutParams roo2 = parma.getLayoutParams();
        float res2 = (float) Ratio.getmovieclipart_width();
        roo2.width = (int) res2;
        parma.setLayoutParams(roo2);

        TextView tv= (TextView) v.findViewById(R.id.moviename);
        tv.setText(Html.fromHtml(list.get(i).getMoviename()));


       final ImageView iv= (ImageView) v.findViewById(R.id.movietheme);
        Picasso.with(ac) //
                .load(Constant.host+list.get(i).getMovietype()+"/"+list.get(i).getMovieimgurl())
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



        parma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=null;
                if (list.get(i).getMovietype().equals("Movie")){
                     it=new Intent(ac,Moviedetail.class);
                }else{
                     it=new Intent(ac,Series_ca_detail.class);
                }

                it.putExtra("a",Constant.host+"/"+list.get(i).getMovietype()+"/"+list.get(i).getMovieimgurl());
                it.putExtra("b",list.get(i).getMovieid());
                it.putExtra("c",list.get(i).getMoviename());
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
