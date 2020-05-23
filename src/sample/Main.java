package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static sample.MessageProcessing.posX;
import static sample.MessageProcessing.posY;
import static sample.dataController.*;

public class Main extends Application {
    public static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();


    Stage secendryStage = new Stage();
    Stage tertiaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        Thread t = new server(1690);
        t.start();

        Parent graphRoot = FXMLLoader.load(getClass().getResource("graph.fxml"));
        primaryStage.setTitle("graphs");
        primaryStage.setScene(new Scene(graphRoot, screenBounds.getWidth(), screenBounds.getHeight()));
        primaryStage.show();

        Parent dataRoot = FXMLLoader.load(getClass().getResource("data.fxml"));
        secendryStage.setTitle("data");
        secendryStage.setScene(new Scene(dataRoot));
        secendryStage.show();

        //tertiaryStage.setTitle("field");
        //tertiaryStage.setScene(field());
        //tertiaryStage.show();


    }

    /*private Path path;
    private MoveTo startPos;
    private Scene field(){
        path = new Path();
        startPos = new MoveTo();
        ArrayList<LineTo> line = new ArrayList<LineTo>();

        path.setStroke(Color.rgb(103,58,183));
        path.setStrokeWidth(10);

        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Platform.runLater(() -> {
                path.getElements().add(startPos);

                for (int i = 0; i< posX.size(); i++){
                    if (startPos == null) {
                        startPos = new MoveTo(posX.get(1), posY.get(1));
                    }
                    line.add(i, new LineTo(posX.get(i), posY.get(i)));
                    path.getElements().add(line.get(i));

                }
                System.out.println(posX.size()+", "+ posY.size());
            });
        },0,200, TimeUnit.MILLISECONDS);

        ImageView fieldBackground;
        File fieldFile = new File("image/field2.jpeg");
        Image fieldImage = new Image(fieldFile.toURI().toString());
        fieldBackground = new ImageView();
        fieldBackground.setImage(fieldImage);
        fieldBackground.setPreserveRatio(true);

        Group root = new Group();

        root.getChildren().addAll(fieldBackground, path);


        return new Scene(root, fieldImage.getWidth(), screenBounds.getHeight()-25);
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
