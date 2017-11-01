package com.phuoctran.fitme.Util;

import android.content.Context;
import android.database.Cursor;

import com.phuoctran.fitme.Databases.Db;
import com.phuoctran.fitme.R;

/**
 * Created by VN-PC on 2017/05/27.
 */

public class RoleDetailUtil
{
    private static Context context;

    public static String getRoleName(Context con,int roleID)
    {
        context=con;

        Cursor cursor = Db.db.GetData("Select role_name from RoleDetail where role_id=" + roleID);
        if (cursor.getCount() > 0)
        {
            cursor.moveToNext();
            return getRoleByLang(cursor.getString(0));
        }
        return "";
    }

    private static String getRoleByLang(String s)
    {
        switch (s)
        {
            case "Male":
                return context.getString(R.string.male);
            case "Female":
                return context.getString(R.string.female);
            case "Boy":
                return context.getString(R.string.boy);
            case "Girl":
                return context.getString(R.string.girl);
            default:
                return s;
        }
    }
}
