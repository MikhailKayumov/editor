package com.mk.editor.handlers;

import javafx.scene.input.MouseEvent;

public class ViewportHandlers {
  static double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;

  public static void mousePressed(MouseEvent event) {
    System.out.println("setOnMousePressed");
//    mousePosX = event.getSceneX();
//    mousePosY = event.getSceneY();
//    mouseOldX = event.getSceneX();
//    mouseOldY = event.getSceneY();
  }

//  public static EventHandler<? super MouseEvent> mousePressed() {
//    return (MouseEvent event) -> {
//
//    };
//  }
}
