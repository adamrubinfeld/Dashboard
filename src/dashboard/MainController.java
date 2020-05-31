package dashboard;

import dashboard.tcp.ComputerDebugging;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static dashboard.tcp.ComputerDebugging.messageBuilder;
import static dashboard.tcp.MessageProcessing.*;

public class MainController {
    @FXML private LineChart<CategoryAxis, NumberAxis> drive;
    @FXML private LineChart<CategoryAxis, NumberAxis> angle;
    @FXML private LineChart<CategoryAxis, NumberAxis> rpm;
    @FXML private LineChart<CategoryAxis, NumberAxis> ticks;

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


    @FXML private Slider Kp1S;
    @FXML private Label Kp1T;
    @FXML private Slider Ki1S;
    @FXML private Label Ki1T;
    @FXML private Slider Kd1S;
    @FXML private Label Kd1T;
    @FXML private Slider s1S;
    @FXML private Label s1T;
    @FXML private Slider mip1S;
    @FXML private Label mip1T;
    @FXML private Slider map1S;
    @FXML private Label map1T;

    @FXML private Slider Kp2S;
    @FXML private Label Kp2T;
    @FXML private Slider Ki2S;
    @FXML private Label Ki2T;
    @FXML private Slider Kd2S;
    @FXML private Label Kd2T;
    @FXML private Slider s2S;
    @FXML private Label s2T;
    @FXML private Slider mip2S;
    @FXML private Label mip2T;
    @FXML private Slider map2S;
    @FXML private Label map2T;

    private double lastP1 = 0;
    private double lastI1 = 0;
    private double lastD1 = 0;
    private double lastMiP1 = 0;
    private double lastMaP1 = 0;
    private double lastMaS1 = 0;

    private double lastP2 = 0;
    private double lastI2 = 0;
    private double lastD2 = 0;
    private double lastMiP2 = 0;
    private double lastMaP2 = 0;
    private double lastMaS2 = 0;

    private static final int WINDOW_SIZE = 13;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss:SS");

    public void initialize(){

        lf.setName("lf");
        rf.setName("rf");
        lb.setName("lb");
        rb.setName("rb");
        anglel.setName("degree");
        horizontalRPM.setName("horizontal");
        verticalRPM.setName("vertical");
        verticalTarget.setName("vt");
        verticalCurrent.setName("vc");
        horizontalTarget.setName("ht");
        horizontalCurrent.setName("hc");

        drive.getData().addAll(lf,rf,lb,rb);
        angle.getData().addAll(anglel);
        rpm.getData().addAll(horizontalRPM, verticalRPM);
        ticks.getData().addAll(verticalTarget, verticalCurrent, horizontalTarget, horizontalCurrent);

        drive.setAnimated(false);
        angle.setAnimated(false);
        rpm.setAnimated(false);
        ticks.setAnimated(false);

        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Platform.runLater(() -> {
                Date now = new Date();
                addDataToList(lf, now, driveData[0]);
                addDataToList(rf, now, driveData[1]);
                addDataToList(lb, now, driveData[2]);
                addDataToList(rb, now, driveData[3]);
                addDataToList(anglel, now, driveData[4]);
                addDataToList(horizontalRPM, now, horizontalData[0]);
                addDataToList(verticalRPM, now, verticalData[0]);
                addDataToList(horizontalCurrent, now, horizontalData[1]);
                addDataToList(horizontalTarget, now, horizontalData[2]);
                addDataToList(verticalCurrent, now, verticalData[1]);
                addDataToList(verticalTarget, now, verticalData[2]);
                if (lf.getData().size() > WINDOW_SIZE) {

                    lf.getData().remove(0);
                    rf.getData().remove(0);
                    lb.getData().remove(0);
                    rb.getData().remove(0);
                    anglel.getData().remove(0);
                    horizontalRPM.getData().remove(0);
                    verticalRPM.getData().remove(0);
                    horizontalTarget.getData().remove(0);
                    horizontalCurrent.getData().remove(0);
                    verticalTarget.getData().remove(0);
                    verticalCurrent.getData().remove(0);

                }


                setLabelValue(Kp1T, Kp1S);
                setLabelValue(Ki1T, Ki1S);
                setLabelValue(Kd1T, Kd1S);
                setLabelValue(s1T, s1S);
                setLabelValue(mip1T, mip1S);
                setLabelValue(map1T, map1S);
                setLabelValue(Kp2T, Kp2S);
                setLabelValue(Ki2T, Ki2S);
                setLabelValue(Kd2T, Kd2S);
                setLabelValue(s2T, s2S);
                setLabelValue(mip2T, mip2S);
                setLabelValue(map2T, map2S);

                double P1    = Kp1S.getValue();
                double I1    = Ki1S.getValue();
                double D1    = Kd1S.getValue();
                double mip1  = mip1S.getValue();
                double map1  = map1S.getValue();
                double mas1  = s1S.getValue();
                double P2    = Kp2S.getValue();
                double I2    = Ki2S.getValue();
                double D2    = Kd2S.getValue();
                double mip2  = mip2S.getValue();
                double map2  = map2S.getValue();
                double mas2  = s2S.getValue();
                if (P1!=lastP1||I1!=lastI1||D1!=lastD1){
                    ComputerDebugging.sendPIDVertical(P1, I1, D1);
                }
                if (P2!=lastP2||I2!=lastI2||D2!=lastD2){
                    ComputerDebugging.sendPIDHorizontal(P2, I2, D2);
                }
                if (mip1!=lastMiP1||map1!=lastMaP1||mas1!=lastMaS1){
                    ComputerDebugging.sendDataVertical(mip1, map1, mas1);
                }
                if (mip2!=lastMiP2||map2!=lastMaP2||mas2!=lastMaS2){
                    ComputerDebugging.sendDataVertical(mip2, map2, mas2);
                }
                if (!messageBuilder.toString().equals("")){
                    messageBuilder.append("CLEAR,%");
                    ComputerDebugging.markEndOfUpdate();
                }

                lastP1 =    P1;
                lastI1 =    I1;
                lastD1 =    D1;
                lastMiP1 =  mip1 ;
                lastMaP1 =  map1 ;
                lastMaS1 =  mas1 ;
                lastP2    = P2   ;
                lastI2    = I2   ;
                lastD2    = D2   ;
                lastMiP2  = mip2 ;
                lastMaP2  = map2 ;
                lastMaS2  = mas2 ;
            });
        },0,200, TimeUnit.MILLISECONDS);
    }


    private void setLabelValue(Label l, Slider s){
        l.textProperty().bind(Bindings.format("%.2f", s.valueProperty()));
    }

    private void addDataToList(XYChart.Series s, Date x, double y){
        s.getData().add(new XYChart.Data<>(simpleDateFormat.format(x), y));
    }
}
