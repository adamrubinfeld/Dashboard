package sample;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class MessageProcessing {

    public static double[] driveData = new double[5];
    public static double[] horizontalData = new double[3];
    public static double[] verticalData = new double[3];



    public MessageProcessing(){

    }

    public static void processMessage(String receivedMessage) {
        //System.out.println("RECEIVED: " + receivedMessage);


        String[] splitMessages = receivedMessage.split("%");


        for(String message : splitMessages){
            String[] splitString = message.split(",");
            String id = splitString[0];

            if(id.equals("DRIVE")){
                processDrive(splitString);
            }else{
                if(id.equals("HORIZONTAL")){
                    processHorizontal(splitString);
                }else{
                    if(id.equals("VERTICAL")){
                        processVertical(splitString);
                    }else{
                        if (id.equals("FRAME")){
                            processFrame(splitString);
                        }

                    }
                }
            }
        }
    }


    private static void processDrive(String[] splitString){
        driveData[0] = parseDouble(splitString[1]);
        driveData[1] = parseDouble(splitString[2]);
        driveData[2] = parseDouble(splitString[3]);
        driveData[3] = parseDouble(splitString[4]);
        driveData[4] = parseDouble(splitString[5]);
    }

    private static void processHorizontal(String[] splitString){
        horizontalData[0] = parseDouble(splitString[1]);
        horizontalData[1] = parseDouble(splitString[2]);
        horizontalData[2] = parseDouble(splitString[3]);
    }

    private static void processVertical(String[] splitString) {
        verticalData[0] = parseDouble(splitString[1]);
        verticalData[1] = parseDouble(splitString[2]);
        verticalData[2] = parseDouble(splitString[3]);
    }

    private static void processFrame(String[] splitString){

    }


    private static double toPX(double cm){
        return cm;
        //return (cm * Screen.getPrimary().getDpi())/2.54;
    }
}