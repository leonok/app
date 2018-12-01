package com.eng.planoestudos.CodeTelas;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.eng.planoestudos.R;

import java.util.ArrayList;

public class EscolhamateriaActivity extends AppCompatActivity {

    private static final  String TAG = EscolhamateriaActivity.class.getCanonicalName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolhamateria);

        LinearLayout checkboxContainer = (LinearLayout)findViewById(R.id.id_llCheckboxContainer);
        CheckBox checkBox;

        ArrayList<String> list = new ArrayList<>();
        list.add("Direito Penal");
        list.add("Portugues");
        list.add("Direito constitucional");
        list.add("Matematica");
        list.add("Informatica");
        list.add("Direito Processual Penal");
        list.add("Ingles");
        list.add("Legislação Especial");

        float size=20;

        final  ArrayList<CheckBox> arrayOfCheckBox = new ArrayList<>();
        for (int i=0; i<list.size();i++){

            checkBox=new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(list.get(i));
            checkBox.setTag(list.get(i));
            checkBox.setTextColor(Color.BLACK);
            checkBox.setTextSize(size);

            checkBox.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener() {


                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean b) {

                    if (b) {

                        Log.d(TAG, "Value is ok = " + buttonView.getTag().toString());

                    } else {

                        Log.d(TAG, "Value is not ok = " + buttonView.getTag().toString());

                    }

                }

            });

            arrayOfCheckBox.add(checkBox);
            checkboxContainer.addView(checkBox);



        }


    }
}
