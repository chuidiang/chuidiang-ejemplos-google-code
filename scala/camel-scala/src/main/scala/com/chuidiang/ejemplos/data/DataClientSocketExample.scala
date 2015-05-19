package com.chuidiang.ejemplos.data

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.net.SocketException
import java.io.InputStream

object DataClientSocketExample {
  def main(args: Array[String]): Unit = {
    new DataClient(55556)
  }
}

class DataClient(val port: Int) {
  val socket = new Socket("localhost", port)
  val reader = socket.getInputStream
  val writer =socket.getOutputStream

  new DataLectorThread(reader).start

  for (i <- 0 until 10) {
    val data = new Data(i,"Juan")
    val msgToSend = new Protocol(data)
    writer.write(msgToSend.getBytes())
    
    Thread.sleep(1000)
  }
  socket.close
}

class DataLectorThread(reader: InputStream) extends Thread {
  override def run() = {
    var continue = true
    try {
      while (continue) {
        reader.read()
//        val line = Option(reader.readLine())
//        line match {
//          case Some(line) => println("Client Received "+line)
//          case _ => continue=false; println("is null")
//        }
      }
    } catch {
      case e: SocketException => println(e.getMessage)
    }
  }
}