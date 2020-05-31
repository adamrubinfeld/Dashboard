package dashboard.tcp;

import java.text.DecimalFormat;

public class ComputerDebugging {
    public static StringBuilder messageBuilder = new StringBuilder();
    private static DecimalFormat df = new DecimalFormat("#.00");

    public static void markEndOfUpdate(){
        messageBuilder.append("CLEAR,%");
        server.send(messageBuilder.toString());
        messageBuilder.delete(0, messageBuilder.length());
    }

    public static void  sendPIDVertical(double kp, double ki, double kd){
        messageBuilder.append("PIDV,");
        messageBuilder.append(df.format(kp));
        messageBuilder.append(",");
        messageBuilder.append(df.format(ki));
        messageBuilder.append(",");
        messageBuilder.append(df.format(kd));
        messageBuilder.append("%");
    }

    public static void sendDataVertical(double minPosition, double maxPosition, double maxSpeed){
        messageBuilder.append("DATAV,");
        messageBuilder.append(df.format(minPosition));
        messageBuilder.append(",");
        messageBuilder.append(df.format(maxPosition));
        messageBuilder.append(",");
        messageBuilder.append(df.format(maxSpeed));
        messageBuilder.append("%");
    }

    public static void sendPIDHorizontal(double kp, double ki, double kd){
        messageBuilder.append("PIDH,");
        messageBuilder.append(df.format(kp));
        messageBuilder.append(",");
        messageBuilder.append(df.format(ki));
        messageBuilder.append(",");
        messageBuilder.append(df.format(kd));
        messageBuilder.append("%");
    }

    public static void sendDataHorizontal(double minPosition, double maxPosition, double maxSpeed){
        messageBuilder.append("DATAH,");
        messageBuilder.append(df.format(minPosition));
        messageBuilder.append(",");
        messageBuilder.append(df.format(maxPosition));
        messageBuilder.append(",");
        messageBuilder.append(df.format(maxSpeed));
        messageBuilder.append("%");
    }
}
