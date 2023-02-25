package view;

import model.hw02.MarbleSolitaireModelState;

import static model.hw02.MarbleSolitaireModelState.SlotState.Marble;

/**
 * Represents a game of Triangular Marble Solitaries which is an implementation of a Marble
 * Solitaire game.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructs a Marble Solitaire View where the default appendable is System.out.
   *
   * @param modelState the model of the Marble Solitaire game
   * @throws IllegalArgumentException if the modelState is of type null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState)
          throws IllegalArgumentException {
    super(modelState);
  }

  /**
   * Constructs the MarbleSolitaireTextView with a given modelState and appendable.
   *
   * @param modelState the Marble Solitaire game being passed through
   * @param appendable the appendable being passed through
   * @throws IllegalArgumentException appendable is of type null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable appendable)
          throws IllegalArgumentException {
    super(modelState, appendable);
  }

  /**
   * Turns the board into a visual string representation.
   *
   * @return a String of what the board looks like
   */
  @Override
  public String toString() {

    String boardStateAsString = "";
    for (int row = 0; row < this.modelState.getBoardSize(); row++) {
      for (int col = 0; col <= row; col++) {
        if (col == 0) {
          for (int spaces = 0; spaces < this.modelState.getBoardSize() - row - 1; spaces++) {
            boardStateAsString += " ";
          }
        }

        if (this.modelState.getSlotAt(row, col) == Marble) {
          boardStateAsString += "O ";
        } else {
          boardStateAsString += "_ ";
        }

        if (col == row) {
          boardStateAsString = boardStateAsString.substring(0, boardStateAsString.length() - 1)
                  + "\n";
          col = 0;
          break;
        }
      }
      if (row == this.modelState.getBoardSize() - 1) {
        boardStateAsString = boardStateAsString.substring(0, boardStateAsString.length() - 1);
      }
    }
    return boardStateAsString;
  }
}
