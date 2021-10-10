package com.mk.editor.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Sphere;

public class SphereMesh extends BaseMesh {
  private static int count = 1;

  public SphereMesh() {
    super("Sphere", SphereMesh.count++, new Sphere(10), new Sphere(10));
  }

  public void setRadius(double radius) {
    ((Sphere)this.shape).setRadius(radius);
    ((Sphere)this.shapeOutline).setRadius(radius);
  }

  public DoubleProperty sphereRadius() {
    return ((Sphere)this.shape).radiusProperty();
  }
}
