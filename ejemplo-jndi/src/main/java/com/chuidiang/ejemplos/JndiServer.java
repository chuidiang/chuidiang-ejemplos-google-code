package com.chuidiang.ejemplos;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jnp.server.Main;
import org.jnp.server.NamingBeanImpl;

public class JndiServer {

   public static void main(String[] args) throws Exception {
      startServer();
      storeSomeData(); 
      System.out.println("Ready");
   }

   private static void storeSomeData() throws NamingException {
      Hashtable<String, String> env = new Hashtable<String, String>();
      env.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
      env.put(Context.PROVIDER_URL,            "jnp://192.168.1.2:5400");
      Context context = new InitialContext(env);
      context.createSubcontext("config");
      context.bind("/config/applicationName", "MyApp");
      context.bind("/config/clase", new SomeData("pedro",4,new Date()));
   }

   private static void startServer() throws Exception, UnknownHostException {
      System.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");

      NamingBeanImpl jnpServer = new NamingBeanImpl();
      jnpServer.start();
      
      Main main = new Main();
      main.setNamingInfo(jnpServer);
      main.setPort(5400);
      main.setBindAddress(InetAddress.getLocalHost().getHostName());
      main.start();
   }

}
