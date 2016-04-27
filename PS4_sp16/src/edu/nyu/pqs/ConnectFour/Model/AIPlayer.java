package edu.nyu.pqs.ConnectFour.Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;

/**
 * AIPlayer is a type of player. This class implements 
 * the interface Player.
 * 
 * AIPlayer is a class which defines a computer
 * (Artificial Intelligence) player.
 * @author himaja
 */
public class AIPlayer implements Player {

  private final String playerName;
  private final CoinType coin;
  private final Color color;
  private final PlayerType playerType;

  /**
   * Builder pattern to build the player object 
   * with its attributes.
   * @author himaja
   *
   */
  public static class AIPlayerBuilder {
    private String playerName;
    private CoinType coin;
    private Color color;

    /**
     * to set the name using builder object
     * @param playerName
     * @return
     */
    public AIPlayerBuilder playerName(String playerName) {
      this.playerName = playerName;
      return this;
    }

    /**
     * To set the coin using the builder object
     * @param coin
     * @return
     */
    public AIPlayerBuilder coin(CoinType coin) {
      this.coin = coin;
      return this;
    }

    /**
     * to set the color using the builder object 
     * @param color
     * @return
     */
    public AIPlayerBuilder color(Color color) {
      this.color = color;
      return this;
    }

    /**
     * build object which returns the AIPlayer object
     * @return
     */
    public AIPlayer build() {
      return new AIPlayer(this);
    }
  }

  private AIPlayer(AIPlayerBuilder aiPlayerBuilder) {
    this.playerName = aiPlayerBuilder.playerName;
    this.coin = aiPlayerBuilder.coin;
    this.color = aiPlayerBuilder.color;
    this.playerType = PlayerType.AI;
  }

  /**
   * This function makes a choice for the AI player
   * to select a position on the board to play the 
   * computer turn.
   * 
   * This function checks the following -
   * 1. It checks to stop the opponent from winning
   * 2. It checks to make a winning move for computer win
   */
  @Override
  public int makeNextMove(GameBoard gameBoard) {
    int cols = gameBoard.getCols();
    CoinType[][] slots = gameBoard.getCoinSlots();
    List<Integer> validColumns = new ArrayList<Integer>();

    for (int j = 0; j < cols; j++) {
      int rowSlot = gameBoard.getFreeSlot(j);
      if (rowSlot >= 0) {
        validColumns.add(j);
        if (slots[rowSlot][j] == CoinType.EMPTY) {
          if (gameBoard.isWinningMove(rowSlot, j, CoinType.P1)) {
            return j;
          }
          if (gameBoard.isWinningMove(rowSlot, j, this.getCoin())) {
            return j;
          }
        }
      }
    }
    Collections.shuffle(validColumns);
    return validColumns.get(0);
  }

  /**
   * Returns the CoinType of this player.
   * Returns Player1/Player2.
   */
  @Override
  public CoinType getCoin() {
    return this.coin;
  }

  /**
   * Getter for playerName
   */
  @Override
  public String getPlayerName() {
    return this.playerName;
  }

  /**
   * Getter for Color of this player
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Getter for the type of the player
   */
  @Override
  public PlayerType getPlayerType() {
    return this.playerType;
  }

  /**
   * hashCode implementation
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((coin == null) ? 0 : coin.hashCode());
    result = prime * result + ((color == null) ? 0 : color.hashCode());
    result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
    return result;
  }


  /**
   * Equals implementation
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AIPlayer other = (AIPlayer) obj;
    if (coin != other.coin)
      return false;
    if (color == null) {
      if (other.color != null)
        return false;
    } else if (!color.equals(other.color))
      return false;
    if (playerName == null) {
      if (other.playerName != null)
        return false;
    } else if (!playerName.equals(other.playerName))
      return false;
    return true;
  }

  /**
   * toString implementation
   */
  @Override
  public String toString() {
    return "AIPlayer1 [playerName=" + playerName + ", coin=" + coin + ", color=" + color + "]";
  }

}
