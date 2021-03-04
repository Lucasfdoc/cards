package com.example.simnumber;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class RegraNegocio {
    public boolean compareSimLists(Context cn, String[] simTxt, String[] simAtual) {
        boolean notChanged = true;
        // Convert String Array to List
        if(simTxt.length != 0 ) {
            List<String> list = Arrays.asList(simTxt);

            /*SubscriptionManager subscriptionManager = (SubscriptionManager) cn.getSystemService(cn.TELEPHONY_SUBSCRIPTION_SERVICE);
            int quantidade = subscriptionManager.getActiveSubscriptionInfoCountMax();
            Log.d("getSubscriptionCount", "=====> " + quantidade);*/

            int interaction = simAtual.length;
            for(int i = 0; i < interaction; i++) {
                if (!list.contains(simAtual[i])) {
                    /*Class<TelephonyManager> telephonyManagerClass = TelephonyManager.class;
                    TelephonyManager telephonyManager = (TelephonyManager) cn.getSystemService(cn.TELEPHONY_SERVICE);
                    try {
                        Method method = telephonyManagerClass.getMethod("getSimState", new Class[]{int.class});
                        method.setAccessible(true);
                        Object object = method.invoke(telephonyManager,1);
                        int a = (int)object;
                        System.out.println(a);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }*/

                    notChanged = false;
                }
            }
        }
        return notChanged;
    }

    /****************************************************/
    //public boolean compareAndUpdateSimLists(String[] simTxt, String[] simAtual, FileManager fm) {
    public boolean compareAndUpdateSimLists(String[] simTxt, String[] simAtual, FileManager fm, DataEncryption dataEncryption) {
        boolean notChanged = true;
        // Convert String Array to List
        if(simTxt.length != 0 ) {
            List<String> list = Arrays.asList(simTxt);

            String iccId = "";

            int interaction = simAtual.length;
            for(int i = 0; i < interaction; i++) {
                if (!list.contains(simAtual[i])) {
                    iccId = simAtual[i];

                    /****************************************************/
                    iccId = dataEncryption.criptografiaBase64Encoder(iccId);

                    fm.escreveDados(iccId + "\n");
                    notChanged = false;
                }
            }
        }
        return notChanged;
    }
}
