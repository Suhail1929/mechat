package com.asynctaskcoffee.ui.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.asynctaskcoffee.ui.R;
import com.asynctaskcoffee.ui.db.table.Chat;
import com.asynctaskcoffee.ui.db.table.Customer;

public class SignupActivity extends DbBaseActivity {

    private EditText editTextUsername, editTextFirstName, editTextLastName;
    private EditText editTextPassword, editTextConfirmPassword;
    private EditText editTextEmail, editTextAddress, editTextCity;
    private EditText editTextPostalCode, editTextPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Inscription");

        getDialog();
        editTextUsername = findViewById(R.id.et_username);
        editTextFirstName = findViewById(R.id.et_first_name);
        editTextLastName = findViewById(R.id.et_last_name);
        editTextPassword = findViewById(R.id.et_pass);
        editTextConfirmPassword = findViewById(R.id.et_cnfrm_pass);
        editTextEmail = findViewById(R.id.et_email);
        editTextAddress = findViewById(R.id.et_address);
        editTextCity = findViewById(R.id.et_city);
        editTextPostalCode = findViewById(R.id.et_postal_code);
        editTextPhone = findViewById(R.id.et_phone);

        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isParamValid();
            }
        });

    }

    private void isParamValid() {

        if (TextUtils.isEmpty(editTextUsername.getText().toString())
        ) {
            editTextUsername.setError(getString(R.string.error_empty_field));
            editTextUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(editTextFirstName.getText().toString())
        ) {
            editTextFirstName.setError(getString(R.string.error_empty_field));
            editTextFirstName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(editTextLastName.getText().toString())
        ) {
            editTextLastName.setError(getString(R.string.error_empty_field));
            editTextLastName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(editTextPassword.getText().toString())
        ) {
            editTextPassword.setError(getString(R.string.error_empty_field));
            editTextPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(editTextConfirmPassword.getText().toString())
        ) {
            editTextConfirmPassword.setError(getString(R.string.error_empty_field));
            editTextConfirmPassword.requestFocus();
            return;
        }
        if (!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())) {
            editTextPassword.setError(getString(R.string.error_Confirmation));
            editTextPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches()) {
            editTextEmail.setError(getString(R.string.error_email));
            editTextEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(editTextAddress.getText().toString())) {
            editTextAddress.setError(getString(R.string.error_empty_field));
            editTextAddress.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(editTextCity.getText().toString())) {
            editTextCity.setError(getString(R.string.error_empty_field));
            editTextCity.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(editTextPostalCode.getText().toString())) {
            editTextPostalCode.setError(getString(R.string.error_empty_field));
            editTextPostalCode.requestFocus();
            return;
        }

        getParam();
    }

    private void getParam() {
        Customer customer = new Customer();
        customer.setUserName(editTextUsername.getText().toString());
        customer.setFirstName(editTextFirstName.getText().toString());
        customer.setLastName(editTextLastName.getText().toString());
        customer.setPassword(editTextPassword.getText().toString());
        customer.setEmail(editTextEmail.getText().toString());
        customer.setAddress(editTextAddress.getText().toString());
        customer.setCity(editTextCity.getText().toString());
        customer.setPostalCode(editTextPostalCode.getText().toString());
        customer.setTelephone(editTextPhone.getText().toString());
        new Signup(this).execute(customer);
    }

    @Override
    public void onItemClick(@Nullable View view, @Nullable Chat itemObj) {

    }
}
