package com.example.javafxtest;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloController {

    @FXML
    private Button btOpen;

    @FXML
    private Button btSave;

    @FXML
    private Button btSearch;

    @FXML
    private Button btSubmit;

    @FXML
    private ComboBox<String> cbOption;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtOutput;

    private static ArrayList<Integer> array;


    @FXML
    void initialize() {
        String[] items = {"Сумма элементов",
                "Четные/Нечетные", "Отрицательные/Положительные", "Среднее",
                "Минимальный/Максимальный", "Перевернуть", "Отсортировать"};
        cbOption.getItems().addAll(items);

        btOpen.setOnAction(event -> {
            array = new ArrayList<Integer>();
            File arrayFile = openFile(HelloApplication.scene);
            try {
                Scanner scanner = new Scanner(arrayFile);
                while (scanner.hasNextInt()) {
                    array.add(scanner.nextInt());
                }
                txtInput.setText(array.toString());

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        btSave.setOnAction(event -> {
            try {
                FileWriter outputFile = new FileWriter(saveFile(HelloApplication.scene));
                String str = txtOutput.getText();
                str = str.replaceAll("[^[\\d- ]]", "");
                outputFile.write(str);
                outputFile.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btSubmit.setOnAction(event -> {
            if (array != null && array.size() != 0)
                switch (cbOption.getSelectionModel().getSelectedIndex()) {
                    case 0 -> txtOutput.setText(Integer.toString(ArrayOperations.sum(array)));
                    case 1 -> {
                        int[] oddEven = ArrayOperations.oddEven(array);
                        txtOutput.setText("Четные: " + oddEven[0] + " Нечетных: " + oddEven[1]);
                    }
                    case 2 -> {
                        int[] positiveNegative = ArrayOperations.positiveNegative(array);
                        txtOutput.setText("Положительные: " + positiveNegative[0] + " Отрицательные: " + positiveNegative[1]);
                    }
                    case 3 -> txtOutput.setText(Double.toString(ArrayOperations.average(array)));
                    case 4 -> {
                        int[] minMax = ArrayOperations.minMax(array);
                        txtOutput.setText("Минимальный: " + minMax[0] + " Максимальный: " + minMax[1]);
                    }
                    case 5 -> {
                        ArrayOperations.reverse(array);
                        txtOutput.setText(array.toString());
                        txtInput.setText(array.toString());
                    }
                    default -> {
                        ArrayOperations.sort(array);
                        txtOutput.setText(array.toString());
                        txtInput.setText(array.toString());
                    }
                }
        });

        btSearch.setOnAction(event -> {
            if (array != null && array.size() != 0)
                txtOutput.setText("Индекс искомого элемента: " +  ArrayOperations.find(array, Integer.parseInt(txtFieldSearch.getText())));
        });

        txtFieldSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*") && !newValue.matches("-\\d*")) {
                    txtFieldSearch.setText(newValue.replaceAll("[^[\\d-]]", ""));
                }
            }
        });
    }

    private File openFile(Scene scene) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(scene.getWindow());
    }

    private File saveFile(Scene scene) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showSaveDialog(scene.getWindow());
    }

}