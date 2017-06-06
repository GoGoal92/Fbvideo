package gogoal.myfb.Adapter;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import gogoal.R;
import gogoal.myfb.Moviedetail;
import gogoal.myfb.Object.Constant;
import gogoal.myfb.Object.get;
import gogoal.myfb.Series_ca_detail;
import gogoal.myfb.utils.HFRecyclerView;

/**
 * Created by Go Goal on 11/25/2016.
 */
public class Cate_recycler_adapter extends HFRecyclerView<get> {


    ArrayList<get> asklist;
    Activity ac;
    Typeface font;


    public Cate_recycler_adapter(Activity getactivity, ArrayList<get> asklist1) {
        super(asklist1, true, true);
        asklist = asklist1;
        ac = getactivity;
        font = Typeface.createFromAsset(ac.getAssets(), "zawgyi.ttf");

    }

    @Override
    protected RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.cardviewrow, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder getHeaderView(LayoutInflater inflater, ViewGroup parent) {
        return new HeaderViewHolder(inflater.inflate(R.layout.nothing, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent) {
        return new FooterViewHolder(inflater.inflate(R.layout.footer_pg, parent, false));
    }




    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {

        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ItemViewHolder) {
            final ItemViewHolder holder1 = (ItemViewHolder) holder;

            holder1.title.setText(Html.fromHtml(asklist.get(i-1).getMoviename()));
            holder1.title.setTypeface(font);


            Picasso.with(ac) //
                    .load(Constant.host+asklist.get(i-1).getMovietype()+"/"+asklist.get(i-1).getMovieimgurl())
                    .into(new Target() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            Drawable drawable = new BitmapDrawable(ac.getResources(), bitmap);
                            holder1.movieimahe.setBackground(drawable);
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                            holder1.movieimahe.setBackgroundResource(R.drawable.error_image);
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                            holder1.movieimahe.setBackgroundResource(R.drawable.loading);
                        }
                    });

                holder1.rp.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                    @Override
                    public void onComplete(RippleView rippleView) {

                        Intent it=null;
                        if (asklist.get(i-1).getMovietype().equals("Movie")){
                            it=new Intent(ac,Moviedetail.class);
                        }else{
                            it=new Intent(ac,Series_ca_detail.class);
                        }

                        it.putExtra("a",Constant.host+"/"+asklist.get(i-1).getMovietype()+"/"+asklist.get(i-1).getMovieimgurl());
                        it.putExtra("b",asklist.get(i-1).getMovieid());
                        it.putExtra("c",asklist.get(i-1).getMoviename());
                        ac.startActivity(it);


                    }
                });




        } else if (holder instanceof FooterViewHolder) {

            FooterViewHolder footholder = (FooterViewHolder) holder;
            if (showpg) {
                footholder.progressBar.setVisibility(View.VISIBLE);
            } else {
                footholder.progressBar.setVisibility(View.GONE);
            }


        }
    }

    static boolean showpg = false;


    @Override
    public int getItemCount() {
        return asklist.size() + 2;
    }

    public void refresh(ArrayList<get> list) {
        asklist = list;
        notifyDataSetChanged();

    }

    public void load_pg(boolean b) {
        showpg = b;
        notifyDataSetChanged();
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {


        public TextView title;
        public ImageView movieimahe;
        public RippleView rp;


        public ItemViewHolder(View v) {
            super(v);

            title= (TextView) v.findViewById(R.id.moviename);
            movieimahe= (ImageView) v.findViewById(R.id.movietheme);
            rp= (RippleView) v.findViewById(R.id.rp);


        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public FooterViewHolder(View v) {
            super(v);

            progressBar = (ProgressBar) v.findViewById(R.id.ppgg);
        }
    }

}