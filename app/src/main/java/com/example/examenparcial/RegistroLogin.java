package com.example.examenparcial;

import android.content.Intent;
import android.content.SharedPreferences;
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

        limparCampos();
    }


    private void registerUser() {
        // SharedPreferences ->  Es útil para guardar datos pequeños y simples, como configuraciones del usuario o estados de la aplicación.
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        /*"UserPrefs", es el nombre del archivo de preferencias.
        * */
        // Crear un editor para hacer cambios en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Guardar un valor booleano con la clave "isRegistered"
        editor.putBoolean("isRegistered", true);

        /*    // Guardar los valores en SharedPreferences
    editor.putBoolean("isRegistered", true);
    editor.putString("nombre", nombre);
    editor.putString("password", passwordInput);
    editor.putString("email", emailInput);
    editor.putString("phone", phoneInput);
    editor.putString("date", dateInput);
    editor.putString("gender", gender);
*/


        // Aplicar los cambios
        editor.apply();
    }


    private void limparCampos(){
        plainText.setText("");
        password.setText("");
        phone.setText("");
        email.setText("");
        date.setText("");
        radioGroup.clearCheck();
    }
}