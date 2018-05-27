package com.jabb.jabbsee.communicators;

import android.util.Log;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.models.User;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthCommunicator {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + AuthCommunicator.class.getSimpleName();
    private final String URL_ADDRESS_LOGIN = Constants.URL_ROOT_ADDRESS + "user";

    public AuthCommunicator(){}


    public User validateUserFromServer(User user) throws IOException {
        Log.d(TAG, "Username: " + user.getUsername() + " Password: " + user.getPassword());
        URL url = new URL(URL_ADDRESS_LOGIN);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(8000);
        connection.setRequestMethod("GET");


        connection.setDoInput(true);

        String userpassword = user.getUsername() + ":" + user.getPassword();
        String encodedAsString = new String(Base64.encodeBase64(userpassword.getBytes()));
        connection.setRequestProperty("Authorization", "Basic " + encodedAsString);


        int responseCode = connection.getResponseCode();
        //Log.d(TAG + "response message", connection.getResponseMessage());
        if (responseCode != 200) {
            Log.d(TAG, "Response code: " + responseCode);
            return null;
        }

        return user;
    }
}


   /* BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
                }
                reader.close();

                */