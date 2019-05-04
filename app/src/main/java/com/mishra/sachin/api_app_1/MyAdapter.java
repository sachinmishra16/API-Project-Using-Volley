package com.mishra.sachin.api_app_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mishra.sachin.api_app_1.POJO.Address;
import com.mishra.sachin.api_app_1.POJO.Main_Pojo;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>
{
    Context context;
    List<Main_Pojo> main_pojoList;
    List<Address> addressList;

    public MyAdapter(Context context, List<Main_Pojo> main_pojoList) {
        this.context = context;
        this.main_pojoList = main_pojoList;
    }


    //this constructor is used to directly access the innner JSONObject's values.

    /*public MyAdapter(Context context, List<Address> addressList) {
        this.context = context;
        this.addressList = addressList;
    }*/

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.my_layout,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i)
    {

        String data="";

        data=data+"Name  :"+main_pojoList.get(i).getName()+"\nUser Name : "+main_pojoList.get(i).getUsername();

        //we can access directly via addresslist.
      //  data=data+addressList.get(i).getStreet();


        myHolder.textView.setText(data);
    }

    @Override
    public int getItemCount()
    {
             //return addressList.size();

        return main_pojoList.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView textView;

        public MyHolder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.textviewID);
        }
    }
}
