import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Spider spider = new Spider();
        spider.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
