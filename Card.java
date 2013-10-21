/**
 * This contains all the methods that are used in PokerGame.java.
 * @author Keith McGill
 * @version 10/23/12
 */
import java.util.*;

public class Card 
{
	final int HEART = 1;
	final int SPADE = 2;
	final int DIAMOND = 3;
	final int CLUB = 4;
	
	final int JACK = 11;
	final int QUEEN = 12;
	final int KING = 13;
	final int ACE = 1;
	
	public int[][] cardArray = new int[52][2];
	public int[][] playerHand = new int[5][2];
	public int[][] computerHand = new int[5][2];
	public int[] playerStraight = new int[5];
	public int[] computerStraight = new int[5];
	public int deckSpot = 0;
	Random rand = new Random();
	/**
	 * This builds the array of the deck of cards.
	 * @constructor This is the constructor of the class.
	 */
	public Card()
	{
		int countValue = 1, countSuit = 1;
		for (int i = 0; i < 52; i++)
		{
			if (countValue == 14)
			{
				countValue = 1;
			}
			if (countSuit == 5)
			{
				countSuit = 1;
			}
			cardArray[i][0] = countValue;
			cardArray[i][1] = countSuit;
			countValue++;
			countSuit++;
		}
	}
	/**
	 * This shuffles the deck of cards.
	 */
	public void Shuffle()
	{
		for (int i = 0; i < 1000000; i++)
		{
			int numRandFirst, numRandSecond;
			int[][] tempSpot = new int[2][2];
			numRandFirst = rand.nextInt(52);
			numRandSecond = rand.nextInt(52);
			tempSpot[0][0] = cardArray[numRandFirst][0];
			tempSpot[1][1] = cardArray[numRandFirst][1];
			cardArray[numRandFirst][0] = cardArray[numRandSecond][0];
			cardArray[numRandFirst][1] = cardArray[numRandSecond][1];
			cardArray[numRandSecond][0] = tempSpot[0][0];
			cardArray[numRandSecond][1] = tempSpot[1][1];
			
		}
	}
	/**
	 * This gives either the player or the computer a new card from the deck.
	 * @param numWho This number determines if the player or computer gets the card.
	 * @param numCard This determines which card is being given for there hand.
	 */
	public void Hand(int numWho, int numCard)
	{
		if (numWho == 0)
		{
			playerHand[numCard][0] = cardArray[deckSpot][0];
			playerHand[numCard][1] = cardArray[deckSpot][1];
			deckSpot++;
		}
		if (numWho == 1)
		{
			computerHand[numCard][0] = cardArray[deckSpot][0];
			computerHand[numCard][1] = cardArray[deckSpot][1];
			deckSpot++;
		}
	}
	/**
	 * This computes the possible scenarios of results for both the player and computers hand.
	 * It then chooses between the cards of both to pick who has the better hand.
	 * @return This returns 1 for player 2 for computer and 3 for tie.
	 */
	public int Winner()
	{
		int playerFlush = 0, computerFlush = 0, playerStr = 0, computerStr = 0, playerRoyalFlush = 0, computerRoyalFlush = 0;
		int playerStrFlush = 0, computerStrFlush = 0, playerFour = 0, computerFour = 0, playerFullHouse = 0, computerFullHouse = 0;
		int playerThree = 0, computerThree = 0, playerTwoPair = 0, computerTwoPair = 0, playerPair = 0, computerPair = 0;
		int playerHighCard = 0, computerHighCard = 0, playerHighPairCard = 0, computerHighPairCard = 0;
		int playerHighTwoPairCard = 0, computerHighTwoPairCard = 0;
		
		if (((playerHand[0][1] == 1 && playerHand[1][1] == 1 && playerHand[2][1] == 1 && playerHand[3][1] == 1 && playerHand[4][1] == 1) ||
				(playerHand[0][1] == 2 && playerHand[1][1] == 2 && playerHand[2][1] == 2 && playerHand[3][1] == 2 && playerHand[4][1] == 2) ||
				(playerHand[0][1] == 3 && playerHand[1][1] == 3 && playerHand[2][1] == 3 && playerHand[3][1] == 3 && playerHand[4][1] == 3) ||
				(playerHand[0][1] == 4 && playerHand[1][1] == 4 && playerHand[2][1] == 4 && playerHand[3][1] == 4 && playerHand[4][1] == 4)))
		{
			playerFlush = 1;
		}
		if (((computerHand[0][1] == 1 && computerHand[1][1] == 1 && computerHand[2][1] == 1 && computerHand[3][1] == 1 && computerHand[4][1] == 1) ||
				(computerHand[0][1] == 2 && computerHand[1][1] == 2 && computerHand[2][1] == 2 && computerHand[3][1] == 2 && computerHand[4][1] == 2) ||
				(computerHand[0][1] == 3 && computerHand[1][1] == 3 && computerHand[2][1] == 3 && computerHand[3][1] == 3 && computerHand[4][1] == 3) ||
				(computerHand[0][1] == 4 && computerHand[1][1] == 4 && computerHand[2][1] == 4 && computerHand[3][1] == 4 && computerHand[4][1] == 4)))
		{
			computerFlush = 1;
		}
		for (int i = 0; i < 5; i++)
		{
			playerStraight[i] = playerHand[i][0];
			computerStraight[i] = computerHand[i][0];
		}
		Arrays.sort(playerStraight);
		Arrays.sort(computerStraight);
		if (playerStraight[1] == (playerStraight[0] + 1) && playerStraight[2] == (playerStraight[0] + 2) && playerStraight[3] == (playerStraight[0] + 3) &&
				playerStraight[4] == (playerStraight[0] + 4))
		{
			playerStr = 1;
		}
		if (computerStraight[1] == (computerStraight[0] + 1) && computerStraight[2] == (computerStraight[0] + 2) && computerStraight[3] == (computerStraight[0] + 3) &&
				computerStraight[4] == (computerStraight[0] + 4))
		{
			computerStr = 1;
		}
		if (playerStraight[0] == 10 && playerStr == 1 && playerFlush == 1)
		{
			playerRoyalFlush = 1;
		}
		if (computerStraight[0] == 10 && computerStr == 1 && computerFlush == 1)
		{
			computerRoyalFlush = 1;
		}
		if (playerStr == 1 && playerFlush == 1)
		{
			playerStrFlush = 1;
		}
		if (computerStr == 1 && computerFlush == 1)
		{
			computerStrFlush = 1;
		}
		if ((playerStraight[0] == playerStraight[1] && playerStraight[1] == playerStraight[2] && playerStraight[2] == playerStraight[3]) ||
				(playerStraight[4] == playerStraight[3] && playerStraight[3] == playerStraight[2] && playerStraight[2] == playerStraight[1]))
		{
			playerFour = 1;
		}
		if ((computerStraight[0] == computerStraight[1] && computerStraight[1] == computerStraight[2] && computerStraight[2] == computerStraight[3]) ||
				(computerStraight[4] == computerStraight[3] && computerStraight[3] == computerStraight[2] && computerStraight[2] == computerStraight[1]))
		{
			computerFour = 1;
		}
		if ((playerStraight[0] == playerStraight[1] && playerStraight[1] == playerStraight[2] && playerStraight[3] == playerStraight[4]) ||
				(playerStraight[4] == playerStraight[3] && playerStraight[3] == playerStraight[2] && playerStraight[1] == playerStraight[0]))
		{
			playerFullHouse = 1;
		}
		if ((computerStraight[0] == computerStraight[1] && computerStraight[1] == computerStraight[2] && computerStraight[3] == computerStraight[4]) ||
				(computerStraight[4] == computerStraight[3] && computerStraight[3] == computerStraight[2] && computerStraight[1] == computerStraight[0]))
		{
			computerFullHouse = 1;
		}
		if ((playerStraight[0] == playerStraight[1] && playerStraight[1] == playerStraight[2]) ||
				(playerStraight[4] == playerStraight[3] && playerStraight[3] == playerStraight[2]))
		{
			playerThree = 1;
		}
		if ((computerStraight[0] == computerStraight[1] && computerStraight[1] == computerStraight[2]) ||
				(computerStraight[4] == computerStraight[3] && computerStraight[3] == computerStraight[2]))
		{
			computerThree = 1;
		}
		
		if (playerStraight[0] == playerStraight[1] && playerStraight[2] == playerStraight[3])
		{
			if (playerStraight[0] > playerStraight[3])
			{
				playerHighTwoPairCard = playerStraight[0];
			}
			else
			{
				playerHighTwoPairCard = playerStraight[3];
			}
			playerTwoPair = 1;
		}
		if (playerStraight[0] == playerStraight[1] && playerStraight[3] == playerStraight[4])
		{
			if (playerStraight[0] > playerStraight[3])
			{
				playerHighTwoPairCard = playerStraight[0];
			}
			else
			{
				playerHighTwoPairCard = playerStraight[3];
			}
			playerTwoPair = 1;
		}
		if (playerStraight[1] == playerStraight[2] && playerStraight[3] == playerStraight[4])
		{
			if (playerStraight[1] > playerStraight[3])
			{
				playerHighTwoPairCard = playerStraight[1];
			}
			else
			{
				playerHighTwoPairCard = playerStraight[3];
			}
			playerTwoPair = 1;
		}
		
		if (computerStraight[0] == computerStraight[1] && computerStraight[2] == computerStraight[3])
		{
			if (computerStraight[0] > computerStraight[3])
			{
				computerHighTwoPairCard = computerStraight[0];
			}
			else
			{
				computerHighTwoPairCard = computerStraight[3];
			}
			computerTwoPair = 1;
		}
		if (computerStraight[0] == computerStraight[1] && computerStraight[3] == computerStraight[4])
		{
			if (computerStraight[0] > computerStraight[3])
			{
				computerHighTwoPairCard = computerStraight[0];
			}
			else
			{
				computerHighTwoPairCard = computerStraight[3];
			}
			computerTwoPair = 1;
		}
		if (computerStraight[1] == computerStraight[2] && computerStraight[3] == computerStraight[4])
		{
			if (computerStraight[1] > computerStraight[3])
			{
				computerHighTwoPairCard = computerStraight[1];
			}
			else
			{
				computerHighTwoPairCard = computerStraight[3];
			}
			computerTwoPair = 1;
		}
		
		if (playerStraight[0] == playerStraight[1])
		{
			playerHighPairCard = playerStraight[1];
			playerPair = 1;
		}
		if (playerStraight[1] == playerStraight[2])
		{
			playerHighPairCard = playerStraight[2];
			playerPair = 1;
		}
		if (playerStraight[2] == playerStraight[3])
		{
			playerHighPairCard = playerStraight[3];
			playerPair = 1;
		}
		if (playerStraight[3] == playerStraight[4])
		{
			playerHighPairCard = playerStraight[4];
			playerPair = 1;
		}
		
		if (computerStraight[0] == computerStraight[1])
		{
			computerHighPairCard = computerStraight[1];
			computerPair = 1;
		}
		if (computerStraight[1] == computerStraight[2])
		{
			computerHighPairCard = computerStraight[2];
			computerPair = 1;
		}
		if (computerStraight[2] == computerStraight[3])
		{
			computerHighPairCard = computerStraight[3];
			computerPair = 1;
		}
		if (computerStraight[3] == computerStraight[4])
		{
			computerHighPairCard = computerStraight[4];
			computerPair = 1;
		}
		
		for (int i = 0; i < 5; i++)
		{
			if (playerHighCard < playerStraight[i])
			{
				playerHighCard = playerStraight[i];
			}
		}
		for (int i = 0; i < 5; i++)
		{
			if (computerHighCard < computerStraight[i])
			{
				computerHighCard = computerStraight[i];
			}
		}
		if (playerRoyalFlush == 1 && computerRoyalFlush == 1)
			return 3;
		else if (playerRoyalFlush == 1)
			return 1;
		else if (computerRoyalFlush == 1)
			return 2;
		
		else if (playerStrFlush == 1 && computerStrFlush == 1)
		{
			if (playerStraight[4] == computerStraight[4])
				return 3;
			else if (playerStraight[4] > computerStraight[4])
				return 1;
			else
				return 2;
		}
		else if (playerStrFlush == 1)
			return 1;
		else if (computerStrFlush == 1)
			return 2;
		
		else if (playerFour == 1 && computerFour == 1)
		{
			if (playerStraight[2] == computerStraight[2])
				return 3;
			else if (playerStraight[2] > computerStraight[2])
				return 1;
			else
				return 2;
		}
		else if (playerFour == 1)
			return 1;
		else if (computerFour == 1)
			return 2;
		
		else if (playerFullHouse == 1 && computerFullHouse == 1)
		{
			if (playerStraight[2] == computerStraight[2])
				return 3;
			else if (playerStraight[2] > computerStraight[2])
				return 1;
			else
				return 2;
		}
		else if (playerFullHouse == 1)
			return 1;
		else if (computerFullHouse == 1)
			return 2;
		
		else if (playerFlush == 1 && computerFlush == 1)
		{
			if (playerHighCard == computerHighCard)
				return 3;
			else if (playerHighCard > computerHighCard)
				return 1;
			else
				return 2;
		}
		else if (playerFlush == 1)
			return 1;
		else if (computerFlush == 1)
			return 2;
		
		else if (playerStr == 1 && computerStr == 1)
		{
			if (playerStraight[4] == computerStraight[4])
				return 3;
			else if (playerStraight[4] > computerStraight[4])
				return 1;
			else
				return 2;
		}
		else if (playerStr == 1)
			return 1;
		else if (computerStr == 1)
			return 2;
		
		else if (playerThree == 1 && computerThree == 1)
		{
			if (playerStraight[2] == computerStraight[2])
				return 3;
			else if (playerStraight[2] > computerStraight[2])
				return 1;
			else
				return 2;
		}
		else if (playerThree == 1)
			return 1;
		else if (computerThree == 1)
			return 2;
		
		else if (playerTwoPair == 1 && computerTwoPair == 1)
			return 3;
		else if (playerTwoPair == 1)
			return 1;
		else if (computerTwoPair == 1)
			return 2;
		
		else if (playerPair == 1 && computerPair == 1)
		{
			if (playerHighPairCard == computerHighPairCard)
				return 3;
			else if (playerHighPairCard > computerHighPairCard)
				return 1;
			else
				return 2;
		}
		else if (playerPair == 1)
			return 1;
		else if (computerPair == 1)
			return 2;
		
		else if (playerHighCard == computerHighCard)
			return 3;
		else if (playerHighCard > computerHighCard)
			return 1;
		else if (computerHighCard > playerHighCard)
			return 2;
		return 0;
	}
	
	
	/**
	 * This gets the name of the card.
	 * @param suit This allows the suit of the card to be found.
	 * @param value This allows the value of the card to be found.
	 * @return
	 */
	public String getCard(int suit, int value)
	{
		String result = "";
		if (value >= 2 && value <= 10)
		{
			result += (value + " ");
		}
		else
		{
			switch (value)
			{
			case ACE: result += "Ace "; break;
			case JACK: result += "Jack "; break;
			case QUEEN: result += "Queen "; break;
			case KING: result += "King "; break;
			}
		}
		result += "of";
		switch (suit)
		{
		case HEART: result += " Hearts"; break;
		case SPADE: result += " Spades"; break;
		case DIAMOND: result += " Diamonds"; break;
		case CLUB: result += " Clubs"; break;
		}
		return result;
	}
	/**
	 * This gets the value of a card.
	 * @param numCard This picks which cards value to get.
	 * @return This returns the value.
	 */
	public int getPlayerValue(int numCard)
	{
		int result;
		result = playerHand[numCard][0];
		return result;
	}
	/**
	 * This gets the suit of a card.
	 * @param numCard This picks which cards suit to get.
	 * @return This returns the suit.
	 */
	public int getPlayerSuit(int numCard)
	{
		int result;
		result = playerHand[numCard][1];
		return result;
	}
	/**
	 * This gets the value of a card.
	 * @param numCard This picks which cards value to get.
	 * @return This returns the value.
	 */
	public int getComputerValue(int numCard)
	{
		int result;
		result = computerHand[numCard][0];
		return result;
	}
	/**
	 * This gets the suit of a card.
	 * @param numCard This picks which cards suit to get.
	 * @return This returns the suit.
	 */
	public int getComputerSuit(int numCard)
	{
		int result;
		result = computerHand[numCard][1];
		return result;
	}
}
