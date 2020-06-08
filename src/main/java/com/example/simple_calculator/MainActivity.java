package com.example.simple_calculator;

import java.lang.Math;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GLOBAL_CONSTS {

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

    @Override
    protected void onResume() {
        super.onResume();

        Intent appDataIntent = getIntent();
        num1 = appDataIntent.getDoubleExtra(intent_Extra_NUM1,0);
        num2 = appDataIntent.getDoubleExtra(intent_Extra_NUM2,0);
        Result =  appDataIntent.getDoubleExtra(intent_Extra_RESULT,0);
        displayText = appDataIntent.getStringExtra(intent_Extra_DISPLAY);

        if(displayText == null)
            displayText = "";

        TextView txtView = findViewById(R.id.display);
        if(appDataIntent.getStringExtra(intent_Extra_CURRENT_DISPLAY) != null)
            txtView.setText(appDataIntent.getStringExtra(intent_Extra_CURRENT_DISPLAY));
        else
            txtView.setText("0");

        dotUsed = appDataIntent.getBooleanExtra(intent_Extra_DOTUSED, false);
        numberOfDecimalPlaces = appDataIntent.getIntExtra(intent_Extra_NUM_DECIMAL_PLACES,0);
        state = appDataIntent.getIntExtra(intent_Extra_STATE,0);
        operation = appDataIntent.getStringExtra(intent_Extra_OPERATION);

        if(operation == null) operation = "";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuX) {
        super.onCreateOptionsMenu(menuX);
        MenuInflater menu_inf = getMenuInflater();
        menu_inf.inflate(R.menu.menu1, menuX);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.debug_toast_menu:
                Context context = getApplicationContext();
                CharSequence text = "num1 = " + num1 +
                    "\nnum2 = " + num2 +
                    "\nResult = " + Result +
                        "\nDisplay = " + displayText +
                        "\nstate = " + state +
                        "\ndotUsed = " +  dotUsed +
                        "\nnumber of decimal places = " + numberOfDecimalPlaces +
                        "\noperation = " + operation;

                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;

            case R.id.social_media_menu:
                Context context2 = getApplicationContext();
                CharSequence text2 = "Go to Credits activity to connect via social media";
                int duration2 = Toast.LENGTH_SHORT;

                Toast toast2 = Toast.makeText(context2, text2, duration2);
                toast2.show();
                break;

            case R.id.debug_menu:
                this.DebugButton(null);
                break;
            case R.id.credit_menu:
                this.OpenCreditButton(null);
                break;
        }

        return true;
    }

    public void OpenCreditButton(View view)
    {
        Intent i = new Intent(this, Credits.class);
        startActivity(i);
    }

    public void DebugButton(View view)
    {
        Intent i = new Intent(this, Debug_Window.class);
        i.putExtra(intent_Extra_NUM1, num1);
        i.putExtra(intent_Extra_NUM2, num2);
        i.putExtra(intent_Extra_DISPLAY, displayText);
        i.putExtra(intent_Extra_DOTUSED, dotUsed);
        i.putExtra(intent_Extra_RESULT, Result);
        i.putExtra(intent_Extra_OPERATION, operation);
        i.putExtra(intent_Extra_NUM_DECIMAL_PLACES, numberOfDecimalPlaces);
        i.putExtra(intent_Extra_STATE, state);
        TextView txtView = findViewById(R.id.display);
        i.putExtra(intent_Extra_CURRENT_DISPLAY, txtView.getText());

        startActivity(i);
    }

    private void updateDisplay(String x)
    {
        TextView d = (TextView)findViewById(R.id.display);
        d.setText(x);
    }

    public void button_ClearAll(View v)
    {
        displayText = "";
        state = 0;
        dotUsed = false;
        numberOfDecimalPlaces = 0;
        Result = 0;
        num1 = 0;
        num2 = 0;
        operation = "";
        updateDisplay("0");
    }

    public void button_number(View v)
    {
        Log.v("Before Num1 = ",""+num1);
        Log.v("Before Num2 = ",""+num2);
        Log.v("Before Result = ",""+Result);

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
                num1 += (Math.pow(0.1, numberOfDecimalPlaces))*digit;
            } else if (state == 1)
            {
                num2 += (Math.pow(0.1, numberOfDecimalPlaces))*digit;
            }
            numberOfDecimalPlaces += 1;
            displayText  = displayText + digit;
            this.updateDisplay(displayText);
            return;
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
        if(numberOfDecimalPlaces > 0)
        {
            numberOfDecimalPlaces += 1;
            displayText  = displayText + '0';
            this.updateDisplay(displayText);
            return;
        }

        if(state == 0){
            num1 = (num1*10);
        }
        else if(state == 1){
            num2 = (num2*10);
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
            numberOfDecimalPlaces = 0;
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

            this.updateDisplay(""+num1);
            num2 = 0;
            displayText = "";
            Result = num1;
            dotUsed = false;
            numberOfDecimalPlaces = 0;

        }
    }



    public void button_equal(View v)
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
                default:
                    Result = num1;
            }

        this.updateDisplay(""+Result);
        state = 0;
        num1 = Result;
        num2 = 0;
        displayText = "";
        dotUsed = false;
        Result = 0;
    }
}