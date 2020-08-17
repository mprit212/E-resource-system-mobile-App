package com.example.e_resourcessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Regstration extends AppCompatActivity {

    Databasehelper mydb;
    EditText et_name,et_email,et_num,et_dob,et_eno,et_pass;
    Button btnsubmit,btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstration);
        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_mail);
        et_num=findViewById(R.id.et_num);
        et_dob=findViewById(R.id.et_birth);
        et_eno=findViewById(R.id.et_eno);
        et_pass=findViewById(R.id.et_pass);
        btnlogin=findViewById(R.id.btnlogin);
        btnsubmit=findViewById(R.id.btnsubmit);
        mydb=new Databasehelper(this);
       // getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void btn_insert(View view)
    {
        if (et_name.length()==0)
        {
            et_name.setError("Name is Required");
        }
        else if (et_email.length()==0)
        {
            et_email.setError("Email is Required");
        }
        else if (et_num.length()==0)
        {
            et_num.setError("Phone No. is Required");
        }
        else if (et_dob.length()==0)
        {
            et_dob.setError("D.O.B is Required");
        }
        else if (et_eno.length()==0)
        {
            et_eno.setError("Enrollment No. is Required");
        }
        else if (et_pass.length()==0)
        {
            et_pass.setError("Password is Required");
        }

        else {

            boolean insert = mydb.insertstudent(et_name.getText().toString(), et_email.getText().toString(), et_num.getText().toString(), et_dob.getText().toString(), et_eno.getText().toString(), et_pass.getText().toString());

            if (insert) {
                Toast.makeText(getApplicationContext(), "Register Sucessfully", Toast.LENGTH_LONG).show();
                et_name.setText(null);
                et_email.setText(null);
                et_num.setText(null);
                et_dob.setText(null);
                et_eno.setText(null);
                et_pass.setText(null);
            } else {
                Toast.makeText(getApplicationContext(), "Please Try Again", Toast.LENGTH_LONG).show();
            }
        }
        }

    public void btn_login(View view)
    {
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
}
