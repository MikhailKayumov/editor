package com.mk.editor.gui.sidebar;

import com.mk.editor.controllers.sidebar.SidebarController;
import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.gui.MainRegion;
import com.mk.editor.shapes.BaseMesh;
import com.mk.editor.shapes.CubeMesh;
import com.mk.editor.shapes.CylinderMesh;
import com.mk.editor.shapes.SphereMesh;
import com.mk.editor.utils.AppColor;
import com.mk.editor.utils.BorderPosition;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

public final class Sidebar extends MainRegion {
  private final World3D world; // мир
  private final VBox properties; // свойства камеры или объекта
  private final VBox specificMeshProps; // специфичные для объекта свойства
  private final Outliner outliner; // список объектов
  private final SidebarController ctrl; // контроллер для свойств камеры или объекта

  /**
   * Конструктор
   * @param world - мир
   * @param camera - камера
   */
  public Sidebar(World3D world, Camera3D camera) {
    super(BorderPosition.LEFT, AppColor.BGPrimary, 1, 0, 1, 2);
    this.root.getChildren().add(this.content);

    this.world = world;
    this.ctrl = new SidebarController(this.world, camera);

    this.outliner = new Outliner(this.world);
    this.properties = new VBox();
    this.specificMeshProps = new VBox();

    VBox container = new VBox();
    container.getChildren().addAll(this.outliner.getRoot(), this.properties, this.specificMeshProps);

    this.addContent(container);
  }

  /**
   * Отрисовывает контент панели
   * @throws IOException возможная ошибка получения файла разметки
   */
  public void render() throws IOException {
    this.outliner.build();

    this.properties.setFillWidth(true);
    this.properties.setMaxWidth(this.root.getWidth());

    this.renderPropertiesView();
    this.world.getPickedNode().addListener(event -> this.onPropertiesViewChange());
  }

  /**
   * Отрисовывает панель управления свойствами камеры или объекта
   * @throws IOException возможная ошибка получения файла разметки
   */
  private void renderPropertiesView() throws IOException {
    String tplFile = this.getPropertiesTemplate();

    FXMLLoader loader = new FXMLLoader(getClass().getResource(tplFile));
    loader.setRoot(this.properties);
    loader.setController(this.ctrl);
    loader.load();

    this.renderSpecificPropertiesView();
  }

  /**
   * Отрисовывает специфичную для объекта панель с контроллами
   * @throws IOException возможная ошибка получения файла разметки
   */
  private void renderSpecificPropertiesView() throws IOException {
    if (!this.world.isSetPickedNode()) return;

    String tplFile = this.getSpecificPropertiesTemplate();
    if (tplFile != null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(tplFile));
      loader.setRoot(this.specificMeshProps);
      loader.setController(this.ctrl);
      loader.load();
    }
  }

  /**
   * Возвращает имя файла разметки на основе
   * выбран какой-либо объект в сцене или нет
   * @return имя файла
   */
  private String getPropertiesTemplate() {
    this.specificMeshProps.getChildren().clear();

    if (this.world.isSetPickedNode()) {
      this.ctrl.setState(SidebarController.State.Object);
      return "object_properties.fxml";
    }

    this.ctrl.setState(SidebarController.State.CameraWorld);
    return "camera_properties.fxml";
  }

  /**
   * Возвращает имя файла разметки на основе выбранного объект в сцене
   * @return имя файла
   */
  private String getSpecificPropertiesTemplate() {
    BaseMesh mesh = this.world.getPickedMesh();
    this.ctrl.setMesh(mesh);

    if (mesh instanceof CubeMesh) {
      return "cube_properties.fxml";
    } else if (mesh instanceof SphereMesh) {
      return "sphere_properties.fxml";
    } else if (mesh instanceof CylinderMesh) {
      return "cylinder_properties.fxml";
    }

    return null;
  }

  /**
   * Обработчик смена контроллов на панели.
   * Срабатывает при выделении или снятии выделения с объекта
   */
  private void onPropertiesViewChange() {
    try {
      this.properties.getChildren().clear();
      this.renderPropertiesView();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
