package com.company;

import java.util.*;

public class Hand {

    int point;

    public int getPoint(List<Card> cardList) {
        point = 0;
        boolean hasAce = false;
        for(int i = 0; i < cardList.size(); i++) {
            if(cardList.get(i).getCardNumber() > 10) {
                point += 10;
            }
            else if(cardList.get(i).getCardNumber() == 1) {
                point += 1;
                hasAce = true;
            }
            else {
                point += cardList.get(i).getCardNumber();
            }
        }

        if(hasAce && point < 12) {
            point += 10;
        }

        if(point > 21) {
            return -1;
        }

        return point;
    }
}
