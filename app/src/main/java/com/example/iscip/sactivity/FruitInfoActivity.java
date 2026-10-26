
package com.example.iscip.sactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iscip.R;

public class FruitInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_info);

        String name = getIntent().getStringExtra("fruit_name");
        String type = getIntent().getStringExtra("fruit_type");
        String description =
                getIntent().getStringExtra("fruit_description");
        String calories =
                getIntent().getStringExtra("fruit_calories");

        TextView nameText = findViewById(R.id.fruitName);
        TextView typeText = findViewById(R.id.fruitType);
        TextView descriptionText =
                findViewById(R.id.fruitDescription);
        TextView caloriesText =
                findViewById(R.id.fruitCalories);

        nameText.setText(name == null ? "Фрукт" : name);
        typeText.setText(type == null ? "" : type);
        descriptionText.setText(
                description == null ? "" : description
        );
        caloriesText.setText(
                calories == null ? "—" : calories + " ккал / 100 г"
        );
    }
}
