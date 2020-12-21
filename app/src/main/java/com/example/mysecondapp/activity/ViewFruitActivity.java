package com.example.mysecondapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.mysecondapp.R;
import com.example.mysecondapp.RMIInterface;
import com.example.mysecondapp.adapter.CustomAdapterViewFruit;
import com.example.mysecondapp.model.Fruit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class ViewFruitActivity extends AppCompatActivity {
    private Button btnBack;

    Client client;
    RMIInterface rmiInterface;
    boolean isConnected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fruit);

        if (!isConnected)connect();
        String  msg = rmiInterface.getAllFruits();
        Gson gson = new Gson();
        List<Fruit> fruits = gson.fromJson(msg,new TypeToken<List<Fruit>>(){}.getType());

        RecyclerView recyclerView = findViewById(R.id.rvFruits);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapterViewFruit(fruits));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


       /* btnBack = (Button) findViewById(R.id.buttonBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });*/
    }

    /** Called when the user taps the Backbutton */
    /*public void goBack() {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/

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