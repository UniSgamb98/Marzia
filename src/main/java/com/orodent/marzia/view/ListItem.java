package com.orodent.marzia.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ListItem extends HBox {
    public final SimpleBooleanProperty toBeRemoved;
    private boolean cannotBeRemoved;
    private String bilanciaValue;
    private String micrometerValue;
    private final Button removeButton;
    private final Label label;

    public static int n = 1;

    public ListItem(String text, boolean device) {
        super(10); // spacing
        bilanciaValue = "";
        micrometerValue = "";
        if (device) {
            bilanciaValue = text;
        } else {
            micrometerValue = text;
        }
        toBeRemoved = new SimpleBooleanProperty(false);
        cannotBeRemoved = false;
        label = new Label(micrometerValue+ "\t" + bilanciaValue);
        Label nl = new Label(n+".");
        n++;
        removeButton = new Button("X");
        this.getChildren().addAll(nl, label, removeButton);

        // Stile opzionale
        this.setStyle("-fx-padding: 5;");
        this.setAlignment(Pos.BASELINE_CENTER);

        // Rimuove l'elemento quando cliccato
        removeButton.setOnAction(e -> {
            if (!cannotBeRemoved)
                toBeRemoved.set(true);
        });

        // CSS
        label.getStyleClass().add("list-item-label");
        removeButton.getStyleClass().add("list-item-button");
        removeButton.getStyleClass().remove("button");
    }

    public void setBilanciaText(String text){
        bilanciaValue = text;
        label.setText(micrometerValue+ "\t" + bilanciaValue);
    }

    public void setMicrometerText(String text){
        micrometerValue = text;
        label.setText(micrometerValue+ "\t" + bilanciaValue);
    }

    public String getText() {
        return micrometerValue+ "\t" + bilanciaValue;
    }

    public void disable(){
        cannotBeRemoved = true;
        removeButton.setDisable(true);
        removeButton.setText("");
        removeButton.getStyleClass().remove("list-item-button");
        removeButton.getStyleClass().add("list-item-button-disabled");
    }

    public void enable(){
        cannotBeRemoved = false;
        removeButton.setDisable(false);
        removeButton.setText("X");
        removeButton.getStyleClass().remove("list-item-button-disabled");
        removeButton.getStyleClass().add("list-item-button");
    }
}