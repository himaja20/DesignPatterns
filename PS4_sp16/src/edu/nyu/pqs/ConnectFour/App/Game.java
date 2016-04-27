package edu.nyu.pqs.ConnectFour.App;

import edu.nyu.pqs.ConnectFour.Model.Model;
import edu.nyu.pqs.ConnectFour.View.GameBoardView;
import edu.nyu.pqs.ConnectFour.View.GameView;

/**
 * Game Class is the parent class which creates and maintains
 * model and view objects.
 * 
 * Game class is a Singleton class, as at any point of time, 
 * there should be only one instance of game running.
 * 
 * @author himaja
 */

public class Game {

  private GameBoardView gui;
  private Model model;
  
  private static Game uniqueInstance;

  private Game(int rows, int cols) {
    this.model = new Model(rows, cols);
    gui = new GameBoardView(model, true);
  }
  
  public static Game getInstance(int rows, int cols){
    if(uniqueInstance == null){
      return new Game(rows,cols);
    }
    return uniqueInstance;
  }

  /**
   * Displays the Intial start menu for the user
   * to select the type of Game - 
   * PlayWithComputer / two-player
   * 
   * Begins the game based on the selection.
   */
  public void start() {
    gui.displayGameOptions();
    beginGame();
  }

  /**
   * Creates views for players
   */
  public void beginGame() {
    new GameView(model, true);
    new GameView(model, false);
  }
}
