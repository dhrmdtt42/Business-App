package com.gstmadeeasy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gstmadeeasy.Activity.CreateBusiness_Activity;
import com.gstmadeeasy.Activity.ViewCustomerDetails;
import com.gstmadeeasy.Model.Business;
import com.gstmadeeasy.R;
import com.gstmadeeasy.Utils.MyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dharam on 12/26/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public ArrayList<Business> businessList;
    public Context mcontext;
    private  UserAdapter.OnClickListener onClickListener;
   // ImageView signImage;




    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.user_details,null);
        //UserViewHolder holder = new UserViewHolder(view);
        return  new UserViewHolder(view);


    }

    public UserAdapter(ArrayList<Business> businessList, Context context, UserAdapter.OnClickListener onClickListener){
        this.businessList = businessList;
        mcontext=context;
        this.onClickListener = onClickListener;

    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

           final Business business = businessList.get(position);

            holder.OrganizationName_editText.setText(business.getOrganizationName());
            holder.OwnerName_editText.setText(business.getOwnerName());
//            holder.edit_Address.setText(business.getAddress());
            holder.EmailID_editText.setText(business.getEmailID());
            holder.ContactNo_editText.setText(business.getContactNo());
//            holder.Address_editText.setText(business.getAddress());
//            holder.bind(business);
//            holder.signImage.setImageBitmap(DbBitmapUtility.getImage(business.getImageBytes()));
           // holder.signImage.setImageResource(R.id.imageView1);
        //    holder.edit_Address.setText(business.getAddress());

        holder.edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mcontext,"you can edit",Toast.LENGTH_LONG).show();
                Intent editIntent= new Intent(mcontext, CreateBusiness_Activity.class);

                editIntent.putExtra("key", business.getBusinessID());
                editIntent.putExtra(MyUtil.OPERATION, MyUtil.OPERATION_EDIT_BUSINESS);
                mcontext.startActivity(editIntent);


            }
        });

//        holder.delete_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent deleteIntent = new Intent(mcontext, CreateBusiness_Activity.class);
//
//                Toast.makeText(mcontext,"you can delete",Toast.LENGTH_LONG).show();
//            }
//        });

//        holder.view_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(mcontext,"you can view your details",Toast.LENGTH_LONG).show();
//                Intent viewIntent = new Intent(mcontext,ViewCustomerDetails.class);
//
//                viewIntent.putExtra("Action", "view");
//                viewIntent.putExtra("key", String.valueOf(business.getBusinessID()));
//
//                mcontext.startActivity(viewIntent);
//
//            }
//        });

        holder.view_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemCLick(business);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onItemCLick(business);
            }
        });



    }

    @Override
    public int getItemCount() {

        return businessList.size();
    }

    public void setFilter(ArrayList<Business> newList) {
        this.businessList = new ArrayList<>();
        this.businessList.addAll(newList);
        notifyDataSetChanged();
    }

    public interface OnClickListener {
        void  onItemCLick(Business business);
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView Status_editText,OrganizationName_editText,OwnerName_editText,EmailID_editText,ContactNo_editText,Address_editText;
        ImageView edit_img, delete_img, view_img,signImage;
        View itemView;
        public UserViewHolder(View itemView) {
            super(itemView);

            OrganizationName_editText = itemView.findViewById(R.id.OrganizationName_editText);
           // edit_Address = itemView.findViewById(R.id.edit_Address);
            OwnerName_editText = itemView.findViewById(R.id.OwnerName_editText);
            EmailID_editText  = itemView.findViewById(R.id.EmailID_editText);
            ContactNo_editText = itemView.findViewById(R.id.ContactNo_editText);
//            Address_editText = itemView.findViewById(R.id.Address_editText);
//            Status_editText = itemView.findViewById(R.id.Status_editText);

            edit_img = itemView.findViewById(R.id.img1);
            //delete_img = itemView.findViewById(R.id.img2);
            view_img = itemView.findViewById(R.id.img3);
            //signImage = itemView.findViewById(R.id.signatureImageView);
            this.itemView = itemView;
        }

//        public void bind(Business business) {
//            onClickListener.onItemCLick(business);
//        }


    }
}
