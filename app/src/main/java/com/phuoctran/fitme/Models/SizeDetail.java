package com.phuoctran.fitme.Models;

/**
 * Created by VN-PC on 2017/05/16.
 */

public class SizeDetail
{
    private int detail_id;
    private String detail_value;
    private String detail_note;
    private int detail_person;
    private int detail_type;

    public SizeDetail(int detail_id, String detail_value, String detail_note, int detail_person, int detail_type)
    {
        this.detail_id = detail_id;
        this.detail_value = detail_value;
        this.detail_note = detail_note;
        this.detail_person = detail_person;
        this.detail_type = detail_type;
    }

    public SizeDetail()
    {
    }

    public int getDetail_id()
    {
        return detail_id;
    }

    public void setDetail_id(int detail_id)
    {
        this.detail_id = detail_id;
    }

    public String getDetail_value()
    {
        return detail_value;
    }

    public void setDetail_value(String detail_value)
    {
        this.detail_value = detail_value;
    }

    public String getDetail_note()
    {
        return detail_note;
    }

    public void setDetail_note(String detail_note)
    {
        this.detail_note = detail_note;
    }

    public int getDetail_person()
    {
        return detail_person;
    }

    public void setDetail_person(int detail_person)
    {
        this.detail_person = detail_person;
    }

    public int getDetail_type()
    {
        return detail_type;
    }

    public void setDetail_type(int detail_type)
    {
        this.detail_type = detail_type;
    }
}
