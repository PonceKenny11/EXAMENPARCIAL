package com.example.examenparcial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void registerSubmit(View view){
        Intent instanciar = new Intent(this, RegistroLogin.class);
        startActivity(instanciar);
        finish();
    }

    private boolean validateLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isRegistered = sharedPreferences.getBoolean("isRegistered", false);

        //String emailInput = email.getText().toString().trim();
        //String passwordInput = password.getText().toString().trim();

        //return isRegistered && !emailInput.isEmpty() && !passwordInput.isEmpty();
        return true;
    }

    /*private boolean validateLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isRegistered = sharedPreferences.getBoolean("isRegistered", false);

        String emailInput = email.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();

        if (isRegistered) {
            String registeredEmail = sharedPreferences.getString("email", "");
            String registeredPassword = sharedPreferences.getString("password", "");

            if (emailInput.equals(registeredEmail) && passwordInput.equals(registeredPassword)) {
                return true;  // Login successful
            } else {
                Toast.makeText(this, "Correo electrónico o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
            return false;
        }
    }*/

}