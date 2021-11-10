package com.example.mission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.mission2.model.UserArray;
import com.example.mission2.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Iterator;

public class AddUserActivity extends AppCompatActivity implements TextWatcher {
    TextInputLayout inputName, inputAge, inputAddress;
    TextInputEditText nameTxt, ageTxt, addressTxt;
    Button btn;
    String name, address, age;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    private User cdata;
    static String EXTRA_USER = "extra";
    static int EXTRA_POSITION = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        nameTxt = findViewById(R.id.namet);
        ageTxt = findViewById(R.id.aget);
        addressTxt = findViewById(R.id.addresst);
        inputName = findViewById(R.id.input_name);
        inputAge = findViewById(R.id.input_age);
        inputAddress = findViewById(R.id.input_address);
        btn = findViewById(R.id.button_tambah);
        inputName.getEditText().addTextChangedListener(this);
        inputAge.getEditText().addTextChangedListener(this);
        inputAddress.getEditText().addTextChangedListener(this);
        toolbar = findViewById(R.id.toolBar);
        if (getIntent().getParcelableExtra(EXTRA_USER) != null) {
            cdata = getIntent().getParcelableExtra(EXTRA_USER);
            nameTxt.setText(cdata.getName());
            ageTxt.setText(cdata.getAge());
            addressTxt.setText(cdata.getAddress());
            toolbar.setTitle("Edit User");
            btn.setText("Update User");
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getParcelableExtra(EXTRA_USER) == null) {
                    Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(AddUserActivity.this, ShowUser.class);
                    intent.putExtra(ShowUser.EXTRA_USERS, cdata);
                    startActivity(intent);
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getParcelableExtra(EXTRA_USER) == null) {
                    progressDialog = new ProgressDialog(AddUserActivity.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.activity_load);
                    UserArray.data.add(new User(name, address, age));
                    Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    if (!cdata.getName().equals(name) || !cdata.getAddress().equals(address) || !cdata.getAge().equals(age)) {
                        Iterator<User> userIterator = UserArray.data.iterator();
                        while (userIterator.hasNext()) {
                            User user = userIterator.next();
                            if (user.name.equals(cdata.getName())) {
                                userIterator.remove();
                            }
                        }
                        progressDialog = new ProgressDialog(AddUserActivity.this);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.activity_load);
                        UserArray.data.add(new User(name, address, age));
                        Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        name = inputName.getEditText().getText().toString().trim();
        age = inputAge.getEditText().getText().toString().trim();
        address = inputAddress.getEditText().getText().toString().trim();
        if (!name.isEmpty() && !address.isEmpty() && !age.isEmpty()) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

