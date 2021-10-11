package com.mk.editor.entities;

import com.mk.editor.shapes.BaseMesh;
import com.mk.editor.utils.Axes;
import com.mk.editor.utils.Grid;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;

// Класс мира приложения
public final class World3D extends Object3D {
  private final Object3D meshes = new Object3D(); // основной контейнер для 3D объектов
  // выбранный объект
  private final SimpleObjectProperty<BaseMesh> pickedNode = new SimpleObjectProperty<>(null);
  private Scene3D scene; // основная сцена

  /**
   * Конструктор
   */
  public World3D() {
    super();
    Grid grid = new Grid();
    Axes axis = new Axes(1, 40);
    this.addChildren(axis, grid, this.meshes);
  }

  /**
   * Устанавливает ссылку на основную сцену
   * @param scene - сцена
   */
  public void setScene(Scene3D scene) {
    this.scene = scene;
  }
  /**
   * Устанавливает цвет заливки мира
   * @param color - цвет
   */
  public void setBGSceneColor(Color color) {
    this.scene.setFill(color);
  }

  /**
   * Получает список 3D объектов мира
   * @return список 3D объектов мира
   */
  public ObservableList<Node> getMeshes() {
    return this.meshes.getChildren();
  }
  /**
   * Добавляет объект в мир
   * @param node - новый объект
   */
  public void addMesh(Node node) {
    this.meshes.addChild(node);
  }
  /**
   * Удаляет выбранные объект из мира
   */
  public void deleteMesh() {
    if (this.isSetPickedNode()) {
      this.meshes.removeChild(this.pickedNode.get());
      this.pickedNode.set(null);
    }
  }

  /**
   * Возвращает выбранный объект
   * @return - выбранный объект
   */
  public BaseMesh getPickedMesh() {
    return this.pickedNode.get();
  }
  /**
   * Возвращает наблюдаемое свойство выбранного объекта
   * @return - наблюдаемое свойство выбранного объекта
   */
  public SimpleObjectProperty<BaseMesh> getPickedNode() {
    return this.pickedNode;
  }
  /**
   * Устанавливает выбранный объект
   * @param node - выбранный объект
   */
  public void setPickedNode(BaseMesh node) {
    if (this.isSetPickedNode()) this.getPickedMesh().unpick();
    if (node != null) {
      node.pick();
      this.pickedNode.set(node);
    }
  }
  /**
   * Отменяет выбранный объект
   */
  public void delPickedNode() {
    if (this.isSetPickedNode()) this.getPickedMesh().unpick();
    this.pickedNode.set(null);
  }
  /**
   * Проверяет, есть ли выбранный объект
   * @return - результат проверки
   */
  public boolean isSetPickedNode() {
    return this.pickedNode.get() != null;
  }

  /**
   * Создает изображение сцены
   */
  public void makeImg() {
    this.scene.makeImg();
  }
}
