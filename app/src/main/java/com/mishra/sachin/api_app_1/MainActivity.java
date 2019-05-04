package com.mishra.sachin.api_app_1;

import android.app.DownloadManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mishra.sachin.api_app_1.POJO.Address;
import com.mishra.sachin.api_app_1.POJO.Company;
import com.mishra.sachin.api_app_1.POJO.Geo;
import com.mishra.sachin.api_app_1.POJO.Main_Pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url="https://jsonplaceholder.typicode.com/users";

    RequestQueue requestQueue;
    TextView textView;

    List<Main_Pojo> main_pojoList;
    ArrayList<Address> addressList=new ArrayList<Address>();

   // List<Company> companyList;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // textView=findViewById(R.id.textViewID);

        recyclerView=findViewById(R.id.recyclerviewID);

        requestQueue=Volley.newRequestQueue(MainActivity.this);



        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                String data="";

                try
                {

                     //here we are not using the Gson.

                    /*JSONArray jsonArray=new JSONArray(response);*/
/*
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

//here we can only get the values of main JSONObject like "username","id" etc..but we can not find the value of inner JSONObject
//like "street","city" because this is present in the another JSONOBject "address".

                    if(i==2)
                    {

                        data=data+"Username :"+jsonObject.getString("username")+"\nid : "
                                +jsonObject.getString("id")+"\n\n";
                      }

                      //this "if" will give the result of particular user.


                    }*/

//here we can get the values of inside JSONObject "address". to give the name of JSONObject "address".
/*

                  for (int i=0;i<jsonArray.length();i++)
                  {
                      JSONObject jsonObject1=jsonArray.getJSONObject(i);

                      JSONObject jsonObject2=jsonObject1.getJSONObject("address");

                      data=data+"\n\nstreet : "+jsonObject2.getString("street");
                  }
*/

/*
//here we can get the values of inside and inside JSONObject "geo". to give the name of JSONObject "geo"

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                        JSONObject jsonObject2=jsonObject1.getJSONObject("address");

                        JSONObject jsonObject3=jsonObject2.getJSONObject("geo");

                        data=data+"Latitude : "+jsonObject3.getString("lat")+"\n"+"Longitude : "+
                                jsonObject3.getString("lng")+"\n\n";

      //here "lat" and "lng" is key.while "address" and ""geo" are JSONObjects
                    }
                    */

/*
//like the JSONObject.""address"" here access the values of "company" JSONObject

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        JSONObject jsonObject2=jsonObject1.getJSONObject("company");

                        data=data+"\n Name : "+jsonObject2.getString("name")+"\n";

                    }
                    textView.setText(data);

*/



                    //Now Using the Gson and POJO Classses





                    Gson gson=new Gson();

                JSONArray jsonArray=new JSONArray(response);

/*//here we are accessing the all values like street, city etc via "MainPojo" class.*/


                    //here we are not using Recyclerview , so using "for loop" otherwise pass complete list
                    // in constructor of Adapter class.

//                main_pojoList= Arrays.asList(gson.fromJson(jsonArray.toString(),Main_Pojo[].class));
//
//                for (int i=0;i<main_pojoList.size();i++)
//                {
//                    data=data+"\nCity : "+main_pojoList.get(i).getAddress().getCity()+"\nStreet : "+main_pojoList.get(i).getAddress()
//                            .getStreet();
//                }


                Address address;

//here we are accessing the value of JSONObject of "address" directly by usiing "Address" class. by going in the JSONObject
// because array is not present in  JSONObject"address"

                /*for (int i=0;i<jsonArray.length();i++)
                {

                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    JSONObject jsonObject2=jsonObject1.getJSONObject("address");

                    address=gson.fromJson(jsonObject2.toString(),Address.class);

                    data=data+"\nCity : "+address.getCity();



                }*/

                //same in case of "geo"

                    Geo geo;
/*
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        JSONObject jsonObject2=jsonObject1.getJSONObject("address");
                        JSONObject jsonObject3=jsonObject2.getJSONObject("geo");

                        geo=gson.fromJson(jsonObject3.toString(),Geo.class);

                        data=data+"\nLongitude : "+geo.getLng()+"\nLattitude : "+geo.getLat();

                    }*/


                 ///   textView.setText(data);


                    //now we using the "RecyclerView//////

//here we are  mainpojo class with Recyclerview////

                    main_pojoList=Arrays.asList(gson.fromJson(jsonArray.toString(),Main_Pojo[].class));

                    MyAdapter adapter=new MyAdapter(MainActivity.this,main_pojoList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    recyclerView.setAdapter(adapter);


 //here we are sending the list, to adapter but JSONObject ""Address" , doesn't have an array , so can not pass the
 //list directly..so object of Address class is added in ArrayList , then pass in constructor of MyApapter().
 //it will be same in case of Geo and Company objects.

                 /*   for (int i=0;i<jsonArray.length();i++)
                {

                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    JSONObject jsonObject2=jsonObject1.getJSONObject("address");

                    address=gson.fromJson(jsonObject2.toString(),Address.class);

                    addressList.add(address);

                   // data=data+"\nCity : "+address.getCity();

                MyAdapter adapter=new MyAdapter(MainActivity.this,addressList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adapter);

                }*/


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Log.d("error",error.toString());
            }
        });

        requestQueue.add(stringRequest);
    }
}
