package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Thread{
     static ServerSocket ss;
     static Socket s;
     static DataInputStream dis;
     static DataOutputStream dos;

     public server(int port){
         try {
             ss = new ServerSocket(port);
             System.out.println();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public void run(){
         try {
             s = ss.accept();
             dis = new DataInputStream(s.getInputStream());
             dos = new DataOutputStream(s.getOutputStream());
             while (true) {
                 MessageProcessing.processMessage(get());
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public static void send(String msg){
         try {
             dos.writeUTF(msg);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public String get() throws IOException {
         String h = dis.readUTF();
         System.out.println(h);
         return h;
     }

     public static void close(){
         try {
             ss.close();
            s.close();
            dis.close();
            dos.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
