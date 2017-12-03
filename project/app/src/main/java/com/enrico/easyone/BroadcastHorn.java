package com.enrico.easyone;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BroadcastHorn extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent restartService = new Intent(context, OverlayService.class);

        Log.d("killed", "killed");
        context.startService(restartService);
    }
}
