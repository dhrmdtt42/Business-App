package com.gstmadeeasy.ApiCalls;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.gstmadeeasy.Model.Business;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Dharam on 3/13/2018.
 */

public class GetBusinessDetails {

    Business business;
    List<Business> list;
    Context context;
    int responseCode;
    String json_url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
//    RequestQueue queue = Volley.newRequestQueue(context);

    public GetBusinessDetails() {


        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, json_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response.toString());

                        //JSONObject jsonObject = ("heroes");
                        try {
//                        responseCode = response.getInt("re");

                            JSONObject jsonObject = new JSONObject();
                            JSONArray heroes = jsonObject.getJSONArray("heroes");

                            Gson gson = new Gson();
                            String jsonString = heroes.getJSONObject(0).toString();
                            business = gson.fromJson(jsonString, Business.class);
                            list.add(business);
                            for (int i = 0; i < heroes.length(); i++) {



                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error", error.toString());
            }

        });

        queue.add(request);

    }

}
