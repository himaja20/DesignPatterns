package edu.nyu.pqs.ConnectFour.Model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;

public class PlayerTests {
  private Player player1;
  private Player player2;
  private Player aiPlayer;
  private GameBoard testBoard;
  private CoinType[][] expectedBoard;

  @Before
  public void setUp() throws Exception {
    player1 = PlayerFactory.createPlayer(
        "Jon Snow", CoinType.P1, Color.RED, PlayerType.HUMAN);
    player2 = PlayerFactory.createPlayer(
        "The WhiteWalkers!", CoinType.P2, Color.BLUE, PlayerType.HUMAN);
    aiPlayer = PlayerFactory.createPlayer(
        "The WhiteWalkers!", CoinType.P2, Color.BLUE, PlayerType.AI);
    int rows = 6;
    int cols = 7;
    testBoard = new GameBoard(rows,cols);
    expectedBoard = new CoinType[rows][cols];

    for(int i = 0; i < rows ; i++){
      for(int j = 0; j < cols; j++){
        expectedBoard[i][j] = CoinType.EMPTY;
      }
    }
  }

  private void performTurnOnTestBoard(int column, CoinType coinType){
    testBoard.getFreeSlotAndSetCoin(column, coinType);
  }

  @Test
  public void checkTypeOfHumanPlayerAfterCreation(){
    assertTrue(player1.getPlayerType() == PlayerType.HUMAN); 
  }

  @Test
  public void checkTypeOfAIPlayerAfterCreation(){
    assertTrue(aiPlayer.getPlayerType() == PlayerType.AI); 
  }

  @Test
  public void checkPlayerColor(){
    assertTrue(player1.getColor() == Color.RED);
  }

  @Test
  public void checkPlayerCoin(){
    assertTrue(player2.getCoin() == CoinType.P2);
  }

  @Test
  public void checkPlayerName(){
    assertTrue(player2.getPlayerName() == "The WhiteWalkers!");
  }

  @Test
  public void checkPlayerEquals(){
    assertTrue(player1.equals(player1));
    assertFalse(player1.equals(player2));
  }

  @Test
  public void makeNextMoveStopOpponentVerticalWin(){
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);

    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 4;
    assertEquals(expected,actual);
  }

  @Test
  public void makeNextMoveStopOpponentHorizontalWin(){
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(2, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(6, CoinType.P1);

    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 3;
    assertEquals(expected,actual);
  }
  
  @Test
  public void makeNextMoveStopOpponentDiagonalWin(){
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(4, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(6, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(6, CoinType.P1);
    performTurnOnTestBoard(6, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    
    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 6;
    assertEquals(expected,actual);
  }

  @Test
  public void makeNextMoveHorizontalWin(){
    performTurnOnTestBoard(2, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(2, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(4, CoinType.P2);
    performTurnOnTestBoard(6, CoinType.P1);
    
    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 2;
    assertEquals(expected,actual);

  }

  @Test
  public void makeNextMoveVerticalWin(){
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);

    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 5;
    assertEquals(expected,actual);
  }

  @Test
  public void makeNextMoveDiagonalRightUpwardsWin(){
    performTurnOnTestBoard(6, CoinType.P1);
    performTurnOnTestBoard(6, CoinType.P2);
    performTurnOnTestBoard(6, CoinType.P1);
    performTurnOnTestBoard(6, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(4, CoinType.P2);
    performTurnOnTestBoard(2, CoinType.P1);

    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 3;

    assertEquals(expected,actual);
  }

  @Test
  public void makeNextMoveDiagonalLeftUpwardsWin(){
    performTurnOnTestBoard(0, CoinType.P1);
    performTurnOnTestBoard(0, CoinType.P2);
    performTurnOnTestBoard(0, CoinType.P1);
    performTurnOnTestBoard(0, CoinType.P2);
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    performTurnOnTestBoard(2, CoinType.P1);
    performTurnOnTestBoard(2, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    
    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 3;

    assertEquals(expected,actual);
  }

  @Test
  public void makeNextMoveDiagonalRightDownwardsWin(){
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(2, CoinType.P1);
    performTurnOnTestBoard(2, CoinType.P2);
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    performTurnOnTestBoard(0, CoinType.P1);
    performTurnOnTestBoard(0, CoinType.P2);
    performTurnOnTestBoard(0, CoinType.P1);
    
    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 0;

    assertEquals(expected,actual);
  }

  @Test
  public void makeNextMoveDiagonalLeftDownwardsWin(){
    performTurnOnTestBoard(2, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(4, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(6, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(6, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(6, CoinType.P1);

    int actual = aiPlayer.makeNextMove(testBoard);
    int expected = 6;

    assertEquals(expected,actual);

  }
}







