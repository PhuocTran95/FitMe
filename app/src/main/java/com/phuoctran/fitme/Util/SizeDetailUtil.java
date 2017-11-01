package com.phuoctran.fitme.Util;

import android.content.Context;
import android.database.Cursor;

import com.phuoctran.fitme.Databases.Db;
import com.phuoctran.fitme.R;

/**
 * Created by VN-PC on 2017/05/27.
 */

public class SizeDetailUtil
{
    private static Context context;

    public static int getNewRoleID(Context con)
    {
        context=con;

        Cursor cursor = Db.db.GetData("Select count(*) from SizeDetail");
        if (cursor.getCount() > 0)
        {
            cursor.moveToNext();
            int max = cursor.getInt(0);
            return max+1;
        }
        return 1;
    }
}
