import controller.MarbleSolitaireControllerImpl;
import model.hw02.EnglishSolitaireModel;
import model.hw02.MarbleSolitaireModel;
import model.hw04.TriangleSolitaireModel;
import view.MarbleSolitaireTextView;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw04TypeChecks {

  /**
   * Represents the main method of the class.
   *
   * @param args a list of arguments being parsed
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helperMarble(new EnglishSolitaireModel(), rd, ap);

    helperTriangle(new TriangleSolitaireModel(3, 3), rd, ap);
  }

  private static void helperMarble(MarbleSolitaireModel model,
                                   Readable rd, Appendable ap) {
    new MarbleSolitaireControllerImpl(model,
            new MarbleSolitaireTextView(model, ap), rd);
  }

  private static void helperTriangle(MarbleSolitaireModel model,
                                     Readable rd, Appendable ap) {
    new MarbleSolitaireControllerImpl(model,
            new MarbleSolitaireTextView(model, ap), rd);
  }

}
