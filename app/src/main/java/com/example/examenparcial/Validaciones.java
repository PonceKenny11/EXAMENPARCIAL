package com.example.examenparcial;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Validaciones {

    private Context contexto; /*Proporciona información sobre el entorno de la aplicación y permite interactuar con otros componentes de Android*/

    public Validaciones(Context contexto){
            this.contexto = contexto;

    }



    // METODOS PUBLIC //

    public void limpiarCampos(EditText[] arrTxT){
        for (int i = 0; i < arrTxT.length; i++) {
            arrTxT[i].setText("");
        }
    }

    public void exitApp(){

    }
    public boolean validateFields(String plainText, String password, String email, String phone, String date, int radioButtonId) {
        if (plainText.trim().isEmpty()) {
            showToast("El campo Nombre es obligatorio");
            return false;
        }

        if (password.isEmpty()) {
            showToast("El campo Contraseña es obligatorio");
            return false;
        } else if (password.length() < 9) {
            showToast("La contraseña debe ser mayor a 9 caracteres");
            return false;
        }

        if (email.trim().isEmpty()) {
            showToast("El campo Usuario es obligatorio");
            return false;
        } /*else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Correo electrónico no válido");
            return false;
        }*/

        if (phone.trim().isEmpty()) {
            showToast("El campo Teléfono es obligatorio");
            return false;
        } else if (!phone.matches("^9\\d{8}$")) {
            showToast("El teléfono debe tener 9 dígitos y comenzar con 9");
            return false;
        }

        if (date.trim().isEmpty()) {
            showToast("El campo Fecha de nacimiento es obligatorio");
            return false;
        } else if (!isValidDate(date)) {
            showToast("Fecha no válida o la persona es menor de 15 años (dd/MM/yyyy)");
            return false;
        }

        if (radioButtonId == -1) {
            showToast("Debe seleccionar una opción");
            return false;
        }

        return true;

    }

    public void focusFields(EditText txtField){
        txtField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    txtField.setText("");
                }
            }
        }
        );

    }


    /*METODOS PRIVATE*/
    private boolean isValidDate(String date) {
        SimpleDateFormat validarFecha = new SimpleDateFormat("dd/MM/yyyy");
        validarFecha.setLenient(false); /*asegura que el análisis de fechas sea estricto*/
        try {
            validarFecha.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    private void showToast(String message) {
        Toast.makeText(this.contexto, message, Toast.LENGTH_SHORT).show();
    }


}
