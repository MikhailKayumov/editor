module com.mk.editor {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.bootstrapfx.core;
  requires java.desktop;

  opens com.mk.editor to javafx.fxml;
  exports com.mk.editor;
}