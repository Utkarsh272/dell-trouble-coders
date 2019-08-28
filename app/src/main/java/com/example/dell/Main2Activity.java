    package com.example.dell;

import androidx.appcompat.app.AppCompatActivity;
//import android.R;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class Main2Activity extends AppCompatActivity implements PaytmPaymentTransactionCallback {


        TextView textViewPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewPrice = findViewById(R.id.textViewPrice);
        Button bt = (Button) findViewById(R.id.buttonBuy);

        //attaching a click listener to the button buy
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //calling the method generateCheckSum() which will generate the paytm checksum for payment
//                generateCheckSum();
                Intent intent = new Intent(Main2Activity.this,Cart.class);
                startActivity(intent);
            }
        });
    }

        private void generateCheckSum() {

            //getting the tax amount first.
            String txnAmount = textViewPrice.getText().toString().trim();

            //creating a retrofit object.
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //creating the retrofit api service
            Api apiService = retrofit.create(Api.class);

            //creating paytm object
            //containing all the values required
            final Paytm paytm = new Paytm(
                    Constatnts.M_ID,
                    Constatnts.CHANNEL_ID,
                    txnAmount,
                    Constatnts.WEBSITE,
                    Constatnts.CALLBACK_URL,
                    Constatnts.INDUSTRY_TYPE_ID
            );

            //creating a call object from the apiService
            Call<Checksum> call = apiService.getChecksum(
                    paytm.getmId(),
                    paytm.getOrderId(),
                    paytm.getCustId(),
                    paytm.getChannelId(),
                    paytm.getTxnAmount(),
                    paytm.getWebsite(),
                    paytm.getCallBackUrl(),
                    paytm.getIndustryTypeId()
            );

            //making the call to generate checksum
            call.enqueue(new Callback<Checksum>() {

                @Override
                public void onResponse(Call<Checksum> call, Response<Checksum> response) {

                    //once we get the checksum we will initiailize the payment.
                    //the method is taking the checksum we got and the paytm object as the parameter
                    initializePaytmPayment(response.body().getChecksumHash(), paytm);
                }

                @Override
                public void onFailure(Call<Checksum> call, Throwable t) {
                    Toast.makeText(Main2Activity.this, t.toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }

        private void initializePaytmPayment(String checksumHash, Paytm paytm) {

            //getting paytm service
            PaytmPGService Service = PaytmPGService.getStagingService();

            //use this when using for production
            //PaytmPGService Service = PaytmPGService.getProductionService();

            //creating a hashmap and adding all the values required
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("MID", Constatnts.M_ID);
            paramMap.put("ORDER_ID", paytm.getOrderId());
            paramMap.put("CUST_ID", paytm.getCustId());
            paramMap.put("CHANNEL_ID", paytm.getChannelId());
            paramMap.put("TXN_AMOUNT", paytm.getTxnAmount());
            paramMap.put("WEBSITE", paytm.getWebsite());
            paramMap.put("CALLBACK_URL", paytm.getCallBackUrl());
            paramMap.put("CHECKSUMHASH", checksumHash);
            paramMap.put("INDUSTRY_TYPE_ID", paytm.getIndustryTypeId());


            //creating a paytm order object using the hashmap
            PaytmOrder order = new PaytmOrder(paramMap);

            //intializing the paytm service
            Service.initialize(order, null);

            //finally starting the payment transaction
            Service.startPaymentTransaction(this, true, true, new PaytmPaymentTransactionCallback() {
                /*Call Backs*/
                public void someUIErrorOccurred(String inErrorMessage) {}
                public void onTransactionResponse(Bundle inResponse) {}
                public void networkNotAvailable() {}
                public void clientAuthenticationFailed(String inErrorMessage) {}
                public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {}
                public void onBackPressedCancelTransaction() {}
                public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {}
            });

        }

        //all these overriden method is to detect the payment result accordingly
        @Override
        public void onTransactionResponse(Bundle bundle) {

            Toast.makeText(this, bundle.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void networkNotAvailable() {
            Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();
        }

        @Override
        public void clientAuthenticationFailed(String s) {
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }

        @Override
        public void someUIErrorOccurred(String s) {
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onErrorLoadingWebPage(int i, String s, String s1) {
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBackPressedCancelTransaction() {
            Toast.makeText(this, "Back Pressed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onTransactionCancel(String s, Bundle bundle) {
            Toast.makeText(this, s + bundle.toString(), Toast.LENGTH_LONG).show();
        }
}
