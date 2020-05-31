package dashboard;

import java.io.FileWriter;
import java.io.IOException;

public class csv {
    public static void write(double kp1, double ki1, double kd1, double s1, double mip1, double map1,
                             double kp2, double ki2, double kd2, double s2, double mip2, double map2){
        try {
            FileWriter file = new FileWriter("data.csv", true);
            file.write(kp1+",");
            file.write(ki1+",");
            file.write(kd1+",");
            file.write(s1+",");
            file.write(mip1+",");
            file.write(map1+",");
            file.write(kp2+",");
            file.write(ki2+",");
            file.write(kd2+",");
            file.write(s2+",");
            file.write(mip2+",");
            file.write(map2+"\n");
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
