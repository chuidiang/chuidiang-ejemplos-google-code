package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MainEmbeddedActiveMQ {
   private static final String QUEUE_NAME = "TEST.FOO";
   private static ActiveMQConnectionFactory connectionFactory;
   private static Connection connection;

   public static void main(String[] args) throws Exception {
      connectionFactory = new ActiveMQConnectionFactory(
            "vm://localhost?broker.persistent=false");
      connection = connectionFactory.createConnection();
      connection.start();

      startMessageTransfer();

      connection.close();
   }

   private static void startMessageTransfer() throws InterruptedException {
      Thread producerThread = new Thread() {
         public void run() {
            try {
               Session session = connection.createSession(false,
                     Session.AUTO_ACKNOWLEDGE);
               MessageProducer producer = Util.createQueueProducer(session,
                     QUEUE_NAME);
               
               
               for (int i = 0; i < 100; i++) {
                  TextMessage message = session.createTextMessage("Hello " + i);
                  producer.send(message);
               }
               
               
               producer.close();
               session.close();
            } catch (Exception e) {
               e.printStackTrace();
            }

         }
      };
      producerThread.start();

      Thread consumerThread[] = new Thread[10];
      for (int i = 0; i < 10; i++) {
         consumerThread[i] = new Thread("Thread " + i) {
            public void run() {
               try {
                  Session session = connection.createSession(false,
                        Session.AUTO_ACKNOWLEDGE);
                  MessageConsumer consumer = Util.createQueueConsumer(session,
                        QUEUE_NAME);
                  
                  
                  while (true) {
                     Message message = consumer.receive(1000);
                     if (message instanceof TextMessage) {
                        System.out.println(Thread.currentThread().getName()
                              + " received "
                              + ((TextMessage) message).getText());
                     }
                     if (null == message) {
                        consumer.close();
                        session.close();
                        return;
                     }
                  }
                  
                  
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         };
         consumerThread[i].start();
      }

      producerThread.join();
      for (int i = 0; i < 10; i++) {
         consumerThread[i].join();
      }
   }
}
