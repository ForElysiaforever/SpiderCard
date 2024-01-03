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
import java.util.Objects;

public class Tool {  //通过游戏逻辑完成图形化界面
    CardTool cardTool = new CardTool();
    double mouseX, mouseY;  //鼠标坐标
    double initialLayoutX, initialLayoutY;  //卡牌初始坐标
    int clickI, clickJ; //记录被点击卡牌的索引


    public void getDeckPane(Pane pane, List<Pane> paneList) {
        ObservableList<Node> childNodes = pane.getChildren();  //得到所有牌堆节点
        paneList.addAll(conversionPane(childNodes));
    }

    public List<Pane> conversionPane(ObservableList<Node> childNodes) {  //将子节点转化
        List<Pane> list = new ArrayList<>();
        for (Node node : childNodes) {
            list.add((Pane) node);
        }
        return list;
    }

    public ImageView returnImage(Card card) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/static/images/rear.gif"))));
        if (card.isCardFace()) {
            return card.getImageView();
        } else {
            return imageView;
        }
    }

    public void location(Pane pane, List<Card> cardList) {  //给卡牌定位
        ObservableList<Node> imageViewList = pane.getChildren();
        List<ImageView> imageList = new ArrayList<>();
        int number = 0;  //记录个数
        int high;  //记录背面高度
        for (int i = 0; i < imageViewList.size(); i++) {
            imageList.add((ImageView) imageViewList.get(i));
            if (!cardList.get(i).isCardFace()) {
                number++;
            }
        }
        high = (number) * 5;
        if (number == 0) {
            high = 0;
        }
        for (int i = 0; i < imageList.size(); i++) {
            Card card = cardList.get(i);
            if (!card.isCardFace()) {
                imageList.get(i).setLayoutY(i * 5);
            } else {
                imageList.get(i).setLayoutY(high + (i - number) * 20);
            }
        }
    }

    public void locationSCard(Pane pane) {  //给补牌堆定位
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
        cardTool.initializeRCard(newGame);
        return newGame;
    }

    public void renderedDeck(List<Pane> paneList, List<List<Card>> cardList) {  //渲染牌堆
        for (int i = 0; i < paneList.size(); i++) {
            paneList.get(i).getChildren().clear();
        }
        for (int i = 0; i < cardList.size(); i++) {
            Pane pane = paneList.get(i);
            List<Card> list = cardList.get(i);
            pane.getChildren().clear();
            for (int j = 0; j < list.size(); j++) {
                if (j == list.size() - 1) {
                    list.get(j).setCardFace(true);
                }
                pane.getChildren().add(returnImage(list.get(j)));
            }
            location(pane, list);
        }
    }

    public void initializeDragAndDrop(List<List<Card>> mobileCards, Pane front, List<Pane> paneList, List<List<Card>> cardList) {  //卡牌的移动效果
        for (int i = 0; i < mobileCards.size(); i++) {
            List<Card> list = mobileCards.get(i);
            for (int j = 0; j < list.size(); j++) {
                int finalI = i;
                int finalJ = j;
                ImageView imageView = list.get(finalJ).getImageView();
                imageView.setOnMousePressed(event -> {
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    clickI = finalI;
                    clickJ = finalJ;
                    initialLayoutX = imageView.getParent().getLayoutX();
                    initialLayoutY = imageView.getLayoutY() + 46;
                    removeImage(paneList , clickI, list.size() - clickJ, front);
                    front.setLayoutY(initialLayoutY);
                    front.setLayoutX(initialLayoutX);
                    location(front);
                });
                front.setOnMouseDragged(event -> {
                    double deltaX = event.getSceneX() - mouseX;
                    double deltaY = event.getSceneY() - mouseY;
                    front.setLayoutX(initialLayoutX + deltaX);
                    front.setLayoutY(initialLayoutY + deltaY);
                });
                front.setOnMouseReleased(event -> {  // 返回到初始位置
                    mouseX = event.getSceneX();
                    mouseY = event.getSceneY();
                    Pane pane = null;
                    for (Pane pane1 : paneList) {
                        if (pane1.getBoundsInParent().contains(mouseX, mouseY)){
                            pane = pane1;
                            break;
                        }
                    }
                    int index = paneList.indexOf(pane);
                    moveImage(paneList, index, front);
                    front.getChildren().clear();
                    removeFromDeck(cardList, clickI, list.size() - clickJ, index);
                    removeEvent(mobileCards);
                    renderedDeck(paneList, cardList);
                    cardTool.determineIfTheCardCanBeMoved(cardList, mobileCards);
                    initializeDragAndDrop(mobileCards, front, paneList, cardList);
                });
            }
        }
    }

    public void location(Pane front) {  //给front的卡牌定位
        ObservableList<Node> imageViewList = front.getChildren();
        List<ImageView> imageList = new ArrayList<>();
        for (Node node : imageViewList) {
            imageList.add((ImageView) node);
        }
        for (int i = 0; i < imageList.size(); i++) {
            imageList.get(i).setLayoutY(i * 17);
        }
    }

    public void removeFromDeck(List<List<Card>> deckList, int clickI, int clickJ, int index) {
        List<Card> cardList = deckList.get(clickI);
        int position = cardList.size() - clickJ;
        while (cardList.size() > position){
            deckList.get(index).add(cardList.remove(position));
        }
    }

    public void removeImage(List<Pane> paneList, int clickI, int number, Pane front) {
        Pane pane = paneList.get(clickI);
        int position = pane.getChildren().size() - number;
        while (pane.getChildren().size() > position){
            front.getChildren().add(pane.getChildren().remove(position));
        }
    }
    public void moveImage(List<Pane> paneList, int index, Pane front){
        System.out.println(index);
        Pane pane = paneList.get(index);
        while (!front.getChildren().isEmpty()){
            pane.getChildren().addAll(front.getChildren());
        }
    }

    public void removeEvent(List<List<Card>> mobileCards) {  //清除事件
        for (List<Card> list : mobileCards) {
            for (Card card : list) {
                card.getImageView().setOnMousePressed(null);
            }
        }
    }
}
