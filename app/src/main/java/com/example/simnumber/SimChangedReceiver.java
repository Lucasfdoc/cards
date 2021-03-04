package com.example.simnumber;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.TimeUnit;
import android.util.Log;


public class SimChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        ObservableObject.getInstance().updateValue(intent);
        Log.d("SimChangedReceiver", "--> SIM state changed <--");
    }
}
