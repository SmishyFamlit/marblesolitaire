package model.hw04;

import java.util.ArrayList;

import model.hw02.MarbleSolitaireModel;

/**
 * Represents an abstract version of Marble Solitaire Model to reduce redundant code.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  protected final int armThickness;
  protected final int emptyRow;
  protected final int emptyCol;
  protected final ArrayList<ArrayList<SlotState>> board;

  /**
   * A constructor that takes in nothing and build the default game of Marble Solitaire.
   */
  public AbstractSolitaireModel() {
    this.armThickness = 3;
    this.emptyRow = (3 * armThickness - 2) / 2;
    this.emptyCol = (3 * armThickness - 2) / 2;
    this.board = createBoard();
  }

  /**
   * Represents a traditional sized game some Marble Solitaire but with a customized
   * location for the empty slot.
   *
   * @param sRow the row where the empty slot will be placed
   * @param sCol the column where the empty slot will be placed
   * @throws IllegalArgumentException when empty row or col are less than zero or not a valid
   *                                  location on the board
   */
  public AbstractSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = 3;

    if (this.outOfBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    this.emptyRow = sRow;
    this.emptyCol = sCol;
    this.board = createBoard();


  }

  /**
   * Represents an Abstract Marble Solitaire where the arm thickness of the board is
   * customizable.
   *
   * @param armThickness The thickness of how long the arms should be starting at 3
   * @throws IllegalArgumentException if armThickness is not a positive odd number, or it is less
   *                                  than one
   */
  public AbstractSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Arm Thickness must be a positive odd number");
    }

    this.armThickness = armThickness;
    this.emptyRow = (3 * armThickness - 2) / 2;
    this.emptyCol = (3 * armThickness - 2) / 2;
    this.board = createBoard();
  }


  /**
   * Represents a game of Marble Solitaire game where the arm thickness of the board is
   * customizable and where the empty slot is placed is also customizable.
   *
   * @param armThickness The thickness of how long the arms should be starting at 3
   * @param sRow         the row where the empty slot will be placed
   * @param sCol         the column where the empty slot will be placed
   * @throws IllegalArgumentException if armThickness is not an odd positive number greater than
   *                                  1 or if the sRow or sCol position are less than zero or
   *                                  not valid spots on the board
   */
  public AbstractSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Arm Thickness must be a positive odd number");
    }

    this.armThickness = armThickness;

    if (this.outOfBounds(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      this.emptyRow = sRow;
      this.emptyCol = sCol;
      this.board = createBoard();
    }


  }

  /**
   * Moves the marble pieces from (fromRow, fromCol) to (toRow, toCol).
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    if (!validMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("This is not a valid move, the from and to positions must "
              + "have a marble and an empty space respectively. "
              + "The must be exactly two spaces between the from and to "
              + "position. You can't move diagonally.");
    }

    ArrayList<SlotState> movingRow = this.board.get(fromRow);
    ArrayList<SlotState> movingCol;
    if (fromRow == toRow && fromCol > toCol && !this.outOfBounds(fromRow, fromCol - 1)) {
      movingRow.set(fromCol - 1, SlotState.Empty);
      movingRow.set(toCol, SlotState.Marble);
      movingRow.set(fromCol, SlotState.Empty);

    } else if (fromRow == toRow && fromCol < toCol && !this.outOfBounds(fromRow, fromCol + 1)) {
      movingRow.set(fromCol + 1, SlotState.Empty);
      movingRow.set(toCol, SlotState.Marble);
      movingRow.set(fromCol, SlotState.Empty);
    }

    if (fromCol == toCol && fromRow > toRow && !this.outOfBounds(fromRow - 1, fromCol)) {
      movingCol = this.board.get(fromRow - 1);
      movingCol.set(fromCol, SlotState.Empty);
      movingCol = this.board.get(fromRow);
      movingCol.set(fromCol, SlotState.Empty);
      movingCol = this.board.get(toRow);
      movingCol.set(toCol, SlotState.Marble);
    } else if (fromCol == toCol && fromRow < toRow && !this.outOfBounds(fromRow + 1, fromCol)) {
      movingCol = this.board.get(fromRow + 1);
      movingCol.set(fromCol, SlotState.Empty);
      movingCol = this.board.get(fromRow);
      movingCol.set(fromCol, SlotState.Empty);
      movingCol = this.board.get(toRow);
      movingCol.set(toCol, SlotState.Marble);
    }
  }

  /**
   * Checks to see if a marble has any valid moves.
   *
   * @param fromRow the row that is being moved from
   * @param fromCol the col that is being moved from
   * @param toRow   the row that is being moved to
   * @param toCol   the cold that is being moved to
   * @return true if move can be made, false if not
   */
  private boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (outOfBounds(fromRow, fromCol) || (outOfBounds(toRow, toCol))) {
      return false;
    }

    if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      return false;
    }

    if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      return false;
    }

    if (fromRow != toRow && fromCol != toCol) {
      return false;
    }

    if (fromRow == toRow && Math.abs(fromCol - toCol) != 2) {
      return false;
    }

    if (fromCol == toCol && Math.abs(fromRow - toRow) != 2) {
      return false;
    }

    if (fromRow == toRow) {
      if (fromCol > toCol) {
        return this.getSlotAt(fromRow, fromCol - 1) == SlotState.Marble;
      }

      return this.getSlotAt(fromRow, fromCol + 1) == SlotState.Marble;
    }

    if (fromRow > toRow) {
      return this.getSlotAt(fromRow - 1, fromCol) == SlotState.Marble;
    }
    return this.getSlotAt(fromRow + 1, fromCol) == SlotState.Marble;

  }

  /**
   * Gets the size of the board being the largest side of the board.
   *
   * @return the largest width of the board
   */
  public int getBoardSize() {
    return 3 * this.armThickness - 2;
  }

  /**
   * Gets the SlotState of the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the SlotState of the board
   * @throws IllegalArgumentException if the (row, col) is not a valid position of the board
   */
  public SlotState getSlotAt(int row, int col)
          throws IllegalArgumentException {
    if (row < 0 || row > this.armThickness + ((2 * armThickness) - 3)) {
      throw new IllegalArgumentException("Cannot get SlotState at row " + row);
    }

    if (col < 0 || col > this.armThickness + ((2 * armThickness) - 3)) {
      throw new IllegalArgumentException("Cannot get SlotState at col " + col);
    }

    ArrayList<SlotState> emptyRowPos;
    emptyRowPos = board.get(row);

    return emptyRowPos.get(col);

  }

  /**
   * Gets the current score of the game.
   *
   * @return the score of the game
   */
  public int getScore() {
    int score = 0;
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {
        if (this.getSlotAt(row, col) == SlotState.Marble) {
          score = score + 1;
        }
      }
    }
    return score;
  }

  /**
   * Creates the board for the marble solitaire game.
   *
   * @return a board filled with SlotStates
   */
  protected abstract ArrayList<ArrayList<SlotState>> createBoard();


  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false if otherwise
   */
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col < this.getBoardSize(); col++) {

        if (this.getSlotAt(row, col) == SlotState.Marble) {
          if (row < 2) {
            if (this.validMove(row, col, row + 2, col)
                    || this.validMove(row, col, row, col - 2)
                    || this.validMove(row, col, row, col + 2)) {
              return false;
            }

          } else if (row > this.getBoardSize() - 3) {

            if (this.validMove(row, col, row - 2, col)
                    || this.validMove(row, col, row, col - 2)
                    || this.validMove(row, col, row, col + 2)) {
              return false;
            }

          } else if (col < 2) {

            if (this.validMove(row, col, row - 2, col)
                    || this.validMove(row, col, row + 2, col)
                    || this.validMove(row, col, row, col + 2)) {
              return false;
            }

          } else if (col > this.getBoardSize() - 3) {

            if (this.validMove(row, col, row - 2, col)
                    || this.validMove(row, col, row + 2, col)
                    || this.validMove(row, col, row, col - 2)) {
              return false;
            }

          } else {

            if (this.validMove(row, col, row + 2, col)
                    || this.validMove(row, col, row, col - 2)
                    || this.validMove(row, col, row, col + 2)
                    || this.validMove(row, col, row - 2, col)) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Checks if the give (sRow, sCol) is out of the bounds of the board.
   *
   * @param sRow the row of where to search
   * @param sCol the col of where to search
   * @return true if out of the bounds of the board, false if otherwise
   */
  protected abstract boolean outOfBounds(int sRow, int sCol);
}
