package com.chuidiang.ejemplos;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MainActiveMQProducer {

	public static void main(String[] args) throws Exception{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"nio://localhost:61616");

		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue("TEST.FOO");
		
		MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        
        for (int i=0;i<10;i++){
        	TextMessage message = session.createTextMessage("Hello "+i);
        	producer.send(message);
        	Thread.sleep(100);
        }
        System.out.println("Finishing...");
        producer.close();
        session.close();
        connection.close();
	}
}
