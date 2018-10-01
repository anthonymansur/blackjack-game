import java.util.ArrayList;
import java.util.List;

public class State {
	private final Deck deck;
	private final Player player;
	private int roundBet;
	List<Card> playerHand;
	List<Card> dealerHand;
	
	public State(int balance) {
		deck = new Deck();
		player = new Player(balance);
		initializeHands();
		roundBet = 0;
	}
	
	private void initializeHands() {
		playerHand = new ArrayList<Card>();
		playerHand.add(deck.getRandomCard());
		playerHand.add(deck.getRandomCard());
		dealerHand = new ArrayList<Card>();
		dealerHand.add(deck.getRandomCard());
		dealerHand.add(deck.getRandomCard());
	}
	
	public int[] getSumOfCards(String type) {
		List<Card> hand;
		if (type.equals("player")) {
			hand = playerHand;
		} else if (type.equals("dealer")) {
			hand = dealerHand;
		} else {
			throw new Error("Invalid Type");
		}
		int[] sum = {0,0};
		boolean aceHasBeenAdded = false;
		for (Card c : hand) {
			if (c.rank.name().equals("ACE") && !aceHasBeenAdded) { // check 
				sum[0] += 1;
				sum[1] += 10;
				aceHasBeenAdded = true;
			} else {
				sum[0] += c.rank.getAction();
				sum[1] += c.rank.getAction();
			}
		}
		return sum;
	}
	
	public int getBetAmount() {
		return roundBet;
	}
	
	public void addToBet(int num) {
		if (num < player.getBalance() - roundBet) {
			roundBet += num;
		}
		else {
			roundBet = player.getBalance();
		}
	}
	
	public void resetRound() {
		initializeHands();
		roundBet = 0;
		if (deck.remainingCards() <= 13) {
			deck.shuffle();
			System.out.println("");
			System.out.println("Shuffling Cards");
			System.out.println("");
		}
	}
	
	public int getPlayerBalance() {
		return player.getBalance();
	}
	
	public Card getCard(String type, int index) {
		List<Card> hand;
		if (type.equals("player")) {
			hand = playerHand;
		} else if (type.equals("dealer")) {
			hand = dealerHand;
		} else {
			throw new Error("Invalid Type");
		}
		return new Card(hand.get(index).rank, hand.get(index).suit);
	}
	
	public void addCard(String type) {
		List<Card> hand;
		if (type.equals("player")) {
			hand = playerHand;
		} else if (type.equals("dealer")) {
			hand = dealerHand;
		} else {
			throw new Error("Invalid Type");
		}
		hand.add(deck.getRandomCard());
	}
	
	public int getHandSize(String type) {
		if (type.equals("player")) {
			return playerHand.size();
		} else if (type.equals("dealer")) {
			return dealerHand.size();
		} else {
			throw new Error("Invalid Type");
		}
	}
	
	public void incrementPlayerBalance() {
		player.incrementBalance((int) (roundBet * (3.0/2)));
	}
	
	public void decrementPlayerBalance() {
		player.decrementBalance(roundBet);
	}
	
	public int getPlayerHighScore() {
		return player.getHighScore();
	}
	
}
