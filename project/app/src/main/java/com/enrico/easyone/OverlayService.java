package com.enrico.easyone;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

public class OverlayService extends Service {

    Notification serviceNotification;
    Intent broadcastIntent;

    BroadcastReceiver mReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        broadcastIntent = new Intent("dontKillMe");
        sendBroadcast(broadcastIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Make a foreground notification to avoid android kills the service
        serviceNotification = new Notification.Builder(this)
                .setOngoing(false)
                .build();
        startForeground(101, serviceNotification);

        IntentFilter filter = new IntentFilter();
        // filter.addAction("usethistochangeoverlayparams");

        mReceiver = new killReceiver();
        registerReceiver(mReceiver, filter);

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FrameLayout frameLayout = new FrameLayout(this);

        frameLayout.setBackgroundColor(getResources().getColor(R.color.colorPhoneDark, getTheme()));
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);


        //change this through preferences
        int dragWidth = 20;

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(dragWidth, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);

        //change this through preferences
        params.gravity = Gravity.START;

        params.x = 0;
        params.y = 0;

        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(OverlayService.this, "yo", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        if (windowManager != null) {
            windowManager.addView(frameLayout, params);
        }
    }

    public class killReceiver extends BroadcastReceiver {

        public killReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {

            //use actions to manage the overlay through preferences
            String action = intent.getAction();
        }
    }
}
