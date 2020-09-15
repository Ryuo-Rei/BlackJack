package com.company;


import java.util.*;
// import java.util.InputMismatchException;
import java.util.Scanner;

import static com.company.GameMsg.showMessage;

/**
 * ゲーム全体を制御するクラス
 */
public class Game {

    // 持ち点
    static int point = 1000;
    // 掛け点
    //int betPoint = 0;
    // プレイヤーのカードの合計点数
    int playerPoint;
    // ディーラーのカードの合計点数
    int dealerPoint;
    // プレイヤーが所持しているカードのリスト
    List<Card> playerCardList;
    // ディーラーが所持しているカードのリスト
    List<Card> dealerCardList;

    /**
     * ゲーム開始メソッド
     */
    public void gameStart() {
        //Bet(msg);
        // ゲーム開始のメッセージを表示
        showMessage(GameMsg.MSG_START);
        // カードの山を生成
        CardStuck cs = new CardStuck();
        // プレイヤーに2枚カードを配る
        playerCardList = cs.getCard(2);
        // ディーラーに2枚カードを配る
        dealerCardList = cs.getCard(2);
        // Handクラスのインスタンス変数生成
        Hand hand = new Hand();

        // プレイヤーのカードを表示
        showCard(playerCardList, GameMsg.MSG_PLAYER, false);
        // ディーラーのカードを表示
        showCard(dealerCardList, GameMsg.MSG_DEALER, true);

        // プレイヤーがカードを引く
        playerDraw(cs);
        // ディーラーがカードを引く
        dealerDraw(cs,hand);

        // プレイヤーのカードを表示
        showCard(playerCardList, GameMsg.MSG_PLAYER, false);
        // プレイヤーの合計点数を取得
        playerPoint = hand.getPoint(playerCardList);
        // 合計点数が-1の場合、バースト
        if(playerPoint == -1) {
            showMessage(GameMsg.MSG_BURST);
        }
        else {
            System.out.println(playerPoint);
        }
        // ディーラーのカードを表示
        showCard(dealerCardList, GameMsg.MSG_DEALER, false);
        // ディーラーの合計点数を取得
        dealerPoint = hand.getPoint(dealerCardList);
        // 合計点数が-1の場合、バースト
        if(dealerPoint == -1){
            showMessage(GameMsg.MSG_BURST);
        }
        else {
            System.out.println(dealerPoint);
        }
    }

    /**
     * 所持しているカードを表示するメソッド
     * @param cardList
     * @param name
     * @param isDealer
     */
    public void showCard(List<Card> cardList, String name, boolean isDealer) {
        System.out.print(name + "のカード：");
        // カードの枚数分ループ
        for(int i = 0; i < cardList.size(); i++) {
            // ディーラーの場合、２枚目以降を*として表示
            if(isDealer) {
                if(i == 0) {
                    System.out.print(cardList.get(i).getString());
                }
                else {
                    System.out.print(" *");
                }
            }
            else {
                if(i == 0) {
                    System.out.print(cardList.get(i).getString());
                }
                else {
                    System.out.print(" " + cardList.get(i).getString());
                }
            }
        }
        System.out.print("\r\n");
    }

    /**
     * プレイヤーがカードを引くメソッド
     * @param cs
     */
    public void playerDraw(CardStuck cs) {
        // ループが一回目か判定するフラグ
        boolean isOnce = true;
        // yかnが入力されるまでループ
        while(true) {
            if(isOnce) {
                showMessage(GameMsg.MSG_DRAWCARD);
            }
            else {
                showMessage(GameMsg.MSG_DRAWCARD_AGAIN);
            }

            Scanner scan = new Scanner(System.in);
            // 入力
            String input = scan.nextLine();

            // 大文字小文字に関係なく、yが入力された場合、１枚カードを引き、引いたカードを表示
            // isOnceフラグをfalseにする
            if(input.equalsIgnoreCase("y")) {
                playerCardList.add(cs.getOneCard());
                System.out.println(playerCardList.get(playerCardList.size() - 1).getString());
                isOnce = false;
            }
            // 大文字小文字に関係なく、nが入力された場合、ループを抜ける
            else if(input.equalsIgnoreCase("n")) {
                break;
            }
            // yかn以外が入力された場合、メッセージを表示し、次のループへ
            else {
                showMessage(GameMsg.MSG_YESORNO);
            }
        }
    }

    /**
     * ディーラーがカードを引くメソッド
     * @param cs
     * @param hand
     */
    public void dealerDraw(CardStuck cs, Hand hand) {
        // ディーラーの合計点数を取得
        dealerPoint = hand.getPoint(dealerCardList);
        // ディーラーの合計点数が17より小さい場合、カードを1枚引く
        while(dealerPoint < 17) {
            dealerCardList.add(cs.getOneCard());
            dealerPoint = hand.getPoint(dealerCardList);
            // ディーラーの合計点数が-1の場合、ループを抜ける
            if(dealerPoint == -1) {
                break;
            }
        }
    }

    /*private int Bet(GameMsg msg) {
        boolean hasError = true;

        while(true) {
            try {
                if (hasError) {
                    msg.ShowMessage(GameMsg.MSG_BET_TRUE);
                } else {
                    msg.ShowMessage(GameMsg.MSG_BET_FALSE);
                }

                msg.ShowMessage(GameMsg.MSG_POINT);
                Scanner scan = new Scanner(System.in);
                betPoint = scan.nextInt();

                if(point < betPoint || (betPoint < 1 || 100 < betPoint)) {
                    hasError = false;
                    continue;
                }
            }
            catch(InputMismatchException e) {
                hasError = false;
                continue;
            }
            break;
        }

        return betPoint;
    }

    private List<String> SetCard (CardStuck cs) {

    }*/
}
