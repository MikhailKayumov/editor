package com.mk.editor.gui;

import com.mk.editor.gui.utils.AppColor;
import com.mk.editor.gui.utils.BorderPosition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public abstract class MainRegion {
  private int columnId;
  private int rowId;
  private int colspan;
  private int rowspan;

  protected ArrayList<Node> content = new ArrayList<>();
  protected final Pane root = new Pane();

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
  public MainRegion(
    Color color,
    int columnId,
    int rowId
  ) {
    this.init(columnId, rowId, 1, 1)
      .setBackground(color);
  }

  public int getColumnId() {
    return this.columnId;
  }
  public int getRowId() {
    return rowId;
  }
  public int getColspan() {
    return colspan;
  }
  public int getRowspan() {
    return rowspan;
  }
  public Pane getRoot() {
    return this.root;
  }

  abstract public void render();

  protected void addContent(Node... nodes) {
    for (Node node: nodes) {
      this.content.add(node);
    }
  }

  private MainRegion init(int columnId, int rowId, int colspan, int rowspan) {
    this.columnId = columnId;
    this.rowId = rowId;
    this.colspan = colspan;
    this.rowspan = rowspan;
    return this;
  }
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
  private MainRegion setBackground(Color color) {
    this.root.setBackground(new Background(new BackgroundFill(
      color,
      new CornerRadii(0),
      new Insets(0)
    )));
    return this;
  }
}
