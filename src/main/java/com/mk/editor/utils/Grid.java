package com.mk.editor.utils;

import com.mk.editor.entities.Object3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;

// Квадратная координатная сетка
public class Grid extends Object3D {
  private final int size; // количество линий
  private final int step; // шаг сетки
  private final int lineLen; // длина одной линии

  /**
   * Конструктор 1
   * @param size - количество линий
   * @param step - шаг сетки
   */
  public Grid(int size, int step) {
    super();

    this.size = size / 2;
    this.step = step;
    this.lineLen = this.size * 2 * this.step;

    this.addChildren(this.buildGrid());
  }
  /**
   * Конструктор 2
   */
  public Grid() {
    this(100, 20);
  }

  /**
   * Строит сетку
   * @return коллекция линий
   */
  private ArrayList<Box> buildGrid() {
    ArrayList<Box> grid = new ArrayList<>();
    for (int i = -this.size; i <= this.size; i++) {
      grid.add(this.buildXLine(i));
      grid.add(this.buildYLine(i));
    }
    return grid;
  }

  /**
   * Строит линию вдоль оси X
   * @param pos - позиция по оси Y
   * @return линия
   */
  private Box buildXLine(int pos) {
    Box line = this.buildLine(this.lineLen, 0.5, pos, AppColor.XAxisColor);
    line.setTranslateY(this.step * pos);
    return line;
  }
  /**
   * Строит линию вдоль оси Y
   * @param pos - позиция по оси X
   * @return линия
   */
  private Box buildYLine(int pos) {
    Box line = this.buildLine(0.5, this.lineLen, pos, AppColor.YAxisColor);
    line.setTranslateX(this.step * pos);
    return line;
  }
  /**
   * Создает линию
   * @param w - ширина по X
   * @param h - длина по Y
   * @param pos - позиция линии
   * @param c - цвет линии
   * @return линия
   */
  private Box buildLine(double w, double h, int pos, Color c) {
    Box line = new Box(w, h, 0.01);
    line.setMaterial(new PhongMaterial(pos == 0 ? c : AppColor.GridColor));
    return line;
  }
}
