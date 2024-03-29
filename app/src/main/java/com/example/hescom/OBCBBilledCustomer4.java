package com.example.hescom;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.hescom.Constant.CIRCLE_ID;
import static com.example.hescom.Constant.CIRCLE_NAME;
import static com.example.hescom.Constant.COMPANY_ID;
import static com.example.hescom.Constant.COMPANY_NAME;
import static com.example.hescom.Constant.DIALOG_FAILURE;
import static com.example.hescom.Constant.DIALOG_SUCCESS;
import static com.example.hescom.Constant.DIVISION_ID;
import static com.example.hescom.Constant.DIVISION_NAME;
import static com.example.hescom.Constant.SUBDIV_ID;
import static com.example.hescom.Constant.SUBDIV_NAME;
import static com.example.hescom.Constant.TARIFF_NAME;
import static com.example.hescom.Constant.ZONE_ID;
import static com.example.hescom.Constant.ZONE_NAME;

public class OBCBBilledCustomer4 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ArrayList<GetsetValues> company_list, zone_list, circle_list, division_list, subdiv_list, tariff_list, status_list;
    RoleAdapter company_adapter, zone_adapter, circle_adapter, div_adapter, subdiv_adapter, tariff_adapter, status_adapter;
    Spinner sp_company, sp_zone, sp_circle, sp_division, sp_subdiv, sp_tariff, sp_status;
    GetsetValues getsetValues;
    ArrayList<GetsetValues> arrayList;
    String COMPANY = "500001", ZONE = "none", CIRCLE = "none", DIVISION = "none", SUB_DIVISION = "none", TARIFF = "All", STATUS = "All";
    Databasehelper databasehelper;
    FunctionCall functionCall;
    SendingData sendingData;
    ArrayList<Response> responseArrayList;
    private boolean userIsInteracting;
    EditText tv_from, tv_to;
    TextView tv_total1, tv_total2, tv_total3, tv_total4, tv_total5, tv_total6, tv_total7, tv_total8, tv_total9, tv_total10;
    Calendar mcalender;
    int day, month, year;
    String dd, FROM_DATE, TO_DATE;
    BarChart mBarChart;
    Button submit;
    String value = "";
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    OBCBBilledAdapter4 obcbAdapter;
    LinearLayout lin1, lin2;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case DIALOG_SUCCESS:
                    progressDialog.dismiss();
                    barchart(responseArrayList);
                    lin1.setVisibility(View.VISIBLE);
                    lin2.setVisibility(View.VISIBLE);
                    break;

                case DIALOG_FAILURE:
                    progressDialog.dismiss();
                    functionCall.showtoast(OBCBBilledCustomer4.this, "No Data found");
                    break;

            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obcbbilled_customer4);
        initialization();
        populate_company_spinner();
        populate_tariff_spinner();
        populate_status_spinner();
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    public void initialization() {
        tv_total1 = findViewById(R.id.txt_total1);
        tv_total2 = findViewById(R.id.txt_total2);
        tv_total3 = findViewById(R.id.txt_total3);
        tv_total4 = findViewById(R.id.txt_total4);
        tv_total5 = findViewById(R.id.txt_total5);
        tv_total6 = findViewById(R.id.txt_total6);
        tv_total7 = findViewById(R.id.txt_total7);
        tv_total8 = findViewById(R.id.txt_total8);
        tv_total9 = findViewById(R.id.txt_total9);
        tv_total10 = findViewById(R.id.txt_total10);
        sendingData = new SendingData();
        functionCall = new FunctionCall();
        databasehelper = new Databasehelper(this);
        databasehelper.openDatabase();
        getsetValues = new GetsetValues();
        sp_company = findViewById(R.id.company);
        sp_company.setOnItemSelectedListener(this);
        sp_zone = findViewById(R.id.zone);
        sp_zone.setOnItemSelectedListener(this);
        sp_circle = findViewById(R.id.circle);
        sp_circle.setOnItemSelectedListener(this);
        sp_division = findViewById(R.id.division);
        sp_division.setOnItemSelectedListener(this);
        sp_subdiv = findViewById(R.id.subdivision);
        sp_subdiv.setOnItemSelectedListener(this);
        sp_tariff = findViewById(R.id.tariff);
        sp_tariff.setOnItemSelectedListener(this);
        sp_status = findViewById(R.id.status);
        sp_status.setOnItemSelectedListener(this);
        tv_from = findViewById(R.id.from_date);
        tv_from.setOnClickListener(this);
        tv_to = findViewById(R.id.to_date);
        tv_to.setOnClickListener(this);
        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);
        mBarChart = findViewById(R.id.barChart);
        arrayList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        responseArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.obcb_recycle);
        lin1 = findViewById(R.id.lin_layout1);
        lin2 = findViewById(R.id.lin_layout2);
        obcbAdapter = new OBCBBilledAdapter4(this, responseArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(obcbAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.from_date) {
            DateDialog1();
        }
        if (v.getId() == R.id.to_date) {
            DateDialog2();
        }
        if (v.getId() == R.id.btn_submit) {
            submit();
        }
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        GetsetValues role;
        switch (adapterView.getId()) {
            case R.id.company:
                if (userIsInteracting) {
                    role = company_list.get(i);
                    COMPANY = role.getCode();
                    populate_zone_spinner(COMPANY);
                }
                break;

            case R.id.zone:
                if (userIsInteracting) {
                    role = zone_list.get(i);
                    ZONE = role.getCode();
                    populate_circle_spinner(ZONE);
                }
                break;

            case R.id.circle:
                if (userIsInteracting) {
                    role = circle_list.get(i);
                    CIRCLE = role.getCode();
                    populate_division_spinner(CIRCLE);
                }
                break;

            case R.id.division:
                if (userIsInteracting) {
                    role = division_list.get(i);
                    DIVISION = role.getCode();
                    populate_subdivision_spinner(DIVISION);
                }
                break;

            case R.id.subdivision:
                if (userIsInteracting) {
                    role = subdiv_list.get(i);
                    SUB_DIVISION = role.getCode();
                }
                break;

            case R.id.tariff:
                if (userIsInteracting) {
                    role = tariff_list.get(i);
                    TARIFF = role.getCode();
                }
                break;

            case R.id.status:
                if (userIsInteracting) {
                    role = status_list.get(i);
                    STATUS = role.getCode();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //Setting company spinner--------------------------------------------------------------------------------------------------
    private void populate_company_spinner() {
        company_list = new ArrayList<>();
        company_list.clear();
        Cursor company_data = databasehelper.getCompany_details();
        if (company_data.getCount() > 0) {
            while (company_data.moveToNext()) {
                GetsetValues getSetValues = new GetsetValues();
                getSetValues.setCode(functionCall.getCursorValue(company_data, COMPANY_ID));
                getSetValues.setName(functionCall.getCursorValue(company_data, COMPANY_NAME));
                company_list.add(getSetValues);
            }
            company_adapter = new RoleAdapter(company_list, getApplicationContext());
            sp_company.setAdapter(company_adapter);
            company_adapter.notifyDataSetChanged();
            sp_company.setSelection(0);
        }
        company_data.close();
    }

    //setting zone spinner--------------------------------------------------------------------------------------------------------
    private void populate_zone_spinner(String company) {
        zone_list = new ArrayList<>();
        zone_list.clear();
        Cursor zone_data = databasehelper.getZone_details(company);
        if (zone_data.getCount() > 0) {
            while (zone_data.moveToNext()) {
                GetsetValues getSetValues = new GetsetValues();
                getSetValues.setCode(functionCall.getCursorValue(zone_data, ZONE_ID));
                getSetValues.setName(functionCall.getCursorValue(zone_data, ZONE_NAME));
                zone_list.add(getSetValues);
            }
            zone_adapter = new RoleAdapter(zone_list, getApplicationContext());
            sp_zone.setAdapter(zone_adapter);
            zone_adapter.notifyDataSetChanged();
            userIsInteracting = false;
            sp_zone.setSelection(0);
        }
        zone_data.close();
    }

    //setting circle spinner--------------------------------------------------------------------------------------------------
    private void populate_circle_spinner(String zone) {
        circle_list = new ArrayList<>();
        circle_list.clear();
        Cursor circle_data = databasehelper.getCircle_details(zone);
        if (circle_data.getCount() > 0) {
            while (circle_data.moveToNext()) {
                GetsetValues getSetValues = new GetsetValues();
                getSetValues.setCode(functionCall.getCursorValue(circle_data, CIRCLE_ID));
                getSetValues.setName(functionCall.getCursorValue(circle_data, CIRCLE_NAME));
                circle_list.add(getSetValues);
            }
            circle_adapter = new RoleAdapter(circle_list, getApplicationContext());
            sp_circle.setAdapter(circle_adapter);
            circle_adapter.notifyDataSetChanged();
            userIsInteracting = false;
        }
        circle_data.close();
    }

    //setting division spinner--------------------------------------------------------------------------------------------------
    private void populate_division_spinner(String circle) {
        division_list = new ArrayList<>();
        division_list.clear();
        Cursor division_data = databasehelper.getDivision_details(circle);
        if (division_data.getCount() > 0) {
            while (division_data.moveToNext()) {
                GetsetValues getSetValues = new GetsetValues();
                getSetValues.setCode(functionCall.getCursorValue(division_data, DIVISION_ID));
                getSetValues.setName(functionCall.getCursorValue(division_data, DIVISION_NAME));
                division_list.add(getSetValues);
            }
            div_adapter = new RoleAdapter(division_list, getApplicationContext());
            sp_division.setAdapter(div_adapter);
            div_adapter.notifyDataSetChanged();
            userIsInteracting = false;
        }
        division_data.close();
    }

    //setting subdivision spinner--------------------------------------------------------------------------------------------------
    private void populate_subdivision_spinner(String division) {
        subdiv_list = new ArrayList<>();
        subdiv_list.clear();
        Cursor sub_division_data = databasehelper.getSubDivision_details(division);
        if (sub_division_data.getCount() > 0) {
            while (sub_division_data.moveToNext()) {
                GetsetValues getSetValues = new GetsetValues();
                getSetValues.setCode(functionCall.getCursorValue(sub_division_data, SUBDIV_ID));
                getSetValues.setName(functionCall.getCursorValue(sub_division_data, SUBDIV_NAME));
                subdiv_list.add(getSetValues);
            }
            subdiv_adapter = new RoleAdapter(subdiv_list, getApplicationContext());
            sp_subdiv.setAdapter(subdiv_adapter);
            subdiv_adapter.notifyDataSetChanged();
            userIsInteracting = false;
        }
        sub_division_data.close();
    }

    //setting tariff spinner--------------------------------------------------------------------------------------------------
    private void populate_tariff_spinner() {
        tariff_list = new ArrayList<>();
        tariff_list.clear();
        Cursor tariffDetails = databasehelper.getTariff_details();
        if (tariffDetails.getCount() > 0) {
            while (tariffDetails.moveToNext()) {
                GetsetValues getSetValues = new GetsetValues();
                getSetValues.setCode(functionCall.getCursorValue(tariffDetails, TARIFF_NAME));
                tariff_list.add(getSetValues);
            }
            tariff_adapter = new RoleAdapter(tariff_list, getApplicationContext());
            sp_tariff.setAdapter(tariff_adapter);
            tariff_adapter.notifyDataSetChanged();
        }
        tariffDetails.close();
    }

    //setting status spinner--------------------------------------------------------------------------------------------------
    private void populate_status_spinner() {
        status_list = new ArrayList<>();
        status_list.clear();
        for (int i = 0; i < getResources().getStringArray(R.array.account_status).length; i++) {
            GetsetValues getSetValues = new GetsetValues();
            String value = getResources().getStringArray(R.array.account_status)[i];
            getSetValues.setCode(value);
            status_list.add(getSetValues);
        }
        status_adapter = new RoleAdapter(status_list, getApplicationContext());
        sp_status.setAdapter(status_adapter);
        status_adapter.notifyDataSetChanged();
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    public void DateDialog1() {
        mcalender = Calendar.getInstance();
        day = mcalender.get(Calendar.DAY_OF_MONTH);
        year = mcalender.get(Calendar.YEAR);
        month = mcalender.get(Calendar.MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                dd = (year + "-" + (month + 1) + "-" + dayOfMonth);
                FROM_DATE = String.valueOf(year);
                tv_from.setText(FROM_DATE);
            }
        };
        DatePickerDialog dpdialog = new DatePickerDialog(this, listener, year, month, day);
        mcalender.add(Calendar.MONTH, -1);
        dpdialog.show();
    }


    public void DateDialog2() {
        mcalender = Calendar.getInstance();
        day = mcalender.get(Calendar.DAY_OF_MONTH);
        year = mcalender.get(Calendar.YEAR);
        month = mcalender.get(Calendar.MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                dd = (year + "-" + (month + 1) + "-" + dayOfMonth);
                TO_DATE = String.valueOf(year);
                tv_to.setText(TO_DATE);
            }
        };
        DatePickerDialog dpdialog = new DatePickerDialog(this, listener, year, month, day);
        mcalender.add(Calendar.MONTH, -1);
        dpdialog.show();
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    public void submit() {
        if (TextUtils.isEmpty(FROM_DATE) || TextUtils.isEmpty(TO_DATE)) {
            functionCall.showtoast(this, "Please select Year");
            return;
        }
        functionCall.showprogressdialog("Please wait to complete", "Data Loading", progressDialog);
        value = "subDivision=" + SUB_DIVISION + "&acc_status=" + STATUS + "&company=500001%20-%20Hubli%20Electricity%20Supply%20Company%20Limited&" +
                "zone=" + ZONE + "&circle=" + CIRCLE + "&division=" + DIVISION + "&fromDate=" + FROM_DATE + "&toDate=" + TO_DATE + "";
        SendingData.OB_CB_Details8 ob_cb_details = sendingData.new OB_CB_Details8(handler, responseArrayList, value, obcbAdapter);
        ob_cb_details.execute();
    }

    //For BarChart part--------------------------------------------------------------------------------------------------
    private void barchart(ArrayList<Response> arrayList) {
        mBarChart.clear();
        double total1 = 0, total2 = 0, total3 = 0, total4 = 0, total5 = 0, total6 = 0, total7 = 0, total8 = 0, total9 = 0, total10 = 0;

        mBarChart.setPinchZoom(true);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawGridBackground(false);

        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(11f);
        l.setWordWrapEnabled(true);


        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(15f);
        leftAxis.setStartAtZero(false);

        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setSpaceTop(15f);
        rightAxis.setStartAtZero(false); // this replaces setStartAtZero(true)

        mBarChart.getAxisRight().setEnabled(false);

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mBarChart); // For bounds control
        mBarChart.setMarker(mv);
        mBarChart.setDrawGridBackground(false);

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();
        ArrayList<BarEntry> values3 = new ArrayList<>();
        ArrayList<BarEntry> values4 = new ArrayList<>();
        ArrayList<BarEntry> values5 = new ArrayList<>();
        ArrayList<BarEntry> values6 = new ArrayList<>();
        ArrayList<BarEntry> values7 = new ArrayList<>();
        ArrayList<BarEntry> values8 = new ArrayList<>();
        ArrayList<BarEntry> values9 = new ArrayList<>();
        ArrayList<BarEntry> values10 = new ArrayList<>();

        for (int i = 0, j=0; i < arrayList.size(); i++) {
            values1.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA2())));
            values2.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA3())));
            values3.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA4())));
            values4.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA5())));
            values5.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA6())));
            values6.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA7())));
            values7.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA8())));
            values8.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA9())));
            values9.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA10())));
            values10.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA11())));
        }

        BarDataSet set1, set2, set3, set4, set5, set6, set7, set8, set9, set10;
        set1 = new BarDataSet(values1, "INSTALLATIONS");
        set1.setColors(Color.BLUE);
        set2 = new BarDataSet(values2, "BILLED INSTALLATIONS");
        set2.setColors(Color.GRAY);
        set3 = new BarDataSet(values3, "CONSUMED UNITS");
        set3.setColors(Color.MAGENTA);
        set4 = new BarDataSet(values4, "BILLED CONSUMED UNITS");
        set4.setColors(Color.BLACK);
        set5 = new BarDataSet(values5, "OPENING BALANCE");
        set5.setColors(Color.RED);
        set6 = new BarDataSet(values6, "DEMAND");
        set6.setColors(Color.GREEN);
        set7 = new BarDataSet(values7, "BILLED DEMAND");
        set7.setColors(Color.YELLOW);
        set8 = new BarDataSet(values8, "NET PAYABLE");
        set8.setColors(Color.CYAN);
        set9 = new BarDataSet(values9, "COLLECTION AMOUNT");
        set9.setColors(Color.LTGRAY);
        set10 = new BarDataSet(values10, "CLOSING BALANCE");
        set10.setColors(Color.WHITE);

        BarData data = new BarData(set1, set2, set3, set4, set5, set6, set7, set8, set9, set10);
        data.setValueFormatter(new LargeValueFormatter());
        data.setDrawValues(false);
        mBarChart.setData(data);
        mBarChart.getBarData();
        mBarChart.invalidate();
        mBarChart.setExtraOffsets(0f, 10f, 0f, 10f);
        mBarChart.setViewPortOffsets(0f, 20f, 0f, 20f);

        for (int i = 0; i < arrayList.size(); i++) {
            total1 = total1 + Double.parseDouble(arrayList.get(i).getA2());
            total2 = total2 + Double.parseDouble(arrayList.get(i).getA3());
            total3 = total3 + Double.parseDouble(arrayList.get(i).getA4());
            total4 = total4 + Double.parseDouble(arrayList.get(i).getA5());
            total5 = total5 + Double.parseDouble(arrayList.get(i).getA6());
            total6 = total6 + Double.parseDouble(arrayList.get(i).getA7());
            total7 = total7 + Double.parseDouble(arrayList.get(i).getA8());
            total8 = total8 + Double.parseDouble(arrayList.get(i).getA9());
            total9 = total9 + Double.parseDouble(arrayList.get(i).getA10());
            total10 = total10 + Double.parseDouble(arrayList.get(i).getA11());
        }

        tv_total1.setText(functionCall.roundoff1(String.valueOf(total1)));
        tv_total2.setText(functionCall.roundoff1(String.valueOf(total2)));
        tv_total3.setText(functionCall.roundoff1(String.valueOf(total3)));
        tv_total4.setText(functionCall.roundoff1(String.valueOf(total4)));
        tv_total5.setText(functionCall.roundoff1(String.valueOf(total5)));
        tv_total6.setText(functionCall.roundoff1(String.valueOf(total6)));
        tv_total7.setText(functionCall.roundoff1(String.valueOf(total7)));
        tv_total8.setText(functionCall.roundoff1(String.valueOf(total8)));
        tv_total9.setText(functionCall.roundoff1(String.valueOf(total9)));
        tv_total10.setText(functionCall.roundoff1(String.valueOf(total10)));
    }
}