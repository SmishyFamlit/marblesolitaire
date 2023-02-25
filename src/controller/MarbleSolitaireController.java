package controller;

/**
 * Represents a controller interface that can play a game of Marble Solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * Play a new game of Marble Solitaire.
   *
   * @throws IllegalArgumentException only if the controller is unable to successfully read input
   *                                  or transmit output
   */
  void playGame() throws IllegalStateException;
}
