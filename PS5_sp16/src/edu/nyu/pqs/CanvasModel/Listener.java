package edu.nyu.pqs.CanvasModel;

/**
 * Listener interface contains methods through which model
 * would communicate to all the listeners / views.
 * 
 * All the listeners / views implement these functions.
 * @author himaja
 *
 */
public interface Listener {

  /**
   * This methods is implemented to show the drawing in the view
   * when asked by the model.
   * 
   * @param x - X cordinate on the canvas to define the point 
   * at which drawing has to made
   * @param y - Y cordinate on the canvas to define the point 
   * at which drawing has to made
   * @param width - width of the point 
   * @param height - height of the point
   * @param color - Color of the brush
   */
  public void paint(int x, int y, int width, int height, java.awt.Color color);

  /**
   * This method is implemented to show the current thickness of the 
   * brush in the GUI
   * @param thickness
   */
  public void showThickness(int thickness);
}
