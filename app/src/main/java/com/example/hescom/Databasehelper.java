package com.example.hescom;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.File;

import static com.example.hescom.Constant.DIR_DATABASE;
import static com.example.hescom.Constant.FILE_HESCOM_DATABASE;

public class Databasehelper {
    private MyHelper mh;
    private SQLiteDatabase myDataBase;
    private FunctionCall functionsCall = new FunctionCall();

    private String DATABASE_NAME;
    private String path = functionsCall.filepath(DIR_DATABASE);
    private String DATABASE_PATH = path + File.separator;

    public Databasehelper(Context context) {
        DATABASE_NAME = FILE_HESCOM_DATABASE;
        mh = new MyHelper(context, DATABASE_NAME, null, 1);
    }

    public class MyHelper extends SQLiteOpenHelper {
        MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    public void db_close() {
        db_close();
    }

    //Open database
    public boolean openDatabase() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        File file = new File(myPath);
        if (file.exists()) {
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            return true;
        } else return false;
    }

    public Cursor getCompany_details() {
        return myDataBase.rawQuery("Select * from company", null);
    }

    public Cursor getZone_details(String value) {
        return myDataBase.rawQuery("Select * from zone where COMPANY_ID = '" + value + "'", null);
    }

    public Cursor getCircle_details(String value) {
        return myDataBase.rawQuery("Select * from circle where ZONE_ID = '" + value + "'", null);
    }

    public Cursor getDivision_details(String value) {
        return myDataBase.rawQuery("Select * from division where CIRCLE_ID = '" + value + "'", null);
    }

    public Cursor getSubDivision_details(String value) {
        return myDataBase.rawQuery("Select * from sub_division where DIVISION_ID = '" + value + "'", null);
    }

    public Cursor getTariff_details() {
        return myDataBase.rawQuery("Select * from tariff_details", null);
    }
}