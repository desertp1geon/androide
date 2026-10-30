
package com.example.iscip.factivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iscip.R;
import com.example.iscip.data.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {

    public interface OnFruitClickListener {
        void onFruitClick(Fruit fruit);
    }

    private final List<Fruit> fruits = new ArrayList<>();
    private final OnFruitClickListener listener;

    public FruitAdapter(OnFruitClickListener listener) {
        this.listener = listener;
    }

    public void setFruits(List<Fruit> newFruits) {
        fruits.clear();

        if (newFruits != null) {
            fruits.addAll(newFruits);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_recycler_item, parent, false);

        return new FruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull FruitViewHolder holder,
            int position
    ) {
        Fruit fruit = fruits.get(position);

        holder.name.setText(fruit.getName());
        holder.type.setText(fruit.getType());
        holder.calories.setText(fruit.getCalories() + " kcal / 100 g");

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFruitClick(fruit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    static class FruitViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView type;
        TextView calories;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.fruitItemName);
            type = itemView.findViewById(R.id.fruitItemType);
            calories = itemView.findViewById(R.id.fruitItemCalories);
        }
    }
}
