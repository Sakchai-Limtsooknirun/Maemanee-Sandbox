package com.hackaton.merchantapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hackaton.merchantapp.R;

public class ResultActivity extends AppCompatActivity {

    private Button backMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        backMain = (Button) findViewById(R.id.backmain);
        Intent intent = getIntent();
        String result_value = intent.getStringExtra("RESULT_VALUE");
        TextView textResult = (TextView) findViewById(R.id.result);
        textResult.setText("QR code: " + result_value + " has been used.");

        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
