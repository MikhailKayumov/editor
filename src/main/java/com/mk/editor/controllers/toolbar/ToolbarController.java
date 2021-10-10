package com.mk.editor.controllers.toolbar;

import com.mk.editor.entities.World3D;
import com.mk.editor.shapes.BaseMesh;
import com.mk.editor.shapes.CubeMesh;
import com.mk.editor.shapes.CylinderMesh;
import com.mk.editor.shapes.SphereMesh;

public final class ToolbarController {
  World3D world;

  public ToolbarController(World3D world) {
    this.world = world;
  }

  // public void startCreatingPlane(MouseEvent event) {
  //   System.out.println("Start Creating Plane: " + event);
  // }
  /**
   * Обработчик на нажатие кнопки для создания куба
   */
  public void startCreatingCube() {
    CubeMesh mesh = new CubeMesh();
    this.addNewMesh(mesh);
  }
  /**
   * Обработчик на нажатие кнопки для создания цилиндра
   */
  public void startCreatingCylinder() {
    CylinderMesh mesh = new CylinderMesh();
    this.addNewMesh(mesh);
  }
  /**
   * Обработчик на нажатие кнопки для создания сферы
   */
  public void startCreatingSphere() {
    SphereMesh mesh = new SphereMesh();
    this.addNewMesh(mesh);
  }
  // public void startCreatingPyramid(MouseEvent event) {
  //   System.out.println("Start Creating Pyramid: " + event);
  // }

  /**
   * Обработчик на нажатие кнопки для создания изображения сцены
   */
  public void renderScene() {
    this.world.makeImg();
  }

  /**
   * Добавляет объект в мир после нажатия на соответствующую кнопку
   * @param mesh - объект
   */
  private void addNewMesh(BaseMesh mesh) {
    if (this.world.isSetPickedNode()) this.world.delPickedNode();
    this.world.addMesh(mesh);
    this.world.setPickedNode(mesh);
  }
}
