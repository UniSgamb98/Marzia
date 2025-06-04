package com.orodent.marzia.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ListItem extends HBox {
    public final SimpleBooleanProperty toBeRemoved;
    public final String text;

    public ListItem(String text) {
        super(10); // spacing
        this.text = text;
        toBeRemoved = new SimpleBooleanProperty(false);
        Label label = new Label(text);
        Button removeButton = new Button("X");
        this.getChildren().addAll(label, removeButton);

        // Stile opzionale
        this.setStyle("-fx-padding: 5;");

        // Rimuove l'elemento quando cliccato
        removeButton.setOnAction(e -> toBeRemoved.set(true));

        // CSS
        label.getStyleClass().add("list-item-label");
        removeButton.getStyleClass().add("list-item-button");
    }

    public String getText() {
        return text;
    }
}