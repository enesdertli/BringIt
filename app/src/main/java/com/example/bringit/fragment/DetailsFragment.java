package com.example.bringit.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bringit.R;


public class DetailsFragment extends Fragment {


    private static final String PRODUCTS = "products";
    private Products products;

    public DetailsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(Products products) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCTS, products);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            products = getArguments().getParcelable(PRODUCTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setProducts(view,products);

    }

    public void setProducts(View view, Products products) {
        TextView txtDescription = (TextView)view.findViewById(R.id.txtDescription);
        txtDescription.setText(products.getDescription());

        ListView listView = (ListView) view.findViewById(R.id.listProducts);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.array_adapter
                ,products.getFoods().toArray(new String[1])));   //problem maybe, get foods?
    }
}

