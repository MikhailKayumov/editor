package com.mk.editor.gui.statusbar;

import com.mk.editor.gui.MainRegion;
import com.mk.editor.gui.utils.AppColor;
import com.mk.editor.gui.utils.BorderPosition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Statusbar extends MainRegion {
  public Statusbar() {
    super(
      BorderPosition.TOP,
      AppColor.BGPrimary,
      0,
      2,
      2,
      1
    );

    Label content = new Label("Statusbar");
    content.setPadding(new Insets(3, 5, 0, 5));
    content.setTextFill(AppColor.FontPrimary);
    content.setFont(new Font("Roboto", 12));
    content.setLineSpacing(0.0);

    this.addContent(content);
  }
}
