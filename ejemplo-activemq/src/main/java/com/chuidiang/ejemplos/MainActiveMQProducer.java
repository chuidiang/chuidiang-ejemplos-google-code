package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.network.NetworkConnector;
import org.apache.activemq.util.ServiceStopper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainActiveMQProducer {

   private static BrokerService brokerService;
   private static Connection connection;
   private static Session session;
   private static MessageProducer pingProducer;
   private static MessageConsumer pongConsumer;

   public static void main(String[] args) throws Exception {
      configureLogger();
      createAndStartActiveMQBroker();
      createActiveMQSession();

      pingProducer = Util.createQueueProducer(session, "PING");
      pongConsumer = Util.createQueueConsumer(session, "PONG");

      pongConsumer.setMessageListener(new MessageListener() {
         public void onMessage(Message message) {
            if (message instanceof TextMessage) {
               try {
                  TextMessage textMessage = (TextMessage) message;
                  String text = textMessage.getText();
                  int value = Integer.parseInt(text);
                  System.out.println("pong = " + value);
                  Util.sendText(session, pingProducer,
                        Integer.toString(value + 1));
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }

         }
      });

      Util.sendText(session, pingProducer, "1");

      System.in.read();
      closeAll();
   }

   private static void closeAll() throws JMSException, Exception {
      System.out.println("Finishing...");
      pingProducer.close();
      pongConsumer.close();
      session.close();
      connection.close();
      brokerService.stopAllConnectors(new ServiceStopper());
      brokerService.stop();
   }

   private static void createActiveMQSession() throws JMSException {
      ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
            "tcp://localhost:61617");

      connection = connectionFactory.createConnection();
      connection.start();

      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
   }

   private static void createAndStartActiveMQBroker() throws Exception {
      brokerService = new BrokerService();
      brokerService.setBrokerName("producer");
      brokerService.addConnector("tcp://localhost:61617");
      brokerService.addNetworkConnector("static://tcp://localhost:61616");
      brokerService.setPersistent(false);
      brokerService.start();
   }

   private static void configureLogger() {
      BasicConfigurator.configure();
      Logger.getRootLogger().setLevel(Level.INFO);
   }
}
