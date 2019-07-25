package com.hackaton.merchantapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hackaton.merchantapp.R;
import com.hackaton.merchantapp.http.promotion.HttpManager;
import com.hackaton.merchantapp.model.Promotion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreatePromotion extends AppCompatActivity {

    String Name, Description, ImagePath, Quantity, Product, OriginalPrice, DiscountPrice;
    EditText name, description, imagePath, quantity, product, originalPrice, discountPrice;
    TextView show;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        imagePath = (EditText) findViewById(R.id.imagePath);
        quantity = (EditText) findViewById(R.id.quantity);
        product = (EditText) findViewById(R.id.product);
        originalPrice = (EditText) findViewById(R.id.originalPrice);
        discountPrice = (EditText) findViewById(R.id.discountPrice);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                try {

                    Promotion promotion = new Promotion();
                    promotion.setTitleName(name.getText().toString());
                    promotion.setDescription(description.getText().toString());
                    promotion.setImagePath(imagePath.getText().toString());
                    promotion.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    promotion.setProduct(product.getText().toString());
                    promotion.setOriginalPrice(Double.parseDouble(originalPrice.getText().toString()));
                    promotion.setDiscountPrice(Double.parseDouble(discountPrice.getText().toString()));

                    int merchantId = 1;

                    Call<Promotion> call = HttpManager.getInstance().getService().postPtomotion(merchantId, promotion);
                    call.enqueue(new Callback<Promotion>() {
                        @Override
                        public void onResponse(Call<Promotion> call, Response<Promotion> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(CreatePromotion.this, SuccessCreatePromotion.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CreatePromotion.this, ("Fail with error code: " + response.code()), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Promotion> call, Throwable t) {
                            Toast.makeText(CreatePromotion.this, "Has some error", Toast.LENGTH_LONG).show();
                            Log.i("ii", t.getMessage());
                        }
                    });

                } catch (Exception ex) {
//                    show.setText(" url exeption! " );
                }
            }

        });
    }

}