package controller;

import entity.Card;
import entity.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
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
    List<ImageView> imageList = new ArrayList<>();  //牌堆卡牌节点集合
    List<Pane> paneList = new ArrayList<>();  //牌堆节点集合
    @FXML
    private Pane scard;

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
        tool.getDeckPane(cardTable, paneList);
        for (int i = 0; i < paneList.size(); i++) {
            paneList.get(i).getChildren().clear();
            if (i < 4) {
                for (int j = 0; j < 6; j++) {
                    Card card = game.getDeckList().get(i).get(j);
                    paneList.get(i).getChildren().add(tool.generateImage(card));
                }
                tool.location(paneList.get(i), i);
            } else if (i < 10) {
                for (int j = 0; j < 5; j++) {
                    Card card = game.getDeckList().get(i).get(j);
                    paneList.get(i).getChildren().add(tool.generateImage(card));
                }
                tool.location(paneList.get(i), i);
            } else {
                for (int j = 0; j < 5; j++) {
                    Card card = new Card();
                    paneList.get(i).getChildren().add(tool.generateImage(card));
                }
                tool.location(paneList.get(i), i);
            }
        }
    }

    @FXML
    void supplementaryCard(MouseEvent event) {  //补牌
        if (!scard.getChildren().isEmpty()) {
            for (int i = 0; i < paneList.size(); i++) {
                if (i != 10) {
                    cardTool.addCardToDeck(game.getDeckList().get(i), game);
                    List<Card> cardList = game.getDeckList().get(i);
                    int lastPosition = cardList.size() - 1;
                    cardList.get(lastPosition).setCardFace(true);  //新添加的牌为正面
                    paneList.get(i).getChildren().add(tool.generateImage(cardList.get(lastPosition)));
                    tool.location(paneList.get(i), 1);
                } else {
                    paneList.get(i).getChildren().remove(paneList.get(i).getChildren().size() - 1);
                }
            }
        }
    }
}
