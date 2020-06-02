package com.example.simple_calculator;

import java.lang.Math;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String displayText = "";
    int state = 0;
    boolean dotUsed = false;
    int numberOfDecimalPlaces = 0;
    double Result = 0;
    double num1 = 0;
    double num2 = 0;
    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void updateDisplay(String x)
    {
        TextView d = (TextView)findViewById(R.id.display);
        d.setText(x);
    }

    public void button_number(View v)
    {
        Log.v("" + num1);

        int digit = 0;

        switch(v.getId())
        {
            case R.id.b0:
                digit = 0;
                break;
            case R.id.b1:
                digit = 1;
                break;
            case R.id.b2:
                digit = 2;
                break;
            case R.id.b3:
                digit = 3;
                break;
            case R.id.b4:
                digit = 4;
                break;
            case R.id.b5:
                digit = 5;
                break;
            case R.id.b6:
                digit = 6;
                break;
            case R.id.b7:
                digit = 7;
                break;
            case R.id.b8:
                digit = 8;
                break;
            case R.id.b9:
                digit = 9;
                break;
        }

        if(numberOfDecimalPlaces > 0)
        {
            if(state == 0)
            {
                num1 += (Math.pow(0.1,numberOfDecimalPlaces))*digit;
            } else if (state == 1)
            {
                num2 += (Math.pow(0.1,numberOfDecimalPlaces))*digit;
            }
            numberOfDecimalPlaces += 1;
        }

        if(state == 0)
        {
            num1 = (num1*10) + digit;
        }
        else if(state == 1)
        {
            num2 = (num2*10) + digit;
        }

        displayText  = displayText + digit;
        this.updateDisplay(displayText);

    }

    public void button_0(View v)
    {
        if(state == 0){
            num1 = (num1*10) + 1;
        }
        else if(state == 1){
            num2 = (num2*10) + 1;
        }

        if(displayText.equals("0")) return;

        displayText  = displayText + "0";
        this.updateDisplay(displayText);
    }

    public void button_dot(View v)
    {
        if(dotUsed) return;

        dotUsed = true;
        numberOfDecimalPlaces = 1;

        displayText  = displayText + ".";
        this.updateDisplay(displayText);
    }

    public void button_operation(View v)
    {
        if(state == 0)
        {
            switch (v.getId())
            {
                case R.id.bDiv:
                    operation = "/";
                    break;
                case R.id.bPlus:
                    operation = "+";
                    break;
                case R.id.bMinus:
                    operation = "-";
                    break;
                case R.id.bMult:
                    operation = "*";
                    break;
            }

            state = 1;
            displayText = "";
            dotUsed = false;
            return;
        }

        if(state == 1)
        {
            switch (operation) {
                case "+":
                    num1 = num1 + num2;
                    break;
                case "-":
                    num1 = num1 - num2;
                    break;
                case "/":
                    num1 = num1 / num2;
                    break;
                case "*":
                    num1 = num1 * num2;
                    break;
            }

            this.updateDisplay(""+num1);
            num2 = 0;
            displayText = "";
            Result = 0;
            dotUsed = false;

        }
    }



    public void button_equal(View v)
    {
        if(state == 1)
        {
            switch (operation) {
                case "+":
                    Result = num1 + num2;
                    break;
                case "-":
                    Result = num1 - num2;
                    break;
                case "/":
                    Result = num1 / num2;
                    break;
                case "*":
                    Result = num1 * num2;
                    break;
            }
        }

        this.updateDisplay(""+Result);
        state = 0;
        num1 = 0;
        num2 = 0;
        displayText = "";
        dotUsed = false;
        Result = 0;
    }
}