package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroLogin extends AppCompatActivity {


    private EditText plainText, password, email, phone, date;
    private RadioGroup radioGroup;
    private Button exitBoton;
    private Validaciones campoValidations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        campoValidations = new Validaciones(RegistroLogin.this);
        plainText = findViewById(R.id.txtNameComplete);
        password = findViewById(R.id.txtNewPass);
        email = findViewById(R.id.txtNewEmail);
        phone = findViewById(R.id.txtNumber);
        date = findViewById(R.id.txtDateNac);
        radioGroup = findViewById(R.id.grupoSexo);
        exitBoton = findViewById(R.id.exitSubmit);

        exitBoton.setOnClickListener(view -> {
            Intent instanciarSalida = new Intent(this, MainActivity.class);
            startActivity(instanciarSalida);
            finish();
        });


    }

    public void ChekInSubmit(View view){
        boolean isValid = campoValidations.validateFields(
                plainText.getText().toString(),
                password.getText().toString(),
                email.getText().toString(),
                phone.getText().toString(),
                date.getText().toString(),
                radioGroup.getCheckedRadioButtonId()
        );

        if(isValid){
            Toast.makeText(this, "Validación exitosa", Toast.LENGTH_SHORT).show();
        }
    }



}