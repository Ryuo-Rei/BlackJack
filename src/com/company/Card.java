package com.company;

public class Card {
    int cardIndex;
    int cardMark;
    int cardNumber;
    String card;

    public Card(int cardIndex) {
        this.cardIndex = cardIndex;
        cardMark = cardIndex / 13;
        cardNumber = (cardIndex % 13) + 1;
    }

    public int getCardMark() {
        return cardMark;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getString() {
        if(cardMark == 0) {
            card = "S" + cardNumber;
        }
        else if(cardMark == 1) {
            card = "C" + cardNumber;
        }
        else if(cardMark == 2) {
            card = "H" + cardNumber;
        }
        else if(cardMark == 3) {
            card = "D" + cardNumber;
        }
        return card;
    }
}
