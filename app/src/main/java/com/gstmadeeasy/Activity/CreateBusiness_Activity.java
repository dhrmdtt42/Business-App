package com.gstmadeeasy.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.gstmadeeasy.Model.Business;
import com.gstmadeeasy.Model.City;
import com.gstmadeeasy.Model.GSTCategory;
import com.gstmadeeasy.Model.State;
import com.gstmadeeasy.R;
import com.gstmadeeasy.Utils.MyUtil;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class CreateBusiness_Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

   // SQLiteDatabase db;

                    EditText OrganizationName_editText,OwnerName_editText, EmailID_editText, ContactNo_editText,Address_editText,City_editText, Country_editText,StateID_editText,StatusID_editText;
            EditText FullAddress_editText,GSTCategoryID_editText,GSTNumber_editText,Status_editText
                    ,uname_editText,password_editText,btnVerify_editText,btnActivate__editText,btnSuspend_editText,btnTerminate;
            //            ,ClientCode_editText
            TextInputLayout OrganizationName_TextInputLayout,OwnerName_TextInputLayout, EmailID_TextInputLayout, ContactNo_TextInputLayout,Address_TextInputLayout;
            TextInputLayout gstNumber_TextInputLayout, country_TextInputLayout, address_TextInputLayout, status_TextInputLayout, city_TextInputLayout;
            Spinner   GSTCategory_Spinner,State_Spinner,City_Spinner;
            Button   save,cancel,back;
            ArrayAdapter<State> stateAdapter;
            ArrayAdapter<GSTCategory>  GSTCategory_Adapter;
            ArrayAdapter<City> CityAdapter;
            String action;
            ImageView signatureImageView;
            Bitmap signBitmap;
            String state_url, gstcategory_url,savejson_url,editjson_url;
            Business businessToSave;
            Context context;
            ArrayList<Business> businesslist;
            ArrayList<State> state_list;
            ArrayList<GSTCategory> GSTCategory_list;
            ArrayList<City> City_list;
            int MY_SOCKET_TIMEOUT_MS = 500;

            int businessId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_customer__details_);

                businessToSave = new Business();

                state_url ="http://API.gstmadeeasy.net/POS_API.svc/GetState";
                gstcategory_url ="http://API.gstmadeeasy.net/POS_API.svc/GetGSTCategory?BusinessID=21";

                savejson_url = "http://API.gstmadeeasy.net/POS_API.svc/SaveBusiness";
//
//                OrganizationName_TextInputLayout = (TextInputLayout)findViewById(R.id. OrganizationName_TextInputLayout);
//                OwnerName_TextInputLayout = (TextInputLayout)findViewById(R.id.OwnerName_TextInputLayout);
//
//                EmailID_TextInputLayout = (TextInputLayout)findViewById(R.id. EmailID_TextInputLayout);
//                ContactNo_TextInputLayout = (TextInputLayout)findViewById(R.id.ContactNo_TextInputLayout);
//                Address_TextInputLayout = (TextInputLayout)findViewById(R.id.Address_TextInputLayout);
//

                OrganizationName_editText = (EditText)findViewById(R.id.OrganizationName_editText);
                OwnerName_editText = (EditText)findViewById(R.id.OwnerName_editText);
                EmailID_editText = (EditText)findViewById(R.id.EmailID_editText);
                ContactNo_editText = (EditText)findViewById(R.id.ContactNo_editText);

                Address_editText = (EditText)findViewById(R.id.Address_editText);
               // City_editText = (EditText)findViewById(R.id.City_editText);
               Country_editText = (EditText)findViewById(R.id.Country_editText);
               // StatusID_editText = (EditText)findViewById(R.id.StatusID_editText);
                FullAddress_editText = (EditText)findViewById(R.id.FullAddress_editText);
               // GSTCategoryID_editText = (EditText)findViewById(R.id.GSTCategoryID_editText);
                GSTNumber_editText = (EditText)findViewById(R.id.GSTNumber_editText);
//                ClientCode_editText = (EditText)findViewById(R.id.ClientCode_editText);
                Status_editText = (EditText)findViewById(R.id.Status_editText);
