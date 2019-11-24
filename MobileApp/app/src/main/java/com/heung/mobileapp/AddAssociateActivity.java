package com.heung.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.heung.mobileapp.service.ServerAPIManager;


public class AddAssociateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_associate);
        getSupportActionBar().setTitle("Add Assistant");
    }

    public void AddAssociate(View view) throws Exception{
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String relationship = ((EditText)findViewById(R.id.relationship)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
        //Get UserID
        //get the email
        String email = CreateUserActivity.getEmail();
        System.out.println("_id is "+email);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    String userID = ServerAPIManager.getUserID(email).get("_id");
                    System.out.println("Users ID is " + userID);
                    ServerAPIManager.addAssociate(userID,name, relationship, phone);
                } catch (Exception e){
                    System.out.println("Error with adding associate");
                }
            }
        };
        thread.start();
        startActivity(new Intent(AddAssociateActivity.this, MainActivity.class));

    }
}