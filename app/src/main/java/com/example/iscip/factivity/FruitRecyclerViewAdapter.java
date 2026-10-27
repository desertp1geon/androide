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

public class FruitRecyclerViewAdapter
        extends RecyclerView.Adapter<FruitRecyclerViewAdapter.FruitItemHolder> {

    private List<Fruit> fruits = new ArrayList<>();

    public void setFruits(List<Fruit> newFruits) {
        fruits = new ArrayList<>(newFruits);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FruitItemHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.fruit_recycler_item,
                        parent,
                        false
                );

        return new FruitItemHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull FruitItemHolder holder,
            int position) {

        holder.bind(fruits.get(position));
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public static class FruitItemHolder
            extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final TextView typeTextView;
        private final TextView caloriesTextView;

        public FruitItemHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView =
                    itemView.findViewById(R.id.fruitItemName);

            typeTextView =
                    itemView.findViewById(R.id.fruitItemType);

            caloriesTextView =
                    itemView.findViewById(R.id.fruitItemCalories);
        }

        public void bind(Fruit fruit) {
            nameTextView.setText(fruit.getName());
            typeTextView.setText(fruit.getType());
            caloriesTextView.setText(
                    fruit.getCalories() + " ккал / 100 г"
            );
        }
    }
}