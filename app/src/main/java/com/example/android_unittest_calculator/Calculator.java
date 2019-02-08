package com.example.android_unittest_calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class Calculator extends AppCompatActivity {
    private static final String TAG = "Calculator";

    String enteredString;
    String lastEnteredString;
    private TextView result;

    private String operand;

    private String operator;

    private Set<String> numbers;

    private Set<String> operators;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
    }

    private void initNumbers() {
        numbers = new HashSet<String>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Integer.toString(i));
        }
    }


    private void initOperators() {
        operators = new HashSet<String>();
        String[] ops = { "+", "-", "*", "/" };
        for (String operator : ops) {
            operators.add(operator);
        }
    }



    public void handleClick(View view) {
        Button clicked = (Button) view;
        String value = clicked.getText().toString();

        if (isNumerical(value)) {
            if (!isDefaultResult(result.getText().toString())) {
                value = result.getText().toString() + value;
            }
        } else if (isOperator(value)) {
            operand = result.getText().toString();
            operator = value;
        } else if (isClear(value)) {
            value = getString(R.string.result_default);
        } else {
            double a = Double.parseDouble(operand), b = Double
                    .parseDouble(result.getText().toString());

            if (operator.equals("+")) {
                value = Double.toString(a + b);
            }

            // Reset values.
            operator = null;
            operand = null;
        }

        result.setText(value);
    }


    private boolean isClear(String value) {
        return value.equals(getString(R.string.buttonClear));
    }


    private boolean isOperator(String value) {
        if (operators == null) {
            initOperators();
        }
        return operators.contains(value);
    }


    private boolean isDefaultResult(String value) {
        return value.equals(getString(R.string.result_default));
    }


    private boolean isNumerical(String value) {
        if (numbers == null) {
            initNumbers();
        }
        return numbers.contains(value);
    }

}