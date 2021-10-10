package com.mk.editor.controllers.sidebar;

import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.shapes.BaseMesh;
import com.mk.editor.utils.AppColor;
import com.mk.editor.utils.TextFieldConverter;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public final class SidebarController implements Initializable {
  public enum State { CameraWorld, Object } // типы состояний
  private final World3D world; // мир сцены
  private final Camera3D camera; // камеры мира
  private State state; // состояния отображения разметки в данный момент

  // Поля вводы для изменения свойств камеры
  @FXML private TextField tx = new TextField("0,000");
  @FXML private TextField ty = new TextField("0,000");
  @FXML private TextField tz = new TextField("0,000");
  @FXML private TextField azimuth = new TextField("0,000");
  @FXML private TextField altitude = new TextField("0,000");
  @FXML private TextField zoom = new TextField("0,000");
  @FXML private ColorPicker bgColor = new ColorPicker();

  // Поля вводы для изменения свойств объекта
  @FXML private TextField x = new TextField("0,000");
  @FXML private TextField y = new TextField("0,000");
  @FXML private TextField z = new TextField("0,000");
  @FXML private TextField rx = new TextField("0,000");
  @FXML private TextField ry = new TextField("0,000");
  @FXML private TextField rz = new TextField("0,000");
  @FXML private TextField sx = new TextField("0,000");
  @FXML private TextField sy = new TextField("0,000");
  @FXML private TextField sz = new TextField("0,000");
  @FXML private ColorPicker objectColor = new ColorPicker();

  // Инициализация разметки с контроллером
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    switch (this.state) {
      case Object:
        this.bindObjectProperties();
        break;
      case CameraWorld:
      default:
        this.bindCameraProperties();
        this.initChangeWorldBGColor();
    }
  }

  /**
   * Конструктор
   * @param world - мир
   * @param camera - камера
   */
  public SidebarController(World3D world, Camera3D camera) {
    this.world = world;
    this.camera = camera;
    this.state = State.CameraWorld;
  }
  /**
   * Устанавливает текущее состояние
   * @param state - состояние
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * Общий метод для связывания свойств перемещения,
   * поворота и зума камеры с полями ввода
   */
  private void bindCameraProperties() {
    this.bindCameraXProperty();
    this.bindCameraYProperty();
    this.bindCameraZProperty();
    this.bindCameraAzimuthProperty();
    this.bindCameraAltitudeProperty();
    this.bindCameraZoomProperty();
  }
  /**
   * Связывает координату X камеры с полем ввода
   */
  private void bindCameraXProperty() {
    Bindings.bindBidirectional(
      this.tx.textProperty(),
      this.camera.xProperty(),
      TextFieldConverter.buildConverter(
        number -> (double)number / 10,
        number -> (double)number * 10
      )
    );
    this.tx.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.tx.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает координату Y камеры с полем ввода
   */
  private void bindCameraYProperty() {
    Bindings.bindBidirectional(
      this.ty.textProperty(),
      this.camera.yProperty(),
      TextFieldConverter.buildConverter(
        number -> (double)number / 10,
        number -> (double)number * 10
      )
    );
    this.ty.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.ty.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает координату Z камеры с полем ввода
   */
  private void bindCameraZProperty() {
    Bindings.bindBidirectional(
      this.tz.textProperty(),
      this.camera.zProperty(),
      TextFieldConverter.buildConverter(
        number -> (double)number / 10,
        number -> (double)number * 10
      )
    );
    this.tz.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.tz.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает угл азимута камеры с полем ввода
   */
  private void bindCameraAzimuthProperty() {
    Bindings.bindBidirectional(
      this.azimuth.textProperty(),
      this.camera.azimuthProperty(),
      TextFieldConverter.buildConverter(
        number -> number,
        number -> number
      )
    );
    this.azimuth.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.azimuth.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает угл высоты камеры с полем ввода
   */
  private void bindCameraAltitudeProperty() {
    Bindings.bindBidirectional(
      this.altitude.textProperty(),
      this.camera.altitudeProperty(),
      TextFieldConverter.buildConverter(
        number -> ((double)number + 90) % 360,
        number -> (double)number - 90
      )
    );
    this.altitude.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.altitude.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает зум камеры с полем ввода
   */
  private void bindCameraZoomProperty() {
    Bindings.bindBidirectional(
      this.zoom.textProperty(),
      this.camera.zoomProperty(),
      TextFieldConverter.buildConverter(
        number -> (double)number / 10 * -1,
        number -> (double)number * 10 * -1
      )
    );
    this.zoom.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.zoom.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Устанавливает обработчик заливки всей сцены
   */
  private void initChangeWorldBGColor() {
    this.bgColor.valueProperty().setValue(AppColor.BGScene);
    this.bgColor.valueProperty().addListener(event -> {
      this.world.setBGSceneColor(((ObjectProperty<Color>)event).getValue());
    });
  }

  /**
   * Общий метод для связывания свойств перемещения,
   * поворота и масштабирования объекта с полями ввода
   */
  private void bindObjectProperties() {
    this.bindObjectXProperty();
    this.bindObjectYProperty();
    this.bindObjectZProperty();
    this.bindObjectRXProperty();
    this.bindObjectRYProperty();
    this.bindObjectRZProperty();
    this.bindObjectSXProperty();
    this.bindObjectSYProperty();
    this.bindObjectSZProperty();
    this.initChangeObjectBGColor();
  }
  /**
   * Связывает координату X камеры с полем ввода
   */
  private void bindObjectXProperty() {
    Bindings.bindBidirectional(
      this.x.textProperty(),
      this.world.getPickedMesh().xProperty(),
      TextFieldConverter.buildConverter(
        number -> (double)number / 10,
        number -> (double)number * 10
      )
    );
    this.x.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.x.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает координату Y камеры с полем ввода
   */
  private void bindObjectYProperty() {
    Bindings.bindBidirectional(
      this.y.textProperty(),
      this.world.getPickedMesh().yProperty(),
      TextFieldConverter.buildConverter(
        number -> (double)number / 10,
        number -> (double)number * 10
      )
    );
    this.y.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.y.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает координату Z камеры с полем ввода
   */
  private void bindObjectZProperty() {
    Bindings.bindBidirectional(
      this.z.textProperty(),
      this.world.getPickedMesh().zProperty(),
      TextFieldConverter.buildConverter(
        number -> (double)number / 10,
        number -> (double)number * 10
      )
    );
    this.z.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.z.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает угла объекта вокруг оси X с полем ввода
   */
  private void bindObjectRXProperty() {
    Bindings.bindBidirectional(
      this.rx.textProperty(),
      this.world.getPickedMesh().rxProperty(),
      TextFieldConverter.buildConverter(
        number -> number,
        number -> number
      )
    );
    this.rx.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.rx.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает угла объекта вокруг оси Y с полем ввода
   */
  private void bindObjectRYProperty() {
    Bindings.bindBidirectional(
      this.ry.textProperty(),
      this.world.getPickedMesh().ryProperty(),
      TextFieldConverter.buildConverter(
        number -> number,
        number -> number
      )
    );
    this.ry.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.ry.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает угла объекта вокруг оси Z с полем ввода
   */
  private void bindObjectRZProperty() {
    Bindings.bindBidirectional(
      this.rz.textProperty(),
      this.world.getPickedMesh().rzProperty(),
      TextFieldConverter.buildConverter(
        number -> number,
        number -> number
      )
    );
    this.rz.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.rz.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает масштабирования объекта по оси X с полем ввода
   */
  private void bindObjectSXProperty() {
    Bindings.bindBidirectional(
      this.sx.textProperty(),
      this.world.getPickedMesh().sxProperty(),
      TextFieldConverter.buildConverter(
        number -> number,
        number -> number
      )
    );
    this.sx.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.sx.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает масштабирования объекта по оси Y с полем ввода
   */
  private void bindObjectSYProperty() {
    Bindings.bindBidirectional(
      this.sy.textProperty(),
      this.world.getPickedMesh().syProperty(),
      TextFieldConverter.buildConverter(
        number -> number,
        number -> number
      )
    );
    this.sy.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.sy.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Связывает масштабирования объекта по оси Z с полем ввода
   */
  private void bindObjectSZProperty() {
    Bindings.bindBidirectional(
      this.sz.textProperty(),
      this.world.getPickedMesh().szProperty(),
      TextFieldConverter.buildConverter(
        number -> number,
        number -> number
      )
    );
    this.sz.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.sz.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
  }
  /**
   * Устанавливает обработчик заливки всей сцены
   */
  private void initChangeObjectBGColor() {
    BaseMesh mesh = this.world.getPickedMesh();
    this.objectColor.valueProperty().setValue(mesh.getMaterial());
    this.objectColor.valueProperty().addListener(event -> {
      mesh.setMaterial(((ObjectProperty<Color>)event).getValue());
    });
  }
}
