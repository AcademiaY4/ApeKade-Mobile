package com.app.apekade.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.apekade.R;

public class PaymentFragment extends Fragment {

    private EditText cnumber, cname, cdate, ccvc;
    private RadioGroup paymentMethodGroup;
    private RadioButton radioMasterCard, radioVisaCard, radioTerms;
    private Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        // Initialize UI elements
        cnumber = view.findViewById(R.id.cnumber);
        cname = view.findViewById(R.id.cname);
        cdate = view.findViewById(R.id.cdate);
        ccvc = view.findViewById(R.id.ccvc);
        paymentMethodGroup = view.findViewById(R.id.payment_method_group);
        radioMasterCard = view.findViewById(R.id.radio_master_card);
        radioVisaCard = view.findViewById(R.id.radio_visa_card);
        radioTerms = view.findViewById(R.id.terms);
        submitButton = view.findViewById(R.id.csubmit);

        // Set click listener for the submit button
        submitButton.setOnClickListener(v -> processPayment());

        return view;
    }

    private void processPayment() {
        // Validate inputs
        if (validateInputs()) {
            // Proceed with payment processing logic here
            Toast.makeText(getActivity(), "Payment Successful!", Toast.LENGTH_SHORT).show();
            // Add your payment processing logic here
        }
    }

    private boolean validateInputs() {
        boolean isValid = true;

        // Check if card number is valid
        if (cnumber.getText().toString().isEmpty()) {
            cnumber.setError("Card number is required");
            isValid = false;
        }

        // Check if name on card is valid
        if (cname.getText().toString().isEmpty()) {
            cname.setError("Name on card is required");
            isValid = false;
        }

        // Check if expiration date is valid
        if (cdate.getText().toString().isEmpty()) {
            cdate.setError("Expiration date is required");
            isValid = false;
        }

        // Check if CVC is valid
        if (ccvc.getText().toString().isEmpty()) {
            ccvc.setError("CVC is required");
            isValid = false;
        }

        // Check if a payment method is selected
        if (paymentMethodGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please select a payment method", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        // Check if terms and conditions are accepted
        if (!radioTerms.isChecked()) {
            Toast.makeText(getActivity(), "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }
}
