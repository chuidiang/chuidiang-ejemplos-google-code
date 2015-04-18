package com.chuidiang.ejemplos;

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ssl.SSLServerSocketFactory;

public class SSLDefaultServerSocket {
   private ServerSocket serverSocket;

   public SSLDefaultServerSocket(int port) throws IOException {
      SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory
            .getDefault();
      serverSocket = serverFactory.createServerSocket(port);
   }

   public void start() {
      Util.startServerWorking(serverSocket);
   }
}
