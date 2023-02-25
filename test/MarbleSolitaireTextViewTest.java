import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.hw02.EnglishSolitaireModel;
import model.hw02.MarbleSolitaireModel;
import model.hw04.EuropeanSolitaireModel;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents test for the Marble Solitaire Text.
 */
public class MarbleSolitaireTextViewTest {
  StringBuilder log;
  MarbleSolitaireModel defaultBoard; //makes a default English game

  MarbleSolitaireView defaultBoardView; //makes the view for the default game
  String defaultViewToString; //default game in string format

  MarbleSolitaireModel defaultEuropean; //makes a default game of european solitaire

  MarbleSolitaireView defaultEuropeanView; //makes view for european solitaire

  String defaultEuropeanViewAsString; //default european game in string format

  @Before
  public void init() {
    log = new StringBuilder();

    defaultBoard = new EnglishSolitaireModel();

    defaultBoardView = new MarbleSolitaireTextView(defaultBoard, this.log);

    defaultViewToString =
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O";

    defaultEuropean = new EuropeanSolitaireModel();

    defaultEuropeanView = new MarbleSolitaireTextView(defaultEuropean);

    defaultEuropeanViewAsString =
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
  }

  //testing the toString to see if it displays the board correctly
  @Test
  public void testToString() {
    assertEquals(this.defaultViewToString, this.defaultBoardView.toString());
  }

  // TODO test the one input view constructor

  //testing passing null as the appendable to the MarbleSolitaireTextView constructor
  @Test
  public void testConstructorNull() {
    try {
      MarbleSolitaireView errorView =
              new MarbleSolitaireTextView(this.defaultBoard, null);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireView errorView =
              new MarbleSolitaireTextView(null, new StringBuilder());
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
  }

  //testing the render method which will render the board to the console
  @Test
  public void testRenderToConsole() throws IOException {
    this.defaultBoardView.renderBoard();
    String renderWithNewLine = this.defaultViewToString;
    assertEquals(renderWithNewLine, this.log.toString());
    this.defaultBoardView.renderMessage("\nScore: " + this.defaultBoard.getScore());
  }

  //Test the toString of the european view
  @Test
  public void testEuropeanToString() {
    assertEquals(defaultEuropeanViewAsString, defaultEuropeanView.toString());
  }

}