//don't have to worry about checkstyle for test cases
// do command a then command i to reformat code

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyAgentTest {

  Connect4Game game;



  @Before
  public void setUp() throws Exception {
    game = new Connect4Game(7, 6);
  }

  @Test
  public void testICanWinVerticallySimple() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 3; i++) {
      redAgent.moveOnColumn(1);
      yellowAgent.moveOnColumn(2);
    }

    assertEquals(redAgent.iCanWin(), 1);

  }

  //Tests you can win vertically in the top 4
  @Test
  public void testICanWinVerticallyTop4() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 2; i++) {
      redAgent.moveOnColumn(1);
      yellowAgent.moveOnColumn(2);
    }

    for (int i = 0; i < 3; i++) {
      redAgent.moveOnColumn(2);
      yellowAgent.moveOnColumn(1);   
    }

    assertEquals(redAgent.iCanWin(), 2);

  }

  //Tests you can win diagonally
  @Test
  public void testICanWinDiagonally() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();

    redAgent.moveOnColumn(1);
    yellowAgent.moveOnColumn(2);
    redAgent.moveOnColumn(2);
    yellowAgent.moveOnColumn(3);
    redAgent.moveOnColumn(4);
    yellowAgent.moveOnColumn(3);
    redAgent.moveOnColumn(3);
    yellowAgent.moveOnColumn(1);
    redAgent.moveOnColumn(4);
    yellowAgent.moveOnColumn(4);
    redAgent.moveOnColumn(4); 

    assertEquals(redAgent.iCanWin(), 4);

  }

  //Tests you can win horizontally
  @Test
  public void testICanWinHorizontally() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 1; i < 4; i++) {
      redAgent.moveOnColumn(i);
      yellowAgent.moveOnColumn(i);
    }

    assertEquals(redAgent.iCanWin(), 4);

  }

  //Tests you can win horizontally near the top
  @Test
  public void testICanWinHorizontally2() {
    MyAgent redAgent = new MyAgent(game,true);
    MyAgent yellowAgent = new MyAgent(game,false);
    game.clearBoard();
    for (int i = 1; i < 3; i++) {

      for (int j = 1; j < 3; j++) {
        redAgent.moveOnColumn(j);
        yellowAgent.moveOnColumn(j);
      }
      for (int k = 3; k < 5; k++) {
        yellowAgent.moveOnColumn(k);
        redAgent.moveOnColumn(k);
      }
    }

    for (int i = 1; i < 4; i++) {
      redAgent.moveOnColumn(i);
      yellowAgent.moveOnColumn(i);
    }

    assertEquals(redAgent.iCanWin(), 4);
  }

  //Tests they can win
  @Test
  public void testTheyCanWin() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 3; i++) {
      redAgent.moveOnColumn(1);
      yellowAgent.moveOnColumn(2);
    }

    assertEquals(redAgent.theyCanWin(), 2);
  }

  // NEWLY CODED BY US
  //Tests they can win horizontally
  @Test
  public void testTheyCanWinHorizontally() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 1; i <4; i++) {
      redAgent.moveOnColumn(i);
      yellowAgent.moveOnColumn(i);
    }

    assertEquals(redAgent.theyCanWin(), 4);
  }

  //Tests they can win diagonally
  @Test
  public void testTheyCanWinDiagonally() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    redAgent.moveOnColumn(3);
    yellowAgent.moveOnColumn(4);
    redAgent.moveOnColumn(4);
    yellowAgent.moveOnColumn(1);
    for (int i = 1; i < 5; i++) {
      yellowAgent.moveOnColumn(i);
      redAgent.moveOnColumn(i+1);
    }

    assertEquals(redAgent.theyCanWin(), 4);
  }

  // Tests you can win against a Beginner agent as Red
  @Test
  public void testRedWinningBeginnerAgent() {
    Agent redAgent = new MyAgent(game, true);
    Agent yellowAgent = new BeginnerAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'R') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Red against Beginner");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }

  // Tests you can win against a Beginner agent as Yellow
  @Test
  public void testYellowWinningBeginnerAgent() {
    Agent redAgent = new BeginnerAgent(game, true);
    Agent yellowAgent = new MyAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Beginner");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }

  // Tests you can win against a Random agent as Red
  @Test
  public void testRedWinningRandomAgent() {
    Agent redAgent = new MyAgent(game, true);
    Agent yellowAgent = new RandomAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'R') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Red against Random");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }

  //Tests you can win against a Random agent as Red
  @Test
  public void testYellowWinningRandomAgent() {
    Agent redAgent = new RandomAgent(game, true);
    Agent yellowAgent = new MyAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Random");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }

  // OUR BONUS CODE...


  // Tests you can win against IntermediateAgent as yellow
  @Test
  public void testYellowWinningIntermediateAgent() {
    Agent redAgent = new IntermediateAgent(game,true);
    Agent yellowAgent = new MyAgent(game,false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard();
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }
      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Intermediate Agent");
    assertTrue(numberOfWins >= 45);
  }

  // Tests you can win against IntermediateAgent as red
  @Test
  public void testRedWinningIntermediateAgent() {
    Agent redAgent = new MyAgent(game,true);
    Agent yellowAgent = new IntermediateAgent(game,false); 
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard();
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }
      if (game.gameWon() == 'R') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Red against Intermediate Agent");
    assertTrue(numberOfWins >= 45);
  }

  // SUPER BONUS TODO: Write testCases to playAgainst AdvancedAgent
  @Test

  public void testRedWinningAdvancedAgent() {
    Agent redAgent = new MyAgent(game,true);
    Agent yellowAgent = new AdvancedAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i<50; i++) {
      game.clearBoard();
      while(!game.boardFull() && game.gameWon() == 'N') { 
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }
    }
    if (game.gameWon() == 'R') {
      numberOfWins++;
    }
    System.out.println("You won: " + numberOfWins + "games as Red against AdvancedAgent");
    assertTrue(numberOfWins >= 45);
  }

  @Test
  public void testYellowWinningAdvancedAgent() {
    Agent redAgent = new AdvancedAgent(game, true);
    Agent yellowAgent = new MyAgent(game,false); 
    int numberOfWins = 0;
    for (int i = 0; i<50; i++) {
      game.clearBoard();
      while(!game.boardFull() && game.gameWon() == 'N') { 
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }
    }
    if (game.gameWon() == 'Y') {
      numberOfWins++;
    }
    System.out.println("You won: " + numberOfWins + "games as Red against AdvancedAgent");
    assertTrue(numberOfWins >= 45);
  }

  // SUPER BONUS TODO: Write testCases to playAgainst BrilliantAgent

  @Test
  public void testYellowWinningBrilliantAgent() {
    Agent redAgent = new BrilliantAgent(game, true);
    Agent yellowAgent = new MyAgent(game,false); 
    int numberOfWins = 0;
    for (int i = 0; i<50; i++) {
      game.clearBoard();
      while(!game.boardFull() && game.gameWon() == 'N') { 
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }
    }
    if (game.gameWon() == 'Y') {
      numberOfWins++;
    }
    System.out.println("You won: " + numberOfWins + "games as Red against BrilliantAgent");
    assertTrue(numberOfWins >= 45);
  }

@Test
public void testRedWinningBrilliantAgent() {
  Agent redAgent = new MyAgent(game,true);
  Agent yellowAgent = new BrilliantAgent(game, false);
  int numberOfWins = 0;
  for (int i = 0; i<50; i++) {
    game.clearBoard();
    while(!game.boardFull() && game.gameWon() == 'N') { 
      redAgent.move();
      if (game.gameWon() != 'R') {
        yellowAgent.move();
      }
    }
  }
  if (game.gameWon() == 'R') {
    numberOfWins++;
  }
  System.out.println("You won: " + numberOfWins + "games as Red against BrilliantAgent");
  assertTrue(numberOfWins >= 45);
}

}