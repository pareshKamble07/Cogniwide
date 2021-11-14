package com.example.mvvmrxjava.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvmrxjava.R;
import com.example.mvvmrxjava.utils.Util;

public class LoginActivity extends AppCompatActivity {
    
    private EditText edt_email,edt_password;
    private Button btn_submit;
    
    private String str_email,str_pass;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_submit = findViewById(R.id.btn_submit);
        
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
        
    }

    private void validation() {

        str_pass = edt_password.getText().toString().trim();
        str_email = edt_email.getText().toString().trim();

        if (str_email.equals("")) {
            Util.showToastMessage(this, "Enter Email id");
        } else if (str_pass.equals("")) {
            Util.showToastMessage(this, "Enter password");
        } else if (Util.isValidEmailId(str_email) == false) {
            Toast.makeText(getApplicationContext(), "InValid Email Address", Toast.LENGTH_SHORT).show();
        } else if (Util.isValidMobile(str_pass) == false) {
            Toast.makeText(getApplicationContext(), "InValid password", Toast.LENGTH_SHORT).show();
        } else if (str_pass.equals("")) {
            Util.showToastMessage(this, "Enter Pass");
        }else
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }


}