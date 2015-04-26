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
 * raised by de MainActiveMQProducer.
 * 
 * @author Chuidiang
 */
public class MainActiveMQConsumer {

   private static BrokerService brokerService;
   private static Connection connection;
   private static Session session;
   private static MessageProducer pongProducer;
   private static MessageConsumer pingConsumer;
   private static final String HOST1 = "localhost";
   private static final String PORT_HOST1 = "61617";
   private static final String HOST2 = "localhost";
   private static final String PORT_HOST2 = "61616";


   public static void main(String[] args) throws Exception {
      configureLogger();
      createAndStartActiveMQBroker();
      createActiveMQSession();

      pongProducer = Util.createQueueProducer(session, "PONG");
      pingConsumer = Util.createQueueConsumer(session, "PING");

      // When a message is received from de ping queue, a new message 
      // is sent to de pong queue.
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
      
      // It wait for a keyboard Intro to exit the program.
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
            MessageFormat.format("tcp://{0}:{1}",HOST2,PORT_HOST2));

      connection = connectionFactory.createConnection();
      connection.start();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
   }

   private static void createAndStartActiveMQBroker() throws Exception {
      brokerService = new BrokerService();
      brokerService.setBrokerName("consumer");
      brokerService.addConnector(MessageFormat.format("tcp://{0}:{1}",HOST2,PORT_HOST2));
      brokerService.addNetworkConnector(MessageFormat.format("static://tcp://{0}:{1}",HOST1, PORT_HOST1));
      brokerService.setPersistent(false);
      brokerService.start();
   }

   private static void configureLogger() {
      BasicConfigurator.configure();
      Logger.getRootLogger().setLevel(Level.INFO);
   }

}
