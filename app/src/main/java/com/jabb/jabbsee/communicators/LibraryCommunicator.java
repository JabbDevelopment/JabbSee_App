package com.jabb.jabbsee.communicators;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.models.Library;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LibraryCommunicator {

        private final String TAG = Constants.LOGGING_TAG_PREFIX + LibraryCommunicator.class.getSimpleName();
        private final String URL_ADDRESS = Constants.URL_ROOT_ADDRESS + "library";

        public LibraryCommunicator(){}


        public Library getLibrary() throws IOException {
            Log.d(TAG,"LibraryCommunicator getCommunication()");

            URL url = new URL(URL_ADDRESS);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(8000);
            connection.setRequestMethod("GET");

            Base64 encoder = new Base64();

            String userpassword = "lisa" + ":" + "lis";
            String encodedAuthorization = encoder.encodeAsString(userpassword.getBytes());
            connection.setRequestProperty("Authorization", "Basic "+
                    encodedAuthorization);



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

            Library library = null;

            //Converting to java Library object
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                library = objectMapper.readValue(response.toString(), Library.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Failed to convert to java object");
            }

            return library;
        }
}
