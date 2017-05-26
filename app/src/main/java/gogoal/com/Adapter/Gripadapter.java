package gogoal.com.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

import gogoal.com.Moviedetail;
import gogoal.com.Object.Constant;
import gogoal.com.Object.get;
import gogoal.com.R;
import gogoal.com.Ratio;

/**
 * Created by Go Goal on 5/6/2017.
 */
public class Gripadapter extends BaseAdapter {

    LayoutInflater in;
    static ArrayList<get> list;
    AppCompatActivity ac;
    Typeface font;

    public Gripadapter(AppCompatActivity ac1, ArrayList<get> gets) {

        ac = ac1;
        in = (LayoutInflater) ac.getSystemService(ac.LAYOUT_INFLATER_SERVICE);
        list = gets;
        font = Typeface.createFromAsset(ac.getAssets(), "zawgyi.ttf");

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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

      final  ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = in.inflate(R.layout.movie_clipart, viewGroup, false);
            holder.parma = (LinearLayout) convertView.findViewById(R.id.parmalinear);
            holder.tv = (TextView) convertView.findViewById(R.id.moviename);
            holder.iv = (ImageView) convertView.findViewById(R.id.movietheme);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        // View v=in.inflate(R.layout.movie_clipart,null);

        // LinearLayout parma= (LinearLayout) v.findViewById(R.id.parmalinear);

        ViewGroup.LayoutParams roo2 = holder.parma.getLayoutParams();
        float res2 = (float) Ratio.getmovieclipart_width();
        roo2.width = (int) res2;
        holder.parma.setLayoutParams(roo2);


        holder.tv.setText(Html.fromHtml(list.get(i).getMoviename()));
        holder.tv.setTypeface(font);


        Picasso.with(ac) //
                .load(Constant.host + "Movie/" + list.get(i).getMovieimgurl())
                .into(new Target() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Drawable drawable = new BitmapDrawable(ac.getResources(), bitmap);
                        holder.iv.setBackground(drawable);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        holder.iv.setBackgroundResource(R.drawable.error_image);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        holder.iv.setBackgroundResource(R.drawable.loading);
                    }
                });

        holder.parma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ac, Moviedetail.class);
                it.putExtra("a", Constant.host + "/Movie/" + list.get(i).getMovieimgurl());
                it.putExtra("b", list.get(i).getMovieid());
                it.putExtra("c", list.get(i).getMoviename());
                ac.startActivity(it);
            }
        });

        return convertView;
    }


    public void refresh(ArrayList list1) {
        list = list1;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        LinearLayout parma;
        TextView tv;
        ImageView iv;
    }
}
