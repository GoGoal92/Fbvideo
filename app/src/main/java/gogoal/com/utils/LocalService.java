package gogoal.com.utils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.id.progress;

/**
 * Created by Go Goal on 5/22/2017.
 */

public class LocalService extends Service {
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {

        public LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    int k=0;

    /** method for clients */
    public int getRandomNumber() {




        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            k++;
            }
        }, 800, 20);




        return k;
    }
}
