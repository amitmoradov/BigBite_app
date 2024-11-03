package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.Cart;

public class PaymentActivity extends AppCompatActivity {
    private Cart cart;
    private TextView totalAmountTextView;
    private Button payButton;
    private ProgressDialog progressDialog;
    private EditText cvvText;
    private EditText idText;
    private EditText dateText;
    private EditText cardNumberText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cart = CartSingleton.getInstance();
        payButton = findViewById(R.id.payButton);
        cvvText = findViewById(R.id.cardCvv);
        idText = findViewById(R.id.cardHolderName);
        dateText = findViewById(R.id.cardExpiryDate);
        cardNumberText = findViewById(R.id.cardNumber);
        cvvText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});



        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        totalAmountTextView.setText("סה״כ לתשלום: ₪" +  cart.getCartTotal());

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(cvvText.getText()) || cvvText.getText().length() != 3 ||TextUtils.isEmpty(idText.getText())
                || TextUtils.isEmpty(dateText.getText()) || TextUtils.isEmpty(cardNumberText.getText())  ) {
                    Toast.makeText(PaymentActivity.this, "נא למלא את כל השדות", Toast.LENGTH_SHORT).show();
                    return; // Stop the process if the files is not correct or empty
                }
                showLoadingDialog(); //Show the loading message

                /** Creating a treadmill that occupies the processor
                 during a few seconds simulates a payment
                 **/
                // Simulate a payment process (this should be your actual payment logic)
                new Thread(() -> {
                    try {
                        Thread.sleep(3000); //Processing time of 3 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(() -> {
                        cart.clearCart();
                        totalAmountTextView.setText("סה״כ לתשלום: ₪" + cart.getCartTotal());
                        hideLoadingDialog(); // Hides the loading message
                        clearAllFields();
                    });
                }).start();
            }
        });
    }

// function to show the loading message
    private void showLoadingDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("טוען..."); //The loading message
        progressDialog.setCancelable(false); //Do not allow the user to cancel the load
        progressDialog.show();
    }

// function to hide the loading message
    private void hideLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void clearAllFields() {
        idText.setText("ת.ז. בעל הכרטיס");
        cardNumberText.setText("מספר כרטיס אשראי");
        dateText.setText("תוקף הכרטיס (MM/YY)");
        cvvText.setText("CVV");

    }
}