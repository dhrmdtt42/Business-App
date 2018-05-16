package com.gstmadeeasy.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.gstmadeeasy.Adapter.UserAdapter;
import com.gstmadeeasy.Model.Business;
import com.gstmadeeasy.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    ArrayList<Business> userList;
    Context context;
    public String json_url;
    //Business business;
    RecyclerView.LayoutManager layoutManager;
    ImageView signImage;

    private  UserAdapter.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        userList = new ArrayList<>();


        json_url="http://API.gstmadeeasy.net/POS_API.svc/GetBusinessList?BusinessID=0";



        recyclerView  = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter  = new UserAdapter( userList,this, onClickListener);


        recyclerView.setAdapter(userAdapter);

        //final SharedPreferences sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
//        //-------------------------------Signature feature--------------------------->>
//
//        String image_path = getIntent().getStringExtra("imagePath");
//        Bitmap bitmap = BitmapFactory.decodeFile(image_path);
//        signImage.setImageBitmap(bitmap);

    }

    @Override
    protected void onResume() {
        super.onResume();
        userList.clear();
     //   userList.addAll(dataBaseHandler.getAllCustomer());
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            //case R.id.id_new:
                //Intent in = new Intent(getApplicationContext(),CreateBusiness_Activity.class);
               // startActivity(new Intent(getApplicationContext(),CreateBusiness_Activity.class).putExtra("Action","new"));
               // break;
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


}
