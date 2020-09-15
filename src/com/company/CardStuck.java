package com.company;

import java.util.*;

public class CardStuck {

    List<Card> cardDeck = new ArrayList<Card>();

    public CardStuck() {
        init();
    }

    public List<Card> init() {
        cardDeck.clear();
        for(int i = 0; i < 52; i++) {
            Card card = new Card(i);
            cardDeck.add(card);
        }

        Collections.shuffle(cardDeck);

        return cardDeck;
    }

    public Card getOneCard() {
        Card card = cardDeck.get(0);
        cardDeck.remove(0);

        return card;
    }

    public List<Card> getCard(int num) {
        List<Card> cardList = new ArrayList<Card>();
        for(int i = 0; i < num; i++) {
            cardList.add(cardDeck.get(0));
            cardDeck.remove(0);
        }
        return cardList;
    }
}
