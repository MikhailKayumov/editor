package com.mk.editor.gui;

import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.gui.sidebar.Sidebar;
import com.mk.editor.gui.toolbar.Toolbar;
import com.mk.editor.gui.viewport.Viewport;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// класс для отрисовки пользовательского интерфейса
public final class Main {
  private final Window window; // окно приложения
  private final Scene scene; // основная сцена
  private final GridPane root; // сетка для размещения

  // основные компоненты редактора
  private ArrayList<MainRegion> nodes = new ArrayList<>();

  private World3D world; // контейнер для 3d объектов
  private Camera3D camera; // камера приложения

  /**
   * Конструктор
   * @param stage - основное состояние приложения
   */
  public Main(Stage stage) {
    this.window = new Window(stage);
    this.root = new GridPane();
    this.scene = new Scene(this.root, 1240, 720);

    this.world = new World3D();
    this.camera = new Camera3D();

    this.nodes.add(new Viewport(this.world, this.camera, stage));
    this.nodes.add(new Toolbar(this.world));
    this.nodes.add(new Sidebar(this.world, this.camera));
  }

  /**
   * Отрисовывает весь контент
   * @throws IOException - возможные ошибки ввода\вывода
   */
  public void render() throws IOException {
    this.layout();
    this.fill();
    this.window.show(this.scene);
    for (MainRegion node: this.nodes) node.render();
  }

  /**
   * Создает разметку для основных панелей
   */
  private void layout() {
    // создания колонн и их размеров
    ColumnConstraints column1 = new ColumnConstraints(400, 300, Double.MAX_VALUE, Priority.ALWAYS, HPos.RIGHT, true);
    this.root.getColumnConstraints().add(column1);
    ColumnConstraints column2 = new ColumnConstraints(302);
    column2.setHalignment(HPos.RIGHT);
    this.root.getColumnConstraints().add(column2);

    // создания строк и их размеров
    RowConstraints row1 = new RowConstraints(42);
    row1.setValignment(VPos.TOP);
    this.root.getRowConstraints().add(row1);
    RowConstraints row2 = new RowConstraints(400, 400, Double.MAX_VALUE, Priority.ALWAYS, VPos.TOP, true);
    this.root.getRowConstraints().add(row2);
  }
  /**
   * Наполняет основные панели контентом
   */
  private void fill() {
    for (MainRegion node: this.nodes) {
      this.root.add(
        node.getRoot(),
        node.getColumnId(),
        node.getRowId(),
        node.getColspan(),
        node.getRowspan()
      );
    }
  }
}
