package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class WelcomeActivity extends AppCompatActivity {
    AppCompatButton logout;
    TextView welcomeText;
    String savedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        logout = findViewById(R.id.logout);
        welcomeText = findViewById(R.id.welcomeText);

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        if (flag) {
            savedName = preferences.getString("name", "Default Name");
            welcomeText.setText("Welcome, " + savedName); // Display the name in welcomeText
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("flag", false);
                editor.apply();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close WelcomeActivity
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close WelcomeActivity
    }
}
