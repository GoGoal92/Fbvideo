package gogoal.myfb.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;

import br.com.bemobi.medescope.Medescope;
import br.com.bemobi.medescope.callback.DownloadStatusCallback;
import gogoal.R;
import gogoal.myfb.utils.Downloadlist;

/**
 * Created by Go Goal on 12/3/2016.
 */
public class Downloadadapter extends RecyclerView.Adapter<Downloadadapter.ContactViewHolder> {

    static Activity ac;
    static ArrayList<String> list;
    Medescope mMedescope;

    public Downloadadapter(Activity getactivity, ArrayList<String> list1) {

        list = list1;
        ac = getactivity;

        mMedescope = Medescope.getInstance(ac);
        mMedescope.setApplicationName(ac.getString(R.string.app_name));

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dm_progress_row, parent, false));
    }


    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {


        holder.mtitle.setText(list.get(position));
        holder.mclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopdownload(list.get(position));
                list.remove(position);
                Downloadlist.removelist(list);
                notifyDataSetChanged();
          //      stopdownload(list.get(position));
            }
        });





        mMedescope.subscribeStatus(ac, list.get(position), new DownloadStatusCallback() {
            @Override
            public void onDownloadNotEnqueued(String downloadId) {

                holder.pg.setVisibility(View.GONE);
                holder.mprogress.setText("Complete");

            }

            @Override
            public void onDownloadPaused(String downloadId, int reason) {

            }

            @Override
            public void onDownloadInProgress(String downloadId, final int progress) {

                holder.pg.setProgress(progress);
                holder.mprogress.setText(progress+" %");

            }

            @Override
            public void onDownloadOnFinishedWithError(String downloadId, int reason, String data) {

                holder.mprogress.setText("Download Fail");

            }

            @Override
            public void onDownloadOnFinishedWithSuccess(String downloadId, String filePath, String data) {
                holder.mprogress.setText("Complete");
            }

            @Override
            public void onDownloadCancelled(String downloadId) {

            }
        });



    }

    private void stopdownload(String s) {
        mMedescope.cancel(s);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView mtitle,mprogress;
        public ImageView mclose;
        public ProgressBar pg;

        public ContactViewHolder(View v) {
            super(v);

            mtitle= (TextView) v.findViewById(R.id.textView3);
            mprogress= (TextView) v.findViewById(R.id.percent);
            mclose= (ImageView) v.findViewById(R.id.close);
            pg= (ProgressBar) v.findViewById(R.id.progressBar2);



        }

    }
}
