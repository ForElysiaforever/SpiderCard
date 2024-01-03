package tools;

import entity.Card;
import entity.Game;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
                generateImage(card);
                cardList.add(card);
            }
        }
        game.setAllCardList(cardList);
    }

    public ImageView generateImage(Card card) {  //根据花色和值生成对于的牌
        String str;
        ImageView imageView = new ImageView();
        str = card.getFlowerColor() + "-" + card.getNumber();
        Image image = new Image(getClass().getResourceAsStream("/static/images/" + str + ".gif"));
        imageView.setImage(image);
        card.setImageView(imageView);
        return imageView;
    }
    public void generateImageBack(Card card) {  //卡牌背面
        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("/static/images/rear.gif"));
        imageView.setImage(image);
        card.setImageView(imageView);
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

    public void initializeRCard(Game game) {  //初始化补牌堆
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = new Card();
            generateImageBack(card);
            cardList.add(card);
        }
        game.setReplacementDeck(cardList);
    }
    public void determineIfTheCardCanBeMoved(List<List<Card>> deckList, List<List<Card>> mobileCards){  //判断可移动卡牌
        mobileCards.clear();
        for (int i = 0; i < deckList.size(); i++) {
            List<Card> list = deckList.get(i);
            List<Card> newList = new ArrayList<>();
            List<Card> mList = new ArrayList<>();
            for (Card card : list) {
                if (card.isCardFace()) {
                    newList.add(card);
                }
            }
            if (newList.size() == 0){
                mobileCards.add(newList);
                continue;
            }
            mList.add(newList.get(newList.size() - 1));
            for (int j = newList.size() - 1; j >= 0; j--) {
                if (j != 0){
                    if (newList.get(j).getNumber() == newList.get(j- 1).getNumber() - 1){
                        mList.add(0,newList.get(j - 1));
                    }else {
                        break;
                    }
                }
            }
            mobileCards.add(mList);
        }
    }
    public boolean judgeRemoveCard(List<List<Card>> mobileCards, List<List<Card>> deckList){
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < mobileCards.size(); i++) {
            cardList = deckList.get(i);
            if (mobileCards.get(i).size() == 13){
                for (int j = 0; j < 13; j++) {
                    cardList.remove(cardList.size() - 1);
                }
                return true;
            }
        }
        return false;
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
