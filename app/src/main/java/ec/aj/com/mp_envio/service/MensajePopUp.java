package ec.aj.com.mp_envio.service;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class MensajePopUp {

    Context context;
    String url;

    public MensajePopUp(Context context){
        this.context = context;
    }

    public void mensajeSimple(String strMensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(strMensaje);
        alertDialogBuilder.show();
    }
    public void mensajeTitulo(String strMensaje, String strTitulo){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(strMensaje);
        alertDialogBuilder.setTitle(strTitulo);
        alertDialogBuilder.show();
    }
}
