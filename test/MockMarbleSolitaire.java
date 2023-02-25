import model.hw02.MarbleSolitaireModel;

/**
 * Represents a mock class of the MarbleSolitaireModel.
 */
public class MockMarbleSolitaire implements MarbleSolitaireModel {
  private final StringBuilder log;

  /**
   * Constructs a MockMarbleSolitaire to help test inputs.
   *
   * @param log a log of all the inputs being passed through
   */
  public MockMarbleSolitaire(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    fromRow++;
    fromCol++;
    toRow++;
    toCol++;
    this.log.append("move: ").append(fromRow).append(", ")
            .append(fromCol).append(", ").append(toRow).append(", ").append(toCol).append("\n");

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
