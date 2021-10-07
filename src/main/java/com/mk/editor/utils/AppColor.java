package com.mk.editor.utils;

import javafx.scene.paint.Color;

public final class AppColor {
  public static Color BGPrimary = Color.grayRgb(44);
  public static Color BGSecondary = Color.grayRgb(49);
  public static Color BGScene = Color.grayRgb(57);
  public static Color BGTransparent = Color.grayRgb(0, 0);

  public static Color BGButton = Color.grayRgb(89);
  public static Color BGButtonHover = Color.grayRgb(100);
  public static Color BGButtonActive = Color.grayRgb(85);

  public static Color FontPrimary = Color.grayRgb(250);

  public static Color BorderColor = Color.grayRgb(144);
  public static Color BorderColorSecondary = Color.grayRgb(98);

  public static Color XAxisColor = Color.rgb(244, 50, 80);
  public static Color YAxisColor = Color.rgb(111, 164, 19);
  public static Color ZAxisColor = Color.rgb(43, 129, 222);
  public static Color GridColor = Color.grayRgb(49);

  public static String getCSS(Color color) {
    int red = (int)(color.getRed() * 255);
    int green = (int)(color.getGreen() * 255);
    int blue = (int)(color.getBlue() * 255);
    double alpha = color.getOpacity();

    return "rgba(" + red + ", " + green + ", " + blue + ", " + alpha + ")";
  }
  private AppColor() {}
}
