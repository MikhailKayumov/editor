package com.mk.editor.shapes;

import com.mk.editor.entities.Object3D;
import com.mk.editor.utils.AppColor;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;

public abstract class BaseMesh extends Object3D {
  protected int number;
  protected String name;
  protected Shape3D shape;
  protected Shape3D shapeOutline;
  protected PhongMaterial material = new PhongMaterial();

  protected BaseMesh(String name, int number, Shape3D shape, Shape3D shapeOutline) {
    this.name = name;
    this.number = number;

    this.shape = shape;
    this.shapeOutline = shapeOutline;

    this.initShapes();
  }

  public void setMaterial(Color color) {
    this.material.setDiffuseColor(color);
  }
  public Color getMaterial() {
    return this.material.getDiffuseColor();
  }

  public void pick() {
    this.addChild(this.shapeOutline);
  }

  public void unpick(){
    this.removeChild(this.shapeOutline);
  }

  public String getName() {
    return this.name + "_" + this.number;
  }

  protected void initShapes() {
    this.shape.setMaterial(this.material);
    this.shape.setRotationAxis(Rotate.X_AXIS);
    this.shape.setRotate(90);

    this.shapeOutline.setDrawMode(DrawMode.LINE);
    this.shapeOutline.setMaterial(new PhongMaterial(AppColor.PickedColor));
    this.shapeOutline.setRotationAxis(Rotate.X_AXIS);
    this.shapeOutline.setRotate(90);

    this.addChild(this.shape);
  }
}
