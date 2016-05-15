package edu.nyu.pqs.CanvasModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class communicates with the other views to show the 
 * painting drawn in one view.
 * 
 * Model maintains the thickness and color of the brush
 * selected by one view and asks the other views to show
 * the paint with these set features.
 * 
 * Available colors - Red , Blue and Green
 * Thickness Range - 0 to 20
 * @author himaja
 *
 */
public class Model {
  private List<Listener> listeners = new ArrayList<Listener>();
  private Color color;
  private int thickness;

  public Model(){
    color = Color.RED;
    this.thickness = 12;
  }

  /**
   * @return the color
   */
  public Color getColor() {
    return color;
  }

  /**
   * @return the thickness
   */
  public int getThickness() {
    return thickness;
  }

  /**
   * Adds a listener to the list of listeners
   * @param listener
   */
  public void addListener(Listener listener){
    listeners.add(listener);
  }

  /**
   * Removes the listener from the list of listener
   * @param listener
   */
  public void removeListener(Listener listener){
    listeners.remove(listener);
  }

  /**
   * Model is communicated that something is drawn on the 
   * canvase at co-ordinate x and co-ordinate y. Model fires the 
   * paint Event for all listener with set features and received
   * coordinates
   * @param x
   * @param y
   */
  public void somethingDrawn(int x, int y){
    fireEventPaint(x, y);
  }


  /**
   * One of the color buttons - Red / blue / green from the view
   * is clicked by the user to change the color of the brush.
   * 
   * Model changes the color of the brush
   * @param color
   */
  public void colorButtonClicked(Color color) {
    this.color = color;
  }

  /**
   * Model receives the request to increase the thickness of the 
   * brush via this function. Model checks if the current thickness
   * is less than 20, before incrementing thickness,as the allowed 
   * thickness range is between 0 - 20.  
   */
  public void incThickness() {
    if (thickness < 20){
      thickness++;
    }
    fireShowThicknessEvent();
  }

  /**
   * Model receives the request to decrease the thickness of the 
   * brush via this function. Model checks if the current thickness
   * is equal to 0, before decrementing thickness,as the allowed 
   * thickness range is between 0 - 20. 
   */
  public void decThickness() {
    if (!(thickness <= 0)){
      thickness--;
    }
    fireShowThicknessEvent();
  }

  /**
   * Fires the paint event on all listeners
   * @param x
   * @param y
   */
  private void fireEventPaint(int x, int y){
    for(Listener listener : listeners){
      listener.paint(x,y,thickness,thickness,color);
    }
  }

  /**
   * Fires the show thickness event on all listeners to 
   * display current thickness on the GUI
   */
  private void fireShowThicknessEvent() {
    for(Listener listener : listeners){
      listener.showThickness(thickness);
    }
  }

}
