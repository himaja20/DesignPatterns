package edu.nyu.pqs.CanvasApp;

import edu.nyu.pqs.CanvasModel.Model;
import edu.nyu.pqs.CanvasView.CanvasView;

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
    model.startCanvas();
  }

}
