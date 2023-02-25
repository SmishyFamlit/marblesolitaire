import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.hw02.EnglishSolitaireModel;
import model.hw02.MarbleSolitaireModel;
import model.hw02.MarbleSolitaireModelState;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;

import static model.hw02.MarbleSolitaireModelState.SlotState.Empty;
import static model.hw02.MarbleSolitaireModelState.SlotState.Invalid;
import static model.hw02.MarbleSolitaireModelState.SlotState.Marble;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test the EnglishSolitaireModel methods.
 */
public class EnglishSolitaireModelTest {
  MarbleSolitaireModel defaultBoard; //makes a default English game
  MarbleSolitaireModel size11; //makes an English model game of thickness 11
  MarbleSolitaireModel threeArgs; //makes English model of size 5 with empty slot at (5,4)
  ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> board; //creates a board of default size
  ArrayList<MarbleSolitaireModelState.SlotState> firstRow; //first row of the board
  ArrayList<MarbleSolitaireModelState.SlotState> secondRow; //second row of the board
  ArrayList<MarbleSolitaireModelState.SlotState> thirdRow; //third row of the board
  ArrayList<MarbleSolitaireModelState.SlotState> forthRow; //fourth row of the board
  ArrayList<MarbleSolitaireModelState.SlotState> fifthRow; //fifth row of the board
  ArrayList<MarbleSolitaireModelState.SlotState> sixthRow; //sixth row of the board
  ArrayList<MarbleSolitaireModelState.SlotState> seventhRow; //seventh row of the board


  MarbleSolitaireView defaultBoardView; //makes the view for the default game
  MarbleSolitaireView size11View; //makes the view for the thickness 11 game
  String defaultViewToString; //default game in string format
  String defaultViewToStringUpdated1; //default game in string format after a move
  String defaultViewToStringUpdated2; //default game in string format after a move
  String defaultViewToStringUpdated3; //default game in string format after a move


  @Before
  public void init() {
    defaultBoard = new EnglishSolitaireModel();

    threeArgs = new EnglishSolitaireModel(5, 5, 4);

    defaultBoardView = new MarbleSolitaireTextView(defaultBoard);

    defaultViewToString = "    O O O\n    O O O\n"
                          + "O O O O O O O\nO O O _ O O O\nO O O O O O O\n    O O O"
                          + "\n    O O O";

    defaultViewToStringUpdated1 = "    O O O\n    O O O\n"
                                  + "O O O O O O O\nO O O O O O O\nO O O _ O O O\n    O _ O"
                                  + "\n    O O O";

    defaultViewToStringUpdated2 = "    O O O\n    O O O\n"
                                  + "O O O O O O O\nO O O O O O O\nO O O O _ _ O\n    O _ O"
                                  + "\n    O O O";

    defaultViewToStringUpdated3 = "    O O O\n    O O O\n"
                                  + "O O O O O O O\nO O O O O O O\nO O _ _ O _ O\n    O _ O"
                                  + "\n    O O O";
    size11 = new EnglishSolitaireModel(11);

    size11View = new MarbleSolitaireTextView((size11));

    board = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>(Arrays.asList(
            firstRow, secondRow, thirdRow, forthRow, fifthRow, sixthRow, seventhRow));

    firstRow = new ArrayList<>(Arrays.asList(Invalid, Invalid, Marble, Marble, Marble,
            Invalid, Invalid));
    secondRow = new ArrayList<>(Arrays.asList(Invalid, Invalid, Marble, Marble, Marble,
            Invalid, Invalid));
    thirdRow = new ArrayList<>(Arrays.asList(Marble, Marble, Marble, Marble, Marble,
            Marble, Marble));
    forthRow = new ArrayList<>(Arrays.asList(Marble, Marble, Marble, Empty,
            Marble, Marble, Marble));
    fifthRow = new ArrayList<>(Arrays.asList(Marble, Marble, Marble, Marble, Marble,
            Marble, Marble));
    sixthRow = new ArrayList<>(Arrays.asList(Invalid, Invalid, Marble, Marble,
            Marble, Invalid, Invalid));
    seventhRow = new ArrayList<>(Arrays.asList(Invalid, Invalid, Marble,
            Marble, Marble, Invalid, Invalid));
    board = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>(Arrays.asList(firstRow,
            secondRow, thirdRow, forthRow, fifthRow, sixthRow, seventhRow));
  }


  //testing the valid initialization of a traditional Marble Solitaire game
  @Test
  public void testValidInitializationNoArguments() {
    assertEquals(7, defaultBoard.getBoardSize());
    assertEquals(Empty, defaultBoard.getSlotAt(3, 3));
  }

  //testing the valid initialization of the constructor of three arguments
  @Test
  public void testValidInitializationThreeArguments() {
    MarbleSolitaireModel size9 = new EnglishSolitaireModel(9, 8, 8);
    assertEquals(13, threeArgs.getBoardSize());
    assertEquals(Empty, threeArgs.getSlotAt(5, 4));
    assertEquals(25, size9.getBoardSize());
    assertEquals(Empty, size9.getSlotAt(8, 8));
    assertEquals(Marble, size9.getSlotAt(12, 12));
  }

