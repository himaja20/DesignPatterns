package edu.nyu.pqs.ConnectFour.View;

import java.awt.Color;

import edu.nyu.pqs.ConnectFour.Model.Listener;
import edu.nyu.pqs.ConnectFour.Model.Model;
import edu.nyu.pqs.ConnectFour.Model.Player;

/**
 * GameView Class is the listener which gets notifications from the Model
 * class.There can be multiple listeners for a game instance.
 * 
 * This class implements the Listener interface through which it communicates to
 * model. It basically is responsible to show all the movements of the player.
 * 
 * When a player makes a move from the UI, view communicates it to the
 * model.Model fires back notifications telling the view what to show in the
 * GUI.
 * 
 * This class interacts with GameViewBoard class to show the effects on the GUI.
 * 
 * @author himaja
 *
 */

public class GameView implements Listener {
  GameBoardView gui;

  /**
   * GameView Constructor initiates game Board UIs for players
   * 
   * @param model
   * @param isActiveBoard
   */
  public GameView(Model model, boolean isActiveBoard) {
    model.addListener(this);
    gui = new GameBoardView(model, isActiveBoard);
    gui.createGameBoard();
  }

  /**
   * This function is an implementation of the method in the Listener Interface.
   * It makes the UI visible to the user.
   */
  @Override
  public void showGameBoard() {
    gui.showGameBoard();
  }

  /**
   * Implementation of the function in Listener Interface public void
   * endGameInWin(Player player)
   */
  @Override
  public void endGameInWin(Player player) {
    gui.setInfoLabelMessage(player.getPlayerName() + " won the game");
    gui.disableControlButtons();
  }

  /**
   * Implementation of the function in Listener Interface public void
   * endGameInDraw()
   */
  @Override
  public void endGameInDraw() {
    gui.setInfoLabelMessage("Match resulted in a draw");
    gui.disableControlButtons();
  }

  /**
   * Implementation of the function in Listener Interface public void
   * showMadeMove(int row, int column, Color color)
   */
  @Override
  public void showMadeMove(int row, int column, Color color) {
    gui.paintCell(row, column, color);
    gui.toggleControlButtons();
  }

  /**
   * Implementation of the function in Listener Interface public void
   * showMadeMove(String message)
   */
  @Override
  public void showMessage(String message) {
    gui.setInfoLabelMessage(message);
  }

}
