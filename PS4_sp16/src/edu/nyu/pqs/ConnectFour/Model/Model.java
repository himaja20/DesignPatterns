package edu.nyu.pqs.ConnectFour.Model;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;

/**
 * Model class maintains the back end data structure 
 * for the board. The views communicate with the model 
 * through the listener interface. Model class
 * maintains the state of the two dimensional game board.
 * 
 * Whenever a move is made in the view, the view tells 
 * the model which column it wants to drop the coin. 
 * The model, then performs the following checks -
 * 
 * 1. Check with game board if the column selected by a view is valid 
 * 2. If it is valid 
 *  a. Model again checks with the gameboard to get the free slot
 * available in that column. 
 *  b. It sets the coin into that free slot. 
 *  c. It checks if this move leads to a game win/draw 
 *  d. If yes, ends the game communicating the same to all views. 
 *  e. If No, toggles the player to continue to the game. 
 * 3. If the selection is invalid, it tells the view to make
 * another selection.
 * 
 * @author himaja
 *
 */
public class Model {

  private List<Listener> listeners;
  private GameBoard board;
  private Player currentPlayer;
  private final int rows;
  private final int cols;
  private Player player1;
  private Player player2;

  /**
   * Constructor
   * @param rows
   * @param cols
   */
  public Model(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.board = new GameBoard(rows, cols);
    this.listeners = new ArrayList<Listener>();
  }

  /**
   * @return the player1
   */
  public Player getPlayer1() {
    return player1;
  }

  /**
   * @param player1
   *          the player1 to set
   */
  private void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  /**
   * @return the player2
   */
  public Player getPlayer2() {
    return player2;
  }

  /**
   * @param player2
   *          the player2 to set
   */
  private void setPlayer2(Player player2) {
    this.player2 = player2;
  }

  /**
   * @return the board
   */
  public GameBoard getBoard() {
    return board;
  }

  /**
   * @return the number of rows
   */
  public int getRows() {
    return rows;
  }

  /**
   * @return the number of cols
   */
  public int getCols() {
    return cols;
  }

  /**
   * @return currentPlayer
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  /**
   * This method is more useful in the Human-AI game.
   * This function is called by the model to prompt the user
   * to make its move, after toggling the user.
   * 
   * This in turn calls the makeNextMove(GameBoard board) method
   * on the current Player and passes the column selection to 
   * the model via the moveMade(column)
   */
  public void makeMove() {
    int move = currentPlayer.makeNextMove(board);
    if (move >= 0) {
      moveMade(move);
    }
  }

  /**
   * Adds a listener to the list of Listeners
   * @param listener
   */
  public void addListener(Listener listener) {
    listeners.add(listener);
  }

  /**
   * Removes a listener from a list of Listeners
   * @param listener
   */
  public void removeListener(Listener listener) {
    listeners.remove(listener);
  }
  
  /**
   * Begins the game by registering the players with the model
   * @param player1
   * @param player2
   */
  public void beginGame(Player player1, Player player2) {
    setPlayer1(player1);
    setPlayer2(player2);
    setCurrentPlayer(player1);
    for (Listener listener : listeners) {
      listener.showGameBoard();
    }
  }

  /**
   * This function checks the following with the game board-
   * 
   * 1. if a selection is valid
   * 2. if yes, updates the board with move
   * 3. Notifies the views to show the move in the UI
   * 4. Checks if this move is a winning move
   * 5. If yes, notifies the views to end the game in Win
   * 6. Checks if this move is leads to a draw
   * 7. If yes, notifies the views to end the game in draw
   * 8. Otherwise, toggles the players to continue the game
   * @param column
   */
  public void moveMade(int column) {
    if (!board.isValidMove(column)) {
      fireInvalidMoveNotification();
      return;
    }
    int rowSlot = board.getFreeSlotAndSetCoin(column, currentPlayer.getCoin());
    fireMoveMadeNotification(rowSlot, column, currentPlayer);
    System.out.println("performTurnOnTestBoard(" + column + ", CoinType." + currentPlayer.getCoin() + ");");
    if (board.isWinningMove(rowSlot, column, currentPlayer.getCoin())) {
      fireGameWonNotification(currentPlayer);
    } else if (board.isFull()) {
      fireGameDrawNotification();
    } else {
      changePlayerTurn();
    }
  }

  /**
   * Toggles the player and sets the current player to the 
   * active player.
   */
  void changePlayerTurn() {
    if (currentPlayer.getCoin() == CoinType.P1) {
      setCurrentPlayer(player2);
    } else if (currentPlayer.getCoin() == CoinType.P2) {
      setCurrentPlayer(player1);
    } else {
      throw new IllegalArgumentException("Unknown Player");
    }
    makeMove();
  }

  private void fireGameDrawNotification() {
    for (Listener listener : listeners) {
      listener.endGameInDraw();
    }
  }

  private void fireGameWonNotification(Player player) {
    for (Listener listener : listeners) {
      listener.endGameInWin(player);
    }
  }

  private void fireMoveMadeNotification(int row, int column, Player player) {
    for (Listener listener : listeners) {
      listener.showMadeMove(row, column, player.getColor());
    }
  }

  private void fireInvalidMoveNotification() {
    for (Listener listener : listeners) {
      listener.showMessage("Invalid move, try another selection");
    }
  }
}
