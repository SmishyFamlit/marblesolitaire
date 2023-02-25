import org.junit.Before;
import org.junit.Test;

import model.hw02.MarbleSolitaireModel;
import model.hw04.TriangleSolitaireModel;

import static model.hw02.MarbleSolitaireModelState.SlotState.Empty;
import static model.hw02.MarbleSolitaireModelState.SlotState.Invalid;
import static model.hw02.MarbleSolitaireModelState.SlotState.Marble;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Represents test for the model of the triangle solitaire model.
 */
public class TriangleSolitaireModelTest {

  MarbleSolitaireModel triangelDefault;

  @Before
  public void init() {
    this.triangelDefault = new TriangleSolitaireModel();
  }

  //test the correct initialization of the default constructor
  @Test
  public void testInitializationDefaultConstructor() {
    assertEquals(5, this.triangelDefault.getBoardSize());
    assertEquals(Empty, this.triangelDefault.getSlotAt(0, 0));
    assertEquals(Invalid, this.triangelDefault.getSlotAt(0, 1));
    assertEquals(Invalid, this.triangelDefault.getSlotAt(0, 4));
    assertEquals(Marble, this.triangelDefault.getSlotAt(4, 1));
    assertEquals(Marble, this.triangelDefault.getSlotAt(4, 4));
  }

  //test the correct and incorrect initialization of the two argument constructor
  @Test
  public void testInitializationTwoArgConstructor() {
    try {
      MarbleSolitaireModel defaultTriangle = new TriangleSolitaireModel(-1,3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultTriangle = new TriangleSolitaireModel(1,-3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel defaultTriangle = new TriangleSolitaireModel(6,6);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      MarbleSolitaireModel defaultTriangle = new TriangleSolitaireModel(9,9);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    MarbleSolitaireModel emptyOneFive = new TriangleSolitaireModel(4, 3);


    assertEquals(5, emptyOneFive.getBoardSize());
    assertEquals(Empty, emptyOneFive.getSlotAt(4, 3));
    assertEquals(Marble, emptyOneFive.getSlotAt(1, 1));
    assertEquals(Marble, emptyOneFive.getSlotAt(4, 1));
    assertEquals(Marble, emptyOneFive.getSlotAt(4, 4));
  }

  //test the correct and incorrect initialization of the one argument constructor
  @Test
  public void testInitializationOneArgConstructor() {
    try {
      MarbleSolitaireModel defaultTriangle = new TriangleSolitaireModel(1);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultTriangle = new TriangleSolitaireModel(-1);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    MarbleSolitaireModel emptyOneFive = new TriangleSolitaireModel(7);

    assertEquals(7, emptyOneFive.getBoardSize());
    assertEquals(Empty, emptyOneFive.getSlotAt(0, 0));
  }

  //test the correct and incorrect initialization of the three argument constructor
  @Test
  public void testInitializationThreeArgConstructor() {
    try {
      MarbleSolitaireModel defaultTriangle =
              new TriangleSolitaireModel(4, 3, 4);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultTriangle =
              new TriangleSolitaireModel(-1, 3, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultTriangle =
              new TriangleSolitaireModel(5, - 4, 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultTriangle =
              new TriangleSolitaireModel(5, 5, - 3);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireModel defaultTriangle =
              new TriangleSolitaireModel(5, 20, 10);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    MarbleSolitaireModel emptyZeroFour =
            new TriangleSolitaireModel(20, 4, 0);

    assertEquals(20, emptyZeroFour.getBoardSize());
    assertEquals(Empty, emptyZeroFour.getSlotAt(4, 0));
  }

  //testing the move method
  @Test
  public void testMove() {
    MarbleSolitaireModel size3 = new TriangleSolitaireModel(3);

    try {
      size3.move(-1, 4, 56, 2);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    //straight up
    try {
      size3.move(2, 1, 0, 0);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    try {
      size3.move(2, 1, 0, 0);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    assertEquals(5, size3.getScore());
    assertFalse(size3.isGameOver());


    size3.move(2, 2, 0,0);
    assertEquals(4, size3.getScore());
    size3.move(2, 0, 2,2);
    size3.move(0, 0, 2,0);

    assertTrue(size3.isGameOver());
  }

}