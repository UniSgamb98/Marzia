package com.orodent.marzia.controller;

import com.orodent.marzia.App;
import com.orodent.marzia.view.ListItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyOnClipBoardController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (ListItem i : App.measurement){
            if (!first) {
                sb.append("\n");
            }
            sb.append(i.getText());
            first = false;
        }

        content.putString(sb.toString());
        clipboard.setContent(content);
    }
}
