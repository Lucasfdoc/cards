package com.example.simnumber;

import android.util.Base64;

public class DataEncryption {
    public DataEncryption(){ }

    /***** Criptografando, criei um pValor como parametro retornar o valor criptografado *****/
    public String criptografiaBase64Encoder(String pValor) {
        return new String(Base64.encode(pValor.getBytes(), Base64.DEFAULT));
    }

    /***** Realizando o inverso criei um pValor como parametro retornar o valor descriptografado*****/
    public String descriptografiaBase64Decode(String pValor) {
        return new String(Base64.decode(pValor.getBytes(), Base64.DEFAULT));
    }
}
