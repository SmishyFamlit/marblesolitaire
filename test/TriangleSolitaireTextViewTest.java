import org.junit.Before;
import org.junit.Test;

import model.hw02.MarbleSolitaireModel;
import model.hw04.TriangleSolitaireModel;
import view.MarbleSolitaireView;
import view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents test for the Triangle solitaire view.
 */
public class TriangleSolitaireTextViewTest {
  MarbleSolitaireModel defaultTriangle;

  MarbleSolitaireView defaultTriangleView;

  String defaultTriangleAsString;



  @Before
  public void init() {
    this.defaultTriangle = new TriangleSolitaireModel();

    this.defaultTriangleView = new TriangleSolitaireTextView(this.defaultTriangle);

    this.defaultTriangleAsString = "    _\n" +
                                   "   O O\n" +
                                   "  O O O\n" +
                                   " O O O O\n" +
                                   "O O O O O";
  }

  //test that the constructor arguments are not null
  @Test
  public void testConstructors() {
    try {
      MarbleSolitaireView error = new TriangleSolitaireTextView(null);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireView error = new TriangleSolitaireTextView(null, null);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireView error = new TriangleSolitaireTextView(this.defaultTriangle,
              null);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }
    try {
      MarbleSolitaireView error = new TriangleSolitaireTextView(null,
              System.out);
      fail("IllegalArgumentException not found");
    } catch (IllegalArgumentException e) {
      e.getStackTrace();
    }

    assertEquals(this.defaultTriangleAsString, this.defaultTriangleView.toString());

  }

  //test the toString method
  @Test
  public void testToString() {
    assertEquals(this.defaultTriangleAsString, this.defaultTriangleView.toString());
  }
}