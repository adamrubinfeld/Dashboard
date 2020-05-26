package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.net.InetAddress;

public class Main extends Application {
    public static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();


    Stage secendryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        //TODO enter here your team number
        Thread t = new server(14872);
        t.start();

        Parent graphRoot = FXMLLoader.load(getClass().getResource("graph.fxml"));
        primaryStage.setTitle("graphs");
        primaryStage.setScene(new Scene(graphRoot, screenBounds.getWidth(), screenBounds.getHeight()));
        primaryStage.show();

        Parent dataRoot = FXMLLoader.load(getClass().getResource("data.fxml"));
        secendryStage.setTitle("data");
        secendryStage.setScene(new Scene(dataRoot));
        secendryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
