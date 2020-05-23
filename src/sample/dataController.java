package sample;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import sample.ComputerDebugging;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static sample.ComputerDebugging.messageBuilder;

public class dataController {
    @FXML
    private Slider kpv;
    @FXML
    private Label kpvt;

    @FXML
    private Slider kiv;
    @FXML
    private Label kivt;

    @FXML
    private Slider kdv;
    @FXML
    private Label kdvt;

    @FXML
    private Slider mipv;
    @FXML
    private Label mipvt;

    @FXML
    private Slider mapv;
    @FXML
    private Label mapvt;

    @FXML
    private Slider masv;
    @FXML
    private Label masvt;



    @FXML
    private Slider kph;
    @FXML
    private Label kpht;

    @FXML
    private Slider kih;
    @FXML
    private Label kiht;

    @FXML
    private Slider kdh;
    @FXML
    private Label kdht;

    @FXML
    private Slider miph;
    @FXML
    private Label mipht;

    @FXML
    private Slider maph;
    @FXML
    private Label mapht;

    @FXML
    private Slider mash;
    @FXML
    private Label masht;


    double lastPV = 0;
    double lastIV = 0;
    double lastDV = 0;
    double lastMiPV = 0;
    double lastMaPV = 0;
    double lastMiSV = 0;
    double lastMaSV = 0;
    double lastStart = 0;
    double lastCube = 0;

    double lastPH = 0;
    double lastIH = 0;
    double lastDH = 0;
    double lastMiPH = 0;
    double lastMaPH = 0;
    double lastMiSH = 0;
    double lastMaSH = 0;

    public void initialize(){
        kpvt.textProperty().bind(data(kpv));
        kivt.textProperty().bind(data(kiv));
        kdvt.textProperty().bind(data(kdv));
        mipvt.textProperty().bind(data(mipv));
        mapvt.textProperty().bind(data(mapv));
        masvt.textProperty().bind(data(masv));

        kpht.textProperty().bind(data(kph));
        kiht.textProperty().bind(data(kih));
        kdht.textProperty().bind(data(kdh));
        mipht.textProperty().bind(data(miph));
        mapht.textProperty().bind(data(maph));
        masht.textProperty().bind(data(mash));

        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            Platform.runLater(() -> {
                double PV    = kpv.getValue();
                double IV    = kiv.getValue();
                double DV    = kdv.getValue();
                double mipV  = mipv.getValue();
                double mapV  = mapv.getValue();
                double masV  = masv.getValue();

                double PH    = kph.getValue();
                double IH    = kih.getValue();
                double DH    = kdh.getValue();
                double mipH  = miph.getValue();
                double mapH  = maph.getValue();
                double masH  = mash.getValue();
                if (PV!=lastPV||IV!=lastIV||DV!=lastDV){
                    ComputerDebugging.sendPIDVertical(kpv.getValue(), kiv.getValue(), kdv.getValue());
                }
                if (PH!=lastPH||IH!=lastIH||DH!=lastDH){
                    ComputerDebugging.sendPIDHorizontal(kph.getValue(), kih.getValue(), kdh.getValue());
                }
                if (mipH!=lastMiPH||mapH!=lastMaPH||masH!=lastMaSH){
                    ComputerDebugging.sendDataHorizontal(miph.getValue(), maph.getValue(), mash.getValue());
                }
                if (mipV!=lastMiPV||mapV!=lastMaPV||masV!=lastMaSV){
                    ComputerDebugging.sendDataVertical(mipv.getValue(), mapv.getValue(), masv.getValue());
                }
                if (!messageBuilder.toString().equals("")){
                    messageBuilder.append("CLEAR,%");
                    ComputerDebugging.markEndOfUpdate();
                }

                lastPV =    PV;
                lastIV =    IV;
                lastDV =    DV;
                lastMiPV =  mipV ;
                lastMaPV =  mapV ;
                lastMaSV =  masV ;

                lastPH    = PH   ;
                lastIH    = IH   ;
                lastDH    = DH   ;
                lastMiPH  = mipH ;
                lastMaPH  = mapH ;
                lastMaSH  = masH ;
            });
        },0,200, TimeUnit.MILLISECONDS);
    }

    private StringExpression data (Slider slider){
        return Bindings.format(
                "%.2f",
                slider.valueProperty()
        );
    }
}
