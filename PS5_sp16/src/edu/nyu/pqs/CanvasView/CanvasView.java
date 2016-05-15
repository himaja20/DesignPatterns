package edu.nyu.pqs.CanvasView;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import edu.nyu.pqs.CanvasModel.Listener;
import edu.nyu.pqs.CanvasModel.Model;

public class CanvasView implements Listener{
  private final JFrame frame;
  private final Canvas canvas;
  private Model model;

  public CanvasView(final Model model) {
    this.model = model;
    model.addListener(this);

    frame = new JFrame();
    frame.setSize(600, 400);
    canvas = new Canvas();
    frame.getContentPane().add(canvas);

    canvas.addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseMoved(MouseEvent e) {
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        model.somethingDrawn(e.getX(), e.getY());
      }
    });

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void paint(int x, int y, int w, int h){
    canvas.getGraphics().fillOval(x, y, w, h);
  }
}
