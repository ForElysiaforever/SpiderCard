package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class SpiderController {
    //工具
    Tool tool = new Tool();
    //补牌堆的节点

    @FXML
    private Pane scard;
    //补牌次数5次
    int scNumber = 5;
    //子节点的集合
    ObservableList<Node> allCard;
    //一个牌堆的卡牌集合
    List<ImageView> list = new ArrayList<>();
    //牌堆jihe
    List<Pane> listPane = new ArrayList<>();
    @FXML
    private ToggleGroup Difficulty;

    @FXML
    private Pane cardTable;

    public SpiderController() {

    }

    //修改游戏难点，默认简单
    @FXML
    void changeDifficulty(ActionEvent event) {

    }

    //退出游戏
    @FXML
    void end(ActionEvent event) {
        System.exit(0);
    }

    //读取游戏存档
    @FXML
    void fileReading(ActionEvent event) {

    }

    //提示可行操作
    @FXML
    void help(ActionEvent event) {

    }

    //规则
    @FXML
    void rule(ActionEvent event) {

    }

    //保存
    @FXML
    void saveGame(ActionEvent event) {

    }

    //开始游戏和重新开始
    @FXML
    void startTheGame(ActionEvent event) {
        //得到子节点
        getChildrenNode(cardTable);
        for (int i = 0; i < listPane.size(); i++) {
            //转换
            Pane pane = listPane.get(i);
            //清空上一局没有消除的卡牌
            pane.getChildren().clear();
            //创建卡牌
            if (i < 4) {
                for (int j = 0; j < 6; j++) {
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/static/images/rear.gif")));
                    pane.getChildren().add(imageView);
                }
                //渲染卡片位置
                location(pane, i);
            } else {
                for (int j = 0; j < 5; j++) {
                    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/static/images/rear.gif")));
                    pane.getChildren().add(imageView);
                }
                location(pane, i);
            }
        }
    }

    //撤回
    @FXML
    void withdraw(ActionEvent event) {

    }

    //定位纸牌位置
    void location(Pane pane, int n) {
        ObservableList<Node> card = pane.getChildren();
        for (Node node : card) {
            list.add((ImageView) node);
        }
        for (int i = 0; i < list.size(); i++) {
            if (n == 10) {
                list.get(i).setTranslateX(i * 5);
            } else {
                list.get(i).setTranslateY(i * 5);
            }
        }
        list.clear();
    }

    //补牌
    @FXML
    void supplementaryCard(MouseEvent event) {
        if (!scard.getChildren().isEmpty()){
            for (int i = 0; i < listPane.size(); i++) {
                ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/static/images/rear.gif")));
                if (i != 10) {
                    listPane.get(i).getChildren().add(imageView);
                    location(listPane.get(i),1);
                }else {
                    listPane.get(i).getChildren().remove(listPane.get(i).getChildren().size() - 1);
                }
            }
            scNumber--;
        }
    }

    //得到子节点
    void getChildrenNode(Pane pane) {
        allCard = pane.getChildren();
        //清除之前储存的节点
        listPane.clear();
        for (Node node : allCard) {
            listPane.add((Pane) node);
        }
    }
}
