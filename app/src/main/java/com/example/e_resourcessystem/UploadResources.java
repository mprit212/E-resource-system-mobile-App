package com.example.e_resourcessystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UploadResources extends AppCompatActivity {

    Button btnsubmit,btnclear,btnpast;
    EditText et_res,et_resdescription;
    Spinner sp_year,sp_sem,sp_subject,sp_type;
    AutoCompleteTextView actv_res;
    String YEAR,SEM,SUBJECT,TYPE,name,RES,RES_DES;
    Databasehelper mydb;


    private ClipboardManager clipboardManager;
    private ClipData clipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_resources);
        getSupportActionBar().setTitle("Upload Resources");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final  String [] year={"SELECT YEAR","F.Y","S.Y","T.Y","MSC-1","MSC-2"};
        final  String [] sem ={"SELECT SEM","1","2","3","4","5","6"};
        final  String [] type={"SELECT RES. TYPE","PDF","PPT","WEBSITE","VIDEO","OTHER"};
        final  String [] subject={"SELECT SUBJECT","HTML","CSS","C#","ASP.NET","C/C++","CLOUD COM.","JAVA","PHP","ANDROID","SE","NETWORKING","CG","PYTHON","DS"};

        mydb=new Databasehelper(this);
        btnsubmit=findViewById(R.id.btnsubmit);
        btnpast=findViewById(R.id.btnpast);
        btnclear=findViewById(R.id.btnclear);
        et_res=findViewById(R.id.et_resource);
        clipboardManager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        et_resdescription=findViewById(R.id.et_resdescri);

        btnpast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipData abc=clipboardManager.getPrimaryClip();
                ClipData.Item  item=abc.getItemAt(0);

                String txt=item.getText().toString();
                et_res.setText(txt);
            }
        });

        // YEAR //
        sp_year=findViewById(R.id.sp_year);

        ArrayAdapter<String> adp1=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,year);
        sp_year.setAdapter(adp1);
        sp_year.setSelection(0);

        sp_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                 YEAR= year[position].toString();
            }

            @Override
             public void onNothingSelected(AdapterView<?> parent)  {

            }
        });

        // SEM //

        sp_sem=findViewById(R.id.sp_sem);


        ArrayAdapter<String> adp2=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,sem);
        sp_sem.setAdapter(adp2);

        sp_sem.setSelection(0);

        sp_sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                 SEM=sem[position].toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
           // SUBJECT //
        sp_subject=findViewById(R.id.sp_subject);

        ArrayAdapter<String> adp3=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,subject);
        sp_subject.setAdapter(adp3);
        sp_subject.setSelection(0);

        sp_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                 SUBJECT=subject[position].toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //TYPE //
        sp_type=findViewById(R.id.sp_type);

        ArrayAdapter<String> adp4=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,type);
        sp_type.setAdapter(adp4);
        sp_type.setSelection(0);

        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                TYPE=type[position].toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.account :
             //   Toast.makeText(this,"MY PROFILE",Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(getApplicationContext(),Update_Profile.class);
                startActivity(intent1);
                return  true;

            case R.id.logout :
                SharedPreferences preferences=getSharedPreferences("unm",MODE_PRIVATE);
                SharedPreferences.Editor editor =preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
                // Toast.makeText(this,"LOGOUT",Toast.LENGTH_LONG).show();
                return  true;

            case R.id.aboutus :
                Toast.makeText(this,"ABOUT US",Toast.LENGTH_LONG).show();
                return  true;

            default:
                return super.onOptionsItemSelected(item);


        }

    }

    public void btnsubmit(View view)
    {
        if (et_res.length()==0)
        {
            et_res.setError("Enter Resource");
        }
        else  if (et_resdescription.length()==0)
        {
            et_resdescription.setError("Enter Resource Description");
        }
        else {
            RES = et_res.getText().toString();
            RES_DES = et_resdescription.getText().toString();
            SharedPreferences obj = getSharedPreferences("unm", MODE_PRIVATE);

            name = obj.getString("EMAIL", " ");


            boolean insertdata = mydb.InsertData(YEAR, SEM, SUBJECT, RES, RES_DES, TYPE, name);

            if (insertdata) {
                Toast.makeText(getApplicationContext(), "Upload Sucessfully", Toast.LENGTH_LONG).show();
                sp_year.setSelection(0);
                sp_sem.setSelection(0);
                sp_type.setSelection(0);
                sp_subject.setSelection(0);
                et_res.setText(null);
                et_resdescription.setText(null);
                name = null;
            } else {
                Toast.makeText(getApplicationContext(), "Not Uploaded", Toast.LENGTH_LONG).show();
            }
        }
    }



}
