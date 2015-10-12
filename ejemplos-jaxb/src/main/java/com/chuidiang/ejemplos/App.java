package com.chuidiang.ejemplos;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.tempuri.po.ObjectFactory;
import org.tempuri.po.PurchaseOrderType;
import org.tempuri.po.USAddress;

/**
 * Hello world!
 * 
 */
public class App {
   public static void main(String[] args) throws Exception {
      java2xml();
      xml2java();

      java2XmlWithRoot();
      xml2javaWithRoot();
   }

   private static void xml2javaWithRoot() throws JAXBException {
      String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
            + "<La_Clase>"
            + "<El_Atributo>ey you</El_Atributo>"
            + "</La_Clase>";
      
      JAXBContext jc = JAXBContext.newInstance(UnaClase.class);
      Unmarshaller ums = jc.createUnmarshaller();
      
      UnaClase theObject = (UnaClase) (ums.unmarshal(new StringReader(xml)));
      
      System.out.println(theObject.getUnAtributo());
   }

   private static void java2XmlWithRoot() throws JAXBException {
      JAXBContext jc = JAXBContext.newInstance(UnaClase.class);
      Marshaller ms = jc.createMarshaller();

      ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      UnaClase anObject = new UnaClase();
      anObject.setUnAtributo("ey you");

      ms.marshal(anObject, System.out);
   }

   private static void xml2java() throws JAXBException, XMLStreamException,
         FactoryConfigurationError {
      String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
            + "<ns2:purchaseOrder xmlns=\"http://tempuri.org/po.xsd\" xmlns:ns2=\"info.source4code.jaxb.model\">"
            + "<shipTo>"
            + "<city>New York</city>"
            + "</shipTo>"
            + "<comment>This is a comment</comment>" + "</ns2:purchaseOrder>";

      JAXBContext jc = JAXBContext.newInstance(PurchaseOrderType.class);
      Unmarshaller unmarshaller = jc.createUnmarshaller();

      XMLStreamReader reader = XMLInputFactory.newFactory()
            .createXMLStreamReader(new StringReader(xml));

      JAXBElement<PurchaseOrderType> root = unmarshaller.unmarshal(reader,
            PurchaseOrderType.class);

      System.out.println("City = " + root.getValue().getShipTo().getCity());
      System.out.println("Comment = " + root.getValue().getComment());
   }

   private static void java2xml() throws JAXBException, PropertyException {
      PurchaseOrderType purchase = new PurchaseOrderType();
      purchase.setComment("This is a comment");
      USAddress address = new USAddress();
      address.setCity("New York");
      purchase.setShipTo(address);

      JAXBContext context = JAXBContext.newInstance(PurchaseOrderType.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      // QName qName = new QName("info.source4code.jaxb.model",
      // "purchaseOrder");
      // JAXBElement<PurchaseOrderType> root = new
      // JAXBElement<PurchaseOrderType>(
      // qName, PurchaseOrderType.class, purchase);

      ObjectFactory factory = new ObjectFactory();
      JAXBElement<PurchaseOrderType> root = factory
            .createPurchaseOrder(purchase);

      marshaller.marshal(root, System.out);
   }
}
