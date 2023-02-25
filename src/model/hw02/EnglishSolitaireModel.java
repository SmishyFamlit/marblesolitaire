package model.hw02;

import java.util.ArrayList;

import model.hw04.AbstractSolitaireModel;

/**
 * Represents an implementation of the game Marble Solitaire that is the English version of Marble
 * Solitaire where the board is plus shaped.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * A constructor that takes in nothing and build the default game of English Marble Solitaire.
   */
  public EnglishSolitaireModel() {
    super();
  }

  /**
   * Represents a traditional sized game of English Marble Solitaire but with a customized
   * location for the empty slot.
   *
   * @param sRow the row where the empty slot will be placed
   * @param sCol the column where the empty slot will be placed
   * @throws IllegalArgumentException when empty row or col are less than zero or not a valid
   *                                  location on the board
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol);


  }

  /**
   * Represents a game of English Marble Solitaire where the arm thickness of the board is
   * customizable.
   *
   * @param armThickness The thickness of how long the arms should be starting at 3
   * @throws IllegalArgumentException if armThickness is not a positive odd number, or it is less
   *                                  than one
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness);
  }


  /**
   * Represents an English Marble Solitaire game where the arm thickness of the board is
   * customizable and where the empty slot is placed is also customizable.
   *
   * @param armThickness The thickness of how long the arms should be starting at 3
   * @param sRow         the row where the empty slot will be placed
   * @param sCol         the column where the empty slot will be placed
   * @throws IllegalArgumentException if armThickness is not an odd positive number greater than
   *                                  1 or if the sRow or sCol position are less than zero or
   *                                  not valid spots on the board
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);


  }

  /**
   * Creates the board for the marble solitaire game.
   *
   * @return a board filled with SlotStates
   */
  protected ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> createBoard() {

    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> board =
            new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();

    for (int i = 0; i < this.armThickness - 1; i++) {
      board.add(this.createInvalidRow());
    }

    for (int i = 0; i < this.armThickness; i++) {
      board.add(this.createMiddleRow());
    }

    for (int i = 0; i < this.armThickness - 1; i++) {
      board.add(this.createInvalidRow());
    }

    //sets the empty position
    ArrayList<MarbleSolitaireModelState.SlotState> emptyRowPos;
    emptyRowPos = board.get(this.emptyRow);
    emptyRowPos.set(this.emptyCol, MarbleSolitaireModelState.SlotState.Empty);

    return board;
  }

  private ArrayList<MarbleSolitaireModelState.SlotState> createMiddleRow() {
    ArrayList<MarbleSolitaireModelState.SlotState> middleRows = new ArrayList<>();
    for (int createRows = 0; createRows < (3 * this.armThickness - 2); createRows++) {
      middleRows.add(MarbleSolitaireModelState.SlotState.Marble);
    }
    return middleRows;
  }

  /**
   * Helps creat the invalid top rows of the board and the invalid bottom rows of the board.
   *
   * @return the invalid rows
   */
  protected ArrayList<MarbleSolitaireModelState.SlotState> createInvalidRow() {
    ArrayList<MarbleSolitaireModelState.SlotState> topSectionRows = new ArrayList<>();

    for (int i = 0; i < this.armThickness - 1; i++) {
      topSectionRows.add(MarbleSolitaireModelState.SlotState.Invalid);
    }

    for (int creatRows = 0; creatRows < this.armThickness; creatRows++) {
      topSectionRows.add(MarbleSolitaireModelState.SlotState.Marble);
    }

    for (int i = 0; i < this.armThickness - 1; i++) {
      topSectionRows.add(MarbleSolitaireModelState.SlotState.Invalid);
    }

    return topSectionRows;
  }

  /**
   * Checks if the position at (row,col) are in the bounds of the board.
   *
   * @param row the row of the SlotState
   * @param col the column of the SlotState
   * @return true if out of bound, false otherwise
   */
  protected boolean outOfBounds(int row, int col) {
    int firstBound = (this.getBoardSize() / 2) - (this.armThickness / 2);
    int secondBound = (this.getBoardSize() / 2) + (this.armThickness / 2);

    if ((row < 0 || col < 0) || (row > this.getBoardSize() || col > this.getBoardSize())) {
      return true;
    } else if (row < firstBound && col > secondBound) {
      return true;
    } else if (row > secondBound && col < firstBound) {
      return true;
    } else if (row < firstBound && col < firstBound) {
      return true;
    } else {
      return row > secondBound && col > secondBound;
    }
  }


}