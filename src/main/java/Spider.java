import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Spider extends Application {
    ObservableList<Node> children;
    List<ImageView> list = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("templates/spider.fxml")));
        primaryStage.setTitle("蜘蛛纸牌");
//        Pane container = (Pane) root.lookup("#card1");
//        children = container.getChildren();
//        for (int i = 0; i < children.size(); i++) {
//            list.add((ImageView) children.get(i));
//        }
//        for (int i = 0; i < list.size(); i++) {
//            if (i == list.size() - 2){
//                list.get(i).setImage();
//            }
//        }
        primaryStage.setScene(new Scene(root, 975, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
