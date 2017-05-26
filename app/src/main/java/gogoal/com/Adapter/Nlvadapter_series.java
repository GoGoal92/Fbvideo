package gogoal.com.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import com.andexert.library.RippleView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import gogoal.com.Categorychoice;
import gogoal.com.Moviedetail;
import gogoal.com.Object.Constant;
import gogoal.com.Object.get;
import gogoal.com.R;
import gogoal.com.Seriesdetail;

/**
 * Created by Go Goal on 5/6/2017.
 */
public class Nlvadapter_series extends BaseAdapter{

   LayoutInflater in;
    static ArrayList<get> list;
    AppCompatActivity ac;
    Typeface font;
    String moviename;

    public Nlvadapter_series(AppCompatActivity ac1, ArrayList<get> gets,String mname) {

        moviename=mname;
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

        View v=in.inflate(R.layout.cardviewrow,null);

        TextView title= (TextView) v.findViewById(R.id.moviename);
        final ImageView movieimahe= (ImageView) v.findViewById(R.id.movietheme);
        RippleView rp= (RippleView) v.findViewById(R.id.rp);


        title.setText(Html.fromHtml(list.get(i).getMoviename()));
        title.setTypeface(font);


        Picasso.with(ac) //
                .load(Constant.host + "series/" + list.get(i).getMovieid() + ".PNG")
                .into(new Target() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Drawable drawable = new BitmapDrawable(ac.getResources(), bitmap);
                        movieimahe.setBackground(drawable);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        movieimahe.setBackgroundResource(R.drawable.error_image);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        movieimahe.setBackgroundResource(R.drawable.loading);
                    }
                });




        rp.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {


                Intent it=new Intent(ac,Seriesdetail.class);
                it.putExtra("a",list.get(i).getMovieid());
                it.putExtra("filename",moviename+" ( "+list.get(i).getMoviepisode()+" )");
                ac.startActivity(it);

               /* ac.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse(list.get(i).getMovieimgurl())));*/


            }
        });

        return v;
    }

    public void refresh(ArrayList list1) {
        list=list1;
        notifyDataSetChanged();
    }
}
