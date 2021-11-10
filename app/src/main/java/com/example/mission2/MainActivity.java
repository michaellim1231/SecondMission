package com.example.mission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mission2.adapter.MainAdapter;
import com.example.mission2.model.UserArray;
import com.example.mission2.model.User;
import com.example.mission2.utils.itemClickSupport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recView;
    RecyclerView.LayoutManager layManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<User> usrArrayList;
    public boolean aCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usrArrayList = UserArray.data;
        fab = findViewById(R.id.addUser);
        recView = findViewById(R.id.recycler_view);
        mAdapter = new MainAdapter(usrArrayList);
        layManager = new LinearLayoutManager(this);
        recView.setLayoutManager(layManager);
        recView.setAdapter(mAdapter);
        recView.setHasFixedSize(true);
        itemClickSupport.addTo(recView).setOnItemClickListener(new itemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(MainActivity.this, ShowUser.class);
                intent.putExtra("EXTRA_POSITION", position);
                intent.putExtra(ShowUser.EXTRA_USERS, usrArrayList.get(position));
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        this.aCheck = false;
    }

    @Override
    public void onBackPressed(){
        if(aCheck){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(a);
        } else{
            Toast.makeText(MainActivity.this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        this.aCheck = true;
    }

}