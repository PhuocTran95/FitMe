package com.phuoctran.fitme.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by VN-PC on 2017/05/16.
 */

public class Db
{
    public static SqliteHelper db;

    private static final int DB_VER = 1;
    private static final String DB_NAME = "FitMeDb";

    public static void InitDb(Context context, SQLiteDatabase.CursorFactory cursorFactory)
    {
        if (db == null) db = new SqliteHelper(context, DB_NAME, cursorFactory, DB_VER);
        db.ChangeData("PRAGMA foreign_keys=on;");
    }
}
