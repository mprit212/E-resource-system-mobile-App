package com.example.e_resourcessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendMail extends AppCompatActivity {

    Button btngetpass;
    EditText Etname,Etemail;
    TextView tvpass;
    String NAME=null;
    String EMAIL=null;
    String PASSWORD=null;
    Databasehelper dbhelp=new Databasehelper(this);
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btngetpass=findViewById(R.id.buttonGETPASS) ;
        Etemail=findViewById(R.id.editTextEmail);
        Etname=findViewById(R.id.editTextName);
        tvpass=findViewById(R.id.tvPass);
btngetpass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if (validate())
        {
           sendMail();
        }
        else {
            Toast.makeText(getApplicationContext(),"User isn't Exists",Toast.LENGTH_LONG).show();
        }
    }
});

    }

    private void sendMail() {

        PASSWORD=tvpass.getText().toString().trim();
        String mail = Etemail.getText().toString().trim();
        String message = "Hello "+NAME +" ,Your Password is  "+PASSWORD +"  BY : E-RESOURCES SYSTEM :)";
        String subject = "Password of E-RESOURCES SYSTEM";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);

        javaMailAPI.execute();

    }

    public boolean validate()
    {
        NAME=Etname.getText().toString();
        EMAIL=Etemail.getText().toString();

        db=dbhelp.getReadableDatabase();
////sid INTEGER primary key autoincrement,snm TEXT,email TEXT,phnum INTEGER, dob DATE, eno INTEGER,pass TEXT
        Cursor cursor=db.rawQuery("select * from tblstu where snm=? and email=?",new String[]{NAME,EMAIL});

        if (cursor.moveToNext())
        {
            tvpass.setText(cursor.getString(cursor.getColumnIndex("pass")));
            return  true;
        }

        else
        {
            return  false;
        }

    }
}
