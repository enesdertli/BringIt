package com.example.bringit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements FoodFragment.OnProductSelected {
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;





    ImageView imageHome;
    ImageView imageBasket;
    ImageView imagePerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);



        firebaseStorage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = firebaseStorage.getReference();

        getData();





}

    public void getData(){

        firebaseFirestore.collection("products").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!= null){
                    Toast.makeText(MainActivity.this,error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }


                if(value != null){
                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String, Object> data = snapshot.getData();
                        String comment =(String) data.get("comment");

                    }

                }
            }
        });

    }


    @Override
    public void productSelected(Products products) {
            Log.d("Main Activity", products.getDescription());

        int display_mode = getResources().getConfiguration().orientation;
        if (display_mode == Configuration.ORIENTATION_PORTRAIT) {


            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("products", products);
            startActivity(intent);
        }else{
            DetailsFragment df = (DetailsFragment) getSupportFragmentManager().findFragmentByTag("details");
            if(df == null){
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                df = DetailsFragment.newInstance(products);
                fts.add(R.id.container, df, "details");
                fts.commit();
            }else{
                df.setProducts(findViewById(R.id.container),products);
            }
        }
    }
}