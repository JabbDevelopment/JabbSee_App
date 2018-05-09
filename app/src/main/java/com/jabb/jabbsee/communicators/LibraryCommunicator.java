package com.jabb.jabbsee.communicators;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.helpers.SerieListHelper;
import com.jabb.jabbsee.models.Library;
import com.jabb.jabbsee.models.Serie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class LibraryCommunicator {

        private final String TAG = Constants.LOGGING_TAG_PREFIX + LibraryCommunicator.class.getSimpleName();
        private AsyncHttpClient client;
        private RequestParams params;
        private final String URL = "http://10.0.2.2:8080/JabbSeeAPI/library";

        public LibraryCommunicator(){
            init();
        }

        public void init(){
            Log.d(TAG,"LibraryCommunicator init()");
            client = new AsyncHttpClient();
            params = new RequestParams();
        }

        public void setParams(){
            Log.d(TAG,"LibraryCommunicator setParams()");
            params.put("serie", SerieListHelper.getInstance().getActiveSerieList().get(0));
        }

        Library library;

        public Library getLibrary(){
            Log.d(TAG,"LibraryCommunicator getCommunication()");

            library = new Library();
            library.setSeriesList(new ArrayList<Serie>());

            client.get(URL, new JsonHttpResponseHandler(){

                @Override
                public synchronized void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    super.onSuccess(statusCode, headers, response);

                    Log.d(TAG, "Success "+ statusCode + ": " +response);

                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        Log.d(TAG, "Library before objectMapper: " + library.toString());
                        library = objectMapper.readValue(response.toString(), Library.class);
                        Log.d(TAG, "Library after objectMapper: " + library.toString());


                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d(TAG, "------------Failed to convert to java object----------");
                    }



                    //library = new Gson().fromJson(response.toString(), Library.class);
                    //Log.d(TAG, "after gson transformation!!!!!!!!!!!!!!!!!!!");
                    //Log.d(TAG, "Library: " + library.toString());

                }

                @Override
                public synchronized void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                    super.onFailure(statusCode, headers, throwable, errorResponse);

                    Log.d(TAG, "Failure "+ statusCode + ": " +errorResponse);
                    //library = null;
                }
            });
           //Log.d(TAG, "library object from gson" + library.toString());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return library;
        }
}
