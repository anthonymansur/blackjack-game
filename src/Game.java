import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

	State state;
	Render render;
	Scanner scan;

	public Game(int balance) {
		state = new State(balance);
		render = new Render(state);
		scan = new Scanner(System.in);
	}

	/**
	 * Collects the increment of the bet for the round and updates the state accordingly
	 */
	public void incrementBet() {
		boolean invalidInput = true;
		int increment = 0;
		
		//Repeats user querying until valid input is entered
		while (invalidInput) {
			if (scan.hasNextInt()) {
				increment = scan.nextInt();
				invalidInput = false;
			} else {
				Render.invalidType();
				scan.next();
			}
		}
		state.addToBet(increment);
	}

	/**
	 * Collects the player's next decision
	 *
	 * @return the string representation of the decision
	 */
	public String askDecision() {
		while (true) {
			String next = scan.next();
			if (next.equals("bet") || next.equals("hit") || next.equals("stay")) {
				return next;
			} else {
				Render.invalidType();
			}
		}
	}

	/**
	 * Wait time function used for delaying the dealer's next cards.
	 */
	public void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns whether or not the sum of the player's hand equals to 21.
	 *
	 * @return true if the sum of the player's hand equals to 21, false otherwise.
	 */
	public boolean playerHasBlackJack() {
		return state.getSumOfCards("player")[0] == 21 || state.getSumOfCards("player")[1] == 21;
	}

	/**
	 * Returns whether or not the sum of the player's hand is over 21.
	 *
	 * @return true if the sum of the player's hand is over 21, false otherwise.
	 */
	public boolean playerWentOver() {
		return state.getSumOfCards("player")[0] > 21 && state.getSumOfCards("player")[0] > 21;
	}

	/**
	 * Returns the highest, valid sum of the hand
	 * 
	 * @param  type  the type of user
	 *
	 * @return       the highest, valid sum of the hand.
	 */
	private int finalSum(String type) {
		if (!type.equals("player") && !type.equals("dealer")) {
			throw new Error("Invalid type");
		}
		int firstSum = state.getSumOfCards(type)[0];
		int secondSum = state.getSumOfCards(type)[1];
		int finalSum = firstSum < 21 && secondSum < 21 ? Math.max(firstSum, secondSum)
						: firstSum > 21 ? secondSum : firstSum; // highest sum under 21
		return finalSum;
	}
	
	
	/**
	 * Returns whether or not the sum of the dealer's hand is less than that of the player
	 *
	 * @return true if the sum of the dealer's hand is less than that of the player, false  
	 * 				otherwise
	 */
	public boolean dealersHandIsLessThanPlayers() {
		int playerFinalSum = finalSum("player");
		int dealerFinalSum = finalSum("dealer");
		return dealerFinalSum < playerFinalSum;
	}

	/**
	 * Returns whether or not the dealer lost
	 *
	 * @return true if the sum of the dealer's hand is less than that of the player or greater 
	 * than 21, false otherwise
	 */
	public boolean dealerLost() {
		int dealerFinalSum = finalSum("dealer");
		return dealerFinalSum > 21 || dealersHandIsLessThanPlayers();
	}

	public static void main(String[] args) {
		// instantiate game
		Game game = new Game(100);
		State state = game.state;
		Render render = game.render;
		Scanner scan = new Scanner(System.in);
		
		render.welcome();

		// The game ends when the player runs out of money
		while (state.getPlayerBalance() > 0) {

			state.resetRound();

			// Ask the starting bet that the player wants to start with
			Render.askBet();
			game.incrementBet();

			// Show the card to player and dealer's face-up card
			render.hand("player");
			render.printDealerShowCard();

			/*
			 * If player received a blackjack with the two starting cards, increment their
			 * balance, announce that they won, and restart round
			 */
			if (game.playerHasBlackJack()) {
				state.incrementPlayerBalance();
				render.announceBlackJack();	
			} 
			
			/*
			 * If player went over 21 with the two starting cards, decrement their
			 * balance, announce that they lost, and restart round
			 */
			else if (game.playerWentOver()) {
				
				state.decrementPlayerBalance();
				render.announceLoss();
				
			} 
			
			/*
			 * If the sum of the player's two starting cards is less than 21, continue the round
			 * asking if they want to receive a new card ("hit"), stay with the two cards ("stay"),
			 * or increase their bet ("bet")
			 */
			else {
				
				boolean roundEnded = false;
				
				/*
				 * Begin the round loop that asks for the players actions and, depending on the 
				 * outcome, will then give the dealer new cards until a winner is determined.
				 */
				while (!roundEnded) {
					
					// Get the player's action
					Render.askDecision();
					String nextAction = game.askDecision();
					
					if (nextAction.equals("bet")) {
						
						// Get the player's bet amount
						Render.askBet();
						game.incrementBet();
						render.betAmount();

					} else if (nextAction.equals("hit")) {

						// Give the player a new card and print new hand
						state.addCard("player");
						render.hand("player");
						Render.space();
						
						// Handle's logic if player received blackjack or went over 21
						if (game.playerHasBlackJack()) {
							state.incrementPlayerBalance();
							render.announceBlackJack();
							roundEnded = true;
						} else if (game.playerWentOver()) {
							state.decrementPlayerBalance();
							render.announceLoss();
							roundEnded = true;
						}

					}
					
					// Begin giving dealer cards until he or she beats player or goes over
					else {

						render.hand("dealer");
						game.wait(3);

						while (game.dealersHandIsLessThanPlayers()) {
							state.addCard("dealer");
							render.hand("dealer");
							game.wait(3);
						}
						if (game.dealerLost()) {
							state.incrementPlayerBalance();
							render.announceWin();
						} else {
							state.decrementPlayerBalance();
							render.announceLoss();
						}
						roundEnded = true;
					}
				}
			}
		}
		
		// Display high score once player lost all of their money
		render.highScore();
		scan.close();
	}
}
