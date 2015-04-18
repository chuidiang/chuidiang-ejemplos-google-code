package com.chuidiang.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

public class SSLDefaultServerSocket {
   private ServerSocket serverSocket;

   public SSLDefaultServerSocket(int port) throws IOException {
      SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory
            .getDefault();
      serverSocket = serverFactory.createServerSocket(port);
   }

   public void start() {
      System.out.println("server start");
      new Thread() {
         public void run() {
            try {
               Socket aClient = serverSocket.accept();
               System.out.println("client accepted");
               aClient.setSoLinger(true, 1000);
               BufferedReader input = new BufferedReader(new InputStreamReader(
                     aClient.getInputStream()));
               String recibido = input.readLine();
               System.out.println("Recibido " + recibido);
               PrintWriter output = new PrintWriter(aClient.getOutputStream());
               output.println("Hello, " + recibido);
               output.flush();
               aClient.close();
            } catch (Exception e) {
               e.printStackTrace();
            }

         }
      }.start();
   }
}
