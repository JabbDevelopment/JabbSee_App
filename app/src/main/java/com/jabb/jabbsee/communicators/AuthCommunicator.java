package com.jabb.jabbsee.communicators;

import android.util.Log;

import com.google.gson.Gson;
import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.models.User;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthCommunicator {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + AuthCommunicator.class.getSimpleName();
    private final String URL_ADDRESS_LOGIN = Constants.URL_ROOT_ADDRESS + "login";

    public AuthCommunicator(){}


    public String getToken(User user) throws IOException {
        Log.d(TAG, "Username: " + user.getUsername() + " Password: " + user.getPassword());
        URL url = new URL(URL_ADDRESS_LOGIN);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(8000);
        connection.setRequestMethod("POST");

        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json"); //; charset=UTF-8
        //connection.setDoInput(true);

        Gson gson = new Gson();
        String userAsJson = gson.toJson(user);

        Log.d(TAG, "User as json: " + userAsJson);

        //sending user on request
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(userAsJson);
        out.close();

        int responseCode = connection.getResponseCode();

        String tempToken = "hej token";
        if (responseCode != 200) {
            Log.d(TAG, "Response code: " + responseCode);
            return null;
        }
        return tempToken;

        //read POST request answer

        /*BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        Log.d(TAG, "Token returned from server: " + inputLine);

        */
    }
}
