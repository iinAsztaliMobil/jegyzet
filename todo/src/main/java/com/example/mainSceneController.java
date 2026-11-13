package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class mainSceneController {

    private final Store store = new Store("todo.txt");

    @FXML
    private TextField inputElement;

    @FXML
    private ListView<String> listViewElement;

    @FXML
    public void initialize() {
        listViewElement.setItems(store.getItems());

        listViewElement.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldValue, newValue) -> {
                if (newValue != null) {
                    inputElement.setText(newValue);
                }
            });
    }

    @FXML
    void onClickAdd(ActionEvent event) {
        String text = inputElement.getText().trim();
        if (text.isEmpty()) return;

        store.add(text);
        inputElement.clear();
    }

    @FXML
    void onClickEdit(ActionEvent event) {
        int index = listViewElement.getSelectionModel().getSelectedIndex();
        String text = inputElement.getText().trim();
        if (index < 0 || text.isEmpty()) return;

        store.update(index, text);
    }

    @FXML
    void onClickDelete(ActionEvent event) {
        int index = listViewElement.getSelectionModel().getSelectedIndex();
        if (index < 0) return;

        store.remove(index);
        inputElement.clear();
    }
}