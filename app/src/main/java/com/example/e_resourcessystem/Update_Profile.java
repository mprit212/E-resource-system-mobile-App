package com.example.e_resourcessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Update_Profile extends AppCompatActivity {

    TextView tv_name,tv_email,tv_num,tv_dob,tv_enum,tvid;
    Databasehelper dbhelp=new Databasehelper(this);
    SQLiteDatabase db;
    String SNM=null;
    String SID=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__profile);
        tv_name=findViewById(R.id.tv_name);
        tv_email=findViewById(R.id.tv_mail);
        tv_num=findViewById(R.id.tv_num);
        tv_dob=findViewById(R.id.tv_birth);
        tv_enum=findViewById(R.id.tv_eno);
        tvid=findViewById(R.id.tvid);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences obj= getSharedPreferences("unm",MODE_PRIVATE);

        SNM=obj.getString("EMAIL"," ");

    }
    protected  void  onResume() {


        getdaata();
        super.onResume();
    }

    private  void  getdaata()
            
    {
        db=dbhelp.getWritableDatabase();
       // tv_name=tv_dob=tv_email=tv_enum=tv_num=null;
        Cursor cursor=db.rawQuery("Select * from tblstu where  snm= ?",new String[] {SNM});
//sid INTEGER primary key autoincrement,snm TEXT,email TEXT,phnum INTEGER, dob DATE, eno INTEGER,pass TEXT
        if(cursor.moveToNext())
        {
            do {
                tvid.setText(cursor.getString(cursor.getColumnIndex("sid")));
                tv_name.setText("Name: " +cursor.getString(cursor.getColumnIndex("snm")));
                tv_email.setText("Email: "+ cursor.getString(cursor.getColumnIndex("email")));
                tv_num.setText("Phone No.: "+ cursor.getString(cursor.getColumnIndex("phnum")));
                tv_enum.setText("Enrollment No.: " +cursor.getString(cursor.getColumnIndex("eno")));
                tv_dob.setText("D.O.B : "+ cursor.getString(cursor.getColumnIndex("dob")));

            }while (cursor.moveToNext());
        }
        SID=tvid.getText().toString();
        cursor.close();
    }

    public void btnupdateprofile(View view)
    {
        Intent i=new Intent(getApplicationContext(),ProfileUpdatemain.class);
        i.putExtra("ID",SID);
        startActivity(i);
    }


}
