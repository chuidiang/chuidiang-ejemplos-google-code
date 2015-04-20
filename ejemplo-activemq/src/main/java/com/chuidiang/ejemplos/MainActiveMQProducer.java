package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.network.NetworkConnector;
import org.apache.activemq.util.ServiceStopper;

public class MainActiveMQProducer {

   public static void main(String[] args) throws Exception {
      BrokerService brokerService = new BrokerService();
      brokerService.setBrokerName("producer");
      brokerService.addConnector("tcp://localhost:61617");
      NetworkConnector networkConnector = brokerService.addNetworkConnector("static://tcp://localhost:61616");
      networkConnector.setDuplex(true);
      brokerService.setPersistent(false);
      brokerService.start();

      ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
            "tcp://localhost:61617");

      Connection connection = connectionFactory.createConnection();
      connection.start();

      Session session = connection.createSession(false,
            Session.AUTO_ACKNOWLEDGE);

      Destination destination = session.createQueue("TEST.FOO");

      MessageProducer producer = session.createProducer(destination);
      producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

      for (int i = 0; i < 100; i++) {
         TextMessage message = session.createTextMessage("Hello " + i);
         producer.send(message);
         Thread.sleep(100);
      }
      System.out.println("Finishing...");
      producer.close();
      session.close();
      connection.close();
      brokerService.stopAllConnectors(new ServiceStopper());
      brokerService.stop();
   }
}
