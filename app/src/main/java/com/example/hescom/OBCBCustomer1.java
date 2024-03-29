package com.example.hescom;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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

public class OBCBCustomer1 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
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
    EditText tv_month;
    TextView tv_total;
    Calendar mcalender;
    int day, month, year;
    String dd, date;
    BarChart mBarChart;
    Button submit;
    String value = "";
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    OBCBAdapter1 obcbAdapter;
    LinearLayout lin1, lin2;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case DIALOG_SUCCESS:
                    progressDialog.dismiss();
                    mBarChart.setVisibility(View.VISIBLE);
                    barchart(responseArrayList);
                    lin1.setVisibility(View.VISIBLE);
                    lin2.setVisibility(View.VISIBLE);
                    break;

                case DIALOG_FAILURE:
                    progressDialog.dismiss();
                    functionCall.showtoast(OBCBCustomer1.this, "No Data found");
                    break;

            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obcbcustomer1);
        initialization();
        populate_company_spinner();
        populate_tariff_spinner();
        populate_status_spinner();
    }

    //---------------------------------------------------------------------------------------------------------------------------
    public void initialization() {
        tv_total = findViewById(R.id.txt_total);
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
        tv_month = findViewById(R.id.from_date);
        tv_month.setOnClickListener(this);
        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);
        mBarChart = findViewById(R.id.barChart);
        arrayList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        responseArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.obcb_recycle);
        lin1 = findViewById(R.id.lin_layout1);
        lin2 = findViewById(R.id.lin_layout2);

        obcbAdapter = new OBCBAdapter1(this, responseArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(obcbAdapter);
    }

    //---------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.from_date) {
            DateDialog1();
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

    //---------------------------------------------------------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------------------------------------------------------------
    public void DateDialog1() {
        mcalender = Calendar.getInstance();
        day = mcalender.get(Calendar.DAY_OF_MONTH);
        year = mcalender.get(Calendar.YEAR);
        month = mcalender.get(Calendar.MONTH);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dd = (year + "-" + (month + 1) + "-" + dayOfMonth);
                date = functionCall.Parse_Date(dd);
                tv_month.setText(date);
            }
        };
        DatePickerDialog dpdialog = new DatePickerDialog(this, listener, year, month, day);
        mcalender.add(Calendar.MONTH, -1);
        dpdialog.show();
    }

    //--------------------------------------------------------------------------------------------------------------------------------\
    public void submit() {
        if (TextUtils.isEmpty(date)) {
            functionCall.showtoast(this, "Please select month");
            return;
        }
        functionCall.showprogressdialog("Please wait to complete", "Data Loading", progressDialog);
        value = "tariff=" + TARIFF + "&acc_status=" + STATUS + "&from=" + date + "&subDivision=" + SUB_DIVISION + "&company=" + COMPANY +
                "&zone=" + ZONE + "&circle=" + CIRCLE + "&division=" + DIVISION + "";
        SendingData.OB_CB_Details1 ob_cb_details = sendingData.new OB_CB_Details1(handler, responseArrayList, value, obcbAdapter);
        ob_cb_details.execute();
    }

    //For BarChart part--------------------------------------------------------------------------------------------------
    private void barchart(ArrayList<Response> arrayList) {
        double total = 0;
        ArrayList<BarEntry> yaxis = new ArrayList<>();
        for (int i = 0, j = 0; i < arrayList.size(); i++) {
            yaxis.add(new BarEntry(j++, Float.parseFloat(arrayList.get(i).getA2())));
        }

        final ArrayList<String> xaxis = new ArrayList<>();
        for (int i = 0, j=0; i < arrayList.size(); i++) {
            xaxis.add(j++, arrayList.get(i).getA3());
        }

        //X axis plotting
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setLabelRotationAngle(-90);
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xaxis));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(arrayList.size());

        //Bar chart title and colors
        BarDataSet barDataSet = new BarDataSet(yaxis, "Installations");
        barDataSet.setDrawIcons(false);
        barDataSet.setColors(Color.RED);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setDrawValues(false);
        data.setHighlightEnabled(false);
        mBarChart.setData(data);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mBarChart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawGridBackground(false);
        //  mChart.setDrawYLabels(false);

        //left axis
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setStartAtZero(false); // this replaces setStartAtZero(true)

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mBarChart); // For bounds control
        mBarChart.setMarker(mv);

        //right axis
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setStartAtZero(false);
        rightAxis.setEnabled(false);// this replaces setStartAtZero(true)


        //legend
        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(8f);
        l.setTextSize(10f);
        l.setXEntrySpace(0f);
        l.setWordWrapEnabled(true);

        //animation of the bar chart
        mBarChart.animateY(1000);
        mBarChart.invalidate();

        for (int i = 0; i < arrayList.size(); i++) {
            total = total + Double.parseDouble(arrayList.get(i).getA2());
        }
        tv_total.setText(functionCall.roundoff1(String.valueOf(total)));
    }
}
