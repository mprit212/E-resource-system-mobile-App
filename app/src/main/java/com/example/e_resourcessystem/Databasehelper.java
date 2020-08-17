package com.example.e_resourcessystem;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Databasehelper extends SQLiteOpenHelper
{
    public  static  final  String Db_name="DBER1.db";
    public  static  final  String tbl1="tblstu";
    public  static  final  String tbl2="tbldata";
    public static final String TBL_STU=" create table if not exists "+ tbl1 +" (sid INTEGER primary key autoincrement,snm TEXT,email TEXT,phnum INTEGER, dob DATE, eno INTEGER,pass TEXT)";
    public static final String TBL_DATA=" create table if not exists "+ tbl2 +" (did INTEGER primary key autoincrement,year TEXT,sem TEXT,subject TEXT,link TEXT,des TEXT,type TEXT,sname TEXT)";

    public Databasehelper(Context context)
    {
        super(context,Db_name,null,1);
//       SQLiteDatabase db=this.getWritableDatabase();
       //context.deleteDatabase(Db_name);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TBL_DATA);
       db.execSQL(TBL_STU);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
      //  db.execSQL("drop table if exists "+tbl2);
      // db.execSQL("drop table if exists "+tbl1);
        //onCreate(db);
    }

    public  boolean insertstudent(String name ,String email, String phnum,String dob,String eno , String pass)
    {

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("snm",name);
        cv.put("email",email);
        cv.put("phnum",phnum);
        cv.put("dob",dob);
        cv.put("eno",eno);
        cv.put("pass",pass);

        long result= db.insert("tblstu",null,cv);

        if (result == -1)
        {
            return  false;
        }

        else
        {
            return  true;
        }


    }


    public  boolean checkuser(String name,String pass)
    {

        SQLiteDatabase db=this.getReadableDatabase();
            String [] selectdcolm={"snm"};
            String [] selectionargs={name,pass};
        Cursor cursor = db.query("tblstu",selectdcolm,"snm = ? AND  pass = ?",selectionargs,null,null,null);

        int count = cursor.getCount();
        cursor.close();

        if (count > 0)
        {

            return  true;

        }

        else
        {
            return  false;
        }
    }

    public  boolean InsertData(String Year,String Sem,String Subject,String Resource , String Res_Desc,String Type, String Snm)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        //did primary key autoincrement,year TEXT,sem TEXT,subject TEXT,link TEXT,des TEXT,type TEXT,snm TEXT
        cv.put("year",Year);
        cv.put("sem",Sem);
        cv.put("subject",Subject);
        cv.put("link",Resource);
        cv.put("des",Res_Desc);
        cv.put("type",Type);
        cv.put("sname",Snm);

        long Res=db.insert("tbldata",null,cv);

        if (Res > 0)
        {
            return  true;
        }

        else
        {
            return  false;
        }


    }
        public boolean update(String Name,String Email,String Phnum,String Dob,String Enum,String Sid)
        {
            ////sid INTEGER primary key autoincrement,snm TEXT,email TEXT,phnum INTEGER, dob DATE, eno INTEGER,pass TEXT
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put("snm",Name);
            cv.put("email",Email);
            cv.put("phnum",Phnum);
            cv.put("dob",Dob);
            cv.put("eno",Enum);
            db.update("tblstu",cv,"sid=?",new String[] {Sid});
            return  true;
        }
}
