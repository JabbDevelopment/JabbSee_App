package com.jabb.jabbsee.communicators;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.managers.AuthManager;
import com.jabb.jabbsee.models.Library;
import com.jabb.jabbsee.models.User;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LibraryCommunicator {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + LibraryCommunicator.class.getSimpleName();
    private final String URL_ADDRESS = Constants.URL_ROOT_ADDRESS + "library";
    private AuthManager authManager;

    public LibraryCommunicator() {
        authManager = new AuthManager();
    }

    public Library getLibrary() throws IOException {
        Log.d(TAG, "LibraryCommunicator getCommunication()");

        URL url = new URL(URL_ADDRESS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(8000);
        connection.setRequestMethod("GET");

        String encodedUserPassword = getEncodedUserPass();
        connection.setRequestProperty("Authorization", "Basic " + encodedUserPassword);

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            Log.d(TAG, "Response code: " + responseCode);
        }

        String responseString = readInput(connection.getInputStream());
        Log.d(TAG + " Response from server ", responseString);
        Library library = convertToLibrary(responseString);
        connection.disconnect();
        return library;
    }

    public Library updateLibrary(Library libraryToBeUpdated) throws IOException {
        URL url = new URL(URL_ADDRESS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(8000);
        connection.setRequestMethod("POST");

        String encodedUserPassword = getEncodedUserPass();
        connection.setRequestProperty("Authorization", "Basic " + encodedUserPassword);

        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json"); //; charset=UTF-8
        connection.setDoInput(true);

        Gson gson = new Gson();
        String libraryAsJson = gson.toJson(libraryToBeUpdated);
        Log.d(TAG, "Library as json: " + libraryAsJson);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(libraryAsJson);
        out.close();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            Log.d(TAG, "Response code: " + responseCode);
        }

        String responseString = readInput(connection.getInputStream());
        Log.d(TAG + " Response from server ", responseString);

        Library updatedLibrary = convertToLibrary(responseString);

        return updatedLibrary;
    }

    private String getEncodedUserPass() {
        User activeUser = authManager.getLoggedInUser();
        Log.d(TAG, "ActiveUser: " + activeUser.getUsername());
        String userpassword = activeUser.getUsername() + ":" + activeUser.getPassword();
        String encodedAsString = new String(Base64.encodeBase64(userpassword.getBytes()));
        return encodedAsString;
    }

    private Library convertToLibrary(String responseString) {
        Library library = null;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            library = objectMapper.readValue(responseString, Library.class);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Failed to convert to java object");
        }
        return library;
    }

    private String readInput(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }
}
