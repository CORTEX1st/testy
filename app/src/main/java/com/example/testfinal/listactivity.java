package com.example.testfinal;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class listactivity extends AppCompatActivity {

RecyclerView mRecycleView;
DatabaseReference mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);

        mRecycleView = findViewById(R.id.recycleview);
        mRecycleView.setHasFixedSize(true);

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("hospital");
        mFirebaseDatabase.keepSynced(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<model,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model, ViewHolder>(
                        model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mFirebaseDatabase
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, model model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getDescription(),model.getImage());

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnclickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                String mTitle = getItem(position).getTitle();
                                String mDescription = getItem(position).getDescription();
                                String mImage = getItem(position).getImage();

                                Intent intent = new Intent(view.getContext(),ListDetail.class);

                                intent.putExtra("nama", mTitle);
                                intent.putExtra("keterangan",mDescription);
                                intent.putExtra("gambar",mImage);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }
                };

        mRecycleView.setAdapter(firebaseRecyclerAdapter);
    }
}
