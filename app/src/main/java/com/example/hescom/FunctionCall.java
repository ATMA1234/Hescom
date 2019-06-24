package com.example.hescom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.hescom.Constant.ASSETS_DB_COPY_ERROR;
import static com.example.hescom.Constant.ASSETS_DB_COPY_SUCCESS;
import static com.example.hescom.Constant.DIR_DATABASE;
import static com.example.hescom.Constant.FILE_HESCOM_DATABASE;

public class FunctionCall {
    public void logStatus(String msg) {
        Log.d("debug", msg);
    }

    public void showtoast(Context context, String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
    }

    public String Appfoldername() {
        return "HESCOM_RAPDRP" + File.separator + "data";
    }

    public boolean check_hescom_rapdrp_db() {
        File dbfile = new File(filepath(DIR_DATABASE) + File.separator + FILE_HESCOM_DATABASE);
        return dbfile.exists();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String filepath(String value) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory(), Appfoldername()
                + File.separator + value);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.toString();
    }

    public void copyAssets(Context context, Handler handler) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("Files");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        assert files != null;
        InputStream in;
        OutputStream out;
        File outFile;
        try {
            in = assetManager.open(FILE_HESCOM_DATABASE);   // if files resides inside the "Files" directory itself
            outFile = new File(filepath(DIR_DATABASE), FILE_HESCOM_DATABASE);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            in.close();
            out.flush();
            out.close();
            handler.sendEmptyMessage(ASSETS_DB_COPY_SUCCESS);
        } catch (Exception e) {
            logStatus(e.getMessage());
            handler.sendEmptyMessage(ASSETS_DB_COPY_ERROR);
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public String getCursorValue(Cursor data, String column_name) {
        if (check_column(data, column_name)) {
            if (!TextUtils.isEmpty(data.getString(data.getColumnIndexOrThrow(column_name))))
                return data.getString(data.getColumnIndexOrThrow(column_name));
            else return "";
        } else return "";
    }

    private boolean check_column(Cursor data, String column) {
        long result = data.getColumnIndex(column);
        return result != -1;
    }

    public String getJSONresult(Cursor cursor) {
        if (cursor.getCount() > 1)
            return getJSONArray(cursor);
        else return getJSONObject(cursor);
    }

    private String getJSONArray(Cursor cursor) {
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null)
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        else rowObject.put(cursor.getColumnName(i), "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        logStatus(resultSet.toString());
        return resultSet.toString();
    }

    private String getJSONObject(Cursor cursor) {
        int totalColumn = cursor.getColumnCount();
        JSONObject rowObject = new JSONObject();
        cursor.moveToFirst();
        for (int i = 0; i < totalColumn; i++) {
            if (cursor.getColumnName(i) != null) {
                try {
                    if (cursor.getString(i) != null)
                        rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                    else rowObject.put(cursor.getColumnName(i), "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        logStatus(rowObject.toString());
        return rowObject.toString();
    }

    //-----------------------------------------method for show progress dialog----------------------------------------------------//
    public void showprogressdialog(String Message, String Title, ProgressDialog dialog) {
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        dialog.setCancelable(false);
        dialog.show();
    }

    public String roundoff(String value) {
        double c = 10000000;
        double d = Double.parseDouble(value) / c;
        BigDecimal a = new BigDecimal(d);
        BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return "" + roundOff;
    }

    public String Parse_Date(String time) {
        String input = "yyyy-MM-d";
        String output = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(input);
        SimpleDateFormat outputFormat = new SimpleDateFormat(output);

        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String roundoff1(String value) {
        BigDecimal a = new BigDecimal(value);
        BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return "" + roundOff;
    }
}
