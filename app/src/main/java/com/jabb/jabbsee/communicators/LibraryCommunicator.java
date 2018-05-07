package com.jabb.jabbsee.communicators;

import android.util.Log;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.helpers.SerieListHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LibraryCommunicator {

        private final String TAG = Constants.LOGGING_TAG_PREFIX + LibraryCommunicator.class.getSimpleName();
        private AsyncHttpClient client;
        private RequestParams params;
        private final String URL = "http://10.0.2.2:8080/JabbSeeAPI/serie";

        public void init(){
            Log.d(TAG,"LibraryCommunicator init()");
            client = new AsyncHttpClient();
            params = new RequestParams();

            setParams();
            getCommunication();

        }

        public void setParams(){
            Log.d(TAG,"LibraryCommunicator setParams()");
            params.put("serie", SerieListHelper.getActiveSerieList().get(0));
        }

        public void getCommunication(){
            Log.d(TAG,"LibraryCommunicator getCommunication()");
            client.post(URL, params, new JsonHttpResponseHandler(){

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    super.onSuccess(statusCode, headers, response);

                    Log.d(TAG, "Success "+ statusCode + ": " +response);

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                    super.onFailure(statusCode, headers, throwable, errorResponse);

                    Log.d(TAG, "Failure "+ statusCode + ": " +errorResponse);
                }
            });

        }
}
