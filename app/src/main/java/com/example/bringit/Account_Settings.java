package com.example.bringit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Account_Settings extends AppCompatActivity {


    private FirebaseAuth auth;



    ImageView imageHome;
    ImageView imageBasket;
    ImageView imagePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_account_settings);


        auth = FirebaseAuth.getInstance();

        imageHome = findViewById(R.id.imgHome);
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_Settings.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageBasket = findViewById(R.id.imgBasket);
        imageBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_Settings.this,Basket.class);
                startActivity(intent);
                finish();
            }
        });

        imagePerson = findViewById(R.id.imgPerson);
        imagePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_Settings.this,Account_Settings.class);
                startActivity(intent);
                finish();
            }
        });




    }

    public void signOut(View view){
        auth.signOut();
        Intent intent = new Intent(Account_Settings.this,Login_Screen.class);
        startActivity(intent);
        finish();
    }
}

