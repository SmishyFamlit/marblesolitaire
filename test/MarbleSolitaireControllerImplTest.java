import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;

import controller.MarbleSolitaireController;
import controller.MarbleSolitaireControllerImpl;
import model.hw02.EnglishSolitaireModel;
import model.hw02.MarbleSolitaireModel;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents test for the controller implementation class.
 */
public class MarbleSolitaireControllerImplTest {
  MarbleSolitaireModel defaultBoard;
  MarbleSolitaireView defaultBoardView;
  StringBuilder log;
  MarbleSolitaireModel mock;
  MarbleSolitaireView mockView;
  Readable in;
  MarbleSolitaireController controller;

  //initializes what we need for testing
  @Before
  public void init() {
    defaultBoard = new EnglishSolitaireModel();
    defaultBoardView = new MarbleSolitaireTextView(defaultBoard);
    log = new StringBuilder();
    mock = new MockMarbleSolitaire(log);
    mockView = new MarbleSolitaireTextView(mock, new StringBuilder());
    in = new StringReader("3 4 -4 A 6 8 q");
    controller = new MarbleSolitaireControllerImpl(mock, mockView, in);
  }

  //test skipping invalid inputs and passing it through the scanner using a mock of the model
  @Test
  public void testModelInputs() {
    controller.playGame();
    assertEquals("move: 3, 4, -4, 6\n", log.toString());

    StringBuilder otherLog = new StringBuilder();
    Readable in = new StringReader("A B C D E F G 1 2 3 4 q");
    MarbleSolitaireModel otherMock = new MockMarbleSolitaire(otherLog);
    MarbleSolitaireView otherView = new MarbleSolitaireTextView(this.mock, new StringBuilder());
    MarbleSolitaireController otherController = new MarbleSolitaireControllerImpl(otherMock,
            otherView, in);
    otherController.playGame();

    assertEquals("move: 1, 2, 3, 4\n", otherLog.toString());
  }

  //testing the quitting sequence
  @Test
  public void testQuittingSequence() {
    String answer = "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n";
    Readable in = new StringReader("q 1 2 3");
    Appendable out = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();

    assertEquals(answer, out.toString());
    Readable in1 = new StringReader("1 q 3 4");
    Appendable out1 = new StringBuilder();
    MarbleSolitaireModel model1 = new EnglishSolitaireModel();
    MarbleSolitaireView view1 = new MarbleSolitaireTextView(model1, out1);
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(model1, view1, in1);
    controller1.playGame();

    assertEquals(answer, out1.toString());

  }


  //testing to see if any of the inputs of the controller constructor are null
  @Test
  public void testControllerIsNotNull() {
    try {
      MarbleSolitaireController errorController = new MarbleSolitaireControllerImpl(
              this.defaultBoard, this.defaultBoardView, null);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireController errorController = new MarbleSolitaireControllerImpl(
              this.defaultBoard, null, this.in);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireController errorController = new MarbleSolitaireControllerImpl(
              null, this.defaultBoardView, this.in);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
  }

  //testing to see if IllegalStateException is thrown when there are no inputs but game is not over
  @Test
  public void testIllegalStateGameNotOverNoInputs() {
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView defaultView = new MarbleSolitaireTextView(this.defaultBoard, out);
    Readable in = new StringReader("2 4 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(this.defaultBoard,
            defaultView, in);
    try {
      controller.playGame();
      fail("IllegalStateException not found");
    } catch (IllegalStateException e) {
      e.getStackTrace();
    }
  }

  //test that if you give 4 values, and they are right make move and render board
  @Test
  public void testValidMoveAndRender() {
    String newOutput = "    O O O\n" +
                       "    O O O\n" +
                       "O O O O O O O\n" +
                       "O O O _ O O O\n" +
                       "O O O O O O O\n" +
                       "    O O O\n" +
                       "    O O O\n" +
                       "Score: 32\n" +
                       "    O O O\n" +
                       "    O _ O\n" +
                       "O O O _ O O O\n" +
                       "O O O O O O O\n" +
                       "O O O O O O O\n" +
                       "    O O O\n" +
                       "    O O O\n" +
                       "Score: 31\n" +
                       "Game quit!\n" +
                       "State of game when quit:\n" +
                       "    O O O\n" +
                       "    O _ O\n" +
                       "O O O _ O O O\n" +
                       "O O O O O O O\n" +
                       "O O O O O O O\n" +
                       "    O O O\n" +
                       "    O O O\n" +
                       "Score: 31\n";
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView defaultView = new MarbleSolitaireTextView(this.defaultBoard, out);
    Readable in = new StringReader("2 4 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(this.defaultBoard,
            defaultView, in);
    controller.playGame();
    assertEquals(newOutput, out.toString());
  }

  //testing IOException with the readable
  @Test
  public void testIOExceptionWithReadable() {
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView defaultView = new MarbleSolitaireTextView(this.defaultBoard, out);
    Readable in = new MockReadable();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(this.defaultBoard,
            defaultView, in);

    try {
      controller.playGame();
      fail("IllegalStateException not found");
    } catch (IllegalStateException e) {
      e.getStackTrace();
    }

  }

  //testing IOException with the Appendable
  @Test
  public void testIOEExceptionWithAppendable() {
    Appendable out = new MockAppendable();
    MarbleSolitaireView defaultView = new MarbleSolitaireTextView(this.defaultBoard, out);
    Readable in = new StringReader("2 4 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(this.defaultBoard,
            defaultView, in);

    try {
      controller.playGame();
      fail("IllegalStateException not found");
    } catch (IllegalStateException e) {
      e.getStackTrace();
    }
  }

  //testing getting IOException
  @Test
  public void testIOExceptionInGeneral() {
    Readable in = new MockReadable();
    Appendable out = new MockAppendable();

    try {
      in.read(CharBuffer.wrap("new"));
      fail("IOException not found");
    } catch (IOException e) {
      e.getStackTrace();
    }

    try {
      out.append(CharBuffer.wrap("new"));
    } catch (IOException e) {
      e.getStackTrace();
    }
  }

  //testing the game over scene
  @Test
  public void testGameOver() {
    Readable in = new StringReader("6 4 4 4 5 6 5 4 5 3 5 5 3 4 5 4 5 5 5 3 3 6 3 4 2 4 4 4 3" +
                                   " 2 3 4 1 3 3 3 1 5 1 3 4 4 2 4 2 5 2 3 7 5 5 5 5 2 5 4 5 5 5" +
                                   " 3 4 2 4 4 4 5 4 3 4 7 4 5 7 3 7 5 5 3 7 3 3 3 5 3 1 3 3 3");
    Appendable out = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
    controller.playGame();

    assertEquals(true,out.toString().contains("Game over!"));
  }

  //testing the invalid move play again
  @Test
  public void testPlayAgainIfInvalidMove() {
    Readable in = new StringReader("1 1 1 1 q");
    StringBuilder out = new StringBuilder();
    MarbleSolitaireView defaultView = new MarbleSolitaireTextView(this.defaultBoard, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(this.defaultBoard,
            defaultView, in);
    controller.playGame();

    assertEquals(true, out.toString().contains("Invalid move. Play again."));
  }
}