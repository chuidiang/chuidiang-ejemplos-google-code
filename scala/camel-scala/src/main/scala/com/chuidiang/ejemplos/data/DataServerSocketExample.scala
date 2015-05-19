package com.chuidiang.ejemplos.data

import java.net._
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.io.InputStream
import java.io.OutputStream
import scala.collection.mutable.ArrayOps
import java.nio.ByteBuffer
import java.util.Arrays

object DataServerSocketExample {
  def main(args: Array[String]): Unit = {
    new DataServer(55558).start
  }
}

class DataClientThread(private val socket: Socket) extends Thread {

  override def run() = {
    val reader = socket.getInputStream
    val writer = socket.getOutputStream
    val thread = new DataServerLectorThread(reader, writer)
    thread.start
    thread.join
    socket.close
  }
}

class DataServer(val port: Int) extends Thread {
  override def run = {
    val serverSocket = new ServerSocket(port)
    while (true) {
      val socket = serverSocket.accept
      new DataClientThread(socket).start
    }
  }
}

class DataServerLectorThread(reader: InputStream, writer: OutputStream) extends Thread {
  override def run() = {
    var bytes = Array.fill(1000){0.toByte}
    var readed=0
    var alreadyReaded = 0
    
    readed=reader.read(bytes,alreadyReaded,bytes.length-alreadyReaded)
    while (readed > 0) {
      val found = ProtocolConstants.searchMessage(ByteBuffer.wrap(bytes.slice(0,alreadyReaded)))
      
      found match {
        case (None,None) => alreadyReaded +=  readed 
        case _ => {
          val msgReceived = new Protocol(null)
          val msgBytes = bytes.slice(found._1.get, found._2.get)
          msgReceived.fromBytes(msgBytes)
          println(msgReceived.data)
          bytes = bytes.slice(found._2.get,bytes.length) ++ Array.fill(bytes.length-found._2.get){0.toByte}
          readed=0
          alreadyReaded=0
        }
      }
      val msgReceived = new Protocol(null)
      msgReceived.fromBytes(bytes)
      println("Received " +msgReceived.data)
      val msgToSend = new Protocol(new Data(123,"This is the response"))
      writer.write(msgToSend.getBytes())
      readed=reader.read(bytes)
    }
  }
}