package edu.nyu.pqs.ConnectFour.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ConnectFour.Model.GameBoard.CoinType;

public class ModelTests {

  private Player player1;
  private Player player2;
  private Model model;
  private CoinType[][] expectedBoard;
  private int rows;
  private int cols;
  private GameBoard testBoard;

  @Before
  public void setUp() throws Exception {
    player1 = PlayerFactory.createPlayer("Jon Snow",
        CoinType.P1, Color.RED, PlayerType.HUMAN);
    player2 = PlayerFactory.createPlayer("Wildling",
        CoinType.P2, Color.BLUE, PlayerType.HUMAN);
    rows = 6;
    cols = 7;
    model = new Model(rows,cols);
    testBoard = model.getBoard();
    expectedBoard = new CoinType[rows][cols];

    for(int i = 0; i < rows ; i++){
      for(int j = 0; j < cols; j++){
        expectedBoard[i][j] = CoinType.EMPTY;
      }
    }
  }

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

  private void performTurnOnTestBoard(int column){
    model.moveMade(column);
  }

  private void setTestCase(int i, int j, CoinType coinType){
    expectedBoard[i][j] = coinType;
  }

  @Test
  public void beginGameTest(){
    model.beginGame(player1, player2);
    assertEquals(model.getPlayer1(),player1);
    assertEquals(model.getPlayer2(),player2);
    assertEquals(model.getCurrentPlayer(),player1);
  }

  @Test
  public void boardStateCheckAfterMove(){
    model.beginGame(player1, player2);
    setTestCase(5,0,CoinType.P1);
    performTurnOnTestBoard(0);
    compareBoards(testBoard.getCoinSlots(),expectedBoard);
  }

  @Test
  public void checkTogglePlayerCheck(){
    model.beginGame(player1, player2);
    setTestCase(5,0,CoinType.P1);
    performTurnOnTestBoard(0);
    assertTrue(model.getCurrentPlayer().getCoin().equals(player2.getCoin()));
  }

  @Test
  public void checkNumRows(){
    assertEquals(this.rows,model.getRows());
  }

  @Test
  public void checkNumCols(){
    assertEquals(this.cols, model.getCols());
  }

  @Test
  public void checkIntialStateOfGameBoard(){
    assertTrue(compareBoards(testBoard.getCoinSlots(),expectedBoard));
  }

}
