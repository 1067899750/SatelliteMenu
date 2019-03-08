package com.diudiu.satellitemenu.password;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Toast;

import com.diudiu.satellitemenu.MainActivity;
import com.diudiu.satellitemenu.R;

public class PayActivity extends Activity implements CustomKeyBordView.CustomerKeyboardClickListener {

    private CustomKeyBordView customKeyBordView;
    private PasswordEditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        passwordEdit = findViewById(R.id.passwordEdit);
        customKeyBordView = findViewById(R.id.customKeyBordView);

        customKeyBordView.setListener(this);

        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String number = passwordEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(number) && number.length() >= passwordEdit.getmPasswordNumber()) {
                    Toast.makeText(PayActivity.this,number,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void click(String number) {
        passwordEdit.addPasswordNumber(number);
    }

    @Override
    public void delete() {
        passwordEdit.deletePassword();
    }
}
