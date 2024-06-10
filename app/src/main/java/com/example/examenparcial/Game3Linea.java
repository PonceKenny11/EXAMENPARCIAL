package com.example.examenparcial;

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

    private char currentPlayer = 'X';
    private char[][] board = new char[3][3];
    private boolean gameEnded = false;
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
        currentPlayer = 'X';
        gameEnded = false;
    }

    public void onCellClicked(View view) {
        if (gameEnded) {
            Toast.makeText(this, "El juego ha terminado. Reinicia el juego.", Toast.LENGTH_SHORT).show();
            return;
        }

        Button button = (Button) view;
        String tag = button.getTag().toString();
        int row = Character.getNumericValue(tag.charAt(0));
        int col = Character.getNumericValue(tag.charAt(1));

        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            button.setText(String.valueOf(currentPlayer));
            if (checkWin()) {
                Toast.makeText(this, "El jugador " + currentPlayer + " ha ganado!", Toast.LENGTH_SHORT).show();
                gameEnded = true;
            } else if (isBoardFull()) {
                Toast.makeText(this, "El juego ha terminado en empate.", Toast.LENGTH_SHORT).show();
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin() {
        // Comprobar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        // Comprobar diagonales
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetGame(View view) {
        initializeBoard();
    }





}