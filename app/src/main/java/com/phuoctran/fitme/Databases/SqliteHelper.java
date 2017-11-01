package com.phuoctran.fitme.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Objects;

import static com.phuoctran.fitme.Databases.Db.db;

/**
 * Created by VN-PC on 2017/05/16.
 */

public class SqliteHelper extends SQLiteOpenHelper
{

    public SqliteHelper(Context context,String db_name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, db_name, factory, version);
    }

    public void insert(String tableName, ContentValues dataToInsert){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableName,null,dataToInsert);
    }

    public void update(String tableName, ContentValues dataToInsert,String whereClause,String[] whereArgs){
        SQLiteDatabase db = getWritableDatabase();
        db.update(tableName,dataToInsert,whereClause,whereArgs);
    }

    public void ChangeData(String sql)
    {
        SQLiteDatabase db = getWritableDatabase();
//        db.setForeignKeyConstraintsEnabled(true);
//        db.beginTransaction();
        db.execSQL(sql);
//        db.setTransactionSuccessful();
//        db.endTransaction();
        //After Check setForeigneky~~
    }

    public Cursor GetData(String sql)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String tblRoleDetail = "CREATE TABLE IF NOT EXISTS RoleDetail (role_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "role_name INTEGER UNIQUE);";
        String tblPerson = "CREATE TABLE IF NOT EXISTS Person (person_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "person_name TEXT NOT NULL, " +
                "person_role INTEGER," +
                "person_avatar BLOB, " +
                "person_about TEXT, " +
                "FOREIGN KEY(person_role) REFERENCES RoleDetail(role_id));";
        String tblSizeType = "CREATE TABLE IF NOT EXISTS SizeType ( type_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "type_name INTEGER UNIQUE );";
        String tblSizeDetail = "CREATE TABLE IF NOT EXISTS SizeDetail (" +
                "detail_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "detail_value TEXT," +
                "detail_note TEXT," +
                "detail_person INTEGER REFERENCES Person(person_id) " +
                "on update cascade " +
                "on delete cascade," +
                "detail_type INTEGER REFERENCES SizeType(type_id)" +
                ");";
        db.execSQL(tblRoleDetail);
        db.execSQL(tblPerson);
        db.execSQL(tblSizeType);
        db.execSQL(tblSizeDetail);

        String insertRole = "INSERT INTO RoleDetail (role_id,role_name) VALUES (1,'Male')," +
                " (2,'Female')," +
                " (3,'Boy')," +
                " (4,'Girl');";

        String insertType = "INSERT INTO SizeType (type_id,type_name) VALUES (1,'Tops')," +
                " (2,'Bottoms')," +
                " (3,'Shoes')," +
                " (4,'Hat')," +
                " (5,'Ring');";

//        String[] insertPerson = {"insert into Person(person_id,person_name,person_role,person_about) values(1,'Skursky Benton',3,'Skursky.Benton@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(2,'Varriano Claribel',4,'Varriano.Claribel@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(3,'Brideau Junita',3,'Brideau.Junita@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(4,'Cookey Carmela',4,'Cookey.Carmela@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(5,'Hiatt Marguerita',4,'Hiatt.Marguerita@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(6,'Sawchuk Nelida',1,'Sawchuk.Nelida@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(7,'Latzke Lemuel',3,'Latzke.Lemuel@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(8,'Mconnell Jettie',2,'Mconnell.Jettie@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(9,'Montezuma Georgene',1,'Montezuma.Georgene@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(10,'Lawler Merlyn',2,'Lawler.Merlyn@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(11,'Ennaco Teri',4,'Ennaco.Teri@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(12,'Bayless Merilyn',3,'Bayless.Merilyn@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(13,'Skulski Hillary',4,'Skulski.Hillary@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(14,'Lizama Lashaunda',4,'Lizama.Lashaunda@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(15,'Wolny Lavonna',2,'Wolny.Lavonna@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(16,'Halsey Nobuko',1,'Halsey.Nobuko@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(17,'Syrop Ernest',2,'Syrop.Ernest@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(18,'Stuer Lashawnda',3,'Stuer.Lashawnda@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(19,'Saras Sherita',4,'Saras.Sherita@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(20,'Ogg Kaitlyn',4,'Ogg.Kaitlyn@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(21,'Keneipp Kate',1,'Keneipp.Kate@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(22,'Labreche Dulce',2,'Labreche.Dulce@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(23,'Loder Corinne',3,'Loder.Corinne@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(24,'Nachor Tarra',4,'Nachor.Tarra@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(25,'Brucato Winfred',3,'Brucato.Winfred@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(26,'Mauson Leonora',4,'Mauson.Leonora@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(27,'Herritt Kirk',3,'Herritt.Kirk@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(28,'Gehrett Aja',2,'Gehrett.Aja@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(29,'Amyot Jutta',2,'Amyot.Jutta@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(30,'Ragel Leota',4,'Ragel.Leota@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(31,'Dallen Jerry',2,'Dallen.Jerry@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(32,'Dhamer Derick',2,'Dhamer.Derick@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(33,'Nabours Adelina',2,'Nabours.Adelina@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(34,'Ferrario Justine',2,'Ferrario.Justine@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(35,'Buzick France',4,'Buzick.France@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(36,'Martabano Lorean',2,'Martabano.Lorean@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(37,'Halter Helaine',2,'Halter.Helaine@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(38,'Hoffis Roosevelt',1,'Hoffis.Roosevelt@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(39,'Julia Caitlin',1,'Julia.Caitlin@gmail.com');",
//                "insert into Person(person_id,person_name,person_role,person_about) values(40,'Flister Jesusita',4,'Flister.Jesusita@gmail.com');"
//        };
        db.execSQL(insertRole);
        db.execSQL(insertType);
//        for (String item :
//                insertPerson)
//        {
//            db.execSQL(item);
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
