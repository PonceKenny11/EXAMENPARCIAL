package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Game3Linea extends AppCompatActivity {

    private char jugadorActual = 'X';
    private char[][] board = new char[3][3];
    private boolean gameFinal = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game3_linea);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeBoard();
    }



    private void initializeBoard() {
        TableLayout tableLayout = findViewById(R.id.gameLinea);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                Button button = (Button) row.getChildAt(j);
                button.setText("");
                board[i][j] = ' ';
            }
        }
        jugadorActual = 'X';
        gameFinal = false;
    }

    public void onCellClicked(View view) {
        if (gameFinal) {
            Toast.makeText(this, "El juego ha terminado. Reinicia el juego.", Toast.LENGTH_SHORT).show();
            return;
        }

        Button button = (Button) view;
        String tag = button.getTag().toString();
        int row = Character.getNumericValue(tag.charAt(0));
        int col = Character.getNumericValue(tag.charAt(1));

        if (board[row][col] == ' ') {
            board[row][col] = jugadorActual;
            button.setText(String.valueOf(jugadorActual));
            if (checkWin()) {
                Toast.makeText(this, "El jugador " + jugadorActual + " ha ganado!", Toast.LENGTH_SHORT).show();
                gameFinal = true;
            } else if (isBoardFull()) {
                Toast.makeText(this, "El juego ha terminado en empate.", Toast.LENGTH_SHORT).show();
                gameFinal = true;
            } else {
                jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin() {//ha ganado
        // Comprobar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == jugadorActual && board[i][1] == jugadorActual && board[i][2] == jugadorActual) {
                return true;
            }
            if (board[0][i] == jugadorActual && board[1][i] == jugadorActual && board[2][i] == jugadorActual) {
                return true;
            }
        }
        // Comprobar diagonales
        if (board[0][0] == jugadorActual && board[1][1] == jugadorActual && board[2][2] == jugadorActual) {
            return true;
        }
        if (board[0][2] == jugadorActual && board[1][1] == jugadorActual && board[2][0] == jugadorActual) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {//empate
        int cont_X = 0;
        int con_O = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    cont_X++;
                } else if (board[i][j] == 'O') {
                    con_O++;
                }
            }
        }

        if(cont_X > 3){
            eliminarCeldaAntigua('X');
        }

        if(con_O > 3){
            eliminarCeldaAntigua('O');
        }

        return (cont_X + con_O) == 9;//exoste empate
    }


    private void eliminarCeldaAntigua(char jugador){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == jugador){
                    board[i][j] = ' ';
                    return;
                }
            }
        }
    }
    public void resetGame(View view) {
        initializeBoard();
    }

    public void ExitApp(View view){
        Intent instanciarSalida = new Intent(this, MainActivity.class);
        startActivity(instanciarSalida);
        finish();
    }



}