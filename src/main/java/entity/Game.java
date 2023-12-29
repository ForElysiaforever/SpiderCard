package entity;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int choose = 1;  //默认简单难度
    private int replacementNumber = 5;  //补牌次数，默认五次
    private List<List<Card>> deckList = new ArrayList<>();  //牌堆集合
    private List<Card> AllCardList = new ArrayList<>();  //卡牌集合

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }

    public int getReplacementNumber() {
        return replacementNumber;
    }

    public void setReplacementNumber(int replacementNumber) {
        this.replacementNumber = replacementNumber;
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

    public Game() {
    }

    public Game(int choose, int replacementNumber, List<List<Card>> deckList, List<Card> AllCardList) {
        this.choose = choose;
        this.replacementNumber = replacementNumber;
        this.deckList = deckList;
        this.AllCardList = AllCardList;
    }

    @Override
    public String toString() {
        return "Game{" +
                "choose=" + choose +
                ", replacementNumber=" + replacementNumber +
                ", deckList=" + deckList +
                ", AllCardList=" + AllCardList +
                '}';
    }
}
