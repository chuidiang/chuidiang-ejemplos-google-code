package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.network.NetworkConnector;
import org.apache.activemq.util.ServiceStopper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class MainActiveMQConsumer {

	public static void main(String[] args) throws Exception {
	   BasicConfigurator.configure();
	   Logger.getRootLogger().setLevel(Level.INFO);
      BrokerService brokerService = new BrokerService();
      brokerService.setBrokerName("consumer");
      brokerService.addConnector("tcp://localhost:61616");
      NetworkConnector connector = brokerService.addNetworkConnector("static://tcp://localhost:61617");
      connector.setDuplex(true);
      brokerService.setPersistent(false);
      brokerService.start();

	   
	   
	   
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");

		// Create a Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();

		connection.setExceptionListener(new ExceptionListener() {

			public void onException(JMSException ex) {
				System.out.println("Error " + ex.getMessage());

			}
		});

		// Create a Session
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("TEST.FOO");

		// Create a MessageConsumer from the Session to the Topic or Queue
		MessageConsumer consumer = session.createConsumer(destination);

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
		consumer.close();
		session.close();
		connection.close();
		brokerService.stopAllConnectors(new ServiceStopper());
		brokerService.stop();
	}

}
