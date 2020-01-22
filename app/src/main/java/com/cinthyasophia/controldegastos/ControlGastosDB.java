package com.cinthyasophia.controldegastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class ControlGastosDB extends SQLiteOpenHelper implements Serializable {
    private final String DB_PATH = "/data/data/com.cinthyasophia.controldegastos/databases/";
    private String db_name;
    private SQLiteDatabase database;
    private Context context;

    public ControlGastosDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db_name = name;
        this.database = null;
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = getWritableDatabase();
        chargeDatabase(context,DB_PATH,db_name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public SQLiteDatabase getOpenDataBase(){

        String myPath = new StringBuilder().append(DB_PATH).append(db_name).toString();
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return database;
    }

    public void chargeDatabase(Context context, String path, String databaseName){

        try {
            OutputStream databaseOutputStream = new FileOutputStream(path+databaseName);
            InputStream databaseInputStream;
            databaseInputStream = context.getAssets().open(databaseName);

            while (databaseInputStream.available()!=0) {
                databaseOutputStream.write(databaseInputStream.read());
            }

            databaseInputStream.close();
            databaseOutputStream.flush();
            databaseOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
