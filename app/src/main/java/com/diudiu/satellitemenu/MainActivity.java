package com.diudiu.satellitemenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.diudiu.satellitemenu.bottonSatellite.BottomStatelliteMenuActivity;
import com.diudiu.satellitemenu.lockPattern.LockPatternActivity;
import com.diudiu.satellitemenu.password.PayDialog;
import com.diudiu.satellitemenu.satellite.SatelliteMenuActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PayDialog payDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        payDialog = new PayDialog(this);
    }

    private void initView() {
        findViewById(R.id.btn_satellite_menu).setOnClickListener(this);
        findViewById(R.id.btn_bottom_satellite_menu).setOnClickListener(this);
        findViewById(R.id.btnLuck).setOnClickListener(this);
        findViewById(R.id.btnPayPassword).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_satellite_menu:
                startActivity(new Intent(this, SatelliteMenuActivity.class));
                break;
            case R.id.btn_bottom_satellite_menu:
                startActivity(new Intent(this,BottomStatelliteMenuActivity.class));
                break;
            case R.id.btnLuck:
                startActivity(new Intent(this,LockPatternActivity.class));
                break;
            case R.id.btnPayPassword:
                payDialog.show();
                break;
        }
    }
}
