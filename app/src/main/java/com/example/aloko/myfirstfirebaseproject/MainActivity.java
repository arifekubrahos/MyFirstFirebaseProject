package com.example.aloko.myfirstfirebaseproject;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private EditText name;
    private Button calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Firebase.setAndroidContext(this);
        setContentView(R.layout.info_activity);
        config.android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);


        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        name = (EditText) findViewById(R.id.username);
        calculate = (Button) findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(height.getText().toString().isEmpty()|| weight.getText().toString().isEmpty()||name.getText().toString().isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Warning").setMessage("Please fill all places!").show();
                }
                else {
                    double h = Double.parseDouble(height.getText().toString()) / 100;
                    double w = Double.parseDouble(weight.getText().toString());
                    double result = Calculate(h, w);

                    Intent intent = new Intent(getApplicationContext(),ResultPage.class);
                    //result sayfasına hangi değeri göndermek isstiyorsak intentte o değeri tutuyoruz.
                    //sayfalar arası değeer geçişleri
                    intent.putExtra("myresult",result);

                    //firebase de referance oluşturduk database imize ulaşmak için
                    DatabaseReference ddRef= FirebaseDatabase.getInstance().getReference().child("kullanıcılar");
                    Model model= new Model();
                    //model bean classı oluşturup bi model nesne oluşturuyoruz her ayrı kullanıcı için
                    model.setName(name.getText().toString());
                    model.setHeight(String.valueOf(h));
                    model.setWeight(String.valueOf(w));
                    model.setResult(String.valueOf(result));
                    //oluşturduğumuz modeli database e push ediyoruz
                    ddRef.push().setValue(model);

                    startActivity(intent);
                }
            }
        });


    }
    public double Calculate(double h, double w){
        double cal = h/(w*w);
        return cal;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(R.id.past== id){
            Intent intent = new Intent(getApplicationContext(),ListPage.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
