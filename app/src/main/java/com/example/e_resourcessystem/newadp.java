package com.example.e_resourcessystem;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class newadp extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    List<model> modelList;
    ArrayList<model> arrayList;

    public newadp(Context context, List<model> modelList) {
        this.context = context;
        this.modelList = modelList;
        layoutInflater=LayoutInflater.from(context);
        this.arrayList=new ArrayList<model>();
        this.arrayList.addAll(modelList);
    }
    public  class  ViewHolder{

        TextView sem,subject,res,res_desc,type,id;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.row,null);


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

            holder=(ViewHolder)convertView.getTag();
        }

        holder.id.setText(modelList.get(position).getId());
        holder.sem.setText("SEM : "+modelList.get(position).getSem());
        holder.subject.setText("SUBJECT : "+modelList.get(position).getSubject());
        holder.res.setText("Resources : "+modelList.get(position).getRes());
        holder.res_desc.setText("Description : "+modelList.get(position).getRes_desc());
        holder.type.setText("Type : "+modelList.get(position).getType());

        return convertView;
    }

    public void filter(String Chartext)
    {
        Chartext=Chartext.toLowerCase(Locale.getDefault());
        modelList.clear();
        if (Chartext.length()==0)
        {

            modelList.addAll(arrayList);

        }
        else
        {
            for (model model:arrayList)
            {
                if(model.getSubject().toLowerCase(Locale.getDefault()).contains(Chartext))
                {
                    modelList.add(model);

                }

            }
        }
        notifyDataSetChanged();
    }
}
