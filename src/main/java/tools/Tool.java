package tools;

import entity.Card;
import entity.Game;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Tool {  //通过游戏逻辑完成图形化界面
    double mouseX, mouseY;  //鼠标坐标
    double initialLayoutX, initialLayoutY;  //卡牌初始坐标

    public void getDeckPane(Pane pane, List<Pane> paneList) {
        ObservableList<Node> childNodes = pane.getChildren();  //得到所有牌堆节点
        paneList.addAll(conversionPane(childNodes));
    }

    public void getImageView(Pane pane, List<ImageView> imageList) {
        ObservableList<Node> childNodes = pane.getChildren();  //得到所有牌堆节点
        imageList.addAll(conversionImage(childNodes));
    }

    public List<ImageView> conversionImage(ObservableList<Node> childNodes) {  //将子节点转化
        List<ImageView> list = new ArrayList<>();
        for (Node node : childNodes) {
            list.add((ImageView) node);
        }
        return list;
    }

    public List<Pane> conversionPane(ObservableList<Node> childNodes) {  //将子节点转化
        List<Pane> list = new ArrayList<>();
        for (Node node : childNodes) {
            list.add((Pane) node);
        }
        return list;
    }

    public ImageView generateImage(Card card) {  //根据花色和值生成对于的牌
        String str;
        ImageView imageView = new ImageView();
        if (card.isCardFace()) {
            str = card.getFlowerColor() + "-" + card.getNumber();
        } else {
            str = "rear";
        }
        Image image = new Image(getClass().getResourceAsStream("/static/images/" + str + ".gif"));
        imageView.setImage(image);
        initializeDragAndDrop(imageView, card);
        return imageView;
    }

    public void location(Pane pane, int n) {  //给卡牌定位
        ObservableList<Node> imageViewList = pane.getChildren();
        List<ImageView> imageList = new ArrayList<>();
        for (Node node : imageViewList) {
            imageList.add((ImageView) node);
        }
        for (int i = 0; i < imageList.size(); i++) {
            if (n == 10) {
                imageList.get(i).setLayoutX(i * 5);
            } else {
                imageList.get(i).setLayoutY(i * 5);
            }
        }
    }

    public Game updateGame(CardTool cardTool) {  //更新
        Game newGame = new Game();
        cardTool.setCard(newGame);
        cardTool.setDeck(newGame);
        return newGame;
    }

    public void initializeDragAndDrop(ImageView imageView, Card card) {  //卡牌的移动效果
        if (card.isCardFace()) {  //只有卡牌正面向上
            imageView.setOnMousePressed(event -> {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                initialLayoutX = imageView.getLayoutX();
                initialLayoutY = imageView.getLayoutY();
            });

            imageView.setOnMouseDragged(event -> {
                double deltaX = event.getSceneX() - mouseX;
                double deltaY = event.getSceneY() - mouseY;
                imageView.setLayoutX(initialLayoutX + deltaX);
                imageView.setLayoutY(initialLayoutY + deltaY);
            });
        }
    }
    public void removeDragAndDrop(ImageView imageView){  //消除卡牌的拖动效果
        imageView.setOnMousePressed(null);
        imageView.setOnMouseDragged(null);
    }
}
