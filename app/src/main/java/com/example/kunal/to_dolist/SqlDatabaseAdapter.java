package com.example.kunal.to_dolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class SqlDatabaseAdapter {
    Sqlhelper sqlhelper;

    public SqlDatabaseAdapter(Context context){
        sqlhelper =new Sqlhelper(context);
    }
    public long insertdata(String name,String marks){

        SQLiteDatabase sqLiteDatabase=sqlhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Sqlhelper.NAME,name);
        contentValues.put(Sqlhelper.MARKS,marks);
        long id= sqLiteDatabase.insert(sqlhelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public Cursor getallData(){
        SQLiteDatabase sqLiteDatabase=sqlhelper.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("SELECT * FROM "+sqlhelper.TABLE_NAME,null);
        return res;
    }



    public boolean deleteData(String id){
        SQLiteDatabase sqLiteDatabase=sqlhelper.getWritableDatabase();
        int row_affected= sqLiteDatabase.delete(Sqlhelper.TABLE_NAME,Sqlhelper.UID+" =?",new String[] {id});
        if(row_affected>0)
            return true;
        else
            return false;
    }


    class Sqlhelper  extends SQLiteOpenHelper {
        private static final String DATABASE_NAME="TodoList";
        private static final String TABLE_NAME="ListTable";
        private static final int DATABASE_VERSION=2;
        private static final String UID="_id";
        private static final String NAME="TITLE";
        private static final String MARKS="DETAILS";
        private static final String createtable="CREATE TABLE "+TABLE_NAME+ " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+MARKS+" VARCHAR(255));";
        private static final String droptable="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public Sqlhelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
            Log.e("d","constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {

                db.execSQL(createtable);
                Log.e("d","oncreate called");
            }catch (SQLException e){
                e.getMessage();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(droptable);
                onCreate(db);
                Log.e("d","onUpgrade called");
            }catch (SQLException e){
                e.getMessage();
            }

        }

    }


}
