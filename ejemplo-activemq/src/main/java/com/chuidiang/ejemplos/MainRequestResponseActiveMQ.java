package com.chuidiang.ejemplos;

import java.io.IOException;

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

public class MainRequestResponseActiveMQ {
   private static final String QUEUE_NAME = "TEST.FOO";
   private static ActiveMQConnectionFactory connectionFactory;
   private static Connection connection;

   public static void main(String[] args) throws Exception {
      BrokerService broker = new BrokerService();
      broker.setPersistent(false);
      broker.setBrokerName("brokerExample");
      broker.start();
      connectionFactory = new ActiveMQConnectionFactory("vm://brokerExample");
      connection = connectionFactory.createConnection();
      connection.start();

      startMessageTransfer();

      
      
      connection.close();
      broker.stopAllConnectors(new ServiceStopper());
      broker.stop();
      
   }

   private static void startMessageTransfer() throws InterruptedException,
         JMSException, IOException {
      final Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

      MessageProducer producerForRequest = Util.createQueueProducer(session, QUEUE_NAME);

      Destination queueForResponse = session.createTemporaryQueue();
      MessageConsumer consumerForResponse = session
            .createConsumer(queueForResponse);

      consumerForResponse.setMessageListener(new MessageListener() {

         public void onMessage(Message message) {
            System.out.println("Received response : " + message);
         }
      });

      MessageConsumer consumerForRequest = Util.createQueueConsumer(session, QUEUE_NAME);
      consumerForRequest.setMessageListener(new MessageListener() {

         public void onMessage(Message request) {
            try {
               System.out.println("Answering ... ");
               TextMessage response = session.createTextMessage();
               response.setText("Response");
               response.setJMSCorrelationID(request.getJMSCorrelationID());
               
               MessageProducer producerForResponse = session.createProducer(request.getJMSReplyTo());
               producerForResponse.send(response);
               producerForResponse.close();
            } catch (Exception e) {
               e.printStackTrace();
            }

         }
      });
      
      TextMessage request = session.createTextMessage();
      request.setText("request");
      request.setJMSReplyTo(queueForResponse);
      producerForRequest.send(request);
      
      System.in.read();
      session.close();
   }
}
