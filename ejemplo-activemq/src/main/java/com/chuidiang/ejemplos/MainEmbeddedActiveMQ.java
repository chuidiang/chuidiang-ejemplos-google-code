package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

public class MainEmbeddedActiveMQ {
   private static ActiveMQConnectionFactory connectionFactory;
   private static Connection connection;
   
   public static void main(String[] args) throws Exception {
      BrokerService broker = new BrokerService();
      broker.setPersistent(false);
      broker.setBrokerName("brokerExample");
      broker.start();
      connectionFactory = new ActiveMQConnectionFactory(
            "vm://brokerExample");
      connection = connectionFactory.createConnection();
      connection.start();

      startMessageTransfer();
   }

   private static void startMessageTransfer() {
      new Thread() {
         

         public void run() {
            try {
               

               Session session = connection.createSession(false,
                     Session.AUTO_ACKNOWLEDGE);

               Destination destination = session.createQueue("TEST.FOO");

               MessageProducer producer = session.createProducer(destination);
               producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

               for (int i = 0; i < 10; i++) {
                  TextMessage message = session.createTextMessage("Hello " + i);
                  producer.send(message);
                  Thread.sleep(100);
               }
               producer.close();
               session.close();
               connection.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
            
         }
      }.start();
      
      new Thread(){
         public void run() {
            try {
               Consumer consumer = new Consumer(connection, "TEST.FOO");
               consumer.start();
               consumer.close();
               connection.close();

            } catch (Exception e){
               e.printStackTrace();
            }
         }
      }.start();
   }
}
