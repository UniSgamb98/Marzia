package com.orodent.marzia.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class PedalInputHandler {

    private static final long HOLD_THRESHOLD = 300; // ms → pressione lunga

    private final EventHandler<ActionEvent> onSingle;
    private final EventHandler<ActionEvent> onDouble;

    private boolean keyIsDown = false;
    private boolean longPressTriggered = false;
    private PauseTransition holdTimer;

    public PedalInputHandler(Scene scene, EventHandler<ActionEvent> onSingle, EventHandler<ActionEvent> onDouble) {
        this.onSingle = onSingle;
        this.onDouble = onDouble;

        scene.addEventFilter(KeyEvent.ANY, this::handle);
    }

    private void handle(KeyEvent event) {

        if (event.getCode() != KeyCode.ENTER)
            return;

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {

            if (keyIsDown) {
                event.consume();
                return; // evita auto-repeat
            }

            keyIsDown = true;
            longPressTriggered = false;

            // timer per long press
            holdTimer = new PauseTransition(Duration.millis(HOLD_THRESHOLD));
            holdTimer.setOnFinished(ae -> {
                if (keyIsDown && !longPressTriggered) {
                    longPressTriggered = true;
                    onDouble.handle(new ActionEvent());
                }
            });
            holdTimer.play();

            event.consume();
        }

        if (event.getEventType() == KeyEvent.KEY_RELEASED && keyIsDown) {
            keyIsDown = false;

            // Se il timer è ancora attivo → pressione breve
            if (!longPressTriggered) {
                holdTimer.stop();
                onSingle.handle(new ActionEvent());
            }

            event.consume();
        }
    }
}
