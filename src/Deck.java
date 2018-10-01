import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	List<Card> deck = new ArrayList<Card>(52);
	
	public Deck() {
		for (Card.ranks rank : Card.ranks.values()) {
			for (Card.suits suit : Card.suits.values()) {
				deck.add(new Card(rank, suit));
			}
		}
	}
	
	// check if easier way to implement
	public Card getRandomCard() {
		if (deck.size() == 0) { return null; }
		int index = (int) Math.round(Math.random() * 52);
		Card c = deck.get(index);
		while (c == null) {
			index = (int) Math.round(Math.random() * 52);
			c = deck.get(index);
		}
		deck.set(index, null);
		return new Card(c.rank, c.suit);
	}
	
	public void shuffle() {
		deck = new ArrayList<Card>(52);
		for (Card.ranks rank : Card.ranks.values()) {
			for (Card.suits suit : Card.suits.values()) {
				deck.add(new Card(rank, suit));
			}
		}
	}
	
	public int remainingCards() {
		int output = 0;
		for (int i = 0; i < deck.size(); i++) {
			if (deck.get(i) != null) {
				output++;
			}
		} return output;
	}

	
}
