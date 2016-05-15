package edu.nyu.pqs.CanvasModel;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class ModelTests {

  Model model;
  @Before
  public void setUp() throws Exception {
    model = new Model();
  }

  @Test
  public void increaseThicknessTest(){
    int thickness = model.getThickness();
    int expected = thickness + 1;
    model.incThickness();
    assertEquals(expected,model.getThickness());
  }
  
  @Test
  public void decreaseThicknessTest(){
    int thickness = model.getThickness();
    model.decThickness();
    assertEquals(thickness- 1,model.getThickness());
  }
  
  @Test
  public void ColorButtonClickedTest(){
    Color expected = Color.BLUE;
    model.ButtonClicked(Color.BLUE);
    assertEquals(expected,model.getColor());
  }
  
  @Test
  public void defaultColorCheck(){
    Color expected = Color.RED;
    assertEquals(expected,model.getColor());
  }
  
  @Test
  public void defaultThicknessCheck(){
    int expected = 12;
    int actual = model.getThickness();
    assertEquals(expected,actual);
  }
  
  @Test
  public void decreaseThicknessLessThanZeroTest(){
    for(int i = model.getThickness(); i >= -2; i--){
      model.decThickness();
    }
    assertTrue(model.getThickness() == 0);
  }
  
  @Test
  public void increaseThicknessGreaterThanTwenty(){
    for(int i = model.getThickness(); i <= 22; i++){
      model.incThickness();
    }
    assertTrue(model.getThickness() == 20);
  }

}
