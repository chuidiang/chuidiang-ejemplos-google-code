package com.chuidiang.ejemplos;

import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

public class MainEmbeddedActiveMQ {
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
      broker.stop();
   }

   private static void startMessageTransfer() throws InterruptedException {
      Thread producerThread = new Thread() {
         public void run() {
            try {
               QueueProducer queueProducer = new QueueProducer(connection,
                     QUEUE_NAME);
               queueProducer.start();
               queueProducer.close();
               connection.close();
            } catch (Exception e) {
               e.printStackTrace();
            }

         }
      };
      producerThread.start();

      Thread consumerThread[] = new Thread[10];
      for (int i = 0; i < 10; i++) {
         consumerThread[i] = new Thread() {
            public void run() {
               try {
                  QueueConsumer queueConsumer = new QueueConsumer(connection,
                        QUEUE_NAME);
                  queueConsumer.start();
                  queueConsumer.close();
                  connection.close();
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
