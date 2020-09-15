package com.company;

import java.util.*;

/**
 * 勝敗判定を行うクラス
 */
public class Judge {

    /**
     * 勝敗判定を行うメソッド
     * @param playerPoint
     * @param dealerPoint
     * @param playerCardList
     * @param dealerCardList
     * @return num(0:プレイヤー勝利、1:ディーラー勝利、2:引き分け)
     */
    public int judgeIssue(int playerPoint, int dealerPoint, List<Card> playerCardList, List<Card> dealerCardList) {
        int num = 2;
        // プレイヤーがバーストした場合、ディーラーの勝ち
        if(playerPoint == -1) {
            System.out.println("ディーラーの勝ち");
            num = 1;
        }
        // プレイヤーの合計点数がディーラーの合計点数よりも大きい場合、プレイヤーの勝ち
        else if(playerPoint > dealerPoint) {
            System.out.println("プレイヤーの勝ち");
            num = 0;
        }
        // プレイヤーの合計点数がディーラーの合計点数よりも小さい場合、ディーラーの勝ち
        else if(playerPoint < dealerPoint) {
            System.out.println("ディーラーの勝ち");
            num = 1;
        }
        // プレイヤーの合計点数が21、かつ、プレイヤーとディーラーの合計点数が同じ場合、(両方ブラックジャック)カードの枚数で勝敗判定する
        else if(playerPoint == 21 && playerPoint == dealerPoint) {
            // プレイヤーのカード枚数がディーラーのカード枚数より多い場合、ディーラーの勝ち
            if(playerCardList.size() > dealerCardList.size()) {
                System.out.println("ディーラーの勝ち");
                num = 1;
            }
            // プレイヤーのカード枚数がディーラーのカード枚数より少ない場合、プレイヤーの勝ち
            else if(playerCardList.size() < dealerCardList.size()) {
                System.out.println("プレイヤーの勝ち");
                num = 0;
            }
            else {
                System.out.println("引き分け");
                num = 2;
            }
        }
        else {
            System.out.println("引き分け");
            num = 2;
        }
        return num;
    }
}