package com.company;


import java.util.*;
// import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    static int point = 1000;
    //int betPoint = 0;
    int playerPoint;
    int dealerPoint;

    public void gameStart() {
        //Bet(msg);
        GameMsg msg = new GameMsg();
        msg.showMessage(GameMsg.MSG_START);
        CardStuck cs = new CardStuck();
        List<Card> playerCardList = cs.getCard(2);
        List<Card> dealerCardList = cs.getCard(2);
        Hand hand = new Hand();

        showCard(playerCardList, "プレイヤー", false);
        showCard(dealerCardList, "ディーラー", true);

        boolean isOnce = true;
        while(true) {
            if(isOnce) {
                msg.showMessage(GameMsg.MSG_DRAWCARD);
            }
            else {
                msg.showMessage(GameMsg.MSG_DRAWCARD_AGAIN);
            }

            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            if(input.equalsIgnoreCase("y")) {
                playerCardList.add(cs.getOneCard());
                System.out.println(playerCardList.get(playerCardList.size() - 1).getString());
                isOnce = false;
            }
            else if(input.equalsIgnoreCase("n")) {
                break;
            }
            else {
                msg.showMessage(GameMsg.MSG_YESORNO);
            }
        }

        dealerPoint = hand.getPoint(dealerCardList);
        while(dealerPoint < 17) {
            dealerCardList.add(cs.getOneCard());
            dealerPoint = hand.getPoint(dealerCardList);
            if(dealerPoint == -1) {
                break;
            }
        }

        showCard(playerCardList, "プレイヤー", false);
        playerPoint = hand.getPoint(playerCardList);
        if(playerPoint == -1) {
            msg.showMessage(GameMsg.MSG_BURST);
        }
        else {
            System.out.println(playerPoint);
        }
        showCard(dealerCardList, "ディーラー", false);
        dealerPoint = hand.getPoint(dealerCardList);
        if(dealerPoint == -1){
            msg.showMessage(GameMsg.MSG_BURST);
        }
        else {
            System.out.println(dealerPoint);
        }
    }

    public void showCard(List<Card> cardList, String name, boolean isDealer) {
        System.out.print(name + "のカード：");
        for(int i = 0; i < cardList.size(); i++) {
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
