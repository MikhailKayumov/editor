package com.mk.editor.utils;

import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

import java.util.Locale;

// Конвертер между полями форма со строками
// и свойствами 3D объектов или камеры
public class TextFieldConverter {
  /**
   * Создает конвертер. Функции обратного вызова служат для преобразования
   * числовых данных свойств в удобочитаемый формат или наоборот
   * @param toCallback - преобразует число для поля ввода
   * @param fromCallback - преобразует число для свойства
   * @return конвертер
   */
  public static NumberStringConverter buildConverter(
    Callback<Double, Double> toCallback,
    Callback<Double, Double> fromCallback
  ) {
    return new NumberStringConverter() {
      private Callback<Double, Double> toCallback;
      private Callback<Double, Double> fromCallback;

      /**
       * Преобразует число в строку
       * @param value - свойство камеры или объекта
       * @return строка для поля ввода
       */
      @Override
      public String toString(Number value) {
        double formattedValue = this.toCallback.call((double)value);
        return String.format(new Locale("en-US"), "%.3f", formattedValue);
      }

      /**
       * Преобразует число из строки
       * @param value - свойство камеры или объекта
       * @return число для установки в свойство
       */
      @Override
      public Double fromString(String value) {
        return this.fromCallback.call(Double.parseDouble(Formater.formatDoubleString(value)));
      }

      /**
       * Устанавливает функции обратного вызова
       * @param toCallback - см. выше
       * @param fromCallback - см. выше
       * @return возвращает ссылку на себя
       */
      public NumberStringConverter setCallbacks(Callback<Double, Double> toCallback, Callback<Double, Double> fromCallback) {
        this.toCallback = toCallback;
        this.fromCallback = fromCallback;
        return this;
      }
    }.setCallbacks(toCallback, fromCallback);
  }
}
