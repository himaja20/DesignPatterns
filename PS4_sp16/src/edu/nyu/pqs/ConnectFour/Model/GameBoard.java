package edu.nyu.pqs.ConnectFour.Model;

/**
 * GameBoard is the two dimensional data structure which 
 * maintains the state of the game.
 * 
 * All operations are reflected and stored in the 2D-array.
 * 
 * @author himaja
 *
 */
public class GameBoard {

  /**
   * Enum to identify CoinTypes in model board 
   * data Structure
   * A player can have one type of Coin
   * @author himaja
   *
   */
  public enum CoinType {
    EMPTY, P1, P2;
  };

  private final int cols;
  private final int rows;
  private CoinType coinSlots[][];
  private final int countToWin = 4;

  /**
   * Constructor creates an empty 2D - array
   * to maintain the state of the game in the model
   * 
   * Initially, all slots have the CoinType "Empty"
   * @param rows
   * @param cols
   */
  public GameBoard(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.coinSlots = new CoinType[rows][cols];

    for (int row = 0; row < this.rows; row++) {
      for (int column = 0; column < this.cols; column++) {
        this.setCoin(row, column, CoinType.EMPTY);
      }
    }
  }

  /**
   * @return the cols
   */
  public int getCols() {
    return cols;
  }

  /**
   * @return the rows
   */
  public int getRows() {
    return rows;
  }

  /**
   * @return the coinSlots
   */
  public CoinType[][] getCoinSlots() {
    return coinSlots;
  }

  /**
   * @return the countToWin
   */
  public int getCountToWin() {
    return countToWin;
  }

  /**
   * This functions gets a free slot available in a specified
   * column and sets the slot with the specified coinType(P1/P2)
   * 
   * @param column
   * @param coinType
   * @return -1 if there is no free slot in the argument column, otherwise
   * returns the row in which the coin is being placed.
   */
  int getFreeSlotAndSetCoin(int column, CoinType coinType) {
    if (column >= cols) {
      throw new IllegalArgumentException("Invalid value for column");
    }
    for (int i = rows - 1; i >= 0; i--) {
      if (coinSlots[i][column] == CoinType.EMPTY) {
        setCoin(i, column, coinType);
        return i;
      }
    }
    return -1;
  }

  /**
   * This functions gets a free slot available in a specified
   * column.
   * 
   * @param column
   * @return -1 if there is no free slot in the argument column, otherwise
   * returns the row in which the there is a free slot
   */
  int getFreeSlot(int column) {
    if (column >= cols) {
      throw new IllegalArgumentException("Column should be less than " + cols);
    }
    for (int i = rows - 1; i >= 0; i--) {
      if (coinSlots[i][column] == CoinType.EMPTY) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Checks if a move can be made into this column.
   * @param column
   * @return true if there are any empty slots, else return false
   */
  public boolean isValidMove(int column) {
    if (column >= cols) {
      throw new IllegalArgumentException("Column should be less than " + cols);
    }
    for (int i = 0; i < rows; i++) {
      if (coinSlots[i][column] == CoinType.EMPTY) {
        return true;
      }
    }
    return false;
  }

  private void setCoin(int row, int column, CoinType coinType) {
    coinSlots[row][column] = coinType;
  }

  /**
   * Checks if the position at the given row and column is a winning move for
   * given coinType (P1/P2)
   * @param row
   * @param column
   * @param coinType
   * @return true if this position is a winning position, else returns false
   */
  boolean isWinningMove(int row, int column, CoinType coinType) {
    if (row >= rows || column >= cols) {
      throw new IllegalArgumentException("row or column is not within limits");
    }
    boolean h = checkForHorizontalFour(row, column, coinType);
    boolean v = checkForVerticalFour(row, column, coinType);
    boolean d = checkForDiagonalFour(row, column, coinType);
    return h || v || d;
  }

  /**
   * Check if the position leads to a diagonal win in all four directions -
   * left,right,up and down.
   * @param row
   * @param column
   * @param coinType
   * @return true if this position leads to a win
   */
  private boolean checkForDiagonalFour(int row, int column, CoinType coinType) {
    int count = 1;
    // diagonal downwards left
    for (int i = row + 1, j = column - 1; i < rows && j >= 0; i++, j--) {
      if (coinSlots[i][j] == coinType) {
        count++;
        if (count == countToWin) {
          return true;
        }
      } else {
        break;
      }
    }
    // diagonal upwards right
    for (int i = row - 1, j = column + 1; j < cols && i >= 0; i--, j++) {
      if (coinSlots[i][j] == coinType) {
        count++;
        if (count == countToWin) {
          return true;
        }
      } else {
        break;
      }
    }

    count = 1;
    // diagonal upwards left
    for (int i = row - 1, j = column - 1; j >= 0 && i >= 0; i--, j--) {
      if (coinSlots[i][j] == coinType) {
        count++;
        if (count == countToWin) {
          return true;
        }
      } else {
        break;
      }
    }
    // diagonal downwards right
    for (int i = row + 1, j = column + 1; i < rows && j < cols; i++, j++) {
      if (coinSlots[i][j] == coinType) {
        count++;
        if (count == countToWin) {
          return true;
        }
      } else {
        break;
      }
    }
    return false;
  }

  /**
   * Checks if the position leads to a vertical win
   * @param row
   * @param column
   * @param coinType
   * @return true if leads to a win vertically
   */
  private boolean checkForVerticalFour(int row, int column, CoinType coinType) {
    int count = 1;
    for (int i = row + 1; i < rows; i++) {
      if (coinSlots[i][column] == coinType) {
        count++;
        if (count == countToWin) {
          return true;
        }
      } else {
        break;
      }
    }

    for (int i = row - 1; i >= 0; i--) {
      if (coinSlots[i][column] == coinType) {
        count++;
        if (count == countToWin) {
          return true;
        }
      } else {
        break;
      }
    }
    return false;
  }

  /**
   * Checks winning move in the horizontal direction
   * @param row
   * @param column
   * @param coinType
   * @return true if it is a winning positon horizontally.
   */
  private boolean checkForHorizontalFour(int row, int column, CoinType coinType) {
    int count = 1;
    for (int i = column - 1; i >= 0; i--) {
      if (coinSlots[row][i] == coinType) {
        count++;
        if (count == countToWin) {
          return true;
        }
      } else {
        break;
      }
    }
    for (int i = column + 1; i < cols; i++) {
      if (coinSlots[row][i] == coinType) {
        count++;
        if (count == countToWin) {
          return true;
        }
      } else {
        break;
      }
    }
    return false;
  }

  /**
   * checks if the board is full. If all slots in the board
   * are either filled by P1/P2, board is full.
   * @return
   */
  public boolean isFull() {
    for (int i = 0; i < cols; i++) {
      if (coinSlots[0][i] == CoinType.EMPTY) {
        return false;
      }
    }
    return true;
  }
}
