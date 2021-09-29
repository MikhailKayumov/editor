package com.mk.editor.gui.viewport;

import com.mk.editor.gui.MainRegion;
import com.mk.editor.gui.utils.AppColor;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Viewport extends MainRegion {
  public Viewport() {
    super(AppColor.BGScene, 0, 1);

    Label content = new Label("Viewport");
    content.setPadding(new Insets(5));
    content.setTextFill(AppColor.FontPrimary);
    content.setFont(new Font("Roboto", 20));
    content.setLineSpacing(20);

    this.addContent(content);
  }
}
