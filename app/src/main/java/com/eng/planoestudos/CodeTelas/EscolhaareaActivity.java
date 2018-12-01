package com.eng.planoestudos.CodeTelas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.eng.planoestudos.R;

public class EscolhaareaActivity extends AppCompatActivity {

    private Button btn_areaPolicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolhaarea);

        btn_areaPolicial = findViewById(R.id.id_btnPolicial);

        btn_areaPolicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EscolhaareaActivity.this, EscolhamateriaActivity.class);
                startActivity(intent);

            }
        });
    }
}
