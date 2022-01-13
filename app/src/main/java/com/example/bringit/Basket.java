package com.example.bringit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Basket extends AppCompatActivity {

    ImageView imageHome;
    ImageView imageBasket;
    ImageView imagePerson;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_basket);

        imageHome = findViewById(R.id.imgHome);
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basket.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageBasket = findViewById(R.id.imgBasket);
        imageBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basket.this,Basket.class);
                startActivity(intent);
                finish();
            }
        });

        imagePerson = findViewById(R.id.imgPerson);
        imagePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basket.this,Account_Settings.class);
                startActivity(intent);
                finish();
            }
        });

        button = findViewById(R.id.btnConfirmBasket);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basket.this,Order_Screen.class);
                startActivity(intent);
            }
        });
    }
    public void goToOrder(View view){
        Intent intent = new Intent(Basket.this,Order_Screen.class);
        startActivity(intent);
        finish();
    }
}

