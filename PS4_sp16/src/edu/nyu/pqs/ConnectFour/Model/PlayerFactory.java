package edu.nyu.pqs.ConnectFour.Model;

import java.awt.Color;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;

/**
 * This is factory which creates different kinds of players. 
 * This factory is used to create different objects at run-time 
 * based on the player Type.
 * 
 * There are two player types 1. Human Player 2. AI player
 * 
 * Based on the requirement, we can create objects by passing 
 * in the type as one of the parameter.
 * 
 * @author himaja
 *
 */
public class PlayerFactory {
  public static Player createPlayer(String playerName, CoinType coin, Color color, PlayerType type) {
    Player player = null;
    if (type == PlayerType.AI) {
      return new AIPlayer.AIPlayerBuilder().playerName(playerName).coin(coin).color(color).build();
    } else if (type == PlayerType.HUMAN) {
      return new HumanPlayer.HumanPlayerBuilder().playerName(playerName).coin(coin).color(color).build();
    }
    return player;
  }

}
