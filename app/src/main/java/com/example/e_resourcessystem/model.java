package com.example.e_resourcessystem;

public class model
{
    String id,sem,subject,res,res_desc,type;

    public model(String id, String sem, String subject, String res, String res_desc, String type) {
        this.id = id;
        this.sem = sem;
        this.subject = subject;
        this.res = res;
        this.res_desc = res_desc;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public String getSem() {
        return this.sem;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getRes() {
        return this.res;
    }

    public String getRes_desc() {
        return this.res_desc;
    }

    public String getType() {
        return this.type;
    }
}
