package com.mk.editor.gui.toolbar;

import com.mk.editor.gui.MainRegion;
import com.mk.editor.gui.utils.AppColor;
import com.mk.editor.gui.utils.BorderPosition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Toolbar extends MainRegion {
  public Toolbar() {
    super(BorderPosition.BOTTOM, AppColor.BGPrimary, 0, 0);

    Label content = new Label("Toolbar");
    content.setPadding(new Insets(5));
    content.setTextFill(AppColor.FontPrimary);
    content.setFont(new Font("Roboto", 20));
    content.setLineSpacing(20);

    this.addContent(content);
  }
}
