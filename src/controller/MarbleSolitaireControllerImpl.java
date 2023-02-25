package controller;

import java.io.IOException;
import java.util.Scanner;

import model.hw02.MarbleSolitaireModel;
import view.MarbleSolitaireView;

/**
 * Represents an implementation of the MarbleSolitaireController
 * for a game of Marble Solitaire.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;

  /**
   * Constructs a controller.
   *
   * @param model the model that the controller will be using
   * @param view  the view that the controller will be using
   * @param input the input that the controller will be reading from
   * @throws IllegalArgumentException if anything passed in is of type null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Values can't be null");

    }

    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(this.input);


    while (!this.model.isGameOver()) {
      renderAndThrowBoard();
      renderAndThrowMessage("Score: " + this.model.getScore());


      int countValid = 0;
      int[] validTracker = new int[4];
      while (countValid < 4) {
        if (!sc.hasNext()) {
          throw new IllegalStateException();
        }
        String userInput = sc.next();
        try {
          validTracker[countValid] = Integer.parseInt(userInput);
          countValid++;
        } catch (NumberFormatException e) {
          if (userInput.equalsIgnoreCase("q")) {
            renderAndThrowMessage("Game quit!\nState of game when quit:");
            renderAndThrowBoard();
            renderAndThrowMessage("Score: " + this.model.getScore());
            return;
          }
        }
      }


      int fromRow = validTracker[0] - 1;
      int fromCol = validTracker[1] - 1;
      int toRow = validTracker[2] - 1;
      int toCol = validTracker[3] - 1;

      try {
        this.model.move(fromRow, fromCol, toRow, toCol);
      } catch (IllegalArgumentException e) {
        renderAndThrowMessage("Invalid move. Play again.");
      }


      if (this.model.isGameOver()) {
        renderAndThrowMessage("Game over!");
        renderAndThrowBoard();
        renderAndThrowMessage("Score: " + this.model.getScore());
      }
    }
  }

  /**
   * Renders the board and catches IOExceptions if there are any thrown.
   */
  private void renderAndThrowBoard() {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException("This can't be done");
    }
  }

  /**
   * Renders a messages to display and catches any IOExceptions if there are any thrown.
   *
   * @param message the message being rendered
   */
  private void renderAndThrowMessage(String message) {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("This can't be done");
    }
  }
}