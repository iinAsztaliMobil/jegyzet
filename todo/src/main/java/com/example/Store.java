package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {

    private final Path file;
    private final ObservableList<String> items = FXCollections.observableArrayList();

    public Store(String fileName) {
        this.file = Path.of(fileName);
        load();
    }

    public ObservableList<String> getItems() {
        return items;
    }

    public void add(String text) {
        System.out.println("adding");
        if (text == null || text.isBlank()) {
            return;
        }
        items.add(text.strip());
        save();
    }

    public void update(int index, String text) {
        System.out.println("updating");
        if (!isValid(index) || text == null || text.isBlank()) {
            return;
        }
        items.set(index, text.strip());
        save();
    }

    public void remove(int index) {
        System.out.println("removing");
        if (!isValid(index)) {
            return;
        }
        items.remove(index);
        save();
    }

    private boolean isValid(int index) {
        return index >= 0 && index < items.size();
    }

    private void load() {
        try {
            if (Files.exists(file)) {
                items.setAll(Files.readAllLines(file, StandardCharsets.UTF_8));
            } else {
                try (InputStream in = getClass().getResourceAsStream("/todo.txt")) {
                    if (in != null) {
                        String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                        items.setAll(content.lines()
                                .filter(line -> !line.isBlank())
                                .collect(Collectors.toList()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try {
            Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
            Files.write(tmp, List.copyOf(items), StandardCharsets.UTF_8);
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}