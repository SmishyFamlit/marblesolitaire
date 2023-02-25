package view;

import model.hw02.MarbleSolitaireModelState;

import static model.hw02.MarbleSolitaireModelState.SlotState.Empty;
import static model.hw02.MarbleSolitaireModelState.SlotState.Invalid;
import static model.hw02.MarbleSolitaireModelState.SlotState.Marble;

/**
 * Represents a view for a game of Marble Solitaire.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView {

  /**
   * Constructs a Marble Solitaire View where the default appendable is System.out.
   *
   * @param modelState the model of the Marble Solitaire game
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState) {
    super(modelState);
  }

  /**
   * Constructs the MarbleSolitaireTextView with a given modelState and appendable.
   *
   * @param modelState the Marble Solitaire game being passed through
   * @param appendable the appendable being passed through
   * @throws IllegalArgumentException if {@code app} is of type null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable appendable)
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
    int armThickness = 0;
    for (int rowArmThicc = 0; rowArmThicc < 1; rowArmThicc++) {
      for (int colArmThicc = 0; colArmThicc < this.modelState.getBoardSize(); colArmThicc++) {
        if (this.modelState.getSlotAt(rowArmThicc, colArmThicc) == Marble
                || this.modelState.getSlotAt(rowArmThicc, colArmThicc) == Empty) {
          armThickness++;
        }
      }
    }
    String boardStateAsString = "";
    int marbleCount = 0;
    for (int row = 0; row < this.modelState.getBoardSize(); row++) {
      for (int col = 0; col < this.modelState.getBoardSize(); col++) {
        if (this.modelState.getSlotAt(row, col) == Marble) {
          marbleCount += 1;
          boardStateAsString += "O ";
        } else if (this.modelState.getSlotAt(row, col) == Empty) {
          marbleCount += 1;
          boardStateAsString += "_ ";
        } else {
          boardStateAsString += "  ";
        }

        if (col == (2 * armThickness - 2) + ((marbleCount - armThickness) / 2)) {
          if (this.modelState.getBoardSize() == col + 1
                  || this.modelState.getSlotAt(row, col + 1) == Invalid) {
            boardStateAsString = boardStateAsString.substring(0, boardStateAsString.length() - 1)
                    + "\n";
            col = 0;
            marbleCount = 0;
            break;
          }
        }
      }

      if (row == this.modelState.getBoardSize() - 1) {
        boardStateAsString = boardStateAsString.substring(0, boardStateAsString.length() - 1);
      }
    }
    return boardStateAsString;
  }
}