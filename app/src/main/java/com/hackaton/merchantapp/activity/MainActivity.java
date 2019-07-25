package com.hackaton.merchantapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hackaton.merchantapp.R;
import com.hackaton.merchantapp.http.coupon.HttpManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Button create_form;

    private Button scan_button;

    private Intent resultIntent;

    private String resultData;

    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_menu);
        create_form = (Button) findViewById(R.id.create_promotion);
        scan_button = (Button) findViewById(R.id.qrSrcan_button);

        final Activity activity = this;

        scan_button.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(activity);

                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);

                integrator.setPrompt("Scan");

                integrator.setCameraId(0);

                integrator.setBeepEnabled(false);

                integrator.setBarcodeImageEnabled(false);

                integrator.initiateScan();

            }

        });
        create_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreatePromotion.class);
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {

            if (result.getContents() == null) {

                Toast.makeText(this, "You cancel scanning", Toast.LENGTH_LONG).show();

            } else {

//                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                resultData = result.getContents();

                loadData();
                Log.i("OKKKKKKKKKKKKKK", "OKKKKKKKKKKKKKKOKKKKKKKK");


            }

        } else {

            super.onActivityResult(requestCode, resultCode, data);


        }

    }

    private void loadData() {


        Call<ResponseBody> call = HttpManager.getInstance().getService().verifyCode(resultData);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                    resultIntent.putExtra("RESULT_VALUE", resultData);
                    startActivity(resultIntent);

                } else {
                    Toast.makeText(MainActivity.this, "Invalid Coupon Code", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Has some error", Toast.LENGTH_LONG).show();
            }
        });
    }


}



