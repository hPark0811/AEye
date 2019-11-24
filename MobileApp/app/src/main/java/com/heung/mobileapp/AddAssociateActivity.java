package com.heung.mobileapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.heung.mobileapp.service.ServerAPIManager;


public class AddAssociateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void AddAssociate(View view) throws Exception{
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String relationship = ((EditText)findViewById(R.id.relationship)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
        //Get UserID
        //get the email
        String email = CreateUserActivity.getEmail();
        String userID = ServerAPIManager.getUserID(email).get("userID");
        ServerAPIManager.addAssociate(userID,name, relationship, phone);

    }
}