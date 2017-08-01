package com.example.aloko.myfirstfirebaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



/**
 * Created by aloko on 18.06.2017.
 */

public class ResultPage extends AppCompatActivity {
    private TextView vke;
    private TextView condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        Bundle setItem = getIntent().getExtras();
        double result = setItem.getDouble("result");

        vke = (TextView) findViewById(R.id.vke);
        vke.setText("Kitle Index: "+result);

        condition = (TextView) findViewById(R.id.condition);

        if(result<18){
            condition.setText("ZAYIF");
        }
        else if(result>17 && result<24){
            condition.setText("NORMAL");
        }
        else if(result>25){
            condition.setText("şişman");
        }
        else{
            condition.setText("unknown value");
        }
    }
}


