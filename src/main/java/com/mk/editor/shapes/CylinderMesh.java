package com.mk.editor.shapes;

import javafx.scene.shape.Cylinder;

public class CylinderMesh extends BaseMesh {
  private static int count = 1;

  public CylinderMesh() {
    super(
      "Cylinder",
      CylinderMesh.count++,
      new Cylinder(10, 10),
      new Cylinder(10, 10)
    );
  }

  public void setRadius(double radius) {
    ((Cylinder)this.shape).setRadius(radius);
    ((Cylinder)this.shapeOutline).setRadius(radius);
  }

  public void setHeight(double height) {
    ((Cylinder)this.shape).setHeight(height);
    ((Cylinder)this.shapeOutline).setHeight(height);
  }
}
