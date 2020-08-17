package com.example.e_resourcessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btn_login,btn_reg;
    EditText etemail,etpass;
     TextView tvname,tvopen;


    Databasehelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login=findViewById(R.id.btn_login);
        btn_reg=findViewById(R.id.btn_reg);
        etemail=findViewById(R.id.etmail);
        etpass=findViewById(R.id.etpass);
        tvname=findViewById(R.id.tvname);
        tvopen=findViewById(R.id.tvopen);
        mydb=new Databasehelper(this);
        SharedPreferences obj=getSharedPreferences("unm",MODE_PRIVATE);
        if(obj.getBoolean("logged",false))
        {
            Intent i=new Intent(getApplicationContext(),Home.class);
            startActivity(i);
        }

        tvopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iv=new Intent(getApplicationContext(),SendMail.class);
                startActivity(iv);
            }
        });

    }

    public void btnreg(View view)
    {
        Intent i =new Intent(getApplicationContext(),Regstration.class);
        startActivity(i);

    }

    public void btnlogin(View view)
    {
        if (etemail.length()==0)
        {
            etemail.setError("Enter Email Here");
        }
        else if (etpass.length()==0)
        {
            etpass.setError("Enter Password Here");
        }

        else {
            boolean login = mydb.checkuser(etemail.getText().toString(), etpass.getText().toString());

            if (login) {
                Toast.makeText(getApplicationContext(), "Login Sucessfull", Toast.LENGTH_LONG).show();
                SharedPreferences obj = getSharedPreferences("unm", MODE_PRIVATE);
                SharedPreferences.Editor editor = obj.edit();
                editor.putString("EMAIL", etemail.getText().toString());
                editor.putBoolean("logged", true);
                editor.commit();

                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "login fail", Toast.LENGTH_LONG).show();
            }
        }
    }
}
