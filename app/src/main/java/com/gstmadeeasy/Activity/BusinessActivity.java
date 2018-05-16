package com.gstmadeeasy.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.gstmadeeasy.Adapter.UserAdapter;
import com.gstmadeeasy.Model.Business;
import com.gstmadeeasy.R;
import com.gstmadeeasy.Utils.MyUtil;


import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessActivity extends AppCompatActivity {
    public Context context;
    public String json_url;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ArrayList<Business> businessList;
    EditText inputSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSearch =  findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String inputText = MyUtil.lowerCase(s.toString());
                ArrayList<Business> businesslist = new ArrayList<>();

                for (Business business : businessList)
                {
                    String organizationName = MyUtil.lowerCase(business.getOrganizationName());
                    String ownerName = MyUtil.lowerCase(business.getOwnerName());
                    String email = MyUtil.lowerCase(business.getEmailID());
                    if(organizationName.contains(inputText) || ownerName.contains(inputText) || email.contains(inputText)){
                        businesslist.add(business);
                    }

                    userAdapter.setFilter(businesslist);

                }




            }
        });




        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);       // all list iszes has fixed size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        businessList= new ArrayList<>();
        json_url="http://API.gstmadeeasy.net/POS_API.svc/GetBusinessList?BusinessID=0";
       // json_url1=state_url="http://API.gstmadeeasy.net/POS_API.svc/GetBusinessList?BusinessID=28";
        //adapter = new MyAdapter(listitems, getApplicationContext());
        userAdapter=new UserAdapter(businessList, getApplicationContext(), new UserAdapter.OnClickListener() {

            @Override
            public void onItemCLick(Business business) {
                Intent intent=new Intent(getApplicationContext(),ViewCustomerDetails.class);
                intent.putExtra("businessid", business.getBusinessID());
                startActivity(intent);
            }

        });
        recyclerView.setAdapter(userAdapter);

    }



    private void loadRecyclerViewData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.......");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, json_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
               json = StringEscapeUtils.unescapeJson(json);
                progressDialog.dismiss();


                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("Data");
                    businessList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                       // String jsonString = object.toString();
                        Gson gson=new Gson();
                        Business business;
                        business = gson.fromJson(object.toString(), Business.class);
                        businessList.add(business);
                    }
                    userAdapter.notifyDataSetChanged();

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

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);                                    //add the request to the request queue

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerViewData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

//            case R.id.id_new:
//                Intent in = new Intent(getApplicationContext(),CreateBusiness_Activity.class);
//                in.putExtra("Action","new");
//                in.putExtra(MyUtil.OPERATION, MyUtil.OPERATION_NEW_BUSINESS);
//                startActivity(in);
//                break;
            case R.id.id_logout :

                SharedPreferences sharedpreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                break;
            default:
                break;

        }
        return true;
    }

    public void addNewBusiness(View view) {

        Intent in = new Intent(getApplicationContext(),CreateBusiness_Activity.class);
                in.putExtra("Action","new");
                in.putExtra(MyUtil.OPERATION, MyUtil.OPERATION_NEW_BUSINESS);
                startActivity(in);
        //startActivity(new Intent(getApplicationContext(),CreateBusiness_Activity.class).putExtra("Action","new"));
    }
}
