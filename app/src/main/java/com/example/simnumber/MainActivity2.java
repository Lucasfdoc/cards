package com.example.simnumber;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Observable;
import java.util.Observer;

import static com.example.simnumber.MainActivity.PERMISSIONS;

public class MainActivity2 {
//public class MainActivity2 extends AppCompatActivity implements Observer {
    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Criação do Diretório e do Arquivo TXT para informações do SIM Card
        fileManager fm = new fileManager();
        fm.criaDiretorio("meuDir");

        setContentView(R.layout.activity_main);
        checkPermission();
        ObservableObject.getInstance().addObserver(this);
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                Button get_sim_number = findViewById(R.id.get_sim_number);
                get_sim_number.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        getSimNumber();
                    }
                });
            } else {
                requestPermissions(new String[]{"android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"}, PERMISSIONS);
            }
        } else {
            Button get_sim_number = findViewById(R.id.get_sim_number);
            get_sim_number.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    getSimNumber();
                }
            });
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS) {
            permissions.forEachIndexed() { i, d ->
                if (d == Manifest.permission.READ_PHONE_STATE && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Button get_sim_number = findViewById(R.id.get_sim_number);
                    get_sim_number.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            getSimNumber();
                        }
                    });
                    this.getSimNumber();
                }
            }
        }
    }

    public void getSimNumber() {


        val sm = getSystemService(TELEPHONY_SUBSCRIPTION_SERVICE) as? SubscriptionManager
        val list = sm?.activeSubscriptionInfoList
        sim_number_text?.removeAllViews()
        list?.forEach {
            sim_number_text?.addView(getTextView("iccId - ${it.iccId}"))
            sim_number_text?.addView(getTextView("countryIso - ${it.countryIso}"))
            sim_number_text?.addView(getTextView("number - ${it.number}"))
            //sim_number_text?.addView(getTextView("mccString - ${it.mccString}"))
            //sim_number_text?.addView(getTextView("mncString - ${it.mncString}"))
            //sim_number_text?.addView(getTextView("cardId - ${it.cardId}"))

            fm.escreveDados(it.iccId + "\n")
            Log.e("iccId -", it.iccId)
        }
    }

    @Override
    public void update(Observable observable, Object o) {

    }*/
}
