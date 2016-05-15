package edu.nyu.pqs.ConnectFour.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;

/**
 * This class consists of tests for all the board
 * related functions.
 * 
 * It checks functions like valid move, getting free slot from
 * the board, checking if a position is a winning move etc
 * @author himaja
 *
 */
public class BoardTests {
  
  private int rows;
  private int cols;
  private GameBoard testBoard;
  private CoinType[][] expectedBoard;

  @Before
  public void setUp() throws Exception {
    rows = 6;
    cols = 7;
    testBoard = new GameBoard(rows,cols);
    expectedBoard = new CoinType[rows][cols];
    
    for(int i = 0; i < rows ; i++){
      for(int j = 0; j < cols; j++){
        expectedBoard[i][j] = CoinType.EMPTY;
      }
    }
  }
  
  /**
   * compares two boards
   * @param testBoard
   * @param expectedBoard
   * @return
   */
  private boolean compareBoards(CoinType[][] testBoard,
      CoinType[][] expectedBoard){
    for(int i = 0; i < rows ; i++){
      for(int j = 0; j < cols; j++){
        if(expectedBoard[i][j] != testBoard[i][j]){
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Performs a turn on the gameBoard object
   * @param column
   * @param coinType
   */
  private void performTurnOnTestBoard(int column, CoinType coinType){
    testBoard.getFreeSlotAndSetCoin(column, coinType);
  }
  
  /**
   * Sets the test case by setting a coin in the test 2D-array
   * @param i
   * @param j
   * @param coinType
   */
  private void setTestCase(int i, int j, CoinType coinType){
    expectedBoard[i][j] = coinType;
  }
  
  @Test
  public void getCoinSlotsShouldReturnCurrentStateOfBoard(){
    boolean expected = compareBoards(testBoard.getCoinSlots()
        ,expectedBoard);
    assertTrue(expected);
    
  }

  @Test
  public void getFreeSlotAndSetCoinTest(){
    testBoard.getFreeSlotAndSetCoin(5,CoinType.P1);
    setTestCase(5,5,CoinType.P1);
    assertTrue(compareBoards(testBoard.getCoinSlots()
         ,expectedBoard));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getFreeSlotAndSetCoinInvalidColumnTest(){
    testBoard.getFreeSlotAndSetCoin(8, CoinType.P1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getFreeSlotOnInvalidColumnTest(){
    testBoard.getFreeSlot(8);
  }
  
  @Test
  public void getFreeSlotOnFullColumnShouldReturnNothing(){
    int actual = 0;
    for (int i = 0; i < rows; i++){
      if(i % 2 == 0){
        performTurnOnTestBoard(3,CoinType.P1);
      }
      else{
        performTurnOnTestBoard(3,CoinType.P2);
      }
    }
    for (int i = 0; i <= rows ; i++){
      actual = testBoard.getFreeSlot(3);
    }
    int expected = -1;
    assertEquals(actual,expected);
  } 
  
  @Test
  public void isValidMoveOnEmptyColumnShouldReturnTrue(){
    assertTrue(testBoard.isValidMove(0)
        && testBoard.isValidMove(1)
        && testBoard.isValidMove(2)
        && testBoard.isValidMove(3)
        && testBoard.isValidMove(4)
        && testBoard.isValidMove(5)
        && testBoard.isValidMove(6));
  }
  
  @Test
  public void isValidMoveOnFullColumnShouldReturnFalse(){
    for (int i = 0; i < rows; i++){
      if(i % 2 == 0){
        performTurnOnTestBoard(3,CoinType.P1);
      }
      else{
        performTurnOnTestBoard(3,CoinType.P2);
      }
    }
    assertFalse(testBoard.isValidMove(3));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void isWinningMoveValidRowArgumentsTest(){
    testBoard.isWinningMove(8, 3, CoinType.P1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void isWinningMoveValidColumnArgumentsTest(){
    testBoard.isWinningMove(3, 8, CoinType.P1);
  }
  
  @Test
  public void isWinningMoveNegativeTest(){
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(4,CoinType.P2);
    performTurnOnTestBoard(5,CoinType.P1);
    performTurnOnTestBoard(6,CoinType.P2);
    performTurnOnTestBoard(4,CoinType.P1);
    performTurnOnTestBoard(5,CoinType.P2);
    performTurnOnTestBoard(6,CoinType.P1);
    performTurnOnTestBoard(5,CoinType.P2);
    performTurnOnTestBoard(6,CoinType.P1);
    performTurnOnTestBoard(6,CoinType.P2);
    
    assertFalse(testBoard.isWinningMove(2, 6, CoinType.P1));
  }
  
  @Test
  public void iswinningMoveDownwardsLeftDiagonalTest(){
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(4,CoinType.P2);
    performTurnOnTestBoard(5,CoinType.P1);
    performTurnOnTestBoard(6,CoinType.P2);
    performTurnOnTestBoard(4,CoinType.P1);
    performTurnOnTestBoard(5,CoinType.P2);
    performTurnOnTestBoard(6,CoinType.P1);
    performTurnOnTestBoard(6,CoinType.P2);
    performTurnOnTestBoard(5,CoinType.P1);
    performTurnOnTestBoard(2,CoinType.P2);
    performTurnOnTestBoard(6,CoinType.P1);
    
    assertTrue(testBoard.isWinningMove(2, 6, CoinType.P1));
  }
  
  @Test
  public void isWinningMoveDownwardsRightDiagonalTest(){
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(2,CoinType.P2);
    performTurnOnTestBoard(1,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(2,CoinType.P1);
    performTurnOnTestBoard(1,CoinType.P2);
    performTurnOnTestBoard(0,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(1,CoinType.P1);
    performTurnOnTestBoard(2,CoinType.P2);
    performTurnOnTestBoard(0,CoinType.P1);

    assertTrue(testBoard.isWinningMove(2, 0, CoinType.P1));
  }
  
  @Test
  public void isWinningMoveUpwardsLeftDiagonal(){
    performTurnOnTestBoard(1,CoinType.P1);
    performTurnOnTestBoard(2,CoinType.P2);
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(2,CoinType.P2);
    performTurnOnTestBoard(1,CoinType.P1);
    performTurnOnTestBoard(3,CoinType.P2);
    performTurnOnTestBoard(1,CoinType.P1);
    performTurnOnTestBoard(2,CoinType.P2);
    performTurnOnTestBoard(0,CoinType.P1);
    performTurnOnTestBoard(1,CoinType.P2);
    performTurnOnTestBoard(0,CoinType.P1);
    performTurnOnTestBoard(4,CoinType.P2);
    
    assertTrue(testBoard.isWinningMove(5, 4, CoinType.P2));
  }
  
  @Test 
  public void isWinningMoveUpwardsRightDiagonal(){
    performTurnOnTestBoard(4,CoinType.P1);
    performTurnOnTestBoard(5,CoinType.P2);
    performTurnOnTestBoard(4,CoinType.P1);
    performTurnOnTestBoard(5,CoinType.P2);
    performTurnOnTestBoard(5,CoinType.P1);
    performTurnOnTestBoard(6,CoinType.P2);
    performTurnOnTestBoard(6,CoinType.P1);
    performTurnOnTestBoard(6,CoinType.P2);
    performTurnOnTestBoard(6,CoinType.P1);
    performTurnOnTestBoard(6,CoinType.P2);
    performTurnOnTestBoard(3,CoinType.P1);
    
    assertTrue(testBoard.isWinningMove(5, 3, CoinType.P1));
  }
  
  @Test //TODO
  public void isWinningMoveHorizontalRight(){
    performTurnOnTestBoard(6,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(5,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(4,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(3,CoinType.P1);
    
    assertTrue(testBoard.isWinningMove(5, 3, CoinType.P1));
  }
  
  @Test
  public void isWinningMoveHorizontalLeft(){
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(4,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(5,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(6,CoinType.P1);
    
    assertTrue(testBoard.isWinningMove(5, 6, CoinType.P1));
  }
  
  @Test
  public void isWinningMoveHorizontalMiddle(){
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(5,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(6,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(4,CoinType.P1);
    
    assertTrue(testBoard.isWinningMove(5, 4, CoinType.P1));
  }
  
  @Test
  public void isWinningMoveVerticalDown(){
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(3,CoinType.P1);
    performTurnOnTestBoard(0,CoinType.P2);
    performTurnOnTestBoard(3,CoinType.P1);
    
    assertTrue(testBoard.isWinningMove(2, 3, CoinType.P1));
  }
  
  @Test
  public void isBoardFullCheckForFullBoard(){
    performTurnOnTestBoard(6, CoinType.P1);
    performTurnOnTestBoard(6, CoinType.P2);
    performTurnOnTestBoard(6, CoinType.P1);
    performTurnOnTestBoard(6, CoinType.P2);
    performTurnOnTestBoard(6, CoinType.P1);
    performTurnOnTestBoard(6, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(4, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(4, CoinType.P2);
    performTurnOnTestBoard(4, CoinType.P1);
    performTurnOnTestBoard(4, CoinType.P2);
    performTurnOnTestBoard(2, CoinType.P1);
    performTurnOnTestBoard(2, CoinType.P2);
    performTurnOnTestBoard(2, CoinType.P1);
    performTurnOnTestBoard(2, CoinType.P2);
    performTurnOnTestBoard(2, CoinType.P1);
    performTurnOnTestBoard(2, CoinType.P2);
    performTurnOnTestBoard(0, CoinType.P1);
    performTurnOnTestBoard(0, CoinType.P2);
    performTurnOnTestBoard(0, CoinType.P1);
    performTurnOnTestBoard(0, CoinType.P2);
    performTurnOnTestBoard(0, CoinType.P1);
    performTurnOnTestBoard(0, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    
    assertTrue(testBoard.isFull());
  }
  
  @Test
  public void isBoardFullCheckForNonEmptyBoard(){
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    performTurnOnTestBoard(5, CoinType.P1);
    performTurnOnTestBoard(3, CoinType.P2);
    performTurnOnTestBoard(1, CoinType.P1);
    performTurnOnTestBoard(5, CoinType.P2);
    performTurnOnTestBoard(3, CoinType.P1);
    performTurnOnTestBoard(1, CoinType.P2);
    
    assertFalse(testBoard.isFull());
  }


}
