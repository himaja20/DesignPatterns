package edu.nyu.pqs.CanvasModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Model {
  private List<Listener> listeners = new ArrayList<Listener>();
  private Color color;
  /**
   * @return the color
   */
  public Color getColor() {
    return color;
  }

  private int thickness;

  /**
   * @return the thickness
   */
  public int getThickness() {
    return thickness;
  }

  public Model(){
    color = Color.RED;
    this.thickness = 12;
  }

  private static class ModelLazyHolder {
    private static final Model INSTANCE = new Model();
  }

  public static Model getInstance() {
    return ModelLazyHolder.INSTANCE;
  }

  public void somethingDrawn(int x, int y){
    fireEventPaint(x, y);
  }

  private void fireEventPaint(int x, int y){
    for(Listener listener : listeners){
      listener.paint(x,y,thickness,thickness,color);
    }
  }

  public void addListener(Listener listener){
    listeners.add(listener);
  }

  public void removeListener(Listener listener){
    listeners.remove(listener);
  }

  public void ButtonClicked(Color color) {
    this.color = color;
  }

  public void incThickness() {
    if (thickness < 20){
      this.thickness++;
    }
    fireShowThicknessEvent();
  }

  private void fireShowThicknessEvent() {
    for(Listener listener : listeners){
      listener.showThickness(thickness);
    }
  }

  public void decThickness() {
    if (!(thickness <= 0)){
      this.thickness--;
    }

    fireShowThicknessEvent();
  }


}
