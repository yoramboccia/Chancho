import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoPlayer extends AbstractPlayer {


  public AutoPlayer(CardPile left, CardPile right, String name) {
    super(right, left, name);
  }

  @Override
  protected int selectCard() {
    List<Integer> cardSeen = new ArrayList<>();
    cardSeen.add(0);
    cardSeen.add(1);
    cardSeen.add(2);
    cardSeen.add(3);

    for (int i = 0; i < cards.length; i++) {
      int finalI = i;
       cardSeen = cardSeen.stream().filter(index -> !cards[finalI].getRank()
           .equals(cards[index].getRank()) || index == finalI)
           .collect(Collectors.toList());
    }

    Random random = new Random();
    return cardSeen.isEmpty() ? random.nextInt(cards.length)
            : cardSeen.get(random.nextInt(cardSeen.size()));
  }
}