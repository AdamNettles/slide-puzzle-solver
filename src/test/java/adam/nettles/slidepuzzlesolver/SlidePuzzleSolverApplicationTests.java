package adam.nettles.slidepuzzlesolver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlidePuzzleSolverApplicationTests {

  @Test
  public void contextLoads() {
  }

	@Test
  public void testRandomize() {
    SlidePuzzle slidePuzzle = new SlidePuzzle();
    slidePuzzle.Randomize();
  }

  @Test
  public void testMoves(){

  }
}
