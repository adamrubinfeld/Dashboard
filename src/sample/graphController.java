package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static sample.Main.screenBounds;
import static sample.MessageProcessing.*;

public class graphController {

    @FXML
    GridPane grid;

    @FXML
    LineChart<CategoryAxis, Number> drive;
    @FXML
    CategoryAxis drivex;
    @FXML
     NumberAxis drivey;

    @FXML
     LineChart<CategoryAxis, Number> angle;
    @FXML
    CategoryAxis anglex;
    @FXML
     NumberAxis angley;

    @FXML
     LineChart<CategoryAxis, Number> liftRPM;
    @FXML
    CategoryAxis liftrpmx;
    @FXML
     NumberAxis liftrpmy;

    @FXML
     LineChart<CategoryAxis, Number> liftticks;
    @FXML
    CategoryAxis liftticksx;
    @FXML
     NumberAxis liftticksy;

     XYChart.Series lf = new XYChart.Series();
     XYChart.Series rf = new XYChart.Series();
     XYChart.Series lb = new XYChart.Series();
     XYChart.Series rb = new XYChart.Series();

     XYChart.Series verticalRPM = new XYChart.Series();
     XYChart.Series horizontalRPM = new XYChart.Series();

     XYChart.Series horizontalTarget = new XYChart.Series();
     XYChart.Series horizontalCurrent = new XYChart.Series();
     XYChart.Series verticalTarget = new XYChart.Series();
     XYChart.Series verticalCurrent = new XYChart.Series();

     XYChart.Series anglel = new XYChart.Series();

    static int WINDOW_SIZE = 13;
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss:SS");

    @FXML
    public void initialize(){
        grid.setMaxSize(screenBounds.getWidth()-10, screenBounds.getHeight()-10);
        drive.setPrefSize(screenBounds.getWidth()/2, screenBounds.getHeight()/2);
        angle.setPrefSize(screenBounds.getWidth()/2, screenBounds.getHeight()/2);
        liftRPM.setPrefSize(screenBounds.getWidth()/2, screenBounds.getHeight()/2);
        liftticks.setPrefSize(screenBounds.getWidth()/2, screenBounds.getHeight()/2);

        lf.setName("lf");
        rf.setName("rf");
        lb.setName("lb");
        rb.setName("rb");

        horizontalRPM.setName("horizontal");
        verticalRPM.setName("vertical");

        verticalTarget.setName("vt");
        verticalCurrent.setName("vc");
        horizontalTarget.setName("ht");
        horizontalCurrent.setName("hc");

        anglel.setName("degree");

        drive.getData().addAll(lf,rf,lb,rb);
        liftRPM.getData().addAll(horizontalRPM, verticalRPM);
        angle.getData().addAll(anglel);
        liftticks.getData().addAll(verticalTarget, verticalCurrent, horizontalTarget, horizontalCurrent);

        angle.setLegendVisible(false);
        liftRPM.setLegendVisible(false);
        //drive.setLegendVisible(false);
        //liftticks.setLegendVisible(false);

        drive.setAnimated(false);
        liftticks.setAnimated(false);
        liftRPM.setAnimated(false);
        angle.setAnimated(false);



        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Platform.runLater(() -> {
                Date now = new Date();
                lf.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), driveData[0]));
                rf.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), driveData[2]));
                lb.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), driveData[1]));
                rb.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), driveData[3]));

                horizontalRPM.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), horizontalData[0]));
                verticalRPM.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), verticalData[0]));

                horizontalCurrent.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), horizontalData[1]));
                horizontalTarget.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), horizontalData[2]));
                verticalCurrent.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), verticalData[1]));
                verticalTarget.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), verticalData[2]));

                anglel.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), driveData[4]));

                if (lf.getData().size() > WINDOW_SIZE) {

                    lf.getData().remove(0);
                    rf.getData().remove(0);
                    lb.getData().remove(0);
                    rb.getData().remove(0);

                    horizontalRPM.getData().remove(0);
                    verticalRPM.getData().remove(0);

                    horizontalTarget.getData().remove(0);
                    horizontalCurrent.getData().remove(0);
                    verticalTarget.getData().remove(0);
                    verticalCurrent.getData().remove(0);

                    anglel.getData().remove(0);
                }

            });
        },0,200, TimeUnit.MILLISECONDS);
    }
}
