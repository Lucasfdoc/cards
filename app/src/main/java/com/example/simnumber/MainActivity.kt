package com.example.simnumber

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SubscriptionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.text_view.view.*
import java.util.*

class MainActivity : AppCompatActivity(), Observer {
    private val fm = FileManager();
    private val rn = RegraNegocio();
    private val notificacao = Notification();
    private val encrypt = DataEncryption();
    private var modoGravacao = false;
    private var tempo: Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
        ObservableObject.getInstance().addObserver(this);

        val toggle: ToggleButton = findViewById(R.id.toggleButton)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            modoGravacao = isChecked
        }
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
               if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                   get_sim_number?.setOnClickListener { getSimNumber() }
                   fm.criaDiretorio("meuDir")
               } else requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSIONS)
        } else {
            get_sim_number?.setOnClickListener { getSimNumber() }
            fm.criaDiretorio("meuDir")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS) {
            permissions.forEachIndexed() { i, d ->
                if (d == Manifest.permission.READ_PHONE_STATE && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    get_sim_number?.setOnClickListener { getSimNumber() }
                    fm.criaDiretorio("meuDir")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun getSimNumber() {
        val sm = getSystemService(TELEPHONY_SUBSCRIPTION_SERVICE) as? SubscriptionManager
        val list = sm?.activeSubscriptionInfoList

        //val quantidade = sm?.activeSubscriptionInfoCountMax
        //Log.d("getSubscriptionCount", "=====> $quantidade")

        var iccIdAtual = "";

        sim_number_text?.removeAllViews()
        list?.forEach {
            iccIdAtual = it.iccId;
            sim_number_text?.addView(getTextView("iccId - $iccIdAtual"))
            sim_number_text?.addView(getTextView("countryIso - ${it.countryIso}"))
            sim_number_text?.addView(getTextView("number - ${it.number}"))
            //sim_number_text?.addView(getTextView("mccString - ${it.mccString}"))
            //sim_number_text?.addView(getTextView("mncString - ${it.mncString}"))
            //sim_number_text?.addView(getTextView("cardId - ${it.cardId}"))

            /***********************************************/
            iccIdAtual = encrypt.criptografiaBase64Encoder(iccIdAtual);

            fm.escreveDados(iccIdAtual + "\n")
            Log.e("iccId -", iccIdAtual)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun getSimNumberArray(): Array<String> {
        val sm = getSystemService(TELEPHONY_SUBSCRIPTION_SERVICE) as? SubscriptionManager
        val list = sm?.activeSubscriptionInfoList
        val simList: MutableList<String> = ArrayList()

        var iccIdAtual = "";

        list?.forEach {
            simList.add(it.iccId)
        }
        return simList.toTypedArray()
    }

    private fun getTextView(text: String): View {
        val view = LayoutInflater.from(this).inflate(R.layout.text_view, sim_number_text, false)
        view?.text_view?.text = text
        return view
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun update(observable: Observable?, data: Any) {
        if ((System.currentTimeMillis() - tempo) > 5000) {

            //Forma de Capturar os dados do TXT e colocar em um array
            /***********************************************/
            //val txtSimCards = fm.obterDadosArquivo();
            val txtSimCards = fm.obterDadosArquivo(encrypt);
            //Capturar os dados atuais - Main - Feito
            val currentSimCards = this.getSimNumberArray()

            if(modoGravacao){
                val notChanged = rn.compareAndUpdateSimLists(txtSimCards, currentSimCards, fm, encrypt);

                if(notChanged) {
                    notificacao.notifica(this, "Nenhuma novo cartão adicionado");
                } else {
                    notificacao.notifica(this, "Alterações aplicadas")
                }
            } else {
                //Compparação dos arrays
                val notChanged = rn.compareSimLists(this, txtSimCards, currentSimCards);

                if (notChanged) {
                    notificacao.notifica(this, "Nenhuma alteração indevida");
                } else {
                    notificacao.notifica(this, "Alteração indevida");
                }
            }
            tempo = System.currentTimeMillis()
        }
    }

    companion object {
        const val PERMISSIONS = 101
    }
}
