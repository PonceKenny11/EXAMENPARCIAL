package com.example.examenparcial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroLogin extends AppCompatActivity {


    private EditText ptxt_names, ptxt_password, ptxt_email, ptxt_phone, ptxt_date;
    private RadioGroup radioGroup;
    private Button exitBoton;

    private Spinner professionCombo;
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

        initializeLogin();

        /*exitBoton.setOnClickListener(view -> {
            Intent instanciarSalida = new Intent(this, MainActivity.class);
            startActivity(instanciarSalida);
            finish();
        });*/


    }



    public void ChekInSubmit(View view){
        boolean isValid = campoValidations.validateFields(
                ptxt_names.getText().toString(),
                ptxt_password.getText().toString(),
                ptxt_email.getText().toString(),
                ptxt_phone.getText().toString(),
                ptxt_date.getText().toString(),
                radioGroup.getCheckedRadioButtonId()
        );

        if(isValid){
            registrarUsuario(ptxt_email.getText().toString(), ptxt_password.getText().toString());
            Toast.makeText(this, "Cuenta Registrada Exitosamente!", Toast.LENGTH_SHORT).show();
        }

        limparCampos();
    }



    //SOPORTE CODE

    private void initializeLogin(){
        campoValidations = new Validaciones(RegistroLogin.this);
        ptxt_names = findViewById(R.id.txtNameComplete);
        ptxt_password = findViewById(R.id.txtNewPass);
        ptxt_email = findViewById(R.id.txtNewEmail);
        ptxt_phone= findViewById(R.id.txtNumber);
        ptxt_date = findViewById(R.id.txtDateNac);
        radioGroup = findViewById(R.id.grupoSexo);
        exitBoton = findViewById(R.id.exitSubmit);
        professionCombo = findViewById(R.id.Spinner_Combo);

        rellenarCombo(professionCombo);
        campoValidations.focusFields(ptxt_names);
        campoValidations.focusFields(ptxt_password);
        campoValidations.focusFields(ptxt_email);
    }
    private void registrarUsuario(String emailInput, String passwordInput) {

        String selectedProfessionText = professionCombo.getSelectedItem().toString();

        // SharedPreferences ->  Es útil para guardar datos pequeños y simples, como configuraciones del usuario o estados de la aplicación.
        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioRegistrado", MODE_PRIVATE);
        /*"UsuarioRegistrado", es el nombre del archivo de preferencias.*/

        // Crear un editor para hacer cambios en SharedPreferences
        SharedPreferences.Editor editarShared = sharedPreferences.edit();

        // Guardar los valores en SharedPreferences
        editarShared.putBoolean("isRegistered", true);

        editarShared.putString("password", passwordInput);
        editarShared.putString("email", emailInput);

        // Aplicar los cambios
        editarShared.apply();
    }

    private void rellenarCombo(Spinner professionCombo){
        // Crea un ArrayAdapter usando el array de strings y un layout por defecto para el spinner
        ArrayAdapter<CharSequence> listaProfesion = ArrayAdapter.createFromResource(this, R.array.combo_items,  android.R.layout.simple_spinner_item);

        listaProfesion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // se muestren en la lista desplegable. android.R.layout.simple_spinner_dropdown_item es un layout simple proporcionado por Android que muestra el texto en una lista desplegable.

        professionCombo.setAdapter(listaProfesion);
    }

    private void limparCampos(){
        EditText[] txt = {ptxt_password,ptxt_names,ptxt_phone,ptxt_date,ptxt_email};
        campoValidations.limpiarCampos(txt);
        radioGroup.clearCheck();
    }

    public void ExitApp(){
        Intent instanciarSalida = new Intent(this, MainActivity.class);
        startActivity(instanciarSalida);
        finish();
    }


}