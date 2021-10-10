package com.mk.editor.shapes;

import javafx.scene.shape.Box;

public class CubeMesh extends BaseMesh {
  private static int count = 1;

  public CubeMesh() {
    super("Cube", CubeMesh.count++, new Box(10, 10, 10), new Box(10, 10, 10));
  }

  public void setSize(double size) {
    ((Box)this.shape).setWidth(size);
    ((Box)this.shape).setHeight(size);
    ((Box)this.shape).setDepth(size);

    ((Box)this.shapeOutline).setWidth(size);
    ((Box)this.shapeOutline).setHeight(size);
    ((Box)this.shapeOutline).setDepth(size);
  }
}
