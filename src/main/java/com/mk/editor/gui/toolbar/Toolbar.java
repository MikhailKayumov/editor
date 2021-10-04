package com.mk.editor.gui.toolbar;

import com.mk.editor.gui.MainRegion;
import com.mk.editor.gui.utils.AppColor;
import com.mk.editor.gui.utils.BorderPosition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class Toolbar extends MainRegion {
  public Toolbar() {
    super(BorderPosition.BOTTOM, AppColor.BGPrimary, 0, 0);
    this.addContent(this.createShapeButtons());
  }

  public void render() {
    this.content.forEach((node) -> this.root.getChildren().add(node));
  }

  private HBox createShapeButtons() {
    FontIcon iBox = new FontIcon("bx-cube");
    iBox.setIconColor(AppColor.FontPrimary);
    iBox.setIconSize(20);

    FontIcon iCylinder = new FontIcon("bx-cylinder");
    iCylinder.setIconColor(AppColor.FontPrimary);
    iCylinder.setIconSize(20);

    Button btnBox = new Button("", iBox);
    btnBox.setMinSize(30, 30);
    btnBox.setMaxSize(30, 30);
    btnBox.setPrefSize(30, 30);
    btnBox.setCursor(Cursor.HAND);
    btnBox.setStyle("-fx-background-color: " + AppColor.getCSS(AppColor.BGButton) + ";");
    btnBox.setOnMouseEntered(e -> {
      btnBox.setStyle(
        "-fx-background-color: " + AppColor.getCSS(AppColor.BGButtonHover) + ";"
      );
    });
    btnBox.setOnMousePressed(e -> {
      btnBox.setStyle(
        "-fx-background-color: " + AppColor.getCSS(AppColor.BGButtonActive) + ";"
      );
    });
    btnBox.setOnMouseExited(e -> {
      btnBox.setStyle(
        "-fx-background-color: " + AppColor.getCSS(AppColor.BGButton) + ";");
    });
    btnBox.setOnMouseReleased(e -> {
      btnBox.setStyle(
        "-fx-background-color: " + AppColor.getCSS(AppColor.BGButtonHover) + ";");
    });

    Button btnCylinder = new Button("", iCylinder);
    btnCylinder.setMinSize(30, 30);
    btnCylinder.setMaxSize(30, 30);
    btnCylinder.setPrefSize(30, 30);
    btnCylinder.setCursor(Cursor.HAND);
    btnCylinder.setStyle("-fx-background-color: " + AppColor.getCSS(AppColor.BGButton) + ";");
    btnCylinder.setOnMouseEntered(e -> {
      btnCylinder.setStyle(
        "-fx-background-color: " + AppColor.getCSS(AppColor.BGButtonHover) + ";"
      );
    });
    btnCylinder.setOnMousePressed(e -> {
      btnCylinder.setStyle(
        "-fx-background-color: " + AppColor.getCSS(AppColor.BGButtonActive) + ";"
      );
    });
    btnCylinder.setOnMouseExited(e -> {
      btnCylinder.setStyle(
        "-fx-background-color: " + AppColor.getCSS(AppColor.BGButton) + ";");
    });
    btnCylinder.setOnMouseReleased(e -> {
      btnCylinder.setStyle(
        "-fx-background-color: " + AppColor.getCSS(AppColor.BGButtonHover) + ";");
    });

    HBox box = new HBox(5, btnBox, btnCylinder);
    box.setPadding(new Insets(5));
    box.setAlignment(Pos.BASELINE_CENTER);

    return box;
  }
}
