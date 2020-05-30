package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;
import java.net.InetAddress;

public class Main extends Application {



    private static boolean showGraphWindow = true;
    private static boolean showDataWindow = true;

    public static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();


    Stage secendryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        //TODO enter here your team number
        Thread t = new server(14872);
        t.start();




        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());

        if (showGraphWindow) {
            Parent graphRoot = FXMLLoader.load(getClass().getResource("graph.fxml"));
            primaryStage.setTitle("graphs");
            primaryStage.setScene(new Scene(graphRoot, screenBounds.getWidth(), screenBounds.getHeight()));
            primaryStage.getIcons().add(logoImage);
            primaryStage.show();
        }

        if (showDataWindow){
            Parent dataRoot = FXMLLoader.load(getClass().getResource("data.fxml"));
            secendryStage.setTitle("data");
            secendryStage.setScene(new Scene(dataRoot));
            secendryStage.getIcons().add(logoImage);
            secendryStage.show();
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
