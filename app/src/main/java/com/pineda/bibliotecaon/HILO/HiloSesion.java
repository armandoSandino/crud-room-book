package com.pineda.bibliotecaon.HILO;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class  HiloSesion extends Thread{
    public  volatile boolean vivo; // mediante esta  variable podriamos detener el hilo
    public HiloSesion(){
        vivo=true;
    }
    public void run(){
        while (vivo){
            try{
                  //    new Hard().execute();
                System.out.println("Estamos en la clase HiloSesion.java");
                sleep(3000);
            }catch (Exception ex){
                System.out.println("Error de hilo" + ex.getMessage() );
                Log.e(null,"Error de hilo" + ex.getMessage() + ex.getStackTrace() );
                ex.getStackTrace();
            }
        }
    }
    /* //para correr el hilo
     Verificar_Envios a =  new Verificar_Envios();
     a.start();
     try{ esperamos un poco
      Thread.sleep(20000);
     }catch(InterruptedException ex){
     System.out.println("Error de tiempo :" + ex.getMessage());
     }
     // a.isAlive() // con esto verificamos si el hilo esta vivo o no
     // si deceamos dormir o colgar el hilo  o simplemente terminarllo
     a.vivo = false;
     try {
 temporizador.join();
 } catch (InterruptedException ie) {
 System.out.println(ie.getMessage());
 }  a = null;
 */
    private class Hard extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected JSONObject doInBackground(String... datos) {
            StringBuilder respuesta = new StringBuilder();
            try {
                URL url_post=null; String cod="";
                url_post = new URL("http://server.com/"
                        +"InsertCoorUsuario.php?cod="+cod+"&la=99");
                HttpURLConnection url = (HttpURLConnection) url_post.openConnection();
                url.setRequestMethod("GET");
                if (url.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream in = new BufferedInputStream(url.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        respuesta.append(line);
                    }
                }
            }catch (MalformedURLException e)
            {
                Log.e("Error", e.getMessage());
            }catch (ProtocolException e)
            {
                Log.e("Error",e.getMessage());
            }catch (IOException e)
            {
                Log.e("Error",e.getMessage());
            }
            JSONObject json = null;
            try {
                json = new JSONObject(respuesta.toString());
            }catch (JSONException e)
            {
                Log.e("Error Alformar el JSON",e.getMessage());
            }
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject data) {
            super.onPostExecute(data);
            try {
                if(data != null ) {
                    if ( !data.getString("status").isEmpty() ) {
                        Log.e("Checa esto Dev"," " + data.getString("status") );
                    }
                }else Log.e("Checa esto Dev","Surgio algun error al hacer la peticion,tienes que echarle dos ojos." );

            }catch(JSONException e){
                Log.e("Error",e.getMessage());
            }
        }

    }

}
