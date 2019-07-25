package com.hackaton.merchantapp.activity;

import androidx.appcompat.app.AppCompatActivity;

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

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        imagePath = (EditText) findViewById(R.id.imagePath);
        quantity = (EditText) findViewById(R.id.quantity);
        product = (EditText) findViewById(R.id.product);
        originalPrice = (EditText) findViewById(R.id.originalPrice);
        discountPrice = (EditText) findViewById(R.id.discountPrice);
        submit = (Button) findViewById(R.id.submit);
        show = (TextView) findViewById(R.id.show);

        submit.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                try {

                    // CALL GetText method to make post method call
//                    GetText();
                    Promotion promotion = new Promotion();
                    promotion.setTitleName(name.getText().toString());
                    promotion.setDescription(description.getText().toString());
                    promotion.setImagePath(imagePath.getText().toString());
                    promotion.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    promotion.setProduct(product.getText().toString());
                    promotion.setOriginalPrice(Double.parseDouble(originalPrice.getText().toString()));
                    promotion.setDiscountPrice(Double.parseDouble(discountPrice.getText().toString()));

                    Gson gson = new Gson();
                    String json = gson.toJson(promotion);

                    show.setText(json);

                    int merchantId = 3;

                    Call<Promotion> call = HttpManager.getInstance().getService().postPtomotion(merchantId, promotion);
                    call.enqueue(new Callback<Promotion>() {
                        @Override
                        public void onResponse(Call<Promotion> call, Response<Promotion> response) {
                            if (response.isSuccessful()) {
                                show.setText("Create success");
                            } else {
                                show.setText("Fail with error code: " + response.code());
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

//    public  void  GetText()  throws UnsupportedEncodingException
//    {
//        // Get user defined values
//        Name = name.getText().toString();
//        Description = description.getText().toString();
//        ImagePath = imagePath.getText().toString();
//        Quantity = quantity.getText().toString();
//        Product = product.getText().toString();
//        OriginalPrice = originalPrice.getText().toString();
//        DiscountPrice = discountPrice.getText().toString();
//
//        System.err.println(Name + Description + ImagePath + Quantity);
//        show.setText(Name + Description + ImagePath + Quantity);
//
//        // Create data variable for sent values to server
//
////        String data = URLEncoder.encode("name", "UTF-8")
////                + "=" + URLEncoder.encode(Name, "UTF-8");
////
////        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
////                + URLEncoder.encode(Email, "UTF-8");
////
////        data += "&" + URLEncoder.encode("user", "UTF-8")
////                + "=" + URLEncoder.encode(Login, "UTF-8");
////
////        data += "&" + URLEncoder.encode("pass", "UTF-8")
////                + "=" + URLEncoder.encode(Pass, "UTF-8");
////
////        String text = "";
////        BufferedReader reader=null;
////
////        // Send data
////        try
////        {
////
////            // Defined URL  where to send data
////            URL url = new URL("/media/webservice/httppost.php");
////
////            // Send POST data request
////
////            URLConnection conn = url.openConnection();
////            conn.setDoOutput(true);
////            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
////            wr.write( data );
////            wr.flush();
////
////            // Get the server response
////
////            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////            StringBuilder sb = new StringBuilder();
////            String line = null;
////
////            // Read Server Response
////            while((line = reader.readLine()) != null)
////            {
////                // Append server response in string
////                sb.append(line + "\n");
////            }
////
////
////            text = sb.toString();
////        }
////        catch(Exception ex)
////        {
////
////        }
////        finally
////        {
////            try
////            {
////
////                reader.close();
////            }
////
////            catch(Exception ex) {}
////        }
////
////        // Show response on activity
////        content.setText( text  );
//
//    }

}