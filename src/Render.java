
public class Render {
	
	private State state;
	
	public Render(State state) {
		this.state = state;
	}
	
	public static void space() {
		System.out.println("");
	}

	public static void invalidType() {
		System.out.println("");
		System.out.println("You have entered an invalid input. Try again.");
	}
	
	public static void askDecision() {
		System.out.println("");
		System.out.print("Do you want to \"bet\" or \"hit\" or \"stay\": ");
	}
	
	public static void askBet() {
		System.out.print("Enter bet: ");
	}
	
	public void betAmount() {
		System.out.println("");
		System.out.println("Current bet amount: " + state.getBetAmount());
		System.out.println("");
	}
	
	public void welcome() {
		System.out.println("Welcome player! You have $" + state.getPlayerBalance() + " in your pocket. Let's start a game of "
						+ "good ol' fashion black jack! Shall we begin with a starting bet?");
	}
	
	public void hand(String type) {
		if (!type.equals("player") && !type.equals("dealer")) {
			throw new Error("Invalid Type");
		}
		System.out.println("");
		System.out.println(type.equals("player") ? "Your hand:" : "Dealer's hand:");
		for (int i = 0; i < state.getHandSize(type); i++) {
			if (i == 0) {
				System.out.print("[ " + state.getCard(type, i).rank.name() + 
								(state.getHandSize(type) > 1 ? (", ") : "")); 
			} else if (i == state.getHandSize(type) - 1) {
				System.out.print(state.getCard(type, i).rank.name() + " ]"); 
			} else {
				System.out.print(state.getCard(type, i).rank.name() + ", ");
			}
		}
		System.out.println("");
	}
	
	public void printDealerShowCard() {
		System.out.println("");
		System.out.println("Dealer's faceup card:");
		System.out.print("[ " + state.getCard("dealer", 1).rank.name() + " ]");
		System.out.println("");
	}
	
	public void announceBlackJack() {
		System.out.println("");
		System.out.println("Black Jack! Good job!");
		System.out.println("");
		System.out.println("Current Balance: " + state.getPlayerBalance());
	}
	
	public void announceWin() {
		System.out.println("");
		System.out.println("You won!");
		System.out.println("");
		System.out.println("Current Balance: " + state.getPlayerBalance());
	}
	
	public void announceLoss() {
		System.out.println("");
		System.out.println("Ouch! You've lost this round");
		System.out.println("");
		System.out.println("Current Balance: " + state.getPlayerBalance());
	}
	
	public void highScore() {
		System.out.println("");
		System.out.println("Highscore: " + state.getPlayerHighScore());
		System.out.println("");
	}
	
}
