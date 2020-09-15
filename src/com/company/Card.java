package com.company;

/**
 * カード一枚の情報を保持するクラス
 */
public class Card {
    int cardIndex;
    int cardMark;
    int cardNumber;
    String card;

    /**
     * コンストラクタ
     * @param cardIndex トランプの山のインデックス
     */
    public Card(int cardIndex) {
        this.cardIndex = cardIndex;
        cardMark = cardIndex / 13;
        cardNumber = (cardIndex % 13) + 1;
    }

    /**
     * カードマークを取得
     * @return cardMark
     */
    public int getCardMark() {
        return cardMark;
    }

    /**
     * カードの数字を取得
     * @return cardNumber
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * 取得したカードマークと数字を合成してカードを作成
     * @return card
     */
    public String getString() {
        // cardIndex / 13の商が0の場合、Spade
        if(cardMark == 0) {
            card = "S" + cardNumber;
        }
        // cardIndex / 13の商が0の場合、Clover
        else if(cardMark == 1) {
            card = "C" + cardNumber;
        }
        // cardIndex / 13の商が0の場合、Heart
        else if(cardMark == 2) {
            card = "H" + cardNumber;
        }
        // cardIndex / 13の商が0の場合、Diamond
        else if(cardMark == 3) {
            card = "D" + cardNumber;
        }
        return card;
    }
}
