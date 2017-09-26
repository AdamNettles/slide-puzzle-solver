package adam.nettles.slidepuzzlesolver;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Piece {

  Position currentPosition;
  Position destination;

  //if true, this is the empty square
  boolean empty;

  public void print(){
    System.out.println(String.format("Destination: %s,%s",this.destination.getX(),this.destination.getY()));
    System.out.println(String.format("empty: %s",this.empty));
  }

}
