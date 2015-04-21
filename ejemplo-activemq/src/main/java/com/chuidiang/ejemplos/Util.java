package com.chuidiang.ejemplos;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Util {
   public static MessageProducer createQueueProducer(Session session, String queue) throws JMSException{
      Destination destination = session.createQueue(queue);
      MessageProducer producer = session.createProducer(destination);
      producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
      return producer;
   }
   
   public static MessageProducer createTopicProducer(Session session, String topic) throws JMSException{
      Destination destination = session.createTopic(topic);
      MessageProducer producer = session.createProducer(destination);
      producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
      return producer;
   }
   
   public static MessageConsumer createQueueConsumer(Session session, String queue) throws JMSException{
      Destination destination = session.createQueue(queue);
      MessageConsumer consumer = session.createConsumer(destination);
      return consumer;
   }
   
   public static MessageConsumer createTopicConsumer(Session session, String topic) throws JMSException{
      Destination destination = session.createTopic(topic);
      MessageConsumer consumer = session.createConsumer(destination);
      return consumer;
   }

   public static void sendText(Session session, MessageProducer pingProducer, String value) throws JMSException {
      TextMessage message = session.createTextMessage(value);
      pingProducer.send(message);
   }
   
}
