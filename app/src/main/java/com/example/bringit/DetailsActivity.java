package com.example.bringit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Products products = getIntent().getParcelableExtra("products");

        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fts.add(R.id.container, DetailsFragment.newInstance(products));
        fts.commit();
    }
}