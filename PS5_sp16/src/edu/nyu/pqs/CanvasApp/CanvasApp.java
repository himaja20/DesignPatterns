package edu.nyu.pqs.CanvasApp;

import edu.nyu.pqs.CanvasModel.Model;
import edu.nyu.pqs.CanvasView.CanvasView;

/**
 * Camvas App is an painting app which provides the user to 
 * paint on the canvas by dragging the  mouse.
 * 
 * User can choose the color of the paint brush. Available
 * colors are Red, Blue and Green as of now.
 * 
 * User can also change the thickness of the brush between a 
 * range of 0 and 20 while painting.
 * 
 * Happy Painting!
 * @author himaja
 *
 */
public class CanvasApp {

  public static void main(String[] args) {
    new CanvasApp().startCanvas();
  }
  
  private void startCanvas() {
    Model model = new Model();
    new CanvasView(model);
    new CanvasView(model);
    new CanvasView(model);
    new CanvasView(model);
  }

}
