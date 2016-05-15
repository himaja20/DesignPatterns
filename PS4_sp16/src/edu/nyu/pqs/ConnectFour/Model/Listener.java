package edu.nyu.pqs.ConnectFour.Model;

import java.awt.Color;

/**
 * This is the interface that each view should implement to perform the
 * operations sepecified by model.
 * 
 * Model notifies the listeners in the following scenarios -
 * 1. When a selected move is valid, model tells the view 
 * to show that move 
 * 2. When a selected move resulted in a win, model tells 
 * the view to end the game in win status 
 * 3. When a selected move resulted in a loss, model 
 * tells the view to end the game in
 * draw status 
 * 4. To show the game board 5. To show any notification message
 * 
 * @author himaja
 *
 */

public interface Listener {

  /**
   * to notify the victory of one of the players and 
   * end the game after that.
   * 
   * @param player
   */
  void endGameInWin(Player player);

  /**
   * notifies a draw between the players and ends the 
   * game after that
   */
  void endGameInDraw();

  /**
   * shows the move just made by one of the players
   * 
   * @param color
   * @param column
   */
  void showMadeMove(int row, int column, Color color);

  /**
   * notify listeners of a messgage
   */
  void showMessage(String message);

  /**
   * makes the views visible to the players
   */
  void showGameBoard();

}
