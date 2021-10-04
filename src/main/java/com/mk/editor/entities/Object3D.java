package com.mk.editor.entities;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Object3D extends Group {
  public enum RotateOrder { XYZ, XZY, YXZ, YZX, ZXY, ZYX }

  protected final Translate t = new Translate(0.0, 0.0, 0.0);
  // protected final Translate p = new Translate(0.0, 0.0, 0.0);
  // protected final Translate ip = new Translate(0.0, 0.0, 0.0);

  protected final Rotate rx = new Rotate(0, Rotate.X_AXIS);
  protected final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
  protected final Rotate rz = new Rotate(0, Rotate.Z_AXIS);

  protected final Scale s = new Scale();

  public Object3D() {
    this(RotateOrder.XZY);
  }
  public Object3D(RotateOrder rotateOrder) {
    super();
    // choose the order of rotations based on the rotateOrder
    switch (rotateOrder) {
      case XYZ:
        this.getTransforms().addAll(t, rz, ry, rx, s);
        break;
      case XZY:
        this.getTransforms().addAll(t, ry, rz, rx, s);
        break;
      case YXZ:
        this.getTransforms().addAll(t, rz, rx, ry, s);
        break;
      case YZX:
        this.getTransforms().addAll(t, rx, rz, ry, s); // For Camera
        break;
      case ZXY:
        this.getTransforms().addAll(t, ry, rx, rz, s);
        break;
      case ZYX:
        this.getTransforms().addAll(t, rx, ry, rz, s);
        break;
    }
  }

  // операции перемещения
  public double getTx() {
    return this.t.getX();
  }
  public void setTx(double x) {
    this.t.setX(x);
  }
  public void addTx(double x) {
    this.setTx(this.getTx() + x);
  }

  public double getTy() {
    return this.t.getY();
  }
  public void setTy(double y) {
    this.t.setY(y);
  }
  public void addTy(double y) {
    this.setTy(this.getTy() + y);
  }

  public double getTz() {
    return this.t.getZ();
  }
  public void setTz(double z) {
    this.t.setZ(z);
  }
  public void addTz(double y) {
    this.setTz(this.getTz() + y);
  }

  // операции вращения
  public double getRx() {
    return this.rx.getAngle();
  }
  public void setRx(double x) {
    this.rx.setAngle(x % 360);
  }
  public void addRx(double x) {
    this.setRx(this.getRx() + x);
  }

  public double getRy() {
    return this.ry.getAngle();
  }
  public void setRy(double y) {
    this.ry.setAngle(y % 360);
  }
  public void addRy(double y) {
    this.setRy(this.getRy() + y);
  }

  public double getRz() {
    return this.rz.getAngle();
  }
  public void setRz(double z) {
    this.rz.setAngle(z % 360);
  }
  public void addRz(double z) {
    this.setRz(this.getRz() + z);
  }

  // операции масштабирования
  public void setSx(double x) {
    this.s.setX(x);
  }
  public void setSy(double y) {
    this.s.setY(y);
  }
  public void setSz(double z) {
    this.s.setZ(z);
  }

  // public void setPivot(double x, double y, double z) {
  //   this.p.setX(x);
  //   this.p.setY(y);
  //   this.p.setZ(z);
  //   this.ip.setX(-x);
  //   this.ip.setY(-y);
  //   this.ip.setZ(-z);
  // }
  // public void addPivot(double x, double y, double z) {
  //   this.p.setX(this.p.getX() + x);
  //   this.p.setY(this.p.getY() + y);
  //   this.p.setZ(this.p.getZ() + z);
  //   this.ip.setX(this.ip.getX() - x);
  //   this.ip.setY(this.ip.getY() - y);
  //   this.ip.setZ(this.ip.getZ() - z);
  // }

  public void reset() {
    // translation
    this.t.setX(0.0);
    this.t.setY(0.0);
    this.t.setZ(0.0);

    // pivot
    // this.p.setX(0.0);
    // this.p.setY(0.0);
    // this.p.setZ(0.0);

    // ?
    // this.ip.setX(0.0);
    // this.ip.setY(0.0);
    // this.ip.setZ(0.0);

    // scale
    this.s.setX(1.0);
    this.s.setY(1.0);
    this.s.setZ(1.0);
  }
  public void resetWithR() {
    this.reset();
    this.rx.setAngle(0.0);
    this.ry.setAngle(0.0);
    this.rz.setAngle(0.0);
  }

  public void debug() {
    System.out.println("t = (" +
      t.getX() + ", " +
      t.getY() + ", " +
      t.getZ() + ")  " +
      "r = (" +
      rx.getAngle() + ", " +
      ry.getAngle() + ", " +
      rz.getAngle() + ")  " +
      "s = (" +
      s.getX() + ", " +
      s.getY() + ", " +
      s.getZ() + ")  "/* +
      "p = (" +
      p.getX() + ", " +
      p.getY() + ", " +
      p.getZ() + ")  " +
      "ip = (" +
      ip.getX() + ", " +
      ip.getY() + ", " +
      ip.getZ() + ")"*/);
  }
}
