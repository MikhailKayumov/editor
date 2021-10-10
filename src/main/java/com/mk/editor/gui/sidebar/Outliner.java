package com.mk.editor.gui.sidebar;

import com.mk.editor.entities.World3D;
import com.mk.editor.shapes.BaseMesh;
import com.mk.editor.utils.AppColor;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import java.util.concurrent.atomic.AtomicInteger;

// Класс создает объект панели для списка 3D мешей
public final class Outliner {
  private final World3D world; // мир
  private final VBox root = new VBox(); // контейнер панели

  /**
   * Конструктор
   * @param world - 3D сцена всего приложения
   */
  public Outliner(World3D world) {
    this.world = world;
    this.root.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.DELETE) this.world.deleteMesh();
    });
  }
  /**
   * Возвращает контейнер панели
   * @return контейнер панели
   */
  public VBox getRoot() {
    return this.root;
  }
  /**
   * Создает панель outliner
   */
  public void build() {
    this.styleOutliner();
    Label title = this.buildOutlinerTitle();
    ScrollPane list = this.buildMeshList();
    VBox.setVgrow(list, Priority.ALWAYS);
    this.root.getChildren().addAll(title, list);
  }

  /**
   * Стилизует панель outliner
   */
  private void styleOutliner() {
    this.root.setMinHeight(240);
    this.root.setPrefHeight(240);
    this.root.setMaxHeight(240);
    this.root.setBorder(new Border(
      new BorderStroke(
        AppColor.BorderColor,
        new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.MITER, StrokeLineCap.BUTT, 10, 0, null),
        new CornerRadii(0),
        new BorderWidths(0, 0, 2, 0)
      )
    ));
  }
  /**
   * Создает заголовок для панели outliner
   * @return заголовок
   */
  private Label buildOutlinerTitle() {
    Label title = new Label("Outliner");
    title.setFont(new Font(16));
    title.setLayoutX(2);
    title.setMaxWidth(Double.MAX_VALUE);
    title.setPadding(new Insets(5, 10, 5, 10));
    title.setTextFill(AppColor.FontPrimary);
    title.setBorder(new Border(
      new BorderStroke(
        AppColor.BorderColor,
        new BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.MITER, StrokeLineCap.BUTT, 10, 0, null),
        new CornerRadii(0),
        new BorderWidths(0, 0, 1, 0)
      )
    ));
    return title;
  }
  /**
   * Создает список 3D объектов, присутствующих на сцене
   * @return панель со списком 3D объектов
   */
  private ScrollPane buildMeshList() {
    VBox list = new VBox();
    list.setBackground(
      new Background(new BackgroundFill(
        AppColor.BGPrimary,
        new CornerRadii(0),
        new Insets(0)
      ))
    );
    this.fillList(list);
    this.world.getMeshes().addListener((ListChangeListener<? super Node>) event -> this.fillList(list));
    return this.buildListScrollPane(list);
  }
  /**
   * Наполняет список
   * @param list - бокс для списка
   */
  private void fillList(VBox list) {
    list.getChildren().clear();
    AtomicInteger i = new AtomicInteger(0);
    this.world.getMeshes().forEach((mesh) -> {
      Label label = new Label(((BaseMesh) mesh).getName());
      label.setPadding(new Insets(4, 18, 4, 18));
      label.setMaxWidth(Double.MAX_VALUE);
      label.setTextFill(AppColor.FontPrimary);
      label.setFont(new Font(14));
      label.setBackground(
        new Background(new BackgroundFill(
          i.getAndIncrement() % 2 == 0 ? AppColor.BGSecondary : AppColor.BGPrimary,
          new CornerRadii(0),
          new Insets(0)
        ))
      );
      label.setOnMouseClicked(e -> this.world.setPickedNode((BaseMesh)mesh));
      list.getChildren().add(label);
    });
  }
  /**
   * Создает панель со скролом и заполняет ее списком объектов с мира
   * @param content - список объектов
   * @return панель со скролом
   */
  private ScrollPane buildListScrollPane(VBox content) {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.fitToHeightProperty().set(true);
    scrollPane.fitToWidthProperty().set(true);
    scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
    scrollPane.setPadding(new Insets(1, 1, 1,3));
    scrollPane.setBackground(
      new Background(new BackgroundFill(
        AppColor.BGTransparent,
        new CornerRadii(0),
        new Insets(0)
      ))
    );
    scrollPane.setContent(content);
    return scrollPane;
  }
}
