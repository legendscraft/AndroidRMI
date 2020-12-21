package com.example.mysecondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    /**Shared preferences */
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mName;
    private Button btnLogin;


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

}