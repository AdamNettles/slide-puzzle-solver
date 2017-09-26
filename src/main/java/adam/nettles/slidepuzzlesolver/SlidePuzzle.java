package adam.nettles.slidepuzzlesolver;

import lombok.*;
import adam.nettles.slidepuzzlesolver.Position;
import adam.nettles.slidepuzzlesolver.Piece;
import java.util.*;

import static adam.nettles.slidepuzzlesolver.Constants.*;
import static adam.nettles.slidepuzzlesolver.Constants.DIRECTIONS.*;

@Data
@NoArgsConstructor
public class SlidePuzzle {

  Piece[][] state;
  Piece emptyPiece;

  /**
   * creates, sets and randomizes the puzzle state
   */
  public void Randomize() {
    this.state = new Piece[DIMENSION_X][DIMENSION_Y];
    Random random = new Random();

    List<Position> availablePositions = new ArrayList<>();
    for (int x = 0; x < DIMENSION_X; x++) {
      for (int y = 0; y < DIMENSION_Y; y++) {
        availablePositions.add(new Position(x,y));
        this.state[x][y] = new Piece();
        this.state[x][y].setCurrentPosition(new Position(x,y));
      }
    }

    //Randomize
    Collections.shuffle(availablePositions);
    this.emptyPiece = this.state[random.nextInt(DIMENSION_X)][random.nextInt(DIMENSION_Y)];
    this.emptyPiece.setEmpty(true);
    Arrays.stream(this.state)
        .forEach(vector -> Arrays.stream(vector)
            .forEach(piece -> {
              piece.setDestination(availablePositions.get(0));
              availablePositions.remove(availablePositions.get(0));
              piece.print();
            })
        );
  }

  /**
   * Move the empty piece in a direction, swapping places with the piece in that position
   * @param direction
   */
  public boolean move(DIRECTIONS direction) {
    int oldY = this.emptyPiece.getCurrentPosition().getY();
    int oldX = this.emptyPiece.getCurrentPosition().getX();
    switch (direction) {
      case DOWN:
        if(oldY > 0) {
          swapPieces(oldX, oldY, oldX, oldY - 1);
          return true;
        } else {
          return false;
        }
      case UP:
        if(oldY < DIMENSION_Y - 1) {
          swapPieces(oldX, oldY, oldX, oldY + 1);
          return true;
        } else {
          return false;
        }
      case LEFT:
        if(oldX > 0) {
          swapPieces(oldX, oldY, oldX - 1, oldY);
          return true;
        } else {
          return false;
        }
      case RIGHT:
        if(oldX < DIMENSION_X - 1) {
          swapPieces(oldX, oldY, oldX + 1, oldY);
          return true;
        } else {
          return false;
        }
      default:
        return false;
    }
  }

  private void swapPieces(int oldX, int oldY, int newX, int newY) {
    this.emptyPiece.getCurrentPosition().setX(newX);
    Piece nonEmptyPiece = this.state[oldX][newX];
    nonEmptyPiece.setCurrentPosition(new Position(oldX, oldY));
    this.state[oldX][newX] = this.emptyPiece;
    this.state[oldX][oldY] = nonEmptyPiece;
  }

}
