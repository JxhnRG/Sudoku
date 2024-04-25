package org.example.sudoku.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.example.sudoku.view.alert.AlertBox;


public class GameController {
    @FXML
    private GridPane sudokuBoard;
    private ArrayList<TextField> textFields;
    private ArrayList<Integer> sudokuValues;

    public GameController() {
        textFields = new ArrayList<>();
    }

    public void initialize() {
        sudokuValues = new ArrayList<>(81);
        for (int i = 0; i < 81; i++) {
            sudokuValues.add(0); // Inicializar todas las celdas con 0 // Start every cell with 0
        }
        // Llena el GridPane con TextField y agrega cada TextField al ArrayList
        //Fills the GridPane with TextField and adds every TextField to the ArrayList
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField textField = new TextField();
                textField.setPrefWidth(30); // Ancho preferido del TextField // Preferred width of TextField
                textField.setPrefHeight(30); // Alto preferido del TextField // Preferred height of TextField
                textField.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
                textField.setAlignment(Pos.CENTER);
                textFields.add(textField); // Agrega el TextField al ArrayList //Adds the TextField to the ArrayList
                // Añade el TextField a la cuadrícula en la posición (col, row) //Adds the TextField to the grid in its position (column, row)
                sudokuBoard.add(textField, col, row);

                insertNumberTextField(1,6);
                insertNumberTextField(3,1);
                insertNumberTextField(5,4);
                insertNumberTextField(7,5);
                insertNumberTextField(9,1);
                insertNumberTextField(11,8);
                insertNumberTextField(12,3);
                insertNumberTextField(14,5);
                insertNumberTextField(15,6);
                insertNumberTextField(18,2);
                insertNumberTextField(25,3);
                insertNumberTextField(26,1);
                insertNumberTextField(27,8);
                insertNumberTextField(30,4);
                insertNumberTextField(32,7);
                insertNumberTextField(35,6);
                insertNumberTextField(38,6);
                insertNumberTextField(42,3);
                insertNumberTextField(45,7);
                insertNumberTextField(46,3);
                insertNumberTextField(48,9);
                insertNumberTextField(50,1);
                insertNumberTextField(52,2);
                insertNumberTextField(53,4);
                insertNumberTextField(54,5);
                insertNumberTextField(56,9);
                insertNumberTextField(60,4);
                insertNumberTextField(62,2);
                insertNumberTextField(65,7);
                insertNumberTextField(66,2);
                insertNumberTextField(68,6);
                insertNumberTextField(69,9);
                insertNumberTextField(73,4);
                insertNumberTextField(75,5);
                insertNumberTextField(77,8);
                insertNumberTextField(79,7);

            }
        }
        for (TextField textField : textFields) {
            textField.setOnKeyReleased(event -> handleTextFieldInput(textField, event));
        }

    }


    private void handleTextFieldInput(TextField textField, KeyEvent event) {
        String input = textField.getText();
        if (!input.matches("[1-9]?")|| input.length()>1) {
            // Si la entrada no es un número del 1 al 9, eliminar el último carácter ingresado
            // If the entry isn't a number between 1 and 9, deletes the last character added
            textField.setText(input.substring(0, input.length() - 1));
            String title = "Error";
            String header = "Entrada inválida";
            String content = "Ingrese un número válido (1-9).";
            new AlertBox().showMessage(title,header,content);
        }
        // Actualizar la lista sudokuValues con el nuevo valor ingresado
        // Updates the list sudokuValues with the new added value
        updateSudokuValues();
        // Verificar si el sudoku está resuelto después de la actualización
        // Verifies if the sudoku is done after the update
        boolean isSolved = isSudokuSolved();
        if (isSolved) {
            String title = "¡Felicidades!";
            String header = null;
            String content = "¡Has completado el Sudoku con éxito!";
            new AlertBox().showMessage(title, null, content);
        }
    }

    public boolean isSudokuSolved() {

        if (sudokuValues == null) {
            return false;
        }
        // Verificar filas
        // Verifies rows
        for (int row = 0; row < 9; row++) {
            Set<Integer> rowSet = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                int value = sudokuValues.get(row * 9 + col);
                if (value != 0 && !rowSet.add(value)) {
                    return false; // Número repetido encontrado en la fila // Repeated number found in the row
                }
            }
        }

        // Verificar columnas // Verifies colums
        for (int col = 0; col < 9; col++) {
            Set<Integer> colSet = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                int value = sudokuValues.get(row * 9 + col);
                if (value != 0 && !colSet.add(value)) {
                    return false; // Número repetido encontrado en la columna // Repeated number found in the column
                }
            }
        }

        // Verificar subcuadrículas // Verifies subgrids
        for (int rowOffset = 0; rowOffset < 9; rowOffset += 3) {
            for (int colOffset = 0; colOffset < 9; colOffset += 3) {
                Set<Integer> subgridSet = new HashSet<>();
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        int value = sudokuValues.get((rowOffset + row) * 9 + colOffset + col);
                        if (value != 0 && !subgridSet.add(value)) {
                            return false; // Número repetido encontrado en la subcuadrícula // Repeated number found in the subgrid
                        }
                    }
                }
            }
        }
        for (int value : sudokuValues) {
            if (value == 0) {
                return false; // Si se encuentra alguna celda vacía, el Sudoku no está resuelto // If any empty grid is found, the sudoku is not complete
            }
        }

        return true; // Sudoku resuelto correctamente // Sudoku done correctly
    }
    public void updateSudokuValues() {
        sudokuValues.clear(); // Limpiar la lista actual // Cleans actual list
        // Recorrer todos los TextField y actualizar sudokuValues con sus valores // Runs all TextFields and updates sudokuValues with its values
        for (TextField textField : textFields) {
            String text = textField.getText();
            int value = text.isEmpty() ? 0 : Integer.parseInt(text);
            sudokuValues.add(value);
        }
    }

    @FXML
    private void onHandleButtonVerification() {
        updateSudokuValues();  //Updates Sudoku values
        boolean isSolved = isSudokuSolved();
        if (isSolved) { //If it is solved correctly it will show an alert box saying it was succesfully solved
            new AlertBox().showMessage("¡Felicidades!", "El Sudoku está resuelto correctamente.", null);
        } else { //If it is NOT solved correctly it will show an alert box showing the sudoku isn't correctly solved
            new AlertBox().showMessage("Error", "El Sudoku no está resuelto correctamente." , null);
        }
    }
    public void insertNumberTextField(int indice, int numero) {
        // Verificar si el índice está dentro de los límites del ArrayList
        // Verifies if the index is inside the ArrayList limits
        if (indice >= 0 && indice < textFields.size()) {
            TextField textField = textFields.get(indice);
            // Establecer el texto del TextField con el número // Establishes TextField text with the number
            textField.setText(String.valueOf(numero));
            textField.setDisable(true);
        }
    }
}
