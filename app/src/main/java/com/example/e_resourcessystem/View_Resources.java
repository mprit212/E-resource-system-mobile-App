package com.example.e_resourcessystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class View_Resources<LVVIEW> extends AppCompatActivity {

    Databasehelper dbhelp=new Databasehelper(this);
    SQLiteDatabase db;
    String url=null;
    String RES=null;
    String SNM=null;

    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Sem = new ArrayList<String>();
    private ArrayList<String> Subject = new ArrayList<String>();
    private ArrayList<String> Res = new ArrayList<String>();
    private ArrayList<String> Res_Desc = new ArrayList<String>();
    private ArrayList<String> Type = new ArrayList<String>();

    ListView LVVIEW;
    Toolbar toolbar;
    Spinner sp_sub;
    String sub="";

    String DID="";
    String RESS="";
    String RESSDESC="";
    String SUBJECT="";
    String Sub="";

    //private Object Toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__resources);
        LVVIEW=findViewById(R.id.listview);
        sp_sub=findViewById(R.id.spinner);
        final  String [] subject={"SELECT SUBJECT","HTML","CSS","C#","ASP.NET","C/C++","CLOUD COM.","JAVA","PHP","ANDROID","SE","NETWORKING","CG","PYTHON","DS"};

        ArrayAdapter<String> adp3=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,subject);
        sp_sub.setAdapter(adp3);
        sp_sub.setSelection(0);
        sp_sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sub=subject[position].toString();
                DisplayData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SharedPreferences obj= getSharedPreferences("unm",MODE_PRIVATE);

        SNM=obj.getString("EMAIL"," ");
       // toolbar=findViewById(R.id.Toolbar);

     //   setSupportActionBar(toolbar);
      //  sp_sub=findViewById(R.id.sp_sub);
      getSupportActionBar().setTitle("View Resources");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerForContextMenu(LVVIEW);


            LVVIEW.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
                {
                        DID=Id.get(position).toString();
                        url=Res.get(position).toString();
                        RESS=Res.get(position).toString();
                        RESSDESC=Res_Desc.get(position).toString();
                        SUBJECT=Subject.get(position).toString();
                    return false;
                }
            });
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Open");
        menu.add("Delete");
        menu.add("Share");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {


     if (item.getTitle().equals("Open"))
       {
           if (!url.startsWith("https://") && !url.startsWith("http://")){
               url = "http://" + url;
           }
           Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
           if (openUrlIntent.resolveActivity(getPackageManager()) != null) {
               startActivity(openUrlIntent);
           }
       }

      if (item.getTitle().equals("Delete"))
       {

           AlertDialog dialog=askoption();
           dialog.show();

           //delete(Integer.parseInt(DID));
           //Toast.makeText(getApplicationContext(),"deleted Sucessfully",Toast.LENGTH_LONG).show();
           //DisplayData();
       }

      if (item.getTitle().equals("Share"))
      {
          Intent Wpintent=new Intent(Intent.ACTION_SEND);

          Wpintent.setType("text/plain");
          Wpintent.setPackage("com.whatsapp");
          Wpintent.putExtra(Intent.EXTRA_TEXT,"Subject :" +SUBJECT+ " ,Description :" + RESSDESC +" ,Resource : "+RESS+" ");
          try {
              startActivity(Wpintent);

          }
          catch (ActivityNotFoundException ex)
          {
              Toast.makeText(getApplicationContext(),"Whatsapp have not been  installed",Toast.LENGTH_LONG).show();
          }
      }

        return super.onContextItemSelected(item);

    }


    protected  void  onResume() {


        //DisplayData();
        super.onResume();
    }

    private  AlertDialog askoption()
    {
        AlertDialog msgbox=new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want to delete ?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(Integer.parseInt(DID));
                        Toast.makeText(getApplicationContext(),"deleted Sucessfully",Toast.LENGTH_LONG).show();
                        DisplayData();
                        //Toast.makeText(getApplicationContext(),"Okay",Toast.LENGTH_LONG).show();
                    }
                })

                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return msgbox;

    }

    private  void DisplayData()
    {
        db=dbhelp.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from tbldata where sname=? And subject=?",new  String[] {SNM,Sub});
        Id.clear();
        Sem.clear();
        Subject.clear();
        Res.clear();
        Res_Desc.clear();
        Type.clear();
      //  did INTEGER primary key autoincrement,year TEXT,sem TEXT,subject TEXT,link TEXT,des TEXT,type TEXT,sname TEXT
        if (cursor.moveToNext())
        {
            do {
                Id.add(cursor.getString(cursor.getColumnIndex("did")));
                Sem.add(cursor.getString(cursor.getColumnIndex("sem")));
                Subject.add(cursor.getString(cursor.getColumnIndex("subject")));
                Res.add(cursor.getString(cursor.getColumnIndex("link")));
                Res_Desc.add(cursor.getString(cursor.getColumnIndex("des")));
                Type.add(cursor.getString(cursor.getColumnIndex("type")));

            }while (cursor.moveToNext());

        }
        CustomAdapter ca=new CustomAdapter(View_Resources.this,Id,Sem,Subject,Res,Res_Desc,Type);
        LVVIEW.setAdapter(ca);
        cursor.close();
    }

        public  void delete(int id)
        {
            db=dbhelp.getWritableDatabase();
            db.delete("tbldata","did="+id,null);
            db.close();

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
               // Toast.makeText(this,"MY PROFILE",Toast.LENGTH_LONG).show();
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
}




