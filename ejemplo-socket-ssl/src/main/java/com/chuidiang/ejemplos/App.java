package com.chuidiang.ejemplos;

import java.io.IOException;

/**
 * Example with SSL sockets using system properties.
 */
public class App {
   public static void main(String[] args) throws IOException {
      System.setProperty("javax.net.ssl.keyStore", "src/main/certs/server/serverKey.jks");
      System.setProperty("javax.net.ssl.keyStorePassword","servpass");
//      System.setProperty("javax.net.ssl.trustStore", "src/main/certs/server/serverTrustedCerts.jks");
//      System.setProperty("javax.net.ssl.trustStorePassword", "servpass");
      System.setProperty("javax.net.ssl.trustStore", "src/main/certs/client/clientTrustedCerts.jks");
      System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");
      
      new SSLDefaultServerSocket(5557).start();

//      System.setProperty("javax.net.ssl.keyStore", "src/main/certs/client/clientKey.jks");
//      System.setProperty("javax.net.ssl.keyStorePassword","clientpass");
//      System.setProperty("javax.net.ssl.trustStore", "src/main/certs/client/clientTrustedCerts.jks");
//      System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");
      new SSLDefaultClientSocket("localhost",5557).start();
   }
}
