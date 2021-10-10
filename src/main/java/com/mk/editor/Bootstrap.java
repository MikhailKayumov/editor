package com.mk.editor;

// Класс обертка (необходим для сборщика Maven)
public final class Bootstrap {
  private Bootstrap() {}
  /**
   * Основная функция запуска приложения
   * @param args - аргументы из командной строки (терминала)
   */
  public static void main(String[] args) {
    App.run();
  }
}
