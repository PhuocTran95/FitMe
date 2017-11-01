package com.phuoctran.fitme.Models;

/**
 * Created by VN-PC on 2017/05/16.
 */

public class RoleDetail
{
    private int role_id;
    private String role_name;

    public RoleDetail(int role_id, String role_name)
    {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public RoleDetail()
    {
    }

    public int getRole_id()
    {
        return role_id;
    }

    public void setRole_id(int role_id)
    {
        this.role_id = role_id;
    }

    public String getRole_name()
    {
        return role_name;
    }

    public void setRole_name(String role_name)
    {
        this.role_name = role_name;
    }
}
