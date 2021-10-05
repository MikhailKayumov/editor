package com.mk.editor.entities;

import com.mk.editor.utils.Axes;
import com.mk.editor.utils.Grid;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

public class World3D extends Object3D {
  private final Object3D meshes = new Object3D();
  private Axes axis = new Axes(1, 40);
  private Grid grid = new Grid();
  private Node pickedNode;

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

  public Object3D getMeshes() {
    return this.meshes;
  }

  public void setPickedNode(Node node) {
    this.delPickedNode();
    this.pickedNode = node;

    if (this.pickedNode instanceof Shape3D) {
      PhongMaterial pickedMaterial = new PhongMaterial(Color.rgb(232, 162, 56));
      ((Shape3D) this.pickedNode).setMaterial(pickedMaterial);
    }
  }
  public void delPickedNode() {
    if (this.pickedNode != null) {
      PhongMaterial pickedMaterial = new PhongMaterial(Color.WHITE);
      pickedMaterial.setSpecularColor(Color.WHITE);
      ((Shape3D) this.pickedNode).setMaterial(pickedMaterial);
    }
    this.pickedNode = null;
  }
}
