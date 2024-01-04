package entity;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Card implements Serializable {
    private boolean cardFace = false;    //牌面true为正面false为背面
    private boolean isRemove = false;  //是否被消除
    private int flowerColor = 1;    //花色,1为黑桃,2为红桃,3为黑梅,4为红梅,默认难度简单
    private int number = 0;    //卡牌的值
    private transient  ImageView imageView;  //对应的卡牌

    public boolean isCardFace() {
        return cardFace;
    }

    public void setCardFace(boolean cardFace) {
        this.cardFace = cardFace;
    }

    public int getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(int flowerColor) {
        this.flowerColor = flowerColor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public void setRemove(boolean remove) {
        isRemove = remove;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardFace=" + cardFace +
                ", isRemove=" + isRemove +
                ", flowerColor=" + flowerColor +
                ", number=" + number +
                ", imageView=" + imageView +
                '}';
    }
}
