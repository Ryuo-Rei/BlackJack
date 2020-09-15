package com.company;

import java.util.*;

/**
 * カードの点数計算を行うクラス
 */
public class Hand {

    // カードの点数合計
    int point;

    /**
     * カードの点数計算を行うメソッド
     * @param cardList
     * @return point
     */
    public int getPoint(List<Card> cardList) {
        // 合計点数を初期化
        point = 0;
        // Aceのカードを所持しているかのフラグ
        boolean hasAce = false;
        // 持っているカードの枚数分ループ
        for(int i = 0; i < cardList.size(); i++) {
            // カードの数字が11以上13以下の場合、点数10点
            if(cardList.get(i).getCardNumber() > 10) {
                point += 10;
            }
            // カードがAceの場合、点数1点
            // hasAceフラグをon(true)にする
            else if(cardList.get(i).getCardNumber() == 1) {
                point += 1;
                hasAce = true;
            }
            // その他の場合、カードの数字をそのまま点数とする
            else {
                point += cardList.get(i).getCardNumber();
            }
        }

        // Aceを所持しており、かつ、合計点数が11以下の場合、Aceを11点として扱い、合計点数に10点追加
        if(hasAce && point < 12) {
            point += 10;
        }

        // 合計点数が21を超えた場合、-1を返す
        if(point > 21) {
            return -1;
        }

        return point;
    }
}
