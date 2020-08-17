package com.example.e_resourcessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginOtp extends AppCompatActivity {

    EditText EDNAME,EDMOBILENO;
    Button btnverify;
    Databasehelper dbhelp=new Databasehelper(this);
    SQLiteDatabase db;
    String NAME="";
    String NUMBER="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otp);
        EDNAME=findViewById(R.id.ETNAME);
        EDMOBILENO=findViewById(R.id.ETMOBILENO);
        btnverify=findViewById(R.id.btnverify);


    }

    public void btnverify(View view)
    {
        if (validate())
        {
            Toast.makeText(getApplicationContext(),"User doesn't exsits in system",Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(getApplicationContext(),"User doesn't exsits in system",Toast.LENGTH_LONG).show();
        }
    }

    public  boolean validate()
    {
        if (EDNAME.length()!=0 && EDMOBILENO.length()!=0)
        {
            NAME=EDNAME.getText().toString();
            NUMBER=EDMOBILENO.getText().toString();
            db = dbhelp.getWritableDatabase();
////sid INTEGER primary key autoincrement,snm TEXT,email TEXT,phnum INTEGER, dob DATE, eno INTEGER,pass TEXT
            Cursor cursor = db.rawQuery("select * from tblstu where snm=? and phnum=?", new String[]{NAME,NUMBER});

            if(cursor.moveToNext())
            {
                return true;
            }

            else
            {
                return false;
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Please Enter Proper Values",Toast.LENGTH_LONG).show();
            return  false;
        }
    }
}
