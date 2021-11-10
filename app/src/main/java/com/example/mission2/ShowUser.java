package com.example.mission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mission2.model.UserArray;
import com.example.mission2.model.User;

import java.util.ArrayList;

public class ShowUser extends AppCompatActivity {

    TextView Username, Userage, Useraddress;
    private User dataArray;
    int contain;
    Button edit, del;
    Toolbar toolbar;

    ArrayList<User> userss = UserArray.data;

    static String EXTRA_USERS = "extra";
//    static int EXTRA_POSITION = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        if (getIntent().getParcelableExtra(EXTRA_USERS) != null) {
            dataArray = getIntent().getParcelableExtra(EXTRA_USERS);
            contain = getIntent().getIntExtra("EXTRA_POSITION",0);
        }

        edit = findViewById(R.id.editbtn);
        del = findViewById(R.id.deletebtn);
        Username = findViewById(R.id.userName);
        Userage = findViewById(R.id.userAge);
        Useraddress = findViewById(R.id.userAddress);
        Username.setText(dataArray.getName());
        Userage.setText(dataArray.getAge());
        Useraddress.setText(dataArray.getAddress());
        toolbar = findViewById(R.id.toolBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ShowUser.this, MainActivity.class);
                    startActivity(intent);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowUser.this, AddUserActivity.class);
                intent.putExtra(AddUserActivity.EXTRA_USER, dataArray);
                startActivity(intent);

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userss.remove(contain);
//                Iterator<User> iter = UserArray.data.iterator();
//                while (iter.hasNext()) {
//                    User user = iter.next();
//                    if (user.name.equals(datanya1.getName())) {
//                        iter.remove();
//                    }
//                }
                Intent intent = new Intent(ShowUser.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
