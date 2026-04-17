package com.orodent.marzia.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ListItem extends HBox {
    public final SimpleBooleanProperty toBeRemoved;
    private String bilanciaValue;
    private String micrometerValue;
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
        label = new Label(micrometerValue+ "\t" + bilanciaValue);
        Label nl = new Label(n+".");
        n++;
        Button bilanciaRemoveButton = new Button("X");
        Button micrometroRemoveButton = new Button("X");
        this.getChildren().addAll(nl, micrometroRemoveButton, label, bilanciaRemoveButton);

        // Stile opzionale
        this.setStyle("-fx-padding: 5;");
        this.setAlignment(Pos.BASELINE_CENTER);

        // Rimuove l'elemento quando cliccato
        bilanciaRemoveButton.setOnAction(e -> {
            setBilanciaText("");
            //if (micrometerValue.isEmpty())
                //toBeRemoved.set(true);
        });
        micrometroRemoveButton.setOnAction(e -> {
            setMicrometerText("");
            //if (bilanciaValue.isEmpty())
                //toBeRemoved.set(true); TODO da sistemare il remove
        });

        // CSS
        label.getStyleClass().add("list-item-label");
        bilanciaRemoveButton.getStyleClass().add("list-item-button");
        bilanciaRemoveButton.getStyleClass().remove("button");
        micrometroRemoveButton.getStyleClass().add("list-item-button");
        micrometroRemoveButton.getStyleClass().remove("button");
    }

    public void setBilanciaText(String text){
        bilanciaValue = text;
        label.setText(micrometerValue+ "\t" + bilanciaValue);
    }

    public void setMicrometerText(String text){
        micrometerValue = text;
        label.setText(micrometerValue+ "\t" + bilanciaValue);
    }

    public String getBilanciaValue(){
        return bilanciaValue;
    }

    public String getMicrometerValue(){
        return micrometerValue;
    }

    public String getText() {
        return micrometerValue+ "\t" + bilanciaValue;
    }
}