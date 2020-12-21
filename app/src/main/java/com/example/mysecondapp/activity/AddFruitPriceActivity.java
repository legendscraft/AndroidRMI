package com.example.mysecondapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mysecondapp.R;
import com.example.mysecondapp.RMIInterface;
import com.example.mysecondapp.adapter.CustomAdapterAddPrice;
import com.example.mysecondapp.adapter.CustomAdapterViewFruit;
import com.example.mysecondapp.model.Fruit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class AddFruitPriceActivity extends AppCompatActivity {

    Client client;
    RMIInterface rmiInterface;
    boolean isConnected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fruit_price);

        if(!isConnected)connect();
        String  msg = rmiInterface.getAllFruitsWithoutPrice();
        Gson gson = new Gson();
        List<Fruit> fruits = gson.fromJson(msg,new TypeToken<List<Fruit>>(){}.getType());

        //show recyclerview list
        RecyclerView recyclerView = findViewById(R.id.rvFruitAdd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapterAddPrice(fruits));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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