package com.example.hescom;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2, button3;
    private static final int BDA_HOME_SCREEN = 1;
    private static final int OBCB_HOME_SCREEN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initialization();
    }

    public void initialization() {
        button1 = findViewById(R.id.btn_home);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.btn_obcb);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.btn_bda);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_home:
                intent = new Intent(HomeScreen.this, HomeDashboard.class);
                startActivity(intent);
                break;

            case R.id.btn_obcb:
                showdialog(OBCB_HOME_SCREEN);
                break;

            case R.id.btn_bda:
                showdialog(BDA_HOME_SCREEN);
                break;
        }
    }

    //------------------------------------Method for alert dialog---------------------------------------------------------------//
    private void showdialog(int id) {
        final AlertDialog login_dialog, login_dialog1;
        if (id == BDA_HOME_SCREEN) {
            AlertDialog.Builder login_dlg = new AlertDialog.Builder(this);
            login_dlg.setTitle("BDA DASHBOARD");
            @SuppressLint("InflateParams") LinearLayout dlg_linear = (LinearLayout) getLayoutInflater().inflate(R.layout.bda_home_screen, null);
            login_dlg.setView(dlg_linear);
            final TextView tariff = dlg_linear.findViewById(R.id.txt_tariff);
            final TextView overall = dlg_linear.findViewById(R.id.txt_overall);
            login_dialog = login_dlg.create();
            tariff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, DashBoard_Tariff_Wise.class);
                    startActivity(intent);
                }
            });
            overall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, DashBoard_Overall.class);
                    startActivity(intent);
                }
            });
            login_dialog.show();
        }
        if (id == OBCB_HOME_SCREEN) {
            AlertDialog.Builder login_dlg1 = new AlertDialog.Builder(this);
            login_dlg1.setTitle("OBCB SUMMARIZATION");
            @SuppressLint("InflateParams") LinearLayout dlg_linear = (LinearLayout) getLayoutInflater().inflate(R.layout.obcb_home_screen, null);
            login_dlg1.setView(dlg_linear);
            final TextView obcb1 = dlg_linear.findViewById(R.id.txt_obcb1);
            final TextView obcb2 = dlg_linear.findViewById(R.id.txt_obcb2);
            final TextView obcb3 = dlg_linear.findViewById(R.id.txt_obcb3);
            final TextView obcb4 = dlg_linear.findViewById(R.id.txt_obcb4);
            final TextView obcb5 = dlg_linear.findViewById(R.id.txt_obcb5);
            final TextView obcb6 = dlg_linear.findViewById(R.id.txt_obcb6);
            final TextView obcb7 = dlg_linear.findViewById(R.id.txt_obcb7);
            final TextView obcb8 = dlg_linear.findViewById(R.id.txt_obcb8);
            final TextView obcb9 = dlg_linear.findViewById(R.id.txt_obcb9);
            final TextView obcb10 = dlg_linear.findViewById(R.id.txt_obcb10);
            final TextView obcb11 = dlg_linear.findViewById(R.id.txt_obcb11);
            final TextView obcb12 = dlg_linear.findViewById(R.id.txt_obcb12);
            login_dialog1 = login_dlg1.create();
            obcb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBCustomer1.class);
                    startActivity(intent);
                }
            });
            obcb2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBCustomer2.class);
                    startActivity(intent);
                }
            });
            obcb3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBCustomer3.class);
                    startActivity(intent);
                }
            });
            obcb4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBCustomer4.class);
                    startActivity(intent);
                }
            });
            obcb5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBBilledCustomer1.class);
                    startActivity(intent);
                }
            });
            obcb6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBBilledCustomer2.class);
                    startActivity(intent);
                }
            });
            obcb7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBBilledCustomer3.class);
                    startActivity(intent);
                }
            });
            obcb8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBBilledCustomer4.class);
                    startActivity(intent);
                }
            });
            obcb9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBUnBilledCustomer1.class);
                    startActivity(intent);
                }
            });
            obcb10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBUnBilledCustomer2.class);
                    startActivity(intent);
                }
            });
            obcb11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBUnBilledCustomer3.class);
                    startActivity(intent);
                }
            });
            obcb12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, OBCBUnBilledCustomer4.class);
                    startActivity(intent);
                }
            });
            login_dialog1.show();
        }
    }

}
