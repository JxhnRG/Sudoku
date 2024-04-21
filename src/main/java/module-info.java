module org.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sudoku to javafx.fxml;
    exports org.example.sudoku;
    exports org.example.sudoku.view;
    opens org.example.sudoku.view to javafx.fxml;
    exports org.example.sudoku.controller;
    opens org.example.sudoku.controller to javafx.fxml;
}