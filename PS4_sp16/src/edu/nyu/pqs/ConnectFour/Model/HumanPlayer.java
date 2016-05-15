package edu.nyu.pqs.ConnectFour.Model;

import java.awt.Color;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;

/**
 * Human Player is a type of Player which 
 * implements the Player Interface.
 * 
 * Human Player defines the attributes 
 * and behaviour of a Human Player.
 * 
 * @author himaja
 */
public class HumanPlayer implements Player {

  private final String playerName;
  private final CoinType coin;
  private final Color color;
  private final PlayerType playerType;

  /**
   * Builder class to build human player objects
   * 
   * @author himaja
   *
   */
  public static class HumanPlayerBuilder {
    private String playerName;
    private CoinType coin;
    private Color color;

    /**
     * to set the player name of the builder object
     * 
     * @param playerName
     * @return HumanPlayerBuilder
     */
    public HumanPlayerBuilder playerName(String playerName) {
      this.playerName = playerName;
      return this;
    }

    /**
     * To set the coin of the builder object
     * 
     * @param coin
     * @return HumanPlayerBuilder
     */
    public HumanPlayerBuilder coin(CoinType coin) {
      this.coin = coin;
      return this;
    }

    /**
     * To set the color of the Builder object
     * 
     * @param color
     * @return HumanPlayerBuilder
     */
    public HumanPlayerBuilder color(Color color) {
      this.color = color;
      return this;
    }

    /**
     * Build the HumanPlayer object using the 
     * Builder object so far created.
     * 
     * @return HumanPlayer
     */
    public HumanPlayer build() {
      return new HumanPlayer(this);
    }
  }

  private HumanPlayer(HumanPlayerBuilder hpb) {
    this.playerName = hpb.playerName;
    this.coin = hpb.coin;
    this.color = hpb.color;
    this.playerType = PlayerType.HUMAN;
  }

  /**
   * @return the coin
   */
  @Override
  public CoinType getCoin() {
    return this.coin;
  }

  /**
   * @return playerType
   */
  @Override
  public PlayerType getPlayerType() {
    return this.playerType;
  }

  /**
   * @return playerName
   */
  @Override
  public String getPlayerName() {
    return this.playerName;
  }

  /**
   * @return color
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * next move Function for a Human player
   * 
   * @return -1 as this function does nothing 
   * and control comes from the view/UI.
   */
  @Override
  public int makeNextMove(GameBoard gameBoard) {
    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((coin == null)
        ? 0 : coin.hashCode());
    result = prime * result + ((color == null)
        ? 0 : color.hashCode());
    result = prime * result + ((playerName == null)
        ? 0 : playerName.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    HumanPlayer other = (HumanPlayer) obj;
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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "HumanPlayer [playerName=" + playerName
        + ", coin=" + coin + ", color=" + color + "]";
  }
}
