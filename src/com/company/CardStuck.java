package com.company;

import java.util.*;

/**
 * トランプの山の情報を保持するクラス
 * @author 龍王 澪
 */
public class CardStuck {

    // カードの山
    List<Card> cardDeck = new ArrayList<Card>();

    /**
     * コンストラクタ
     */
    public CardStuck() {
        init();
    }

    /**
     * トランプの山を生成
     */
    public void init() {
        cardDeck.clear();
        // カードの山に52枚のカードを追加
        for(int i = 0; i < 52; i++) {
            Card card = new Card(i);
            cardDeck.add(card);
        }

        // カードの山をシャッフル
        Collections.shuffle(cardDeck);
    }

    /**
     * カードを一枚取得
     * @return card カードクラスのインスタンス変数
     */
    public Card getOneCard() {
        // カードの山の一番上のカードを取得
        Card card = cardDeck.get(0);
        // 取得したカードをカードの山から削除
        cardDeck.remove(0);

        return card;
    }

    /**
     * カードを指定した枚数取得
     * @param num 指定枚数
     * @return cardList 取得したカードのリスト
     */
    public List<Card> getCard(int num) {
        // 取得するカードの山
        List<Card> cardList = new ArrayList<Card>();
        // 指定した枚数分のカードを追加し、取得したカードをカードの山から削除
        for(int i = 0; i < num; i++) {
            cardList.add(getOneCard());
        }
        return cardList;
    }
}
