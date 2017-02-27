package com.example.android.detail_transaksi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.example.android.detail_transaksi.config.Config;

public class MainActivity extends AppCompatActivity {
    public EditText editKode;

    String URL =  Config.URL + "API_transaksi/index.php";

    JSONParser jsonParser=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Creating a shared preference
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.MyPREFERENCES, Context.MODE_PRIVATE);

        //baca data
        String kode = sharedPreferences.getString(Config.kode_transaki,"");
        String lab = sharedPreferences.getString(Config.nama_lab,"");
        String cp = sharedPreferences.getString(Config.nama_kontak,"");

        TextView txtView=(TextView)findViewById(R.id.kode);
        txtView.setText(kode);

        TextView txtView2=(TextView)findViewById(R.id.nama_lab);
        txtView2.setText(lab);

        TextView txtView3=(TextView)findViewById(R.id.nama_kontak);
        txtView3.setText(cp);

    }


    public void login(View view) {
        editKode=(EditText)findViewById(R.id.editName);


        final ProgressDialog ringProgressDialog = ProgressDialog.show(MainActivity.this, "Mohon Tunggu","Masuk ke Aplikasi", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    AttemptLogin attemptLogin= new AttemptLogin();
                    attemptLogin.execute(editKode.getText().toString(),"");


                } catch (Exception e) {

                }
                ringProgressDialog.dismiss();
            }
        }).start();
    }

    private class AttemptLogin extends AsyncTask<String,String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {

            String kode= args[0];

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("kode", kode));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();

                    //sp
                    //Creating a shared preference
                    SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.MyPREFERENCES, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putString(Config.kode_transaki,result.getString("kodeTransaksi"));
                    editor.putString(Config.nama_lab,result.getString("NamaLab"));
                    editor.putString(Config.nama_kontak,result.getString("namaCP"));

                    //Saving values to editor
                    editor.commit();

                    // Reload the Main Activity
                    reloadActivity();

                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    // Reload MainActivity
    public void reloadActivity() {
        Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(objIntent);
    }
}
