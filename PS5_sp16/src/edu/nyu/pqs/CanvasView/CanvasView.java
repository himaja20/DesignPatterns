package edu.nyu.pqs.CanvasView;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import edu.nyu.pqs.CanvasModel.Listener;
import edu.nyu.pqs.CanvasModel.Model;

public class CanvasView implements Listener{
  JFrame f;
  Canvas c;
  int x=-1, y=-1;
  Model model;

  public CanvasView(final Model model) {
    this.model = new Model();
    model.addListener(this);
    f = new JFrame();
    f.setSize(600, 400);
    c = new Canvas() {
      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      public void paint(Graphics g) {
        // super.paint(g);  
      }
    };
    f.getContentPane().add(c);
    c.addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        if(x==-1){
          x = e.getX();
          y = e.getY();
        }
        int diffx =  Math.abs(x - e.getX());
        int diffy =  Math.abs(y - e.getY());
        System.out.println("diffx:"+diffx+"\t"+"diffy:"+diffy);
        int speed = (int) Math.sqrt((diffx + diffy));
        if(speed>1){
          paint(x,y,20-speed*2, 20-speed*2);
          model.somethingDrawn(x,y,20-speed*2, 20-speed*2);
          //c.getGraphics().fillOval(x, y, 20-speed*2, 20-speed*2);
        }else {
          paint(x,y,20, 20);
          model.somethingDrawn(x, y, 20, 20);
//          c.getGraphics().fillOval(x, y, 20, 20);
        }
        System.out.print("Speed:"+speed + "\t");
        System.out.println("x:"+e.getX());
        x = e.getX();
        y = e.getY();
      }
    });
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

  public void paint(int x, int y, int w, int h){
    c.getGraphics().fillOval(x, y, w, h);
  }
  @Override
  public void startCanvas() {

  }

  public void showDrawing() {
    // TODO Auto-generated method stub

  }
}
