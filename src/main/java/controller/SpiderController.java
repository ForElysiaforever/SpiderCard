package controller;

import entity.Card;
import entity.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import tools.CardTool;
import tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class SpiderController {
    Game game = new Game();
    CardTool cardTool = new CardTool();
    Tool tool = new Tool();
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
    void end(ActionEvent event) {  //退出游戏
        System.exit(0);
    }

    @FXML
    void startTheGame(ActionEvent event) {  //开始游戏和重新开始
        //初始化
        game = tool.updateGame(cardTool);
        tool.getDeckPane(cardTable, paneList);
        cardList = game.getDeckList();
        sCard.getChildren().clear();
        //牌堆
        tool.renderedDeck(paneList, cardList);
        cardTool.determineIfTheCardCanBeMoved(cardList, game.getMobileCards());
        tool.initializeDragAndDrop(front, paneList, game);
        //补牌堆
        for (int i = 0; i < game.getReplacementDeck().size(); i++) {
            sCard.getChildren().add(game.getReplacementDeck().get(i).getImageView());
        }
        tool.locationSCard(sCard);
    }

    @FXML
    void supplementaryCard(MouseEvent event) {  //补牌
        if (!sCard.getChildren().isEmpty()) {
            for (int i = 0; i < cardList.size(); i++) {
                List<Card> list = cardList.get(i);
                cardTool.addCardToDeck(list, game);
                list.get(list.size() - 1).setCardFace(true);
            }
            tool.renderedDeck(paneList, cardList);
            sCard.getChildren().remove(sCard.getChildren().size() - 1);
            tool.removeEvent(game.getMobileCards());
            cardTool.determineIfTheCardCanBeMoved(cardList, game.getMobileCards());
            tool.initializeDragAndDrop(front, paneList, game);
        } else {

        }
    }
}
