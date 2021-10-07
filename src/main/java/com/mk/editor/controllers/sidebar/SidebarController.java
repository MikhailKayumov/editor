package com.mk.editor.controllers.sidebar;

import com.mk.editor.entities.Camera3D;
import com.mk.editor.entities.World3D;
import com.mk.editor.utils.Formater;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {
  public enum State { CameraWorld, Object }
  private final World3D world;
  private final Camera3D camera;
  private final NumberStringConverter converter;
  private State state;

  @FXML private TextField zoom = new TextField("0,000");

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    switch (this.state) {
      case Object:
        System.out.println("123");
        break;
      case CameraWorld:
      default:
        this.initCameraProperty();
    }
  }

  public SidebarController(World3D world, Camera3D camera) {
    this.world = world;
    this.camera = camera;
    this.state = State.CameraWorld;
    this.converter = new NumberStringConverter() {
      @Override
      public String toString(Number value) {
        return String.format(new Locale("en-US"), "%.3f", (double)value / 10 * -1);
      }
      @Override
      public Double fromString(String value) {
        return Double.parseDouble(Formater.formatDoubleString(value)) * 10 * -1;
      }
    };
  }
  public void setState(State state) {
    this.state = state;
  }

  // методы инициализации камеры и мира
  private void initCameraProperty() {
    this.initCameraZoomProperty();
  }
  private void initCameraZoomProperty() {
    Bindings.bindBidirectional(
      this.zoom.textProperty(),
      this.camera.zoomProperty(),
      this.converter
    );

    this.zoom.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        this.zoom.setText(newValue.replaceAll("[^\\d.-]", ""));
      }
    });
    this.zoom.focusedProperty().addListener((change) -> {
      if (!((ReadOnlyBooleanProperty)change).get()) {
        this.zoom.setText(Formater.formatDoubleString(this.zoom.getText()));
      }
    });
  }
}
