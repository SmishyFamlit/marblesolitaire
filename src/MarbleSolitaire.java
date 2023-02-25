import java.io.InputStreamReader;

import controller.MarbleSolitaireController;
import controller.MarbleSolitaireControllerImpl;
import model.hw02.EnglishSolitaireModel;
import model.hw02.MarbleSolitaireModel;
import model.hw04.EuropeanSolitaireModel;
import model.hw04.TriangleSolitaireModel;
import view.TriangleSolitaireTextView;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;

/**
 * Represents a game of Marble Solitaire.
 */
public final class MarbleSolitaire {

  /**
   * The main method where everything will be working together.
   *
   * @param args an Array of arguments
   */
  public static void main(String[] args) {

    MarbleSolitaireModel modelType = null;
    MarbleSolitaireView viewType = null;
    int size = -1;
    int row = -1;
    int col = -1;

    if (args[0].equalsIgnoreCase("english")
            || args[0].equalsIgnoreCase("european")) {
      size = 3;
      row = ((-2 + 2 * size) + size) / 2;
      col = ((-2 + 2 * size) + size) / 2;

    } else {
      size = 5;
      row = 0;
      col = 0;
    }
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-size")) {
        size = Integer.parseInt(args[i + 1]);
        row = ((-2 + 2 * size) + size) / 2;
        col = ((-2 + 2 * size) + size) / 2;
      }

      if (args[i].equalsIgnoreCase("-hole")) {
        row = Integer.parseInt(args[i + 1]) - 1;
        col = Integer.parseInt(args[i + 2]) - 1;
      }

    }

    String boardType = args[0];


    switch (boardType) {
      case "english":
        if (size == -1) {
          modelType = new EnglishSolitaireModel(size, row, col);
        }
        modelType = new EnglishSolitaireModel(size, row, col);
        viewType = new MarbleSolitaireTextView(modelType, System.out);
        break;

      case "european":
        if (size == -1) {
          modelType = new EuropeanSolitaireModel(size, row, col);
        }
        modelType = new EuropeanSolitaireModel(size, row, col);
        viewType = new MarbleSolitaireTextView(modelType, System.out);
        break;

      case "triangular":
        if (size == -1) {
          modelType = new TriangleSolitaireModel(size, row, col);
        }
        modelType = new TriangleSolitaireModel(size, row, col);
        viewType = new TriangleSolitaireTextView(modelType, System.out);
        break;

      default:
        throw new IllegalStateException("No board type found");

    }

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(modelType, viewType,
            new InputStreamReader(System.in));
    controller.playGame();
  }
}