//                uname_editText = (EditText)findViewById(R.id.uname_editText);
//                password_editText = (EditText)findViewById(R.id.password_editText);
               // StateID_editText = (EditText)findViewById(R.id.StateID_editText);

                save = (Button)findViewById(R.id.save);
               // cancel = (Button) findViewById(R.id.cancel);
                back = (Button)findViewById(R.id.back);
                State_Spinner = (Spinner)findViewById(R.id.StateName);
                GSTCategory_Spinner = (Spinner)findViewById(R.id.GSTCategory_spinner);
                City_Spinner = findViewById(R.id.City_spinner);
                signatureImageView = (ImageView) findViewById(R.id.signatureImageView);

                State_Spinner.setOnItemSelectedListener(this);
                state_list = new ArrayList<>();
                state_list.add(new State("-- Choose State --"));

                stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state_list);
                stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stateAdapter.notifyDataSetChanged();
                State_Spinner.setAdapter(stateAdapter);


                GSTCategory_Spinner.setOnItemSelectedListener(this);


                 GSTCategory_list = new ArrayList<>();
                GSTCategory_list.add(new GSTCategory("--Choose GSTCategory--"));


                GSTCategory_Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GSTCategory_list);
                GSTCategory_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                GSTCategory_Adapter.notifyDataSetChanged();
                GSTCategory_Spinner.setAdapter(GSTCategory_Adapter);


                City_Spinner.setOnItemSelectedListener(this);

                City_list = new ArrayList<>();
                City_list.add(new City("--Choose City--"));
                CityAdapter = new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City_list);
                CityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                CityAdapter.notifyDataSetChanged();
                City_Spinner.setAdapter(CityAdapter);


                loadData();

 }

    private void loadData() {

        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.......");
        progressDialog.show();
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (MyUtil.OPERATION_EDIT_BUSINESS.equals(getIntent().getStringExtra(MyUtil.OPERATION))) {
                    loadDataToEdit();
                }
            }
        });

        final StringRequest gstCategoryRequest=new StringRequest(Request.Method.POST, gstcategory_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
                json = StringEscapeUtils.unescapeJson(json);
                try {


                    JSONObject jsonObject = new JSONObject(json);
                    String result = jsonObject.getJSONArray("Response").getJSONObject(0).getString("Response");
                    if ("Success".equals(result)) {

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            // String jsonString = object.toString();
                            Gson gson=new Gson();
                            GSTCategory gstCategory;
                            gstCategory = gson.fromJson(object.toString(), GSTCategory.class);
                            GSTCategory_list.add(gstCategory);
                        }
                            GSTCategory_Adapter.notifyDataSetChanged();
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

        StringRequest state_request=new StringRequest(Request.Method.POST, state_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
                json = StringEscapeUtils.unescapeJson(json);
                progressDialog.dismiss();
                try {


                    JSONObject jsonObject = new JSONObject(json);
                    String result = jsonObject.getJSONArray("Response").getJSONObject(0).getString("Response");
                    if ("Success".equals(result)) {

                        JSONArray jsonArray = jsonObject.getJSONArray("Data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            // String jsonString = object.toString();
                            Gson gson=new Gson();
                            State state;
                            state = gson.fromJson(object.toString(), State.class);
                            state_list.add(state);
                        }
                        stateAdapter.notifyDataSetChanged();
                        requestQueue.add(gstCategoryRequest);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Error Occured getting states", Toast.LENGTH_SHORT);
                    }


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

            requestQueue.add(state_request);                                    //add the request to the request queue

    }

//    private void loadDataToView(Business business) {
//
//
//
//        OrganizationName_editText.setText(business.getOrganizationName());
//        OwnerName_editText.setText(business.getOwnerName());
//        EmailID_editText.setText(business.getEmailID());
//        ContactNo_editText.setText(business.getContactNo());
//        Country_editText.setText(business.getCountry());
//        Address_editText.setText(business.getAddress());
//        Status_editText.setText(business.getStatus());
//        String stateValue = "Banglore";
//        if (stateValue != null) {
//            int spinnerPosition = State_Spinner.getSelectedItemPosition();
//            State_Spinner.setSelection(spinnerPosition);
//        }
//        String gst_Category= "GSTRegister";
//        if (gst_Category != null) {
//            int spinnerPosition = GSTCategory_Spinner.getSelectedItemPosition();
//            GSTCategory_Spinner.setSelection(spinnerPosition);
//        }
//       // String commercialCategory= "spico";
////        if (commercialCategory != null) {
////            int spinnerPosition = CityAdapter.getPosition(commercialCategory);
////            ComercialCotegory.setSelection(spinnerPosition);
////        }
////        Average_TurnOver.setText(business.getAverage_turn_over());
////        Average_Sales_Invoice.setText(business.getAverage_sales_invoice());
//        signatureImageView.setImageBitmap(DbBitmapUtility.getImage(business.getImageBytes()));
//
//
//    }

            private void loadDataToEdit() {


                int business_id = getIntent().getIntExtra("key", 0);

                editjson_url = "http://API.gstmadeeasy.net/POS_API.svc/GetBusinessList";

                final ProgressDialog progressDialog=new ProgressDialog(this);
                progressDialog.setMessage("Loading Data.......");
                progressDialog.show();


                String json_url = Uri.parse(editjson_url).buildUpon().appendQueryParameter("BusinessId",business_id+"").toString();
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
                                businessToSave = business;
                                businessId = business.getBusinessID();
                                OrganizationName_editText.setText(business.getOrganizationName());
                                OwnerName_editText.setText(business.getOwnerName());
                                ContactNo_editText.setText(business.getContactNo());
                                EmailID_editText.setText(business.getEmailID());
                                GSTNumber_editText.setText(business.getGSTNumber());
                                for (int j = 0; j < GSTCategory_list.size(); j++) {
                                    GSTCategory gstCategory = GSTCategory_list.get(j);
                                    if (business.getGSTCategoryID() == gstCategory.getID()) {
                                        GSTCategory_Spinner.setSelection(j);
                                        break;
                                    }
                                }

                                Address_editText.setText(business.getAddress());

                                for (int j = 0; j <state_list.size() ; j++) {
                                    State state = state_list.get(j);
                                    if (business.getStateID()==state.getStateID()){
                                        State_Spinner.setSelection(j);
                                    }
                                }

                                FullAddress_editText.setText(business.getFullAddress());
                                Status_editText.setText(business.getStatus());
//                                view_CLientCode.setText(business.getClientCode());


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

            }


    public  void  onSaveClick(View view){

            String OrganizationName = OrganizationName_editText.getText().toString();
            if(OrganizationName.isEmpty()){
                Toast.makeText(getApplicationContext()," Please enter the Organization Name",Toast.LENGTH_LONG).show();
                return;
            }

            String OwnerName = OwnerName_editText.getText().toString();
            if(OwnerName.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter Owner Name ",Toast.LENGTH_LONG).show();
                return;

            }


            String EmailID = EmailID_editText.getText().toString().trim();
            if(EmailID.isEmpty() || !isEmailvalid(EmailID)){
                Toast.makeText(getApplicationContext(),"Please enter your valid Email Id ",Toast.LENGTH_LONG).show();
                return;
            }

            String ContactNo = ContactNo_editText.getText().toString();
            if(ContactNo.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter your Contact Number ",Toast.LENGTH_LONG).show();
                return;
            }else if (ContactNo.length()!=10){
                Toast.makeText(getApplicationContext(),"Please enter your valid Contact Number ",Toast.LENGTH_LONG).show();
                return;
            }


            String GSTNumber = GSTNumber_editText.getText().toString();
            if(GSTNumber.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter the  GST number ",Toast.LENGTH_LONG).show();
                return;
            }

            if (GSTCategory_Spinner.getSelectedItemPosition()== 0) {
                Toast.makeText(getApplicationContext(),"Please select the GSTCategory ",Toast.LENGTH_SHORT).show();
                return;
            }



            String Address = Address_editText.getText().toString().trim();
            if(Address.isEmpty()) {
                Toast.makeText(getApplicationContext(),"Please enter the Address ",Toast.LENGTH_LONG).show();
                return;
            }
            String Country = Address_editText.getText().toString().trim();
            if(Country.isEmpty()) {
                Toast.makeText(getApplicationContext(),"Please enter the Address ",Toast.LENGTH_LONG).show();
                return;
            }

            if (State_Spinner.getSelectedItemPosition()== 0) {
                Toast.makeText(getApplicationContext(),"Please select the State",Toast.LENGTH_SHORT).show();
                return;
            }

    //        if (City_Spinner.getSelectedItemPosition()== 0) {
    //            Toast.makeText(getApplicationContext(),"Please select the City",Toast.LENGTH_SHORT).show();
    //            return;
    //        }


            String  FullAddress =  FullAddress_editText.getText().toString().trim();
            if(FullAddress.isEmpty()) {
                Toast.makeText(getApplicationContext(),"Please enter the Full Address ",Toast.LENGTH_LONG).show();
                return;
            }

    //        String GSTCategory = GSTCategoryID_editText.getText().toString().trim();
    //        if(GSTCategory.isEmpty()) {
    //            Toast.makeText(getApplicationContext(),"Choose Model ",Toast.LENGTH_LONG).show();
    //            return;
    //        }



            String Status = Status_editText.getText().toString();
            if(Status.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter your Status ",Toast.LENGTH_LONG).show();
                return;
            }


    //        String ClientCode = ClientCode_editText.getText().toString();
    //        if(ClientCode.isEmpty()){
    //            Toast.makeText(getApplicationContext(),"Please enter the ClientCode ",Toast.LENGTH_LONG).show();
    //            return;
    //        }




    //
    //        if(signBitmap == null) {
    //            Toast.makeText(getApplicationContext(),"Enter signature",Toast.LENGTH_LONG).show();
    //            return;
    //        }


            businessToSave.setBusinessID(businessId);
            businessToSave.setOrganizationName(OrganizationName);
            businessToSave.setOwnerName(OwnerName);
            businessToSave.setContactNo(ContactNo);
            businessToSave.setEmailID(EmailID);
            businessToSave.setGSTNumber(GSTNumber);
            GSTCategory gstCategory = (GSTCategory) GSTCategory_Spinner.getSelectedItem();
            businessToSave.setGSTCategoryID(gstCategory.getID());
            businessToSave.setAddress(Address);
            State state = (State)State_Spinner.getSelectedItem();
            businessToSave.setStateID(state.getStateID());
            City city = (City) City_Spinner.getSelectedItem();
            businessToSave.setStatus(Status);
    //        business.setCity(city.getCityName());
           // business.setStatus();
    //        business.setClientCode(ClientCode);
    //        business.setImageBytes(DbBitmapUtility.getBytes(signBitmap));

            saveApiCall(businessToSave);
//            super.onBackPressed();
    }

    private void saveApiCall(Business business) {

        final RequestQueue requestQueue= Volley.newRequestQueue(this);

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.......");
        progressDialog.show();

        Gson gson = new Gson();
        String json_string = gson.toJson(business);

        JSONObject jsonExportObject = new JSONObject();
        JSONArray jsonBusinessArray = new JSONArray();
        try {
            JSONObject jsonBusinessObject = new JSONObject(json_string);
            jsonBusinessArray.put(jsonBusinessObject);
            jsonExportObject.put("Business", jsonBusinessArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jsonBusinessArray.length() == 0) {
            Toast.makeText(this, "Error while converting", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = Uri.parse(savejson_url).buildUpon().appendQueryParameter("Business",jsonExportObject.toString()).toString();

        final StringRequest saveBusinessRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String json = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
                json = StringEscapeUtils.unescapeJson(json);
                try {


                    JSONObject jsonObject = new JSONObject(json);
                    String result = jsonObject.getJSONArray("Response").getJSONObject(0).getString("Response");
                    if ("Success".equals(result)) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
                        CreateBusiness_Activity.super.onBackPressed();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error Occured during save Business", Toast.LENGTH_SHORT);
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
        saveBusinessRequest.setRetryPolicy(new DefaultRetryPolicy( MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(saveBusinessRequest);
    }

                private boolean isEmailvalid(String s) {

                    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                    //String email1 = "user@domain.com";
                    Boolean b = s.matches(EMAIL_REGEX);

                    return b;
                }
                @Override
                public void onClick(View v) {
                    switch (v.getId())
                    {
                        case R.id.back :
                            super.onBackPressed();

                            break;
            //            case R.id.cancel :
            //                Toast.makeText(getApplicationContext(),"User cancel the process",Toast.LENGTH_LONG).show();
            //                startActivity(new Intent(CreateBusiness_Activity.this, SignatureActivity.class));
            //                break;
                        default:
                            break;
                    }
                }



                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

    public void getSignatureButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateBusiness_Activity.this);
        final View v = getLayoutInflater().inflate(R.layout.activity_signature, null);
        builder.setView(v);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Button clearButton = (Button) v.findViewById(R.id.clearButton);
        Button saveSignatureButton = (Button) v.findViewById(R.id.saveSignatureButton);

        final LinearLayout mContent = (LinearLayout) v.findViewById(R.id.canvasLayout);
        final Signature signature = new Signature(getApplicationContext(), mContent, saveSignatureButton, null);
        signature.setBackgroundColor(Color.WHITE);
        // Dynamically generating Layout through java code
        mContent.addView(signature, ViewGroup.LayoutParams.MATCH_PARENT, MATCH_PARENT);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature.clear();
            }
        });
        saveSignatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signBitmap =  signature.createBitmap();
                signatureImageView.setImageBitmap(signBitmap);
                dialog.dismiss();
            }
        });
    }
}

     class Signature extends View {

        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        public Path path = new Path();
        public Bitmap bitmap;
        Button saveSignatureButton;
        LinearLayout mContent;

        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public Signature(Context context, LinearLayout mContent, Button saveSignatureButton, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);

            this.mContent = mContent;
            this.saveSignatureButton = saveSignatureButton;
        }

        public Bitmap createBitmap() {
            mContent.setDrawingCacheEnabled(true);
            bitmap = Bitmap.createBitmap(mContent.getWidth(), mContent.getHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            mContent.draw(canvas);
            return bitmap;
        }

        public void clear() {
            path.reset();
            invalidate();
            saveSignatureButton.setEnabled(false);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            float eventX = event.getX();
            float eventY = event.getY();
            saveSignatureButton.setEnabled(true);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

                    lastTouchX = eventX;
                    lastTouchY = eventY;

                    return true;
                }

        private void debug(String string) {

            Log.v("log_tag", string);

        }

        private void expandDirtyRect(float historicalX, float historicalY) {
            if (historicalX < dirtyRect.left) {
                dirtyRect.left = historicalX;
            } else if (historicalX > dirtyRect.right) {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top) {
                dirtyRect.top = historicalY;
            } else if (historicalY > dirtyRect.bottom) {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }




