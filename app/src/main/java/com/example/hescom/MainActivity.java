package com.example.hescom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import static com.example.hescom.Constant.ASSETS_DB_COPY_ERROR;
import static com.example.hescom.Constant.ASSETS_DB_COPY_SUCCESS;
import static com.example.hescom.Constant.LOGIN_FAILURE;
import static com.example.hescom.Constant.LOGIN_SUCCESS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button login;
    EditText userName, password;
    SendingData sendingData;
    FunctionCall functionCall;
    GetsetValues getsetValues;
    String VALUE = "";
    ProgressDialog progressDialog;
    Databasehelper databasehelper;


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case ASSETS_DB_COPY_SUCCESS:
                    functionCall.showtoast(MainActivity.this, "success");
                    break;

                case ASSETS_DB_COPY_ERROR:
                    functionCall.copyAssets(MainActivity.this, handler);
                    break;

                case LOGIN_SUCCESS:
                    progressDialog.dismiss();
                    functionCall.showtoast(MainActivity.this, "Login success");
                    moveToNext();
                    break;

                case LOGIN_FAILURE:
                    progressDialog.dismiss();
                    functionCall.showtoast(MainActivity.this, "Login Failure");
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);
        initData();
        enable_database();
    }
    private void enable_database() {
        databasehelper = new Databasehelper(MainActivity.this);
        databasehelper.openDatabase();
        if (!functionCall.check_hescom_rapdrp_db()) {
            functionCall.copyAssets(MainActivity.this, handler);
        }
    }

    private void initData() {
        progressDialog = new ProgressDialog(this);
        getsetValues = new GetsetValues();
        sendingData = new SendingData();
        functionCall = new FunctionCall();
        userName = findViewById(R.id.et_user_name);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            userLogin();
//            moveToNext();
        }
    }

    public void moveToNext() {
        Intent intent = new Intent(MainActivity.this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

    public void userLogin() {
        if (TextUtils.isEmpty(userName.getText())) {
            userName.setError("Please Enter UserName");
            return;
        }
        if (TextUtils.isEmpty(password.getText())) {
            password.setError("Please Enter Password");
            return;
        }
        String name = userName.getText().toString();
        String pass = password.getText().toString();
        String user_name = name.replace(" ", "%20");
        VALUE = "username=" + user_name + "&password=" + pass;
        functionCall.showprogressdialog("Please wait to complete...", "Login", progressDialog);
        SendingData.Login login = sendingData.new Login(getsetValues, handler, VALUE, name);
        login.execute(name, pass);
    }
}
