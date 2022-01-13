package com.example.bringit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.bringit.fragment.Dessert_fragment;
import com.example.bringit.fragment.DetailsActivity;
import com.example.bringit.fragment.DetailsFragment;
import com.example.bringit.fragment.Drink_Fragment;
import com.example.bringit.fragment.Favourite_Fragment;
import com.example.bringit.fragment.FoodFragment;
import com.example.bringit.fragment.Fragment_Chicken;
import com.example.bringit.fragment.Meat_Fragment;
import com.example.bringit.fragment.Meatball_Fragment;
import com.example.bringit.fragment.Products;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity implements FoodFragment.OnProductSelected {
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private StorageReference storageReference;

    Button button;
    ImageView imageHome;
    ImageView imageBasket;
    ImageView imagePerson;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnFavourite);
        button.performClick();



        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        storageReference = firebaseStorage.getReference();



        imageHome = findViewById(R.id.imgHome);
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


        imageBasket = findViewById(R.id.imgBasket);
        imageBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Basket.class);
                startActivity(intent);

            }
        });

        imagePerson = findViewById(R.id.imgPerson);
        imagePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Account_Settings.class);
                startActivity(intent);

            }
        });


}
    public void getDataChicken(View view){
        Intent intent = new Intent(MainActivity.this,Add_to_basket.class);
        startActivity(intent);
    }
    public void getFav(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Favourite_Fragment favourite_fragment = new Favourite_Fragment();
        fragmentTransaction.replace(R.id.frame_layout,favourite_fragment).commit();

    }
    public void getMeatball(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Meatball_Fragment meatball_fragment = new Meatball_Fragment();
        fragmentTransaction.replace(R.id.frame_layout,meatball_fragment).commit();
    }
    public void getChicken(View view){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment_Chicken fragment_chicken = new Fragment_Chicken();
        fragmentTransaction.replace(R.id.frame_layout,fragment_chicken).commit();
    }
    public void getMeat(View view){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Meat_Fragment meat_fragment = new Meat_Fragment();
        fragmentTransaction.replace(R.id.frame_layout,meat_fragment).commit();
    }
    public void getDrink(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Drink_Fragment drink_fragment = new Drink_Fragment();
        fragmentTransaction.replace(R.id.frame_layout,drink_fragment).commit();

    }
    public void getDessert(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Dessert_fragment dessert_fragment = new Dessert_fragment();
        fragmentTransaction.replace(R.id.frame_layout,dessert_fragment).commit();

    }



/*
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

    } */




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