  //testing the invalid positions of an empty row for three argument constructor
  @Test
  public void testInvalidPositionThreeArguments() {
    try {
      MarbleSolitaireModel invalidPosn = new EnglishSolitaireModel(3, 1, 0);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel invalidPosn2 = new EnglishSolitaireModel(5, -1, 0);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel invalidPosn3 = new EnglishSolitaireModel(3, 3, -3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
  }

  //testing the constructor with three arguments that throws an IllegalArgumentException when
  //armThickness is not a positive odd number
  @Test
  public void testThreeArgumentsPositiveOddNumber() {
    try {
      MarbleSolitaireModel invalidArmThickness =
              new EnglishSolitaireModel(2, 2, 2);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel invalidArmThickness2 =
              new EnglishSolitaireModel(8, 8, 8);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel invalidArmThickness3 =
              new EnglishSolitaireModel(-8, 8, 8);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
  }

  //testing the valid initialization of a Marble Solitaire game of different arm thickness
  @Test
  public void testValidInitializationDiffSize() {
    assertEquals(31, size11.getBoardSize());
  }

  // testing the invalid of the constructor that takes in the row and col of the empty position
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationInvalidPosn1() {
    MarbleSolitaireModel exEngSolModelError = new EnglishSolitaireModel(0, 1);
  }

  // testing the invalid of the constructor that takes in the row and col of the empty position
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationInvalidPosn2() {
    MarbleSolitaireModel exEngSolModelError = new EnglishSolitaireModel(5, 5);
  }

  // testing the invalid of the constructor that takes in the row and col of the empty position
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationInvalidPosn3() {
    MarbleSolitaireModel exEngSolModelError = new EnglishSolitaireModel(1, 5);
  }

  // testing the invalid of the constructor that takes in the row and col of the empty position
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationInvalidPosn4() {
    MarbleSolitaireModel exEngSolModelError = new EnglishSolitaireModel(6, 1);
  }

  // testing the invalid of the constructor that takes in the row and col of the empty position
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationInvalidPosn5() {
    MarbleSolitaireModel exEngSolModelError = new EnglishSolitaireModel(-5, 1);
  }

  // testing the invalid of the constructor that takes in the row and col of the empty position
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationInvalidPosn6() {
    MarbleSolitaireModel exEngSolModelError = new EnglishSolitaireModel(5, -1);
  }

  // testing the invalid of the constructor that takes in the row and col of the empty position
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitializationInvalidPosn7() {
    MarbleSolitaireModel exEngSolModelError = new EnglishSolitaireModel(0, 5);
  }

  // testing the invalid use of getSlotAt when inputting a (row,col) that is not on the board
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt1() {
    defaultBoard.getSlotAt(-1, 3);
  }

  // testing the invalid use of getSlotAt when inputting a (row,col) that is not on the board
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt2() {
    defaultBoard.getSlotAt(7, 3);
  }

  // testing the invalid use of getSlotAt when inputting a (row,col) that is not on the board
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt3() {
    defaultBoard.getSlotAt(3, -1);
  }

  // testing the invalid use of getSlotAt when inputting a (row,col) that is not on the board
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt4() {
    defaultBoard.getSlotAt(3, 7);
  }

  // testing getSlotAt and seeing if it returns the correct thing
  @Test
  public void testGetSlotAt() {
    assertEquals(Invalid, defaultBoard.getSlotAt(0, 0));
    assertEquals(Marble, defaultBoard.getSlotAt(1, 3));
    assertEquals(Empty, defaultBoard.getSlotAt(3, 3));
  }


  // testing the move to see if it is moving the marbles correctly
  @Test
  public void testMove() {
    //test moving normally with a normal default game
    assertEquals(this.defaultViewToString, this.defaultBoardView.toString());
    defaultBoard.move(5, 3, 3, 3);
    assertEquals(defaultViewToStringUpdated1, this.defaultBoardView.toString());
    defaultBoard.move(4, 5, 4, 3);
    assertEquals(defaultViewToStringUpdated2, this.defaultBoardView.toString());
    defaultBoard.move(4, 2, 4, 4);
    assertEquals(defaultViewToStringUpdated3, this.defaultBoardView.toString());

    //test trying to move diagonally
    try {
      defaultBoard.move(2, 0, 4, 2);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultBoard.move(6, 3, 4, 5);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultBoard.move(3, 5, 5, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultBoard.move(2, 0, 2, -2);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultBoard.move(4, 5, 4, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      defaultBoard.move(4, 4, 4, 2);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    //testing isGameOver when it is not
    assertFalse(defaultBoard.isGameOver());

    defaultBoard.move(2, 3, 4, 3);
    defaultBoard.move(4, 4, 4, 2);
    defaultBoard.move(2, 5, 2, 3);
    defaultBoard.move(1, 3, 3, 3);
    defaultBoard.move(2, 1, 2, 3);
    defaultBoard.move(0, 2, 2, 2);
    defaultBoard.move(0, 4, 0, 2);
    defaultBoard.move(3, 3, 1, 3);
    defaultBoard.move(1, 4, 1, 2);
    defaultBoard.move(6, 4, 4, 4);
    defaultBoard.move(4, 1, 4, 3);
    defaultBoard.move(4, 4, 4, 2);
    defaultBoard.move(3, 1, 3, 3);
    defaultBoard.move(3, 4, 3, 2);
    defaultBoard.move(3, 6, 3, 4);
    defaultBoard.move(6, 2, 6, 4);
    defaultBoard.move(4, 2, 6, 2);
    defaultBoard.move(2, 2, 4, 2);
    defaultBoard.move(0, 2, 2, 2);

    assertTrue(defaultBoard.isGameOver());

  }

  // testing trying to move an empty space to a
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    this.defaultBoard.move(3, 3, 3, 5);
  }

  // testing trying to move a marble 3 spots over
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove2() {
    this.defaultBoard.move(3, 6, 3, 3);
  }

  @Test
  public void testGetScore() {
    assertEquals(32, this.defaultBoard.getScore());
    defaultBoard.move(5, 3, 3, 3);
    assertEquals(31, this.defaultBoard.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveEmptySlot() {
    this.defaultBoard.move(3, 3, 3, 5);
  }

}