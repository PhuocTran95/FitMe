package com.phuoctran.fitme.Models;

/**
 * Created by VN-PC on 2017/05/16.
 */

public class SizeType
{
    private  int type_id;
    private  String type_name;

    public SizeType(int type_id, String type_name)
    {
        this.type_id = type_id;
        this.type_name = type_name;
    }

    public SizeType()
    {
    }

    public int getType_id()
    {
        return type_id;
    }

    public void setType_id(int type_id)
    {
        this.type_id = type_id;
    }

    public String getType_name()
    {
        return type_name;
    }

    public void setType_name(String type_name)
    {
        this.type_name = type_name;
    }
}
