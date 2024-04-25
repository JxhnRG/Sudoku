package org.example.sudoku.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


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
            sudokuValues.add(0); // Inicializar todas las celdas con 0
        }
        // Llena el GridPane con TextField y agrega cada TextField al ArrayList
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField textField = new TextField();
                textField.setPrefWidth(30); // Ancho preferido del TextField
                textField.setPrefHeight(30); // Alto preferido del TextField
                textField.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
                textField.setAlignment(Pos.CENTER);
                textFields.add(textField); // Agrega el TextField al ArrayList
                // Añade el TextField a la cuadrícula en la posición (col, row)
                sudokuBoard.add(textField, col, row);

                insertNumberTextField(1,6);
                insertNumberTextField(3,1);
                insertNumberTextField(5,4);
                insertNumberTextField(7,5);
                insertNumberTextField(11,8);
                insertNumberTextField(12,3);
                insertNumberTextField(14,5);
                insertNumberTextField(15,6);
                insertNumberTextField(18,2);
                insertNumberTextField(26,1);
                insertNumberTextField(27,8);
                insertNumberTextField(30,4);
                insertNumberTextField(32,7);
                insertNumberTextField(35,6);
                insertNumberTextField(38,6);
                insertNumberTextField(42,3);
                insertNumberTextField(45,7);
                insertNumberTextField(48,9);
                insertNumberTextField(50,1);
                insertNumberTextField(53,4);
                insertNumberTextField(54,5);
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
            textField.setText(input.substring(0, input.length() - 1));
            showAlert("Error", "Ingrese un número válido (1-9).");
        }
        // Actualizar la lista sudokuValues con el nuevo valor ingresado
        updateSudokuValues();
        // Verificar si el sudoku está resuelto después de la actualización
        boolean isSolved = isSudokuSolved();
        if (isSolved) {
            showAlert("¡Felicidades!", "El Sudoku está resuelto correctamente.");
        }
    }

    public boolean isSudokuSolved() {

        if (sudokuValues == null) {
            return false;
        }
        // Verificar filas
        for (int row = 0; row < 9; row++) {
            Set<Integer> rowSet = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                int value = sudokuValues.get(row * 9 + col);
                if (value != 0 && !rowSet.add(value)) {
                    return false; // Número repetido encontrado en la fila
                }
            }
        }

        // Verificar columnas
        for (int col = 0; col < 9; col++) {
            Set<Integer> colSet = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                int value = sudokuValues.get(row * 9 + col);
                if (value != 0 && !colSet.add(value)) {
                    return false; // Número repetido encontrado en la columna
                }
            }
        }

        // Verificar subcuadrículas
        for (int rowOffset = 0; rowOffset < 9; rowOffset += 3) {
            for (int colOffset = 0; colOffset < 9; colOffset += 3) {
                Set<Integer> subgridSet = new HashSet<>();
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        int value = sudokuValues.get((rowOffset + row) * 9 + colOffset + col);
                        if (value != 0 && !subgridSet.add(value)) {
                            return false; // Número repetido encontrado en la subcuadrícula
                        }
                    }
                }
            }
        }
        for (int value : sudokuValues) {
            if (value == 0) {
                return false; // Si se encuentra alguna celda vacía, el Sudoku no está resuelto
            }
        }

        return true; // Sudoku resuelto correctamente
    }
    public void updateSudokuValues() {
        sudokuValues.clear(); // Limpiar la lista actual
        // Recorrer todos los TextField y actualizar sudokuValues con sus valores
        for (TextField textField : textFields) {
            String text = textField.getText();
            int value = text.isEmpty() ? 0 : Integer.parseInt(text);
            sudokuValues.add(value);
        }
    }

    @FXML
    private void onHandleButtonVerification() {
        updateSudokuValues();
        boolean isSolved = isSudokuSolved();
        if (isSolved) {
            showAlert("¡Felicidades!", "El Sudoku está resuelto correctamente.");
        } else {
            showAlert("Error", "El Sudoku no está resuelto correctamente.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void insertNumberTextField(int indice, int numero) {
        // Verificar si el índice está dentro de los límites del ArrayList
        if (indice >= 0 && indice < textFields.size()) {
            TextField textField = textFields.get(indice);
            // Establecer el texto del TextField con el número
            textField.setText(String.valueOf(numero));
            textField.setDisable(true);
        }
    }
}
