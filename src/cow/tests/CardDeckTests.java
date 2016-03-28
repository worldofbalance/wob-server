package cow.tests;

import java.util.ArrayList;

import shared.db.PlayerDAO;
import cow.model.Card;
import cow.model.CardDeck;
import shared.model.Player;
import shared.util.Log;

public class CardDeckTests {
	
	private Player playerOne;
	private Player playerTwo;
	boolean emptyDeck;

	public CardDeckTests() {
		this.playerOne = PlayerDAO.getPlayer(108);
		this.playerTwo = PlayerDAO.getPlayer(109);
		
		this.emptyDeck = true;
	}
	
	public void generatePlayerDecks() {
		CardDeck deck1 = new CardDeck(this.emptyDeck); // Empty Deck
		CardDeck deck2 = new CardDeck(this.emptyDeck); // Empty Deck
		
		ArrayList<Integer> card_ids = new ArrayList<Integer>();
		card_ids.add(15);
		card_ids.add(15);
		card_ids.add(17);
		card_ids.add(13);
		card_ids.add(13);
		card_ids.add(12);

		CardDeck mainDeck = new CardDeck(card_ids);  // Species Deck
		
		int deckSize = mainDeck.getDeckSize();

		for(int i=0; i<deckSize; i++){
			Card card = mainDeck.popCardFromDeck();
			
			deck1.addCard(card);
			deck2.addCard(card);
		}

		
		this.playerOne.givePlayerADeck(deck1); // Add deck to player1
		this.playerTwo.givePlayerADeck(deck2); // Add deck to player2
	}
	
	
	public void run() {
		this.generatePlayerDecks();
		Log.consoleln("Player 1 Deck: ");
		this.playerOne.getPlayerDeck().printOriginalDeck();
		Log.consoleln("Player 1 Deck Size: " + this.playerOne.getPlayerDeck().getDeckSize());
		Log.consoleln("Player 2 Deck: ");
		this.playerTwo.getPlayerDeck().printOriginalDeck();
		Log.consoleln("Player 2 Deck Size: " + this.playerTwo.getPlayerDeck().getDeckSize());
	}
	
	public static void main(String[] args) {
		CardDeckTests deckTests = new CardDeckTests();
		deckTests.run();
		
		System.exit(0);
	}

}
