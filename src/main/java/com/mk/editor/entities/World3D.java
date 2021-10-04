package com.mk.editor.entities;

import javafx.scene.Node;

public class World3D extends Object3D {
  public World3D() {
    super();
  }

  public void addChild(Node node) {
    this.getChildren().add(node);
  }
  public void addChildren(Node... node) {
    this.getChildren().addAll(node);
  }
}
