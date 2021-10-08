package com.mk.editor.controllers.sidebar;

import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.utils.TextFieldConverter;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {
  public enum State { CameraWorld, Object } // типы состояний
  private final World3D world;
  private final Camera3D camera; // камеры мира
  private State state; // состояния отображения разметки в данный момент

  // Поля вводы для изменения свойств камеры
  @FXML private TextField tx = new TextField("0,000");
  @FXML private TextField ty = new TextField("0,000");
  @FXML private TextField tz = new TextField("0,000");
  @FXML private TextField azimuth = new TextField("0,000");
  @FXML private TextField altitude = new TextField("0,000");
  @FXML private TextField zoom = new TextField("0,000");

  // Инициализация разметки с контроллером
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    switch (this.state) {
      case Object:
        System.out.println("123");
        break;
      case CameraWorld:
      default:
        this.bindCameraProperties();
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

  // методы связки полей ввода камеры с ее свойствами
  /**
   * Общий метод для связывания свойств камеры с полями
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
   * Связывает свойство X
   */
  private void bindCameraXProperty() {
    Bindings.bindBidirectional(
      this.tx.textProperty(),
      this.camera.camXProperty(),
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
   * Связывает свойство Y
   */
  private void bindCameraYProperty() {
    Bindings.bindBidirectional(
      this.ty.textProperty(),
      this.camera.camYProperty(),
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
   * Связывает свойство Z
   */
  private void bindCameraZProperty() {
    Bindings.bindBidirectional(
      this.tz.textProperty(),
      this.camera.camZProperty(),
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
   * Связывает свойство угла азимута
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
   * Связывает свойство угла высоты
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
   * Связывает свойство зума
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
}
