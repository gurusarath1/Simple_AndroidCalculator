package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class Debug_Window extends AppCompatActivity implements GLOBAL_CONSTS {

    String displayText = "";
    int state = 0;
    boolean dotUsed = false;
    int numberOfDecimalPlaces = 0;
    double Result = 0;
    double num1 = 0;
    double num2 = 0;
    String operation = "";
    String current_display = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug__window);

        Intent appDataIntent = getIntent();

        TextView txtView = (TextView) findViewById(R.id.num1_view);
        num1 = appDataIntent.getDoubleExtra(intent_Extra_NUM1,0);
        txtView.setText( "" + num1 );

        txtView = (TextView) findViewById(R.id.num2_view);
        num2 = appDataIntent.getDoubleExtra(intent_Extra_NUM2,0);
        txtView.setText( "" + num2 );

        txtView = (TextView) findViewById(R.id.result_view);
        Result =  appDataIntent.getDoubleExtra(intent_Extra_RESULT,0);
        txtView.setText( "" + Result );

        txtView = (TextView) findViewById(R.id.display_view);
        displayText = appDataIntent.getStringExtra(intent_Extra_DISPLAY);
        txtView.setText( "" + displayText );

        txtView = (TextView) findViewById(R.id.dot_used_view);
        dotUsed = appDataIntent.getBooleanExtra(intent_Extra_DOTUSED, false);
        txtView.setText( "" + dotUsed);

        txtView = (TextView) findViewById(R.id.num_decimal_places_view);
        numberOfDecimalPlaces = appDataIntent.getIntExtra(intent_Extra_NUM_DECIMAL_PLACES,0);
        txtView.setText( "" + numberOfDecimalPlaces );

        txtView = (TextView) findViewById(R.id.state_view);
        state = appDataIntent.getIntExtra(intent_Extra_STATE,0);
        txtView.setText( "" + state );

        txtView = (TextView) findViewById(R.id.operation_view);
        operation = appDataIntent.getStringExtra(intent_Extra_OPERATION);
        txtView.setText( "" + operation );

        txtView = (TextView) findViewById(R.id.current_display_view);
        current_display = appDataIntent.getStringExtra(intent_Extra_CURRENT_DISPLAY);
        txtView.setText( "" + current_display );
    }

    public void Back_button(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(intent_Extra_NUM1, num1);
        i.putExtra(intent_Extra_NUM2, num2);
        i.putExtra(intent_Extra_DISPLAY, displayText);
        i.putExtra(intent_Extra_DOTUSED, dotUsed);
        i.putExtra(intent_Extra_RESULT, Result);
        i.putExtra(intent_Extra_OPERATION, operation);
        i.putExtra(intent_Extra_NUM_DECIMAL_PLACES, numberOfDecimalPlaces);
        i.putExtra(intent_Extra_STATE, state);
        TextView txtView = findViewById(R.id.current_display_view);
        i.putExtra(intent_Extra_CURRENT_DISPLAY, txtView.getText());

        startActivity(i);

        return;
    }
}