package com.sifiso.ylibrary.YLibrary.dto.util;

import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aubreyM on 2014/08/28.
 */

public class TimerUtil {

    public interface TimerListener {
        public void onSessionDisconnected();

        public void classOut();
    }

    static TimerListener listener;
    static Timer timer;
    static final long TEN_SECONDS = 1 * 1000;

    public static void startTimer(TimerListener timerListener) {
        //
        Log.d("TimerUtil", "########## Websocket Session Timer starting .....");
        listener = timerListener;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("TimerUtil", "########## about to disconnect websocket session");
                WebSocketUtil.disconnectSession();
                listener.onSessionDisconnected();
            }
        }, TEN_SECONDS);
    }

    public static void killTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            Log.w("TimerUtil", "########## Websocket Session Timer KILLED");
        }
    }

    public static void startClass(final TimerListener listener, final long time) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("Timer", time + "");
                listener.classOut();
            }
        }, new Date(), time);

    }
}
