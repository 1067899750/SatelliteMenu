package com.diudiu.satellitemenu.satellite;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diudiu.satellitemenu.R;
import com.diudiu.satellitemenu.satellite.view.ArcMenu;

public class SatelliteMenuActivity extends Activity implements View.OnClickListener {

    private ArcMenu arcMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statellite_menu);

        initView();
    }

    private void initView() {
        arcMenu = findViewById(R.id.arcMenu);
        findViewById(R.id.btn).setOnClickListener(this);
        arcMenu.setOnMunuItemClickListener(new ArcMenu.OnMunuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                if (arcMenu.isOpen()) {
                    arcMenu.toggleMenu(600);
                }
                break;
        }
    }
}
