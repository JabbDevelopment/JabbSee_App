package com.jabb.jabbsee.communicators;

import android.util.Base64;
import android.util.Log;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.models.User;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthCommunicator {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + AuthCommunicator.class.getSimpleName();
    private final String URL_ADDRESS_LOGIN = Constants.URL_ROOT_ADDRESS + "user";

    public AuthCommunicator(){}


    public String validateUserFromServer(User user) throws IOException {
        Log.d(TAG, "Username: " + user.getUsername() + " Password: " + user.getPassword());
        URL url = new URL(URL_ADDRESS_LOGIN);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(8000);
        connection.setRequestMethod("GET");


        connection.setDoInput(true);

        String userpassword = user.getUsername() + ":" + user.getPassword();
       // String encodedAuthorization = encoder.encodeAsString(userpassword.getBytes());
        byte[] encodedBytes = Base64.encode(userpassword.getBytes(), 2);//(userpassword.getBytes());
        String encodedAsString = new String(encodedBytes);
        connection.setRequestProperty("Authorization", "Basic " + encodedAsString);


        int responseCode = connection.getResponseCode();
        Log.d(TAG + "response message", connection.getResponseMessage());
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


// connection.setDoOutput(true);
//connection.setRequestProperty("Content-Type", "application/json"); //; charset=UTF-8
//connection.setDoInput(true);

// Gson gson = new Gson();
// String userAsJson = gson.toJson(user);

//Log.d(TAG, "User as json: " + userAsJson);

//sending user on request
        /*OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(userAsJson);
        out.close();*/