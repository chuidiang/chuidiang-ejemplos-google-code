package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class QueueProducer {
   private MessageProducer producer;
   private Session session;

   public QueueProducer(Connection connection, String queue) throws JMSException {
      session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

      Destination destination = session.createQueue(queue);

      producer = session.createProducer(destination);
      producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

   }

   public void start() throws JMSException, InterruptedException {
      for (int i = 0; i < 10; i++) {
         
         TextMessage message = session.createTextMessage("Hello " + i);
         producer.send(message);
         Thread.sleep(100);
      }

      
   }

   public void close() throws JMSException {
      producer.close();
      session.close();
      
   }
}
