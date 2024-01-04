package controller;

import entity.Card;
import entity.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import tools.CardTool;
import tools.GameIO;
import tools.Tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpiderController {
    GameIO gameIO = new GameIO();
    Game game = new Game();
    CardTool cardTool = new CardTool();
    Tool tool = new Tool();
    int choose = 1;
    List<List<Card>> cardList = new ArrayList<>();  //牌堆卡牌集合
    List<Pane> paneList = new ArrayList<>();  //牌堆节点集合
    @FXML
    private Pane sCard;
    @FXML
    private Pane front;
    @FXML
    private Pane timeAndSold;
    @FXML
    private ToggleGroup Difficulty;
    @FXML
    private Pane cardTable;
    @FXML
    private Pane removeCard;

    @FXML
    void end(ActionEvent event) {  //退出游戏
        System.exit(0);
    }

    @FXML
    void startTheGame(ActionEvent event) {  //开始游戏和重新开始
        //初始化
        game = tool.updateGame(cardTool, choose);
        tool.getDeckPane(cardTable, paneList);
        cardList = game.getDeckList();
        sCard.getChildren().clear();
        //牌堆
        tool.renderedDeck(paneList, cardList);
        cardTool.determineIfTheCardCanBeMoved(cardList, game.getMobileCards());
        tool.initializeDragAndDrop(front, paneList, game, removeCard);
        //补牌堆
        for (int i = 0; i < game.getReplacementDeck().size(); i++) {
            sCard.getChildren().add(game.getReplacementDeck().get(i).getImageView());
        }
        tool.locationSCard(sCard);
    }

    @FXML
    void supplementaryCard(MouseEvent event) {  //补牌
        if (game.getReplacementDeck().size() != 0) {
            for (int i = 0; i < cardList.size(); i++) {
                List<Card> list = cardList.get(i);
                cardTool.addCardToDeck(list, game);
                list.get(list.size() - 1).setCardFace(true);
            }
            tool.renderedDeck(paneList, cardList);
            sCard.getChildren().remove(sCard.getChildren().size() - 1);
            tool.removeEvent(game.getMobileCards());
            cardTool.determineIfTheCardCanBeMoved(cardList, game.getMobileCards());
            tool.initializeDragAndDrop(front, paneList, game, removeCard);
            game.getReplacementDeck().remove(game.getReplacementDeck().size() - 1);
            game.setNumber(game.getNumber() - 1);
        }
    }

    @FXML
    void back(ActionEvent event) {  //撤回
        if (tool.getGameList().size() > 1){
            for (Pane pane : paneList) {
                pane.getChildren().clear();
            }
            sCard.getChildren().clear();
            removeCard.getChildren().clear();
            game = tool.getGameList().remove(tool.getGameList().size() - 2);
            for (int i = 0; i < tool.getGameList().size(); i++) {
                for (List<Card> list : tool.getGameList().get(i).getDeckList()) {
                    System.out.print(list.size());
                }
                System.out.println();
            }
            cardList = game.getDeckList();
            tool.renderedDeck(paneList, cardList);
            cardTool.determineIfTheCardCanBeMoved(cardList, game.getMobileCards());
            tool.initializeDragAndDrop(front, paneList, game, removeCard);
        }
    }
    @FXML
    void achievement(ActionEvent event) {

    }
    @FXML
    void readGame(ActionEvent event) throws IOException, ClassNotFoundException {
        game = gameIO.loadGame();
        tool.getDeckPane(cardTable, paneList);
        cardList = game.getDeckList();
        sCard.getChildren().clear();
        for (Card card : game.getAllCardList()) {
            cardTool.generateImage(card);
        }
        for (List<Card> list : cardList) {
            for (Card card : list) {
                cardTool.generateImage(card);
            }
        }
        tool.renderedDeck(paneList, cardList);
        cardTool.determineIfTheCardCanBeMoved(cardList, game.getMobileCards());
        tool.initializeDragAndDrop(front, paneList, game, removeCard);
        for (Card card : game.getReplacementDeck()) {
            cardTool.generateImageBack(card);
        }
        for (int i = 0; i < game.getReplacementDeck().size(); i++) {
            sCard.getChildren().add(game.getReplacementDeck().get(i).getImageView());
        }
        tool.locationSCard(sCard);
    }

    @FXML
    void saveGame(ActionEvent event) throws IOException {
        gameIO.saveGame(game);
    }
    @FXML
    void universal(ActionEvent event) {
        choose = cardTool.UNIVERSAL;
    }
    @FXML
    void simple(ActionEvent event) {
        choose = cardTool.SIMPLE;
    }
    @FXML
    void difficult(ActionEvent event) {
        choose = cardTool.DIFFICULTY;
    }

}
