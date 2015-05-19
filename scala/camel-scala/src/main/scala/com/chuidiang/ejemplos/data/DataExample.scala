package com.chuidiang.ejemplos.data

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.SimpleRegistry
import org.apache.log4j.BasicConfigurator
import org.apache.log4j.Logger
import org.apache.log4j.Level

object DataExample extends App {
  BasicConfigurator.configure()
  Logger.getRootLogger.setLevel(Level.INFO)
  val registry = new SimpleRegistry;
  registry.put("FromDecoder", new DataDecoder)
  registry.put("FromEncoder", new DataEncoder)
  registry.put("ToDecoder", new DataDecoder)
  registry.put("ToEncoder", new DataEncoder)
  
  val camelContext = new DefaultCamelContext(registry)
  
  camelContext.addRoutes(new RouteBuilder {
    override def configure : Unit = {
      from("netty:tcp://localhost:55556?decoder=#FromDecoder&encoder=#FromEncoder").to(
//      from("netty:tcp://localhost:55556?textline=true").to(
          "netty:tcp://localhost:55558?decoder=#ToDecoder&encoder=#ToEncoder")
//      from("netty:tcp://localhost:55556?textline=true") to "netty:tcp://localhost:55558?textline=true"
    }
  })
  camelContext.start
  Console.in.read()
}

