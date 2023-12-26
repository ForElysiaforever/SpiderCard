import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Objects;

public class Spider extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("templates/spider.fxml")));
        primaryStage.setTitle("蜘蛛纸牌");
        Pane container = (Pane) root.lookup("#card1");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }
}
