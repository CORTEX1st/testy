package com.example.testfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView nearby,list,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nearby = (CardView) findViewById(R.id.nearby);
        list = (CardView) findViewById(R.id.list);
        about = (CardView) findViewById(R.id.about);

        nearby.setOnClickListener(this);
        list.setOnClickListener(this);
        about.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.nearby : i = new Intent(this,mapsactivity.class);startActivity(i); break;

            case R.id.list : i = new Intent(this,listactivity.class);startActivity(i); break;

            case R.id.about : i = new Intent(this,aboutactivity.class);startActivity(i); break;
        }
    }
}
