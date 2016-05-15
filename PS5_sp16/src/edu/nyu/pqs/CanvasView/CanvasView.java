package edu.nyu.pqs.CanvasView;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nyu.pqs.CanvasModel.Listener;
import edu.nyu.pqs.CanvasModel.Model;

/**
 * Canvas View implements front end of the Canvas. It has the implementation 
 * of the GUI. 
 * 
 * GUI has the following -
 * 1. A Canvas where the user can draw by dragging the mouse
 * 2. Three Color choice Buttons - Red / blue / Green
 * 3. Two Thickness control Buttons - + / -
 * 4. A display to show the current thickness of the brush
 * 
 * @author himaja
 *
 */
public class CanvasView implements Listener{
  private final JFrame frame;
  private final Canvas canvas;
  private final Model model;
  private JButton red;
  private JButton blue;
  private JButton green;
  private JButton incThickness;
  private JButton decThickness;
  private JLabel thicknessDisplay; 
  
  public CanvasView(final Model model) {
    this.model = model;
    model.addListener(this);

    this.frame = new JFrame("PQS Canvas");
    this.red = new JButton("Red");
    this.blue = new JButton("Blue");
    this.green = new JButton("Green");

    this.incThickness = new JButton("+");
    this.decThickness = new JButton("-");
    this.thicknessDisplay = new JLabel(Integer.toString(model.getThickness()));

    GridLayout colorButtons = new GridLayout(3, 1, 4, 4);
    GridLayout thicknessButtons = new GridLayout(1,2,2,2);

    JPanel colourControlPanel = new JPanel();
    colourControlPanel.setLayout(colorButtons);
    colourControlPanel.add(red,BorderLayout.EAST);
    colourControlPanel.add(blue,BorderLayout.CENTER);
    colourControlPanel.add(green,BorderLayout.WEST);

    JPanel thicknessControlPanel = new JPanel();
    thicknessControlPanel.setLayout(thicknessButtons);
    thicknessControlPanel.add(decThickness, BorderLayout.WEST);
    thicknessControlPanel.add(thicknessDisplay, BorderLayout.CENTER);
    thicknessControlPanel.add(incThickness, BorderLayout.EAST);

    JPanel paintControlPanel = new JPanel();
    paintControlPanel.add(colourControlPanel);
    paintControlPanel.add(thicknessControlPanel);

    frame.getContentPane().add(paintControlPanel, BorderLayout.EAST);
    canvas = new Canvas();
    frame.getContentPane().add(canvas, BorderLayout.CENTER);
    frame.setSize(1000, 400);
    frame.setVisible(true);

    canvas.addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseMoved(MouseEvent e) {
      }

      /**
       * Model is communicated that something is drawn
       */
      @Override
      public void mouseDragged(MouseEvent e) {
        model.somethingDrawn(e.getX(), e.getY());
      }
    });

    red.addActionListener(new ActionListener() {
      
      /**
       * Model is communicated that the red color is chosen
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        model.colorButtonClicked(Color.RED);
      }
    });
    
    blue.addActionListener(new ActionListener() {
      
      /**
       * Model is communicated that the blue color is chosen
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        model.colorButtonClicked(Color.BLUE); 
      }
    });
    
    green.addActionListener(new ActionListener() {
      
      /**
       * Model is communicated that the Green color is chosen
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        model.colorButtonClicked(Color.GREEN);        
      }
    });
    
    incThickness.addActionListener(new ActionListener() {
      
      /**
       * Model is requested for an increase in thickness
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        model.incThickness();
      }
    });
    
    decThickness.addActionListener(new ActionListener() {
      
      /**
       * Model is requested for a decrease in thickness
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        model.decThickness();        
      }
    });
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * Implementation of the paint method in Listener Interface
   */
  @Override
  public void paint(int x, int y, int width, int height, Color color) {
    Graphics g = canvas.getGraphics();
    g.setColor(color);
    g.fillOval(x, y, width, height);
  }

  /**
   * Implementation of the showThickness method in Interface
   */
  @Override
  public void showThickness(int thickness) {
    thicknessDisplay.setText(Integer.toString(thickness));    
  }
}
