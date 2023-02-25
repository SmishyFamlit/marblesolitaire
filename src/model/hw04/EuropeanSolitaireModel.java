package model.hw04;

import java.util.ArrayList;

import model.hw02.MarbleSolitaireModel;

/**
 * Represents a game of European Solitaire Model.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel implements MarbleSolitaireModel {

  /**
   * Constructs a European Solitaire Model with an octogonal board of side length three and an
   * empty space in the middle.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * Constructs a European Solitaire Model with an octogonal board of side length three and an
   * empty space in (sRow,sCol).
   *
   * @param sRow the row of the empty space
   * @param sCol the col of the empty space
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * Constructs a European Solitaire Model with an octogonal board of side length armThickness and
   * an empty slot in the middle.
   *
   * @param armThickness the side length of the board
   */
  public EuropeanSolitaireModel(int armThickness) {
    super(armThickness);
  }

  /**
   * Constructs a European Solitaire Model with an octogonal board of side length armThickness and
   * an empty slot at (sRow,sCol).
   *
   * @param armThickness the side length of the board
   * @param sRow         the row of the slot
   * @param sCol         the column of the slot
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  /**
   * Creates a board for the Marble Solitaire Game.
   *
   * @return the board filled with marbles and an empty slot
   */
  protected ArrayList<ArrayList<SlotState>> createBoard() {

    ArrayList<ArrayList<SlotState>> board =
            new ArrayList<ArrayList<SlotState>>();

    for (int row = 0; row < this.armThickness * 3 - 2; row++) {
      ArrayList<SlotState> createdRow = new ArrayList<SlotState>();
      for (int col = 0; col < this.armThickness * 3 - 2; col++) {
        if (isInvalidRow(row, col)) {

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

  /**
   * Checks to see if the given (sRow, sCol) are out of the bounds of the board.
   *
   * @param sRow the row of where to search
   * @param sCol the col of where to search
   * @return true if out of the bounds, and false otherwise
   */
  @Override
  protected boolean outOfBounds(int sRow, int sCol) {

    if (sRow < 0 || sCol < 0) {
      return true;
    }
    boolean topLeftBound = sRow < this.armThickness - 1 && sCol < this.armThickness - 1 - sRow;
    boolean topRightBound = sRow < this.armThickness - 1 && sCol > 2 * this.armThickness - 2 + sRow;
    boolean bottomLeftBound = sRow > 2 * this.armThickness - 2
            && sCol < sRow - (2 * this.armThickness) + 2;
    boolean bottomRightBound = sRow > 2 * this.armThickness - 2
            && sCol > 5 * (this.armThickness - 1) - sRow;

    return topLeftBound || topRightBound || bottomLeftBound || bottomRightBound;
  }

  /**
   * Checks to see if the given (sRow, sCol) is an invalid row.
   *
   * @param sRow the row that is being checked for the invalids
   * @param sCol the column that is being checked for the invalids
   * @return true if the board is invalid, false otherwise
   */
  private boolean isInvalidRow(int sRow, int sCol) {

    return (sCol < this.armThickness - 1 - sRow && sRow < this.armThickness - 1)
            || (sRow < this.armThickness - 1 && sCol > 2 * this.armThickness - 2 + sRow)
            || (sRow > 2 * this.armThickness - 2 && sCol < sRow - (2 * this.armThickness) + 2)
            || (sRow > 2 * this.armThickness - 2 && sCol > 5 * (this.armThickness - 1) - sRow);
  }
}
