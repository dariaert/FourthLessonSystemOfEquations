package com.msaggik.fourthlessonsystemofequations;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // поля
    private float a, b, c;
    private int x1, x2;
    private TextView equation;
    private EditText inputX1, inputX2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equation = findViewById(R.id.equation);
        inputX1 = findViewById(R.id.inputX1);
        inputX2 = findViewById(R.id.inputX2);
        button = findViewById(R.id.button);

        randomCoefficient(100);

        equation.setText(a + "*X^2 + " + b + "*X + " + c + " = 0");

        x1 = (int) Math.round((Math.sqrt(Math.abs(b*b - 4*a*c)) - b) / (2*a));
        x2 = (int) Math.round((-Math.sqrt(Math.abs(b*b - 4*a*c)) - b) / (2*a));


        inputX1.setOnFocusChangeListener(focusListener);
        inputX2.setOnFocusChangeListener(focusListener);
        button.setOnClickListener(listener);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int inX1 = Integer.parseInt(inputX1.getText().toString());
            int inX2 = Integer.parseInt(inputX2.getText().toString());

            if (x1 == inX1 && x2 == inX2) {
                randomCoefficient(100);

                equation.setText(a + "*X^2 + " + b + "*X + " + c + " = 0");

                x1 = (int) Math.round((Math.sqrt(Math.abs(b*b - 4*a*c)) - b) / (2*a));
                x2 = (int) Math.round((-Math.sqrt(Math.abs(b*b - 4*a*c)) - b) / (2*a));
            } else {
                Toast.makeText(MainActivity.this, "Текущее решение не правильное", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {


            switch (view.getId()) {
                case R.id.inputX1:
                    if(!b) {
                        int inX1 = Integer.parseInt(inputX1.getText().toString());
                        if (x1 != inX1 | x2 !=inX1) {
                            inputX1.setTextColor(Color.RED);
                            Toast.makeText(MainActivity.this, "Введено не правильное значение X1", Toast.LENGTH_SHORT).show();
                        } else {
                            inputX1.setTextColor(0xFF177C3A);
                        }
                    }
                    break;
                case R.id.inputX2:
                    if(!b) {
                        int inX2 = Integer.parseInt(inputX2.getText().toString());
                        if (x1 != inX2 | x2 !=inX2) {
                            inputX2.setTextColor(Color.RED);
                            Toast.makeText(MainActivity.this, "Введено не правильное значение X2", Toast.LENGTH_SHORT).show();
                        } else {
                            inputX2.setTextColor(0xFF177C3A);
                        }
                    }
                    break;
            }
        }
    };

    private void randomCoefficient(int limit) {
        Random random = new Random();
        a = random.nextInt(limit);
        b = random.nextInt(limit);
        c = random.nextInt(limit);
    }
}