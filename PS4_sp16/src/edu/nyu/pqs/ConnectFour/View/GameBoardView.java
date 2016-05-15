package edu.nyu.pqs.ConnectFour.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;
import edu.nyu.pqs.ConnectFour.Model.Model;
import edu.nyu.pqs.ConnectFour.Model.Player;
import edu.nyu.pqs.ConnectFour.Model.PlayerFactory;
import edu.nyu.pqs.ConnectFour.Model.PlayerType;

/**
 * GameBoardView maintains all the Java Swing Code to show the GUI to the user.
 * The functions in this class will be called by the listeners to show the
 * effects on the GUI.
 * 
 * It basically does operations like - 1. Showing the grid panel of the game
 * 2.Showing the moves made by the user by setting the backgrounds of the cells
 * to respective colors 3. Enabling and Disabling Control buttons etc.
 * 
 * @author himaja
 *
 */
public class GameBoardView {

  private final Model model;
  private final int rows;
  private final int cols;
  private JFrame frame = new JFrame("ConnectFour Game of the Westeros");
  private JFrame openingFrame = new JFrame("Connect Four Game of the Westeros- Options");
  private JLabel label;
  private JPanel[][] slots;
  private boolean isActiveBoard;
  private ArrayList<JButton> buttons = new ArrayList<JButton>();

  /**
   * Constructor
   * 
   * @param model
   * @param isActiveBoard
   */
  public GameBoardView(Model model, boolean isActiveBoard) {
    this.model = model;
    this.rows = model.getRows();
    this.cols = model.getCols();
    this.slots = new JPanel[rows][cols];
    label = new JLabel();
    this.isActiveBoard = isActiveBoard;
  }

  /**
   * Displays the UI for the start menu to select the game type -
   * playWithComputer/2-player
   * 
   * Loads the game based on the selection made by the user.
   */
  public void displayGameOptions() {
    JPanel optionPanel = new JPanel(new GridLayout(1, 2, 2, 2));
    JButton playWithComputer = new JButton("Play with Computer");
    playWithComputer.setBackground(Color.LIGHT_GRAY);
    JButton twoPlayer = new JButton("2-Player Game");
    optionPanel.add(playWithComputer);
    twoPlayer.setBackground(Color.LIGHT_GRAY);
    optionPanel.add(twoPlayer);
    optionPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    optionPanel.setBackground(Color.darkGray);
    openingFrame.getContentPane().add(optionPanel);
    openingFrame.setVisible(true);
    openingFrame.setSize(500, 150);

    playWithComputer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        openingFrame.setVisible(false);
        Player player1 = PlayerFactory.createPlayer("Jon Snow", CoinType.P1, Color.RED, PlayerType.HUMAN);
        Player player2 = PlayerFactory.createPlayer("The WhiteWalkers!", CoinType.P2, Color.BLUE, PlayerType.AI);
        model.beginGame(player1, player2);
      }
    });

    twoPlayer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        openingFrame.setVisible(false);
        Player player1 = PlayerFactory.createPlayer("Jon Snow", CoinType.P1, Color.RED, PlayerType.HUMAN);
        Player player2 = PlayerFactory.createPlayer("Wilding", CoinType.P2, Color.BLUE, PlayerType.HUMAN);
        model.beginGame(player1, player2);
      }
    });
  }

  /**
   * Creates the Swing UI for the gameBoard. It has the following - 1. A 6x7
   * grid with empty slots 2. Control buttons to drop coins 3. Information panel
   * to show messages to the user.
   */
  public void createGameBoard() {
    JPanel masterPanel = new JPanel(new BorderLayout());
    GridLayout boardLayout = new GridLayout(rows, cols, 4, 4);
    JPanel gamePanel = new JPanel();
    gamePanel.setLayout(boardLayout);
    gamePanel.setBackground(Color.DARK_GRAY);
    gamePanel.setOpaque(true);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        slots[i][j] = panel;
        gamePanel.add(slots[i][j]);
      }
    }
    GridLayout controlLayout = new GridLayout(1, cols, 2, 2);
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(controlLayout);

    for (int i = 0; i < (1 * cols); i++) {
      final JButton button = new JButton(Integer.toString(i));
      buttons.add(button);
      button.setName(Integer.toString(i));
      button.setEnabled(isActiveBoard);
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          buttonClicked(button.getName());
        }
      });
      button.setBackground(Color.ORANGE);
      controlPanel.add(button);
    }

    JPanel infoPanel = new JPanel(new BorderLayout());
    label.setText("Let's begin the game");
    infoPanel.add(label);

    masterPanel.add(infoPanel, BorderLayout.SOUTH);
    masterPanel.add(gamePanel, BorderLayout.CENTER);
    masterPanel.add(controlPanel, BorderLayout.NORTH);

    frame.getContentPane().add(masterPanel);

    frame.setVisible(false);
    frame.setSize(600, 500);
  }

  /**
   * Makes the GameBoard visible to the views
   */
  public void showGameBoard() {
    frame.setVisible(true);
  }

  /**
   * Intimates the model of the selection made by the user in the UI.
   * 
   * @param name
   */
  public void buttonClicked(String name) {
    label.setText(name);
    model.moveMade(Integer.parseInt(name));
  }

  /**
   * Disables the control Buttons on the UI; user will not be able to make any
   * more selections
   */
  public void disableControlButtons() {
    for (JButton button : buttons) {
      button.setEnabled(false);
    }
  }

  /**
   * Shows a message in the information panel of the game
   * 
   * @param message
   */
  public void setInfoLabelMessage(String message) {
    label.setText(message);
  }

  /**
   * Sets the background of a cell to the specified color
   * 
   * @param row
   * @param column
   * @param color
   */
  public void paintCell(int row, int column, Color color) {
    slots[row][column].setBackground(color);
  }

  /**
   * Toggles the control buttons to be enabled/disabled so that only one player
   * can play at a time
   */
  public void toggleControlButtons() {
    for (JButton button : buttons) {
      button.setEnabled(!button.isEnabled());
    }
  }
}
