package com.gstmadeeasy.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.gstmadeeasy.Adapter.UserAdapter;
import com.gstmadeeasy.Model.Business;
import com.gstmadeeasy.Model.City;
import com.gstmadeeasy.Model.GSTCategory;
import com.gstmadeeasy.Model.State;
import com.gstmadeeasy.R;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewCustomerDetails extends AppCompatActivity {

    EditText view_OrgsanizationName,view_OwnerName, view_ContactNo,view_EmailID,view_GSTNumber,view_GSTCategory,view_Address,view_State,view_City,view_FullAddress,view_Status,view_CLientCode;

    Button view_Back;
    ImageView view_SignatureImage;
    String viewjson_url;
    ArrayList<Business> businessViewList;
   UserAdapter userAdapter;
    Bitmap signBitmap;
   //DataBaseHandler dataBaseHandler;
    Business business;
    int MY_SOCKET_TIMEOUT_MS=500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_details);



        business = new Business();
        view_OrgsanizationName = findViewById(R.id.OrganizationName);
        view_OwnerName = findViewById(R.id.view_OwnerName);
        view_ContactNo = findViewById(R.id.view_ContactNo);
        view_EmailID = findViewById(R.id.view_EmailId);
        view_GSTNumber = findViewById(R.id.view_GstNumber);
        view_GSTCategory = findViewById(R.id.view_GstCategory);
        view_Address = findViewById(R.id.view_Address);
        view_State = findViewById(R.id.view_State);
        view_City = findViewById(R.id.view_City);
        view_FullAddress= findViewById(R.id.view_FullAddress);
        view_Status = findViewById(R.id.view_Status);
        view_CLientCode = findViewById(R.id.view_ClientCode);


//        view_SignatureImage = (ImageView)findViewById(R.id.view_SignatureImage);

        view_Back = (Button)findViewById(R.id.view_Back);

        view_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ViewCustomerDetails.this, BusinessActivity.class);
//                startActivity(intent);
                ViewCustomerDetails.super.onBackPressed();
                //Toast.makeText(getApplicationContext()," ",Toast.LENGTH_SHORT).show();
            }
        });

            int business_id = getIntent().getIntExtra("businessid", 0);
            loadDataToView(business_id);

    }

    private void loadDataToView(int business_id) {

        viewjson_url = "http://API.gstmadeeasy.net/POS_API.svc/GetBusinessList";
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.......");
        progressDialog.show();


       String json_url = Uri.parse(viewjson_url).buildUpon().appendQueryParameter("BusinessId",business_id+"").toString();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, json_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
                json = StringEscapeUtils.unescapeJson(json);
                progressDialog.dismiss();


                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("Data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        // String jsonString = object.toString();
                        Gson gson=new Gson();
                        Business business;
                        business = gson.fromJson(object.toString(), Business.class);
                        view_OrgsanizationName.setText(business.getOrganizationName());
                        view_OwnerName.setText(business.getOwnerName());
                        view_ContactNo.setText(business.getContactNo());
                        view_EmailID.setText(business.getEmailID());
                        view_GSTCategory.setText(business.getGSTCategory());
                        view_GSTNumber.setText(business.getGSTNumber());
                        view_Address.setText(business.getAddress());
                        view_State.setText(business.getStateName());
                       // City city = new City();
                        view_City.setText(business.getCity());
                        view_FullAddress.setText(business.getFullAddress());
                        view_Status.setText(business.getStatus());
                        view_CLientCode.setText(business.getClientCode());

                    }
                   // userAdapter.notifyDataSetChanged();

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

        stringRequest.setRetryPolicy(new DefaultRetryPolicy( MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //add the request to the request queue



//        view_SignatureImage.setImageBitmap(DbBitmapUtility.getImage(business.getImageBytes()));
        //view_Sales_Person.setText(business.getSales_person());





    }
}
