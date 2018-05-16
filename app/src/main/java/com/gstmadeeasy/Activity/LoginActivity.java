package com.gstmadeeasy.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.gstmadeeasy.Model.GSTCategory;
import com.gstmadeeasy.R;
import com.gstmadeeasy.Utils.ApiCallHandler;
import com.gstmadeeasy.Utils.MyUtil;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText login, password;
    TextInputLayout login_TextInputLayout, password_TextInputLayout;
    //PostLogin postlogin;

    SharedPreferences sharedPreferences;
    String Jsonlogin_url;
    String username,password_key;

   int  MY_SOCKET_TIMEOUT_MS=500;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Jsonlogin_url = "http://API.gstmadeeasy.net/POS_API.svc/validateAdmin";
        sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("logged" ,false)) {
           // Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,BusinessActivity.class);
            startActivity(intent);
            finish();
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);

        login_TextInputLayout = (TextInputLayout)findViewById(R.id.login_TextInputLayout);
        password_TextInputLayout = (TextInputLayout)findViewById(R.id.password_TextInputLayout);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(login.getText().toString().equals("")){
                    if(password.getText().toString().equals(""))
                    {

                    }
                    else{
                           showMessage("please enter the correct password");
                           return;
                    }
                }else{
                     showMessage(" enter the correct login id");
                }


                loadLoginData();

            }
        });
    }

    private void loadLoginData() {

        final RequestQueue requestQueue= Volley.newRequestQueue(this);

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.......");
        progressDialog.show();

        username = login.getText().toString();
         password_key = password.getText().toString();


        String login_url = Uri.parse(Jsonlogin_url).buildUpon().
                appendQueryParameter("UserName",username).
                appendQueryParameter("Pwd",password_key).toString();

        final StringRequest loginRequest=new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
                json = StringEscapeUtils.unescapeJson(json);
                try {


                    JSONObject jsonObject = new JSONObject(json);
                    String result = jsonObject.getJSONArray("Response").getJSONObject(0).getString("Response");
                    if ("Success".equals(result)) {


                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logged", true);
                        editor.putString(MyUtil.USERNAME, username);
                        editor.putString(MyUtil.PASSWORD, password_key);
                        editor.commit();

                        Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,BusinessActivity.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error Occured getting gstcategory", Toast.LENGTH_SHORT);
                    }
                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "error occured", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        loginRequest.setRetryPolicy(new DefaultRetryPolicy( MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(loginRequest);
    }


    private void showMessage (String msg)
    {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);

        builder.setTitle("GSTMadeEasy")
                .setMessage(msg)
                .setPositiveButton("OK" ,   new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .show();

    }
}

