package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class QueueConsumer {
   private MessageConsumer consumer;
   private Session session;
   private static int globalConsumerCounter=1;
   private int consumerNumber;

   public QueueConsumer(Connection connection, String queue) throws JMSException {
      consumerNumber  = globalConsumerCounter++;
      session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

      // Create the destination (Topic or Queue)
      Destination destination = session.createQueue(queue);

      consumer = session.createConsumer(destination);
   }
   
   public void start() throws JMSException {
      // Wait for a message
      while(true) {
         Message message = consumer.receive(1000);

         if (null==message){
            break;
         }
         if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println(consumerNumber + " received: " + text);
         } else {
            System.out.println(consumerNumber + " received: " + message);
         }
      }
      System.out.println(consumerNumber+" ended");
   }

   public void close() throws JMSException {
      consumer.close();
      session.close();
   }
}
