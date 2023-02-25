package model.hw04;

import java.util.ArrayList;

import model.hw02.MarbleSolitaireModel;

/**
 * Represents a game of Triangle Solitaire Model where you can play a full game.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {
  private final int base;
  private final int emptyRow;
  private final int emptyCol;
  private final ArrayList<ArrayList<SlotState>> board;

  /**
   * Creates a game of Triangular Marble Solitaire where the board has a base of 5 and the
   * empty position is located at (0,0).
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Creates a game of Triangular Marble Solitaire where the board has a customizable base and the
   * empty position is at (0,0).
   *
   * @param base the width of the bottom row of the triangle
   * @throws IllegalArgumentException if the base is less than 0
   */
  public TriangleSolitaireModel(int base) throws IllegalArgumentException {
    this(base, 0, 0);

  }

  /**
   * Creates a game of Triangular Marble Solitaire where the board has a base of 5 and the
   * empty position is at (sRow, sCol).
   *
   * @param sRow the row of the empty position
   * @param sCol the col of the empty position
   * @throws IllegalArgumentException if sRow or sCol are out of the bounds of the board
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(5, sRow, sCol);

  }

  /**
   * Creates a game of Triangular Marble Solitaire where the board has a customizable base and the
   * empty position is at (sRow, sCol).
   *
   * @param base the width of the bottom row of the triangle
   * @param sRow the row of the empty position
   * @param sCol the col of the empty position
   * @throws IllegalArgumentException if sRow or sCol are out of the bounds of the board or base
   *                                  is less than 0.
   */
  public TriangleSolitaireModel(int base, int sRow, int sCol)
          throws IllegalArgumentException {


    if (base <= 1) {
      throw new IllegalArgumentException("Dimensions must be greater than 3");
    }

    this.base = base;

    if (outOfBounds(sRow, sCol) || sCol > sRow) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    this.emptyRow = sRow;
    this.emptyCol = sCol;
    this.board = createBoard();

  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    if (!validMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("This is not a valid move, the from and to positions must "
                                         + "have a marble and an empty space respectively. "
                                         + "The must be exactly two spaces between the from and to "
                                         + "position. You can't move diagonally.");
    }

    ArrayList<SlotState> movingRow = this.board.get(fromRow);
    ArrayList<SlotState> movingCol;
    if (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2) {
      if (toRow < fromRow && toCol < fromCol) {
        movingRow = board.get(toRow);
        movingRow.set(toCol, SlotState.Marble);
        movingRow = board.get(toRow + 1);
        movingRow.set(toCol + 1, SlotState.Empty);
        movingRow = board.get(fromRow);
        movingRow.set(fromCol, SlotState.Empty);
      }

      if (toRow < fromRow && toCol > fromCol) {
        movingRow = board.get(toRow);
        movingRow.set(toCol, SlotState.Marble);
        movingRow = board.get(toRow + 1);
        movingRow.set(toCol - 1, SlotState.Empty);
        movingRow = board.get(fromRow);
        movingRow.set(fromCol, SlotState.Empty);
      }

      if (toRow > fromRow && toCol > fromCol) {
        movingRow = board.get(toRow);
        movingRow.set(toCol, SlotState.Marble);
        movingRow = board.get(toRow - 1);
        movingRow.set(toCol - 1, SlotState.Empty);
        movingRow = board.get(fromRow);
        movingRow.set(fromCol, SlotState.Empty);
      }

      if (toRow > fromRow && toCol < fromCol) {
        movingRow = board.get(toRow);
        movingRow.set(toCol, SlotState.Marble);
        movingRow = board.get(toRow - 1);
        movingRow.set(toCol + 1, SlotState.Empty);
        movingRow = board.get(fromRow);
        movingRow.set(fromCol, SlotState.Empty);
      }
    }
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

  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row++) {
      for (int col = 0; col <= row; col++) {
        try {
          if (validMove(row, col, row + 2, col)) {
            return false;
          }
        } catch (Exception ignored) {
        }

        try {
          if (validMove(row, col, row + 2, col + 2)) {
            return false;
          }
        } catch (Exception ignored) {
        }

        try {
          if (validMove(row, col, row, col + 2)) {
            return false;
          }
        } catch (Exception ignored) {
        }

        try {
          if (validMove(row, col, row - 2, col)) {
            return false;
          }
        } catch (Exception ignored) {
        }

        try {
          if (validMove(row, col, row, col - 2)) {
            return false;
          }
        } catch (Exception ignored) {
        }
      }
    }
    return true;
  }


  @Override
  public int getBoardSize() {
    return this.base;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (outOfBounds(row, col)) {
      throw new IllegalArgumentException("Cannot get the slot at (" + row + ", " + col + ")");
    }

    ArrayList<SlotState> emptyRowPos;
    emptyRowPos = board.get(row);

    return emptyRowPos.get(col);
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < getBoardSize(); row++) {
      for (int col = 0; col <= row; col++) {
        if (getSlotAt(row, col) == SlotState.Marble) {
          score += 1;
        }
      }
    }
    return score;
  }

  private boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (!outOfBounds(fromRow, fromCol) && !outOfBounds(toRow, toCol))
           && (this.getSlotAt(fromRow, fromCol) == SlotState.Marble)
           && (this.getSlotAt(toRow, toCol) == SlotState.Empty)
           && ((fromRow == toRow && Math.abs(fromCol - toCol) == 2)
               || (fromCol == toCol && Math.abs(fromRow - toRow) == 2)
               || (Math.abs(fromCol - toCol) == 2 && Math.abs(fromRow - toRow) == 2))
           && (this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2) == SlotState.Marble);
  }


  private boolean outOfBounds(int sRow, int sCol) {
    if (sRow < 0 || sCol < 0) {
      return true;
    }
    return sRow >= this.base || sCol >= this.base;
  }

  private ArrayList<ArrayList<SlotState>> createBoard() {
    ArrayList<ArrayList<SlotState>> board =
            new ArrayList<ArrayList<SlotState>>();

    for (int row = 0; row < this.base; row++) {
      ArrayList<SlotState> createdRow = new ArrayList<SlotState>();
      for (int col = 0; col < this.base; col++) {
        if (col > row) {
          createdRow.add(SlotState.Invalid);
        } else {
          createdRow.add(SlotState.Marble);
        }

      }
      board.add(createdRow);
    }
    //sets the empty position
    ArrayList<SlotState> emptyRowPos;
    emptyRowPos = board.get(this.emptyRow);
    emptyRowPos.set(this.emptyCol, SlotState.Empty);

    return board;
  }
}
