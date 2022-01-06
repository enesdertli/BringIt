package com.example.bringit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bringit.databinding.ActivityLoginScreenBinding;
import com.example.bringit.databinding.ActivityRegisterScreenBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Screen extends AppCompatActivity {

    TextView txtNewUser;
    TextView txtForgotPassword;
    Button button;
    private ActivityLoginScreenBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if(user != null){
            Intent intent = new Intent(Login_Screen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        txtNewUser = findViewById(R.id.txtNewUser);
        txtNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Screen.this,Register_Screen.class);
                startActivity(intent);
            }
        });

    }

    public void signInClicked(View view){

        String email = binding.txtEmail.getText().toString();
        String password = binding.txtPassword.getText().toString();

        if(email.equals(" ") || password.equals("")){
            Toast.makeText(this,"Enter email and password",Toast.LENGTH_LONG).show();
        } else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(Login_Screen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Login_Screen.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}