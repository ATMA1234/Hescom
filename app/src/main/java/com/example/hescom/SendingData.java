package com.example.hescom;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static com.example.hescom.Constant.BASE_URL;

public class SendingData {
    private ReceivingData receivingData = new ReceivingData();

    private String UrlPostConnection(String Post_Url, JSONObject jsonObject) throws IOException {
        String response;
        URL url = new URL(Post_Url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(4 * 60 * 1000);
        conn.setConnectTimeout(4 * 60 * 1000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
        writer.write(jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }
            response = responseBuilder.toString();
        } else response = "";
        return response;
    }

    private String UrlPostConnection2(String Post_Url, HashMap<String, String> datamap) throws IOException {
        String response;
        URL url = new URL(Post_Url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(4 * 60 * 1000);
        conn.setConnectTimeout(4 * 60 * 1000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
        writer.write(getPostDataString(datamap));
        writer.flush();
        writer.close();
        os.close();
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }
            response = responseBuilder.toString();
        } else response = "";
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    private String UrlGetConnection(String getUrl) throws IOException {
        String response;
        URL url = new URL(getUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(4 * 60 * 1000);
        conn.setConnectTimeout(4 * 60 * 1000);
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line);
            }
            response = responseBuilder.toString();
        } else response = "";
        return response;
    }

    //-----------------------------------------------Fetch------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class Dashborad_details1 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> arrayList;
        DashboardAdapter dashboardAdapter;
        Handler handler;
        String value = "";

