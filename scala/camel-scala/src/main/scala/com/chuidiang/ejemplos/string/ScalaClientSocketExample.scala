package com.chuidiang.ejemplos.string

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.net.SocketException

object ScalaClientSocketExample {
  def main(args: Array[String]): Unit = {
    new Client(55556)
  }
}

class Client(val port: Int) {
  val socket = new Socket("localhost", port)
  val reader = new BufferedReader(new InputStreamReader(socket.getInputStream))
  val writer = new PrintWriter(socket.getOutputStream)

  new LectorThread(reader).start

  for (i <- 0 until 10) {
    writer.println("Juan" + i)
    writer.flush
    Thread.sleep(1000)
  }
  socket.close
}

class LectorThread(reader: BufferedReader) extends Thread {
  override def run() = {
    var continue = true
    try {
      while (continue) {
        val line = Option(reader.readLine())
        line match {
          case Some(line) => println("Client Received "+line)
          case _ => continue=false; println("is null")
        }
      }
    } catch {
      case e: SocketException => println(e.getMessage)
    }
  }
}