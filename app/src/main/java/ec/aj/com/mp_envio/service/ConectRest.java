package ec.aj.com.mp_envio.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConectRest {

    Context context;
    String url;
    MensajePopUp mensaje;

    public ConectRest(Context context, String url){
        this.context = context;
        this.url = url;
        this.mensaje = new MensajePopUp(context);
    }

    public void comsumirRest(String strLatLon, final ProgressDialog progressDialog, String strUsuario) throws IOException {

        Map<String, String> params = new HashMap();
        params.put("usuario", strUsuario); // 30 car
        params.put("localizacion", strLatLon); // 100 caracteres
        params.put("descripcion", "Ayuda"); //40 caracteres
        params.put("estado", "Activo"); // 10
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        params.put("fechaCreacion", currentDateandTime);
        params.put("fechaCierre", "");

        JSONObject parameters = new JSONObject(params);

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.POST, //Request.Method.GET,
                url,
                parameters, //null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String strRespuesta = "-1";

                        try {
                            strRespuesta = response.getString("valor");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                        if(strRespuesta.equals("si valio"))
                            mensaje.mensajeTitulo("En un momento la atenderán",
                                                    "Alerta enviada");
                        else
                            mensaje.mensajeSimple("Hubo un problema, intente nuevamente");

                        Log.e("Rest Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        mensaje.mensajeSimple("Hubo un problema, intente nuevamente.");
                        Log.e("Rest Error", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);
    }
}
