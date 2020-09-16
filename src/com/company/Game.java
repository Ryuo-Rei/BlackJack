package com.company;


import java.util.*;
import java.util.Scanner;

import static com.company.GameMsg.*;

/**
 * ゲーム全体を制御するクラス
 */
public class Game {

    // 所持ポイント
    static int point = 1000;
    // 掛けポイント
    int betPoint = 0;
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
        // ゲーム開始のメッセージを表示
        showMessage(GameMsg.MSG_START);
        while (0 < point && point < 2000) {
            // 賭けポイントを入力
            bet();
            // カードの山を生成
            CardStuck cs = new CardStuck();
            // プレイヤーに2枚カードを配る
            playerCardList = cs.getCard(2);
            // playerCardList.add(new Card(0));
            // playerCardList.add(new Card(12));
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
            dealerDraw(cs, hand);

            // プレイヤーのカードを表示
            showCard(playerCardList, GameMsg.MSG_PLAYER, false);
            // プレイヤーの合計点数を取得
            playerPoint = hand.getPoint(playerCardList);
            // 合計点数が-1の場合、バースト
            BLOCK:
            {
                if (playerPoint == -1) {
                    showMessage(GameMsg.MSG_BURST);
                    break BLOCK;
                } else {
                    showMessage(MSG_PLAYER + "の合計点数：" + playerPoint + "点\r\n");
                }
                // ディーラーのカードを表示
                showCard(dealerCardList, GameMsg.MSG_DEALER, false);
                // ディーラーの合計点数を取得
                dealerPoint = hand.getPoint(dealerCardList);
                // 合計点数が-1の場合、バースト
                if (dealerPoint == -1) {
                    showMessage(GameMsg.MSG_BURST);
                } else {
                    showMessage(MSG_DEALER + "の合計点数：" + dealerPoint + "点\r\n");
                }
            }
            Judge judge = new Judge();
            // 勝敗を判定
            int num = judge.judgeIssue(playerPoint, dealerPoint, playerCardList, dealerCardList);
            // 所持ポイント更新
            updatePoint(num);
            if (point >= 2000) {
                showMessage(MSG_END_WIN);
            } else if (point <= 0) {
                showMessage(MSG_END_LOSE);
            }
        }
    }

    /**
     * 所持しているカードを表示するメソッド
     *
     * @param cardList 　所持カードのリスト
     * @param name     　名前
     * @param isDealer 　ディーラーかどうかの判定フラグ
     */
    public void showCard(List<Card> cardList, String name, boolean isDealer) {
        System.out.print(name + "のカード：");
        // カードの枚数分ループ
        for (int i = 0; i < cardList.size(); i++) {
            // ディーラーの場合、２枚目以降を*として表示
            if (isDealer) {
                if (i == 0) {
                    System.out.print(cardList.get(i).getCardInfo());
                } else {
                    System.out.print(" *");
                }
            } else {
                if (i == 0) {
                    System.out.print(cardList.get(i).getCardInfo());
                } else {
                    System.out.print(" " + cardList.get(i).getCardInfo());
                }
            }
        }
        System.out.print("\r\n");
    }

    /**
     * プレイヤーがカードを引くメソッド
     *
     * @param cs カードの山
     */
    public void playerDraw(CardStuck cs) {
        // ループが一回目か判定するフラグ
        boolean isOnce = true;
        // yかnが入力されるまでループ
        while (true) {
            if (isOnce) {
                showMessage(GameMsg.MSG_DRAWCARD);
            } else {
                showMessage(GameMsg.MSG_DRAWCARD_AGAIN);
            }

            Scanner scan = new Scanner(System.in);
            // 入力
            String input = scan.nextLine();

            // 大文字小文字に関係なく、yが入力された場合、１枚カードを引き、引いたカードを表示
            // isOnceフラグをfalseにする
            if (input.equalsIgnoreCase("y")) {
                playerCardList.add(cs.getOneCard());
                System.out.println(playerCardList.get(playerCardList.size() - 1).getCardInfo());
                isOnce = false;
            }
            // 大文字小文字に関係なく、nが入力された場合、ループを抜ける
            else if (input.equalsIgnoreCase("n")) {
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
     *
     * @param cs   カードの山
     * @param hand Handクラスのインスタンス変数
     */
    public void dealerDraw(CardStuck cs, Hand hand) {
        // ディーラーの合計点数を取得
        dealerPoint = hand.getPoint(dealerCardList);
        // ディーラーの合計点数が17より小さい場合、カードを1枚引く
        while (dealerPoint < 17) {
            dealerCardList.add(cs.getOneCard());
            dealerPoint = hand.getPoint(dealerCardList);
            // ディーラーの合計点数が-1の場合、ループを抜ける
            if (dealerPoint == -1) {
                break;
            }
        }
    }

    /**
     * 賭け金を決定するメソッド
     */

    private void bet() {
        boolean hasError = true;

        // 正しい入力値が入力されるまでループ
        while (true) {
            try {
                // 現在の所持ポイント表示
                showMessage(MSG_POINT + point + "\r\n");
                if (hasError) {
                    showMessage(GameMsg.MSG_BET_TRUE);
                } else {
                    showMessage(GameMsg.MSG_BET_FALSE);
                }

                Scanner scan = new Scanner(System.in);
                betPoint = scan.nextInt();

                // 入力された値が所持ポイントより多い、もしくは、1 ~ 100以外が入力された場合、次のループへ
                if (point < betPoint || (betPoint < 1 || 100 < betPoint)) {
                    hasError = false;
                    continue;
                }
            } catch (InputMismatchException e) {
                hasError = false;
                continue;
            }
            break;
        }
    }

    /**
     * 所持ポイントを更新するメソッド
     *
     * @param num 勝敗判定(0:プレイヤー勝利、1:ディーラー勝利、2:引き分け)
     */
    public void updatePoint(int num) {
        // 見てほしい箇所 ここから-----------

        // プレイヤーが勝利した場合
        if (num == 0) {
            int value1 = playerCardList.get(0).getCardNumber();
            int value2 = playerCardList.get(1).getCardNumber();
            // SpadeのAceとJackでブラックジャックになった場合、賭けポイントを15倍する
            if (playerCardList.size() == 2 && playerPoint == 21 &&
                    (isSpadeAce() &&
                    (value1 == 11 || value2 == 11))) {

                showMessage("+" + betPoint * 15 + "点\r\n");
                point += betPoint * 15;
            }
            // Spade以外のAceとJackでブラックジャックになった場合、賭けポイントを5倍する
            else if (playerCardList.size() == 2 && playerPoint == 21 &&
                    (!isSpadeAce() &&
                    (value1 == 11 || value2 == 11))) {
                showMessage("+" + betPoint * 5 + "点\r\n");
                point += betPoint * 5;
            }
            // AceとJack以外の絵札でブラックジャックになった場合、賭けポイントを2.5倍する
            else if (playerCardList.size() == 2 && playerPoint == 21 &&
                    ((value1 == 1 || value2 == 1) &&
                    (value1 >= 12 || value2 >= 12))) {
                showMessage("+" + betPoint * 2.5 + "点\r\n");
                point += betPoint * 2.5;
            }
            // カードが三枚とも7の場合、賭けポイントを10倍する
            else if (playerCardList.size() == 3 &&
                    (value1 == 7 && value2 == 7 &&
                            playerCardList.get(2).getCardNumber() == 7)) {
                showMessage("+" + betPoint * 10 + "点\r\n");
                point += betPoint * 10;
            }
            // 手札が7枚以上かつ合計点数が21点以下の場合、賭けポイントを10倍する。
            else if (playerCardList.size() >= 7 && playerPoint <= 21) {
                showMessage("+" + betPoint * 10 + "点\r\n");
                point += betPoint * 10;
            }
            // 手札が6枚以上かつ合計点数が21点以下の場合、賭けポイントを5倍する。
            else if (playerCardList.size() >= 6 && playerPoint <= 21) {
                showMessage("+" + betPoint * 5 + "点\r\n");
                point += betPoint * 5;
            } else {
                showMessage("+" + betPoint + "点\r\n");
                point += betPoint;
            }
        }
        // ここまで-----------

        // ディーラーが勝利した場合、所持ポイントから賭けポイントを引く
        else if (num == 1) {
            showMessage("-" + betPoint + "点\r\n");
            point -= betPoint;
        } else {
            showMessage("±0点");
        }
    }

    /**
     * 所持カードにSpadeのAceが含まれるかを判定するメソッド
     * @return isSpadeAce
     */
    private boolean isSpadeAce() {
        boolean isSpadeAce = true;
        if(!playerCardList.get(0).getCardInfo().equals(GameMsg.MSG_SPADEACE) &&
                !playerCardList.get(1).getCardInfo().equals(GameMsg.MSG_SPADEACE)) {
            isSpadeAce= false;
        }
        return isSpadeAce;
    }
}
