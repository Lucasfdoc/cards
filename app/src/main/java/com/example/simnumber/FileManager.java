package com.example.simnumber;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private File diretorio;
    private File file;

    public void criaDiretorio(String nomeDiretorio, String nomeArquivo){
        Log.i("criaDiretorio:", "==> Criando Diretorio e Arquivo");
        String diretorioApp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + "/"+nomeDiretorio+"/";

        diretorio = new File(diretorioApp);
        diretorio.mkdirs();
        file = new File(diretorioApp, nomeArquivo);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escreveDados(String data){
        try {
            FileOutputStream fosExt = new FileOutputStream(file, true);
            //Escreve no arquivo
            //fosExt.write(lastModif);
            fosExt.write(data.getBytes());

            //Obrigatoriamente você precisa fechar
            fosExt.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public String[] obterDadosArquivo() {
    public String[] obterDadosArquivo(DataEncryption dataEncryption) {
        Scanner leitor = null;
        String[] vetorDeLinhas = null;
        String iccId = "";
        try {
            leitor = new Scanner(file);
            List linhas = new ArrayList<>();
            while (leitor.hasNextLine()) {
                iccId = leitor.nextLine();

                /***********************************************/
                iccId = dataEncryption.descriptografiaBase64Decode(iccId);

                linhas.add(iccId);
            }
            leitor.close();

            vetorDeLinhas = (String[])linhas.toArray(new String[linhas.size()]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return vetorDeLinhas;

    }

}
