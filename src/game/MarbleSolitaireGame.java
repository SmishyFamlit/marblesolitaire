package game;

import java.io.InputStreamReader;

import controller.MarbleSolitaireControllerImpl;
import model.hw02.EnglishSolitaireModel;
import model.hw02.MarbleSolitaireModel;
import view.MarbleSolitaireTextView;

/**
 * Represents a game of Marble Solitaire.
 */
public class MarbleSolitaireGame {

  /**
   * The main method where everything will be working together.
   *
   * @param args an Array of arguments
   */
  public static void main(String[] args) {
    MarbleSolitaireModel defaultBoard = new EnglishSolitaireModel();
    new MarbleSolitaireControllerImpl(defaultBoard, new MarbleSolitaireTextView(defaultBoard),
            new InputStreamReader(System.in)).playGame();
  }
}
