package com.example.mysecondapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysecondapp.R;
import com.example.mysecondapp.RMIInterface;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    /**Shared preferences */
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mName;
    private Button btnLogin;

    Client client;
    RMIInterface rmiInterface;
    boolean isConnected=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Shared Preferences*/
        mName = (EditText) findViewById(R.id.editTextTextPersonName);
        btnLogin = (Button) findViewById(R.id.button);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkSharedPreferences();

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }

        if(!isConnected)connect();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save username
                String edit_message = mName.getText().toString();
                mEditor.putString(getString(R.string.edit_message), edit_message);
                mEditor.commit();
                sendMessage();
            }
        });
    }
    /* Shared Preferences */
    private void checkSharedPreferences(){
        String edit_message = mPreferences.getString(getString(R.string.edit_message), "");

        mName.setText(edit_message);
    }
    /** Called when the user taps the Login button */
    public void sendMessage() {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void connect() {
        try{
            CallHandler callHandler = new CallHandler();
            client = new Client("192.168.190.108", 1099, callHandler);
            rmiInterface = (RMIInterface) client.getGlobal(RMIInterface.class);
            isConnected=true;
            Toast.makeText(this, "RMI connected.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("RMIOUT", e.getMessage());
            Toast.makeText(this, "RMI connection failed.", Toast.LENGTH_SHORT).show();
        }
    }

}