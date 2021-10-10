package com.mk.editor.gui;

import com.mk.editor.utils.AppColor;
import com.mk.editor.utils.BorderPosition;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

import java.io.IOException;

// Базовый класс для основных панелей приложения
public abstract class MainRegion {
  private int columnId; // номер колонны
  private int rowId; // номер строки
  private int colspan; // количество занимаемых колонн
  private int rowspan; // количество занимаемых строк

  protected Group content = new Group(); // контент
  protected Pane root = new Pane(); // корневой узел панели

  /**
   * Конструктор 1
   * @param pos - позиция отображения границы
   * @param color - цвет заливки
   * @param columnId - номер колонны
   * @param rowId - номер строки
   * @param colspan - количество занимаемых колонн
   * @param rowspan - количество занимаемых строк
   */
  public MainRegion(
    BorderPosition pos,
    Color color,
    int columnId,
    int rowId,
    int colspan,
    int rowspan
  ) {
    this.init(columnId, rowId, colspan, rowspan)
      .setBorder(pos)
      .setBackground(color);
  }
  /**
   * Конструктор 2
   * @param pos - позиция отображения границы
   * @param color - цвет заливки
   * @param columnId - номер колонны
   * @param rowId - номер строки
   */
  public MainRegion(
    BorderPosition pos,
    Color color,
    int columnId,
    int rowId
  ) {
    this.init(columnId, rowId, 1, 1)
      .setBackground(color)
      .setBorder(pos);
  }
  /**
   * Конструктор 3
   * @param color - цвет заливки
   * @param columnId - номер колонны
   * @param rowId - номер строки
   */
  public MainRegion(
    Color color,
    int columnId,
    int rowId
  ) {
    this.init(columnId, rowId, 1, 1)
      .setBackground(color);
  }

  /**
   * Возвращает номер колонны
   * @return номер колонны
   */
  public int getColumnId() {
    return this.columnId;
  }
  /**
   * Возвращает номер строки
   * @return номер строки
   */
  public int getRowId() {
    return rowId;
  }
  /**
   * Возвращает количество занимаемых колонн
   * @return количество занимаемых колонн
   */
  public int getColspan() {
    return colspan;
  }
  /**
   * Возвращает количество занимаемых строк
   * @return количество занимаемых строк
   */
  public int getRowspan() {
    return rowspan;
  }
  /**
   * Возвращает корневой узел панели
   * @return корневой узел панели
   */
  public Pane getRoot() {
    return this.root;
  }

  /**
   * Отрисовывает контент панели
   * @throws IOException возможная ошибка ввода\вывода
   */
  abstract public void render() throws IOException;

  /**
   * Добавляет узел в контент
   * @param node - узел
   */
  protected void addContent(Node node) {
    this.content.getChildren().add(node);
  }

  /**
   * Инициализиурет панель
   * @param columnId - номер колонны
   * @param rowId - номер строки
   * @param colspan - количество занимаемых колонн
   * @param rowspan - количество занимаемых строк
   * @return ссылка на себя
   */
  private MainRegion init(int columnId, int rowId, int colspan, int rowspan) {
    this.columnId = columnId;
    this.rowId = rowId;
    this.colspan = colspan;
    this.rowspan = rowspan;
    return this;
  }
  /**
   * Устанавливает границу
   * @param pos - позиция границы
   * @return ссылка на себя
   */
  private MainRegion setBorder(BorderPosition pos) {
    int bWidth = 2;
    BorderWidths bWidths = new BorderWidths(bWidth);
    switch (pos) {
      case TOP:
        bWidths = new BorderWidths(bWidth, 0, 0, 0);
        break;
      case RIGHT:
        bWidths = new BorderWidths(0, bWidth, 0, 0);
        break;
      case LEFT:
        bWidths = new BorderWidths(0, 0, 0, bWidth);
        break;
      case BOTTOM:
        bWidths = new BorderWidths(0, 0, bWidth, 0);
        break;
    }

    Border border = new Border(
      new BorderStroke(
        AppColor.BorderColor,
        new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.MITER, StrokeLineCap.BUTT, 10, 0, null),
        new CornerRadii(0),
        bWidths
      )
    );

    this.root.setBorder(border);
    return this;
  }
  /**
   * Устанавливает цвет заливки
   * @param color - цвет заливки
   * @return ссылка на себя
   */
  private MainRegion setBackground(Color color) {
    this.root.setBackground(new Background(new BackgroundFill(
      color,
      new CornerRadii(0),
      new Insets(0)
    )));
    return this;
  }
}
