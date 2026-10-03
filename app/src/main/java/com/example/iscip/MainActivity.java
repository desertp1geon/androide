package com.example.iscip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText nameInput;
    private EditText colorInput;
    private EditText weightInput;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        colorInput = findViewById(R.id.colorInput);
        weightInput = findViewById(R.id.weightInput);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> sendFruit());
    }

    private void sendFruit() {

        String name = nameInput.getText().toString().trim();
        String color = colorInput.getText().toString().trim();
        String weightText = weightInput.getText().toString().trim();

        if (name.isEmpty() || color.isEmpty() || weightText.isEmpty()) {

            Toast.makeText(
                    this,
                    "Заповніть всі поля",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        double weight = Double.parseDouble(weightText);

        Fruit fruit = new Fruit(
                name,
                color,
                weight
        );

        Intent intent = new Intent(
                MainActivity.this,
                SecondActivity.class
        );

        intent.putExtra("fruit", fruit);

        startActivity(intent);
    }
}