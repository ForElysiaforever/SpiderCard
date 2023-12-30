package controller;

import entity.Card;
import entity.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import tools.CardTool;
import tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class SpiderController {
    Game game = new Game();
    CardTool cardTool = new CardTool();
    Tool tool = new Tool();
    List<ImageView> imageList = new ArrayList<>();  //牌堆卡牌节点集合
    List<Pane> paneList = new ArrayList<>();  //牌堆节点集合
    @FXML
    private Pane sCard;
    @FXML
    private StackPane front;

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
        game = tool.updateGame(cardTool);
        paneList.clear();
        sCard.getChildren().clear();
        tool.getDeckPane(cardTable, paneList);
        for (int i = 0; i < paneList.size(); i++) {
            paneList.get(i).getChildren().clear();
            if (i < 4) {
                for (int j = 0; j < 6; j++) {
                    Card card = game.getDeckList().get(i).get(j);
                    ImageView imageView = tool.generateImage(card);
                    paneList.get(i).getChildren().add(imageView);
                    tool.initializeDragAndDrop(imageView, card, paneList);
                }
                tool.location(paneList.get(i), i, game.getDeckList().get(i));
            } else if (i < 10) {
                for (int j = 0; j < 5; j++) {
                    Card card = game.getDeckList().get(i).get(j);
                    ImageView imageView = tool.generateImage(card);
                    paneList.get(i).getChildren().add(imageView);
                    tool.initializeDragAndDrop(imageView, card, paneList);
                }
                tool.location(paneList.get(i), i, game.getDeckList().get(i));
            }
        }
        for (int i = 0; i < 5; i++) {
            Card card = new Card();
            sCard.getChildren().add(tool.generateImage(card));
        }
        tool.location(sCard);
    }

    @FXML
    void supplementaryCard(MouseEvent event) {  //补牌
        if (!sCard.getChildren().isEmpty()) {
            for (int i = 0; i < paneList.size() - 1; i++) {
                cardTool.addCardToDeck(game.getDeckList().get(i), game);
                List<Card> cardList = game.getDeckList().get(i);
                int lastPosition = cardList.size() - 1;
                cardList.get(lastPosition).setCardFace(true);  //新添加的牌为正面
                ImageView imageView = tool.generateImage(cardList.get(lastPosition));
                paneList.get(i).getChildren().add(imageView);
                tool.location(paneList.get(i), 1, game.getDeckList().get(i));
                tool.initializeDragAndDrop(imageView, cardList.get(lastPosition), paneList);
            }
            sCard.getChildren().remove(sCard.getChildren().size() - 1);
        }
    }
}
