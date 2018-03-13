import java.util.HashSet;
import java.util.Set;

/**
 * The class Dealer encapsulates the actions of a Chancho game-dealer. The game
 * dealer is responsible for dealing cards from the provided game-deck to each
 * player, and scheduling rounds of the game until a player has won the game.
 * All players who have declared themselves as a winner should be congratulated.
 * 
 * Developers should provide the constructor,
 * 
 * public Dealer(Set<Player> players, Deck gameDeck);
 * 
 */
public final class Dealer {

  private Set<Player> players;
  private Deck gameDeck;

  public Dealer(Set<Player> players, Deck gameDeck) {
    this.players = players;
    this.gameDeck = gameDeck;
  }

  private void dealCards() {

    for (Player player : players) {
      for (int i = 0; i < 4; i++) {
        player.addToHand(gameDeck.removeFromTop());
      }
    }
  }

  public void playGame() {
    dealCards();

    boolean needToBreak = false;
    while (true) {
      for (Player player : players) {
        if (player.hasWon()) {
          congratulateWinners();
          needToBreak = true;
        }
      }
      if (needToBreak) {
        break;
      }

      for (Player player : players) {
        player.discard();
      }

      for (Player player : players) {
        player.pickup();
      }

    }
  }

    private void congratulateWinners() {
    for (Player player : players) {
      if (player.hasWon()) {
        System.out.println("The game has been won! Congratulations to:");
        System.out.println(player);
      }
    }
  }
}