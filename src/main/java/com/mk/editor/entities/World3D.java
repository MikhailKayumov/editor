package com.mk.editor.entities;

import com.mk.editor.utils.Axes;
import com.mk.editor.utils.Grid;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

public class World3D extends Object3D {
  private final Object3D meshes = new Object3D();
  private Axes axis = new Axes(1, 40);
  private Grid grid = new Grid();
  private SimpleObjectProperty<Shape3D> pickedNode = new SimpleObjectProperty<>(null);

  public World3D() {
    super();
    this.addChildren(this.axis, this.grid, this.meshes);
  }

  public void addMesh(Node node) {
    this.meshes.getChildren().add(node);
  }
  public void addMesh(Node... node) {
    this.meshes.getChildren().addAll(node);
  }
  public void deleteMesh() {
    Shape3D node = this.pickedNode.get();
    if (node != null) {
      this.meshes.getChildren().remove(node);
      this.delPickedNode();
    }
  }

  public Object3D getMeshes() {
    return this.meshes;
  }

  public SimpleObjectProperty<Shape3D> getPickedNode() {
    return this.pickedNode;
  }
  public boolean isSetPickedNode() {
    return this.pickedNode.get() != null;
  }
  public void setPickedNode(Node node) {
    if (this.pickedNode.get() != null) this.delPickedNode();
    if (node instanceof Shape3D) {
      PhongMaterial pickedMaterial = new PhongMaterial(Color.rgb(232, 162, 56));
      ((Shape3D) node).setMaterial(pickedMaterial);
      this.pickedNode.set((Shape3D) node);
    }
  }
  public void delPickedNode() {
    Shape3D node = this.pickedNode.get();
    if (node != null) {
      PhongMaterial pickedMaterial = new PhongMaterial(Color.WHITE);
      pickedMaterial.setSpecularColor(Color.WHITE);
      node.setMaterial(pickedMaterial);
    }
    this.pickedNode.set(null);
  }
}
