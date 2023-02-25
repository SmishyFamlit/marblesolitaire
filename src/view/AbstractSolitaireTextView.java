package view;

import java.io.IOException;

import model.hw02.MarbleSolitaireModelState;

/**
 * Represents an Abstract Solitaire view to reduce code duplication.
 */
public abstract class AbstractSolitaireTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState modelState;
  protected final Appendable output;

  /**
   * Constructs a Marble Solitaire View where the default appendable is System.out.
   *
   * @param modelState the model of the Marble Solitaire game
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState modelState) {
    if (modelState == null) {
      throw new IllegalArgumentException("Model can't be of type null");
    }
    this.modelState = modelState;
    this.output = System.out;
  }

  /**
   * Constructs the MarbleSolitaireTextView with a given modelState and appendable.
   *
   * @param modelState the Marble Solitaire game being passed through
   * @param appendable the appendable being passed through
   * @throws IllegalArgumentException if {@code app} is of type null
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState modelState, Appendable appendable)
          throws IllegalArgumentException {
    if (modelState == null || appendable == null) {
      throw new IllegalArgumentException("Inputs cannot be of type null");
    }
    this.modelState = modelState;
    this.output = appendable;
  }

  /**
   * Turns the board into a visual string representation.
   *
   * @return a String of what the board looks like
   */
  public abstract String toString();

  /**
   * Renders the board to the output that was given.
   *
   * @throws IOException if there is an issue outputting
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      this.output.append(this.toString());
    } catch (IOException e) {
      throw new IOException();
    }
  }

  /**
   * Renders a message to be displayed to the output given.
   *
   * @param message the message to be transmitted
   * @throws IOException if there is an issue outputting
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IOException();
    }
  }
}
