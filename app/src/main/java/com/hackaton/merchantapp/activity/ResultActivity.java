package com.hackaton.merchantapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.hackaton.merchantapp.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String result_value = intent.getStringExtra("RESULT_VALUE");
        TextView textResult = (TextView) findViewById(R.id.result);
        textResult.setText("code : "+result_value+" is Success.\n Thank You");

    }
}
