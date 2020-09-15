package com.company;

public class GameMsg {

    final static String MSG_START = "ゲームを開始します。\r\n";
    final static String MSG_BET_TRUE = "betするポイントを入力してください：";
    final static String MSG_BET_FALSE = "入力された値ではbetできません。もう一度入力してください：";
    final static String MSG_POINT = "現在の所持ポイント：" + Game.point;
    final static String MSG_DRAWCARD = "カードを引きますか？(y:はい n:いいえ)：";
    final static String MSG_DRAWCARD_AGAIN = "もう一度カードを引きますか？(y:はい n:いいえ)：";
    final static String MSG_YESORNO = "y(Y)かn(N)を入力してください。\r\n";
    final static String MSG_BURST = "バーストしました。\r\n";

    public void showMessage(String judge, String message) {
        if(judge == "勝ち") {

        }
        else if(judge == "引き分け") {

        }
        else if(judge == "負け") {

        }
        else {

        }
    }

    public void showMessage(String message) {
        System.out.print(message);
    }

}
