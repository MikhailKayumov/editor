module com.mk.editor {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.ikonli.core;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.ikonli.boxicons;
  requires org.kordamp.bootstrapfx.core;
  requires java.desktop;
  requires javafx.swing;

  opens com.mk.editor to javafx.fxml;
  opens com.mk.editor.controllers.sidebar to javafx.fxml;
  opens com.mk.editor.controllers.toolbar to javafx.fxml;

  exports com.mk.editor;
  exports com.mk.editor.controllers.sidebar;
  exports com.mk.editor.controllers.toolbar;
}