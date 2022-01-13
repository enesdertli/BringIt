package com.example.bringit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Map;

public class Add_to_basket extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private StorageReference storageReference;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private TextView retrieveTV;
    ImageView imageHome;
    ImageView imageBasket;
    ImageView imagePerson;
    Button btnAddToBasket;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_to_basket);

        firebaseDatabase = FirebaseDatabase.getInstance();


        databaseReference = firebaseDatabase.getReference("Data");

        // initializing our object class variable.
        retrieveTV = findViewById(R.id.idTVRetrieveData);
        getdata();

        imageHome = findViewById(R.id.imgHome);
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_to_basket.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageBasket = findViewById(R.id.imgBasket);
        imageBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_to_basket.this,Basket.class);
                startActivity(intent);
                finish();
            }
        });

        imagePerson = findViewById(R.id.imgPerson);
        imagePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_to_basket.this,Account_Settings.class);
                startActivity(intent);
                finish();
            }
        });
        btnAddToBasket = findViewById(R.id.btnAddToBasket);
        btnAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_to_basket.this,Basket.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void getdata() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                retrieveTV.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Add_to_basket.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void goToBasket(View view){

        Intent intent = new Intent(Add_to_basket.this,Basket.class);
        startActivity(intent);
        finish();
    }
}

