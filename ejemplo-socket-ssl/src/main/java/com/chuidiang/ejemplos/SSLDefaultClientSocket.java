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

   public SSLDefaultClientSocket(String server, int port)
         throws UnknownHostException, IOException {
      SSLSocketFactory clientFactory = (SSLSocketFactory) SSLSocketFactory
            .getDefault();
      client = clientFactory.createSocket(server, port);
   }

   public void start() {
      Util.startClientWorking(client);
   }
}
