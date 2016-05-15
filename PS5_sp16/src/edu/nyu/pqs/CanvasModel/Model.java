package edu.nyu.pqs.CanvasModel;

import java.util.ArrayList;
import java.util.List;

public class Model {
  private List<Listener> listeners = new ArrayList<Listener>();
  private int curX;
  private int curY;
  
  public Model(){
    setCurX(-1);
    setCurY(-1);
  }
  
  public void startCanvas(){
    fireStartCanvas();
  }
  
  public void somethingDrawn(int x, int y){
    if(getCurX() == -1){
      setCurX(x);
      setCurY(y);
    }
    fireEventPaint(x, y, 20, 20);
    setCurX(x);
    setCurY(y);
  }
  
  public void fireEventPaint(int x, int y, int w, int h){
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

  public int getCurX() {
    return curX;
  }

  private void setCurX(int x) {
    this.curX = x;
  }

  public int getCurY() {
    return curY;
  }

  private void setCurY(int y) {
    this.curY = y;
  }
  
  
}