        public Dashborad_details1(Handler handler, ArrayList<Response> arrayList, DashboardAdapter dashboardAdapter, String value) {
            this.handler = handler;
            this.arrayList = arrayList;
            this.dashboardAdapter = dashboardAdapter;
            this.value = value;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "homeDashBoardsIndividual?val=" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getDashboardDetails1(result, handler, arrayList, dashboardAdapter);
        }
    }

    //-----------------------------------------------Fetch------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class Dashborad_details2 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> arrayList;
        Handler handler;
        String value = "";

        public Dashborad_details2(Handler handler, ArrayList<Response> arrayList, String value) {
            this.handler = handler;
            this.arrayList = arrayList;
            this.value = value;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "homeDashBoardsIndividual?val=" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getDashboardDetails2(result, handler, arrayList);
        }
    }

    //-----------------------------------------------Fetch------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class Dashborad_details3 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> arrayList;
        Handler handler;
        String value = "";

        public Dashborad_details3(Handler handler, ArrayList<Response> arrayList, String value) {
            this.handler = handler;
            this.arrayList = arrayList;
            this.value = value;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "homeDashBoardsIndividual?val=" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getDashboardDetails3(result, handler, arrayList);
        }
    }

    //-----------------------------------------------Fetch------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class Dashborad_details4 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> arrayList;
        Handler handler;
        String value = "";

        public Dashborad_details4(Handler handler, ArrayList<Response> arrayList, String value) {
            this.handler = handler;
            this.arrayList = arrayList;
            this.value = value;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "homeDashBoardsIndividual?val=" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getDashboardDetails4(result, handler, arrayList);
        }
    }

    //-----------------------------------------------Fetch------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class Dashborad_details5 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> arrayList;
        Handler handler;
        String value = "";

        public Dashborad_details5(Handler handler, ArrayList<Response> arrayList, String value) {
            this.handler = handler;
            this.arrayList = arrayList;
            this.value = value;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "homeDashBoardsIndividual?val=" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getDashboardDetails5(result, handler, arrayList);
        }
    }

    //**********************************************For user Login***********************************************************************
    @SuppressLint("StaticFieldLeak")
    public class Login extends AsyncTask<String, String, String> {
        String response = "", name = "";
        GetsetValues getSetValues;
        Handler handler;
        String value = "";

        public Login(GetsetValues getSetValues, Handler handler, String value, String name) {
            this.getSetValues = getSetValues;
            this.handler = handler;
            this.value = value;
            this.name = name;
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> datamap=new HashMap<>();
            datamap.put("username", params[0]);
            datamap.put("password", params[1]);

            try {
                response = UrlPostConnection2(BASE_URL + "index12",datamap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.get_Details(result, handler, name);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details1 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBAdapter1 obcbAdapter1;

        public OB_CB_Details1(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBAdapter1 obcbAdapter1) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbAdapter1 = obcbAdapter1;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_monthlyTariffWise_G1_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails1(result, handler, responseArrayList, obcbAdapter1);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details2 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBAdapter2 obcbAdapter2;
        GetsetValues getsetValues;

        public OB_CB_Details2(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBAdapter2 obcbAdapter2, GetsetValues getsetValues) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbAdapter2 = obcbAdapter2;
            this.getsetValues=getsetValues;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_TariffWise_G2_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails2(result, handler, responseArrayList, obcbAdapter2, getsetValues);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details3 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBAdapter3 obcbAdapter3;
        GetsetValues getsetValues;

        public OB_CB_Details3(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBAdapter3 obcbAdapter3, GetsetValues getsetValues) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbAdapter3 = obcbAdapter3;
            this.getsetValues=getsetValues;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_statuswise_G4_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails3(result, handler, responseArrayList, obcbAdapter3, getsetValues);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details4 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBAdapter4 obcbAdapter4;
        GetsetValues getsetValues;

        public OB_CB_Details4(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBAdapter4 obcbAdapter4, GetsetValues getsetValues) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbAdapter4 = obcbAdapter4;
            this.getsetValues=getsetValues;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_summary_G3_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails4(result, handler, responseArrayList, obcbAdapter4, getsetValues);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details5 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBBilledAdapter1 obcbBilledAdapter1;

        public OB_CB_Details5(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBBilledAdapter1 obcbBilledAdapter1) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbBilledAdapter1 = obcbBilledAdapter1;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_Billed_monthlyTariffWise_G1_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails5(result, handler, responseArrayList, obcbBilledAdapter1);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details6 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBBilledAdapter2 obcbBilledAdapter2;
        GetsetValues getsetValues;

        public OB_CB_Details6(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBBilledAdapter2 obcbBilledAdapter2, GetsetValues getsetValues) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbBilledAdapter2 = obcbBilledAdapter2;
            this.getsetValues=getsetValues;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_billed_TariffWise_G2_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails6(result, handler, responseArrayList, obcbBilledAdapter2, getsetValues);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details7 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBBilledAdapter3 obcbBilledAdapter3;
        GetsetValues getsetValues;

        public OB_CB_Details7(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBBilledAdapter3 obcbBilledAdapter3, GetsetValues getsetValues) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbBilledAdapter3 = obcbBilledAdapter3;
            this.getsetValues=getsetValues;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_billed_summary_G3_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails7(result, handler, responseArrayList, obcbBilledAdapter3, getsetValues);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details8 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBBilledAdapter4 obcbBilledAdapter4;
        GetsetValues getsetValues;

        public OB_CB_Details8(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBBilledAdapter4 obcbBilledAdapter4) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbBilledAdapter4 = obcbBilledAdapter4;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_billed_statusWise_summary_G4_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails8(result, handler, responseArrayList, obcbBilledAdapter4);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details9 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBUnBilledAdapter1 obcbUnBilledAdapter1;

        public OB_CB_Details9(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBUnBilledAdapter1 obcbUnBilledAdapter1) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbUnBilledAdapter1 = obcbUnBilledAdapter1;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_unBilled_monthlyTariffWise_G1_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails9(result, handler, responseArrayList, obcbUnBilledAdapter1);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details10 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBUnBilledAdapter2 obcbUnBilledAdapter2;
        GetsetValues getsetValues;

        public OB_CB_Details10(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBUnBilledAdapter2 obcbUnBilledAdapter2,
                               GetsetValues getsetValues) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbUnBilledAdapter2 = obcbUnBilledAdapter2;
            this.getsetValues=getsetValues;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_unBilled_TariffWise_G2_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails10(result, handler, responseArrayList, obcbUnBilledAdapter2, getsetValues);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details11 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBUnBilledAdapter3 obcbUnBilledAdapter3;
        GetsetValues getsetValues;

        public OB_CB_Details11(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBUnBilledAdapter3 obcbUnBilledAdapter3,GetsetValues getsetValues) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbUnBilledAdapter3 = obcbUnBilledAdapter3;
            this.getsetValues=getsetValues;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_Billed_summary_G3_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails11(result, handler, responseArrayList, obcbUnBilledAdapter3,getsetValues);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class OB_CB_Details12 extends AsyncTask<String, String, String> {
        String response = "";
        ArrayList<Response> responseArrayList;
        Handler handler;
        String value = "";
        OBCBUnBilledAdapter4 obcbUnBilledAdapter4;
        GetsetValues getsetValues;

        public OB_CB_Details12(Handler handler, ArrayList<Response> responseArrayList, String value, OBCBUnBilledAdapter4 obcbUnBilledAdapter4) {
            this.handler = handler;
            this.responseArrayList = responseArrayList;
            this.value = value;
            this.obcbUnBilledAdapter4 = obcbUnBilledAdapter4;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                response = UrlGetConnection(BASE_URL + "ob_cb_unBilled_statuswise_G4_data?" + value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            receivingData.getObCbDetails12(result, handler, responseArrayList, obcbUnBilledAdapter4);
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------------
    @SuppressLint("StaticFieldLeak")
    public class BDA_Tariffwise_value extends AsyncTask<String, String, String> {
        Handler handler;
        String response = "";
        ArrayList<Response> arrayList;
        String value;
        GetsetValues getsetValues;

        public BDA_Tariffwise_value(Handler handler, ArrayList<Response> arrayList, String value, GetsetValues getsetValues) {
            this.handler = handler;
            this.arrayList = arrayList;
            this.value = value;
            this.getsetValues=getsetValues;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                response = UrlGetConnection(BASE_URL + "dashboard_g1_data?" + value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            receivingData.get_BDA_Tariffwise_value(s, handler, arrayList, getsetValues);
        }
    }

}
