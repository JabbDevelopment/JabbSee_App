package com.jabb.jabbsee.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;
import com.jabb.jabbsee.managers.AuthManager;

import java.io.IOException;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mUserName;
    private EditText mPassword;
    private View mProgressView;
    private View mLoginFormView;
    private Button mSignInButton;
    private AuthManager authManager;
    private final String TAG = Constants.LOGGING_TAG_PREFIX + LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authManager = new AuthManager();

        mUserName = (findViewById(R.id.userName));
        mPassword = findViewById(R.id.password);
        mSignInButton = findViewById(R.id.sign_in_button);

        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUserName.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, Constants.NO_USERNAME, Toast.LENGTH_SHORT).show();
                } else if(mPassword.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, Constants.NO_PASSWORD, Toast.LENGTH_SHORT).show();
                } else {
                    new getLoginAccess().execute();
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }




    private class getLoginAccess extends AsyncTask<Void, Void, Boolean> {

        public getLoginAccess(){}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Log.d(TAG, "Do in background");
            boolean success;
            try {
                Log.d(TAG, "Username: " + mUserName.getText().toString() +
                " Password: " + mPassword.getText().toString());
                 success = authManager.login(mUserName.getText().toString(), mPassword.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return success;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            //progressBar.setVisibility(View.GONE);

            if(!success){
                Log.e(TAG, "Failed to connect to server");
                //new showConnectionError(charSequence, getActivity()).showUnableToConnectDialog();
                return;
            }

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);

        }
    }


}

