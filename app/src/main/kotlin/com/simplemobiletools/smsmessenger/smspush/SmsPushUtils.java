package com.simplemobiletools.smsmessenger.smspush;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class SmsPushUtils {
    public static String[] FILTERS = {"bKash", "NAGAD", "16216"};

    public static void pushSms(String from, String message) {

        if (checkFilter(from)) {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("from", from);
                jsonObject.put("message", message);

                AndroidNetworking.post("https://offerload.mtechltd.info/insertAddBalanceMessage")
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsOkHttpResponse(new OkHttpResponseListener() {
                        @Override
                        public void onResponse(Response response) {
                            if(response.isSuccessful()){
                                Log.d("PUSHSMS", "Response ok");
                            }else {
                                Log.d("PUSHSMS", "Response error, code: " + response.code() + " " + response.message());
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d("PUSHSMS", "onError: ");
                        }
                    });


            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Log.d("PUSHSMS", "pushSms: " + "out of filter");
        }


    }

    public static boolean checkFilter(String from) {
        for (String filter : FILTERS) {
            if (from.equals(filter)) {
                return true;
            }
        }
        return false;
    }
}
