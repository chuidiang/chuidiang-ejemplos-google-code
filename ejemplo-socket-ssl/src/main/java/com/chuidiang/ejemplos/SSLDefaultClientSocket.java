package com.chuidiang.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

public class SSLDefaultClientSocket {
   Socket client = null;
   public SSLDefaultClientSocket(String server,int port) throws UnknownHostException, IOException{
      SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      client = clientFactory.createSocket(server,port);
   }

   public void start() {
      System.out.println("client start");
      new Thread() {
         public void run(){
            try {
               PrintWriter output = new PrintWriter(client.getOutputStream());
               output.println("Federico");
               output.flush();
               System.out.println("Federico sent");
               BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
               String received = input.readLine();
               System.out.println("Received : "+received);
               client.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }      
         }
      }.start();
   }
}
