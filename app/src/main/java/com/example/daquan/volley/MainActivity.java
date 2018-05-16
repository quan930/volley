package com.example.daquan.volley;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String url = "http://10.1.33.164:8080/getTemp";
    private String urlImage = "https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=8120f1aa4d4a20a425133495f13bf347/3b87e950352ac65cd20ecfcbf9f2b21193138a7b.jpg";
    private ImageView imageView;
    private TextView jsonTextView;
    private TextView stringTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        jsonTextView = findViewById(R.id.quantext);
        stringTextView = findViewById(R.id.quanString);

        JsonObjectRequest jsonObjectRequest;
        StringRequest stringRequest;
        ImageRequest imageRequest;



        //StringRequest
        Response.Listener<String> stringListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                stringTextView.setText(s);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        };
        stringRequest = new StringRequest(Request.Method.POST,url,stringListener,errorListener);
        Volley.newRequestQueue(this).add(stringRequest);


        //JsonObjectRequest
        Response.Listener<JSONObject> objectListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String time = null;
                try {
                    time = jsonObject.getString("time");
                    jsonTextView.setText(time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        JSONObject jsonObject = null;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,jsonObject,objectListener,errorListener);
        Volley.newRequestQueue(this).add(jsonObjectRequest);

        //
        Response.Listener<Bitmap> bitmapListener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        };
        imageRequest = new ImageRequest(urlImage,bitmapListener,0,0,null,null,errorListener);
        Volley.newRequestQueue(this).add(imageRequest);
    }
}
