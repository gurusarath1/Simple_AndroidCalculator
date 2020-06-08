package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    public void Back_button(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        return;
    }

    public void GitHub_button(View view)
    {
        Uri.Builder u_builder = new Uri.Builder();
        u_builder.scheme("https")
                .path("github.com/gurusarath1");

        Uri uri_to_github = u_builder.build();

        Intent i = new Intent(Intent.ACTION_VIEW, uri_to_github);

        if(i.resolveActivity(getPackageManager()) != null)
        {
            startActivity(i);
        }
    }

    public void LinkedIn_button(View view)
    {
        Uri uri_to_linkedin = Uri.parse("https://www.linkedin.com/in/gurusarath1/");

        Intent i = new Intent(Intent.ACTION_VIEW, uri_to_linkedin);

        if(i.resolveActivity(getPackageManager()) != null)
        {
            startActivity(i);
        }
    }
}