package gogoal.com.utils;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;

import com.fenjuly.library.ArrowDownloadButton;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Go Goal on 5/21/2017.
 */

public class DownloadService extends IntentService {
    public static final int UPDATE_PROGRESS = 8344;
    String name,urlToDownload,path,where;

    ArrowDownloadButton pg;


    ResultReceiver receiver;
    private static MyResultReceiver reciever;

    public DownloadService() {
        super("DownloadService");
    }

    public static void setreciever(MyResultReceiver reciever1) {
        reciever = reciever1;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
         urlToDownload = intent.getStringExtra("url");
         name = intent.getStringExtra("name");
         path = intent.getStringExtra("file");
        where = intent.getStringExtra("pg");



        receiver = intent.getParcelableExtra("receiver");
      //  receiver = (ResultReceiver) intent.getParcelableExtra("receiver");

      //  new DownloadTask().execute();
        try {
            URL url = new URL(urlToDownload);
            URLConnection connection = url.openConnection();
            connection.connect();
            // this will be useful so that you can show a typical 0-100% progress bar
            int fileLength = connection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());
            OutputStream output = new FileOutputStream(path+"/"+name+".mp4");

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
              /*  Intent it = new Intent();
                it.setAction("takeit");
                it.putExtra("progress",(int) (total * 100 / fileLength));
                sendBroadcast(it);*/


                Bundle resultData = new Bundle();
                resultData.putInt("progress" ,(int) (total * 100 / fileLength));
                receiver.send(UPDATE_PROGRESS, resultData);
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bundle resultData = new Bundle();
        resultData.putInt("progress" ,100);
        receiver.send(UPDATE_PROGRESS, resultData);
    }




}