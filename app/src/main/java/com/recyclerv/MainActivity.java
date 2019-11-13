package com.recyclerv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //    private final String url = "https://api.github.com/search/users?q=dreamt";
    private RequestQueue mQueue;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private ArrayList<ExampleItem> exampleList;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        button = findViewById(R.id.bb);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                api();
//            }
//        });

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        exampleList = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);

        String url = "https://api.github.com/search/users?q=dreamt";
        Log.i("url : ", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);

//  Should using imageLink .png
                        String imageUrl = item.getString("url");
                        String name = item.getString("login");
                        int id = item.getInt("id");

                        //exampleList.add(new ExampleItem(imageUrl,name,id)); ตัวแปรอาเรย์.add(new ชื่อ class_item(ชื่อตัวแปรที่ดึงข้อมุลมาจาก api,..,..));
                        exampleList.add(new ExampleItem(imageUrl, name, id));
                    }
                    mAdapter = new ExampleAdapter(MainActivity.this, exampleList);
                    mRecyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }


}