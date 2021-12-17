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

public class LoginActivity extends DbBaseActivity {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Connexion");
        getDialog();

        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_pass);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isParamValid();
            }
        });

        findViewById(R.id.tv_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(LoginActivity.this, SignupActivity.class);
            }
        });
    }

    @Override
    public void onItemClick(@Nullable View view, @Nullable Chat itemObj) {

    }

    private void isParamValid() {
        if (!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches()) {
            editTextEmail.setError(getString(R.string.error_email));
            editTextEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
            editTextPassword.setError(getString(R.string.error_password));
            editTextPassword.requestFocus();
            return;
        }
        getParam();
    }

    private void getParam() {
        Customer customer = new Customer();
        customer.setEmail(editTextEmail.getText().toString());
        customer.setPassword(editTextPassword.getText().toString());

        new login(this).execute(customer);
    }
}
