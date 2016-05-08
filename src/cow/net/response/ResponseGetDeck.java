package cow.net.response;

import shared.util.GamePacket;
import cow.model.Card;
import cow.model.CardDeck;
import cow.metadata.NetworkCode;
import cow.net.response.GameResponse;

public class ResponseGetDeck extends GameResponse {
	int numCards;
	int numFields;
	CardDeck deck; 
		
	public ResponseGetDeck() {
	    response_id = NetworkCode.GET_DECK;
	}
	 
	@Override
	public byte[] getBytes() {
	    GamePacket packet = new GamePacket(response_id);
		Card card;
	    packet.addInt32(numCards);
	    packet.addInt32(numFields);
	        
	    // Add card data to Game Packet: 
	    for (int i = 0; i < numCards; i++) {
	    	// ha: if not storing deck info in server, easier just to pop from deck
	    	card = deck.popCardFromDeck();
	    	// speciesID not included
			if (card != null) {
				packet.addInt32(card.getCardID());
				packet.addInt32(card.getHealth());
				packet.addInt32(card.getAttack());
				packet.addInt32(card.getLevel());
				packet.addString(card.getDietType());
				packet.addString(card.getSpeciesName());
				packet.addString(card.getDescription());
			}
	    }

	    return packet.getBytes();
	}

	/**
	 * @return the numCards
	 */
	public int getNumCards() {
		return numCards;
	}

	/**
	 * @return the numFields
	 */
	public int getNumFields() {
		return numFields;
	}

	/**
	 * @return the deck
	 */
	public CardDeck getDeck() {
		return deck;
	}

	/**
	 * @param numCards the numCards to set
	 */
	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

	/**
	 * @param numFields the numFields to set
	 */
	public void setNumFields(int numFields) {
		this.numFields = numFields;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(CardDeck deck) {
		this.deck = deck;
	}

}

