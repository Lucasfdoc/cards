package com.example.simnumber;

import android.content.Context;
import android.widget.Toast;

public class Notification {
    public boolean notifica(Context context, String information){
        Toast.makeText(context, information, Toast.LENGTH_SHORT).show();
        return true;
    }
}
