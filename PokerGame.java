/**
 * Keith's Casino is a game of poker where the player and the computer are given 1 card a turn for 5 turns.
 * There is a pot that 5 of each players bank is put into each turn.
 * When the winner is declared he gets the pot. In a tie the pot is split.
 * It is possible to continue using the same banks in numerous games until either the player or computers bank runs out.
 * The banks can be reset to 1000 from the main menu.
 * @author Keith McGill
 * @version 10/23/12
 */
import java.util.*;

public class PokerGame 
{

	public static void main(String[] args) 
	{
		int playGame = 0, choiceGame, playerBank = 1000, computerBank = 1000, continueGame = 0, choiceBet, countRound = 0, choiceBetNow = 0;;
		int gamePot = 0, numRand = 0, gameWinner = 0, splitPot = 0;;
		while (playGame != 1)
		{
			Scanner scan = new Scanner(System.in);
			Random rand = new Random();
			
			System.out.println("Welcome to Keith's Casino!");
			System.out.println("1. Play Game");
			System.out.println("2. Reset Banks");
			System.out.println("3. Quit");
			System.out.print("Enter your choice: ");
			choiceGame = scan.nextInt();
			
			if (choiceGame == 1)
			{
				Card card = new Card();
				card.Shuffle();
				countRound = 0;
				gamePot = 0;
				continueGame = 0;
				gameWinner = 0;
				while (continueGame != 1)
				{
					if (countRound == 3)
					{
						numRand = rand.nextInt(2);
						if (numRand == 0)
						{
							System.out.println("The computer has quit the game.\n");
							System.out.println("You win!");
							playerBank += gamePot;
							System.out.println("Your bank: " + playerBank);
							System.out.println("Computer bank: " + computerBank);
							System.out.println();
							choiceGame = 1;
							break;
						}
					}
					card.Hand(0, countRound);
					card.Hand(1, countRound);
					computerBank -= 5;
					gamePot += 5;
					System.out.println("\nThe computer has bet.");
					System.out.println("Computer bank: " + computerBank);
					System.out.println("\nYour cards are...");
					for (int i = 0; i <= countRound; i++)
					{
						System.out.println(card.getCard(card.getPlayerSuit(i), card.getPlayerValue(i)));
					}
					System.out.println("\nYour bank: " + playerBank);
					System.out.println("1. Bet");
					System.out.println("2. Quit");
					System.out.print("Choose One: ");
					while (choiceBetNow != 1)
					{
						choiceBet = scan.nextInt();
						if (choiceBet == 1)
						{
							playerBank -= 5;
							gamePot += 5;
							choiceBetNow = 1;
						}
						else if (choiceBet == 2)
						{
							continueGame = 1;
							choiceBetNow = 1;
							System.out.println();
						}
						else
						{
							System.out.println("You have entered an unacceptable response.");
							choiceBetNow = 0;
							System.out.println("\nYour bank: " + playerBank);
							System.out.println("1. Bet");
							System.out.println("2. Quit");
							System.out.print("Choose One: ");
						}
					}
					choiceBetNow = 0;
					if (countRound == 4 || computerBank <= 0 || playerBank <= 0)
					{
						System.out.println("\nThe computers cards are...");
						for (int i = 0; i <= countRound; i++)
						{
							System.out.println(card.getCard(card.getComputerSuit(i), card.getComputerValue(i)));
						}
						System.out.println("\nYour cards are...");
						for (int i = 0; i <= countRound; i++)
						{
							System.out.println(card.getCard(card.getPlayerSuit(i), card.getPlayerValue(i)));
						}
						if (computerBank <= 0)
						{
							System.out.println("The computer has ran out of money. You won!");
						}
						else if (playerBank <= 0)
						{
							System.out.println("You have run out of money. You lose!");
						}
						else
						{
							gameWinner = card.Winner();
							if (gameWinner == 3)
							{
								System.out.println("\nThe game is a tie.");
								splitPot = gamePot / 2;
								playerBank += splitPot;
								computerBank += splitPot;
								System.out.println("Your bank: " + playerBank);
								System.out.println("Computer bank: " + computerBank);
								System.out.println();
							}
							else if (gameWinner == 1)
							{
								System.out.println("\nYou have won!");
								playerBank += gamePot;
								System.out.println("Your bank: " + playerBank);
								System.out.println("Computer bank: " + computerBank);
								System.out.println();
							}
							else if (gameWinner == 2)
							{
								System.out.println("\nThe computer has won!");
								computerBank += gamePot;
								System.out.println("Your bank: " + playerBank);
								System.out.println("Computer bank: " + computerBank);
								System.out.println();
							}
						}
						continueGame = 1;
					}
					countRound++;
				}
			}
			else if (choiceGame == 2)
			{
				computerBank = 1000;
				playerBank = 1000;
				System.out.println("Banks have been reset.\n");
			}
			else if (choiceGame == 3)
			{
				System.out.println("Thank you for playing Keith's Casino!");
				playGame = 1;
			}
			else
			{
				System.out.println("You have entered an unacceptable response.\n");
			}
			
		}
		
		
	}

}
