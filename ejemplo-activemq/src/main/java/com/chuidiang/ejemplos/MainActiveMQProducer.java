package com.chuidiang.ejemplos;

import java.text.MessageFormat;

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
import org.apache.activemq.util.ServiceStopper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This class raise an ActiveMQ broker which works in cluster with the ActiveMQ broker 
 * raised by de MainActiveMQConsumer.
 * 
 * @author Chuidiang
 */
public class MainActiveMQProducer {

   private static BrokerService brokerService;
   private static Connection connection;
   private static Session session;
   private static MessageProducer pingProducer;
   private static MessageConsumer pongConsumer;
   private static final String HOST1 = "localhost";
   private static final String PORT_HOST1 = "61617";
   private static final String HOST2 = "localhost";
   private static final String PORT_HOST2 = "61616";


   public static void main(String[] args) throws Exception {
      configureLogger();
      createAndStartActiveMQBroker();
      createActiveMQSession();

      pingProducer = Util.createQueueProducer(session, "PING");
      pongConsumer = Util.createQueueConsumer(session, "PONG");

      // When a message is received from pong queue, a message is sent
      // to the ping queue
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

      // Start the ping pong sending the first message.
      Util.sendText(session, pingProducer, "1");

      // It waits for a keyboard Intro to exit program.
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
            MessageFormat.format("tcp://{0}:{1}", HOST1, PORT_HOST1));

      connection = connectionFactory.createConnection();
      connection.start();

      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
   }

   private static void createAndStartActiveMQBroker() throws Exception {
      brokerService = new BrokerService();
      brokerService.setBrokerName("producer");
      brokerService.addConnector(MessageFormat.format("tcp://{0}:{1}", HOST1, PORT_HOST1));
      brokerService.addNetworkConnector(MessageFormat.format("static://tcp://{0}:{1}",HOST2, PORT_HOST2));
      brokerService.setPersistent(false);
      brokerService.start();
   }

   private static void configureLogger() {
      BasicConfigurator.configure();
      Logger.getRootLogger().setLevel(Level.INFO);
   }
}
