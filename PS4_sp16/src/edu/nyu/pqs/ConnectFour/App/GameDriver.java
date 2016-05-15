package edu.nyu.pqs.ConnectFour.App;

/**
 * Main function
 * 
 * @author himaja
 *
 */
public class GameDriver {

  public static void main(String[] args) {
    Game game = Game.getInstance(6, 7);
    game.start();
  }
}
