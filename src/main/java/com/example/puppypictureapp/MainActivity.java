package com.example.puppypictureapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;

public class MainActivity extends SimpleActivity {
    private static final String WEBSITE_PAGE="http://www.martystepp.com/dogs/";
    private String [] lines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Ion.with(this)
                .load("http://www.martystepp.com/dogs/files.txt")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        log(result);
                        puppyList(result);
                    }
                });
       Spinner spin = $(R.id.spinner);
       spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                puppyLoad(lines[position]);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }
    public void puppyList(String result){
         lines = result.split("\n");
        SimpleList.with(this).setItems(R.id.spinner,lines);
    }

    public void clickme_click(View view){
        // Aniamation
        TextView tv = $(R.id.head);
        Button butt = $(R.id.clickme);
        YoYo.with(Techniques.FlipInX)
                .duration(5000)
                .playOn(tv);
        YoYo.with(Techniques.BounceInUp)
                .duration(2000)
                .playOn(butt);
        //picasso
        ImageView imag = $(R.id.pic);
        Picasso.get()
                .load("http://www.martystepp.com/dogs/daisy-03.jpg")
                .resize(1000,1000)
                .placeholder(R.drawable.loading)
                .into(imag);
    }
    public void puppyLoad(String picture){
        ImageView imag = $(R.id.pic);
        Picasso.get()
                .load("http://www.martystepp.com/dogs/"+picture)
                .fit()
                .placeholder(R.drawable.loading)
                .into(imag);
    }
}
