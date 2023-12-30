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
    int imagePosition, deckPanePosition;  //记录拖动操作对于卡牌的索引

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
        card.setImageView(imageView);
        return imageView;
    }

    public void location(Pane pane, int n, List<Card> cardList) {  //给卡牌定位
        ObservableList<Node> imageViewList = pane.getChildren();
        List<ImageView> imageList = new ArrayList<>();
        int number = 0;  //记录个数
        int high = 0;  //记录背面高度
        for (int i = 0; i < imageViewList.size(); i++) {
            imageList.add((ImageView) imageViewList.get(i));
            if (!cardList.get(i).isCardFace()){
                number++;
            }
        }
        high = (number) * 5;
        if (number == 0){
            high = 0;
        }
        for (int i = 0; i < imageList.size(); i++) {
            Card card = cardList.get(i);
            if (!card.isCardFace()){
                imageList.get(i).setLayoutY(i * 5);
            }else {
                System.out.println(high);
                imageList.get(i).setLayoutY(high + (i - number) * 17);
            }
        }
    }

    public void location(Pane pane) {  //给卡牌定位
        ObservableList<Node> imageViewList = pane.getChildren();
        List<ImageView> imageList = new ArrayList<>();
        for (Node node : imageViewList) {
            imageList.add((ImageView) node);
        }
        for (int i = 0; i < imageList.size(); i++) {
            imageList.get(i).setLayoutX(i * 5);
        }
    }

    public Game updateGame(CardTool cardTool) {  //更新
        Game newGame = new Game();
        cardTool.setCard(newGame);
        cardTool.setDeck(newGame);
        return newGame;
    }

    public void initializeDragAndDrop(ImageView imageView, Card card, List<Pane> paneList) {  //卡牌的移动效果
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
            imageView.setOnMouseReleased(event -> {  // 返回到初始位置
                imageView.setLayoutX(initialLayoutX);
                imageView.setLayoutY(initialLayoutY);
            });
        }
    }

    private Pane findTargetPane(double x, double y, List<Pane> paneList) {
        for (Node node : paneList) {
            if (node instanceof Pane) {
                Pane pane = (Pane) node;
                if (pane.getBoundsInParent().contains(x, y)) {
                    return pane;
                }
            }
        }
        return null;
    }

    public void removeDragAndDrop(ImageView imageView) {  //消除卡牌的拖动效果
        imageView.setOnMousePressed(null);
        imageView.setOnMouseDragged(null);
    }
}
