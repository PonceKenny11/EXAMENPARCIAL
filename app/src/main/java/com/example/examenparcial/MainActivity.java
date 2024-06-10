package com.example.examenparcial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText correo, contrasenia;

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

        correo = findViewById(R.id.txtEmail);
        contrasenia = findViewById(R.id.txtPassword);
    }


    public void registerSubmit(View view){
        Intent instanciar = new Intent(this, RegistroLogin.class);
        startActivity(instanciar);
        finish();
    }

    public void ingresandoLG(View view){
        if (validateLogin()){
            Toast.makeText(this, "BIENVENIDO! :D", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean validateLogin() {
        Validaciones campoValidators = new Validaciones(MainActivity.this);
        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioRegistrado", MODE_PRIVATE);
        boolean isRegistered = sharedPreferences.getBoolean("isRegistered", true);

        String emailInput = correo.getText().toString().trim();
        String passwordInput = contrasenia.getText().toString().trim();
        EditText[] txt = {correo, contrasenia};

        if (isRegistered) {
            String correoShared = sharedPreferences.getString("email", "");
            String contraseniaShare = sharedPreferences.getString("password", "");

            if (emailInput.equals(correoShared) && passwordInput.equals(contraseniaShare)) {
                return true;  // Iniciar Sesion Exitoso!!
            } else {
                Toast.makeText(this, "Correo electrónico o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                campoValidators.limpiarCampos(txt);
                return false;
            }
        } else {
            Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
            campoValidators.limpiarCampos(txt);
            return false;
        }
    }

}