package edu.nyu.pqs.CanvasModel;

import java.util.ArrayList;
import java.util.List;

public class Model {
  private List<Listener> listeners = new ArrayList<Listener>();
  boolean currentState = false;
  
  public void startCanvas(){
    fireStartCanvas();
  }
  
  public void somethingDrawn(int x, int y, int w, int h){
    for(Listener listener : listeners){
      listener.paint(x,y,w,h);
    }
  }
  
  public void fireStartCanvas(){
    for(Listener listener : listeners){
      listener.startCanvas();
    }
  }
  
  public void addListener(Listener listener){
    listeners.add(listener);
  }
  
  public void removeListener(Listener listener){
    listeners.remove(listener);
  }
  
  
}
