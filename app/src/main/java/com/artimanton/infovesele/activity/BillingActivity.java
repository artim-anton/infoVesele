package com.artimanton.infovesele.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.artimanton.infovesele.R;
import com.artimanton.infovesele.utilities.BackGroundActivity;

public class BillingActivity extends Activity {
    // SAMPLE APP CONSTANTS
    private static final String ACTIVITY_NUMBER = "activity_num";
    private static final String LOG_TAG = "iabv3";

    // PRODUCT & SUBSCRIPTION IDS
    private static final String PRODUCT_ID = "com.artimanton.infovesele";
    private static final String SUBSCRIPTION_ID = "com.artimanton.infovesele.subs1";
    private static final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjwSzeEgAXyQBBKCp724PVqTDbIzY9CGhkTdXFrPNmxpwmLjUyHB9oR3rlkOQIbCPRgU1nWaWqD27qhijbnx1dm3sNZ136EnB2tzbMZjTU88p9Meizz8YfkAhD0csKiEzYk7tzbhO9EWfIprDylbD6UpJsa8OJS//8xQHykcgt2r/DKICxoRoR3hxNczgQY9dtJOPcdrMwKKB402lkqqdEAEyN1t5E0vxBQpqU6Ouufjx3aUrI12GovTnn1kOyT4UUYt20UQz9E9M9GRaBoHgxPstZB8rY6ffkkqaKmmmqjFM5g8hY9OxNF8jkApcjgAtvq03t4j6YOiFUetI+4yc5wIDAQAB"; // PUT YOUR MERCHANT KEY HERE;
    // put your Google merchant id here (as stated in public profile of your Payments Merchant Center)
    // if filled library will provide protection against Freedom alike Play Market simulators
    private static final String MERCHANT_ID=null;

    private BillingProcessor bp;
    private boolean readyToPurchase = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        //TextView title = (TextView)findViewById(R.id.titleTextView);
        //title.setText(String.format(getString(R.string.title), getIntent().getIntExtra(ACTIVITY_NUMBER, 1)));

        if(!BillingProcessor.isIabServiceAvailable(this)) {
            showToast("In-app billing service is unavailable, please upgrade Android Market/Play to version >= 3.9.16");
        }

        bp = new BillingProcessor(this, LICENSE_KEY, MERCHANT_ID, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(String productId,TransactionDetails details) {
                //showToast("onProductPurchased: " + productId);
                //updateTextViews();
            }
            @Override
            public void onBillingError(int errorCode, Throwable error) {
                //showToast("onBillingError: " + Integer.toString(errorCode));
            }
            @Override
            public void onBillingInitialized() {
               // showToast("onBillingInitialized");
                readyToPurchase = true;
                //updateTextViews();
            }
            @Override
            public void onPurchaseHistoryRestored() {
                showToast("onPurchaseHistoryRestored");
                for(String sku : bp.listOwnedProducts())
                    Log.d(LOG_TAG, "Owned Managed Product: " + sku);
                for(String sku : bp.listOwnedSubscriptions())
                    Log.d(LOG_TAG, "Owned Subscription: " + sku);
               // updateTextViews();
            }
        });
    }

  /*   @Override
   protected void onResume() {
        super.onResume();

        updateTextViews();
    }*/

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

   /* private void updateTextViews() {
        TextView text = (TextView)findViewById(R.id.productIdTextView);
        text.setText(String.format("%s is%s purchased", PRODUCT_ID, bp.isPurchased(PRODUCT_ID) ? "" : " not"));
        text = (TextView)findViewById(R.id.subscriptionIdTextView);
        text.setText(String.format("%s is%s subscribed", SUBSCRIPTION_ID, bp.isSubscribed(SUBSCRIPTION_ID) ? "" : " not"));
    }*/

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        if (!readyToPurchase) {
            showToast("Billing not initialized.");
            return;
        }
        switch (v.getId()) {
            /*case R.id.purchaseButton:
                bp.purchase(this,PRODUCT_ID);
                break;
            case R.id.consumeButton:
                Boolean consumed = bp.consumePurchase(PRODUCT_ID);
                updateTextViews();
                if (consumed)
                    showToast("Successfully consumed");
                break;
            case R.id.productDetailsButton:
                SkuDetails sku = bp.getPurchaseListingDetails(PRODUCT_ID);
                showToast(sku != null ? sku.toString() : "Failed to load SKU details");
                break;*/
            case R.id.subscribeButton:
                bp.subscribe(this,SUBSCRIPTION_ID);
                break;
            /*case R.id.updateSubscriptionsButton:
                if (bp.loadOwnedPurchasesFromGoogle()) {
                    showToast("Subscriptions updated.");
                    updateTextViews();
                }
                break;
            case R.id.subsDetailsButton:
                SkuDetails subs = bp.getSubscriptionListingDetails(SUBSCRIPTION_ID);
                showToast(subs != null ? subs.toString() : "Failed to load subscription details");
                break;
            case R.id.launchMoreButton:
                startActivity(new Intent(this, BillingActivity.class).putExtra(ACTIVITY_NUMBER, getIntent().getIntExtra(ACTIVITY_NUMBER, 1) + 1));
                break;
            default:
                break;*/
        }
    }

    public void backButton(View view) {
        finish();
    }

}
