package com.jabb.jabbsee.communicators;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.helpers.SerieListHelper;
import com.jabb.jabbsee.models.Library;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LibraryCommunicator {

        private final String TAG = Constants.LOGGING_TAG_PREFIX + LibraryCommunicator.class.getSimpleName();
        private AsyncHttpClient client;
        private RequestParams params;
        private final String URL_ADDRESS = "http://10.0.2.2:8080/JabbSeeAPI/library";

        public LibraryCommunicator(){
            init();
        }

        public void init(){
            Log.d(TAG,"LibraryCommunicator init()");
            //client = new AsyncHttpClient();
            //params = new RequestParams();
        }

        public void setParams(){
            Log.d(TAG,"LibraryCommunicator setParams()");
            params.put("serie", SerieListHelper.getInstance().getActiveSerieList().get(0));
        }

        Library library;

        public Library getLibrary() throws IOException {
            Log.d(TAG,"LibraryCommunicator getCommunication()");

            URL url = new URL(URL_ADDRESS);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(8000);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                Log.d(TAG, "Response code: " + responseCode);
            }

            //read GET request answer
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            Log.d(TAG + " Response from server ", response.toString());

            //Converting to java Library object
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                library = objectMapper.readValue(response.toString(), Library.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Failed to convert to java object");
            }


            /*client.get(URL, new JsonHttpResponseHandler(){

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
            */
            return library;
        }
}
