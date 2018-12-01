package com.eng.planoestudos.CodeTelas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.eng.planoestudos.R;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_second);



        // novo método
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        // inflar seu layout de activity aqui!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_second, null, false);
        drawer.addView(contentView, 0);

        navigationView.setCheckedItem(R.id.nav_second);

    }
}
