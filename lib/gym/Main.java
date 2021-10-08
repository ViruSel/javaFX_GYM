package gym;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application
{
    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/Main.fxml")));
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            stage = primaryStage;
            primaryStage.show();
            primaryStage.setResizable(true);
        }
        catch (Exception e) {e.printStackTrace();}
    }

    public static void main(String[] args) { launch(args); }
}