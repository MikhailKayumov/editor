package com.mk.editor.gui.toolbar;

import com.mk.editor.controllers.toolbar.ToolbarController;
import com.mk.editor.entities.World3D;
import com.mk.editor.gui.MainRegion;
import com.mk.editor.utils.AppColor;
import com.mk.editor.utils.BorderPosition;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

// Класс создания тулбара с кнопками возможных 3D объектов
public final class Toolbar extends MainRegion {
  private final GridPane box; // основной контейнер
  private final FXMLLoader loader; // загрузчик разметки

  /**
   * Конструктор
   * @param world - мир сцены
   */
  public Toolbar(World3D world) {
    super(BorderPosition.BOTTOM, AppColor.BGPrimary, 0, 0);
    this.root.getChildren().add(this.content);

    this.box = new GridPane();
    this.addContent(this.box);

    this.loader = new FXMLLoader(getClass().getResource("toolbar.fxml"));
    this.loader.setRoot(this.box);
    this.loader.setController(new ToolbarController(world));
  }

  /**
   * Отрисовывает контент панели
   * @throws IOException - ошибка получения файла разметки
   */
  public void render() throws IOException {
    this.box.setPrefSize(this.root.getWidth(), this.root.getHeight() - 2);
    this.root.widthProperty().addListener(e -> {
      this.box.setPrefWidth(((ReadOnlyDoubleProperty)e).getValue());
    });
    this.loader.load();
  }
}
