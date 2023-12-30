package tools;

import entity.Card;
import entity.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardTool {  //实现游戏逻辑
    final int SIMPLE = 1;  //简单难度
    final int UNIVERSAL = 2;  //普通难度
    final int DIFFICULTY = 3;  //困难难度

    /*简单*/
    public void setCard(Game game) {  //初始化卡牌，将卡牌加入cardList
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card();
                card.setFlowerColor(1);
                card.setNumber(j + 1);
                cardList.add(card);
            }
        }
        game.setAllCardList(cardList);
    }

    public List<Card> initializeDeck(Game game) {  //初始化牌堆
        List<Card> cardList = new ArrayList<>();
        if (game.getDeckList().size() < 4) {
            for (int i = 0; i < 6; i++) {
                addCardToDeck(cardList, game);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                addCardToDeck(cardList, game);
            }
        }
        return cardList;
    }

    public void setDeck(Game game) {  //将牌堆加入deckList
        List<Card> cardList = new ArrayList<>();
        List<List<Card>> deckList = game.getDeckList();
        for (int i = 0; i < 10; i++) {
            cardList = initializeDeck(game);
            cardList.get(cardList.size() - 1).setCardFace(true);  //默认开始时牌堆最后一张牌正面
            deckList.add(cardList);
        }
        game.setDeckList(deckList);
    }

    public void addCardToDeck(List<Card> cardList, Game game) {  //随机往牌堆添加卡牌
        Random random = new Random();
        int randomIndex = random.nextInt(game.getAllCardList().size()); // 随机生成索引
        Card selectedCard = game.getAllCardList().get(randomIndex); // 获取被选中的卡牌
        cardList.add(selectedCard); // 将被选中的卡牌添加到cardList中
        game.getAllCardList().remove(randomIndex); // 从allCardList中删除被选中的卡牌
    }

//    public void setChoose(int n) {  //选择难度
//        switch (n) {
//            case 1:
//                choose = SIMPLE;
//                break;
//            case 2:
//                choose = UNIVERSAL;
//                break;
//            case 3:
//                choose = DIFFICULTY;
//                break;
//            default:
//                System.out.println("程序异常！");
//        }
//    }
}
