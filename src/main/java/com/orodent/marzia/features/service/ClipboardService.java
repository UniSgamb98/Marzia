package com.orodent.marzia.features.service;

import com.orodent.marzia.app.AppModel;
import com.orodent.marzia.features.view.partials.ListItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ClipboardService {
    private final AppModel model;

    public ClipboardService(AppModel model) {
        this.model = model;
    }

    public void copyMeasurementsToClipboard() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(buildMeasurementsText());
        clipboard.setContent(content);
    }

    public String buildMeasurementsText() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (ListItem item : model.measurement) {
            if (!first) {
                sb.append("\n");
            }
            sb.append(item.getText());
            first = false;
        }

        return sb.toString();
    }
}
