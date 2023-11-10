package com.bda.websocket;

public class Game {

	  private Slot[][] board;
	  private Slot currentTurn = Slot.EXXES;
	  private String message;

	  public Game() {
	    restartGame();
	    message = currentTurn + " turn.";
	  }

	  public void play(Move move) {
	    String tempMessage = "";
	    if (move.getPlayer() != null && move.getPlayer() == currentTurn && board[move.getX()][move.getY()] == Slot.NONE) {
	      board[move.getX()][move.getY()] = move.getPlayer();
	      currentTurn = currentTurn == Slot.EXXES ? Slot.CIRCLE : Slot.EXXES;
	      Slot winner = winner();
	      if (winner != Slot.NONE) {
	        if (winner == Slot.TIE) {
	          tempMessage += "TIE!... Starting a new game.";
	        } else {
	          tempMessage += winner + " won!... Starting a new game.";
	        }
	        restartGame();
	      }
	    } else {
	      tempMessage += "invalid move";
	    }
	    tempMessage += currentTurn + " turn.";
	    this.message = tempMessage;
	  }

	  public Slot winner() {
	    int count = 0;
	    for (int i = 0; i < 3; i++) {
	      count += board[i][0] == Slot.NONE ? 1 : 0;
	      count += board[i][1] == Slot.NONE ? 1 : 0;
	      count += board[i][2] == Slot.NONE ? 1 : 0;
	      if (equal(board[i][0], board[i][1], board[i][2])) {
	        return board[i][0];
	      }
	      if (equal(board[0][i], board[1][i], board[2][i])) {
	        return board[0][i];
	      }
	    }
	    if (count == 0) {
	      return Slot.TIE;
	    }
	    if (equal(board[0][0], board[1][1], board[2][2])) {
	      return board[1][1];
	    }
	    if (equal(board[0][2], board[1][1], board[2][0])) {
	      return board[1][1];
	    }
	    return Slot.NONE;
	  }

	  private void restartGame() {
	    board = new Slot[3][3];
	    for (int i = 0; i < board.length; i++) {
	      for (int j = 0; j < board[i].length; j++) {
	        board[i][j] = Slot.NONE;
	      }
	    }
	  }

	  private boolean equal(Slot slot1, Slot slot2, Slot slot3) {
	    if (slot1 == Slot.NONE) {
	      return false;
	    }
	    return slot1 == slot2 && slot2 == slot3;
	  }

	  public Slot[][] getBoard() {
	    return board;
	  }

	  public Slot getCurrentTurn() {
	    return currentTurn;
	  }

	  public String getMessage() {
	    return message;
	  }
	}
