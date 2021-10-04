package com.mk.editor.gui.sidebar;

import com.mk.editor.gui.MainRegion;
import com.mk.editor.gui.utils.AppColor;
import com.mk.editor.gui.utils.BorderPosition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Sidebar extends MainRegion {
  public Sidebar() {
    super(
      BorderPosition.LEFT,
      AppColor.BGPrimary,
      1,
      0,
      1,
      2
    );

    Label content = new Label("Sidebar");
    content.setPadding(new Insets(5));
    content.setTextFill(AppColor.FontPrimary);
    content.setFont(new Font("Roboto", 20));
    content.setLineSpacing(20);

    this.addContent(content);
  }

  public void render() {
    this.content.forEach((node) -> this.root.getChildren().add(node));
  }
}
