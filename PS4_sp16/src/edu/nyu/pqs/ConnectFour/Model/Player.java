package edu.nyu.pqs.ConnectFour.Model;

import java.awt.Color;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;

/**
 * Player interface has the functions that each type of 
 * player has to implement. This interface forces each player 
 * to have the following attributes -
 * 
 * 1. Coin - eg: player1/player2/empty 2. Color - eg: Red/Blue 
 * 3. PlayerType - Human/AI 4. PlayerName - Name of the player.
 */
public interface Player {

  /**
   * This is a function that tells the user to make his next move. 
   * AI and Human Players can have different behaviour for this 
   * functionality
   * 
   * @param gameBoard
   * @return
   */
  public int makeNextMove(GameBoard gameBoard);

  /**
   * forces the implementing class to have a coin attribute. 
   * Returns the attribue CoinType - Player1/Player2/Empty
   * 
   * @return
   */
  public CoinType getCoin();

  /**
   * Forces the implementing class to have a name attribute. 
   * Returns the name of the player
   * 
   * @return
   */
  public String getPlayerName();

  /**
   * Forces the implementing class to have a color attribute. 
   * Returns the colour of the coin.
   * 
   * @return
   */
  public Color getColor();

  /**
   * Forces the implementing class to have a type of Player attribute. 
   * Returns the type - HUMAN / AI
   * 
   * @return
   */
  public PlayerType getPlayerType();

}
