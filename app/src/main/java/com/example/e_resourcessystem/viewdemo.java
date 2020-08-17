package com.example.e_resourcessystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewdemo extends AppCompatActivity {
    Databasehelper dbhelp=new Databasehelper(this);
    SQLiteDatabase db;
    ListView lvview;
    newadp adapter;

    ArrayList<String> Id = new ArrayList<String>();
     ArrayList<String> Sem = new ArrayList<String>();
     ArrayList<String> Subject = new ArrayList<String>();
     ArrayList<String> Res = new ArrayList<String>();
     ArrayList<String> Res_Desc = new ArrayList<String>();
     ArrayList<String> Type = new ArrayList<String>();
     ArrayList<model> arrayList=new ArrayList<model>();
    String SNM=null;
    String DID="";
    String RESS="";
    String RESSDESC="";
    String SUBJECT="";
    String url="";

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Open");
        menu.add("Delete");
        menu.add("Share");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
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
            delete(Integer.parseInt(DID));
            DisplayData();
            Toast.makeText(getApplicationContext(),"deleted Sucessfully",Toast.LENGTH_LONG).show();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdemo);
        SharedPreferences obj= getSharedPreferences("unm",MODE_PRIVATE);

        SNM=obj.getString("EMAIL"," ");
        lvview=findViewById(R.id.lvview);
        registerForContextMenu(lvview);

        lvview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DID=Id.get(position).toString();
                url=Res.get(position).toString();
                RESS=Res.get(position).toString();
                RESSDESC=Res_Desc.get(position).toString();
                SUBJECT=Subject.get(position).toString();
                return false;
            }
        });


    }
    protected  void  onResume() {


        DisplayData();
        super.onResume();
    }

    public  void delete(int id)
    {
        db=dbhelp.getWritableDatabase();
        db.delete("tbldata","did="+id,null);
        db.close();

    }

    public   void  DisplayData()
    {
        db=dbhelp.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from tbldata where sname=?",new  String[] {SNM});

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

            for (int i=0;i<Id.size();i++)
            {
                model model=new model(Id.get(i),Sem.get(i),Subject.get(i),Res.get(i),Res_Desc.get(i),Type.get(i));

                arrayList.add(model);
            }
        }

        adapter=new newadp(this,arrayList);
        lvview.setAdapter(adapter);
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.searchmenu,menu);
         MenuItem menuItem=menu.findItem(R.id.searchmenu);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               if (TextUtils.isEmpty(newText))
               {

                   adapter.filter("");
                   lvview.clearTextFilter();
               }

               else
               {
                   adapter.filter(newText);
               }

                return  true;
            }
        });
        return true;
    }
}
