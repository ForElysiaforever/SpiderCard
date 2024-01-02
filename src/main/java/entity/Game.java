package entity;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int choose = 1;  //默认简单难度
    private List<Card> removeDeck = new ArrayList<>();  //消除
    private List<Card> replacementDeck = new ArrayList<>();  //补牌次数，默认五次
    private List<List<Card>> deckList = new ArrayList<>();  //牌堆集合
    private List<Card> AllCardList = new ArrayList<>();  //卡牌集合
    private List<List<Card>> mobileCards = new ArrayList<>();  //可移动卡牌集合

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public List<Card> getRemoveDeck() {
        return removeDeck;
    }

    public void setRemoveDeck(List<Card> removeDeck) {
        this.removeDeck = removeDeck;
    }

    public List<Card> getReplacementDeck() {
        return replacementDeck;
    }

    public void setReplacementDeck(List<Card> replacementDeck) {
        this.replacementDeck = replacementDeck;
    }

    public List<List<Card>> getDeckList() {
        return deckList;
    }

    public void setDeckList(List<List<Card>> deckList) {
        this.deckList = deckList;
    }

    public List<Card> getAllCardList() {
        return AllCardList;
    }

    public void setAllCardList(List<Card> allCardList) {
        AllCardList = allCardList;
    }

    public List<List<Card>> getMobileCards() {
        return mobileCards;
    }

    public void setMobileCards(List<List<Card>> mobileCards) {
        this.mobileCards = mobileCards;
    }

    @Override
    public String toString() {
        return "Game{" +
                "choose=" + choose +
                ", removeDeck=" + removeDeck +
                ", replacementDeck=" + replacementDeck +
                ", deckList=" + deckList +
                ", AllCardList=" + AllCardList +
                ", mobileCards=" + mobileCards +
                '}';
    }
}
