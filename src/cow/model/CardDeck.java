package cow.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import cow.db.CardDAO;
import shared.util.Log;

public class CardDeck {
	
	private ArrayList<Card> deck = null;
	private ArrayList<Integer> idList = null;
	private boolean isEmpty = false;
	private int playerID = 0;
	
	public CardDeck(boolean empty) {
		this.deck = new ArrayList<Card>();
		this.isEmpty = empty;
	}

	public CardDeck() {
		this.deck = new ArrayList<Card>();
		this.idList = new ArrayList<Integer>();
		this.idList = CardDAO.getCardIdList();
		this.generateRandomDeck();
	}
	
	public CardDeck(ArrayList<Integer> cardIdList) {
		this.deck = new ArrayList<Card>();
		this.idList = cardIdList;
		randomizeIdList();
		this.generateDeck();
	}
	
	public CardDeck(ArrayList<Integer> cardIdList, int playerID) {
		this.deck = new ArrayList<Card>();
		this.idList = cardIdList;
		this.playerID = playerID;
		randomizeIdList();
		this.generateDeck();
	}
	
	public ArrayList<Card> addCard(Card card) {
		if(isEmpty){
			this.deck.add(card);
		}
		return this.deck;
	}

	public Card popCardFromDeck() {
		if(this.getDeckSize()==0){
			return null;
		}
		return this.deck.remove(this.getDeckSize()-1);
	}
	
	public int getDeckSize() {
		return deck.size();
	}
	
	public ArrayList<Card> getDeck() {
		return this.deck;
	}
	
	public void printOriginalDeck() {
		int size = this.getDeckSize();
		for(int i=0; i<size; i++) {
			Log.consoleln(this.deck.get(i).toString());
		}
	}
	
	public void printDeck() {
		int size = this.getDeckSize();
		for(int i=0; i<size; i++) {
			Log.consoleln(this.popCardFromDeck().toString());
		}
	}
	
	public void printIdList() {
		int size = this.idList.size();
		for(int i=0; i<size; i++) {
			Log.consoleln(i+1 + ": " + this.idList.get(i));
		}		
	}
	
	private void generateRandomDeck() {
		this.randomizeIdList();
		int size = idList.size();
		for(int i=0; i<size; i++) {
			Integer id = idList.get(i);
			Card card = CardDAO.getCard(id);
			deck.add(card);
		}
	}
	
	private void generateDeck() {
		int size = idList.size();
		for(int i=0; i<size; i++) {
			Integer id = idList.get(i);
			Card card = CardDAO.getCard(id);
			deck.add(card);
		}
	}
	
	private void randomizeIdList() {
		long seed = System.nanoTime();
		Collections.shuffle(this.idList, new Random(seed));
	}

	public static void main(String[] args) {
		ArrayList<Integer> card_ids = new ArrayList<Integer>();
		ArrayList<Integer> id_list = new ArrayList<Integer>();
		card_ids.add(15);
		card_ids.add(15);
		card_ids.add(17);
		card_ids.add(13);
		card_ids.add(13);
		card_ids.add(12);
		
		
		
		CardDeck deck = new CardDeck(id_list);
		//CardDeck deck = new CardDeck(card_ids);
		Log.consoleln("Deck: ");
		deck.printDeck();
		
		System.exit(0);
	}

}
