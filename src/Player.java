
public class Player {
	private Card[] hand;
	private int balance;
	private int highScore;
	
	public Player(int balance) {
		this.balance = balance;
		highScore = 0;
	}
	
	public void incrementBalance(int num) {
		this.balance += num;
		if (balance > highScore) {
			highScore = balance;
		}
	}
	
	public void decrementBalance(int num) {
		this.balance -= num;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public void setHand(Card[] hand) {
		this.hand = hand;
	}
	
	public Card[] getHand() {
		return hand.clone();
	}
}
