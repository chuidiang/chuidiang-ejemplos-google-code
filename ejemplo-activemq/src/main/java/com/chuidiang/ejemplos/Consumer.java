package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer {
   private MessageConsumer consumer;
   private Session session;

   public Consumer(Connection connection, String queue) throws JMSException {
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
            System.out.println("Received: " + text);
         } else {
            System.out.println("Received: " + message);
         }
      }
      System.out.println("Finishing...");
   }

   public void close() throws JMSException {
      consumer.close();
      session.close();
   }
}
