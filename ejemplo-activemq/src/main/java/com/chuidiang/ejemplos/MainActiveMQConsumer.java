package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.util.ServiceStopper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainActiveMQConsumer {

   private static BrokerService brokerService;
   private static Connection connection;
   private static Session session;
   private static MessageProducer pongProducer;
   private static MessageConsumer pingConsumer;

   public static void main(String[] args) throws Exception {
      configureLogger();
      createAndStartActiveMQBroker();
      createActiveMQSession();

      pongProducer = Util.createQueueProducer(session, "PONG");
      pingConsumer = Util.createQueueConsumer(session, "PING");

      pingConsumer.setMessageListener(new MessageListener() {

         public void onMessage(Message message) {
            if (message instanceof TextMessage) {
               try {
                  TextMessage textMessage = (TextMessage) message;
                  String text = textMessage.getText();
                  int value = Integer.parseInt(text);
                  System.out.println("ping = " + value);
                  Util.sendText(session, pongProducer,
                        Integer.toString(value + 1));
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }

         }
      });
      System.in.read();
      
      closeActiveMQ();
   }

   private static void closeActiveMQ() throws JMSException, Exception {
      System.out.println("Finishing...");
      pongProducer.close();
      pongProducer.close();
      session.close();
      connection.close();
      brokerService.stopAllConnectors(new ServiceStopper());
      brokerService.stop();
   }

   private static void createActiveMQSession() throws JMSException {
      ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
            "tcp://localhost:61616");

      connection = connectionFactory.createConnection();
      connection.start();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
   }

   private static void createAndStartActiveMQBroker() throws Exception {
      brokerService = new BrokerService();
      brokerService.setBrokerName("consumer");
      brokerService.addConnector("tcp://localhost:61616");
      brokerService.addNetworkConnector("static://tcp://localhost:61617");
      brokerService.setPersistent(false);
      brokerService.start();
   }

   private static void configureLogger() {
      BasicConfigurator.configure();
      Logger.getRootLogger().setLevel(Level.INFO);
   }

}
