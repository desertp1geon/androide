
package com.example.iscip.factivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static org.koin.java.KoinJavaComponent.get;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iscip.basic.mvvm.BaseActivity;
import com.example.iscip.data.Fruit;
import com.example.iscip.databinding.ActivityMainBinding;
import com.example.iscip.sactivity.FruitInfoActivity;

import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = get(MainActivityViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
        setupButtons();
        observeViewModel();

        viewModel.loadFruits();
    }

    private void setupRecyclerView() {

        adapter = new FruitAdapter(this::openFruitInfo);

        binding.fruitsRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        binding.fruitsRecyclerView.setAdapter(adapter);
    }

    private void setupButtons() {

        binding.addFruitButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddFruitActivity.class
            );

            startActivity(intent);
        });
    }

    private void observeViewModel() {

        viewModel.getFruits().observe(this, fruits -> {

            if (fruits == null) {
                return;
            }

            adapter.setFruits(fruits);

            if (fruits.isEmpty()) {
                binding.emptyText.setVisibility(View.VISIBLE);
                binding.fruitsRecyclerView.setVisibility(View.GONE);
            } else {
                binding.emptyText.setVisibility(View.GONE);
                binding.fruitsRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getErrorMessage().observe(this, message -> {

            if (message != null) {
                Toast.makeText(
                        this,
                        message,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void openFruitInfo(Fruit fruit) {

        Intent intent = new Intent(
                MainActivity.this,
                FruitInfoActivity.class
        );

        intent.putExtra("fruit_id", fruit.getId());
        intent.putExtra("fruit_name", fruit.getName());
        intent.putExtra("fruit_type", fruit.getType());
        intent.putExtra("fruit_description", fruit.getDescription());
        intent.putExtra("fruit_calories", fruit.getCalories());

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (viewModel != null) {
            viewModel.loadFruits();
        }
    }

    @Override
    public void onCloseScope() {
    }
}
