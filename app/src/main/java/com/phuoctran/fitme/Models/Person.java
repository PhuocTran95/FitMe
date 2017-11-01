package com.phuoctran.fitme.Models;

/**
 * Created by VN-PC on 2017/05/16.
 */

public class Person
{
    private int person_id;
    private String person_name;
    private int person_role;
    private byte[] person_avatar;
    private String person_about;

    public int getPerson_id()
    {
        return person_id;
    }

    public void setPerson_id(int person_id)
    {
        this.person_id = person_id;
    }

    public String getPerson_name()
    {
        return person_name;
    }

    public void setPerson_name(String person_name)
    {
        this.person_name = person_name;
    }

    public int getPerson_role()
    {
        return person_role;
    }

    public void setPerson_role(int person_role)
    {
        this.person_role = person_role;
    }

    public byte[] getPerson_avatar()
    {
        return person_avatar;
    }

    public void setPerson_avatar(byte[] person_avatar)
    {
        this.person_avatar = person_avatar;
    }

    public String getPerson_about()
    {
        return person_about;
    }

    public void setPerson_about(String person_about)
    {
        this.person_about = person_about;
    }

    public Person(int person_id, String person_name, int person_role, byte[] person_avatar, String person_about)
    {
        this.person_id = person_id;
        this.person_name = person_name;
        this.person_role = person_role;
        this.person_avatar = person_avatar;
        this.person_about = person_about;
    }

    public Person()
    {

    }
}
