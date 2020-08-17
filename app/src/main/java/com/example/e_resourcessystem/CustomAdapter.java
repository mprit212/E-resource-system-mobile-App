package com.example.e_resourcessystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    Databasehelper dbhelp;
    SQLiteDatabase db;

    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Sem = new ArrayList<String>();
    private ArrayList<String> Subject = new ArrayList<String>();
    private ArrayList<String> Res = new ArrayList<String>();
    private ArrayList<String> Res_Desc = new ArrayList<String>();
    private ArrayList<String> Type = new ArrayList<String>();

    public  CustomAdapter(Context context,ArrayList<String> Id,ArrayList<String> Sem,ArrayList<String> Subject,ArrayList<String> Res,ArrayList<String> Res_Desc,ArrayList<String> Type)
    {

        this.mContext=context;
        this.Id=Id;
        this.Sem=Sem;
        this.Subject=Subject;
        this.Res=Res;
        this.Res_Desc=Res_Desc;
        this.Type=Type;
    }

    @Override
    public int getCount() {
        return Id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public  class  viewHolder
    {
        TextView sem,subject,res,res_desc,type,id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        viewHolder holder;
        dbhelp=new Databasehelper(mContext);
        LayoutInflater layoutInflater;

        if (convertView == null)
        {
            layoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.row,null);
            holder=new viewHolder();
            holder.id=(TextView)convertView.findViewById(R.id.tvid);
            holder.sem=(TextView)convertView.findViewById(R.id.tvsem);
            holder.subject=(TextView)convertView.findViewById(R.id.tvsubject);
            holder.res=(TextView)convertView.findViewById(R.id.tvres);
            holder.res_desc=(TextView)convertView.findViewById(R.id.tvres_desc);
            holder.type=(TextView)convertView.findViewById(R.id.tvtype);
            convertView.setTag(holder);
        }
        else
        {
            holder=(viewHolder)convertView.getTag();

        }
        holder.id.setText(Id.get(position));
        holder.sem.setText("Sem : " + Sem.get(position));
        holder.subject.setText("Subject : " +Subject.get(position));
        holder.res.setText("Resouorce : " +Res.get(position));
        holder.res_desc.setText("Description : " +Res_Desc.get(position));
        holder.type.setText("Type : " +Type.get(position));
        return convertView;
    }
}
