import org.junit.Before;
import org.junit.Test;

import model.hw02.MarbleSolitaireModel;
import model.hw04.EuropeanSolitaireModel;
import view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

import static model.hw02.MarbleSolitaireModelState.SlotState.Empty;
import static model.hw02.MarbleSolitaireModelState.SlotState.Invalid;
import static model.hw02.MarbleSolitaireModelState.SlotState.Marble;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Represents a class for testing the European Solitaire Model methods.
 */
public class EuropeanSolitaireModelTest {
  MarbleSolitaireModel defaultEuropeanBoard;
  MarbleSolitaireTextView defaultEuropeanBoardView;

  @Before
  public void init() {
    defaultEuropeanBoard = new EuropeanSolitaireModel();
    defaultEuropeanBoardView = new MarbleSolitaireTextView(defaultEuropeanBoard);
  }

  //test the correct initialization of the default constructor
  @Test
  public void testInitializationDefaultConstructor() {
    MarbleSolitaireModel defaultEuropean = new EuropeanSolitaireModel();
    assertEquals(7, defaultEuropean.getBoardSize());
    assertEquals(Empty, defaultEuropean.getSlotAt(3, 3));
    assertEquals(Marble, defaultEuropean.getSlotAt(1, 1));
    assertEquals(Marble, defaultEuropean.getSlotAt(1, 5));
    assertEquals(Marble, defaultEuropean.getSlotAt(5, 1));
    assertEquals(Marble, defaultEuropean.getSlotAt(5, 5));
  }

  //test the correct and incorrect initialization of the two argument constructor
  @Test
  public void testInitializationTwoArgConstructor() {
    try {
      MarbleSolitaireModel defaultEuropean = new EuropeanSolitaireModel(-1,3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultEuropean = new EuropeanSolitaireModel(1,-3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel defaultEuropean = new EuropeanSolitaireModel(0,0);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel defaultEuropean = new EuropeanSolitaireModel(6,6);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel defaultEuropean = new EuropeanSolitaireModel(9,9);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    MarbleSolitaireModel emptyOneFive = new EuropeanSolitaireModel(1, 5);


    assertEquals(7, emptyOneFive.getBoardSize());
    assertEquals(Empty, emptyOneFive.getSlotAt(1, 5));
    assertEquals(Marble, emptyOneFive.getSlotAt(1, 1));
    assertEquals(Marble, emptyOneFive.getSlotAt(5, 1));
    assertEquals(Marble, emptyOneFive.getSlotAt(5, 5));
  }

  //test the correct and incorrect initialization of the one argument constructor
  @Test
  public void testInitializationOneArgConstructor() {
    try {
      MarbleSolitaireModel defaultEuropean = new EuropeanSolitaireModel(4);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultEuropean = new EuropeanSolitaireModel(-1);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    MarbleSolitaireModel emptyOneFive = new EuropeanSolitaireModel(7);

    assertEquals(19, emptyOneFive.getBoardSize());
    assertEquals(Empty, emptyOneFive.getSlotAt(9, 9));
  }

  //test the correct and incorrect initialization of the three argument constructor
  @Test
  public void testInitializationThreeArgConstructor() {
    try {
      MarbleSolitaireModel defaultEuropean =
              new EuropeanSolitaireModel(4, 3, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultEuropean =
              new EuropeanSolitaireModel(-1, 3, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultEuropean =
              new EuropeanSolitaireModel(5, - 4, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultEuropean =
              new EuropeanSolitaireModel(5, 5, - 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultEuropean =
              new EuropeanSolitaireModel(5, 20, 10);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    MarbleSolitaireModel emptyZeroFour =
            new EuropeanSolitaireModel(5, 0, 4);

    assertEquals(13, emptyZeroFour.getBoardSize());
    assertEquals(Empty, emptyZeroFour.getSlotAt(0, 4));
  }

  //test the move method
  @Test
  public void testMoveMethod() {
    String currentBoardState =
            "    _ _ _\n" +
            "  O _ _ _ O\n" +
            "O _ O _ _ _ O\n" +
            "O _ _ _ O _ _\n" +
            "O _ O _ _ _ O\n" +
            "  O _ _ _ O\n" +
            "    O _ O";
    defaultEuropeanBoard.move(5, 3, 3, 3);
    defaultEuropeanBoard.move(4, 5, 4, 3);
    defaultEuropeanBoard.move(4, 2, 4, 4);

    //test trying to move diagonally
    try {
      defaultEuropeanBoard.move(2, 0, 4, 2);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultEuropeanBoard.move(6, 3, 4, 5);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultEuropeanBoard.move(3, 5, 5, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultEuropeanBoard.move(2, 0, 2, -2);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultEuropeanBoard.move(4, 5, 4, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultEuropeanBoard.move(4, 4, 4, 2);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    //testing isGameOver when it is not
    assertFalse(defaultEuropeanBoard.isGameOver());

    defaultEuropeanBoard.move(2, 3, 4, 3);
    defaultEuropeanBoard.move(4, 4, 4, 2);
    defaultEuropeanBoard.move(2, 5, 2, 3);
    defaultEuropeanBoard.move(1, 3, 3, 3);
    defaultEuropeanBoard.move(2, 1, 2, 3);
    defaultEuropeanBoard.move(0, 2, 2, 2);
    defaultEuropeanBoard.move(0, 4, 0, 2);
    defaultEuropeanBoard.move(3, 3, 1, 3);
    defaultEuropeanBoard.move(1, 4, 1, 2);
    defaultEuropeanBoard.move(6, 4, 4, 4);
    defaultEuropeanBoard.move(4, 1, 4, 3);
    defaultEuropeanBoard.move(4, 4, 4, 2);
    defaultEuropeanBoard.move(3, 1, 3, 3);
    defaultEuropeanBoard.move(3, 4, 3, 2);
    defaultEuropeanBoard.move(3, 6, 3, 4);
    defaultEuropeanBoard.move(6, 2, 6, 4);
    defaultEuropeanBoard.move(4, 2, 6, 2);
    defaultEuropeanBoard.move(2, 2, 4, 2);
    defaultEuropeanBoard.move(0, 2, 2, 2);

    assertTrue(defaultEuropeanBoard.isGameOver());

    assertEquals(currentBoardState, this.defaultEuropeanBoardView.toString());
  }

  //test the getBoardSize method
  @Test
  public void testGetBoardSize() {
    MarbleSolitaireModel size5 = new EuropeanSolitaireModel(5);
    assertEquals(7, this.defaultEuropeanBoard.getBoardSize());
    assertEquals(13, size5.getBoardSize());
  }

  //test the getSlotAt method
  @Test
  public void testGetSlotAt() {
    assertEquals(Marble, this.defaultEuropeanBoard.getSlotAt(0, 2));
    assertEquals(Invalid, this.defaultEuropeanBoard.getSlotAt(0, 1));
    assertEquals(Empty, this.defaultEuropeanBoard.getSlotAt(3, 3));

    try {
      this.defaultEuropeanBoard.getSlotAt(-1, 4);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      this.defaultEuropeanBoard.getSlotAt(4, -1);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      this.defaultEuropeanBoard.getSlotAt(20, 20);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
  }
}