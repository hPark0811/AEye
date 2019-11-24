package com.heung.mobileapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.heung.mobileapp.service.ServerAPIManager;

public class CreateUserActivity extends AppCompatActivity {
    static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
    public void CreateUser(View view) throws  Exception{
        try{
            email = ((EditText)findViewById(R.id.email)).getText().toString();
            String password = ((EditText)findViewById(R.id.password)).getText().toString();
            String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
            System.out.println(email + password + phone);
            ServerAPIManager.addUser(email, password, phone);
        }catch (Exception e){
            System.out.println(e);
        }
        setAssociate();

    }
    public static String getEmail(){
        return email;
    }
    public void setAssociate(){
        setContentView(R.layout.activity_add_associate);
        startActivity(new Intent(CreateUserActivity.this, AddAssociateActivity.class));
    }

}