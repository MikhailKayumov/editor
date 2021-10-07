package com.mk.editor.utils;

import java.util.regex.Pattern;

public class Formater {
  public static String formatDoubleString(String str) {
    Boolean isNegative = str.startsWith("-");

    String withoutLetters = str.replaceAll("[^0-9.]", "");
    String[] parts = withoutLetters.split(Pattern.quote("."));

    String result = "0.000";
    if (parts.length == 1) result = parts[0] + ".000";
    else if (parts.length > 1) result = parts[0] + "." + parts[1];

    return isNegative ? "-" + result : result;
  }
}
