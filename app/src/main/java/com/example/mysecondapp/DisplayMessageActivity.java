package com.example.mysecondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    /**Shared preferences */
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mName;
    private Button btnLogout;
    private Button btnViewFruits;
    private Button btnAddFruitPrice;
    private Button btnUpdateFruitPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        mName = (EditText) findViewById(R.id.editTextTextPersonName);
        btnLogout = (Button) findViewById(R.id.button2);
        btnViewFruits = (Button) findViewById(R.id.viewFruits);
        btnAddFruitPrice = (Button) findViewById(R.id.addFruitPrice);
        btnUpdateFruitPrice = (Button) findViewById(R.id.updateFruitPrice);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save username
                mEditor.clear();
                mEditor.commit();

                logoutUser();
            }
        });
        btnViewFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFruits();
            }
        });
        btnAddFruitPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFruitPrice();
            }
        });
        btnUpdateFruitPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFruitPrice();
            }
        });
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome "+message);

    }
    /** Called when the user taps the View Fruits button */
    public void viewFruits() {
        // Do something in response to button
        Intent intent = new Intent(this, ViewFruitActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Add Fruits button */
    public void addFruitPrice() {
        // Do something in response to button
        Intent intent = new Intent(this, AddFruitPriceActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Update Fruits button */
    public void updateFruitPrice() {
        // Do something in response to button
        Intent intent = new Intent(this, UpdateFruitPriceActivity.class);
        startActivity(intent);
    }
    /** Called when the user taps the Logout button */
    public void logoutUser() {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}