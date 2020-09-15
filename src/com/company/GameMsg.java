package com.company;

/**
 * メッセージ関連のオブジェクトを保持するクラス
 */
public class GameMsg {

    /**
     * 定数定義
     */
    final static String MSG_PLAYER = "プレイヤー";
    final static String MSG_DEALER = "ディーラー";
    final static String MSG_START = "ゲームを開始します。\r\n";
    final static String MSG_BET_TRUE = "betするポイントを入力してください：";
    final static String MSG_BET_FALSE = "入力された値ではbetできません。もう一度入力してください：";
    final static String MSG_POINT = "現在の所持ポイント：" + Game.point;
    final static String MSG_DRAWCARD = "カードを引きますか？(y:はい n:いいえ)：";
    final static String MSG_DRAWCARD_AGAIN = "もう一度カードを引きますか？(y:はい n:いいえ)：";
    final static String MSG_YESORNO = "y(Y)かn(N)を入力してください。\r\n";
    final static String MSG_BURST = "バーストしました。\r\n";

    public static void showMessage(String judge, String message) {
        if(judge == "勝ち") {

        }
        else if(judge == "引き分け") {

        }
        else if(judge == "負け") {

        }
        else {

        }
    }

    /**
     * メッセージを表示するメソッド
     * @param message
     */
    public static void showMessage(String message) {
        System.out.print(message);
    }

}
