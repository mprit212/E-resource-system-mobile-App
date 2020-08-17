package com.example.e_resourcessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileUpdatemain extends AppCompatActivity {
EditText etname,etemail,etnum,etdob,eteum;
    Databasehelper dbhelp=new Databasehelper(this);
    SQLiteDatabase db;
    String SNM;
    String SID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_updatemain);
        etname=findViewById(R.id.etname);
        etemail=findViewById(R.id.etmail);
        etdob=findViewById(R.id.etbirth);
        eteum=findViewById(R.id.eteno);
        etnum=findViewById(R.id.etnum);

        Bundle bundle=getIntent().getExtras();
        SID=bundle.getString("ID");

        getSupportActionBar().setTitle("Update Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences obj= getSharedPreferences("unm",MODE_PRIVATE);

        SNM=obj.getString("EMAIL"," ");

    }

    public void Clear(View view)
    {
        etnum.setText(null);
        eteum.setText(null);
        etdob.setText(null);
        etemail.setText(null);
        etname.setText(null);
    }

    public void Update(View view)
    {

       db=dbhelp.getWritableDatabase();
    boolean isupdate = dbhelp.update(etname.getText().toString(),etemail.getText().toString(),etnum.getText().toString(),etdob.getText().toString(),eteum.getText().toString(),SID);

    if (isupdate)
    {
        Toast.makeText(getApplicationContext(),"Updated Sucessfully",Toast.LENGTH_LONG).show();
    }
    else
    {
        Toast.makeText(getApplicationContext(),"Not Updated",Toast.LENGTH_LONG).show();
    }



    }
    protected  void  onResume() {


        getres();
        super.onResume();
    }

    private void getres()
    {
        db=dbhelp.getWritableDatabase();
        // tv_name=tv_dob=tv_email=tv_enum=tv_num=null;
        Cursor cursor=db.rawQuery("Select * from tblstu where  snm= ?",new String[] {SNM});
//sid INTEGER primary key autoincrement,snm TEXT,email TEXT,phnum INTEGER, dob DATE, eno INTEGER,pass TEXT
        if(cursor.moveToNext())
        {
            do {

                etname.setText(cursor.getString(cursor.getColumnIndex("snm")));
                etemail.setText(cursor.getString(cursor.getColumnIndex("email")));
                etnum.setText(cursor.getString(cursor.getColumnIndex("phnum")));
                eteum.setText(cursor.getString(cursor.getColumnIndex("eno")));
                etdob.setText(cursor.getString(cursor.getColumnIndex("dob")));

            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
