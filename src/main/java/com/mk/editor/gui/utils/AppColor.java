package com.mk.editor.gui.utils;

import javafx.scene.paint.Color;

public final class AppColor {
  public static Color BGPrimary = Color.rgb(44, 44, 44, 1);
  public static Color BGSecondary = Color.rgb(37, 37, 37, 1);
  public static Color BGScene = Color.rgb(57, 57, 57, 1);
  public static Color BGTransparent = Color.rgb(0, 0, 0, 0);

  public static Color BGButton = Color.rgb(89, 89, 89, 1);
  public static Color BGButtonHover = Color.rgb(100, 100, 100, 1);
  public static Color BGButtonActive = Color.rgb(85, 85, 85, 1);

  public static Color FontPrimary = Color.rgb(250, 250, 250);

  public static Color BorderColor = Color.rgb(144, 144, 144, 1);
  public static Color GridColor = Color.rgb(104, 104, 104, 0.5);

  public static String getCSS(Color color) {
    int red = (int)(color.getRed() * 255);
    int green = (int)(color.getGreen() * 255);
    int blue = (int)(color.getBlue() * 255);
    double alpha = color.getOpacity();

    return "rgba(" + red + ", " + green + ", " + blue + ", " + alpha + ")";
  }
  private AppColor() {}
}
