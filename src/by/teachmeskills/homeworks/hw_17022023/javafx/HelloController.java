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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
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

    private static int[] array = new int[0];


    @FXML
    void initialize() {
        String[] items = {"Сумма элементов",
                "Четные/Нечетные", "Отрицательные/Положительные", "Среднее",
                "Минимальный/Максимальный", "Перевернуть", "Отсортировать"};
        cbOption.getItems().addAll(items);

        btOpen.setOnAction(event -> {
            File arrayFile = openFile(HelloApplication.scene);
            int[] tempArray;
            try {
                Scanner scanner = new Scanner(arrayFile);
                while (scanner.hasNextInt()) {
                    tempArray = new int[array.length + 1];
                    System.arraycopy(array, 0, tempArray, 0, array.length);
                    tempArray[tempArray.length - 1] = scanner.nextInt();
                    array = new int[array.length + 1];
                    System.arraycopy(tempArray, 0, array, 0, array.length);
                }
                txtInput.setText(Arrays.toString(array));

            } catch (FileNotFoundException e) {
                txtOutput.setText("Файл не найден");
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
            if (array != null && array.length != 0)
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
                        txtOutput.setText(Arrays.toString(array));
                        txtInput.setText(Arrays.toString(array));
                    }
                    default -> {
                        ArrayOperations.sort(array);
                        ArrayOperations.sort(array);
                        txtOutput.setText(Arrays.toString(array));
                        txtInput.setText(Arrays.toString(array));
                    }
                }
        });

        btSearch.setOnAction(event -> {
            if (array != null && array.length != 0)
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