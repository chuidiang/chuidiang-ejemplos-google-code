package com.chuidiang.ejemplos;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiClient {

   public static void main(String[] args) throws NamingException {
      final Hashtable<String, String> env = new Hashtable<String, String>();

      env.put("java.naming.factory.initial",
            "org.jnp.interfaces.NamingContextFactory");
      env.put("java.naming.provider.url", "jnp://192.168.1.2:5400");

      Context context = new InitialContext(env);

      System.out.println("Application name = "
            + context.lookup("java:/config/applicationName"));
      System.out.println("someData = " + context.lookup("java:/config/clase"));

   }

}
