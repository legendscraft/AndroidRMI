package com.example.mysecondapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.model.Fruit;

import java.util.List;

public class CustomAdapterUpdatePrice extends RecyclerView.Adapter<CustomAdapterUpdatePrice.ViewHolder> {
    private List<Fruit> fruits;
    public CustomAdapterUpdatePrice(List<Fruit> fruits){
        this.fruits = fruits;
    }

    @Override
    public CustomAdapterUpdatePrice.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_update_price, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(CustomAdapterUpdatePrice.ViewHolder holder, int position) {
        holder.textView.setText(this.fruits.get(position).getName());
        holder.price.setText("KSH "+this.fruits.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return this.fruits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private TextView price;
        private ImageView addPriceButton;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.textView = view.findViewById(R.id.tvFruitName);
            this.price = view.findViewById(R.id.tvFruitPrice);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.textView.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}