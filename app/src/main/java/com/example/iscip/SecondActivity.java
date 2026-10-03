package com.example.iscip;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    private TextView nameText;
    private TextView colorText;
    private TextView weightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameText = findViewById(R.id.nameText);
        colorText = findViewById(R.id.colorText);
        weightText = findViewById(R.id.weightText);

        Fruit fruit =
                (Fruit) getIntent().getSerializableExtra("fruit");

        if (fruit != null) {

            nameText.setText(
                    "Назва: " + fruit.getName()
            );

            colorText.setText(
                    "Колір: " + fruit.getColor()
            );

            weightText.setText(
                    "Вага: " + fruit.getWeight() + " г"
            );
        }
    }
}