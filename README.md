Welcome to my implementation of Black Jack. Below, you will find a description 
of the particular rules for the game created, as well as an explanation of 
design choices and data structures used.

How to start:
cd to the java application on terminal. Compile it (i.e. javac Game.java) and then run it
(i.e. java Game).


Certain rules to consider: 
You will start of with $100. After making an initial bet, you and the dealer will 
receive two cards and the dealer will show you their second card. You win by 
getting blackjack or being closer to 21 than the dealer. If you win, you will receive
3/2 times your bet amount, and if you lose, you lose your bet. The deck of cards
is only shuffled after at least 75% of it is used.


Objective:
The purpose is to get the highest amount of money possible before you eventually 
lose it all. 


Design choice: 
This game was implemented using Java in order to take advantage of object-oriented
programming and well as due to high familiarity with the language. There are 6 classes 
(Card, Deck, Game, Player, Render, & State). The Card class is used to instantiate the 
cards that are held together by the Deck class. The Player class holds various data points
of the player, such as their balance, hand, and high score. 

The game is structured using the MVC design pattern. The state of the program is held
in the State class, the logic and main method is held in the Game class, and the console-
based view is held in the Render class.


Data structures: 
An ArrayList was used to keep track of the player's and dealer's hand as well as the 
entire deck itself. This allowed for easy searching and manipulation of a collection 
of cards. 

Since an Ace card can be worth either a one or a ten, an array was used to hold the two
different possible sums that a hand can take.


Algorithms:
Each card is randomly selected from the deck by generating a random number between 0 and 
51. If a card has already been selected, a new number will be generated repeatedly until
a card is selected. This allows for a quick implementation of searching for cards in a
non-biased approach.


Testing:
No testing has been implemented for this game; however, in the future, JUnit testing of 
the methods used and several edge cases of the game can be implemented (such as the ace 
card having two possible values, when the player and dealer have the same sum, and betting
more than your balance). These edge cases were tested briefly by manually running the game.

