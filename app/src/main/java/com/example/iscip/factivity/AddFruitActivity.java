
package com.example.iscip.factivity;

import android.os.Bundle;
import android.widget.Toast;

import static org.koin.java.KoinJavaComponent.get;

import com.example.iscip.basic.mvvm.BaseActivity;
import com.example.iscip.databinding.ActivityAddFruitBinding;

public class AddFruitActivity extends BaseActivity {

    private ActivityAddFruitBinding binding;
    private AddFruitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = get(AddFruitViewModel.class);

        binding = ActivityAddFruitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

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
            }
        });

        viewModel.getSaveSuccess().observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                Toast.makeText(
                        this,
                        "Фрукт успішно збережено",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
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

    @Override
    public void onCloseScope() {
    }
}
