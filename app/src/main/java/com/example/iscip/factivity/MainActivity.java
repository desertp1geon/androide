package com.example.iscip.factivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.iscip.sactivity.FruitInfoActivity;
import com.example.iscip.databinding.ActivityMainBinding;
import com.example.iscip.data.Fruit;
import com.example.iscip.data.FruitRepository;
import com.example.iscip.data.FruitDatabase;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private FruitRepository fruitRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Створюємо репозиторій
        fruitRepository = FruitDatabase.getInstance(getApplicationContext());

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getFruitModel().observe(this, model -> {
            if (model == null) {
                return;
            }

            if (model.isShowError()) {
                model.setShowError(false);
                viewModel.getFruitModel().setValue(model);

                Toast.makeText(
                        this,
                        "Будь ласка, заповніть усі поля",
                        Toast.LENGTH_SHORT
                ).show();

            } else if (model.isNavigateToNext()) {
                Fruit fruit = new Fruit(
                        0,
                        model.getName().trim(),
                        model.getType().trim(),
                        model.getDescription().trim(),
                        model.getCalories().trim()
                );

                // Скидаємо прапорець, щоб не повторити перехід.
                model.setNavigateToNext(false);
                viewModel.getFruitModel().setValue(model);

                openFruitInfo(fruit);
            }
        });
    }

    private void openFruitInfo(Fruit fruit) {
        Intent intent = new Intent(
                MainActivity.this,
                FruitInfoActivity.class
        );

        intent.putExtra("fruit_name", fruit.getName());
        intent.putExtra("fruit_type", fruit.getType());
        intent.putExtra(
                "fruit_description",
                fruit.getDescription()
        );
        intent.putExtra("fruit_calories", fruit.getCalories());

        startActivity(intent);
    }
}