package gogoal.com.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
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

import gogoal.com.Object.Constant;
import gogoal.com.Object.get;
import gogoal.com.Seriesdetail;
import gogoal.R;
import gogoal.com.Ratio;

/**
 * Created by Go Goal on 5/6/2017.
 */
public class Gripadapterseries extends BaseAdapter{

   LayoutInflater in;
    static ArrayList<get> list;
    AppCompatActivity ac;
    Typeface font;

    public Gripadapterseries(AppCompatActivity ac1, ArrayList<get> gets) {

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

        View v=in.inflate(R.layout.movie_clipart,null);
        LinearLayout parma= (LinearLayout) v.findViewById(R.id.parmalinear);

        ViewGroup.LayoutParams roo2 = parma.getLayoutParams();
        float res2 = (float) Ratio.getmovieclipart_width();
        roo2.width = (int) res2;
        parma.setLayoutParams(roo2);

        TextView tv= (TextView) v.findViewById(R.id.moviename);
        tv.setText(Html.fromHtml(list.get(i).getMoviename()+"( "+list.get(i).getMoviepisode()+" )"));
        tv.setTypeface(font);


        final ImageView iv= (ImageView) v.findViewById(R.id.movietheme);


        Picasso.with(ac) //
                .load(Constant.host+"series/"+list.get(i).getMovieid()+".PNG")
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
                Intent it=new Intent(ac,Seriesdetail.class);
                it.putExtra("a",list.get(i).getMovieid());
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
