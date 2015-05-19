package com.chuidiang.ejemplos.string

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.SimpleRegistry
import org.apache.log4j.BasicConfigurator
import org.apache.log4j.Logger
import org.apache.log4j.Level

object CamelExample extends App {
  BasicConfigurator.configure()
  Logger.getRootLogger.setLevel(Level.INFO)
  val registry = new SimpleRegistry;
  registry.put("FromDecoder", new CustomDecoder("FromDecoder"))
  registry.put("FromEncoder", new CustomEncoder("FromEncoder"))
  registry.put("ToDecoder", new CustomDecoder("ToDecoder"))
  registry.put("ToEncoder", new CustomEncoder("ToEncoder"))
  
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

