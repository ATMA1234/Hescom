package com.example.hescom;

import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.hescom.Constant.DIALOG_FAILURE;
import static com.example.hescom.Constant.DIALOG_FAILURE1;
import static com.example.hescom.Constant.DIALOG_FAILURE2;
import static com.example.hescom.Constant.DIALOG_FAILURE3;
import static com.example.hescom.Constant.DIALOG_FAILURE4;
import static com.example.hescom.Constant.DIALOG_SUCCESS;
import static com.example.hescom.Constant.DIALOG_SUCCESS1;
import static com.example.hescom.Constant.DIALOG_SUCCESS2;
import static com.example.hescom.Constant.DIALOG_SUCCESS3;
import static com.example.hescom.Constant.DIALOG_SUCCESS4;
import static com.example.hescom.Constant.LOGIN_FAILURE;
import static com.example.hescom.Constant.LOGIN_SUCCESS;

public class ReceivingData {
    FunctionCall functionCall = new FunctionCall();

    //---------------------------------------------------------------------------------------------------------------------------
    void getDashboardDetails1(String result, Handler handler, ArrayList<Response> responseArrayList, DashboardAdapter dashboardAdapter) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            jsonArray1 = new JSONArray(jsonObject.getString("object1"));
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());
//                response.setA9(arrayList1.get(8).getValue());
                responseArrayList.add(response);
                dashboardAdapter.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getDashboardDetails2(String result, Handler handler, ArrayList<Response> responseArrayList) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            jsonArray1 = new JSONArray(jsonObject.getString("object2"));
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());
//                response.setA9(arrayList1.get(8).getValue());
                responseArrayList.add(response);
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS1);
                else handler.sendEmptyMessage(DIALOG_FAILURE1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE1);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getDashboardDetails3(String result, Handler handler, ArrayList<Response> responseArrayList) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            jsonArray1 = new JSONArray(jsonObject.getString("object4"));
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());
//                response.setA9(arrayList1.get(8).getValue());
                responseArrayList.add(response);
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS2);
                else handler.sendEmptyMessage(DIALOG_FAILURE2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE2);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getDashboardDetails4(String result, Handler handler, ArrayList<Response> responseArrayList) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            jsonArray1 = new JSONArray(jsonObject.getString("object3"));
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());
//                response.setA9(arrayList1.get(8).getValue());

                responseArrayList.add(response);
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS3);
                else handler.sendEmptyMessage(DIALOG_FAILURE3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE3);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getDashboardDetails5(String result, Handler handler, ArrayList<Response> responseArrayList) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            jsonArray1 = new JSONArray(jsonObject.getString("object5"));
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());
//                response.setA9(arrayList1.get(8).getValue());
                responseArrayList.add(response);
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS4);
                else handler.sendEmptyMessage(DIALOG_FAILURE4);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE4);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void get_Details(String result, Handler handler, String name) {
        result = result.replace("\n", "");
        if (result.equals(name)) {
            handler.sendEmptyMessage(LOGIN_SUCCESS);
        } else handler.sendEmptyMessage(LOGIN_FAILURE);
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails1(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBAdapter1 obcbAdapter1) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());

                responseArrayList.add(response);
                obcbAdapter1.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails2(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBAdapter2 obcbAdapter2, GetsetValues getsetValues) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                if (getsetValues.getMonth_flag().equals("N")) {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                    response.setA3(arrayList1.get(2).getValue());
                    response.setA4(arrayList1.get(3).getValue());
                } else {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setMonth(arrayList1.get(1).getValue());
                    response.setA2(arrayList1.get(2).getValue());
                    response.setA3(arrayList1.get(3).getValue());
                    response.setA4(arrayList1.get(4).getValue());
                }

                responseArrayList.add(response);
                obcbAdapter2.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails3(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBAdapter3 obcbAdapter3, GetsetValues getsetValues) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                if (getsetValues.getMonth_flag().equals("N")) {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                    response.setA3(arrayList1.get(2).getValue());
                } else {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setMonth(arrayList1.get(1).getValue());
                    response.setA2(arrayList1.get(2).getValue());
                    response.setA3(arrayList1.get(3).getValue());
                }
                responseArrayList.add(response);
                obcbAdapter3.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails4(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBAdapter4 obcbAdapter4, GetsetValues getsetValues) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                if (getsetValues.getMonth_flag().equals("N")) {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                } else {
                    response.setMonth(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                }
                responseArrayList.add(response);
                obcbAdapter4.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails5(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBBilledAdapter1 obcbBilledAdapter1) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());
                response.setA9(arrayList1.get(8).getValue());
                response.setA10(arrayList1.get(9).getValue());
                response.setA11(arrayList1.get(10).getValue());
                response.setA12(arrayList1.get(11).getValue());
                response.setA13(arrayList1.get(12).getValue());

                responseArrayList.add(response);
                obcbBilledAdapter1.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails6(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBBilledAdapter2 obcbBilledAdapter2,
                         GetsetValues getsetValues) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                if (getsetValues.getMonth_flag().equals("N")){
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                    response.setA3(arrayList1.get(2).getValue());
                    response.setA4(arrayList1.get(3).getValue());
                    response.setA5(arrayList1.get(4).getValue());
                    response.setA6(arrayList1.get(5).getValue());
                    response.setA7(arrayList1.get(6).getValue());
                    response.setA8(arrayList1.get(7).getValue());
                    response.setA9(arrayList1.get(8).getValue());
                    response.setA10(arrayList1.get(9).getValue());
                    response.setA11(arrayList1.get(10).getValue());
                    response.setA12(arrayList1.get(11).getValue());
                    response.setA13(arrayList1.get(12).getValue());
                }else {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                    response.setA3(arrayList1.get(2).getValue());
                    response.setA4(arrayList1.get(3).getValue());
                    response.setA5(arrayList1.get(4).getValue());
                    response.setA6(arrayList1.get(5).getValue());
                    response.setA7(arrayList1.get(6).getValue());
                    response.setA8(arrayList1.get(7).getValue());
                    response.setA9(arrayList1.get(8).getValue());
                    response.setA10(arrayList1.get(9).getValue());
                    response.setA11(arrayList1.get(10).getValue());
                    response.setA12(arrayList1.get(11).getValue());
                    response.setA13(arrayList1.get(12).getValue());
                    response.setMonth(arrayList1.get(13).getValue());
                }
                responseArrayList.add(response);
                obcbBilledAdapter2.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails7(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBBilledAdapter3 obcbBilledAdapter3,
                         GetsetValues getsetValues) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                if (getsetValues.getMonth_flag().equals("N")){
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                    response.setA3(arrayList1.get(2).getValue());
                    response.setA4(arrayList1.get(3).getValue());
                    response.setA5(arrayList1.get(4).getValue());
                    response.setA6(arrayList1.get(5).getValue());
                    response.setA7(arrayList1.get(6).getValue());
                    response.setA8(arrayList1.get(7).getValue());
                    response.setA9(arrayList1.get(8).getValue());
                    response.setA10(arrayList1.get(9).getValue());
                    response.setA11(arrayList1.get(10).getValue());
                }else {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setMonth(arrayList1.get(1).getValue());
                    response.setA2(arrayList1.get(2).getValue());
                    response.setA3(arrayList1.get(3).getValue());
                    response.setA4(arrayList1.get(4).getValue());
                    response.setA5(arrayList1.get(5).getValue());
                    response.setA6(arrayList1.get(6).getValue());
                    response.setA7(arrayList1.get(7).getValue());
                    response.setA8(arrayList1.get(8).getValue());
                    response.setA9(arrayList1.get(9).getValue());
                    response.setA10(arrayList1.get(10).getValue());
                    response.setA11(arrayList1.get(11).getValue());
                }
                responseArrayList.add(response);
                obcbBilledAdapter3.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails8(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBBilledAdapter4 obcbBilledAdapter4) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());
                response.setA9(arrayList1.get(8).getValue());
                response.setA10(arrayList1.get(9).getValue());
                response.setA11(arrayList1.get(10).getValue());

                responseArrayList.add(response);
                obcbBilledAdapter4.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails9(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBUnBilledAdapter1 obcbUnBilledAdapter1) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());
                response.setA9(arrayList1.get(8).getValue());
                response.setA10(arrayList1.get(9).getValue());

                responseArrayList.add(response);
                obcbUnBilledAdapter1.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails10(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBUnBilledAdapter2 obcbUnBilledAdapter2,
                          GetsetValues getsetValues) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                if (getsetValues.getMonth_flag().equals("N")) {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                    response.setA3(arrayList1.get(2).getValue());
                    response.setA4(arrayList1.get(3).getValue());
                    response.setA5(arrayList1.get(4).getValue());
                    response.setA6(arrayList1.get(5).getValue());
                    response.setA7(arrayList1.get(6).getValue());
                    response.setA8(arrayList1.get(7).getValue());
                    response.setA9(arrayList1.get(8).getValue());
                    response.setA10(arrayList1.get(9).getValue());
                } else {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                    response.setA3(arrayList1.get(2).getValue());
                    response.setA4(arrayList1.get(3).getValue());
                    response.setA5(arrayList1.get(4).getValue());
                    response.setA6(arrayList1.get(5).getValue());
                    response.setA7(arrayList1.get(6).getValue());
                    response.setA8(arrayList1.get(7).getValue());
                    response.setA9(arrayList1.get(8).getValue());
                    response.setA10(arrayList1.get(9).getValue());
                    response.setMonth(arrayList1.get(10).getValue());
                }
                responseArrayList.add(response);
                obcbUnBilledAdapter2.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails11(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBUnBilledAdapter3 obcbUnBilledAdapter3,
                          GetsetValues getsetValues) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                if (getsetValues.getMonth_flag().equals("N")) {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(arrayList1.get(1).getValue());
                    response.setA3(arrayList1.get(2).getValue());
                    response.setA4(arrayList1.get(3).getValue());
                    response.setA5(arrayList1.get(4).getValue());
                    response.setA6(arrayList1.get(5).getValue());
                    response.setA7(arrayList1.get(6).getValue());
                    response.setA8(arrayList1.get(7).getValue());
                } else {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setMonth(arrayList1.get(1).getValue());
                    response.setA2(arrayList1.get(2).getValue());
                    response.setA3(arrayList1.get(3).getValue());
                    response.setA4(arrayList1.get(4).getValue());
                    response.setA5(arrayList1.get(5).getValue());
                    response.setA6(arrayList1.get(6).getValue());
                    response.setA7(arrayList1.get(7).getValue());
                    response.setA8(arrayList1.get(8).getValue());
                }
                responseArrayList.add(response);
                obcbUnBilledAdapter3.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void getObCbDetails12(String result, Handler handler, ArrayList<Response> responseArrayList, OBCBUnBilledAdapter4 obcbUnBilledAdapter4) {
        responseArrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                response.setA1(arrayList1.get(0).getValue());
                response.setA2(arrayList1.get(1).getValue());
                response.setA3(arrayList1.get(2).getValue());
                response.setA4(arrayList1.get(3).getValue());
                response.setA5(arrayList1.get(4).getValue());
                response.setA6(arrayList1.get(5).getValue());
                response.setA7(arrayList1.get(6).getValue());
                response.setA8(arrayList1.get(7).getValue());

                responseArrayList.add(response);
                obcbUnBilledAdapter4.notifyDataSetChanged();
                if (responseArrayList.size() > 0)
                    handler.sendEmptyMessage(DIALOG_SUCCESS);
                else handler.sendEmptyMessage(DIALOG_FAILURE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DIALOG_FAILURE);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    void get_BDA_Tariffwise_value(String result, Handler handler, ArrayList<Response> arrayList, GetsetValues getsetValues) {
        arrayList.clear();
        JSONArray jsonArray1, jsonArray2;
        try {
            jsonArray1 = new JSONArray(result);
            for (int i = 0; i < jsonArray1.length(); i++) {
                ArrayList<Response> arrayList1 = new ArrayList<>();
                Response response = new Response();
                jsonArray2 = new JSONArray(jsonArray1.getString(i));
                for (int j = 0; j < jsonArray2.length(); j++) {
                    Response response1 = new Response();
                    if (jsonArray2.getString(j).equals("null")) {
                        response1.setValue("0");
                    } else response1.setValue(jsonArray2.getString(j));
                    arrayList1.add(response1);
                }
                if (getsetValues.getMonth_flag().equals("N")) {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setA2(functionCall.roundoff1(arrayList1.get(1).getValue()));
                    response.setA3(functionCall.roundoff1(arrayList1.get(2).getValue()));
                    response.setA4(functionCall.roundoff1(arrayList1.get(3).getValue()));
                    response.setA5(functionCall.roundoff1(arrayList1.get(4).getValue()));
                    response.setA6(functionCall.roundoff1(arrayList1.get(5).getValue()));
                    response.setA7(functionCall.roundoff1(arrayList1.get(6).getValue()));
                    response.setA8(functionCall.roundoff1(arrayList1.get(7).getValue()));
                } else {
                    response.setA1(arrayList1.get(0).getValue());
                    response.setMonth(arrayList1.get(1).getValue());
                    response.setA2(functionCall.roundoff1(arrayList1.get(2).getValue()));
                    response.setA3(functionCall.roundoff1(arrayList1.get(3).getValue()));
                    response.setA4(functionCall.roundoff1(arrayList1.get(4).getValue()));
                    response.setA5(functionCall.roundoff1(arrayList1.get(5).getValue()));
                    response.setA6(functionCall.roundoff1(arrayList1.get(6).getValue()));
                    response.setA7(functionCall.roundoff1(arrayList1.get(7).getValue()));
                    response.setA8(functionCall.roundoff1(arrayList1.get(8).getValue()));
                }
                arrayList.add(response);
            }

            if (arrayList.size() > 0)
                handler.sendEmptyMessage(DIALOG_SUCCESS);
            else handler.sendEmptyMessage(DIALOG_FAILURE);
        } catch (JSONException e) {
            handler.sendEmptyMessage(DIALOG_FAILURE);
            e.printStackTrace();
        }
    }

}
